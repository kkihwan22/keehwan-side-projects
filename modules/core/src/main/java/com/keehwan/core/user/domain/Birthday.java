package com.keehwan.core.user.domain;

import lombok.Getter;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Birthday {
    private static final DateTimeFormatter DEFAULT_FORMAT = DateTimeFormatter.ofPattern("yyyyMMdd");

    @Getter
    private LocalDate date;

    public Birthday(String date, DateTimeFormatter formatter) {
        this.date = LocalDate.parse(date, formatter);
    }

    public Birthday(String date) {
        this(date, DEFAULT_FORMAT);
    }

    public int getAge() {
        return Period.between(this.date, LocalDate.now()).getYears();
    }

    public boolean isNineteenOrOlder() {
        return getAge() >= 19;
    }
}
