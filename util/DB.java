package util;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Drazen
 */
public class DB {
    private static DB instance;
    private static final int MAX_CON = 5;
    private static final Connection[] bafer = new Connection[MAX_CON];
    private int first = 0, last = 0, free = MAX_CON;

    private DB() { // za MySQL
        try {
            Class.forName("com.mysql.jdbc.Driver");
            for (int i = 0; i < MAX_CON; i++) {
                // u slucaju da nije moguce ostvariti konekciju sa bazom zbog problema sa
                // keystorom koristiti sledecu liniju
                // bafer[i] =
                // DriverManager.getConnection("jdbc:mysql://localhost:3306/jsf_laboratorija?useSSL=false","root","");
                // u slucaju problema sa vremenskim zonama u workbenchu ili phpmyadminu izvrsiti
                // sledece komande
                // SET @@global.time_zone='+00:00';
                // SET @@session.time_zone='+00:00';
                bafer[i] = DriverManager.getConnection("jdbc:mysql://localhost:3306/zoo2020?useSSL=false", "root", "12345");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DB getInstance() {
        if (instance == null)
            instance = new DB();
        return instance;
    }

    public synchronized Connection getConnection() {
        if (free == 0)
            return null;
        free--;
        Connection con = bafer[first];
        first = (first + 1) % MAX_CON;
        return con;
    }

    public synchronized void putConnection(Connection con) {
        if (con == null)
            return;
        free++;
        bafer[last] = con;
        last = (last + 1) % MAX_CON;
    }
}
