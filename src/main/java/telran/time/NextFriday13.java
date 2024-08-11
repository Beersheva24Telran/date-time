package telran.time;

import java.time.DayOfWeek;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;

public class NextFriday13 implements TemporalAdjuster{

    @Override
    public Temporal adjustInto(Temporal temporal) {
        Temporal temp = set13Month(temporal);
        while(temp.get(ChronoField.DAY_OF_WEEK) != DayOfWeek.FRIDAY.getValue()) {
            temp = temp.plus(1, ChronoUnit.MONTHS);
        }
        return temp;
    }

    private Temporal set13Month(Temporal temporal) {
        if (temporal.get(ChronoField.DAY_OF_MONTH) >= 13) {
            temporal = temporal.plus(1, ChronoUnit.MONTHS);
        }
        temporal = temporal.with(ChronoField.DAY_OF_MONTH, 13);
        return temporal;
    }

}
