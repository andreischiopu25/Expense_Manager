package com.example.demo;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateTimeUtil {

    public static String convertDateToString(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }
    public static Date convertStringToDate(String dateString) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date utilDate = sdf.parse(dateString);
        return new Date(utilDate.getTime());

    }
}
