package com.practice.date;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;

public class DateApp {
    public static void main(String... args){

        LocalDate today = LocalDate.now();

        int year = today.get(ChronoField.YEAR);
        int month = today.get(ChronoField.MONTH_OF_YEAR);
        int day = today.get(ChronoField.DAY_OF_MONTH);

        System.out.println(day + "/" + month + "/" + year);

        LocalDate date1 = LocalDate.parse("20140318",
        DateTimeFormatter.BASIC_ISO_DATE);
        LocalDate date2 = LocalDate.parse("2014-03-18",
        DateTimeFormatter.ISO_LOCAL_DATE);

        System.out.println(date1);
        System.out.println(date2);
    }
}