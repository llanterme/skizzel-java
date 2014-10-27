package za.co.skizzel.domain.logic;

import za.co.skizzel.domain.entities.*;
import za.co.skizzel.domain.utils.Utils;
import za.co.skizzel.infrastructure.dao.*;
import za.co.skizzel.infrastructure.entities.*;

import java.io.*;
import java.lang.Exception;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SkizzelLogic {

   private SkizzelDao skizzelDao;
   private Utils utils;

   public SkizzelLogic() {
      skizzelDao = new SkizzelDao();
      utils = new Utils();
   }

   public long createUser(User user) {

      try {

         UserEntity newUserEntity = new UserEntity();
         newUserEntity.setName(user.getName());
         newUserEntity.setEmail(user.getEmail());
         newUserEntity.setPassword(user.getPassword());

         UserEntity createdUser = skizzelDao.createUser(newUserEntity);

         return createdUser.getUserId();

      } catch (Exception ex) {
         System.out.print(ex.getMessage());
      }
      return 0;
   }

   public long createReceipt(Receipt receipt) {

      try {

         ReceiptEntity newReceiptEntity = new ReceiptEntity();
         newReceiptEntity.setAlias(receipt.getAlias());
         newReceiptEntity.setDateCreated(receipt.getDateCreated());
         newReceiptEntity.setCategoryId(receipt.getCategoryId());
         newReceiptEntity.setUserId(receipt.getUserId());
         newReceiptEntity.setFilterDate(utils.formatDate(receipt.getDateCreated()));
         UserEntity userEntity = new UserEntity(receipt.getUserId());
         newReceiptEntity.setUserEntity(userEntity);

         ReceiptEntity createdReceipt = skizzelDao.createReceipt(newReceiptEntity);

         return createdReceipt.getReceiptId();

      } catch (Exception ex) {
         System.out.print(ex.getMessage());
      }

      return 0;

   }

   public long createImage(Image image) {
      try {
         ImageEntity imageEntity = new ImageEntity();
         imageEntity.setImageURL(image.getImageURL());
         imageEntity.setReceiptId(image.getReceiptId());

         return skizzelDao.createImage(imageEntity).getReceiptId();
      } catch (Exception ex) {

         System.out.print(ex.getMessage());
      }

      return 0;
   }

   public List<Receipt> getUserReceiptOverview(long userId, String receiptMonth) {

      List<Receipt> receiptOverview = new ArrayList<Receipt>();
      List<ReceiptEntity> receiptDao = skizzelDao.getUserReceiptOverview(userId, receiptMonth);

      for (ReceiptEntity receipt : receiptDao) {

         Receipt newReceipt = new Receipt();
         newReceipt.setReceiptImageCollection(this.getReceiptImages(receipt.getReceiptId()));
         newReceipt.setAlias(receipt.getAlias());
         newReceipt.setDateCreated(receipt.getDateCreated());
         newReceipt.setCategoryId(receipt.getCategoryId());
         newReceipt.setUserId(userId);
         newReceipt.setReceiptId(receipt.getReceiptId());

         receiptOverview.add(newReceipt);
      }


      try {

      } catch (Exception ex) {
         System.out.print(ex.getMessage());

      }

      return receiptOverview;
   }

   public User getUserOverView(long userId, String receiptMonth) {

      String[] monthRaw = receiptMonth.split("_");
      String selectedReceiptMonth = monthRaw[0] + " " + monthRaw[1];
      User userProfile = new User();
      try {

         UserEntity user = skizzelDao.getUserOverView(userId, selectedReceiptMonth);
         userProfile.setName(user.getName());
         userProfile.setEmail(user.getEmail());
         userProfile.setUserId(user.getUserId());

         Collection<Receipt> filteredReceipts = new ArrayList<Receipt>();
         if (user.getReceiptEntitySet().size() > 0) {

            for (ReceiptEntity receipt : user.getReceiptEntitySet()) {

               if (receipt.getFilterDate().equals(selectedReceiptMonth)) {

                  Receipt newReceipt = new Receipt();
                  newReceipt.setReceiptImageCollection(this.getReceiptImages(receipt.getReceiptId()));
                  newReceipt.setAlias(receipt.getAlias());
                  newReceipt.setDateCreated(receipt.getDateCreated());
                  newReceipt.setCategoryId(receipt.getCategoryId());
                  newReceipt.setUserId(userId);
                  newReceipt.setReceiptId(receipt.getReceiptId());
                  newReceipt.setFilterDate(receipt.getFilterDate());

                  filteredReceipts.add(newReceipt);
               }

            }

         }

         userProfile.setReceiptList(filteredReceipts);

         if (user.getCategoryEntitySet().size() > 0) {
            Collection<Category> categoryCollection = new ArrayList<Category>();

            for (CategoryEntity item : user.getCategoryEntitySet()) {
               Category category = new Category();
               category.setCategory(item.getType());
               category.setCategoryId(item.getCategoryId());
               category.setUserId(userId);

               categoryCollection.add(category);
            }
            userProfile.setCategoriesList(categoryCollection);
         }

      } catch (Exception ex) {
         System.out.print(ex.getMessage());
         return null;
      }

      return userProfile;
   }

   public long authenticateUser(String email, String password) {
      try {
         if (skizzelDao.authUser(email, password)) {
            return getUserId(email);
         }
      } catch (Exception ex) {
         System.out.print(ex.getMessage());
      }

      return 0;
   }

   public void saveToDisc(final InputStream fileInputStream, final String fileName) throws IOException {
      try {

         final Utils propertiesManager = new Utils();

         String[] rawFileValues = fileName.split("_");
         int receiptId = Integer.parseInt(rawFileValues[1]);
         String fileNameToSave = java.util.UUID.randomUUID() + "_" + rawFileValues[0].trim();

         String SERVER_UPLOAD_LOCATION_FOLDER = propertiesManager.getPropValues("uploadPath");

         String filePath = SERVER_UPLOAD_LOCATION_FOLDER + fileNameToSave;

         final int BUFFER_SIZE = 1024;

         final OutputStream out = new FileOutputStream(new File(filePath));
         int read = 0;
         byte[] bytes = new byte[BUFFER_SIZE];

         while ((read = fileInputStream.read(bytes)) != -1) {
            out.write(bytes, 0, read);
         }

         out.flush();
         out.close();

         Image newImage = new Image();
         newImage.setReceiptId(receiptId);
         newImage.setImageURL(fileNameToSave);
         createImage(newImage);

      } catch (IOException ex) {
         System.out.print(ex.getMessage());
      }
   }

   public List<GroupedDate> getUserMonths(long userId) {

      final Utils utils = new Utils();

      List<GroupedDate> groupingList = new ArrayList<GroupedDate>();

      try {
         for (String date : skizzelDao.getUserMonths(userId)) {
            GroupedDate newListItem = new GroupedDate();
            newListItem.setDateCreated(date);
            newListItem.setDateCount(skizzelDao.getUserMonthCount(userId, date));
            groupingList.add(newListItem);
         }

         return groupingList;

      } catch (Exception ex) {
         System.out.print(ex.getMessage());
      }

      return null;
   }

   private Collection<Image> getReceiptImages(long receiptId) {

      final Utils propertiesManager = new Utils();

      try {

         List<ImageEntity> theImages = skizzelDao.getReceiptImageList(receiptId);

         Collection<Image> imageCollection = new ArrayList<Image>();
         if (theImages != null) {
            for (ImageEntity image : theImages) {
               Image newImage = new Image();
               newImage.setImageURL(propertiesManager.getPropValues("ImageDownloadURL") + image.getImageURL());
               newImage.setImageId(image.getImageId());
               newImage.setReceiptId(receiptId);

               imageCollection.add(newImage);
            }
         }

         return imageCollection;
      } catch (Exception ex) {
         System.out.print(ex.getMessage());
      }

      return null;
   }

   private long getUserId(String email) {
      return skizzelDao.getUser(email).getUserId();
   }

}
