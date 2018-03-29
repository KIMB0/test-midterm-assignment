package test;

import org.junit.Test;
import testex.DateFormatter;
import testex.JokeException;

import java.util.Calendar;
import java.util.Date;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.junit.Assert.*;

public class DateFormatterTest {
    private Calendar calendar;

    @Test
    public void getFormattedDate() throws Exception {
        DateFormatter dateFormatter = new DateFormatter();
        Date time = new Date();

        //Testing a valid timezone
        String rightTimeZone = "Europe/Kiev";
        assertThat(dateFormatter.getFormattedDateString(rightTimeZone, time), is(notNullValue()));

        //Testing a invalid timezone
        String wrongTimeZone = "IAmWrong";
        assertThrows(JokeException.class, () -> dateFormatter.getFormattedDateString(wrongTimeZone, time));
    }

    @Test
    public void checkFormattedDate() throws JokeException {
        DateFormatter dateFormatter = new DateFormatter();
        String rightTimeZone = "Europe/Copenhagen";
        calendar = Calendar.getInstance();
        calendar.set(2018, Calendar.MARCH, 28, 16, 57);
        Date date = calendar.getTime();
        String timeFormatted = dateFormatter.getFormattedDateString(rightTimeZone, date);

        //Testing that the output timeformat is the same as predicted
        assertThat(timeFormatted, is("28 mar. 2018 04:57 PM"));
    }
}

