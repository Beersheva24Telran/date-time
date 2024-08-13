package telran.time;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.util.Arrays;

public class PastTemporalDateProximity implements TemporalAdjuster{
    Temporal [] temporals;
public PastTemporalDateProximity(Temporal[] temporals) {
        this.temporals = Arrays.copyOf(temporals, temporals.length);
        Arrays.sort(this.temporals, this::temporalCompare);
    }

    @Override
    public Temporal adjustInto(Temporal temporal) {
        Temporal res = null;
        int index = getIndexLastAmongLessThan(temporal);
        if (index >= 0) {
            Temporal found = temporals[index];
            long daysFoundSource = betweenDays(found, temporal);
            res = temporal.minus(daysFoundSource, ChronoUnit.DAYS);
        }
        return res;
    }
    private int getIndexLastAmongLessThan(Temporal temporal) {
        int pastIndex = 0;
        int futureIndex = temporals.length - 1;
        int current = (pastIndex + futureIndex) / 2;
        while(pastIndex <= futureIndex) {
            int compResult = temporalCompare(temporal, temporals[current]);
            if (compResult > 0) {
                pastIndex = current + 1;
            } else {
                futureIndex = current - 1;
            }
            current = (pastIndex + futureIndex) / 2;
        }
        return futureIndex;
    }

    private int temporalCompare(Temporal t1, Temporal t2) {
        return Long.compare(betweenDays(t2, t1), 0);
    }

    private long betweenDays(Temporal from, Temporal to) {
        return ChronoUnit.DAYS.between(LocalDate.from(from), LocalDate.from(to));
    }

}
