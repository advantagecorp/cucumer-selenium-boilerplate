package com.sample;

import java.io.IOException;

/**
 * The application main class.
 */
public final class Main {

    /**
     * Cannot be instantiated.
     */
    private Main() { }

    /**
     * Application main entry point.
     * @param args command line arguments.
     * @throws IOException if there are problems reading logging properties
     */
    public static void main(final String[] args) throws IOException {
        startServer();
    }

    static String startServer() throws IOException {

        // By default this will pick up application.yaml from the classpath
        return "TEST";
    }

}