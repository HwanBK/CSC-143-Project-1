import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for methods that interact with PollutionData objects.
 *
 * @author Hwansu Kim (Billy)
 * @version 04.16.2022
 */
class PollutionDataManagerTest {

    /** PollutionDataManager for all tests */
    private PollutionDataManager testManager;


    /**
     * Constructor for instantiating PollutionDataManager for testing.
     *
     * @throws FileNotFoundException throws a FileNotFoundException.
     */
    public PollutionDataManagerTest() throws FileNotFoundException {
        File testCountFile = new File("TestPollutionDataCounts.txt");
        File testDataFile = new File("TestPollutionData.txt");
        testManager = new PollutionDataManager(testCountFile, testDataFile);
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
    void testConstructor() {
        assertEquals("Leon River", testManager.getRiverName(1));
        assertEquals(1, testManager.findRiver("Leon River"));
        assertEquals(2, testManager.getRiverCount());
        assertEquals(22, testManager.getPollutionReadingCount(0, 12));
        assertEquals(new PollutionData(0, 11, 1, 46, 56,
                                        0.0, 0.0), testManager.getPollutionData(0,
                                        11, 0));
    }


    /**
     * Test method for getRiverName.
     */
    @Test
    void getRiverName() {
        assertEquals("Blanco River", testManager.getRiverName(0));
        assertNotEquals("Leon River", testManager.getRiverName(0));
        assertEquals("Leon River", testManager.getRiverName(1));
        assertNotEquals("Blanco River", testManager.getRiverName(1));
    }


    /**
     * Test method for findRiver.
     */
    @Test
    void findRiver() {
        assertEquals(0, testManager.findRiver("Blanco River"));
        assertNotEquals(1, testManager.findRiver("Blanco River"));
        assertEquals(1, testManager.findRiver("Leon River"));
        assertNotEquals(0, testManager.findRiver("Leon River"));
    }


    /**
     * Test method for getRiverCount.
     */
    @Test
    void getRiverCount() {
        assertEquals(2, testManager.getRiverCount());
        assertNotEquals(1, testManager.getRiverCount());
        assertNotEquals(3, testManager.getRiverCount());
    }


    /**
     * Test method for getPollutionReadingCount.
     */
    @Test
    void getPollutionReadingCount() {
        assertEquals(25, testManager.getPollutionReadingCount(0, 1));
        assertEquals(19, testManager.getPollutionReadingCount(0, 6));
        assertEquals(22, testManager.getPollutionReadingCount(0, 12));
        assertEquals(22, testManager.getPollutionReadingCount(1, 1));
        assertEquals(21, testManager.getPollutionReadingCount(1, 12));
    }


    /**
     * Test method for getPollutionData.
     */
    @Test
    void getPollutionData() {
        assertEquals(new PollutionData(0, 12, 31, 43, 52,
                                        0.0, 0.0)
                                        , testManager.getPollutionData(0, 12, 21));

        assertEquals(new PollutionData(1, 1, 31, 49, 31,
                                        0.0, 0.0)
                                        , testManager.getPollutionData(1, 1, 21));
    }


    /**
     * Test method for generateArsenicReport.
     */
    @Test
    void generateArsenicReport() {
        assertNotNull(testManager.generateArsenicReport());
    }


    // EXCEPTION TESTS //
    /**
     * Test method for exceptions.
     */
    @Test
    void testExceptions() {
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            testManager.getPollutionReadingCount(-1, 32);
        });
    }
}