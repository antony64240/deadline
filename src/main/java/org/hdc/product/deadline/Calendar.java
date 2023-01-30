package org.hdc.product.deadline;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.util.Date;

public abstract class Calendar implements Serializable {

    protected Calendar() {
    }

    public static Builder createCalendar() {
        return new CalendarBuilder();
    }


    public interface Builder {
        Builder createPeriod(DayOfWeek dayOfWeek, String startPeriod, String endPeriod) throws DeadLineException;

        Builder addOffDay(Integer day, Integer month, Integer year);

        Builder addYearlyOffDay(Integer day, Integer month, Integer nbrOfYears);

        Calendar build() throws DeadLineException;
    }

    public abstract Date calcDeadLine(String duration, Date date) throws DeadLineException;

}
