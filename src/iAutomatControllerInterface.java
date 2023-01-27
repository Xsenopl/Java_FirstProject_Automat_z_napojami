
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
    void start();
    void kupno();

    void pokazListeProduktow();
    void edycja();
    void usunZAutomatu();
    void usunZBazy();

}
