package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Menu extends JFrame implements ActionListener {

    JMenuBar menu;
    JButton zaloguj, wyjscie, sprawdzDane, stworzPacjetna;
    JLabel napisLogowanie1, napisLogowanie2, imie, nazwiwsko, pesel, opis1, opis2;
    JTextField poleNazwaUzytkownia, poleImie, poleNazwisko, polePesell;
    JPasswordField poleHaslo;
    private final String pop = "admin";
    private final String pop1 = "admin";

    private Person person;
    public PeselGetter peselGetter;
    public PeselValidator peselValidator;
    public PeselExtractor peselExtractor;
    public KeyboardReader keyboardReader;
    private MenuLekarskie menuLekarskie;
    private MenuPacjenta menuPacjenta;
    JFileChooser chooser = new JFileChooser();


    public Menu() {
        this.person = new Person();
        this.peselGetter = new PeselGetter();
        this.peselValidator = new PeselValidator();
        this.peselExtractor = new PeselExtractor();
        this.keyboardReader = new KeyboardReader();

        setTitle("Baza danych");
        setSize(400, 300);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        menu = new JMenuBar();
        setJMenuBar(menu);


        opis1 = new JLabel("Jeśli nie posiadasz karty pacjenta");
        opis1.setBounds(20, 60, 300, 20);
        add(opis1);
        opis2 = new JLabel("wypelnij poniższe 3 pola i kliknij Stworz");
        opis2.setBounds(20, 80, 300, 20);
        add(opis2);
        imie = new JLabel("Imie: ");
        imie.setBounds(20, 100, 80, 20);
        add(imie);
        poleImie = new JTextField();
        poleImie.setBounds(100, 100, 80, 20);
        add(poleImie);
        nazwiwsko = new JLabel("Nazwisko: ");
        nazwiwsko.setBounds(20, 120, 80, 20);
        add(nazwiwsko);
        poleNazwisko = new JTextField();
        poleNazwisko.setBounds(100, 120, 80, 20);
        add(poleNazwisko);
        pesel = new JLabel("Pesel: ");
        pesel.setBounds(20, 140, 80, 20);
        add(pesel);
        polePesell = new JTextField();
        polePesell.setBounds(100, 140, 80, 20);
        add(polePesell);
        stworzPacjetna = new JButton("Stwórz");
        stworzPacjetna.setBounds(200, 140, 90, 20);
        add(stworzPacjetna);
        stworzPacjetna.addActionListener(this);

        zaloguj = new JButton("Zaloguj");
        zaloguj.setBounds(200, 40, 90, 20);
        add(zaloguj);
        zaloguj.addActionListener(this);
        napisLogowanie1 = new JLabel("Nazwa: ");
        napisLogowanie1.setBounds(20, 20, 80, 20);
        add(napisLogowanie1);
        poleNazwaUzytkownia = new JTextField();
        poleNazwaUzytkownia.setBounds(100, 20, 80, 20);
        add(poleNazwaUzytkownia);
        napisLogowanie2 = new JLabel("Haslo: ");
        napisLogowanie2.setBounds(20, 40, 80, 20);
        add(napisLogowanie2);
        poleHaslo = new JPasswordField();
        poleHaslo.setBounds(100, 40, 80, 20);
        add(poleHaslo);

        sprawdzDane = new JButton("Sprawdz");
        sprawdzDane.setBounds(200, 180, 90, 20);
        add(sprawdzDane);
        sprawdzDane.addActionListener(this);


        wyjscie = new JButton("Wyjscie");
        wyjscie.setBounds(200, 220, 90, 20);
        add(wyjscie);
        wyjscie.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {

        Object zrodlo = e.getSource();
        String poleUzytkownik = poleNazwaUzytkownia.getText();
        String poleHaslo = this.poleHaslo.getText();
        String poleImie1 = String.valueOf(this.poleImie.getText());
        String poleNazwisko1 = String.valueOf(this.poleNazwisko.getText());
        String polePesel = String.valueOf(this.polePesell.getText());

        if (zrodlo == stworzPacjetna) {
            person.setName(poleImie1);
            person.setSurname(poleNazwisko1);
            person.setPesel(polePesel);
            String polePesel1 = String.valueOf(polePesel);
            polePesel1 = peselGetter.fetchPesel(polePesel1);
            person.peselCorrect(polePesel);
            person.setGender(person.getGenderToWriteToFile(polePesel1));
            person.setInsurance(true);
            person.setDiagnosis(" ");
            person.setDateBirth(person.getDateBirth(polePesel1));
            try {
                person.genderWriter(polePesel1);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            poleImie.setText("");
            poleNazwisko.setText("");
            polePesell.setText("");
            JOptionPane.showMessageDialog(null, "Karta pacjenta dodana");
            dispose();
            Menu menu2 = new Menu();
            menu2.setVisible(true);
        }

        if (zrodlo == wyjscie) {
            dispose();
        }
        if (zrodlo == zaloguj) {
            if (poleUzytkownik.equals(pop) && poleHaslo.equals(pop1)) {
                dispose();
                menuLekarskie = new MenuLekarskie();
                menuLekarskie.setVisible(true);

            } else {
                JOptionPane.showMessageDialog(this, "Błędne dane logowania");
            }
        }

        if (zrodlo == sprawdzDane) {

            int result = chooser.showOpenDialog(getParent());
            if (result == JFileChooser.APPROVE_OPTION) {
                dispose();
                menuPacjenta = new MenuPacjenta();
                menuPacjenta.setVisible(true);
                menuPacjenta.poleSprawdzDane.setEnabled(false);
                File file = chooser.getSelectedFile();
                try {
                    Scanner text = new Scanner(file);
                    while (text.hasNext()) {
                        menuPacjenta.poleSprawdzDane.append(text.nextLine() + "\n");
                    }
                } catch (FileNotFoundException e2) {
                    e2.printStackTrace();
                }
            }
        }


    }


}
