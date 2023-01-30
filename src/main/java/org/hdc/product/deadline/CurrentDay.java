package org.hdc.product.deadline;

import java.time.LocalDateTime;

public class CurrentDay {
    private LocalDateTime localDateTime;
    private Long durationSecond;

    public CurrentDay() {
    }

    public CurrentDay(LocalDateTime localDateTime, Long durationSecond) {
        this.localDateTime = localDateTime;
        this.durationSecond = durationSecond;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public Long getDurationSecond() {
        return durationSecond;
    }

    public void setDurationSecond(Long durationSecond) {
        this.durationSecond = durationSecond;
    }
}
