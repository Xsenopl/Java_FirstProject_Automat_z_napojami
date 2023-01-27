import java.sql.*;

public class JDBsql implements JDBsqlInterface{
    final protected String url="jdbc:mysql://127.0.0.1/automatznapojamijava";
    final protected String password="";
    final protected String username="root";
    private Connection connection;


    public JDBsql() {
        try {
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
        assert connection != null;
        zamknijBaze(connection);
    }

    protected void pokazBaze(){
        try {
            connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from napoje1");
            System.out.println("id,\tnr_na_Liście,  nazwa  cena  ilość");
            while (rs.next()) {
                System.out.println(rs.getInt(1) + "  \t\t" + rs.getString(2) + " ----- " + rs.getString(3) + " \t" + rs.getDouble(4) + "zł  ," + rs.getInt(5));
            }
        }catch(SQLException e){
            System.out.println("Wyj (pokazanieBazy): " + e);
        }
        zamknijBaze(connection);
    }

    private void zamknijBaze(Connection con){
        try {
            con.close();
        }catch(SQLException e) {
            System.out.println("Wyj 4 (zamknięcie bazy): " + e);
        }
    }
    public cNapoj[] przeniesienieZBazyDoTablicy(int l){
        cNapoj[] tabtym = new cNapoj[l];

        try {
            connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from napoje1 where nr_na_liście is not null");
            while (rs.next()) {
                //System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+sr.getDouble(3)+" "+sr.getInt(4));
                int nr = rs.getInt(2);
                if(nr<l)
                    tabtym[nr] = new cNapoj(nr, rs.getString(3),rs.getDouble(4),rs.getInt(5));
            }
        }catch(SQLException e){
            System.out.println("Wyj (przeniesienieZ): " + e);
        }
        zamknijBaze(connection);
        return tabtym;
    }


 /*   public void przeniesienieZTablicyDoBazy(cNapoj[] nap){   /// NIEZROBIONE
        try {
            connection = DriverManager.getConnection(url, username, password);
            PreparedStatement prepStmt = connection.prepareStatement("Update napoje set ilosc WHERE id= ? ;");


            for(int i = 0; i<nap.length; i++){




            }
        }catch (SQLException e){
            System.out.println("Wyj (przeniesienieDo): " + e);
        }
    }
    public void przeniesienieZTabDoBazy(cNapoj nap){

    }
*/
    public void zmienIlosc1(cNapoj nap){
        try {
            connection = DriverManager.getConnection(url, username, password);
            PreparedStatement prepStmt = connection.prepareStatement("Update napoje1 set ilosc = ? WHERE nr_na_liście = ? ;");
            prepStmt.setInt(1, nap.getIlosc());
            prepStmt.setInt(2, nap.getNr_na_liscie());
            prepStmt.execute();
        }catch (SQLException e){
            System.out.println("Wyj (przeniesienieDo): " + e);
        }
        zamknijBaze(connection);
    }
    public void usunZListyB(cNapoj nap){
        try {
            connection = DriverManager.getConnection(url, username, password);
            PreparedStatement prepStmt = connection.prepareStatement("Update napoje1 set nr_na_liście = null WHERE nr_na_liście = ? ;");
            prepStmt.setInt(1, nap.getNr_na_liscie());
            prepStmt.execute();
        }catch (SQLException e){
            System.out.println("Wyj (usunięcieZListy): " + e);
        }
        zamknijBaze(connection);
    }
    public void usunZBazy(int id){
        try {
            connection = DriverManager.getConnection(url, username, password);
            PreparedStatement prepStmt = connection.prepareStatement("Delete from napoje1 where id = ? ;");
            prepStmt.setInt(1, id);
            prepStmt.execute();
        }catch (SQLException e){
            System.out.println("Wyj (usunięcieZ): " + e);
        }
        zamknijBaze(connection);

    }

    //Zwraca null kiedy: nie można znaleźć obiektu po id; nr_na_liście jest null'em
    public Integer getNrPoId(int id){
        Integer nr = null;
        try {
            connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select nr_na_liście from napoje1 where id = "+id);
            if (rs.next()) {
               // System.out.println("\n---Widzi i robi---\n");
                if (rs.getString(1) != null) {nr = rs.getInt(1);}
                  //  System.out.println(nr);}
            }
        }catch (SQLException e){
            System.out.println("Wyj (gerNrPoId): " + e);
        }
        zamknijBaze(connection);
        return nr;
    }

}
