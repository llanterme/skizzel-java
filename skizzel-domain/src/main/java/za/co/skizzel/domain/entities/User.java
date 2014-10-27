package za.co.skizzel.domain.entities;


import java.util.Collection;

public class User {

   private String email;

   private String password;

   private String name;

   private long userId;

   private Collection<Receipt> receiptList;

   private Collection<Category> categoriesList;

   public Collection<Category> getCategoriesList() {
      return categoriesList;
   }

   public void setCategoriesList(Collection<Category> categoriesList) {
      this.categoriesList = categoriesList;
   }

   public Collection<Receipt> getReceiptList() {
      return receiptList;
   }

   public void setReceiptList(Collection<Receipt> receiptList) {
      this.receiptList = receiptList;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public long getUserId() {
      return userId;
   }

   public void setUserId(long userId) {
      this.userId = userId;
   }

   public User() {}

   public User(long userId) {
      this.userId = userId;
   }


}
