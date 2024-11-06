import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.example.CreditCard;

public class CreditCardTest {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/yy");
    private static Calendar cal;
    private CreditCard testCard;

    @BeforeAll
    public static void setUpBeforeClass() {
        cal = Calendar.getInstance();
        System.out.println("Initializing static resources before all tests");
    }

    @AfterAll
    public static void tearDownAfterClass() {
        cal = null;
        System.out.println("Static resources cleaned up after all tests");
    }

    @BeforeEach
    public void setUp() {
        cal.set(2025, Calendar.JANUARY, 1);
        testCard = new CreditCard("P01","1234 5678 9012 3456", "Test User", cal.getTime(), "123");
        System.out.println("Initialized a new CreditCard instance for each test");
    }

    @AfterEach
    public void tearDown() {
        testCard = null;
        System.out.println("Test resources reset after each test");
    }

    @Test
    @DisplayName("CVV Validity Test")
    public void testIsCvvValid() {
        assertTrue(testCard.isCvvValid("123"), "CVV should be valid.");
        assertFalse(testCard.isCvvValid("abc"), "CVV should be invalid.");
    }

    @Test
    @DisplayName("Card Number Validity Test")
    public void testIsCardNumberValid() {
        assertTrue(testCard.isCardNumberValid(), "Card number should be valid.");

        testCard.setCardNumber("12345"); // Invalid number
        assertFalse(testCard.isCardNumberValid(), "Card number should be invalid.");
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/card.csv")
    @DisplayName("Card Type Detection Test")
    public void testGetCardType(String cardNumber, String expectedType) {
        CreditCard card = new CreditCard("P01",cardNumber, "Jane Doe", new Date(), "123");
        assertEquals(expectedType, card.getCardType(), "Card type is different from expected.");
    }

    @Test
    @DisplayName("ToString Method Test")
    public void testToString() {
        String expectedString = "CreditCard{productId='P01', cardNumber='1234 5678 9012 3456', cardHolder='Test User', expirationDate=" + cal.getTime() + ", cvv='123'}";
        assertEquals(expectedString, testCard.toString(), "The toString method should return the expected value.");
    }

    @ParameterizedTest
    @CsvSource({
            "01/25, true",
            "12/30, true",
            "02/24, false",
            "01/23, false"
    })
    @DisplayName("Expiration Date Validity Test")
    public void testIsExpirationDateValid(String expirationDateStr, boolean expectedValidity) throws ParseException {
        Date expirationDate = dateFormat.parse(expirationDateStr);
        CreditCard card = new CreditCard("P03","1234 5678 9012 3456", "John Doe", expirationDate, "123");

        assertEquals(expectedValidity, card.isExpirationDateValid(), "Expiration date validity is different from expected.");
    }
}
