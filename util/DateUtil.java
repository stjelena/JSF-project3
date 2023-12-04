package util;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class DateUtil {

    //===========================================
    // java.util.Date i String
    //===========================================

    /**
     * Pretvara java.util.Date u String u formatu yyyy-MM-dd HH:mm:ss.<br>
     * Isto kao poziv metode utilToString(date, "yyyy-MM-dd HH:mm:ss")
     *
     * @param date objekat java.util.Date
     * @return ispis datuma u formatu yyyy-MM-dd HH:mm:ss
     */
    public static String utilDateToString(java.util.Date utilDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // format izmeniti po zelji
        return sdf.format(utilDate);
    }

    /**
     * Pretvara java.util.Date u String u formatu koji je naveden kao parametar.
     *
     * @param utilDate objekat java.util.Date
     * @param pattern  patern za ispis datuma (yyyy-MM-dd, dd.MM.yyyy. ili
     *                 slično)
     * @return ispis datuma u zadatom formatu
     */
    public static String utilDateToString(java.util.Date utilDate, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(utilDate);
    }

    /**
     * Pretvara zadati string u objekat tipa java.util.Date.<br>
     * Podrazumeva da string treba da bude u zadatom formatu.
     *
     * @param date string koji predstavlja datum
     * @param pattern patern u kojem je datum ispisan
     * @return objekat tipa java.util.Date koji predstavlja datum dat stringom
     * @throws ParseException ukoliko string ili patern nije dobrog formata,
     * baca grešku
     */
    public static java.util.Date stringToUtilDate(String date, String pattern) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.parse(date);
    }

    //===========================================
    // java.util.Date i java.sql
    //===========================================

    /**
     * Pretvara objekat tipa java.util.Date u objekat tipa java.sql.Date
     *
     * @param utilDate datum tipa java.util.Date
     * @return datum tipa java.sql.Date
     */
    public static java.sql.Date utilDateToSqlDate(java.util.Date utilDate) {
        return new java.sql.Date(utilDate.getTime());
    }

    /**
     * Pretvara objekat tipa java.sql.Date u objekat tipa java.util.Date
     *
     * @param sqlDate datum tipa java.sql.Date
     * @return datum tipa java.util.Date
     */
    public static java.util.Date sqlDateToUtilDate(java.sql.Date sqlDate) {
        return new java.util.Date(sqlDate.getTime());
    }

    /**
     * Pretvara objekat tipa java.util.Date u objekat tipa java.sql.Timestamp
     *
     * @param utilDate datum tipa java.util.Date
     * @return datum tipa java.sql.Timestamp
     */
    public static java.sql.Timestamp utilDateToSqlTimestamp(java.util.Date utilDate) {
        return new java.sql.Timestamp(utilDate.getTime());
    }

    /**
     * Pretvara objekat tipa java.sql.Timestamp u objekat tipa java.util.Date
     *
     * @param sqlDate datum tipa java.sql.Timestamp
     * @return datum tipa java.util.Date
     */
    public static java.util.Date sqlTimestampToUtilDate(java.sql.Timestamp sqlTimestamp) {
        return new java.util.Date(sqlTimestamp.getTime());
    }

    //===========================================
    // java.sql i java.time - Date, Time, DateTime/Timestamp
    //===========================================

    /**
     * Pretvara objekat tipa java.time.LocalDate u objekat tipa
     * java.sql.Date.<br>
     * Koristi se kada imamo samo datum (npr. 2022-12-09).
     *
     * @param date datum tipa java.time.LocalDate
     * @return datum tipa java.sql.Date
     */
    public static java.sql.Date localDatetoSqlDate(LocalDate date) {
        return java.sql.Date.valueOf(date); // string format je yyyy-MM-dd
    }

    /**
     * Pretvara objekat tipa java.sql.Date u objekat tipa
     * java.time.LocalDate.<br>
     * Koristi se kada imamo samo datum (npr. 2022-12-09).
     *
     * @param sqlDate datum tipa java.sql.Date
     * @return datum tipa java.time.LocalDate
     */
    public static LocalDate sqlDateToLocalDate(java.sql.Date sqlDate) {
        return sqlDate.toLocalDate();
    }

    /**
     * Pretvara objekat tipa java.time.LocalTime u objekat tipa
     * java.sql.Time.<br>
     * Koristi se kada imamo samo vreme (npr. 19:30:00).
     *
     * @param time datum tipa java.time.LocalTime
     * @return datum tipa java.sql.Time
     */
    public static java.sql.Time localTimetoSqlTime(LocalTime time) {
        return java.sql.Time.valueOf(time); // string format je HH:mm:ss
    }

    /**
     * Pretvara objekat tipa java.sql.Time u objekat tipa
     * java.time.LocalTime.<br>
     * Koristi se kada imamo samo vreme (npr. 19:30:00).
     *
     * @param sqlTime datum tipa java.sql.Time
     * @return datum tipa java.time.LocalTime
     */
    public static LocalTime sqlTimeToLocalTime(java.sql.Time sqlTime) {
        return sqlTime.toLocalTime();
    }

    /**
     * Pretvara objekat tipa java.time.LocalDateTime u objekat tipa
     * java.sql.Timestamp.<br>
     * Koristi se kada imamo i datum i vreme (npr. 2022-12-09 19:30:00).
     *
     * @param time datum tipa java.time.LocalDateTime
     * @return datum tipa java.sql.Timestamp
     */
    public static java.sql.Timestamp localDateTimeToSqlTimestamp(LocalDateTime time) {
        return java.sql.Timestamp.valueOf(time);
    }

    /**
     * Pretvara objekat tipa java.sql.Timestamp u objekat tipa
     * java.time.LocalDateTime.<br>
     * Koristi se kada imamo i datum i vreme (npr. 2022-12-09 19:30:00).
     *
     * @param sqlTimestamp datum tipa java.sql.Timestamp
     * @return datum tipa java.time.LocalDateTime
     */
    public static LocalDateTime sqlTimestampToLocalDateTime(java.sql.Timestamp sqlTimestamp) {
        return sqlTimestamp.toLocalDateTime();
    }

    // ===========================================
    // java.util i java.time
    // ===========================================

    /**
     * Pretvara objekat tipa java.time.LocalDate u objekat tipa
     * java.util.Date.<br>
     * Koristi se kada imamo samo datum (npr. 2022-12-09).
     *
     * @param date datum tipa java.time.LocalDate
     * @return datum tipa java.util.Date
     */
    public static java.util.Date localDatetoUtilDate(LocalDate date) {
        return DateUtil.sqlDateToUtilDate(DateUtil.localDatetoSqlDate(date));
    }

    /**
     * Pretvara objekat tipa java.util.Date u objekat tipa
     * java.time.LocalDate.<br>
     * Koristi se kada imamo samo datum (npr. 2022-12-09).
     *
     * @param utilDate datum tipa java.util.Date
     * @return datum tipa java.time.LocalDate
     */
    public static LocalDate utilDateToLocalDate(java.util.Date utilDate) {
        return DateUtil.sqlDateToLocalDate(DateUtil.utilDateToSqlDate(utilDate));
    }


    /**
     * Pretvara objekat tipa java.time.LocalDateTime u objekat tipa
     * java.util.Date.<br>
     * Koristi se kada imamo i datum i vreme (npr. 2022-12-09 19:30:00).
     *
     * @param time datum tipa java.time.LocalDateTime
     * @return datum tipa java.util.Date
     */
    public static java.util.Date localDateTimeToUtilDate(LocalDateTime time) {
        return new java.util.Date(DateUtil.localDateTimeToSqlTimestamp(time).getTime());
    }

    /**
     * Pretvara objekat tipa java.util.Date u objekat tipa
     * java.time.LocalDateTime.<br>
     * Koristi se kada imamo i datum i vreme (npr. 2022-12-09 19:30:00).
     *
     * @param utilDate datum tipa java.util.Date
     * @return datum tipa java.time.LocalDateTime
     */
    public static LocalDateTime utilDateToLocalDateTime(java.util.Date utilDate) {
        return DateUtil.sqlTimestampToLocalDateTime(new java.sql.Timestamp(utilDate.getTime()));
    }

    //===========================================
    // današnji datum/vreme
    //===========================================

    /**
     * Vraća trenutni datum i vreme, objekta tipa java.util.Date.
     *
     * @return trenutni datum i vreme
     */
    public static java.util.Date currentUtilDate() {
        return new java.util.Date();
    }

    /**
     * Vraća trenutni datum, objekta tipa java.sql.Date.
     *
     * @return trenutni datum
     */
    public static java.sql.Date currentSqlDate() {
        return new java.sql.Date(System.currentTimeMillis());
    }

    /**
     * Vraća trenutni datum i vreme, objekta tipa java.sql.Timestamp.
     *
     * @return trenutni datum i vreme
     */
    public static java.sql.Timestamp currentSqlTimestamp() {
        return new java.sql.Timestamp(System.currentTimeMillis());
    }

    /**
     * Vraća trenutni datum, objekta tipa java.time.LocalDate.
     *
     * @return trenutni datum
     */
    public static java.time.LocalDate currentLocalDate() {
        return LocalDate.now();
    }

    /**
     * Vraća trenutni datum i vreme, objekta tipa java.time.LocalDateTime.
     *
     * @return trenutni datum i vreme
     */
    public static java.time.LocalDateTime currentLocalDateTime() {
        return LocalDateTime.now();
    }

}