package com.company;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class Person {


    final String MALE = "male";
    final String FEMALE = "female";
    private final String DOT = ".";
    private String name;
    private String surname;
    private String pesel;
    private String dateBirth;
    private String gender;
    private String diagnosis;
    private boolean treatmentStatus;
    private boolean insurance;
    private Date dzis = new Date();

    public PeselGetter peselGetter;
    public PeselValidator peselValidator;
    public PeselExtractor peselExtractor;
    public KeyboardReader keyboardReader;


    public Person() {
        this.peselGetter = new PeselGetter();
        this.peselValidator = new PeselValidator();
        this.peselExtractor = new PeselExtractor();
        this.keyboardReader = new KeyboardReader();
    }

    public void allMethods() throws IOException {
        name = getName();
        surname = getSurname();
        pesel = peselGetter.fetchPesel(pesel);
        peselCorrect(pesel);
        System.out.println(getDateBirth(pesel));
        gender = getGenderToWriteToFile(pesel);
        System.out.println(gender);
        setGenderToFile(gender, true);
        insurance = true;
        treatmentStatus = true;
        diagnosis = "";
        System.out.println(tworzenie());
        peselWriter(pesel);
    }

    public String getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(String dateBirth) {
        this.dateBirth = dateBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public boolean isTreatmentStatus() {
        return treatmentStatus;
    }

    public void setTreatmentStatus(boolean treatmentStatus) {
        this.treatmentStatus = treatmentStatus;
    }

    public boolean isInsurance() {
        return insurance;
    }

    public void setInsurance(boolean insurance) {
        this.insurance = insurance;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getName() {
       /*
        name = keyboardReader.lineReader();
        */
        return name;
    }

    public String getSurname() {
       /*
        surname = keyboardReader.lineReader();
        */
        return surname;
    }

    public String peselCorrect(String pesel) {

        if (!peselValidator.validatePeselStructure(pesel)) {
            JOptionPane.showMessageDialog(null, "Pesel is incorrect! ");
            System.exit(0);
        }

        if (peselValidator.validatePeselBuild(pesel)) {
            JOptionPane.showMessageDialog(null, "Pesel is incorrect! ");
            System.exit(0);
        }
        return pesel;
    }

    public boolean setGenderToFile(String gender, boolean treatmentStatus) throws IOException {
        if (gender.equals(MALE) && treatmentStatus) {
            genderWriter("Men.txt");
            return false;
        } else if (gender.equals(FEMALE) && treatmentStatus)
            genderWriter("Women.txt");
        return true;
    }

    public void genderWriter(String fileName) throws IOException {
        File file = new File(fileName);
        if (!file.exists()) {
            file.createNewFile();
            FileWriter fileWriter = new FileWriter(file, true);
            fileWriter.write(tworzenie() + "\r\n");
            fileWriter.close();
        } else {
            FileWriter fileWriter = new FileWriter(file, true);
            fileWriter.write(tworzenie() + " \r\n");
            fileWriter.close();
        }
    }

    public void peselWriter(String pesel) throws IOException {

        File file = new File(pesel);

        if (!file.exists()) {
            file.createNewFile();
            FileWriter fileWriter = new FileWriter(file, true);
            fileWriter.write(diagnozowanie() + "\r\n");
            fileWriter.close();
        } else {
            FileWriter fileWriter = new FileWriter(file, true);
            fileWriter.write(diagnozowanie() + " \r\n");
            fileWriter.close();
        }
    }

    public String getGenderToWriteToFile(String pesel) {
        if (peselExtractor.extractPesel(pesel, 9) % 2 == 0) {
            gender = FEMALE;
        } else {
            gender = MALE;
        }
        return gender;
    }

    public String getDateBirth(String pesel) {
        dateBirth = peselExtractor.extractBirthDate(pesel).toString();
        return dateBirth;
    }

    public Person(String name, String surname, String pesel, String dateBirth, String gender, String diagnosis, boolean treatmentStatus, boolean insurance) {
        this.name = name;
        this.surname = surname;
        this.pesel = pesel;
        this.dateBirth = dateBirth;
        this.gender = gender;
        this.diagnosis = diagnosis;
        this.treatmentStatus = treatmentStatus;
        this.insurance = insurance;
    }

    public StringBuilder tworzenie() {
        StringBuilder stringBuilder = new StringBuilder();
        return stringBuilder
                .append("Pesel: " + pesel + "\n")
                .append("Imie: " + name + "\n")
                .append("Nazwisko: " + surname + "\n")
                .append("Data urodzenia: " + dateBirth + "\n")
                .append("Plec: " + gender + "\n")
                .append("Status zdrowotnosci: " + treatmentStatus + "\n")
                .append("Ubezpieczenie: " + insurance + "\n")
                .append("Data wizyty: " + dzis + "\n")
                .append("Diagnoza: " + diagnosis + "\n")
                .append(DOT + "\n");
    }

    public StringBuilder diagnozowanie() {
        StringBuilder stringBuilder = new StringBuilder();
        return stringBuilder
                .append("Data wizyty: " + dzis + "\n")
                .append("Diagnoza: " + diagnosis + "\n")
                .append(DOT);
    }

    public String toString() {
        return "Person{" +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", pesel='" + pesel + '\'' +
                ", dateBirth='" + dateBirth + '\'' +
                ", gender='" + gender + '\'' +
                ", diagnosis='" + diagnosis + '\'' +
                ", treatmentStatus=" + treatmentStatus +
                ", insurance=" + insurance +
                '}';
    }

}
