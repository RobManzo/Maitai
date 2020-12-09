package com.manzo.entities;

public class Utente {
    private int idUtente;
    private String email;
    private String pass;
    private String nome;
    private String cognome;
    private String CodFisc;
    private String telefono;
    private String dataNasc;
    private int attivo;
    private String ruolo;

    public int getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(int idUtente) {
        this.idUtente = idUtente;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getCodFisc() {
        return CodFisc;
    }

    public void setCodFisc(String codFisc) {
        CodFisc = codFisc;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(String dataNasc) {
        this.dataNasc = dataNasc;
    }

    public int getAttivo() {
        return attivo;
    }

    public void setAttivo(int attivo) {
        this.attivo = attivo;
    }

    public String getRuolo() { return ruolo; }

    public void setRuolo(String ruolo) { this.ruolo = ruolo; }


    public Utente(int idUtente, String email, String pass, String nome, String cognome, String codFisc, String telefono, String dataNasc, String ruolo) {
        this.idUtente = idUtente;
        this.email = email;
        this.pass = pass;
        this.nome = nome;
        this.cognome = cognome;
        CodFisc = codFisc;
        this.telefono = telefono;
        this.dataNasc = dataNasc;
        this.ruolo = ruolo;
    }


}
