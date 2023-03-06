import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CSVWriter {

    static void writeCSVLine(String csvOutputFile, String csvDelimiter, BufferedWriter writer,
                             DateTimeFormatter formatter, Logger logger) throws IOException {
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(csvOutputFile))) {
            // Read the CSV file line by line
            line = br.readLine();
            while ((line = br.readLine()) != null) {
                // Split the line into fields using the CSV delimiter
                String[] fields = line.split(csvDelimiter);

                String dateString = fields[0];
                if (dateString.startsWith("\"") && dateString.endsWith("\"")) {
                    dateString = dateString.substring(1, dateString.length() - 1);
                } else {
                    dateString = dateString + " 00:00:00";
                }
                // Parse the date string into a LocalDateTime object
                LocalDateTime localDateTime = LocalDateTime.parse(dateString, formatter);

                int year = localDateTime.getYear();

                String field1Str = fields[1];
                // Print the results
                int weekNumber = (localDateTime.getDayOfYear() + 6) / 7;
                writer.write(year + "," + localDateTime.getDayOfYear() + "," + + weekNumber + "," + localDateTime + "," + field1Str + "\n");
                logger.info(year + "," + localDateTime.getDayOfYear() + "," + + weekNumber + "," + localDateTime + "," + field1Str );
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        writer.close();
    }


}
