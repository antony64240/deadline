package org.hdc.product.deadline;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

public class Period {

    private final Long duration;
    private final LocalDateTime startPeriod;
    private final LocalDateTime endPeriod;

    public Period(String startPeriod, String endPeriod) throws DeadLineException {
        String[] tab = startPeriod.split(":");
        this.startPeriod = LocalDateTime.of(0, 1, 1, Integer.parseInt(tab[0]), Integer.parseInt(tab[1]));
        tab = endPeriod.split(":");
        this.endPeriod = LocalDateTime.of(0, 1, 1, Integer.parseInt(tab[0]), Integer.parseInt(tab[1]));
        Date tmpStartPeriod = Date.from(this.startPeriod.toInstant(ZoneOffset.UTC));
        Date tmpEndPeriod = Date.from(this.endPeriod.toInstant(ZoneOffset.UTC));
        this.duration = (tmpEndPeriod.getTime() - tmpStartPeriod.getTime()) / 1000;
        if(this.duration<=0){
            throw new DeadLineException("Start period cannot be after or equal to end.", 2);
        }
    }

    public Period(LocalDate currentDate, LocalDateTime startPeriod, LocalDateTime endPeriod, Long duration) {
        this.startPeriod = LocalDateTime.of(currentDate.getYear(), currentDate.getMonth(), currentDate.getDayOfMonth(), startPeriod.getHour(), startPeriod.getMinute(), startPeriod.getSecond());
        this.endPeriod =  LocalDateTime.of(currentDate.getYear(), currentDate.getMonth(), currentDate.getDayOfMonth(), endPeriod.getHour(), endPeriod.getMinute(), endPeriod.getSecond());
        this.duration = duration;
    }

    public Long getDuration() {
        return duration;
    }

    public LocalDateTime getStartPeriod() {
        return startPeriod;
    }

    public LocalDateTime getEndPeriod() {
        return endPeriod;
    }
}
