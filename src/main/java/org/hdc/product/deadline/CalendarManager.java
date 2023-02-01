package org.hdc.product.deadline;

import java.util.Date;
import java.util.List;

public abstract class CalendarManager {

    protected CalendarManager() {
    }

    public static CalendarManager.Builder createCalendarManager() {
        return new CalendarManagerBuilder();
    }

    public interface Builder {
        Builder addCalendar(String calendarName, Calendar calendar) throws DeadLineException;

        Builder forceSystemDeadLineOnThrow(Boolean bool);

        CalendarManager build() throws DeadLineException;
    }

    public abstract Date calcDeadLine(String calendarName, String duration, Date date) throws DeadLineException;

    public abstract List<String> getCalendarNames(String calendarName) throws DeadLineException;

}
