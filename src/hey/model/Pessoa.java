package rmiserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Pessoa implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    public String nome, password, trabalho, departamento, telemovel, morada, CCnumber, CCVal;
    public ArrayList<String> Tables = new  ArrayList<String>();

    public Pessoa(String nome, String password, String job, String telemovel, String morada, String CCnumber, String CCVal, String departamento, ArrayList<String> Tables){
        this.nome = nome;
        this.password = password;
        this.trabalho = job;
        this.departamento = departamento;
        this.telemovel = telemovel;
        this.morada = morada;
        this.CCnumber = CCnumber;
        this.CCVal = CCVal;
        this.Tables = Tables;
    }

    public Pessoa() {
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTrabalho() {
        return this.trabalho;
    }

    public void setTrabalho(String trabalho) {
        this.trabalho = trabalho;
    }

    public String getDepartamento() {
        return this.departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getTelemovel() {
        return this.telemovel;
    }

    public void setTelemovel(String telemovel) {
        this.telemovel = telemovel;
    }

    public String getMorada() {
        return this.morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public String getCCnumber() {
        return this.CCnumber;
    }

    public void setCCnumber(String CCnumber) {
        this.CCnumber = CCnumber;
    }

    public String getCCVal() {
        return this.CCVal;
    }

    public void setCCVal(String CCVal) {
        this.CCVal = CCVal;
    }

    public ArrayList<String> getTables() {
        return this.Tables;
    }

    public String toString(){//overriding the toString() method
        return  "USERNAME: "       + this.nome         +
                "\nPASSWORD: "     + this.password     +
                "\nJOB: "          + this.trabalho     +
                "\nDEPARTAMENTO: " + this.departamento +
                "\nTELE: "         + this.telemovel    +
                "\nADRESS: "       + this.morada       +
                "\nCCNUMBER: "     + this.CCnumber     +
                "\nCCVAL: "        + this.CCVal        ;
    }

    public void RegisterPerson(String nome,String telemovel,String morada,String cc,String CCval,String cargo, String departamento, String pass) throws IOException {
        InputStreamReader input = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input);
        Scanner scan = new Scanner(System.in);
        String in;
        int num;
        this.nome = nome;
        this.telemovel = telemovel;
        this.morada = morada;
        this.CCnumber = cc;
        this.CCVal = CCval;
        this.trabalho = cargo;
        this.departamento = departamento;
        this.password = pass;

    }
}
