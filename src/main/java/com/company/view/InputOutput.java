package com.company.view;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.function.Predicate;

public interface InputOutput {
    String getString(String prompt); // input

    void put(Object object); // output

    default String getString(String prompt, int minLength, int maxLength) {
        String res = "";
        int length = 0;
        String errMsg = "";
        do {
            res = getString(errMsg + prompt);
            length = res.length();
            errMsg = "Wrong string lenght. ";
        } while (length < minLength || length > maxLength);
        return res;
    }

    default String getString(String prompt, Predicate<String> predicate) {
        String res = "";
        String errMsg = "";
        do {
            res = getString(errMsg + prompt);
            errMsg = "Wrong input. ";
        } while (!predicate.test(res));

        return res;
    }

    default String getString(String prompt, List<String> from) {
        String res = "";
        String errMsg = "";
        do {
            res = getString(errMsg + prompt);
            errMsg = "Wrong input. ";
        } while (!from.contains(res));

        return res;
    }

    default Integer getInteger(String prompt) {
        Integer res = null;
        String errMsg = "";
        while (true) {
            try {
                String str = getString(errMsg + prompt); // esli ne polushilos rasparsit i chtob ne viletel a pereshel v catch
                res = Integer.parseInt(str);
                //res.parseInt(str);
                return res;
            } catch (NumberFormatException e) {
                errMsg = "Not a number. ";
                // e.printStackTrace();
            }
        }

    }

    default Integer getInteger(String prompt, Integer minValue, Integer maxValue) { //home work
        Integer res = null;
        String errMsg = "";
        while (true) {
            try {
                String str = getString(errMsg + prompt);
                res = Integer.parseInt(str);
                if (res >= minValue || res <=maxValue)
                    return res;
            } catch (NumberFormatException e) {
                errMsg = "Wrong Integer value. ";
            }
        }
    }

    default Integer getInteger(String prompt, Predicate<Integer> predicate) { // home work
        Integer res = null;
        String errMsg = "";
        while(true) {
            try {
                String str = getString(errMsg + prompt);
                res = Integer.parseInt(str);
                if (predicate.test(res)) return res;
            }	catch (NumberFormatException e){
                errMsg = "Wrong input. ";
            }

        }

    }

    default LocalDate getDate(String prompt, String format) {
        LocalDate res = null;
        String errMsg = "";
        DateTimeFormatter dateF = DateTimeFormatter.ofPattern(format);
        while(true){
            String str = getString(errMsg + prompt);
            try{
                res = LocalDate.parse(str, dateF);
                return res;
            }catch (DateTimeException e){
                errMsg = "Wrong input date ";
            }
        }

    }

    default LocalDate getDate(String prompt, String format, LocalDate fromInclusive, LocalDate toExclusive) {
        LocalDate res = null;
        String errMsg = "";
        DateTimeFormatter dateF = DateTimeFormatter.ofPattern(format);
        while(true){
            String str = getString(errMsg + prompt);
            try{
                res = LocalDate.parse(str, dateF);
                if (res.isBefore(fromInclusive) || res.isAfter(toExclusive) || res.isEqual(toExclusive)) {
                    errMsg = "Date out of range";
                }else return res;
            }catch (DateTimeException e){
                errMsg = "Wrong input date. ";
            }
        }

    }
}


