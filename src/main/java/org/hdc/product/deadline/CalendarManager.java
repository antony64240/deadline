package org.hdc.product.deadline;

import java.util.Date;

public abstract class CalendarManager {

    protected CalendarManager() {
    }

    public static CalendarManager.Builder createCalendarManager() {
        return null;
    }

    public interface Builder {
        Builder addCalendar() throws DeadLineException;

        CalendarManager build() throws DeadLineException;
    }

    public interface Runner {

        Date calcDeadLine(String calendarName, String duration, Date date) throws Exception;

    }

    public abstract Date calcDeadLine(String calendarName, String duration, Date date) throws Exception;

}
