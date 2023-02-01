package org.hdc.product.deadline;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class CalendarManagerImpl extends CalendarManager implements Serializable {

    private Map<String, Calendar> calendars;

    private boolean isForceSystemDeadLineOnThrow;

    public CalendarManagerImpl(Map<String, Calendar> calendars, boolean isForceDeadLineOnThrow) {
        this.calendars = calendars;
        this.isForceSystemDeadLineOnThrow = isForceDeadLineOnThrow;
    }

    @Override
    public Date calcDeadLine(String calendarName, String duration, Date date) throws DeadLineException {
        if(calendarName == null || "".equals(calendarName)){
            return this.calcSysDeadLine(duration, date);
        }
        try{
            Calendar calendar = this.getCalendar(calendarName);
            return calendar.calcDeadLine(duration, date);
        } catch (DeadLineException deadLineException){
            if(deadLineException.getVendorCode() == 9){
                if(this.isForceSystemDeadLineOnThrow){
                    return calcSysDeadLine(duration, date);
                } else {
                    throw new DeadLineException(calendarName + " do not exist and default system deadline on throw is not active", 8);
                }
            } else {
                throw new DeadLineException("Error during calculating dead line with param : calendarName : " + calendarName + ", duration : " + duration + ", date : " + date, 8);
            }
        }
    }

    public Calendar getCalendar(String calendarName) throws DeadLineException {
        Calendar calendar = calendars.get(calendarName);
        if(calendar == null) throw new DeadLineException("calendar name : " + calendarName + " do not exist.", 9);
        return calendars.get(calendarName);
    }

    @Override
    public List<String> getCalendarNames(String calendarName) {
        return new ArrayList<>(calendars.keySet());
    }

    private static Date calcSysDeadLine(String duration, Date startDate) {
        LocalDateTime localDate = startDate.toInstant()
                .atZone(ZoneOffset.UTC)
                .toLocalDateTime();

        return Date.from(localDate.plus(Duration.parse(duration)).atZone(ZoneOffset.UTC).toInstant());
    }

}
