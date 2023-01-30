package org.hdc.product.deadline;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class CalendarManagerBuilder implements CalendarManager.Builder, Serializable {

    private Map<String, Calendar> calendars = new HashMap<>();
    private boolean isForceSystemDeadLineOnThrow = false;

    @Override
    public CalendarManager.Builder addCalendar(String calendarName, Calendar calendar) throws DeadLineException {
        if (calendars.containsKey(calendarName)) throw new DeadLineException(calendarName + " already exist.", 6);
        this.calendars.put(calendarName, calendar);
        return this;
    }

    @Override
    public CalendarManager.Builder forceSystemDeadLineOnThrow(Boolean bool) {
        this.isForceSystemDeadLineOnThrow = bool;
        return this;
    }

    @Override
    public CalendarManager build() throws DeadLineException {
        if( calendars.isEmpty() ) throw new DeadLineException("CalendarManager must have at least one calendar.", 7);
        return new CalendarManagerImpl(this.calendars, this.isForceSystemDeadLineOnThrow);
    }
}
