import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class LeerIni {

    public static void main(String[] args) {

        // Ruta al archivo INI
        String rutaArchivo = "data.ini";

        // Crear una instancia de la clase Properties
        Properties propiedades = new Properties();

        // Cargar el archivo INI
        try (FileReader lector = new FileReader(rutaArchivo)) {
            propiedades.load(lector);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Obtener el valor de una clave
        String valor = propiedades.getProperty("clave1");
        String valor2 = propiedades.getProperty("clave2");
        String valor3 = propiedades.getProperty("clave3");

        // Imprimir el valor
        System.out.println("Valor: " + valor);
        System.out.println("Valor: " + valor2);
        System.out.println("Valor: " + valor3);
    }
}
