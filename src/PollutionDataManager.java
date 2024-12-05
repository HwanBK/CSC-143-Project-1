import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Class for methods that interact with PollutionData objects.
 *
 * @author Hwansu Kim (Billy)
 * @version 04.16.2022
 */
public class PollutionDataManager implements PollutionDataManagerInterface {

    //public enum PollutionType {
    //    ARSENIC,
    //    LEAD,
    //    FERTILIZER,
    //    PESTICIDE
    //}

    // INSTANCE DATA //
    /** 3D-array of PollutionData */
    private PollutionData[][][] dataArray;
    /** Array of river names */
    private String[] riverNameArray;
    // Javadoc placeholder //
    //private PollutionType pollutionType;


    // Constructor //
    /**
     * Class constructor.
     *
     * @param countFile The file containing reading counts.
     * @param dataFile  The file containing reading data.
     */
    public PollutionDataManager(File countFile, File dataFile) {
        Scanner countFileIn = null;
        Scanner dataFileIn = null;
        try {
            countFileIn = new Scanner(countFile);
            dataFileIn = new Scanner(dataFile);
        } catch(FileNotFoundException fileException) {
            System.out.println("The file was not found.");
        }

        int numOfRivers = countFileIn.nextInt();
        countFileIn.nextLine();
        dataFileIn.nextLine();

        riverNameArray = new String[numOfRivers];
        dataArray = new PollutionData[numOfRivers][12][];

        for (int riverIndex = 0; riverIndex < numOfRivers; riverIndex++) {
            String[] countLineArray = countFileIn.nextLine().split(",");
            riverNameArray[riverIndex] = countLineArray[0];

            for (int monthIndex = 0; monthIndex < 12; monthIndex++) {
                dataArray[riverIndex][monthIndex] = new PollutionData[Integer.parseInt(countLineArray[monthIndex + 1])];
            }
        }
        countFileIn.close();

        int dataRiverIndex = 0;
        int monthReadingIndex = 0;
        int lastMonthNumber = -99;
        int lastDayNumber = -99;
        do {
            String[] dataLineArray = dataFileIn.nextLine().split(",");

            int dataMonth = Integer.parseInt(dataLineArray[1]);
            int dataDay = Integer.parseInt(dataLineArray[2]);
            int dataArsenic = Integer.parseInt(dataLineArray[3]);
            int dataLead = Integer.parseInt(dataLineArray[4]);
            double dataFertilizer = Double.parseDouble(dataLineArray[5]);
            double dataPesticide = Double.parseDouble(dataLineArray[6]);

            if (lastMonthNumber > dataMonth) {
                dataRiverIndex += 1;
            }
            if (lastDayNumber > dataDay) {
                monthReadingIndex = 0;
            }
            PollutionData dataObject = new PollutionData(dataRiverIndex, dataMonth, dataDay, dataArsenic, dataLead,
                                                        dataFertilizer, dataPesticide);
            dataArray[dataRiverIndex][dataMonth - 1][monthReadingIndex] = dataObject;

            monthReadingIndex += 1;
            lastMonthNumber = dataMonth;
            lastDayNumber = dataDay;
        } while (dataFileIn.hasNextLine());
        dataFileIn.close();
    }


    /**
     * retrieves the name of a river
     *
     * @param riverIndex the index of the river
     * @return the river name
     */
    @Override
    public String getRiverName(int riverIndex) {
        return riverNameArray[riverIndex];
    }


    /**
     * retrieves the index associated with the specified river name (case insensitive)
     *
     * @param riverName the name of the river
     * @return the associated river's index, or -1 if the river is not found
     */
    @Override
    public int findRiver(String riverName) {
        if (riverName == null || riverName.isEmpty()) {
            throw new IllegalArgumentException("riverName must not be null or empty.");
        }

        int riverIdx = -1;
        for (int riverIndex = 0; riverIndex < getRiverCount(); riverIndex++) {
            if (getRiverName(riverIndex).equalsIgnoreCase(riverName)) {
                riverIdx = riverIndex;
            }
        }
        return riverIdx;
    }


    /**
     * retrieves the count of rivers
     *
     * @return the number of rivers for which data is available
     */
    @Override
    public int getRiverCount() {
        return riverNameArray.length;
    }


    /**
     * retrieves the number of readings available for a specified river and month
     *
     * @param riverIndex  the index of the river
     * @param monthNumber the month number (not index!), 1-12
     * @return the number of data readings available
     */
    @Override
    public int getPollutionReadingCount(int riverIndex, int monthNumber) {
        checkRange(monthNumber, 1, 12, "monthNumber");
        return dataArray[riverIndex][monthNumber - 1].length;
    }


    /**
     * retrieves pollution data for a specified river, month, and day
     *
     * @param riverIndex  the index of the river
     * @param monthNumber the month number (not index!), 1-12
     * @param dayIndex    the day index (not date!)
     * @return the associated pollution data
     */
    @Override
    public PollutionData getPollutionData(int riverIndex, int monthNumber, int dayIndex) {
        return dataArray[riverIndex][monthNumber - 1][dayIndex];
    }


    //**
    // * mutator for the pollutionType.
    // *
    // * @param pollutionType the pollutionType.
    // */
    //public void setPollutionType(PollutionType pollutionType) {
    //    this.pollutionType = pollutionType;
    //}

    //**
    // * creates an array of PollutionAverage.
    // *
    // * @param pollutionType the pollutionType.
    // * @param howMany       number of rivers.
    // * @return array of PollutionAverage
    //*/
    //public PollutionAverage[] getTopNPollutedRivers(PollutionDataManager.PollutionType pollutionType, int howMany) {
    //    if (howMany > getRiverCount()) {
    //        howMany = getRiverCount();
    //    }
    //    PollutionAverage[] arrayOfAvg = new PollutionAverage[howMany];
    //    return null;
    //}


    /**
     * generates a report showing each river's monthly average for arsenic, plus a yearly average
     *
     * @return arsenic report
     */
    @Override
    public String generateArsenicReport() {
        StringBuilder arsenicReport = new StringBuilder(35);
        arsenicReport.append("ARSENIC REPORT\n\n");
        arsenicReport.append(String.format("River%39s %7s %7s %7s %7s %7s %7s %7s %7s %7s %7s %7s %8s",
                                            "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "Avg\n"));

        for (int dash = 0; dash < 38; dash++) {
            arsenicReport.append("-");
        }

        for (int numOfCol = 0; numOfCol < 13; numOfCol++) {
            arsenicReport.append(" ");
            for (int dash = 0; dash < 5; dash++) {
                arsenicReport.append("-");
            }
            arsenicReport.append("  ");
        }

        double arsenicMonthlyTotal = 0.0;
        double arsenicMonthlyAvg = 0.0;
        double arsenicYearlyTotal = 0.0;
        double arsenicYearlyAvg = 0.0;

        for (int riverIndex = 0; riverIndex < getRiverCount(); riverIndex++) {
            arsenicReport.append("\n" + String.format("%-36s", getRiverName(riverIndex)));

            for (int monthIndex = 0; monthIndex < 12; monthIndex++) {
                for (int dayIndex = 0; dayIndex < getPollutionReadingCount(riverIndex, monthIndex + 1);
                     dayIndex++) {
                    arsenicMonthlyTotal += dataArray[riverIndex][monthIndex][dayIndex].arsenicReading();
                }

                arsenicMonthlyAvg = arsenicMonthlyTotal / getPollutionReadingCount(riverIndex,
                                                                        monthIndex + 1);
                arsenicYearlyTotal += arsenicMonthlyAvg;
                arsenicReport.append(String.format("%8.2f", arsenicMonthlyAvg));
                arsenicMonthlyTotal = 0.0;
            }
            arsenicYearlyAvg = arsenicYearlyTotal / 12;
            arsenicReport.append(String.format("%8.2f", arsenicYearlyAvg));
            arsenicYearlyTotal = 0.0;
        }
        arsenicReport.append("\n");
        for (int dash = 0; dash < 38; dash++) {
            arsenicReport.append("-");
        }

        for (int numOfCol = 0; numOfCol < 13; numOfCol++) {
            arsenicReport.append(" ");
            for (int dash = 0; dash < 5; dash++) {
                arsenicReport.append("-");
            }
            arsenicReport.append("  ");
        }
        return arsenicReport.toString();
    }


    /**
     * Exception check for numerical range.
     *
     * @param numToCheck            the number to check.
     * @param minNum                the minimum number of the range.
     * @param maxNum                the maximum number of the range.
     * @param identifier            the variable name of the number in question.
     */
    private void checkRange(int numToCheck, int minNum, int maxNum, String identifier) {
        if (numToCheck < minNum || numToCheck > maxNum) {
            throw new IllegalArgumentException(identifier + " must be between " + minNum + " and " + maxNum + ".");
        }
    }
}
