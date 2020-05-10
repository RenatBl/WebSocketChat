package ru.itis.chat.utils;

import org.springframework.stereotype.Service;
import ru.itis.chat.dto.MessageDto;
import ru.itis.chat.models.Message;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class MessageResolver {

    public static List<MessageDto> resolveMessages(List<Message> messages) {
        List<MessageDto> messageDtoList = new ArrayList<>();
        for (Message message: messages) {
            String date = "";
            LocalDateTime dateTime = message.getCreationTime();

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

            messageDtoList.add(MessageDto.builder()
                    .id(message.getId())
                    .content(message.getContent())
                    .owner(message.getOwner().getUsername())
                    .date(date)
                    .chatId(message.getChat().getId())
                    .build());
        }
        return messageDtoList;
    }

}
