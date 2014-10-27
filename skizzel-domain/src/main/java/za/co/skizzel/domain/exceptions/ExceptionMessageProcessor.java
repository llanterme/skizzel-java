package za.co.skizzel.domain.exceptions;

/**
 * Created by Luke on 2014/10/08.
 */
public class ExceptionMessageProcessor {

   public static void processErrorCodes(ExceptionHandler e) throws ExceptionHandler {
      String s = e.getErrorCode();
      if (s.equals("BAD_FILE_TYPE")) {
         System.out.println("Bad File Type, notify user");
         throw e;
      } else if (s.equals("ERROR_CREATING_USER")) {
         System.out.println("error creating new user.");
         throw e;
      } else if (s.equals("FILE_CLOSE_EXCEPTION")) {
         System.out.println("File Close failed, just log it.");

      } else {
         System.out.println("Unknown exception occured, lets log it for further debugging." + e.getMessage());
         e.printStackTrace();
      }
   }

}
