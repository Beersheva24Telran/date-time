package telran.time;

import static org.junit.jupiter.api.Assertions.*;

import java.time.*;
import java.time.chrono.*;
import java.time.temporal.*;

import org.junit.jupiter.api.Test;

public class PastTemporalDateProximityTest {
    Temporal[] temporals = {
            MinguoDate.now().minus(1, ChronoUnit.DAYS),
            ThaiBuddhistDate.now().plus(3, ChronoUnit.DAYS),
            LocalDate.now().minus(2, ChronoUnit.YEARS),
            LocalDateTime.now().plus(1, ChronoUnit.MONTHS)
    };
    PastTemporalDateProximity adjuster = new PastTemporalDateProximity(temporals);

@Test
void localDateTimeAsMinguoDate(){
    LocalDateTime ldt = LocalDateTime.now();
    LocalDateTime expected = LocalDateTime.now().minusDays(1);
    assertEquals(0, ChronoUnit.SECONDS.between(ldt.with(adjuster), expected));
}
@Test
void minguoDateAsZonedDateTime() {
    MinguoDate md = MinguoDate.now().plus(2, ChronoUnit.MONTHS);
    MinguoDate expected = MinguoDate.now().plus(1, ChronoUnit.MONTHS);
    assertEquals(expected, md.with(adjuster));
}
@Test
void zonedDateTimeAsThaiBuddhist() {
    Temporal zdt = ZonedDateTime.now().plusDays(4);
    ZonedDateTime expected = ZonedDateTime.now().plus(3, ChronoUnit.DAYS);
    assertEquals(0, ChronoUnit.SECONDS.between(zdt.with(adjuster), expected));
}
@Test
void localDateNotFound() {
    LocalDate ld = LocalDate.now().minusYears(3);
    assertNull(ld.with(adjuster));
}
}
