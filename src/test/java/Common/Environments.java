package Common;

import ru.yandex.qatools.allure.annotations.Step;

public class Environments {
    public static String TEST_VOCALPOINT_BASE_URL = "http://test2.vocalpoint.com";
    public static String UAT_VOCALPOINT_BASE_URL = "http://uat.vocalpoint.com";
    public static String TEST_UNRATOJUNTAS_BASE_URL = "http://test2.unratojuntas.com";
    public static String UAT_UNRATOJUNTAS_BASE_URL = "https://uat.unratojuntas.com";

    public static String TEST_VOCALPOINT_BASE_URL_ARTICLES = "http://test2.vocalpoint.com/articles";

    @Step("Go to the testable environment.")
    public static void goTo(String url) {
        TestHelper.get(url);
    }
    @Step("Go to the Un Rato Juntas test environment.")
    public static void goToUnRatoJuntasTestEnvironment(String uri) {
        TestHelper.get(TEST_UNRATOJUNTAS_BASE_URL + uri);
    }
    @Step("Go to the Vocalpoint UAT environment.")
    public static void goToVocalpointUATEnvironment(String uri) {
        TestHelper.get(UAT_VOCALPOINT_BASE_URL + uri);
    }
    @Step("Go to the Un Rato Juntas UAT environment.")
    public static void goToUnRatoJuntasUATEnvironment(String uri) {
        TestHelper.get(UAT_UNRATOJUNTAS_BASE_URL + uri);
    }

    public static String grabbedArticleUrl = "default";

    public static String getGrabbedArticleUrl() {
        grabbedArticleUrl = TestHelper.driver.getCurrentUrl();
        return grabbedArticleUrl;
    }
}