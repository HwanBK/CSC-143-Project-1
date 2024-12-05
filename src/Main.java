import java.io.File;
import java.io.FileNotFoundException;

/**
 * Client example code.
 *
 * @author Hwansu Kim (Billy)
 * @version 04.16.2022
 */
public class Main {
    /**
     * main method for client code.
     *
     * @param args n/a.
     * @throws FileNotFoundException throws a FileNotFoundException.
     */
    public static void main(String[] args) throws FileNotFoundException {
        File riverCountFile = new File("RiverPollutionDataCounts.txt");
        File riverDataFile = new File("RiverPollutionData.txt");

        PollutionDataManager newManager = new PollutionDataManager(riverCountFile, riverDataFile);
        System.out.println("getRiverName: " + newManager.getRiverName(1));
        System.out.println("findRiver: " + newManager.findRiver("WichITa RiVer"));

        System.out.println("findRiver (Fail result): " + newManager.findRiver("My toilet"));
        System.out.println("getRiverCount: " + newManager.getRiverCount());
        System.out.println("getPollutionReadingCount: " +
                            newManager.getPollutionReadingCount(0, 1));

        System.out.println();
        System.out.println(newManager.getPollutionData(34, 12, 22));
        System.out.println(newManager.generateArsenicReport());
    }
}
