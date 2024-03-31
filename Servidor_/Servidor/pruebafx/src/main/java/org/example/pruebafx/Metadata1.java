package org.example.pruebafx;

import org.apache.log4j.Logger;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.tag.FieldKey;

import java.io.File;

public class Metadata1 {
    private static final Logger LOG = Log.getLogger(Metadata.class);
    public static String extractMetadata1(String wavFilePath) {
        try {
            // Leer el archivo WAV usando JAudioTagger
            File file = new File(wavFilePath);
            AudioFile audioFile = AudioFileIO.read(file);

            // Obtener los metadatos
            String title = audioFile.getTag().getFirst(FieldKey.TITLE);


            // Crear una cadena de texto con los metadatos
            StringBuilder metadataText = new StringBuilder();
            metadataText.append("Title: ").append(title);


            // Retornar los metadatos como una cadena de texto
            return metadataText.toString();
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error("Error:: No se pudo leer metadata");
            return null;
        }
    }

}
