package za.co.skizzel.service;


import za.co.skizzel.domain.entities.GroupedDate;
import za.co.skizzel.domain.entities.Image;
import za.co.skizzel.domain.entities.Receipt;
import za.co.skizzel.domain.entities.User;
import za.co.skizzel.domain.exceptions.ExceptionHandler;
import za.co.skizzel.domain.exceptions.ExceptionMessageProcessor;
import za.co.skizzel.domain.logic.SkizzelLogic;
import za.co.skizzel.wrappers.AbstractServiceResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.InputStream;
import java.util.List;

@Path("/service")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class RestService {

   private SkizzelLogic skizzelLogic;

   public RestService() {
      this.skizzelLogic = new SkizzelLogic();
   }

   @POST
   @Path("/CreateUser")
   public Response createUser(User user) {

      AbstractServiceResponse serviceResponse;
      Long userId = skizzelLogic.createUser(user);
      if (userId != 0) {
         serviceResponse = new AbstractServiceResponse(userId.toString(), "success");
         return Response.status(201).entity(serviceResponse).build();
      } else {

         return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error").build();
      }

   }

   @POST
   @Path("/Authenticate")
   public Response authenticateUser(User user) {

      AbstractServiceResponse serviceResponse;
      Long userId = skizzelLogic.authenticateUser(user.getEmail(), user.getPassword());
      try {

         if (userId != 0) {
            serviceResponse = new AbstractServiceResponse(userId.toString(), "success");
            return Response.status(201).entity(serviceResponse).build();
         } else {
            serviceResponse = new AbstractServiceResponse("denied", "denied");
            return Response.status(201).entity(serviceResponse).build();
         }
      } catch (Exception ex) {
         return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error").build();
      }


   }

   @POST
   @Path("/CreateReceipt")
   public Response createReceipt(Receipt receipt) {

      AbstractServiceResponse serviceResponse;
      Long receiptId = skizzelLogic.createReceipt(receipt);
      if (receiptId != 0) {
         serviceResponse = new AbstractServiceResponse(receiptId.toString(), "success");
         return Response.status(201).entity(serviceResponse).build();
      } else {

         return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error").build();
      }

   }

   @POST
   @Path("/CreateImage")
   public Response createImage(Image image) {

      AbstractServiceResponse serviceResponse;
      Long imageId = skizzelLogic.createImage(image);
      if (imageId != 0) {
         serviceResponse = new AbstractServiceResponse(imageId.toString(), "success");
         return Response.status(201).entity(serviceResponse).build();
      } else {

         return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error").build();
      }

   }

   @GET
   @Path("/UserOverView/{userId}/{receiptMonth}")
   @Consumes(MediaType.APPLICATION_JSON)
   @Produces(MediaType.APPLICATION_JSON)
   public Response getUserOverView(@PathParam("userId") long userId, @PathParam("receiptMonth") String receiptMonth) {

      User userOverview = skizzelLogic.getUserOverView(userId,receiptMonth);

      if (userOverview != null) {
         return Response.status(200).entity(userOverview).build();
      } else {
         return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error").build();
      }

   }

   @GET
   @Path("/UserReceiptOverview/{userId}/{receiptMonth}")
   @Consumes(MediaType.APPLICATION_JSON)
   @Produces(MediaType.APPLICATION_JSON)
   public Response getUserReceiptOverview(@PathParam("userId") long userId, @PathParam("receiptMonth") String receiptMonth) {

      List<Receipt> receiptOverview = skizzelLogic.getUserReceiptOverview(userId,receiptMonth);

      if (receiptOverview != null) {
         return Response.status(200).entity(receiptOverview).build();
      } else {
         return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error").build();
      }

   }

   @GET
   @Path("/UserDates/{userId}")
   @Consumes(MediaType.APPLICATION_JSON)
   @Produces(MediaType.APPLICATION_JSON)
   public Response getUserDates(@PathParam("userId") long userId) {

      List<GroupedDate> groupedDate = skizzelLogic.getUserMonths(userId);

      if (groupedDate != null) {
         return Response.status(200).entity(groupedDate).build();
      } else {
         return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error").build();
      }

   }


   @POST
   @Path("/Upload")
   @Consumes(MediaType.APPLICATION_OCTET_STREAM)
   @Produces(MediaType.APPLICATION_JSON)
   public Response putFile(@QueryParam("attachmentName") String attachmentName, InputStream fileInputStream) throws Throwable {

      skizzelLogic.saveToDisc(fileInputStream, attachmentName);

      AbstractServiceResponse serviceResponse = new AbstractServiceResponse("success", "success");

      return Response.status(200).entity(serviceResponse).build();

   }


}



