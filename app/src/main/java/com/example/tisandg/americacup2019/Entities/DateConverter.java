package com.example.tisandg.americacup2019.Entities;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

public class DateConverter {
    @TypeConverter
    public static Date toDate(String dateString) {
        return dateString == null ? null : new Date(dateString);
    }

}
