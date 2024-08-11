package telran.time;

import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;

public class PastTemporalDateProximity implements TemporalAdjuster{
//TODO some encapsulation
//array of temporals supprting Day, Month, Year (Dates) 
    @Override
    public Temporal adjustInto(Temporal temporal) {
        // TODO Auto-generated method stub
        //return the temporal for the encapsulated array that is a nearest in past
        throw new UnsupportedOperationException("Unimplemented method 'adjustInto'");
    }

}
