import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class DateParser {
    public static void main(String[] args) {
        String dateStr = "2017-08-19T23:35:00.000Z";

        // Parse the date string into an Instant object
        Instant instant = Instant.parse(dateStr);

        // Convert the Instant to a LocalDateTime in the default time zone
        LocalDateTime localDateTime = instant.atZone(ZoneId.systemDefault()).toLocalDateTime();

        // Format the LocalDateTime as a string
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDate = localDateTime.format(formatter);

        // Print the formatted date
        System.out.println(formattedDate);
    }
}
