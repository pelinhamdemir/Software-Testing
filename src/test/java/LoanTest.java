import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import com.example.Loan;

import java.util.Calendar;
import java.util.Date;

public class LoanTest {
    private Loan loan;

    @BeforeEach
    public void setUp() {
        Calendar cal = Calendar.getInstance();
        cal.set(2023, Calendar.JANUARY, 1);
        Date loanStartDate = cal.getTime();
        loan = new Loan("L001", "John Doe", 100000, 5, loanStartDate, 10);
        System.out.println("Loan instance created for testing");
    }

    @AfterEach
    public void tearDown() {
        loan = null;
        System.out.println("Loan instance cleaned up after tests");
    }

    @Test
    @DisplayName("Loan ID Assertion Test")
    public void testLoanId() {
        assertNotNull(loan.getProductId(), "Loan ID should not be null");
        assertEquals("L001", loan.getProductId(), "Loan ID should match expected value");
    }

    @Test
    @DisplayName("Customer Name Assertion Test")
    public void testCustomerName() {
        assertNotNull(loan.getCustomerName(), "Customer Name should not be null");
        assertEquals("John Doe", loan.getCustomerName(), "Customer Name should match expected value");
    }

    @Test
    @DisplayName("Loan Amount Assertion Test")
    public void testLoanAmount() {
        assertTrue(loan.getLoanAmount() > 0, "Loan amount should be greater than zero");
        assertEquals(100000, loan.getLoanAmount(), 1, "Loan amount should match expected value");
    }

    @Test
    @DisplayName("Interest Rate Assertion Test")
    public void testInterestRate() {
        assertTrue(loan.getInterestRate() > 0, "Interest rate should be positive");
        assertEquals(5, loan.getInterestRate(), 0.01, "Interest rate should match expected value");
    }

    @Test
    @DisplayName("Monthly Interest Rate Calculation Test")
    public void testCalculateMonthlyInterestRate() {
        double monthlyInterestRate = loan.calculateMonthlyInterestRate();
        assertEquals(0.00416666667, monthlyInterestRate, 0.00000001, "Monthly interest rate should match expected value.");
    }

    @Test
    @DisplayName("Loan Term Assertion Test")
    public void testTermInYears() {
        assertEquals(10, loan.getTermInYears(), "Loan term should be 10 years");
    }

    @Test
    @DisplayName("Monthly Payment Calculation Test")
    public void testCalculateMonthlyPayment() {
        double monthlyPayment = loan.calculateMonthlyPayment();
        assertTrue(monthlyPayment > 0, "Monthly payment should be greater than zero");
        assertEquals(1060.66, monthlyPayment, 0.01, "Monthly payment should match expected value");
    }

    @Test
    @DisplayName("Total Repayment Calculation Test")
    public void testCalculateTotalRepayment() {
        double totalRepayment = loan.calculateTotalRepayment();
        assertTrue(totalRepayment > loan.getLoanAmount(), "Total repayment should be greater than loan amount");
        assertEquals(127278.61, totalRepayment, 0.01, "Total repayment should match expected value");
    }

    @Test
    @DisplayName("Loan Start Date Assertion Test")
    public void testLoanStartDate() {
        assertNotNull(loan.getLoanStartDate(), "Loan start date should not be null");
    }



    @Test
    @DisplayName("White-Box Invalid Loan Term Case Test")
    public void testInvalidLoanTerm() {
        Loan loan = new Loan("L123", "John Doe", 100000, 5, new Date(), 0); // termInYears = 0
        assertThrows(IllegalArgumentException.class, loan::calculateMonthlyPayment);
    }

    @DisplayName("White-Box Invalid Loan Amount Case Test")
    @Test
    public void testInvalidLoanAmount() {
        Loan loan = new Loan("L123", "John Doe", 0, 5, new Date(), 15); // loanAmount = 0
        assertThrows(IllegalArgumentException.class, loan::calculateMonthlyPayment);
    }

    @Test
    @DisplayName("White-Box Interest Rate Edge Case Test")
    public void testZeroInterestRateForWhiteBox() {
        loan.setInterestRate(0);
        assertEquals(loan.getLoanAmount() / (loan.getTermInYears() * 12), loan.calculateMonthlyPayment(), "Monthly payment should match for zero interest");
    }
    @Test
    @DisplayName("White-Box Regular Loan Case Test")
    public void testRegularLoanCalculationForWhiteBox() {
        Loan loan = new Loan("L123", "John Doe", 100000, 5, new Date(), 15); // interestRate = 5%
        double monthlyInterestRate = 5.0 / 12 / 100; // Convert annual rate to monthly rate
        int numberOfPayments = 15 * 12;
        double expectedPayment = 100000 * monthlyInterestRate / (1 - Math.pow(1 + monthlyInterestRate, -numberOfPayments));
        assertEquals(expectedPayment, loan.calculateMonthlyPayment(), 0.01);
    }

    @Test
    @DisplayName("Table-Based Test - Regular Loan Calculation with Positive Interest Rate")
    public void testRegularLoanCalculationForTable() {
        Loan loan = new Loan("123", "John Doe", 100000, 5, new Date(), 15); // Valid case
        double monthlyInterestRate = 5.0 / 12 / 100;
        int numberOfPayments = 15 * 12;
        double expectedPayment = 100000 * monthlyInterestRate / (1 - Math.pow(1 + monthlyInterestRate, -numberOfPayments));
        assertEquals(expectedPayment, loan.calculateMonthlyPayment(), 0.01);
    }

    @Test
    @DisplayName("Table-Based Test - Zero Interest Rate Loan")
    public void testZeroInterestRateForTable() {
        Loan loan = new Loan("124", "Jane Doe", 100000, 0, new Date(), 15); // Zero interest rate
        int numberOfPayments = 15 * 12;
        double expectedPayment = 100000.0 / numberOfPayments;
        assertEquals(expectedPayment, loan.calculateMonthlyPayment(), 0.01);
    }

    @Test
    @DisplayName("Table-Based Test - Short Loan Term")
    public void testShortLoanTerm() {
        Loan loan = new Loan("125", "Alice Smith", 50000, 5, new Date(), 1); // Short term (1 year)
        double monthlyInterestRate = 5.0 / 12 / 100;
        int numberOfPayments = 1 * 12;
        double expectedPayment = 50000 * monthlyInterestRate / (1 - Math.pow(1 + monthlyInterestRate, -numberOfPayments));
        assertEquals(expectedPayment, loan.calculateMonthlyPayment(), 0.01);
    }

    @Test
    @DisplayName("Table-Based Test - Invalid Loan Term (Zero)")
    public void testInvalidLoanTermZero() {
        Loan loan = new Loan("126", "Bob Brown", 100000, 5, new Date(), 0); // termInYears = 0
        assertThrows(IllegalArgumentException.class, loan::calculateMonthlyPayment);
    }

    @Test
    @DisplayName("Table-Based Test - Invalid Loan Amount (Zero)")
    public void testInvalidLoanAmountZero() {
        Loan loan = new Loan("127", "Charlie Green", 0, 5, new Date(), 15); // loanAmount = 0
        assertThrows(IllegalArgumentException.class, loan::calculateMonthlyPayment);
    }

    @Test
    @DisplayName("Table-Based Test - Invalid Loan Term (Negative)")
    public void testInvalidLoanTermNegative() {
        Loan loan = new Loan("128", "Daisy White", 100000, 5, new Date(), -1); // termInYears = -1
        assertThrows(IllegalArgumentException.class, loan::calculateMonthlyPayment);
    }

    @Test
    @DisplayName("Table-Based Test - Invalid Loan Amount (Negative)")
    public void testInvalidLoanAmountNegative() {
        Loan loan = new Loan("129", "Eve Black", -50000, 5, new Date(), 10); // loanAmount = -50000
        assertThrows(IllegalArgumentException.class, loan::calculateMonthlyPayment);
    }

}
