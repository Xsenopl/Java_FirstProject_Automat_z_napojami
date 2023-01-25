
public interface iAutomatControllerInterface {

    default void startMenu() {
        System.out.println("""
                Jesteś przed automatem z napojami, co robisz?
                1 - Kupuję napój.
                2 - Edytuję automat.
                0 - Odchodzę.
                """);
    }
    void start();
    void kupno();
    boolean sprawdzMiejsceNULL(int msc);
    boolean sprawdzMiejsceZero(int msc);
    void pokazListeProduktow();




}
