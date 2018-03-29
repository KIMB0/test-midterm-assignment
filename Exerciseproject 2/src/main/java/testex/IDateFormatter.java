package testex;

import java.util.Date;

public interface IDateFormatter {

    public String getFormattedDateString(String timeZone, Date time) throws JokeException;
}
