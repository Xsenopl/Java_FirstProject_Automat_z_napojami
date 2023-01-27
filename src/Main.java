//import jdk.incubator.vector.VectorOperators;

import java.sql.*;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {

    private static Connection connection;
    private static Statement statement;

    public static void main(String[] args) {
        cAutomatZNapojami automatZNapojami = new cAutomatZNapojami();
        cAutomatController controller1 = new cAutomatController(automatZNapojami);

        System.out.println("Automat z napojami");


  //      VectorOperators.Conversion<Integer, String>()





        controller1.start();

    }





















    public static void wyswietlDane(ResultSet rs){
        try{
            System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getInt(3));
        }catch(SQLException e){
            System.out.println("Wyj wyświetl: "+e +"\n");
            // e.printStackTrace();
        }
    }






    public static void Start(){
        Scanner input = new Scanner(System.in);
        int decyzja;

        while (true) {
            StartMenu();
            try {
                decyzja = input.nextInt();
                switch (decyzja) {
                    case 0 -> { System.out.println("Odchodzisz od automatu."); System.exit(0); }
                    case 1 -> System.out.println("Wybrano 1. Kupujesz.");
                    case 2 -> System.out.println("Wybrano 2");
                    default -> System.out.println("\n\nŹle wybrano czynność liczba.");
                }
            }catch(InputMismatchException e){
                System.out.println("\n\nŹle wybrano czynność litera.");
                input.next();
            }




        }
    }
    public static void StartMenu(){
        System.out.println("""
                    Jesteś przed automatem z napojami, co robisz?
                    1 - Kupuję napój.
                    2 - Edytuję automat.
                    0 - Odchodzę.
                    """);
    }







}