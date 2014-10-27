import za.co.skizzel.domain.entities.GroupedDate;
import za.co.skizzel.domain.logic.SkizzelLogic;
import za.co.skizzel.infrastructure.dao.SkizzelDao;

import java.util.HashMap;

/**
 * Created by Luke on 2014/10/09.
 */
public class Runner {

   public static void main(String[] args) {

      HashMap Months = new HashMap();
      Months.put("01", "January");
      Months.put("02", "Febuary");

      System.out.print(Months.get("01"));


}


}
