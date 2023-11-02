package ru.otus.daniil.server;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainApplication {
    public static final int PORT = 8189;
    public static int threadNum = 3;
    // + К домашнему задания:
    // Добавить логирование!!!


    public static void main(String[] args) {

        Map<String, MyWebApplication> router = new HashMap<>();
        router.put("/calculator", new CalculatorWebApplication());
        router.put("/greetings", new GreetingsWebApplication());
        router.put("/countdown", new TimerWebApplication());
        Logger logger = LogManager.getLogger(MainApplication.class.getName());
        ExecutorService eServise = Executors.newFixedThreadPool(threadNum);


        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            logger.info("Main: Сервер запущен, порт: " + PORT);

            while (true) {
                try  {
                    Socket socket = serverSocket.accept();
                    Handler handler = new Handler(socket, router, logger);
                    eServise.execute(handler::run);

                } catch (IOException e) {
                    logger.error("Main: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            logger.error("Main: " + e.getMessage());
        }

    }
}
