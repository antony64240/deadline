package org.hdc.product.deadline;

import java.time.DayOfWeek;
import java.util.Date;


public class runner {
    public static void main(String[] arg) throws DeadLineException {

        CalendarImpl calendar = (CalendarImpl) Calendar.newCalendar("MONDAY_TUESDAY")
                .createPeriod(DayOfWeek.MONDAY, "9:00", "17:00")
                .createPeriod(DayOfWeek.TUESDAY, "10:00", "12:00")
                .createPeriod(DayOfWeek.TUESDAY, "14:00", "18:00")
                .addYearlyOffDay(01, 01, 100)
                .addOffDay(29, 05, 2023)
                .build();

        Date date = calendar.calcDeadLine("PT11M", new Date());

        System.out.println(date);
    }
}

