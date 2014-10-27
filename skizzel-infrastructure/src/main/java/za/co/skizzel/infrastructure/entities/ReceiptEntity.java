package za.co.skizzel.infrastructure.entities;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="receipt")
public class ReceiptEntity {

  @Id
  @GeneratedValue
  @Column(name="ReceiptId", unique = true, nullable = false, updatable = false )
  private Long receiptId;

  @Column(name="alias")
  private String alias;

   @Column(name="filterDate")
   private String filterDate;

   @Column(name="categoryId")
   private long categoryId;

  @Column(name="DateCreated")
  private String dateCreated;

  @ManyToOne
  @JoinColumn(name="userId", nullable=false, insertable = false, updatable = false)
  private UserEntity userEntity;

   @OneToMany(fetch = FetchType.EAGER, mappedBy = "receiptEntity" , cascade = { CascadeType.ALL } )

   private Set<ImageEntity> imageEntitySet;

   private long userId;

   public String getFilterDate() {
      return filterDate;
   }

   public void setFilterDate(String filterDate) {
      this.filterDate = filterDate;
   }

   public long getUserId() {
      return userId;
   }

   public void setUserId(long userId) {
      this.userId = userId;
   }

   public long getCategoryId() {
      return categoryId;
   }

   public void setCategoryId(long categoryId) {
      this.categoryId = categoryId;
   }

   public Long getReceiptId() {
    return receiptId;
  }

  public void setReceiptId(Long receiptId) {
    this.receiptId = receiptId;
  }

  public String getAlias() {
    return alias;
  }

  public void setAlias(String alias) {
    this.alias = alias;
  }

  public String getDateCreated() {
    return dateCreated;
  }

  public void setDateCreated(String dateCreated) {
    this.dateCreated = dateCreated;
  }

  public UserEntity getUserEntity() {
    return userEntity;
  }

  public void setUserEntity(UserEntity userEntity) {
    this.userEntity = userEntity;
  }

   public ReceiptEntity() {};

}
