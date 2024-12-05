/**
 * Record for PollutionData object class.
 *
 * @author Hwansu Kim (Billy)
 * @version 04.16.2022
 */

/**
 *
 * @param riverIndex        the index of the river.
 * @param monthNumber       the month number, 1-12.
 * @param dayNumber         the day number, 1-31.
 * @param arsenicReading    the arsenic reading, ug/L.
 * @param leadReading       the lead reading, ppb.
 * @param fertilizerReading the fertilizer reading, mg/L.
 * @param pesticideReading  the pesticide reading, mg/L.
 */
public record PollutionData(int riverIndex, int monthNumber, int dayNumber, int arsenicReading, int leadReading
        , double fertilizerReading, double pesticideReading) { //implements Comparable<PollutionData> {


    /**
     * Class constructor.
     *
     * @param riverIndex            the index of the river.
     * @param monthNumber           the month number, 1-12.
     * @param dayNumber             the day number, 1-31.
     * @param arsenicReading        the arsenic reading, ug/L.
     * @param leadReading           the lead reading, ppb.
     * @param fertilizerReading     the fertilizer reading, mg/L.
     * @param pesticideReading      the pesticide reading, mg/L.
     */
    public PollutionData {
        checkNonNegative(riverIndex, "riverIndex");
        checkRange(monthNumber, 1, 12, "monthNumber");

        checkRange(dayNumber, 1, 31, "dayNumber");
        checkNonNegative(arsenicReading, "arsenicReading");

        checkNonNegative(leadReading, "leadReading");
        checkNonNegative(fertilizerReading, "fertilizerReading");

        checkNonNegative(pesticideReading, "pesticideReading");
    }


    /**
     * Accessor for riverIndex.
     *
     * @return the river index.
     */
    public int riverIndex() {
        return this.riverIndex;
    }

    /**
     * Accessor for monthNumber.
     *
     * @return the month number.
     */
    public int monthNumber() {
        return this.monthNumber;
    }

    /**
     * Accessor for dayNumber.
     *
     * @return the day number.
     */
    public int dayNumber() {
        return this.dayNumber;
    }

    /**
     * Accessor for arsenicReading.
     *
     * @return the arsenic reading.
     */
    public int arsenicReading() {
        return this.arsenicReading;
    }

    /**
     * Accessor for leadReading.
     *
     * @return the lead reading.
     */
    public int leadReading() {
        return this.leadReading;
    }

    /**
     * Accessor for fertilizerReading.
     *
     * @return the fertilizer reading.
     */
    public double fertilizerReading() {
        return this.fertilizerReading;
    }

    /**
     * Accessor for pesticideReading.
     *
     * @return the pesticide reading.
     */
    public double pesticideReading() {
        return this.pesticideReading;
    }

    /**
     * String output for PollutionData objects.
     *
     * @return PollutionData string output.
     */
    public String toString() {
        return String.format("River Index: %d\nMonth Number: %d\nDay Number: %d\nArsenic Reading: %d\n" +
                                "Lead Reading: %d\nFertilizer Reading: %f\nPesticide Reading: %f\n",
                                riverIndex(), monthNumber(), dayNumber(), arsenicReading(), leadReading(),
                                fertilizerReading(),pesticideReading());
    }

    /**
     * Exception check for negative values.
     *
     * @param numToCheck            the number to check.
     * @param identifier            the variable name of the number in question.
     */
    private void checkNonNegative(double numToCheck, String identifier) {
        if (numToCheck < 0.0) {
            throw new IllegalArgumentException(identifier + " cannot be negative");
        }
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

    //public int compareTo(PollutionData other) {
    //    return null;
    //}
}
