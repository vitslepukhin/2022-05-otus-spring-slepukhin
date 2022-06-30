package ru.otus.slepukhin.service.IO;

public interface IO {
    void write(String out);
    String read();
    String request(String message);
}
