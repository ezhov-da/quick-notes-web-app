package ru.ezhov.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author deonisius
 */
public class DateTimeConverter {

    private DateFormat dateTimeFormat;
    private String dateTimeStr;

    public DateTimeConverter(String dateTimeStr) {
        this.dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.dateTimeStr = dateTimeStr;
    }

    public Timestamp getTimeStamp() throws ParseException {
        return new Timestamp(dateTimeFormat.parse(dateTimeStr).getTime());
    }

}
