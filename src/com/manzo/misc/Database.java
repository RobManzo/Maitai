package com.manzo.misc;

import com.manzo.entities.Ordine;
import com.manzo.entities.Prenotazione;
import com.manzo.entities.Prodotto;
import com.manzo.entities.Utente;
import javafx.util.Pair;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

/**
 * Classe per la connessione con il Database. Gestisce tutti i metodi che
 * interagiscono con esso, consentendo di effettuare interrogazioni per manipolare i dati al suo interno.
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
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo che data l'email, ottiene l'utente dal DataBase
     * 
     * @param email
     * @return Utente
     * @throws SQLException
     */
    public static Utente takeUser(String email) throws SQLException {
        String query1 = "SELECT * FROM manzo.utenti AS U WHERE U.email=?";
        try (Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(query1)) {
            statement.setString(1, email);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return new Utente(result.getInt("idUtente"), result.getString("email"), result.getString("pass"), result.getString("nome"), result.getString("cognome"), result.getString("codFisc"), result.getString("telefono"), result.getString("dataNasc"), result.getString("indirizzo"), result.getString("ruolo"));    //Attenzione a sta roba AO
            } else {
                return null;
            }
        }

    }

    /**
     * Metodo che dato l'id, ottiene l'utente dal DataBase
     *
     * @param ID
     * @return Utente
     * @throws SQLException
     */
    public static Utente takeUser(int ID) throws SQLException {
        String query1 = "SELECT * FROM manzo.utenti AS U WHERE U.idUtente=?";
        try (Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(query1)) {
            statement.setInt(1, ID);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return new Utente(result.getInt("idUtente"), result.getString("email"), result.getString("pass"), result.getString("nome"), result.getString("cognome"), result.getString("codFisc"), result.getString("telefono"), result.getString("dataNasc"), result.getString("indirizzo"), result.getString("ruolo"));    //Attenzione a sta roba AO
            } else {
                return null;
            }
        }

    }

    /**
     * Metodo che in base al ruolo del richiedente, ottiene la lista degli utenti
     *
     * @param role
     * @return List<Utente>
     * @throws SQLException
     */
    public static List<Utente> getUsers(String role) throws SQLException{
        String query1;
        if (role.equals("admin")){
            query1 = "SELECT * FROM manzo.utenti";
            try (Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(query1)) {
                ResultSet result = statement.executeQuery();
                ArrayList<Utente> users = new ArrayList<>();
                while (result.next()) {
                    users.add(new Utente(result.getInt("idUtente"), result.getString("email"), result.getString("pass"), result.getString("nome"), result.getString("cognome"), result.getString("codFisc"), result.getString("telefono"), result.getString("dataNasc"), result.getString("indirizzo"), result.getString("ruolo")));
                } return users;
            }
        }
        else{
            query1 = "SELECT * FROM manzo.utenti AS U WHERE U.ruolo=?";
            try (Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(query1)) {
                statement.setString(1, "cliente");
                ResultSet result = statement.executeQuery();
                ArrayList<Utente> users = new ArrayList<>();
                while (result.next()) {
                    users.add(new Utente(result.getInt("idUtente"), result.getString("email"), result.getString("pass"), result.getString("nome"), result.getString("cognome"), result.getString("codFisc"), result.getString("telefono"), result.getString("dataNasc"), result.getString("indirizzo"), result.getString("ruolo")));
                } return users;
            }
        }


    }

    /**
     * Metodo che dato l'id, elimina l'utente
     *
     * @param id
     * @return boolean
     * @throws SQLException
     */
    public static boolean deleteUser(int id) throws SQLException {
        String query = "DELETE FROM manzo.utenti AS U WHERE U.idUtente=?";
        try (Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        }
    }

    /**
     * Inserisce all'interno del database i dati del cliente che effettua la registrazione
     *
     * @param nome
     * @param cognome
     * @param email
     * @param telefono
     * @param dataNasc
     * @param password
     * @throws SQLException
     */
    public static void userSignIn(String nome, String cognome, String email, String codFisc, String telefono, LocalDate dataNasc, String password, String indirizzo, String provincia) throws SQLException {
        String query1 = "INSERT INTO manzo.utenti (email, pass, nome, cognome, codFisc, telefono, dataNasc, ruolo, indirizzo, provincia) " +
                "VALUES (?, SHA2(?, 256), ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(query1)) {
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
     * Inserisce all'interno del database i dati del cliente che effettua la registrazione (ADMIN)
     *
     * @param nome
     * @param cognome
     * @param email
     * @param telefono
     * @param dataNasc
     * @param password
     * @param ruolo
     * @param codFisc
     * @param indirizzo
     * @param provincia
     * @throws SQLException
     */
    public static void userSignIn(String nome, String cognome, String email, String codFisc, String telefono, LocalDate dataNasc, String password, String indirizzo, String provincia, String ruolo) throws SQLException {
        String query1 = "INSERT INTO manzo.utenti (email, pass, nome, cognome, codFisc, telefono, dataNasc, ruolo, indirizzo, provincia) " +
                "VALUES (?, SHA2(?, 256), ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(query1)) {
            statement.setString(1, email);
            statement.setString(2, password);
            statement.setString(3, nome);
            statement.setString(4, cognome);
            statement.setString(5, codFisc);
            statement.setString(6, telefono);
            statement.setDate(7, Date.valueOf(dataNasc));
            statement.setString(8, ruolo);
            statement.setString(9, indirizzo);
            statement.setString(10, provincia);
            statement.executeUpdate();
        }
    }

    /**
     * Ritorna TRUE se esiste un account con la stessa email, viceversa FALSE
     *
     * @param email
     * @param codfisc
     * @return boolean
     * @throws SQLException
     */
    public static boolean checkExist(String email, String codfisc) throws SQLException {
        String query = "SELECT U.email FROM manzo.utenti AS U WHERE u.email=? OR U.codFisc=?";
        boolean ris = false;
        try (Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
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
     * Metodo che genera una stringa di 8 caratteri con almeno un carattare minuscolo, uno maiuscolo e un numero
     *
     * @param length
     * @return String
     */
    private static String generateRandomPassword(int length) {
        String character = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        String password = "";
        Random random = new Random();
        String regex = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}";

        while (!password.matches(regex)) {
            password = "";
            for (int i = 0; i < length; i++) {
                password += character.charAt(random.nextInt(character.length()));
            }
        }
        return password;
    }


    /**
     * Metodo per la reimpostazione della password
     *
     * @param email
     * @param codfisc
     * @return String
     * @throws SQLException
     */
    public static String resetPassword(String email, String codfisc) throws SQLException {
        if(checkExist(email, codfisc)){
            String newpwd = generateRandomPassword(8);
            String query = "UPDATE manzo.utenti SET pass=SHA2(?, 256) WHERE email=? ";
            try (Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, newpwd);
                statement.setString(2, email);
                if(statement.executeUpdate()>0){
                    return newpwd;
                } else return null;
             }
        } else return null;
    }

    /**
     * Metodo per il cambio della password
     *
     * @param id
     * @param newpsw
     * @return boolean
     * @throws SQLException
     */
    public static boolean changePassword(int id, String newpsw) throws SQLException {
        String query = "UPDATE manzo.utenti SET pass=SHA2(?, 256) WHERE idUtente=? ";
        try (Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, newpsw);
            statement.setInt(2, id);
            return statement.executeUpdate() > 0;
        }
    }

    /**
     * Metodo per il cambio del numero di telefono
     *
     * @param id
     * @param newtel
     * @return boolean
     * @throws SQLException
     */
    public static boolean changeTel(int id, String newtel) throws SQLException {
        String query = "UPDATE manzo.utenti SET telefono=? WHERE idUtente=? ";
        try (Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, newtel);
            statement.setInt(2, id);
            return statement.executeUpdate() > 0;
        }
    }

    /**
     * Metodo per il cambio del domicilio
     *
     * @param id
     * @param newaddr
     * @return boolean
     * @throws SQLException
     */
    public static boolean changeAddr(int id, String newaddr) throws SQLException {
        String query = "UPDATE manzo.utenti SET indirizzo=? WHERE idUtente=? ";
        try (Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, newaddr);
            statement.setInt(2, id);
            return statement.executeUpdate() > 0;
        }
    }

    /**
     * Metodo per ottenere i posti occupati per ogni prenotazione di un determinato giorno
     * @param thisday
     * @param timeslot
     * @return List<Integer>
     * @throws SQLException
     */
    public static List<Integer> getPosti(LocalDate thisday, int timeslot) throws SQLException {
        String query = "SELECT * FROM manzo.prenotazioni AS P WHERE P.dataPrenotazione=? AND P.fasciaOraria=?";
        try (Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
            List<Integer> pren = new ArrayList<>();
            statement.setDate(1, Date.valueOf(thisday));
            statement.setInt(2, timeslot);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                for (String postazione : result.getString("idPostazione").split(",")) {
                    pren.add(Integer.parseInt(postazione));
                }
            }
            return pren;
        }
    }

    /**
     * Metodo per ottenere la prenotazione specificata
     *
     * @param u
     * @param id
     * @return Prenotazione
     * @throws SQLException
     */
    public static Prenotazione getPrenotazione(Utente u, int id) throws SQLException {
        String query = "SELECT * FROM manzo.prenotazioni AS P WHERE P.Utenti_idUtente=? AND P.idPrenotazione=?";
        try (Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
            Prenotazione pren;
            statement.setInt(1, u.getIdUtente());
            statement.setInt(2, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                LocalTime enter;
                LocalTime exit;
                result.getTime("oraIngresso");
                if (result.wasNull()) {
                    enter = null;
                } else enter = result.getTime("oraIngresso").toLocalTime();
                result.getTime("oraUscita");
                if (result.wasNull()) {
                    exit = null;
                } else exit = result.getTime("oraUscita").toLocalTime();
                pren = new Prenotazione(result.getInt("idPrenotazione"), LocalDate.parse(result.getDate("dataEsecuzione").toString()), LocalDate.parse(result.getDate("dataPrenotazione").toString()), result.getString("idPostazione"), enter, exit, result.getInt("fasciaOraria"), result.getDouble("price"));
                return pren;
            } else return null;
        }
    }

    /**
     * Metodo per ottenere la prenotazione specificata (Staff)
     *
     * @param id
     * @return Prenotazione
     * @throws SQLException
     */
    public static Prenotazione getPrenotazione(int id) throws SQLException {
        String query = "SELECT * FROM manzo.prenotazioni AS P WHERE P.idPrenotazione=?";
        try (Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
            Prenotazione pren;
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                LocalTime enter;
                LocalTime exit;
                result.getTime("oraIngresso");
                if (result.wasNull()) {
                    enter = null;
                } else enter = result.getTime("oraIngresso").toLocalTime();
                result.getTime("oraUscita");
                if (result.wasNull()) {
                    exit = null;
                } else exit = result.getTime("oraUscita").toLocalTime();
                pren = new Prenotazione(result.getInt("idPrenotazione"), LocalDate.parse(result.getDate("dataEsecuzione").toString()), LocalDate.parse(result.getDate("dataPrenotazione").toString()), result.getString("idPostazione"), enter, exit, result.getInt("fasciaOraria"), result.getDouble("price"));
                return pren;
            } else return null;
        }
    }

    /**
     * Metodo per ottenere l'utente che ha effettuato una determinata prenotazione
     * @param id
     * @return int
     * @throws SQLException
     */
    public static int userByPrenotazione(int id) throws SQLException{
        String query = "SELECT * FROM manzo.prenotazioni AS P JOIN utenti AS U ON P.utenti_idUtente = U.idUtente WHERE P.idPrenotazione=?";
        try (Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) return result.getInt("idUtente");
            else return 0;
        }
    }



    /**
     * Metodo per ottenere la lista delle prenotazioni riguardo un utente
     *
     * @param u
     * @return List<Prenotazione>
     * @throws SQLException
     */
    public static List<Prenotazione> getPrenotazioni(Utente u) throws SQLException {
        String query = "SELECT * FROM manzo.prenotazioni AS P WHERE P.Utenti_idUtente=? ORDER BY P.dataPrenotazione DESC";
        try (Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
            List<Prenotazione> pren = new ArrayList<>();
            statement.setInt(1, u.getIdUtente());
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                LocalTime enter;
                LocalTime exit;
                result.getTime("oraIngresso");
                if (result.wasNull()) {
                    enter = null;
                } else enter = result.getTime("oraIngresso").toLocalTime();
                result.getTime("oraUscita");
                if (result.wasNull()) {
                    exit = null;
                } else exit = result.getTime("oraUscita").toLocalTime();
                pren.add(new Prenotazione(result.getInt("idPrenotazione"), LocalDate.parse(result.getDate("dataEsecuzione").toString()), LocalDate.parse(result.getDate("dataPrenotazione").toString()), result.getString("idPostazione"), enter, exit, result.getInt("fasciaOraria"), result.getDouble("price")));
            }
            return pren;
        }
    }

    /**
     * Metodo per ottenere la lista delle prenotazioni (Odierne o Complessive)
     *
     * @param type
     * @return List<Prenotazione>
     * @throws SQLException
     */
    public static List<Prenotazione> getPrenotazioni(boolean type) throws SQLException {
        if(type){
            String query = "SELECT * FROM manzo.prenotazioni AS P ORDER BY P.idPrenotazione DESC";
            try (Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
                List<Prenotazione> pren = new ArrayList<>();
                ResultSet result = statement.executeQuery();
                while (result.next()) {
                    LocalTime enter;
                    LocalTime exit;
                    result.getTime("oraIngresso");
                    if (result.wasNull()) {
                        enter = null;
                    } else enter = result.getTime("oraIngresso").toLocalTime();
                    result.getTime("oraUscita");
                    if (result.wasNull()) {
                        exit = null;
                    } else exit = result.getTime("oraUscita").toLocalTime();
                    pren.add(new Prenotazione(result.getInt("idPrenotazione"), LocalDate.parse(result.getDate("dataEsecuzione").toString()), LocalDate.parse(result.getDate("dataPrenotazione").toString()), result.getString("idPostazione"), enter, exit, result.getInt("fasciaOraria"), result.getDouble("price")));
                }
                return pren;
            }
        }else {
            String query = "SELECT * FROM manzo.prenotazioni AS P WHERE P.dataPrenotazione=? ORDER BY P.idPrenotazione DESC";
            try (Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
                List<Prenotazione> pren = new ArrayList<>();
                statement.setDate(1, Date.valueOf(LocalDate.now()));
                ResultSet result = statement.executeQuery();
                while (result.next()) {
                    LocalTime enter;
                    LocalTime exit;
                    result.getTime("oraIngresso");
                    if (result.wasNull()) {
                        enter = null;
                    } else enter = result.getTime("oraIngresso").toLocalTime();
                    result.getTime("oraUscita");
                    if (result.wasNull()) {
                        exit = null;
                    } else exit = result.getTime("oraUscita").toLocalTime();
                    pren.add(new Prenotazione(result.getInt("idPrenotazione"), LocalDate.parse(result.getDate("dataEsecuzione").toString()), LocalDate.parse(result.getDate("dataPrenotazione").toString()), result.getString("idPostazione"), enter, exit, result.getInt("fasciaOraria"), result.getDouble("price")));
                }
                return pren;
            }
        }
    }

    /**
     * Metodo per l'inserimento di una nuova prenotazione nel DB
     *
     * @param u
     * @param ts
     * @param datapren
     * @param pos
     * @param prezzo
     * @return boolean
     * @throws SQLException
     */
    public static boolean insertPrenotazione(Utente u, int ts, LocalDate datapren, String pos, Double prezzo) throws SQLException {
        String query = "INSERT INTO manzo.prenotazioni (dataPrenotazione, idPostazione, fasciaOraria, Utenti_idUtente, price, dataEsecuzione) VALUES (?,?,?,?,?,?)";
        try (Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
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
     *
     * @param u
     * @param id
     * @return boolean
     * @throws SQLException
     */
    public static boolean deletePrenotazione(Utente u, int id) throws SQLException {
        String query = "DELETE FROM manzo.prenotazioni AS P WHERE P.Utenti_idUtente=? AND P.idPrenotazione=?";
        try (Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            statement.setInt(1, u.getIdUtente());
            statement.setInt(2, id);
            return statement.executeUpdate() > 0;
        }
    }

    /**
     * Metodo per eliminare la prenotazione specificata (ADMIN)
     *
     * @param id
     * @return boolean
     * @throws SQLException
     */
    public static boolean deletePrenotazione(int id) throws SQLException {
        String query = "DELETE FROM manzo.prenotazioni AS P WHERE P.idPrenotazione=?";
        try (Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        }
    }


    /**
     * Metodo per il settaggio dell'orario di accesso alla struttura
     *
     * @param thisday
     * @param userId
     * @param ts
     * @param thistime
     * @return int
     * @throws SQLException
     */
    public static int setEntry(LocalDate thisday, int userId, int ts, LocalTime thistime) throws SQLException {
        String query = "SELECT * FROM manzo.prenotazioni AS P WHERE P.dataPrenotazione=? AND P.Utenti_idUtente=? AND (P.fasciaOraria=? OR P.fasciaOraria=0)";
        try (Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            statement.setDate(1, Date.valueOf(thisday));
            statement.setInt(2, userId);
            statement.setInt(3, ts);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                result.getTime("oraIngresso");
                if (result.wasNull()) {
                    result.updateTime("oraIngresso", Time.valueOf(thistime));
                    result.updateRow();
                    return result.getInt("idPrenotazione");
                } else return 0;
            } else return 0;
        }
    }

    /**
     * Metodo per il settaggio dell'orario di uscita dalla struttura
     * @param thisday
     * @param userId
     * @param thistime
     * @return boolean
     * @throws SQLException
     */
    public static boolean setExit(LocalDate thisday, int userId, LocalTime thistime) throws SQLException {
        String query = "SELECT * FROM manzo.prenotazioni AS P WHERE P.dataPrenotazione=? AND P.Utenti_idUtente=?";
        try (Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            statement.setDate(1, Date.valueOf(thisday));
            statement.setInt(2, userId);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                result.getTime("oraIngresso");
                if (!result.wasNull()) {
                    result.getTime("oraUscita");
                    if (result.wasNull()) {
                        result.updateTime("oraUscita", Time.valueOf(thistime));
                        result.updateRow();
                        return true;
                    } else return false;
                } else return false;
            } else return false;
        }
    }

    /**
     * Metodo per il settaggio dell'orario di uscita dalla struttura (Staff)
     * @param idPren
     * @param thistime
     * @return boolean
     * @throws SQLException
     */
    public static boolean setExit(int idPren, LocalTime thistime) throws SQLException {
        String query = "SELECT * FROM manzo.prenotazioni AS P WHERE P.idPrenotazione=?";
        try (Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            statement.setInt(1, idPren);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                result.getTime("oraIngresso");
                if (!result.wasNull()) {
                    result.getTime("oraUscita");
                    if (result.wasNull()) {
                        result.updateTime("oraUscita", Time.valueOf(thistime));
                        result.updateRow();
                        return true;
                    } else return false;
                } else return false;
            } else return false;
        }
    }

    /**
     * Metodo per verificare se l'utente è all'interno della struttura
     *
     * @param thisday
     * @param userId
     * @return int
     * @throws SQLException
     */
    public static int getEntry(LocalDate thisday, int userId) throws SQLException {
        String query = "SELECT * FROM manzo.prenotazioni AS P WHERE P.dataPrenotazione=? AND P.Utenti_idUtente=?";
        try (Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setDate(1, Date.valueOf(thisday));
            statement.setInt(2, userId);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                result.getTime("oraIngresso");
                if (!result.wasNull()) {
                    result.getTime("oraUscita");
                    if (result.wasNull()) {
                        return result.getInt("idPrenotazione");
                    } else return 0;
                } else return 0;
            } else return 0;
        }
    }


    /**
     * Metodo per ottenere tutti i prodotti presenti nel DB
     *
     * @return List<Prodotto>
     * @throws SQLException
     */
    public static List<Prodotto> getProd() throws SQLException {
        String query = "SELECT * FROM manzo.prodotti";
        try (Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet result = statement.executeQuery();
            List<Prodotto> products = new ArrayList<>();
            while (result.next()) {
                Prodotto p = new Prodotto(result.getInt("idProdotto"), result.getString("nome"), result.getString("ingredienti"), result.getString("descrizione"), result.getBigDecimal("importo"), result.getString("categoria"), result.getString("imgurl"));
                products.add(p);
            }
            return products;
        }
    }

    /**
     * Metodo per ottenere il prodotto specificato
     * @param idProd
     * @return Prodotto
     * @throws SQLException
     */
    public static Prodotto getProd(int idProd) throws SQLException {
        String query = "SELECT * FROM manzo.prodotti AS P WHERE p.idProdotto=?";
        try (Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idProd);
            ResultSet result = statement.executeQuery();
            Prodotto p = null;
            if (result.next()) {
                p = new Prodotto(result.getInt("idProdotto"), result.getString("nome"), result.getString("ingredienti"), result.getString("descrizione"), result.getBigDecimal("importo"), result.getString("categoria"), result.getString("imgurl"));
            }
            return p;
        }
    }

    /**
     * Metodo per l'inserimento di un ordine di ristorazione
     * @param order
     * @param idPren
     * @return int
     * @throws SQLException
     */
    public static int setOrder(Ordine order, int idPren) throws SQLException {
        String query = "INSERT INTO manzo.ordini (data, orario, totale, statoOrdine, prenotazioni_idPrenotazione) VALUES (?,?,?,?,?)";
        try(Connection connection=dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setDate(1, Date.valueOf(order.getData()));
            statement.setTime(2, Time.valueOf(order.getOra()));
            statement.setBigDecimal(3, order.getImporto());
            statement.setString(4, order.getStato());
            statement.setInt(5, idPren);
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            int idordine = -1;
            if(rs.next()){
                idordine = rs.getInt(1);
                rs.close();
                for (Map.Entry<Integer, Pair<Integer, BigDecimal>> entry : order.getProdotti().entrySet()) {
                    String query2 = "INSERT INTO manzo.ordini_has_prodotti (ordini_idOrdine, prodotti_idProdotto, quantita, importo) VALUES (?,?,?,?)";
                    Pair<Integer, BigDecimal> detailsprod = entry.getValue();
                    try(PreparedStatement statement2 = connection.prepareStatement(query2)) {
                        statement2.setInt(1, idordine );
                        statement2.setInt(2, entry.getKey());
                        statement2.setInt(3, detailsprod.getKey());
                        statement2.setBigDecimal(4, detailsprod.getValue());
                        statement2.executeUpdate();
                    }
                }
            } return idordine;
        }
    }

    /**
     * Metodo per ottenere l'ordine specificato
     * @param idOrdine
     * @return Ordine
     * @throws SQLException
     */
    public static Ordine getOrder(int idOrdine) throws SQLException {
        String query = "SELECT * FROM manzo.ordini AS O JOIN manzo.ordini_has_prodotti AS P ON O.idOrdine=P.ordini_idOrdine WHERE O.idOrdine=?";
        try (Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idOrdine);
            ResultSet result = statement.executeQuery();
            if(result.next()){
                HashMap<Integer, Pair<Integer, BigDecimal>> prodotti = new HashMap<>();
                LocalDate orderdate = LocalDate.parse(result.getDate("data").toString());
                LocalTime ordertime = result.getTime("orario").toLocalTime();
                BigDecimal importo = result.getBigDecimal("totale");
                String orderstatus = result.getString("statoOrdine");
                int idPrenotazione = result.getInt("prenotazioni_idPrenotazione");
                do {
                    prodotti.put(result.getInt("prodotti_idProdotto"), new Pair<>(result.getInt("quantita"), result.getBigDecimal("importo")));
                } while(result.next());
                return new Ordine(idOrdine, orderdate, ordertime, prodotti, importo, orderstatus, idPrenotazione);
            } else return null;
        }
    }

    /**
     * Metodo per ottenere la lista degli ordini (Odierni o Complessivi)
     * @param actual
     * @return List<Ordine>
     * @throws SQLException
     */
    public static List<Ordine> getOrders(boolean actual) throws SQLException{
        if(actual){
            String query = "SELECT * FROM manzo.ordini as O WHERE O.data=? ORDER BY O.orario ASC";
            try (Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
                LocalDate now = LocalDate.now();
                statement.setDate(1, Date.valueOf(now));
                ResultSet result = statement.executeQuery();
                List<Ordine> orders = new ArrayList<>();
                while (result.next()){
                    orders.add(Database.getOrder(result.getInt("idOrdine")));
                }
                return orders;
            }
        }else {
            String query = "SELECT * FROM manzo.ordini as O ORDER BY O.orario ASC";
            try (Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
                LocalDate now = LocalDate.now();
                ResultSet result = statement.executeQuery();
                List<Ordine> orders = new ArrayList<>();
                while (result.next()){
                    orders.add(Database.getOrder(result.getInt("idOrdine")));
                } return orders;
            }

        }
    }

    /**
     * Metodo per l'eliminazione dell'ordine specificato
     * @param idOrdine
     * @return boolean
     * @throws SQLException
     */
    public static boolean deleteOrder(int idOrdine) throws SQLException {
        String query = "DELETE FROM manzo.ordini AS O JOIN manzo.ordini_has_prodotti AS OP ON O.idOrdine = OP.ordini_IdOrdine WHERE O.idOrdine=?";
        try (Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            statement.setInt(1, idOrdine);
            return statement.executeUpdate() > 0;
        }
    }


    /**
     * Metodo per il modificare lo stato dell'ordine di ristorazione
     * @param id
     * @return boolean
     * @throws SQLException
     */
    public static boolean setOrderStatus(int id) throws SQLException{
        String query = "SELECT statoOrdine FROM manzo.ordini WHERE idOrdine=? ";
        try (Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if(result.next()){
                if(result.getString("statoOrdine").equals("emesso")){
                    String query2 = "UPDATE manzo.ordini SET statoOrdine=? WHERE idOrdine=? ";
                    try (Connection connection2 = dataSource.getConnection(); PreparedStatement statement2 = connection2.prepareStatement(query2)) {
                        statement2.setString(1, "pronto");
                        statement2.setInt(2, id);
                        return statement2.executeUpdate() > 0;
                    }
                } else return false;
            }else return false;
        }
    }

    /**
     * Metodo per ottenere le postazione riguardanti una prenotazione
     * @param id
     * @return String
     * @throws SQLException
     */
    public static String getSeat(int id) throws SQLException{
        String query = "SELECT * FROM manzo.prenotazioni as P WHERE P.idPrenotazione=?";
        try(Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(query)){
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if(result.next()){
                return result.getString("idPostazione");
            } else return null;
        }
    }


}
