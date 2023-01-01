package com.auth.detailes.web.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;


public class DateTimeConverter {
    public static final String FORMAT_YYYY_MM_DD = "yyyy-MM-dd";
    public static final String FORMAT_DD_MM_YYYY = "dd/MM/yyyy";

    public static Date toDate(String date) {
        if(date == null || date.isEmpty())
            return null;

       // LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern(FORMAT_YYYY_MM_DD));
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_YYYY_MM_DD);
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String toString(Date dateOperation) {
        if(dateOperation == null)
            return null;
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_YYYY_MM_DD);
        return sdf.format(dateOperation);
    }


    public static String addMonthToDateAsString(Date dateCreation) {
        String date = toString(dateCreation);
        if(date != null && !date.isEmpty()){
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dateCreation);
            calendar.add(Calendar.DATE, 30);
            return toString(calendar.getTime());
        }
        return "";
    }



    public static Instant getInstantFromString(String dateString){
        return  Instant.parse(dateString);
    }
    public static Instant getInstantStartOfDayFromString(String dateString, String dateFormat){
        LocalDate ins = LocalDate.parse(dateString , DateTimeFormatter.ofPattern(dateFormat));
        LocalDateTime insTime = ins.atStartOfDay();
        return insTime.toInstant(getCurrentOffset());
    }
    public static Instant getInstantEndOfDayFromString(String dateString, String dateFormat){
        LocalDate ins = LocalDate.parse(dateString , DateTimeFormatter.ofPattern(dateFormat));
        LocalDateTime insTime = ins.plusDays(1).atStartOfDay();
        return insTime.toInstant(getCurrentOffset());
    }

    private static ZoneOffset getCurrentOffset() {
        ZoneId zone = ZoneId.of("Africa/Casablanca");
        return zone.getRules().getOffset(LocalDateTime.now());
    }
}
