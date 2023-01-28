import java.sql.Connection;

public interface JDBsqlInterface {

    void pokazBaze();
    void zamknijBaze(Connection con);
    cNapoj[] przeniesienieZBazyDoTablicy(int l);
    void zmienIlosc1(cNapoj nap);
    void zmienNrNaLisicie1(cNapoj nap, int id);
    void usunZListyB(cNapoj nap);
    void usunZBazy(int id);
    boolean dodajDoBazy(cNapoj nap);
    cNapoj getProduktPoId(int id);
    Integer getNrPoId(int id);  //Zwraca null kiedy: nie można znaleźć obiektu po id lub nr_na_liście jest null'em
}
