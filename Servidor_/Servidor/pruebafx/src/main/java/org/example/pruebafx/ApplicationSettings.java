package org.example.pruebafx;

import org.apache.log4j.Logger;
import org.ini4j.Ini;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ApplicationSettings {
    private final Properties properties;
    private static final Logger LOG = Log.getLogger(ApplicationSettings.class);
    private static final String INI_FILENAME = "settings.ini";
    private static ApplicationSettings instance = null;

    private int puertoServidor;
    private String carpetaBibliotecaMusical;

    public static ApplicationSettings getApplicationSettings() {
        if (instance == null) {
            instance = new ApplicationSettings();
        }
        return instance;
    }

    private ApplicationSettings() {
        this.properties = new Properties();
        try {
            this.properties.load(new FileInputStream(INI_FILENAME));
            this.leerSettings();
        } catch (Exception e) {
            LOG.error("Error:: Error al leer", e);
            throw new RuntimeException("settings.ini no encontrado o incorrecto");
        }
    }

    private void leerSettings() throws Exception {
        this.puertoServidor = this.leerIntProperty("puerto");
        this.carpetaBibliotecaMusical = this.leerStringProperty("RutaMusica");
    }

    private int leerIntProperty(String key) {
        String value = properties.getProperty(key);
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            LOG.error("Error:: El valor de la clave es inválido, tiene que ser entero", e);
            throw e;
        }
    }

    private String leerStringProperty(String key) throws Exception {
        String value = properties.getProperty(key);
        if (value == null || value.isEmpty()) {
            throw new Exception("Error:: El valor de la clave es inválido");
        }
        return value;
    }

    public String getCarpetaBibliotecaMusical() {
        return carpetaBibliotecaMusical;
    }

    public int getPuertoServidor() {
        return puertoServidor;
    }
}
