package com.company;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPacjenta extends JFrame implements ActionListener {

    JMenuBar menu;
    JButton wyjscie, powrot;
    JLabel wyswietlDane;
    JTextArea poleSprawdzDane;
    private Menu menu1;


    public MenuPacjenta() {
        this.menu1 = new Menu();

        setTitle("Menu Pacjenta");
        setSize(800, 800);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        menu = new JMenuBar();
        setJMenuBar(menu);

        wyswietlDane = new JLabel("Wczytane dane(Pacjent nie posiada możliwości zapisu danych): ");
        wyswietlDane.setBounds(20, 20, 700, 20);
        add(wyswietlDane);

        poleSprawdzDane = new JTextArea();
        poleSprawdzDane.setBounds(20, 60, 740, 600);
        poleSprawdzDane.setLineWrap(true);
        poleSprawdzDane.setWrapStyleWord(true);
        poleSprawdzDane.setBorder(new LineBorder(Color.BLACK));
        add(poleSprawdzDane);

        wyjscie = new JButton("Wyjscie");
        wyjscie.setBounds(600, 700, 85, 20);
        add(wyjscie);
        wyjscie.addActionListener(this);

        powrot = new JButton("Powrot");
        powrot.setBounds(500, 700, 85, 20);
        add(powrot);
        powrot.addActionListener(this);


    }

    public void actionPerformed(ActionEvent e) {
        Object zrodlo = e.getSource();
        if (zrodlo == wyjscie) {
            dispose();
        }

        if (zrodlo == powrot) {
            dispose();
            menu1 = new Menu();
            menu1.setVisible(true);
        }
    }
}

