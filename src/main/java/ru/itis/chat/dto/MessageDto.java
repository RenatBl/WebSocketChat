package ru.itis.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MessageDto {
    private Long id;
    private String content;
    private String owner;
    private String date;
    private Long chatId;

    public void setCreationDate(LocalDateTime dateTime) {
        String date = "";

        int hour = dateTime.getHour();
        if (hour >= 10) {
            date += hour;
        } else {
            date += "0" + hour;
        }
        date += ":";
        int minute = dateTime.getMinute();
        if (minute >= 10) {
            date += minute;
        } else {
            date += "0" + minute;
        }
        date += " ";
        int day = dateTime.getDayOfMonth();
        if (day >= 10) {
            date += day;
        } else {
            date += "0" + day;
        }
        date += ".";
        int month = dateTime.getMonthValue();
        if (month >= 10) {
            date += month;
        } else {
            date += "0" + month;
        }
        date += ".";
        int year = dateTime.getYear();
        date += year;

        this.date = date;
    }
}
