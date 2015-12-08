package Tests;

import Common.TestHelper;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex.qatools.allure.annotations.*;

import java.util.Arrays;
import java.util.Collection;

@Title("Reset Password Feature Test Suite")
@Description("This suite's tests covers functional requirements for Reset Password feature.")
@RunWith(Parameterized.class)
public class TestsResetPassword {
    private String webdriver;

    @Parameterized.Parameters
    public static Collection testData() {
        return Arrays.asList(new Object[][] {
                {"firefox"},
                {"chrome"}
        });
    }

    public TestsResetPassword(String webdriver) {
        this.webdriver = webdriver;
    }

    @Before
    public void setUp() {
        TestHelper.localDriverFullscreen(webdriver);
    }
    @After
    public void tearDown() {
        TestHelper.quit();
    }



}
