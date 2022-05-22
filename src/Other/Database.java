package Other;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Database {
    private static Database instance = null;

    public Database() {
        System.out.println("Started database");
    }

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public static void reset() {
        instance = null;
    }

    public void updateEntry(String entry, int value) {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:date.db");
            c.setAutoCommit(false);

            stmt = c.createStatement();

            String sql = "UPDATE SCORES set " + entry + " = " + value;

            stmt.executeUpdate(sql);
            c.commit();

            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    public ArrayList<Integer> getDatabaseInfo() {
        ArrayList<Integer> result = new ArrayList<>();
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:date.db");
            c.setAutoCommit(false);

            stmt = c.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM SCORES;");

            while (rs.next()) {
                int score = rs.getInt("high_score");
                result.add(score);

                int red = rs.getInt("red_kills");
                result.add(red);

                int blue = rs.getInt("blue_kills");
                result.add(blue);

                int green = rs.getInt("green_kills");
                result.add(green);

                int ylw = rs.getInt("ylw_kills");
                result.add(ylw);
            }
            stmt.close();
            rs.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return result;
    }
}
