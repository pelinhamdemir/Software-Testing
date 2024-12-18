import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SelectClasses({ CreditCardTest.class, LoanTest.class })
@SuiteDisplayName("Credit Card and Loan Tests Suite")
public class CreditCardLoanTestSuite {
}