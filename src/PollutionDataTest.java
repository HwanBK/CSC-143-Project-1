import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for PollutionData.
 *
 * @author Hwansu Kim (Billy)
 * @version 04.16.2022
 */
class PollutionDataTest {

    /** PollutionData one for testing */
    private PollutionData testData;
    /** PollutionData two for testing */
    private PollutionData testDataTwo;
    /** PollutionData three for testing */
    private PollutionData testDataThree;
    /** PollutionData four for testing */
    private PollutionData testDataFour;


    /**
     * Constructor for instantiating PollutionData objects for testing.
     */
    public PollutionDataTest() {
        testData = new PollutionData(0, 1, 1, 4, 0,
                                0.2, 0.383);

        testDataTwo = new PollutionData(0, 12, 31, 43, 52,
                                    0.0, 0.0);

        testDataThree = new PollutionData(1, 1, 2, 65, 31,
                                        0.0, 0.0);

        testDataFour = new PollutionData(1, 12, 30, 67,
                                        21, 0.0, 0.0);
    }


    /**
     *
     */
    @BeforeEach
    void setUp() {
    }


    /**
     *
     */
    @AfterEach
    void tearDown() {
    }



    // HAPPY PATH TESTS //
    /**
     * Test method for the constructor.
     */
    @Test
    public void testConstructor() {
        assertEquals(0, testData.riverIndex());
        assertEquals(1, testData.monthNumber());
        assertEquals(1, testData.dayNumber());
        assertEquals(4, testData.arsenicReading());
        assertEquals(0, testData.leadReading());
        assertEquals(0.2, testData.fertilizerReading());
        assertEquals(0.383, testData.pesticideReading());
    }


    /**
     * Test method for riverIndex accessor.
     */
    @Test
    void riverIndex() {
        assertEquals(0, testData.riverIndex());
        assertEquals(0, testDataTwo.riverIndex());
        assertEquals(1, testDataThree.riverIndex());
        assertEquals(1, testDataFour.riverIndex());
    }


    /**
     * Test method for monthNumber accessor.
     */
    @Test
    void monthNumber() {
        assertEquals(1, testData.monthNumber());
        assertEquals(12, testDataTwo.monthNumber());
        assertEquals(1, testDataThree.monthNumber());
        assertEquals(12, testDataFour.monthNumber());
    }


    /**
     * Test method for monthNumber accessor.
     */
    @Test
    void dayNumber() {
        assertEquals(1, testData.dayNumber());
        assertEquals(31, testDataTwo.dayNumber());
        assertEquals(2, testDataThree.dayNumber());
        assertEquals(30, testDataFour.dayNumber());
    }


    /**
     * Test method for arsenicReading accessor.
     */
    @Test
    void arsenicReading() {
        assertEquals(4, testData.arsenicReading());
        assertEquals(43, testDataTwo.arsenicReading());
        assertEquals(65, testDataThree.arsenicReading());
        assertEquals(67, testDataFour.arsenicReading());
    }


    /**
     * Test method for leadReading accessor.
     */
    @Test
    void leadReading() {
        assertEquals(0, testData.leadReading());
        assertEquals(52, testDataTwo.leadReading());
        assertEquals(31, testDataThree.leadReading());
        assertEquals(21, testDataFour.leadReading());
    }


    /**
     * Test method for fertilizerReading accessor.
     */
    @Test
    void fertilizerReading() {
        assertEquals(0.2, testData.fertilizerReading());
        assertEquals(0.0, testDataTwo.fertilizerReading());
        assertEquals(0.0, testDataThree.fertilizerReading());
        assertEquals(0.0, testDataFour.fertilizerReading());
    }


    /**
     * Test method for pesticideReading accessor.
     */
    @Test
    void pesticideReading() {
        assertEquals(0.383, testData.pesticideReading());
        assertEquals(0.0, testDataTwo.pesticideReading());
        assertEquals(0.0, testDataThree.pesticideReading());
        assertEquals(0.0, testDataFour.pesticideReading());
    }


    // EXCEPTION TESTS //
    /**
     * Test method for exceptions.
     */
    @Test
    void testExceptions() {
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new PollutionData(-1, 1, 1, 2, 3, 0.2,
                                0);
        });
    }
}