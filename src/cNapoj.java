public class cNapoj {
    private String nazwa;
    private int ilosc;
    private Integer nr_na_liscie;
    private double cena;

    public cNapoj(int nr_na_liscie, String nazwa, double cena, int ilosc) {
        this.nr_na_liscie = nr_na_liscie;
        this.nazwa = nazwa;
        this.cena = cena;
        this.ilosc = ilosc;
    }

    @Override
    public String toString(){
        return (nazwa +" "+cena+"zł - dostępnych: "+ ilosc + " sztuk.");
    }

    public int getIlosc() { return ilosc; }
    public double getCena() { return cena; }
    public String getNazwa() { return nazwa; }
    public Integer getNr_na_liscie() { return nr_na_liscie; }

    public void setNazwa(String nazwa) {  this.nazwa = nazwa; }
    public void setIlosc(int ilosc) { this.ilosc = ilosc; }
    public void setNr_na_liscie(Integer nr_na_liscie) { this.nr_na_liscie = nr_na_liscie; }
    public void setCena(double cena) { this.cena = cena; }
}
