package org.hdc.product.deadline;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface CalendarDay<E> {

    CalendarDay<E> setHolidays(final Set<E> holidays);

    boolean isHoliday(final E date);

    Integer getNextWorkingDay(final DayOfWeek dayOfWeek) throws Exception;

    Integer getNumberOfDayUntil(DayOfWeek day1, DayOfWeek day2);

    Map<String, WorkDay> getWorkDayParamsMap();

    void setWorkDayParams(WorkDay workDay);

    List<Integer> getWeekDaysOffSet();

}
