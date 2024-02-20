package doranco.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public final class Dates {

    private static SimpleDateFormat FORMAT_DATE = new SimpleDateFormat("dd/MM/yyyy");

    private Dates() {
    }

    public static Date convertFromStringToDate(String dateStr) throws Exception {
        return FORMAT_DATE.parse(dateStr);
    }

    public static String convertFromDateToString(Date date) throws Exception {
        return FORMAT_DATE.format(date);
    }

    public static Date convertDateSqlToDateUtil(java.sql.Date date) {
        if (date == null) {
            return null;
        }
        return new Date(date.getTime());
    }

    public static java.sql.Date convertDateUtilToDateSql(Date date) {
        if (date == null) {
            return null;
        }
        return new java.sql.Date(date.getTime());
    }

}
