package org.diiage.dtrqandroid.data.converters;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Date;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.room.TypeConverter;

import javax.inject.Singleton;

import dagger.Provides;

public class DateTypeConverter {

    @TypeConverter
    public static Date toDate(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long toLong(Date value) {
        return value == null ? null : value.getTime();
    }

    @TypeConverter
    public static String toString(Date value) {
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendPattern("dd/MM/yyyy 'à' HH:mm")
                .toFormatter();

        return value != null ?
                LocalDateTime.of(
                        value.getYear(),
                        value.getMonth(),
                        value.getDay(),
                        value.getHours(),
                        value.getMinutes())
                        .format(formatter)
                : "";
    }
}