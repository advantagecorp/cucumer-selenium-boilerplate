package com.sample;

import java.io.IOException;
import io.helidon.config.Config;
import io.helidon.webserver.Routing;
import io.helidon.webserver.WebServerConfig;
import io.helidon.webserver.WebServer;

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

    /**
     * Start the server.
     * @return the created {@link WebServer} instance
     * @throws IOException if there are problems reading logging properties
     */
    static WebServer startServer() throws IOException {

        // By default this will pick up application.yaml from the classpath
        Config config = Config.create();

        // Get webserver config from the "server" section of application.yaml
        WebServerConfig serverConfig =
                WebServerConfig.create(config.get("server"));

        WebServer server = WebServer.create(serverConfig);
        // Try to start the server. If successful, print some info and arrange to
        // print a message at shutdown. If unsuccessful, print the exception.


        // Server threads are not daemon. No need to block. Just react.
        return server;
    }

    /**
     * Creates new {@link Routing}.
     *
     * @return configured routing
     * @param config configuration of this server
     */
    private static Routing createRouting(Config config) {

        return new Routing() {
            @Override
            public Class<? extends Routing> routingType() {
                return Routing.super.routingType();
            }
        };
    }
}