package za.co.skizzel.domain.entities;

public class Category {

   private Long categoryId;

   private Long userId;

   private String Category;

   public Long getCategoryId() {
      return categoryId;
   }

   public void setCategoryId(Long categoryId) {
      this.categoryId = categoryId;
   }

   public Long getUserId() {
      return userId;
   }

   public void setUserId(Long userId) {
      this.userId = userId;
   }

   public String getCategory() {
      return Category;
   }

   public void setCategory(String category) {
      Category = category;
   }

   public Category(){};

}
