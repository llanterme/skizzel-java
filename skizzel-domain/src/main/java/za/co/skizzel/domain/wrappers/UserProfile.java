package za.co.skizzel.domain.wrappers;

import za.co.skizzel.domain.entities.User;

public class UserProfile {

   private User user;


   public User getUser() {
      return user;
   }

   public void setUser(User user) {
      this.user = user;
   }
}
