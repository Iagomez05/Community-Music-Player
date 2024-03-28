package org.example.pruebafx;

import org.apache.log4j.Logger;
import org.ini4j.Ini;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class INI {
    private final Properties properties;
    private static final Logger LOG = Log.getLogger(INI.class);
    public INI(String filePath) {
        properties = new Properties();
        try {
            properties.load(new FileInputStream(filePath));
        } catch (IOException e) {
            e.printStackTrace();
            LOG.error("Error:: Error al leer");
        }
    }

    public String getValue(String key) {
        System.out.println(properties.getProperty(key));
        return properties.getProperty(key);
    }

    public int getIntValue(String key) {
        String value = properties.getProperty(key);
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            System.out.println("Error: El valor para la clave '" + key + "' no es un número entero válido.");
            LOG.error("Error:: El valor de la clave es invalido, tiene que ser entero");
            return 0; // Devuelve 0 , lo cual seria un error
        }
    }
    public static String Carpeta(String rutaArchivoIni) {
        try {
            File fileToParse = new File(rutaArchivoIni);
            Ini ini = new Ini(fileToParse);
            return ini.get("Configuracion", "RutaMusica");
        } catch (IOException e) {
            System.err.println("Error al leer el archivo INI: " + e.getMessage());
            LOG.error("Error al leer el archivo INI: " + e.getMessage());
            return null;
        }
    }
    public static String Carpeta1(String rutaArchivoIni) {
        try {
            File fileToParse = new File(rutaArchivoIni);
            Ini ini = new Ini(fileToParse);
            return ini.get("Configuracion", "RutaMusics");
        } catch (IOException e) {
            System.err.println("Error al leer el archivo INI: " + e.getMessage());
            LOG.error("Error al leer el archivo INI: " + e.getMessage());
            return null;
        }
    }
}
