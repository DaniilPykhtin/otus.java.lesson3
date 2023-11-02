package ru.otus.daniil.server;

import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class Handler implements Runnable {
    Socket socket;
    Map<String, MyWebApplication> router;
    Logger logger;
    String threadName = Thread.currentThread().getName();

    public Handler(Socket socket, Map<String, MyWebApplication> router, Logger logger) {
        this.socket = socket;
        this.router = router;
        this.logger = logger;
    }

    @Override
    public void run() {

        try {
            logger.info(threadName + ": Клиент подключился");
            byte[] buffer = new byte[2048];
            int n = socket.getInputStream().read(buffer);
            String rawRequest = new String(buffer, 0, n);
            Request request = new Request(rawRequest);
            logger.info(threadName + ": Получен запрос:");
            logger.info(threadName + ": " + request.print());
            boolean executed = false;
            for (Map.Entry<String, MyWebApplication> e : router.entrySet()) {
                if (request.getUri().startsWith(e.getKey())) {
                    e.getValue().execute(request, socket.getOutputStream());
                    executed = true;

                    break;
                }
            }
            if (!executed) {
                socket.getOutputStream().write(("HTTP/1.1 200 OK\r\nContent-Type: text/html\r\n\r\n<html><body><h1>Unknown application</h1></body></html>").getBytes(StandardCharsets.UTF_8));
            }
            socket.close();

        } catch (IOException | InterruptedException e) {
            logger.error(threadName + ": " + e.getMessage());
        }
    }
}
