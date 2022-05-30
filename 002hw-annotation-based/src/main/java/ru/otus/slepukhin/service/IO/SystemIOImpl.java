package ru.otus.slepukhin.service.IO;

import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class SystemIOImpl implements IO {

    private final Scanner sc = new Scanner(System.in);

    @Override
    public void write(String out) {
        System.out.println(out + " ");
    }

    @Override
    public String read() {
        return this.sc.nextLine();
    }
}
