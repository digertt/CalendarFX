package com.calendarfx.app;
import com.calendarfx.model.Calendar;
import java.io.Serializable;

public class User implements Serializable {

    //default serialVersion id
    private static final long serialVersionUID = 1L;

    private String name;
    private String password;
    private Calendar calendar;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public void setCalendar(Calendar newCalendar) {
        this.calendar = newCalendar;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public String getName() {
        return name;
    }

    public void setPassword(String newPassword) {
        this.password = newPassword;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return new StringBuffer(" Name: ").append(this.name)
                .append("\nPassword: ").append(this.password).append("\nCalendar: ").append(this.calendar.toString()).toString();
    }
}
