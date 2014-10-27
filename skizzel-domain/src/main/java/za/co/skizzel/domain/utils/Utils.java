package za.co.skizzel.domain.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

public class Utils {

   public String getPropValues(String value) throws IOException {

      Properties prop = new Properties();
      String propFileName = "config.properties";

      InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
      prop.load(inputStream);
      if (inputStream == null) {
         throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
      }

      return prop.getProperty(value);
   }

   public String formatDate(String date) {

      String[] rawDate = date.split("-");

      return getMonths().get(rawDate[1]).toString() + " " + rawDate[0];
   }

   private HashMap<String, String> getMonths() {

      HashMap Months = new HashMap();

      Months.put("01", "January");
      Months.put("02", "Febuary");
      Months.put("03", "March");
      Months.put("04", "April");
      Months.put("05", "May");
      Months.put("06", "June");
      Months.put("07", "July");
      Months.put("08", "August");
      Months.put("09", "September");
      Months.put("10", "October");
      Months.put("11", "November");
      Months.put("12", "December");

      return Months;

   }
}

