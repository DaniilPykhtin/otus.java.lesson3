package ru.otus.daniil.server;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class TimerWebApplication implements MyWebApplication {
    private String name;
    private int secondsToWait = 0;

    public TimerWebApplication() {
        this.name = "Timer Web Application";
    }
    public TimerWebApplication(int secondsToWait) {
        this.name = "Timer Web Application";
        this.secondsToWait = secondsToWait;
    }


    @Override
    public void execute(Request request, OutputStream output) throws IOException, InterruptedException {
        String username = request.getParam("username");

        if (request.getUri().contains("/sec")) {
            secondsToWait = Integer.parseInt(request.getParam("number"));
        } else if (request.getUri().contains("/min")) {
            secondsToWait = Integer.parseInt(request.getParam("number")) * 60;
        }

        output.write(("HTTP/1.1 200 OK\r\nContent-Type: text/html\r\n\r\n<html><body><h1>" + name + "</h1><h2>Timer is set on " + secondsToWait + "second(s)</h2></body></html>").getBytes(StandardCharsets.UTF_8));
        output.flush();
        Thread.sleep(1000L * secondsToWait);
        output.write(("<html><body><h1> done </h1></body></html>").getBytes(StandardCharsets.UTF_8));
        output.flush();
    }
}