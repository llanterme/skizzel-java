package za.co.skizzel.infrastructure.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="user")
public class UserEntity {

  @Id
  @GeneratedValue
  @Column(name="userId", unique = true, nullable = false, updatable = false )
  private Long userId;

  @Column(name="email")
  private String email;

  @Column(name="password")
  private String password;

  @Column(name="name")
  private String name;

  @OneToMany(fetch = FetchType.EAGER, mappedBy = "userEntity" , cascade = { CascadeType.ALL } )
  private Set<ReceiptEntity> receiptEntitySet;

   @OneToMany(fetch = FetchType.EAGER, mappedBy = "userEntity" , cascade = { CascadeType.ALL } )
   private Set<CategoryEntity> categoryEntitySet;

   public Set<CategoryEntity> getCategoryEntitySet() {
      return categoryEntitySet;
   }

   public void setCategoryEntitySet(Set<CategoryEntity> categoryEntitySet) {
      this.categoryEntitySet = categoryEntitySet;
   }

   public Long getUserId() {
      return userId;
   }

   public void setUserId(Long userId) {
      this.userId = userId;
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

   public Set<ReceiptEntity> getReceiptEntitySet() {
      return receiptEntitySet;
   }

   public void setReceiptEntitySet(Set<ReceiptEntity> receiptEntitySet) {
      this.receiptEntitySet = receiptEntitySet;
   }

   public UserEntity(){};

   public UserEntity(long userId){
      this.userId = userId;
   };

}
