import java.sql.*;

public class cAutomatZNapojami extends JDBsql
{
    private cNapoj[] napoje;
    private double money;
    final private short large = 9;











    public cAutomatZNapojami() {
        super();
        this.napoje = new cNapoj[large];
        this.money = 100;

        this.napoje = przeniesienieZBazyDoTablicy(large);
  /*      try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Połączono z bazą");

        }catch (ClassNotFoundException e){
            //Wyrzuć wyjątki jeśli zostaną wychwycone błędy związane z podłączeniem z bazą lub błędy zapytania o dane
            System.out.println("Wyj 1: (Brak sterownika JDBC) "+e);
        }catch(SQLException e){
            //Wyrzuć wyjątki jeśli zostaną wychwycone błędy związane z logowaniem do bazy i kwerendą
            System.out.println("Wyj 2: "+e);
        }

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet= statement.executeQuery("select * from napoje where nr_na_liście is not null");

 //       while(resultSet.next())
 //           System.out.println(resultSet.getInt(1)+" "+resultSet.getString(2)+" "+resultSet.getDouble(3)+" "+resultSet.getInt(4));

            napoje = przeniesienieZBazyDoTablicy(resultSet);
            System.out.println("Wykonano przeniesienie");

        }catch(SQLException e){
                System.out.println("Wyj 3: "+e);
            }

 //       this.pokazTowar();



        try {
        connection.close();
        }catch(SQLException e) {
            System.out.println("Wyj 4: " + e);
        }
*/
    }

  /*  public cNapoj[] przeniesienieZBazyDoTablicy(ResultSet rs){
        cNapoj[] tabtym = new cNapoj[large];
        try {
            while (rs.next()) {
                int nr = rs.getInt(1);
                if(nr<large)
                    tabtym[nr] = new cNapoj(nr, rs.getString(2),rs.getDouble(3),rs.getInt(4));
            }

        }catch(SQLException e){
            System.out.println("Wyj (przeniesienie): " + e);
        }
        return tabtym;
    }
*/
    public void pokazTowar(){
        for(int i=0; i<large; i++){
            try {
                System.out.println(i + ". " + napoje[i].toString());
            }catch(NullPointerException e){
                System.out.println(i+". pusty");
            }
        }
    }
    public void kupNapoj(int nr, double m){
        double reszta = m - napoje[nr].getCena();
        System.out.println("Oto reszta: "+reszta+"zł.");
        napoje[nr].setIlosc(napoje[nr].getIlosc()-1);
        System.out.println("Dostajesz swój napój: "+ napoje[nr].getNazwa() +"\n\n");
    }

    public cNapoj[] getNapoje() {return napoje; }
    public short getLarge()     {return large; }
    public double getMoney()    {return money; }
}
