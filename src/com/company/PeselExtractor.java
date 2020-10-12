package com.company;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PeselExtractor {


    public LocalDate extractBirthDate(String pesel) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(extractPesel(pesel, 4));
        stringBuilder.append(extractPesel(pesel, 5));
        stringBuilder.append("-");
        stringBuilder.append(extractPesel(pesel, 2));
        stringBuilder.append(extractPesel(pesel, 3));
        stringBuilder.append("-");
        stringBuilder.append("19");
        stringBuilder.append(extractPesel(pesel, 0));
        stringBuilder.append(extractPesel(pesel, 1));
        DateTimeFormatter dataTimeFormatter = DateTimeFormatter.ofPattern("d-MM-yyyy");
        LocalDate localDate = LocalDate.parse(stringBuilder.toString(), dataTimeFormatter);
        return localDate;
    }

    public int extractPesel(String pesel, int index) {
        char[] peselDigits = pesel.toCharArray();
        return Integer.valueOf(String.valueOf(peselDigits[index]));//mogło by być -48(ASCI) a tak mamy z chara do stringa ze ztsringa do inta
    }
}
