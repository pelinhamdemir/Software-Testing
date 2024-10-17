import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({CreditCardTest.class, LoanTest.class})
public class BankTestSuite {
    // Suite sınıfı, belirtilen test sınıflarını çalıştırır.
}

