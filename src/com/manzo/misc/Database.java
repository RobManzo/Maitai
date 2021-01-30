package com.manzo.misc;

import com.manzo.entities.Prenotazione;
import com.manzo.entities.Utente;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.*;

/**
 * Questa classe modella la connessione con il database. Gestisce tutti i metodi che
 * interagiscono con il database, consentendo di effettuare interrogazioni per inserire,
 * cancellare, aggiornare o ritornare i dati delle tabelle.
 * @author Manzo Roberto
 */
public class Database {
    /**
     * Dichiarazione delle variabili necessarie a gestire la connessione con il database e
     * l'esecuzione delle query.
     */
    private static Context context = null;
    private static DataSource dataSource = null;


    /**
     * Inizializza il context ed il data source per la comunicazione con il database.
     */
    static {
        try {
            context = new InitialContext();
            dataSource = (DataSource) context.lookup("java:comp/env/jdbc/manzo");
        }
        catch (NamingException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param email
     * @return
     * @throws SQLException
     */
    public static Utente takeUser(String email) throws SQLException{
        String query1 = "SELECT * FROM manzo.utenti AS U WHERE U.email=?";
        try(Connection connection=dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(query1)) {

            statement.setString(1, email);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return new Utente(result.getInt("idUtente"), result.getString("email"), result.getString("pass"), result.getString("nome"), result.getString("cognome"),result.getString("telefono"), result.getString("codFisc"), result.getString("dataNasc"), result.getString("ruolo"));    //Attenzione a sta roba AO
            } else {
                return null;
            }
        }

    }

    /**
     * Inserisce all'interno del database i dati del cliente che effettua la registrazione
     * @param nome
     * @param cognome
     * @param email
     * @param telefono
     * @param dataNasc
     * @param password
     * @throws SQLException
     */
    public static void userSignIn(String nome, String cognome, String email, String codFisc, String telefono, LocalDate dataNasc, String password, String indirizzo, String provincia) throws SQLException{
        String query1 = "INSERT INTO manzo.utenti (email, pass, nome, cognome, codFisc, telefono, dataNasc, ruolo, indirizzo, provincia) " +
                "VALUES (?, SHA2(?, 256), ?, ?, ?, ?, ?, ?, ?, ?)";
        try(Connection connection=dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(query1)) {
            statement.setString(1, email);
            statement.setString(2, password);
            statement.setString(3, nome);
            statement.setString(4, cognome);
            statement.setString(5, codFisc);
            statement.setString(6, telefono);
            statement.setDate(7, Date.valueOf(dataNasc));
            statement.setString(8, "cliente");
            statement.setString(9, indirizzo);
            statement.setString(10, provincia);
            statement.executeUpdate();
        }
    }

    /**
     * Ritorna TRUE se esiste un account con la stessa email, viceversa FALSE
     * @param email
     * @return
     * @throws SQLException
     */

    public static boolean checkExist(String email, String codfisc) throws SQLException{
        String query = "SELECT U.email FROM manzo.utenti AS U WHERE u.email=? OR U.codFisc=?";
        boolean ris=false;
        try(Connection connection=dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, email);
            statement.setString(2, codfisc);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                ris = true;
            }
            result.close();
            return ris;
        }
    }

    /**
     * Metodo che genera una stringa di 10 caratteri con almeno un carattare minuscolo, uno maiuscolo e un numero
     * @param length
     * @return
     */
    public static String generateRandomPassword(int length) {
        String character = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        String password="";
        Random random = new Random();
        String regex = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}";

        while (!password.matches(regex)){
            password = "";
            for (int i = 0; i < length; i++) {
                password+=character.charAt(random.nextInt(character.length()));
            }
        }
        return password;
    }

    /**
     * Metodo per la reimpostazione della password
     * @param email
     * @return
     * @throws SQLException
     */
    public static String resetPassword(String email) throws SQLException{
        String newpwd=generateRandomPassword(8);
        String query = "UPDATE manzo.utenti SET password=SHA2(?, 256) WHERE email=? ";
        try(Connection connection=dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, newpwd);
            statement.setString(2, email);
            int result = statement.executeUpdate();
            if (result > 0) {
                return  newpwd;
            }else{
                return null;
            }
        }

    }

    public static List<Integer> getPosti(LocalDate thisday, int timeslot) throws SQLException {
        String query = "SELECT * FROM manzo.prenotazioni AS P WHERE P.dataPrenotazione=? AND P.fasciaOraria=?";
        try(Connection connection=dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
            List<Integer> pren = new ArrayList<Integer>();
            statement.setDate(1, Date.valueOf(thisday));
            statement.setInt(2, timeslot);
            ResultSet result = statement.executeQuery();
            while(result.next()){
               for(String postazione : result.getString("idPostazione").split(",")){
                   pren.add(Integer.parseInt(postazione));
               }
            }
            return pren;
        }
    }

    /**
     * Metodo per ottenere la prenotazione specificata
     * @param u
     * @return
     * @throws SQLException
     */
    public static Prenotazione getPrenotazione(Utente u, int id) throws SQLException {
        String query = "SELECT * FROM manzo.prenotazioni AS P WHERE P.Utenti_idUtente=? AND P.idPrenotazione=?";
        try(Connection connection=dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
            Prenotazione pren;
            statement.setInt(1, u.getIdUtente());
            statement.setInt(2, id);
            ResultSet result = statement.executeQuery();
            if(result.next()){
                LocalTime enter;
                LocalTime exit;
                result.getTime("oraIngresso");
                if(result.wasNull()){
                    enter = null;
                }else enter = result.getTime("oraIngresso").toLocalTime();
                result.getTime("oraUscita");
                if (result.wasNull()){
                    exit = null;
                } else exit = result.getTime("oraUscita").toLocalTime();
                pren = new Prenotazione(result.getInt("idPrenotazione"), LocalDate.parse(result.getDate("dataEsecuzione").toString()), LocalDate.parse(result.getDate("dataPrenotazione").toString()), result.getString("idPostazione"), enter, exit, result.getInt("fasciaOraria"), result.getDouble("price"));
                return pren;
            } else return null;
        }
    }

    /**
     * Metodo per ottenere la lista delle prenotazioni riguardo un utente
     * @param u
     * @return
     * @throws SQLException
     */
    public static List<Prenotazione> getPrenotazioni(Utente u) throws SQLException {
        String query = "SELECT * FROM manzo.prenotazioni AS P WHERE P.Utenti_idUtente=? ORDER BY P.dataPrenotazione DESC";
        try(Connection connection=dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
            List<Prenotazione> pren = new ArrayList<>();
            statement.setInt(1, u.getIdUtente());
            ResultSet result = statement.executeQuery();
            while(result.next()){
                LocalTime enter;
                LocalTime exit;
                result.getTime("oraIngresso");
                if(result.wasNull()){
                    enter = null;
                }else enter = result.getTime("oraIngresso").toLocalTime();
                result.getTime("oraUscita");
                if (result.wasNull()){
                    exit = null;
                } else exit = result.getTime("oraUscita").toLocalTime();
                    pren.add(new Prenotazione(result.getInt("idPrenotazione"), LocalDate.parse(result.getDate("dataEsecuzione").toString()), LocalDate.parse(result.getDate("dataPrenotazione").toString()), result.getString("idPostazione"), enter, exit, result.getInt("fasciaOraria"), result.getDouble("price")));
            } return pren;
        }
    }

    /**
     * Metodo per l'inserimento di una nuova prenotazione nel DB
     * @param u
     * @return
     * @throws SQLException
     */
    public static boolean insertPrenotazione(Utente u, int ts, LocalDate datapren, String pos, Double prezzo) throws SQLException {
        String query = "INSERT INTO manzo.prenotazioni (dataPrenotazione, idPostazione, fasciaOraria, Utenti_idUtente, price, dataEsecuzione) VALUES (?,?,?,?,?)";
        try(Connection connection=dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            statement.setDate(1, Date.valueOf(datapren));
            statement.setString(2, pos);
            statement.setInt(3, ts);
            statement.setInt(4, u.getIdUtente());
            statement.setDouble(5, prezzo);
            statement.setDate(6, Date.valueOf(LocalDate.now()));
            return statement.executeUpdate() > 0;
        }
    }

    /**
     * Metodo per eliminare la prenotazione specificata
     * @param u
     * @return
     * @throws SQLException
     */
    public static boolean deletePrenotazione(Utente u, int id) throws SQLException {
        String query = "DELETE FROM manzo.prenotazioni AS P WHERE P.Utenti_idUtente=? AND P.idPrenotazione=?";
        try(Connection connection=dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            statement.setInt(1, u.getIdUtente());
            statement.setInt(2, id);
            return statement.executeUpdate() > 0;
        }
    }



    /**
     * Metodo per il settaggio dell'orario di accesso alla struttura
     * @param thisday
     * @param userId
     * @param ts
     * @return
     * @throws SQLException
     */
    public static boolean setEntry(LocalDate thisday, int userId, int ts, LocalTime thistime) throws SQLException {
        String query = "SELECT * FROM manzo.prenotazioni AS P WHERE P.dataPrenotazione=? AND P.Utenti_idUtente=? AND (P.fasciaOraria=? OR P.fasciaOraria=0)";
        try(Connection connection=dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            statement.setDate(1, Date.valueOf(thisday));
            statement.setInt(2, userId);
            statement.setInt(3, ts);
            ResultSet result = statement.executeQuery();
            if(result.next()){
                result.getTime("oraIngresso");
                if(result.wasNull()){
                    result.updateTime("oraIngresso", Time.valueOf(thistime));
                    result.updateRow();
                    System.out.println(result.getTime("oraIngresso"));
                    return true;
                }else return false;
            } else return false;
        }
    }

    /**
     * Metodo per verificare se l'utente è all'interno della struttura
     * @param thisday
     * @param userId
     * @return
     * @throws SQLException
     */
    public static boolean getEntry(LocalDate thisday, int userId) throws SQLException {
        String query = "SELECT * FROM manzo.prenotazioni AS P WHERE P.dataPrenotazione=? AND P.Utenti_idUtente=?";
        try(Connection connection=dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setDate(1, Date.valueOf(thisday));
            statement.setInt(2, userId);
            ResultSet result = statement.executeQuery();
            if(result.next()){
                result.getTime("oraIngresso");
                if(!result.wasNull()){
                    result.getTime("oraUscita");
                    if(result.wasNull()){
                        return true;
                    } else return false;
                } else return false;
            } else return false;
        }
    }

    public static boolean setExit(LocalDate thisday, int userId, LocalTime thistime) throws SQLException {
        String query = "SELECT * FROM manzo.prenotazioni AS P WHERE P.dataPrenotazione=? AND P.Utenti_idUtente=?";
        try(Connection connection=dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            statement.setDate(1, Date.valueOf(thisday));
            statement.setInt(2, userId);
            ResultSet result = statement.executeQuery();
            if(result.next()){
                result.getTime("oraIngresso");
                if(!result.wasNull()){
                    result.getTime("oraUscita");
                    if(result.wasNull()){
                        result.updateTime("oraUscita", Time.valueOf(thistime));
                        result.updateRow();
                        return true;
                    } else return false;
                }else return false;
            } else return false;
        }
    }

}
