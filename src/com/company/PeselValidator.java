package com.company;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PeselValidator {

    public boolean validatePeselStructure(String pesel) {
        Pattern pattern = Pattern.compile("[0-9]{11}");
        Matcher matcher = pattern.matcher(pesel);
        return matcher.matches();
    }

    public boolean validatePeselBuild(String pesel) {
        int sumaKontrolna = 0;
        char[] array = pesel.toCharArray();
        /* //ze znaku zmiana na string a ze stringa na inta
        sumaKontrolna += 9 * (array[0]-48); odejmowanie liczby z [0] - 48 z asci
        */
        sumaKontrolna += 9 * Integer.parseInt(String.valueOf(array[0]));
        sumaKontrolna += 7 * Integer.parseInt(String.valueOf(array[1]));
        sumaKontrolna += 3 * Integer.parseInt(String.valueOf(array[2]));
        sumaKontrolna += 1 * Integer.parseInt(String.valueOf(array[3]));
        sumaKontrolna += 9 * Integer.parseInt(String.valueOf(array[4]));
        sumaKontrolna += 7 * Integer.parseInt(String.valueOf(array[5]));
        sumaKontrolna += 3 * Integer.parseInt(String.valueOf(array[6]));
        sumaKontrolna += 1 * Integer.parseInt(String.valueOf(array[7]));
        sumaKontrolna += 9 * Integer.parseInt(String.valueOf(array[8]));
        sumaKontrolna += 7 * Integer.parseInt(String.valueOf(array[9]));



        return sumaKontrolna % 10 != Integer.parseInt(String.valueOf(array[10]));

    }
}
