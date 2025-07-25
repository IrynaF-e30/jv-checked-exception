package core.basesyntax;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.util.Arrays;

import static org.junit.Assert.*;

public class UserServiceTest {
    private static UserService userService;
    private static final PasswordValidator passwordValidator;

    static {
        passwordValidator = new PasswordValidator();
    }

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeClass
    public static void setUp() {
        userService = new UserService();
    }

    @Before
    public void beforeTest() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void afterTest() {
        outContent.reset();
        System.setOut(originalOut);
    }

    @Test
    public void registerUser_correctInputData() {
        User user = new User("email@email", "Password#123", "Password#123");
        userService.registerUser(user);
        String actualMessage = outContent.toString().trim();
        String expectedResult = "User " + user + " was saved to database!!!";
        assertEquals("User " + user + " should be saved. " +
            "Let's call method saveUser()\n", expectedResult, actualMessage);
    }

    @Test
    public void registerUser_incorrectInputData() {
        User user = new User("email@email", "123", "123");
        userService.registerUser(user);
        String actualMessage = outContent.toString().trim();
        String expectedResult = "Your passwords are incorrect. Try again.";
        assertEquals("You should print message: \"" + expectedResult
            + "\" in catch block, after failed validation\n", expectedResult, actualMessage);
    }

    @Test
    public void registerUser_throwsException() {
        Class<?>[] exceptionTypes = getRegisterMethod().getExceptionTypes();
        assertEquals("Don't add exception to the signature of method registerUser(). " +
            "Let's use try-catch construction\n", 0, exceptionTypes.length);
    }

    private Method getRegisterMethod() {
        return Arrays.stream(UserService.class.getDeclaredMethods())
            .filter(m -> m.getName().equals("registerUser"))
            .findFirst()
            .orElseThrow(() -> new RuntimeException("Method registerUser() should be present " +
                "in the UserService class"));
    }

    @Test
    public void passwordValidate_exceptionClassHasConstructor() {
        boolean isInputParamPresent =
            Arrays.stream(PasswordValidationException.class.getConstructors())
                .flatMap(c -> Arrays.stream(c.getParameterTypes()))
                .anyMatch(t -> t.equals(String.class));
        assertTrue("Don't hardcode the message in the exception class, "
                + "you should have constructor with message\n", isInputParamPresent);
    }

    @Test
    public void passwordValidate_exceptionClassIsChecked() {
        try {
            User user = new User("login@email", "Test_1233", "Test_12345");
            passwordValidator.validate(user.password(), user.repeatPassword());
        } catch (RuntimeException e) {
            fail("You should create a checked exception 'PasswordValidationException'\n");
        } catch (Exception ignored) {
        }
    }

    @Test
    public void passwordValidate_exceptionExpected() {
        try {
            User user = new User("login@email", "Test_1233", "Test_12345");
            passwordValidator.validate(user.password(), user.repeatPassword());
        } catch (Exception e) {
            assertEquals("Your exception should have a message \"Wrong passwords\"\n",
                "Wrong passwords", e.getMessage());
        }
    }

    @Test
    public void passwordValidate_throwsExceptionExpected() {
        Class<?>[] exceptionTypes = getValidateMethod().getExceptionTypes();
        assertTrue("Add an exception to the signature of method validate()\n",
            exceptionTypes.length != 0);

        assertEquals("You should throw only one exception in signature of the " +
            "method validate()\n", 1, exceptionTypes.length);

        assertEquals("You should add your exception to signature of method validate()\n",
            "PasswordValidationException", exceptionTypes[0].getSimpleName());
    }

    private Method getValidateMethod() {
        return Arrays.stream(PasswordValidator.class.getDeclaredMethods())
            .filter(m -> m.getName().equals("validate"))
            .findFirst()
            .orElseThrow(() -> new RuntimeException("Method validate() should be present " +
                "in the PasswordValidator class"));
    }

    @Test
    public void passwordValidate_correctInputData() {
        User user = new User("login@email", "Test_12345", "Test_12345");
        try {
            passwordValidator.validate(user.password(), user.repeatPassword());
        } catch (Exception e) {
            fail("Checking of passwords doesn't work correctly! " +
                    "We got exception while testing a valid input\n");
        }
    }

    @Test
    public void passwordValidate_incorrectInputData() {
        User user = new User("login@email", "test_12345", "Test_12345");
        try {
            passwordValidator.validate(user.password(), user.repeatPassword());
            fail("Validation should throw PasswordValidationException for parameters: "
                 + "password - " + user.password()
                 + " and repeatPassword - " + user.repeatPassword() + "\n");
        } catch (Exception ignored) {
        }
    }

    @Test
    public void passwordValidate_shortInputData() {
        User user = new User("login@email", "1111", "1111");
        try {
            passwordValidator.validate(user.password(), user.repeatPassword());
            fail("Validation should throw PasswordValidationException for parameters: "
                 + "password - " + user.password()
                 + " and repeatPassword - " + user.repeatPassword() + "\n");
        } catch (Exception ignored) {
        }
    }

    @Test
    public void passwordValidate_emptyInputData() {
        User user = new User("login@email", "", "");
        try {
            passwordValidator.validate(user.password(), user.repeatPassword());
            fail("Validation should throw PasswordValidationException for empty input data\n");
        } catch (Exception ignored) {
        }
    }

    @Test
    public void passwordValidate_nullInputData() {
        User user = new User("login@email", null, null);
        try {
            passwordValidator.validate(user.password(), user.repeatPassword());
            fail("Validation should throw PasswordValidationException for null input data\n");
        } catch (NullPointerException e) {
            Assert.fail("Validation shouldn't throw NullPointerException for parameters: password - "
                        + user.password() + " and repeatPassword - " + user.repeatPassword() + "\n");
        } catch (Exception ignored) {
        }
    }
}
