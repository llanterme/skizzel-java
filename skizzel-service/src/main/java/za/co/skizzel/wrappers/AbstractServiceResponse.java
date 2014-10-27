package za.co.skizzel.wrappers;


public class AbstractServiceResponse {

   private String message;

   private String status;

   public String getMessage() {
      return message;
   }

   public void setMessage(String message) {
      this.message = message;
   }

   public String getStatus() {
      return status;
   }

   public void setStatus(String status) {
      this.status = status;
   }

   public AbstractServiceResponse(){}

   public AbstractServiceResponse(String message){
      this.message = message;
   }

   public AbstractServiceResponse(String message, String status)
   {
      this.message = message;
      this.status = status;
   }


}
