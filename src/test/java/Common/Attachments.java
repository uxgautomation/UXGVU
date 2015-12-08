package Common;

import ru.yandex.qatools.allure.annotations.Attachment;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Attachments {
//    @Attachment(value = "{0}", type = "image/png")
//    public static byte[] saveImageAttach(String attachName) {
//        try {
//            URL defaultImage = SmokeTestSteps.class.getResource("/allure.png");
//            File imageFile = new File(defaultImage.toURI());
//            return toByteArray(imageFile);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return new byte[0];
//    }
//    private static byte[] toByteArray(File file) throws IOException {
//        return Files.readAllBytes(Paths.get(file.getPath()));
//    }
//
//    @Attachment(value = "{0}", type = "text/plain")
//    public static String saveTextLog(String attachName, String message) {
//        return message;
//    }
}
