package ru.otus.slepukhin.service.IO;

import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

@Service
public class SystemIOImpl implements IO {
    private final PrintStream output;
    private final Scanner input;

    public SystemIOImpl(PrintStream outputStream, InputStream inputStream) {
        this.output = outputStream;
        this.input = new Scanner(inputStream);
    }

    @Override
    public void write(String out) {
        output.println(out + " ");
    }

    @Override
    public String read() {
        return input.nextLine();
    }

    @Override
    public String request(String message) {
        write(message);
        return read();
    }
}
