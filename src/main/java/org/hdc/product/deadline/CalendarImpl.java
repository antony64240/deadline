package org.hdc.product.deadline;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Map;
import java.util.Set;

public class CalendarImpl extends Calendar implements Calendar.Runner, Serializable {

    private final String calendarName;

    private final Map<String, WorkDay> workingDays;

    private final Set<String> offDays;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public CalendarImpl(String calendarName, Map<String, WorkDay> workingDays, Set<String> offDays) {
        this.calendarName = calendarName;
        this.workingDays = workingDays;
        this.offDays = offDays;
    }

    private boolean isOffDay(final LocalDate date) {
        return offDays.contains(formatter.format(date));
    }

    @Override
    public Date calcDeadLine(String duration, Date date) throws DeadLineException {

        Long durationInSecond = Duration.parse(duration).getSeconds();

        LocalDateTime startLocalDate = DeadLineUtils.getLocalDateTime(date);

        CurrentDay currentDay = new CurrentDay(startLocalDate, durationInSecond);

        while (true) {
            if (workingDays.containsKey(currentDay.getLocalDateTime().getDayOfWeek().name()) && !this.isOffDay(startLocalDate.toLocalDate())) {
                WorkDay workDayParam = workingDays.get(currentDay.getLocalDateTime().getDayOfWeek().name());
                WorkDay currentWorkDayWithParamEnrichment = DeadLineUtils.setCurrentWorkDayWithParamDay(workDayParam, currentDay.getLocalDateTime().toLocalDate());
                currentDay = DeadLineUtils.consumeDuration(currentWorkDayWithParamEnrichment, currentDay.getLocalDateTime(), currentDay.getDurationSecond());
            }
            if(currentDay.getDurationSecond() <= 0){
                break;
            }
            currentDay.setLocalDateTime(DeadLineUtils.incrementDay(currentDay));
        }

        return Date.from(currentDay.getLocalDateTime().atZone(ZoneOffset.UTC).toInstant());
    }

}
