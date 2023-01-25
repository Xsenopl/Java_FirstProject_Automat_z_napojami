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
                    case 2 -> System.out.println("Wybrano 2");
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
    if(!sprawdzMiejsceNULL(decyzja) || !sprawdzMiejsceZero(decyzja)) {System.out.println("\tTo miejsce jest puste\n\n"); return;}

    System.out.println(" "+automat.getNapoje()[decyzja].toString()+"\nZapłać używając nominałów");
 //   Scanner input3 = new Scanner(System.in);
    double zaplata = input.nextDouble();
    automat.kupNapoj(decyzja,zaplata);
}

    public boolean sprawdzMiejsceNULL(int msc){
        return automat.getNapoje()[msc] != null;
    }
    public boolean sprawdzMiejsceZero(int msc){
        return automat.getNapoje()[msc].getIlosc() != 0;
    }

    public void pokazListeProduktow(){
        int i=1;
        for(cNapoj n: automat.getNapoje()){
            System.out.println(" "+i+".  "+n.toString());
        }
    }

















}


//klasa z obsługą błędu
class BlednyZakresTablicy extends Exception {
    public BlednyZakresTablicy() {
    }
}