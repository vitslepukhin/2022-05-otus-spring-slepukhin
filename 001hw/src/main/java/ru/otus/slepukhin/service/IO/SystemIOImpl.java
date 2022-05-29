package ru.otus.slepukhin.service.IO;

import java.util.Scanner;

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
