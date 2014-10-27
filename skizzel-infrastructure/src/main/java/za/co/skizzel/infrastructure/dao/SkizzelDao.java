package za.co.skizzel.infrastructure.dao;

import org.hibernate.*;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import za.co.skizzel.infrastructure.entities.*;
import za.co.skizzel.infrastructure.util.*;
import java.util.List;

public class SkizzelDao {

   public UserEntity createUser(UserEntity userEntity) {

      Session session = HibernateUtil.getSessionFactory().openSession();

      Transaction tx = null;
      try {
         tx = session.beginTransaction();

         session.save(userEntity);

         tx.commit();

         return userEntity;
      } catch (HibernateException e) {
         if (tx != null) {
            tx.rollback();
         }
         e.printStackTrace();
      } finally {
         session.close();
      }
      return null;
   }

   public ReceiptEntity createReceipt(ReceiptEntity receiptEntity) {

      Session session = HibernateUtil.getSessionFactory().openSession();

      Transaction tx = null;
      try {
         tx = session.beginTransaction();

         session.save(receiptEntity);

         tx.commit();

         return receiptEntity;
      } catch (HibernateException e) {
         if (tx != null) {
            tx.rollback();
         }
         e.printStackTrace();
      } finally {
         session.close();
      }
      return null;
   }

   public ImageEntity createImage(ImageEntity imageEntity) {

      Session session = HibernateUtil.getSessionFactory().openSession();

      Transaction tx = null;
      try {
         tx = session.beginTransaction();

         session.save(imageEntity);

         tx.commit();

         return imageEntity;
      } catch (HibernateException e) {
         if (tx != null) {
            tx.rollback();
         }
         e.printStackTrace();
      } finally {
         session.close();
      }
      return null;
   }

   public UserEntity getUserOverView(long userId, String receiptMonth) {

      Session session = HibernateUtil.getSessionFactory().openSession();
       UserEntity user = new UserEntity();

      try {
         session.beginTransaction();

              Criteria criteria = session.createCriteria(UserEntity.class, "user")
               .add(Restrictions.eq("userId", userId));

         user = (UserEntity) criteria.uniqueResult();

      } catch (Exception ex) {

         System.out.print(ex.getMessage());
      } finally {

         session.getTransaction().commit();
         session.close();

      }

      return user;
   }

   public List<ReceiptEntity> getUserReceiptOverview(long userId, String receiptMonth) {

      Session session = HibernateUtil.getSessionFactory().openSession();

      try {
         session.beginTransaction();
         Criteria criteria = session.createCriteria(ReceiptEntity.class)
             .add(Restrictions.eq("dateCreated", receiptMonth))
             .add(Restrictions.eq("userId", userId));

         criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

         List list = criteria.list();

         return list;
      } catch (Exception ex) {

      } finally {
         session.getTransaction().commit();
         session.close();

      }

      return null;

   }

   public List<ImageEntity> getReceiptImageList(long receiptId) {

      Session session = HibernateUtil.getSessionFactory().openSession();

      try {
         session.beginTransaction();
         Criteria criteria = session.createCriteria(ImageEntity.class, "image")
             .add(Restrictions.eq("receiptId", receiptId));
         List list = criteria.list();

         return list;
      } catch (Exception ex) {

      } finally {
         session.getTransaction().commit();
         session.close();

      }

      return null;

   }

   public Boolean authUser(String email, String password) {

      Session session = HibernateUtil.getSessionFactory().openSession();

      Criteria criteria = session.createCriteria(UserEntity.class)
          .add(Restrictions.eq("email", email))
          .add(Restrictions.eq("password", password));

      if (criteria.uniqueResult() != null) {
         return true;
      } else {
         return false;
      }
   }

   public UserEntity getUser(String email) {

      Session session = HibernateUtil.getSessionFactory().openSession();
      UserEntity user = new UserEntity();

      try {
         session.beginTransaction();
         Criteria criteria = session.createCriteria(UserEntity.class, "user")
             .add(Restrictions.eq("email", email));
         user = (UserEntity) criteria.uniqueResult();
      } catch (Exception ex) {

      } finally {
         session.getTransaction().commit();
         session.close();

      }

      return user;

   }

   public List<String> getUserMonths(long userId) {

      Session session = HibernateUtil.getSessionFactory().openSession();

      try {
         session.beginTransaction();
         Criteria criteria = session.createCriteria(ReceiptEntity.class)
         .add(Restrictions.eq("userId", userId));
        criteria.setProjection(Projections.distinct(Projections.property("filterDate")));

         List<String> list  = criteria.list();

         return list;
      } catch (Exception ex) {
         System.out.print(ex.getMessage());
      } finally {
         session.getTransaction().commit();
         session.close();

      }

      return null;

   }

   public Long getUserMonthCount(long userId, String receiptMonth) {

      Session session = HibernateUtil.getSessionFactory().openSession();

         Long monthCount = 12345678910L;

      try {
         session.beginTransaction();
         Criteria criteria = session.createCriteria(ReceiptEntity.class)
             .add(Restrictions.eq("userId", userId))
             .add(Restrictions.eq("filterDate", receiptMonth));
         criteria.setProjection(Projections.rowCount()).uniqueResult();

         monthCount = (Long) criteria.uniqueResult();

         return monthCount;
      } catch (Exception ex) {
         System.out.print(ex.getMessage());
      } finally {
         session.getTransaction().commit();
         session.close();

      }

      return null;

   }

}



