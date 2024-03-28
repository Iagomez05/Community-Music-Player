package org.example.pruebafx;

import org.apache.log4j.Logger;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.tag.FieldKey;

import java.io.File;

public class Metadata {

    private static final Logger LOG = Log.getLogger(Metadata.class);
    public static SongData extractMetadata(String wavFilePath) {
        MetadataList metadataList = new MetadataList();
        try {
            // Leer el archivo WAV usando JAudioTagger
            File file = new File(wavFilePath);
            AudioFile audioFile = AudioFileIO.read(file);

            // Obtener los metadatos
            String artist = audioFile.getTag().getFirst(FieldKey.ARTIST);
            String title = audioFile.getTag().getFirst(FieldKey.TITLE);
            String album = audioFile.getTag().getFirst(FieldKey.ALBUM);
            String genre = audioFile.getTag().getFirst(FieldKey.GENRE);

            metadataList.insert(artist);
            metadataList.insert(title);
            metadataList.insert(album);
            metadataList.insert(genre);

            SongData songData1 = new SongData(metadataList.head.data,
                    metadataList.head.next.data,
                    metadataList.head.next.next.data,
                    metadataList.head.next.next.next.data);

            return songData1;
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error("Error:: No se pudo leer metadata");
            return null;
        }
    }
}
