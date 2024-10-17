import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SelectClasses({ CreditCardTest.class, LoanTest.class })
@SuiteDisplayName("All Tests Suite")
public class AllTestsSuite {
}