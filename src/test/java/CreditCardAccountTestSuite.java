import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SelectClasses({ CreditCardTest.class, AccountTest.class })
@SuiteDisplayName("Credit Card and Account Tests Suite")
public class CreditCardAccountTestSuite {
}