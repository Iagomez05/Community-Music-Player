import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class LeerIni {
    private Properties properties;

    public LeerIni(String filePath) {
        properties = new Properties();
        try {
            properties.load(new FileInputStream(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getValue(String key) {
        return properties.getProperty(key);
    }

    public int getIntValue(String key) {
        String value = properties.getProperty(key);
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            System.out.println("Error: El valor para la clave '" + key + "' no es un número entero válido.");
            return 0; // Devuelve 0 , lo cual seria un error
        }
    }
}

