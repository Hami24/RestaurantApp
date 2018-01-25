package Baza.konekcija;

public class MySQLpodaci {
    public static String HOST="localhost";
    public static int PORT= 3306;
    public static String DB_NAME = "student1";
    public static String DB_HOST = "root";
    public static String DB_PASSWORD = "";
    
    public static String URL = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DB_NAME;
}
