import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.InputMismatchException;
import java.util.Scanner;

public class cAutomatController implements iAutomatControllerInterface
{
    private cAutomatZNapojami automat;

    public cAutomatController(cAutomatZNapojami a){
        automat = a;
    }



    public  void start()
    {
        Scanner input = new Scanner(System.in);
        int decyzja;

        while (true) {
            startMenu();
            try {
                decyzja = input.nextInt();
                switch (decyzja) {
                    case 0 -> { System.out.println("Odchodzisz od automatu."); System.exit(0); }
                    case 1 -> { System.out.println("Wybrano 1 - Kupujesz.");  kupno();  }
                    case 2 -> { System.out.println("Wybrano 2 - Edytujesz." ); edycja();}
                    default -> System.out.println("\n\nŹle wybrano czynność liczba.");
                }
            }catch(InputMismatchException e){
                System.out.println("\n\nŹle wybrano czynność litera.");
                input.next();
            }
        }
    }






public void kupno(){
    System.out.println("Wybierz numer produktu, który chcesz kupić:");
    automat.pokazTowar();
    Scanner input = new Scanner(System.in);
    int decyzja;
    while (true){
        try {
            decyzja = input.nextInt();
            if(decyzja<0 || decyzja>automat.getLarge()-1) throw new BlednyZakresTablicy();
            break;

        } catch (InputMismatchException e) {
            System.out.println("Podano błędną wartość!");
            input.next();
        }
        catch (BlednyZakresTablicy e) {
            System.out.println("Podano błędną wartość!");
        }
    }
    System.out.print("Wybrano numer "+decyzja);
    if(!automat.sprawdzMiejsceNULL(decyzja) || !automat.sprawdzMiejsceZero(decyzja)) {System.out.println("\tTo miejsce jest puste\n\n"); return;}

    System.out.println(" "+automat.getNapoje()[decyzja].toString()+"\nZapłać używając nominałów");
 //   Scanner input3 = new Scanner(System.in);
    double zaplata = input.nextDouble();
    automat.kupNapoj(decyzja,zaplata);
}

    public void pokazListeProduktow(){
        int i=1;
        for(cNapoj n: automat.getNapoje()){
            System.out.println(" "+i+".  "+n.toString());
        }
    }

    public void edycja() {
        System.out.println("Wpisz hasło, by móc edytować: ");
        Scanner input = new Scanner(System.in);
        if (!automat.sprawdzHaslo(input.nextLine())) { System.out.println("Błędne hasło.\n\n"); return; }
        System.out.println("\nWeryfikacja pomyślna");
        int decyzja;

        while (true) {
            edytorMenu();
            try {
                decyzja = input.nextInt();
                switch (decyzja) {
                    case 0 -> { System.out.println("Log Out."); return; }
                    case 1 -> { System.out.println("Wybrano 1 - Usuwasz z automatu."); usunZAutomatu();  }
                    case 2 -> { System.out.println("Wybrano 2 - Usuwasz z bazy." ); usunZBazy(); }
                    case 3 -> { System.out.println("Wybrano 3 - Dodajesz do automatu." ); }
                    case 4 -> { System.out.println("Wybrano 4 - Dodajesz do bazy." ); }
                    default -> System.out.println("\n\nŹle wybrano czynność liczba.");
                }
            }catch(InputMismatchException e){
                System.out.println("\n\nŹle wybrano czynność litera.");
                input.next();
            }
        }
    }

    public void usunZAutomatu(){
        System.out.println("Automat posiada towary: ");
        automat.pokazTowar();
        System.out.println("Wpisz numer na liście do usunięcia danego towaru.");
        Scanner input = new Scanner(System.in);
        int decyzja;

        while (true){
            try {
                decyzja = input.nextInt();
                if(decyzja<0 || decyzja>automat.getLarge()-1) throw new BlednyZakresTablicy();
                break;

            } catch (InputMismatchException e) {
                System.out.println("Podano błędną wartość!1");
                input.next();
            }
            catch (BlednyZakresTablicy e) {
                System.out.println("Podano błędną wartość!2");
            }
        }

        if(!automat.sprawdzMiejsceNULL(decyzja)) {
            System.out.println("Miejsce jest puste, możesz dodać do niego towar"); return; }

        System.out.println("Postanowiłeś usunąć "+automat.getNapoje()[decyzja].getNazwa()+ " Czy napewno chcesz to zrobić?\ny / n\n");
        String yn; yn = input.next();
        if(yn.equals("y") || yn.equals("Y") || yn.equals("yes") || yn.equals("Yes")){
            automat.usunZListyA(decyzja);
        }
        else {
            System.out.println("Anulowano");
        }
    }
    public void usunZBazy(){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Baza posiada produkty: ");
        automat.pokazBaze();
        System.out.println("Wybierz id produktu do usunięcia:");
       // while
        try{
         automat.usunZBazy(Integer.parseInt(reader.readLine()));
        }catch (IOException e){
            System.out.println("IOException błąd! " + e);
        }catch (NumberFormatException e){
            System.out.println("Anulowano wybieranie \n\n");
        }
    }















}


//klasa z obsługą błędu
class BlednyZakresTablicy extends Exception {
    public BlednyZakresTablicy() {
    }
}