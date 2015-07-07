package extractor.com.easyfinance.util;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Date;

public class UtilDate {

    public static String getDateFormatted(Date date) {
        DateTime dt = new DateTime(date);
        DateTimeFormatter fmt = DateTimeFormat.forPattern("dd/MM/yyyy");
        return dt.toString(fmt);
    }

}
