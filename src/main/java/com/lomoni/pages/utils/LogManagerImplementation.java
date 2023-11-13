package com.lomoni.pages.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

/*
 * Author : Braine Lomoni 168864 21/10/2023
 * Functionality :
 * - Initialize a logger object using Log4j library
 * - Provides methods to log messages at different levels of severity.
 * - Debug
 * - Info
 * - Warn
 * - Error
 * - Fatal
 * - Trace
 */
public abstract class LogManagerImplementation {
    private static Logger logger;

    public static void Log(String level, String info, Exception exception, String className){
        try {
            if (!className.isEmpty()) {
                logger = LogManager.getLogger(className);
            } else {
                Objects.requireNonNull(className, "className must not be null");
            }
        } catch(Exception e){
            Log("FATAL","className must not be null", e,LogManagerImplementation.class.getName());
        }
        switch(level){
            case "DEBUG":
                logger.debug(info, exception);
                break;
            case "INFO":
                logger.info(info, exception);
                break;
            case "WARN":
                logger.warn(info, exception);
                break;
            case "ERROR":
                logger.error(info, exception);
                break;
            case "FATAL":
                logger.fatal(info, exception);
                break;
            case "TRACE":
                logger.trace(info, exception);
                break;
            default:
                logger.warn("Unrecognized severity level "+level);
                break;
        }
    }
}
