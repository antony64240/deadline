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
        currentDay.setRemainingDuration(durationSecond);
        currentDay.setLocalDateTime(startDate);

        if(startDate.isBefore(workDay.getPeriodList().get(0).getStartPeriod()) && workDay.getTotalDurationInDay() < durationSecond){
            currentDay.setRemainingDuration(currentDay.getRemainingDuration()-workDay.getTotalDurationInDay());
            currentDay.setLocalDateTime(workDay.getPeriodList().get(workDay.getPeriodList().size()-1).getEndPeriod());
        } else {
            for (Period period : workDay.getPeriodList()) {
                if (startDate.isBefore(period.getStartPeriod()) || startDate.isEqual(period.getStartPeriod())) {
                    currentDay.setLocalDateTime(period.getStartPeriod());
                    currentDay.setRemainingDuration(currentDay.getRemainingDuration());
                    if (currentDay.getRemainingDuration() > period.getDuration()) {
                        currentDay.setLocalDateTime(period.getEndPeriod());
                        currentDay.setRemainingDuration(currentDay.getRemainingDuration() - period.getDuration());
                    } else {
                        currentDay.setLocalDateTime(setTime(currentDay.getRemainingDuration(), currentDay.getLocalDateTime()));
                        currentDay.setRemainingDuration(0L);
                        break;
                    }
                } else if (startDate.isAfter(period.getStartPeriod()) && startDate.isBefore(period.getEndPeriod())) {
                    currentDay.setLocalDateTime(startDate);
                    currentDay.setRemainingDuration(currentDay.getRemainingDuration());

                    Long milliSecondStay = (Date.from(period.getEndPeriod().toInstant(ZoneOffset.UTC)).getTime()) - (Date.from(startDate.toInstant(ZoneOffset.UTC)).getTime() / 1000);
                    if (milliSecondStay > 0) {
                        if (currentDay.getRemainingDuration() > milliSecondStay) {
                            currentDay.setLocalDateTime(period.getEndPeriod());
                            currentDay.setRemainingDuration(currentDay.getRemainingDuration() - milliSecondStay);
                        } else {
                            currentDay.setLocalDateTime(setTime(currentDay.getRemainingDuration(), currentDay.getLocalDateTime()));
                            currentDay.setRemainingDuration(0L);
                            break;
                        }
                    }
                } else if (startDate.isAfter(period.getEndPeriod()) || startDate.isEqual(period.getEndPeriod())) {
                    currentDay.setLocalDateTime(period.getEndPeriod());
                    currentDay.setRemainingDuration(currentDay.getRemainingDuration());
                }
            }
        }

        return currentDay;
    }

    private static LocalDateTime setTime(Long remainingDuration, LocalDateTime localDateTime) {
        long numberHours = remainingDuration / 3600000;
        long numberMinute = (remainingDuration % 3600000) / 60000;
        long numberSecond = ((remainingDuration % 3600000) % 60000) / 1000;
        long numberMillisecond = ((remainingDuration % 3600000) % 60000) % 1000;
        return localDateTime.plusMinutes(numberMinute).plusHours(numberHours).plusSeconds(numberSecond).plusNanos(numberMillisecond * 1000000);
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
