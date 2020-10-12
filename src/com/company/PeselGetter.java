package com.company;

public class PeselGetter {

    private KeyboardReader keyboardReader;

    public PeselGetter() {
        this.keyboardReader = new KeyboardReader();
    }

    public String fetchPesel(String pesel) {
        /*    String pesel = keyboardReader.lineReader();*/
        return pesel;
    }


}
