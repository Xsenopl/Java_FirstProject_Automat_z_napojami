import java.sql.Connection;
import java.util.Map;

public interface JDBsqlInterface {

    void pokazBaze();
    void zamknijBaze(Connection con);
    cNapoj[] przeniesienieZBazyDoTablicy(int l, int d);
    void zmienIlosc1(cNapoj nap);
    void zmienNrNaLisicie1(cNapoj nap, int id);
    public void zmienIloscPoId(int id, int il);
    void usunZListyB(cNapoj nap);
    void usunZBazy(int id);
    boolean dodajDoBazy(cNapoj nap);
    boolean dodajDoBazy(cNapoj nap,int id);
    cNapoj getProduktPoId(int id);
    Integer getNrPoId(int id);  //Zwraca null kiedy: nie można znaleźć obiektu po id lub nr_na_liście jest null'em
    Map getPokazCalaBaze();
}
