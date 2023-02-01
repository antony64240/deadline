package org.hdc.product.deadline;

import java.time.LocalDateTime;

public class CurrentDay {
    private LocalDateTime localDateTime;
    private Long remainingDuration;

    public CurrentDay() {
    }

    public CurrentDay(LocalDateTime localDateTime, Long remainingDuration) {
        this.localDateTime = localDateTime;
        this.remainingDuration = remainingDuration;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public Long getRemainingDuration() {
        return remainingDuration;
    }

    public void setRemainingDuration(Long remainingDuration) {
        this.remainingDuration = remainingDuration;
    }
}
