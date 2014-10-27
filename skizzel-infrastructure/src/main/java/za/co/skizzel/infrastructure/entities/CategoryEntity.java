package za.co.skizzel.infrastructure.entities;

import javax.persistence.*;

@Entity
@Table(name="category")
public class CategoryEntity {

   @Id
   @GeneratedValue
   @Column(name="categoryId", unique = true, nullable = false, updatable = false )
   private Long categoryId;

   private Long userId;

   @Column(name="type")
   private String type;

   @ManyToOne
   @JoinColumn(name="userId", nullable=false, insertable = false, updatable = false)
   private UserEntity userEntity;

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

   public String getType() {
      return type;
   }

   public void setType(String type) {
      this.type = type;
   }

   public CategoryEntity(){};

}
