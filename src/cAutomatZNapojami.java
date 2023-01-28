import java.util.Objects;

public class cAutomatZNapojami extends JDBsql
{
    private cNapoj[] napoje;
    private double money;
    final private short large = 9, depth =10;
    final private String password = "qwe123";











    public cAutomatZNapojami() {
        super();
        this.napoje = new cNapoj[large];
        this.money = 100;

        this.napoje = przeniesienieZBazyDoTablicy(large);
    }

    public void pokazTowar(){
        for(int i=0; i<large; i++){
            try {
                System.out.println(i + ". " + napoje[i].toString());
            }catch(NullPointerException e){
                System.out.println(i+". pusty");
            }
        }
    }
    @Override
    public void pokazBaze(){super.pokazBaze(); }

    public void kupNapoj(int nr, double m){         //niedorobiona całkiem
        double reszta = m - napoje[nr].getCena();
        System.out.println("Oto reszta: "+reszta+"zł.");
        napoje[nr].setIlosc(napoje[nr].getIlosc()-1);
        System.out.println("Dostajesz swój napój: "+ napoje[nr].getNazwa() +"\n\n");
        zmienIlosc1(napoje[nr]);
    }

    public void usunZListyA(int nr){

        napoje[nr].setIlosc(0);
        zmienIlosc1(napoje[nr]);
        usunZListyB(napoje[nr]);

        napoje[nr].setNr_na_liscie(null);
        napoje[nr] =null;
        System.out.println("Nastąpiło usunięcie z listy automatu");
        //System.out.println("A oto towar po zmianie")
        // pokazTowar();
    }
    @Override
    public void usunZBazy(int id){
        Integer nr = getNrPoId(id);

        try{
            if (czyWIndexieTablicy(nr)) {
            //    System.out.println("Produkt mieści się na liście");
                usunZListyA(nr);
            }
        }catch (NullPointerException e){
         //   System.out.println("Produktu nie ma na liście");
        }
        finally {
            super.usunZBazy(id);
            System.out.println("Dokonano usunięcia. " + id+" "+nr+"\n\n");
        }
 /*
        if(nr != null) {
            System.out.println("\n\n\n Produkt istnieje ");
            if (czyWIndexieTablicy(nr)) {
                usunZListyA(nr);
                    System.out.println("------------  i mieści się w tablicy");
            }
      //      super.usunZBazy(id);        //NullPointerException ArrayIndexOutOfBoundsException
            System.out.println("Dokonano usunięcia. " + nr);
        }
        else System.out.println("Produkt nie istnieje! = "+ nr);

  */
    }
    public void dodajNaListe1(cNapoj nap, int id){
        napoje[nap.getNr_na_liscie()] = nap;
        zmienNrNaLisicie1(nap,id);
        zmienIlosc1(nap);
    }
    @Override
    public boolean dodajDoBazy(cNapoj nap){
       return super.dodajDoBazy(nap);
    }


    public void setNullNapojeNr(int nr) {
        if (czyWIndexieTablicy(nr))
            napoje[nr]=null;    }
    public cNapoj[] getNapoje() {return napoje; }
    public short getLarge()     {return large; }
    public short getDepth() {return depth; }
    public double getMoney()    {return money; }
    @Override
    public cNapoj getProduktPoId(int id){ return super.getProduktPoId(id); }
    public boolean sprawdzMiejsceNULL(int msc){ return napoje[msc] != null;}            //Zwraca true, jeśli miejsce nie jest puste
    public boolean sprawdzMiejsceZero(int msc){  return napoje[msc].getIlosc() != 0;}   //Zwraca true, jeśli miejsce posiada ilość != 0
    public boolean sprawdzHaslo(String h){return Objects.equals(h, password);}
    public boolean czyWIndexieTablicy(int i) throws NullPointerException{
        return(0<=i && i<large);
    }


}
