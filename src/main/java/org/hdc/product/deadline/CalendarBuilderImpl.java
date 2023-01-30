package org.hdc.product.deadline;


import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class CalendarBuilderImpl implements Calendar.Builder {
    private final String name;
    private final Map<String, WorkDay> workingDays = new HashMap<>();
    private final Set<String> offDays = new HashSet<>();
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public CalendarBuilderImpl(String name) {
        this.name = name;
    }

    @Override
    public Calendar.Builder createPeriod(DayOfWeek dayOfWeek, String startPeriod, String endPeriod) throws DeadLineException {
        if (!workingDays.containsKey(dayOfWeek.name())) {
            workingDays.put(dayOfWeek.name(), new WorkDay(dayOfWeek.name()));
        }
        workingDays.get(dayOfWeek.name()).addPeriod(new Period(startPeriod, endPeriod));
        return this;
    }

    @Override
    public Calendar.Builder addOffDay(Integer day, Integer month, Integer year) {
        LocalDate localDate = LocalDate.of(year, month, day);
        offDays.add(formatter.format(localDate));
        return this;
    }

    @Override
    public Calendar.Builder addYearlyOffDay(Integer day, Integer month, Integer nbrOfYears) {
        LocalDate localDate = LocalDate.of(LocalDate.now().getYear(), month, day);
        offDays.add(formatter.format(localDate));
        for (int i = 0; i < nbrOfYears; i++) {
            localDate = localDate.plusYears(1);
            offDays.add(formatter.format(localDate));
        }
        return this;
    }

    @Override
    public Calendar build() throws DeadLineException {
        if ( (name == null) || "".equals(name) ) throw new DeadLineException("Calendar name cannot be null or empty", 1);
        if( workingDays.isEmpty() ) throw new DeadLineException("Calendar must have at least one working day and one period", 5);
        return new CalendarImpl(name, workingDays, offDays);
    }

}
