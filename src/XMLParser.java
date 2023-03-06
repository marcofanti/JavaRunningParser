import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.WeekFields;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLParser {
    private static final Logger LOGGER = Logger.getLogger(XMLParser.class.getName());

    public static void main(String[] args) {
        LOGGER.setLevel(Level.INFO);
        try {
            // Create a DocumentBuilderFactory
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

            // Create a DocumentBuilder
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Specify the directory containing the XML files
            File dir = new File("/Users/mfanti/Documents/Master_Of_Data_Science_CU/Java_Parser/JavaRunningParser/smashrun-2023-03-03");

            // Get a list of the XML files in the directory
            File[] files = dir.listFiles((dir1, name) -> name.endsWith(".tcx"));

            BufferedWriter writer = new BufferedWriter(new FileWriter("/Users/mfanti/Documents/Master_Of_Data_Science_CU/Java_Parser/JavaRunningParser/data/running.csv"));
            writer.write("Year, " + "DayOfYear, " + "WeekOfYear, " + "DateTime, " + "distanceMeters, " + "totalTimeSeconds\n");

            // Iterate over the XML files
            for (File file : files) {
                // Parse the XML file into a Document object
                Document doc = builder.parse(file);

                // Get the root element of the document
                Element root = doc.getDocumentElement();

                // Get the activity element
                NodeList activityList = root.getElementsByTagName("Activity");
                Element activityElement = (Element) activityList.item(0);

                // Get the start time
                NodeList lapList = activityElement.getElementsByTagName("Lap");
                Element lapElement = (Element) lapList.item(0);
                String startTimeStr = lapElement.getAttribute("StartTime");

                Instant startTime = Instant.parse(startTimeStr);
                LocalDateTime localDateTime = startTime.atZone(ZoneId.systemDefault()).toLocalDateTime();
                int year = localDateTime.getYear();

                NodeList distanceMetersList = lapElement.getElementsByTagName("DistanceMeters");
                Node distanceMetersNode = distanceMetersList.item(0);
                String distanceMetersStr = distanceMetersNode.getTextContent();
                Double distanceMetersDouble = Double.parseDouble(distanceMetersStr);
                int distanceMeters = distanceMetersDouble.intValue();

                NodeList totalTimeSecondsList = lapElement.getElementsByTagName("TotalTimeSeconds");
                Node totalTimeSecondsNode = totalTimeSecondsList.item(0);
                String totalTimeSecondsStr = totalTimeSecondsNode.getTextContent();
                Integer totalTimeSecondsInteger = Integer.parseInt(totalTimeSecondsStr);
                int totalTimeSeconds = totalTimeSecondsInteger.intValue();

                // Print the results
                int weekNumber = (localDateTime.getDayOfYear() + 6) / 7;
                writer.write(year + "," + localDateTime.getDayOfYear() + "," + + weekNumber + "," + localDateTime + "," + distanceMeters + "," + totalTimeSeconds + "\n");
                LOGGER.info(year + "," + localDateTime.getDayOfYear() + "," + + weekNumber + "," + localDateTime + "," + distanceMeters + "," + totalTimeSeconds);
            }
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
