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
                    case 0 -> { System.out.println("Odchodzisz od automatu."); input.close(); System.exit(0); }
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
    int decyzja = -1;
    for(int i=1; i<4; i++){
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
    try {
        System.out.print("Wybrano numer " + decyzja);
        if (!automat.sprawdzMiejsceNULL(decyzja) || !automat.sprawdzMiejsceZero(decyzja)) {
            System.out.println("\tTo miejsce jest puste.\n\n");
            return;
        }
    }catch (ArrayIndexOutOfBoundsException e){
        System.out.println("\tTo miejsce jest puste\n\n");
        return;
    }
    System.out.println(" "+automat.getNapoje()[decyzja].toString()+"\nZapłać używając nominałów");
    double zaplata = input.nextDouble();
    automat.kupNapoj(decyzja,zaplata);
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
                    case 3 -> { System.out.println("Wybrano 3 - Dodajesz do automatu." ); dodajNaListe(); }
                    case 4 -> { System.out.println("Wybrano 4 - Dodajesz do bazy." ); dodajDoBazy();}
                    case 8 -> { System.out.println("Wybrano 8 - Eksportujesz z bazy." ); eksportFile();}
                    case 9 -> { System.out.println("Wybrano 9 - Importujesz do bazy." ); importFile();}
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
        Integer decyzja = null;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Baza posiada produkty: ");
        automat.pokazBaze();
        System.out.println("Wybierz id produktu do usunięcia:");
        try{
            decyzja = Integer.parseInt(reader.readLine());
        }catch (IOException e){
            System.out.println("IOException błąd! " + e);
        }catch (NumberFormatException | NullPointerException e){
            System.out.println("Anulowano wybieranie \n\n"); return;
        }//catch (NoSuchElementException e){}
        if(automat.getProduktPoId(decyzja)==null) {
            System.out.println("Nie ma takiego produktu."); return;
        }
        System.out.println("Czy na pewno chcesz usunąć produkt z bazy? ( y / n )");
        if(takCzyNie())
            automat.usunZBazy(decyzja);
        else System.out.println("Usuwanie z bazy anulowane.\n\n");
    }

    public void dodajNaListe() {
        int nr;
        System.out.println("Oto miejsca na liście automatu oraz ich zawartość");
        automat.pokazTowar();
        System.out.println("Wybierz miejsce, które chcesz edytować.");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Integer decyzja;
        try {
            decyzja = Integer.parseInt(reader.readLine());
            if (!automat.czyWIndexieTablicy(decyzja)) throw new BlednyZakresTablicy();
        } catch (IOException e) {
            System.out.println("IOException błąd! " + e + "\nAnulowano edycję miejsc.");
            return;
        } catch (BlednyZakresTablicy e) {
            System.out.println("Błędny zakres tablicy. Anulowano edycję miejsc.");
            return;
        } catch (NumberFormatException e) {
            System.out.println("Anulowano edycję miejsc \n\n");
            return;
        }

        nr= decyzja;
        if (automat.sprawdzMiejsceNULL(nr)) {                                                                           //Jeśłi miejsce nie jest puste
            System.out.println("Miejsce " + nr + " posiada już produkt. Czy chcesz go najpierw usunąć z listy produktów?\ny / n\n");
            if (takCzyNie()) {
                automat.usunZListyA(nr);
                dodajNaListe();
            }
        }
        else{                                                                                                        //Jeśłi miejsce jest puste
            cNapoj n;
            int id, nrPast=-1;
            System.out.println("\n Wybrano miejsce "+nr+" do dodania towaru. Oto baza dostępnych produktów:");
            automat.pokazBaze();
            System.out.println("Wybierz produkt id, który dodać do asortymentu");

            try {
                decyzja = Integer.parseInt(reader.readLine());

            }catch (IOException e) {
                System.out.println("IOException błąd! " + e + "\nAnulowano edycję miejsc.");
                return;
            }catch (NumberFormatException e) {
                System.out.println("Anulowano edycję miejsc \n\n");
                return;
            }
            n = automat.getProduktPoId(decyzja);
            if(n==null){
                System.out.println("Nie ma produktu o id = "+decyzja+"\n"); return;}
            id = decyzja;
            if(automat.getNrPoId(decyzja)!=null) nrPast= automat.getNrPoId(decyzja);
            System.out.println("Podaj liczbę umieszczonych sztuk produktów "+n+" Nie może być ich więcej niż "+automat.getDepth()+" sztuk.");
            try {
                decyzja = Integer.parseInt(reader.readLine());

            }catch (IOException e) {
                System.out.println("IOException błąd! " + e + "\nAnulowano edycję miejsc.");
                return;
            }catch (NumberFormatException e) {
                System.out.println("Anulowano edycję miejsc \n\n");
                return;
            }
            if(decyzja<0) decyzja=0;
            else if(decyzja>10) decyzja=(int)automat.getDepth();

            automat.setNullNapojeNr(nrPast);
            n.setNr_na_liscie(nr);
            n.setIlosc(decyzja);
            automat.dodajNaListe1(n, id);

            System.out.println("Dodano na listę"+n+". Oto lista po zmianie:");
            automat.pokazTowar();
        }
    }

    public void dodajDoBazy(){
        cNapoj nowyProdokt;
        String nazwa;
        int ilosc;
        //Integer nr_na_liscie;
        double cena;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.print("Nazwa: ");    nazwa = reader.readLine();
            System.out.print("Ilość: ");    ilosc = Integer.parseUnsignedInt(reader.readLine());
            System.out.print("Cena: ");    cena = Double.parseDouble(reader.readLine());
            if(cena<=0.00) throw new NumberFormatException();
        } catch (IOException | NumberFormatException e) {
            System.out.println("Błąd we wprowadzaniu danych nowego produktu\n"); return;
        }
        nowyProdokt = new cNapoj(null, nazwa, cena, ilosc);
        if(automat.dodajDoBazy(nowyProdokt))
            System.out.println("Dodano produkt "+nowyProdokt+" do bazy");
        else System.out.println("Dodawanie produktu "+nowyProdokt+" się nie powiodło.");
    }


    public void eksportFile(){
        System.out.print("Podaj nazwę pliku.csv, do którego zamierzasz wyeksportować dane z bazy danych: ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        CsvManager cv = new CsvManager();
        String nazwa = "";
        try{
            nazwa = reader.readLine();

        }catch (IOException e){
            System.out.println("Błąd we wpisywaniu nazwy pliku");
        }
        cv.exportToFileCsv(nazwa);

    }
    public void importFile(){
        System.out.print("Podaj nazwę pliku.csv, z którego zamierzasz importować dane z bazy danych: ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        CsvManager cv = new CsvManager();
        String nazwa = "";
        try{
            nazwa = reader.readLine();

        }catch (IOException e){
            System.out.println("Błąd we wpisywaniu nazwy pliku");
        }
        cv.importFromFileCsvToDatabase(nazwa);
    }

}
















//klasa z obsługą błędu
class BlednyZakresTablicy extends Exception {
    public BlednyZakresTablicy() {
    }
}