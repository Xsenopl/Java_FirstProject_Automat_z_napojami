import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    private static Connection connection;
    private static Statement statement;

    public static void main(String[] args) {
        cAutomatZNapojami automatZNapojami = new cAutomatZNapojami();
        cAutomatController controller1 = new cAutomatController(automatZNapojami);

        System.out.println("Automat z napojami");


        controller1.start();


    }

}