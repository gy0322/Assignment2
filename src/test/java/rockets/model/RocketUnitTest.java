package rockets.model;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class RocketUnitTest {

    private Rocket target;
    private Rocket mockTarget;
    private LaunchServiceProvider lsp;

    @BeforeEach
    public void setUp() {
        lsp = mock(LaunchServiceProvider.class);
        LaunchServiceProvider l = new LaunchServiceProvider("hehe", 1949, "China");
        target = new Rocket("HeHe X", "China", l);
        mockTarget = new Rocket("HeHe x", "China", lsp);
    }

    @AfterEach
    public void tearDown() {
    }

    @DisplayName("should create rocket successfully when given right parameters to constructor")
    @Test
    public void shouldConstructRocketObject() {
        String name = "BFR";
        String nationality = "USA";
        LaunchServiceProvider manufacturer = new LaunchServiceProvider("SpaceX", 2002, "USA");
        Rocket bfr = new Rocket(name, nationality, manufacturer);
        assertNotNull(bfr);
    }

    @DisplayName("should throw exception when given null manufacturer to constructor")
    @Test
    public void shouldThrowExceptionWhenNoManufacturerGiven() {
        String name = "BFR";
        String country = "USA";
        assertThrows(NullPointerException.class, () -> new Rocket(name, country, null));
    }

    @DisplayName("should throw exception when pass an empty string as name to the Rocket's constructor")
    @ParameterizedTest
    @ValueSource(strings = {"", " ", "  "})
    public void shouldThrowExceptionWhenSetNameToEmptyString(String name){
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Rocket(name, "China", target.getManufacturer()));
        assertEquals("name cannot be empty or null", exception.getMessage());
    }



    @DisplayName("should throw exception when pass an empty string as country to the Rocket's constructor")
    @ParameterizedTest
    @ValueSource(strings = {"", " ", "  "})
    public void shouldThrowExceptionWhenSetCountryToEmptyString(String country){

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Rocket("HeHe X", country, target.getManufacturer()));
        assertEquals("country cannot be empty or null", exception.getMessage());
    }

    @DisplayName("should throw exception when pass a null as country to the Rocket's constructor")
    @Test
    public void shouldThrowExceptionWhenSetCountryToNull(){

        NullPointerException exception = assertThrows(NullPointerException.class, () -> new Rocket("HeHe X", null, target.getManufacturer()));
        assertEquals("country cannot be empty or null", exception.getMessage());
    }


    @DisplayName("should set rocket massToLEO value")
    @ValueSource(strings = {"10000", "15000"})
    public void shouldSetMassToLEOWhenGivenCorrectValue(String massToLEO) {
        String name = "BFR";
        String nationality = "USA";
        LaunchServiceProvider manufacturer = new LaunchServiceProvider("SpaceX", 2002, "USA");

        Rocket bfr = new Rocket(name, nationality, manufacturer);

        bfr.setMassToLEO(massToLEO);
        assertEquals(massToLEO, bfr.getMassToLEO());
    }

    @DisplayName("should throw exception when set massToLEO to null")
    @Test
    public void shouldThrowExceptionWhenSetMassToLEOToNull() {
        String name = "BFR";
        String nationality = "USA";
        LaunchServiceProvider manufacturer = new LaunchServiceProvider("SpaceX", 2002, "USA");
        Rocket bfr = new Rocket(name, nationality, manufacturer);
        assertThrows(NullPointerException.class, () -> bfr.setMassToLEO(null));
    }

    @DisplayName("should throw exception when pass a name which has empty space at the beginning or the end to the Rocket's constructor")
    @ParameterizedTest
    @ValueSource(strings = {"aa ", " aa", " aa "})
    public void shouldThrowExceptionWhenTheNameContainsEmptySpaceAtTheBeginningOrEnd(String name) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Rocket(name, "China", target.getManufacturer()));
        assertEquals("There should be no empty space at the beginning or the end of a name", exception.getMessage());
    }

    @DisplayName("should throw exception when pass a country which has empty space at the beginning or the end to the Rocket's constructor")
    @ParameterizedTest
    @ValueSource(strings = {"aa ", " aa", " aa "})
    public void shouldThrowExceptionWhenTheCountryContainsEmptySpaceAtTheBeginningOrEnd(String country) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Rocket("hehe X", country, target.getManufacturer()));
        assertEquals("There should be no empty space at the beginning or the end of a country", exception.getMessage());
    }

    @DisplayName("should throw exception when pass a name which length is not between 2 to 40 inclusively to the Rocket's constructor")
    @ParameterizedTest
    @ValueSource(strings = {"1", "12345678901234567890123456789012345678901"})
    public void shouldThrowExceptionWhenTheLengthOfTheNameIsInvalid(String name){

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Rocket(name, "China", target.getManufacturer()));
        assertEquals("The length of the name must be equal or greater than 2 and equal or smaller than 40", exception.getMessage());
    }

    @DisplayName("should Not throw exception when pass a name which length is between 2 to 40 inclusively to the Rocket's constructor")
    @ParameterizedTest
    @ValueSource(strings = {"12","213","12345678901234567890", "123456789012345678901234567890123456789", "1234567890123456789012345678901234567890"})
    public void shouldNotThrowExceptionWhenTheLengthOfTheNameIsValid(String name){

        assertDoesNotThrow(()-> {
            new Rocket(name, "China", target.getManufacturer());
        });

    }

    @DisplayName("should throw exception when pass a country which length is not between 2 to 40 inclusively to the Rocket's constructor")
    @ParameterizedTest
    @ValueSource(strings = {"1", "12345678901234567890123456789012345678901"})
    public void shouldThrowExceptionWhenTheLengthOfTheCountryIsInvalid(String country){

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Rocket("HeHe X", country, target.getManufacturer()));
        assertEquals("The length of the country must be equal or greater than 2 and equal or smaller than 40", exception.getMessage());
    }

    private static Stream<Arguments> LaunchServiceProviderObjectProvider() {

        LaunchServiceProvider l1 = new LaunchServiceProvider("hehe", 1949, "aa");
        LaunchServiceProvider l2 = new LaunchServiceProvider("hehe", 1949, "aaa");
        LaunchServiceProvider l3 = new LaunchServiceProvider("hehe", 1949, "aaaaaaaaaaaaaaaaaaaa");
        LaunchServiceProvider l4 = new LaunchServiceProvider("hehe", 1949, "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        LaunchServiceProvider l5 = new LaunchServiceProvider("hehe", 1949, "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");

        return Stream.of(Arguments.of("aa", l1), Arguments.of("aaa", l2), Arguments.of("aaaaaaaaaaaaaaaaaaaa", l3), Arguments.of("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", l4), Arguments.of("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", l5));
    }

    @DisplayName("should Not throw exception when pass a country which length is between 2 to 40 inclusively to the Rocket's constructor")
    @ParameterizedTest
    @MethodSource("LaunchServiceProviderObjectProvider")
    //@ValueSource(strings = {"aa", "aaa", "aaaaaaaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"})
    public void shouldNotThrowExceptionWhenTheLengthOfTheCountryIsValid(String country, LaunchServiceProvider manufacture){

        assertDoesNotThrow(()-> {
            new Rocket("HeHe X", country, manufacture);
        });
    }
}