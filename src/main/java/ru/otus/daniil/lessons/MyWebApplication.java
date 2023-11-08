package ru.otus.daniil.lessons;

import java.io.IOException;
import java.io.OutputStream;

public interface MyWebApplication {
    void execute(Request request, OutputStream output) throws IOException;
}
