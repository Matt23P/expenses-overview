package com.example.eoapi.Utils;

public class Constants {
    public static final String PWD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%]).{8,32}$";
    public static final String EMAIL_REGEX = "^(([^<>()[\\\\]\\\\.,;:\\s@\\\"]+(\\.[^<>()[\\\\]\\\\.,;:\\s@\\\"]+)*)|(\\\".+\\\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
    public static final String USERNAME_REGEX = "^[a-zA-Z0-9](?:[a-zA-Z0-9 ]*[a-zA-Z0-9])?$";
    public static final String YEAR_REGEX = "^[0-9]{4}$";
    public static final String MONTH_DAY_REGEX = "^[0-9]{2}$";
}
