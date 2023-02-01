package org.hdc.product.deadline;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class WorkDay implements Serializable {
    private String workDay;

    private List<Period> periodList = new ArrayList<>();

    private Long TotalDurationInDay = 0L;


    public WorkDay(String workDay) {
        this.workDay = workDay;
        this.TotalDurationInDay = 0L;
    }

    public WorkDay(String workDay, List<Period> periods) throws Exception {
        this.workDay = workDay;
        for (Period period : periods) {
             this.addPeriod(period);
        }
    }

    public void addPeriod(Period periodToAdd) throws DeadLineException {
        for (Period period : this.periodList) {
            if((periodToAdd.getStartPeriod().isAfter(period.getStartPeriod()) && periodToAdd.getStartPeriod().isBefore(period.getEndPeriod())) ||
                    (periodToAdd.getEndPeriod().isAfter(period.getStartPeriod()) && periodToAdd.getEndPeriod().isBefore(period.getEndPeriod())) ||
                    (periodToAdd.getStartPeriod().isBefore(period.getStartPeriod()) && periodToAdd.getEndPeriod().isAfter(period.getEndPeriod())) ||
                    (period.getStartPeriod().isBefore(periodToAdd.getStartPeriod()) && period.getEndPeriod().isAfter(periodToAdd.getEndPeriod())) ||
                    (periodToAdd.getStartPeriod().isEqual(period.getStartPeriod()) || periodToAdd.getEndPeriod().isEqual(period.getEndPeriod()))){
                throw new DeadLineException(periodToAdd + " are in conflict with " + period, 4);
            }
        }
        this.TotalDurationInDay += periodToAdd.getDuration();
        this.periodList.add(periodToAdd);
        this.sortPeriodList();
    }

    private void sortPeriodList(){
        this.periodList = periodList.stream().sorted(Comparator.comparing(Period::getStartPeriod)).collect(Collectors.toList());
    }

    public String getWorkDay() {
        return workDay;
    }

    public List<Period> getPeriodList() {
        return periodList;
    }

    public Long getTotalDurationInDay() {
        return TotalDurationInDay;
    }
}
