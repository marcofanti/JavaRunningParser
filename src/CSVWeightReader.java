import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CSVWeightReader {
    private static final Logger LOGGER = Logger.getLogger(CSVWeightReader.class.getName());
    // Define the date format
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) {
        LOGGER.setLevel(Level.INFO);
        String csvWeightFile =
                "/Users/mfanti/Documents/Master_Of_Data_Science_CU/Java_Parser/JavaRunningParser/data_MAR_1677860907/weight.csv";
        String line = "";
        String csvDelimiter = ",";

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(
                    "/Users/mfanti/Documents/Master_Of_Data_Science_CU/Java_Parser/JavaRunningParser/data/weight.csv"));
            writer.write("Year, " + "DayOfYear, " + "WeekOfYear, " + "DateTime, " + "Weight\n");
            CSVWriter.writeCSVLine(csvWeightFile, csvDelimiter, writer, formatter, LOGGER);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
