package za.co.skizzel.domain.entities;


import java.util.Collection;

public class Receipt {

   private long receiptId;

   private long userId;

   private String dateCreated;

   private String alias;

   private String filterDate;

   private long categoryId;

   private Collection<Image> receiptImageCollection;

   public Collection<Image> getReceiptImageCollection() {
      return receiptImageCollection;
   }

   public void setReceiptImageCollection(Collection<Image> receiptImageCollection) {
      this.receiptImageCollection = receiptImageCollection;
   }

   public String getFilterDate() {
      return filterDate;
   }

   public void setFilterDate(String filterDate) {
      this.filterDate = filterDate;
   }

   public long getReceiptId() {
      return receiptId;
   }

   public void setReceiptId(long receiptId) {
      this.receiptId = receiptId;
   }

   public long getUserId() {
      return userId;
   }

   public void setUserId(long userId) {
      this.userId = userId;
   }

   public String getDateCreated() {
      return dateCreated;
   }

   public void setDateCreated(String dateCreated) {
      this.dateCreated = dateCreated;
   }

   public String getAlias() {
      return alias;
   }

   public void setAlias(String alias) {
      this.alias = alias;
   }

   public long getCategoryId() {
      return categoryId;
   }

   public void setCategoryId(long categoryId) {
      this.categoryId = categoryId;
   }

   public Receipt(){}


}
