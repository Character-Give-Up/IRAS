package org.character.iras.Utils;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Component
public class TimeStampGenerator {
    public TimeStampGenerator() {
    }

    public String getCurrentTimeString(String pattern){
        Calendar calendar = Calendar.getInstance();
        Date time = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(time);

    }
}
