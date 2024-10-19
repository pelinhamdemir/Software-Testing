import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import com.example.Account;

public class AccountTest {
    private Account account;

    @BeforeEach
    public void setUp() {
        account = new Account(500.0); // Initialize account with a starting balance of 500
        System.out.println("Account instance created for testing");
    }

    @AfterEach
    public void tearDown() {
        account = null;
        System.out.println("Account instance cleaned up after test");
    }

    @Test
    @DisplayName("Deposit Test")
    public void testDeposit() {
        account.putMoney(200.0);
        assertTrue(account.checkBalance() > 500.0, "Balance should be greater than the initial balance after deposit");
        assertNotNull(account, "Account object should not be null after deposit operation");
    }

    @Test
    @DisplayName("Withdrawal Test")
    public void testWithdrawal() {
        account.getMoney(150.0);
        assertTrue(account.checkBalance() < 500.0, "Balance should be less than the initial balance after withdrawal");
        assertFalse(account.checkBalance() < 0, "Balance should not be negative after valid withdrawal");
    }

    @Test
    @DisplayName("Overdraw Test")
    public void testOverdraw() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            account.getMoney(600.0); // Attempt to withdraw more than the balance
        });
        assertNotNull(exception, "Exception should not be null when attempting to overdraw");
        assertTrue(exception.getMessage().contains("Insufficient funds"), "Exception message should indicate insufficient funds");
    }
}