package za.co.skizzel.domain.entities;

public class GroupedDate {

   private String dateCreated;

   private long dateCount;

   public long getDateCount() {
      return dateCount;
   }

   public void setDateCount(long dateCount) {
      this.dateCount = dateCount;
   }

   public String getDateCreated() {
      return dateCreated;
   }

   public void setDateCreated(String dateCreated) {
      this.dateCreated = dateCreated;
   }
}
