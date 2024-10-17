import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.api.Test;

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
        // Set a default valid expiration date and a default test credit card instance for each test
        cal.set(2025, Calendar.JANUARY, 1);
        testCard = new CreditCard("1234 5678 9012 3456", "Test User", cal.getTime(), "123");
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
        assertTrue(testCard.isCvvValid("123"), "CVV geçerli olmalı.");
        assertFalse(testCard.isCvvValid("456"), "CVV geçersiz olmalı.");
    }

    @Test
    @DisplayName("Card Number Validity Test")
    public void testIsCardNumberValid() {
        assertTrue(testCard.isCardNumberValid(), "Kart numarası geçerli olmalı.");

        testCard.setCardNumber("12345"); // Geçersiz numara
        assertFalse(testCard.isCardNumberValid(), "Kart numarası geçersiz olmalı.");
    }

    @ParameterizedTest
    @CsvSource({
            "4111 1111 1111 1111, Visa",
            "5111 1111 1111 1111, MasterCard",
            "6111 1111 1111 1111, Unknown"
    })
    @DisplayName("Card Type Detection Test")
    public void testGetCardType(String cardNumber, String expectedType) {
        CreditCard card = new CreditCard(cardNumber, "Jane Doe", new Date(), "123");
        assertEquals(expectedType, card.getCardType(), "Kart tipi beklenenden farklı.");
    }

    @Test
    @DisplayName("ToString Method Test")
    public void testToString() {
        String expectedString = "CreditCard{cardNumber='1234 5678 9012 3456', cardHolder='Test User', expirationDate=" + cal.getTime() + ", cvv='123'}";
        assertEquals(expectedString, testCard.toString(), "toString metodu beklenen değeri döndürmelidir.");
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
        CreditCard card = new CreditCard("1234 5678 9012 3456", "John Doe", expirationDate, "123");

        assertEquals(expectedValidity, card.isExpirationDateValid(), "Son kullanma tarihi geçerliliği beklenenden farklı.");
    }
}