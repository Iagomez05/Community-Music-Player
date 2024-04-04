package org.example.pruebafx;

import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.RollingFileAppender;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;

public class Log {
    private static Logger LOG;

    public static Logger getLogger(Class name) {
        try {
            LOG = Logger.getLogger(name);
            String logfile = "log.errores//filelog.";
            Date fecha = new Date();

            SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
            String fechaAc = formato.format(fecha);
            PatternLayout defaultLayout = new PatternLayout("%d{dd-MM-yyyy HH:mm:ss} %-5p %c{1}:%L: %m%n");

            RollingFileAppender rollingFileAppender = new RollingFileAppender();
            rollingFileAppender.setFile(logfile + fechaAc + ".log", true, false, 0);
            rollingFileAppender.setMaxFileSize("10MB");
            rollingFileAppender.setMaxBackupIndex(5);
            rollingFileAppender.setLayout(defaultLayout);

            LOG.removeAllAppenders();
            LOG.addAppender(rollingFileAppender);
            LOG.setAdditivity(true);

        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(Log.class.getName()).log(Level.SEVERE, null, ex);
        }
        return LOG;
    }
}