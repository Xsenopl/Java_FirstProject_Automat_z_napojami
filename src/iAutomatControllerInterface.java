import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public interface iAutomatControllerInterface {

    default void startMenu() {
        System.out.println("""
                Jesteś przed automatem z napojami, co robisz?
                1 - Kupuję napój.
                2 - Edytuję automat.
                0 - Odchodzę.
                """);
    }
    default void edytorMenu(){
        System.out.println("""
                Panel sterowania. Wybierz czynność.
                1 - Usuń napój z automatu.
                2 - Usuń napój z bazy.
                3 - Dodaj napój do automatu.
                4 - Dodaj napój do bazy.
                0 - Zakończ.
                """);
    }
    default boolean takCzyNie(){
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        String temp = null;
        try{
            temp = r.readLine();
        }catch (IOException e){
            System.out.println("IOException błąd! " + e);
        }catch (Exception e) {
            System.out.println("Błąd w wyborze tak lub nie "+ e); return false;
        }
        return (temp.equals("y") || temp.equals("Y") || temp.equals("yes") || temp.equals("Yes"));
    }
    void start();
    void kupno();

    void pokazListeProduktow();
    void edycja();
    void usunZAutomatu();
    void usunZBazy();
    void dodajNaListe();

}
