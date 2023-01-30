package org.hdc.product.deadline;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

public final class DeadLineUtils {


    public static WorkDay setCurrentWorkDayWithParamDay(WorkDay workDayParam, LocalDate startDate) throws DeadLineException {
        WorkDay workDay = new WorkDay(workDayParam.getWorkDay());

        for (Period period : workDayParam.getPeriodList()) {
            Period tmpPeriod = new Period(startDate, period.getStartPeriod(), period.getEndPeriod(), period.getDuration());
            workDay.addPeriod(tmpPeriod);
        }

        return workDay;
    }


    public static CurrentDay consumeDuration(WorkDay workDay, LocalDateTime startDate, Long durationSecond) {
        CurrentDay currentDay = new CurrentDay();
        currentDay.setDurationSecond(durationSecond);
        currentDay.setLocalDateTime(startDate);

        if(startDate.isBefore(workDay.getPeriodList().get(0).getStartPeriod()) && workDay.getTotalSecondInDay() < durationSecond){
            currentDay.setDurationSecond(currentDay.getDurationSecond()-workDay.getTotalSecondInDay());
            currentDay.setLocalDateTime(workDay.getPeriodList().get(workDay.getPeriodList().size()-1).getEndPeriod());
        } else {
            for (Period period : workDay.getPeriodList()) {
                if (startDate.isBefore(period.getStartPeriod()) || startDate.isEqual(period.getStartPeriod())) {
                    currentDay.setLocalDateTime(period.getStartPeriod());
                    currentDay.setDurationSecond(currentDay.getDurationSecond());
                    if (currentDay.getDurationSecond() > period.getDuration()) {
                        currentDay.setLocalDateTime(period.getEndPeriod());
                        currentDay.setDurationSecond(currentDay.getDurationSecond() - period.getDuration());
                    } else {
                        currentDay.setLocalDateTime(setTime(currentDay.getDurationSecond(), currentDay.getLocalDateTime()));
                        currentDay.setDurationSecond(0L);
                        break;
                    }
                } else if (startDate.isAfter(period.getStartPeriod()) && startDate.isBefore(period.getEndPeriod())) {
                    currentDay.setLocalDateTime(startDate);
                    currentDay.setDurationSecond(currentDay.getDurationSecond());

                    Long secondStay = (Date.from(period.getEndPeriod().toInstant(ZoneOffset.UTC)).getTime() / 1000) - (Date.from(startDate.toInstant(ZoneOffset.UTC)).getTime() / 1000);
                    if (secondStay > 0) {
                        if (currentDay.getDurationSecond() > secondStay) {
                            currentDay.setLocalDateTime(period.getEndPeriod());
                            currentDay.setDurationSecond(currentDay.getDurationSecond() - secondStay);
                        } else {
                            currentDay.setLocalDateTime(setTime(currentDay.getDurationSecond(), currentDay.getLocalDateTime()));
                            currentDay.setDurationSecond(0L);
                            break;
                        }
                    }
                } else if (startDate.isAfter(period.getEndPeriod()) || startDate.isEqual(period.getEndPeriod())) {
                    currentDay.setLocalDateTime(period.getEndPeriod());
                    currentDay.setDurationSecond(currentDay.getDurationSecond());
                }
            }
        }

        return currentDay;
    }

    private static LocalDateTime setTime(Long durationSecond, LocalDateTime localDateTime) {
        Long numberHours = durationSecond / 3600;
        Long numberMinute = (durationSecond % 3600) / 60;
        Long numberSecond = (durationSecond % 60);
        return localDateTime.plusMinutes(numberMinute).plusHours(numberHours).plusSeconds(numberSecond);
    }

    public static LocalDateTime incrementDay(CurrentDay currentDay) {
        LocalDateTime startLocalDate = LocalDateTime.of(currentDay.getLocalDateTime().getYear(), currentDay.getLocalDateTime().getMonth(), currentDay.getLocalDateTime().getDayOfMonth(), 0, 0, 0);
        return startLocalDate.plusDays(1);
    }

    public static LocalDateTime getLocalDateTime(Date startDate) {
        return startDate.toInstant()
                .atZone(ZoneOffset.UTC)
                .toLocalDateTime();
    }

}
