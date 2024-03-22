import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class EscribirIni {
    public static void main(String[] args) {

        // Ruta al archivo INI
        String rutaArchivo = "data.ini";

        // Crear una instancia de la clase Properties
        Properties propiedades = new Properties();

        // Agregar las claves y valores al objeto Properties
        propiedades.setProperty("clave1", "valor");
        propiedades.setProperty("clave2", "valor 1 prueba");

        // Almacenar el objeto Properties en un archivo INI
        try (FileOutputStream fos = new FileOutputStream(rutaArchivo)) {
            propiedades.store(fos, "Comentario");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
