package com.company;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class MenuLekarskie extends JFrame implements ActionListener {
    JMenuBar menu;
    JButton wyloguj, wyjscie, dodaj, wyczysc, wstawDane,wczytaj,wyczysc2;
    JLabel napisDiagnoza, napisPesel,napisZapisDoPliku;
    JTextArea poleDiagnoza, poleSprawdzDane;
    JTextField polePesel;
    JFileChooser zapiszPlik = new JFileChooser();
    JFileChooser wczytajPlik = new JFileChooser();
    private Menu menuMenu;
    private Person person;
    public PeselGetter peselGetter;




    public MenuLekarskie() {

        person = new Person();
        this.peselGetter = new PeselGetter();

        setTitle("Baza danych Lekarza");
        setSize(800, 800);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        menu = new JMenuBar();
        setJMenuBar(menu);

        napisDiagnoza = new JLabel("Wpisz diagnozę: ");
        napisDiagnoza.setBounds(40, 60, 120, 20);
        add(napisDiagnoza);
        poleDiagnoza = new JTextArea();
        poleDiagnoza.setBounds(20, 80, 150, 600);
        poleDiagnoza.setLineWrap(true);
        poleDiagnoza.setWrapStyleWord(true);
        poleDiagnoza.setBorder(new LineBorder(Color.BLACK));
        add(poleDiagnoza);

        napisPesel = new JLabel("Pesel");
        napisPesel.setBounds(60, 10, 50, 20);
        add(napisPesel);
        polePesel = new JTextField();
        polePesel.setBounds(20, 30, 150, 20);
        add(polePesel);

        napisZapisDoPliku = new JLabel("Dane z plik(Możliwość edycji i ponownego zapisu(przycisk Zapisz)):");
        napisZapisDoPliku.setBounds(200, 10, 400, 20);
        add(napisZapisDoPliku);

        poleSprawdzDane = new JTextArea();
        poleSprawdzDane.setBounds(200, 30, 500, 650);
        poleSprawdzDane.setLineWrap(true);
        poleSprawdzDane.setWrapStyleWord(true);
        poleSprawdzDane.setBorder(new LineBorder(Color.BLACK));
        add(poleSprawdzDane);

        dodaj = new JButton("Dodaj");
        dodaj.setBounds(20, 700, 85, 20);
        add(dodaj);
        dodaj.addActionListener(this);

        wyloguj = new JButton("Wyloguj");
        wyloguj.setBounds(580, 720, 85, 20);
        add(wyloguj);
        wyloguj.addActionListener(this);

        wyczysc = new JButton("Wyczysc");
        wyczysc.setBounds(20, 720, 85, 20);
        add(wyczysc);
        wyczysc.addActionListener(this);

        wstawDane = new JButton("Zapisz");
        wstawDane.setBounds(300, 700, 85, 20);
        add(wstawDane);
        wstawDane.addActionListener(this);

        wczytaj = new JButton("Wczytaj");
        wczytaj.setBounds(400,700,85,20);
        add(wczytaj);
        wczytaj.addActionListener(this);

        wyczysc2 = new JButton("Wyczyść");
        wyczysc2.setBounds(500,700,85,20);
        add(wyczysc2);
        wyczysc2.addActionListener(this);

        wyjscie = new JButton("Wyjscie");
        wyjscie.setBounds(680, 720, 85, 20);
        add(wyjscie);
        wyjscie.addActionListener(this);



    }


    public void actionPerformed(ActionEvent e) {

        Object zrodlo = e.getSource();
        String polePesel1 = String.valueOf(this.polePesel.getText());
        String poleDiagnoza1 = String.valueOf(this.poleDiagnoza.getText());
        if (zrodlo == wyjscie) {
            dispose();
        }

        if (zrodlo == wyloguj) {
            if (!wyloguj.isSelected()) {
                dispose();
                menuMenu = new Menu();
                menuMenu.setVisible(true);
            }
        }
        if (zrodlo == wyczysc) {
            polePesel.setText("");
            poleDiagnoza.setText("");
        }

        if (zrodlo == dodaj) {
            try {
                person.setDiagnosis(poleDiagnoza1);
                person.setPesel(polePesel1);
                polePesel1 = peselGetter.fetchPesel(polePesel1);
                person.peselCorrect(polePesel1);
                person.peselWriter(polePesel1);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            JOptionPane.showMessageDialog(null,"Diagnoza dodana pomyślnie");
            polePesel.setText("");
            poleDiagnoza.setText("");
        }

        if(zrodlo == wyczysc2){
            poleSprawdzDane.setText("");
        }


        if (zrodlo == wstawDane) {
            if (zapiszPlik.showSaveDialog(null) == JFileChooser.APPROVE_OPTION)
            {
                File file = zapiszPlik.getSelectedFile();
                try {
                    PrintWriter saveF = new PrintWriter(file);
                    Scanner sText = new Scanner(poleSprawdzDane.getText());
                    while (sText.hasNext()) {
                        saveF.println(sText.nextLine() + "\n");
                    }
                    saveF.close();
                } catch (FileNotFoundException e2) {
                    e2.printStackTrace();
                }
            }
        }

        if(zrodlo == wczytaj){
            int result = wczytajPlik.showOpenDialog(getParent());
            if (result == JFileChooser.APPROVE_OPTION)
            {
                File file = wczytajPlik.getSelectedFile();
                try {
                    Scanner text = new Scanner(file);
                    while (text.hasNext()) {
                        poleSprawdzDane.append(text.nextLine() + "\n");
                    }
                } catch (FileNotFoundException e2) {
                    e2.printStackTrace();
                }
            }
        }
    }
}
