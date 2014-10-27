package za.co.skizzel.domain.entities;

public class Image {

   private long receiptId;

   private String imageURL;

   private long imageId;

   public long getReceiptId() {
      return receiptId;
   }

   public void setReceiptId(long receiptId) {
      this.receiptId = receiptId;
   }

   public String getImageURL() {
      return imageURL;
   }

   public void setImageURL(String imageURL) {
      this.imageURL = imageURL;
   }

   public long getImageId() {
      return imageId;
   }

   public void setImageId(long imageId) {
      this.imageId = imageId;
   }

}
