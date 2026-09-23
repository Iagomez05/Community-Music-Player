package org.example.pruebafx;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Log {
    private Log() {
    }

    public static Logger getLogger(Class<?> type) {
        return LogManager.getLogger(type);
    }
}
