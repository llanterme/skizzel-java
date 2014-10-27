package za.co.skizzel.infrastructure.entities;


import javax.persistence.*;

@Entity
@Table(name="image")
public class ImageEntity {

   @Id
   @GeneratedValue
   @Column(name="imageId", unique = true, nullable = false, updatable = false )
   private Long imageId;

   @Column(name="imageURL")
   private String imageURL;

   @ManyToOne
   @JoinColumn(name="receiptId", nullable=false, insertable = false, updatable = false)
   private ReceiptEntity receiptEntity;

   private long receiptId;

   public Long getImageId() {
      return imageId;
   }

   public void setImageId(Long imageId) {
      this.imageId = imageId;
   }

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

   public ReceiptEntity getReceiptEntity() {
      return receiptEntity;
   }

   public void setReceiptEntity(ReceiptEntity receiptEntity) {
      this.receiptEntity = receiptEntity;
   }
}
