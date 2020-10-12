package com.company;

import java.util.Scanner;

public class KeyboardReader {

    public String lineReader() {
        return new Scanner(System.in).nextLine();
    }

    public int intReader() {
        return new Scanner(System.in).nextInt();
    }
}
