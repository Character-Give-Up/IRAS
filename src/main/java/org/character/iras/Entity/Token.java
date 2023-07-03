package org.character.iras.Entity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Token {
    private final String value;
    private Date expiredTime;

    public Token(String value, Date expiredTime) {
        this.value = value;
        this.expiredTime = expiredTime;
    }

    @Override
    public String toString() {
        return "Token{" +
                "value='" + value + '\'' +
                ", expiredTime=" + getExpiredDate("yyyy-MM-dd HH:mm:ss") +
                '}';
    }

    public String getValue() {
        return value;
    }

    public Date getExpiredTime() {
        return expiredTime;
    }

    public String getExpiredDate(String pattern){
        return new SimpleDateFormat(pattern).format(expiredTime);
    }

    public Date getExpiredDate(){
        return this.expiredTime;
    }

    public void setExpiredTime(Date expiredTime) {
        this.expiredTime = expiredTime;
    }

    public boolean isExpired(){
        Date now = Calendar.getInstance().getTime();
        return now.after(getExpiredTime());
    }

}
