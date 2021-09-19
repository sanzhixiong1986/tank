import java.io.IOException;
import java.util.Properties;

/**
 * @author joy
 * @date 2021/9/15
 */
public class TestConfig {
    public static void main(String[] args) {
        Properties properties = new Properties();
        try {
            properties.load(TestConfig.class.getClassLoader().getResourceAsStream("config"));
            System.out.println((String) properties.get("intGameTankNum"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
