package Steps;

import Common.TestHelper;
import PageObjects.Environments;
import ru.yandex.qatools.allure.annotations.Step;

public class SmokeSteps {
    @Step("Go to the Vocalpoint test environment.")
    public static void goToVocalpointTestEnvironment(String uri) {
        TestHelper.get(Environments.TEST_VOCALPOINT_BASE_URL + uri);
    }
    @Step("Go to the Un Rato Juntas test environment.")
    public static void goToUnRatoJuntasTestEnvironment(String uri) {
        TestHelper.get(Environments.TEST_UNRATOJUNTAS_BASE_URL + uri);
    }
    @Step("Go to the Vocalpoint UAT environment.")
    public static void goToVocalpointUATEnvironment(String uri) {
        TestHelper.get(Environments.UAT_VOCALPOINT_BASE_URL + uri);
    }
    @Step("Go to the Un Rato Juntas UAT environment.")
    public static void goToUnRatoJuntasUATEnvironment(String uri) {
        TestHelper.get(Environments.UAT_UNRATOJUNTAS_BASE_URL + uri);
    }


}
