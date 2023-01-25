import java.sql.*;

public class JDBsql {
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

/*        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet= statement.executeQuery("select * from napoje where nr_na_liście is not null");

            //       while(resultSet.next())
            //           System.out.println(resultSet.getInt(1)+" "+resultSet.getString(2)+" "+resultSet.getDouble(3)+" "+resultSet.getInt(4));

   //         napoje = przeniesienieZBazyDoTablicy(resultSet);
            System.out.println("Wykonano przeniesienie");

        }catch(SQLException e){
            System.out.println("Wyj 3: "+e);
        }

        //       this.pokazTowar();


*/

        assert connection != null;
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
            ResultSet rs = statement.executeQuery("select * from napoje where nr_na_liście is not null");
            while (rs.next()) {
                int nr = rs.getInt(1);
                if(nr<l)
                    tabtym[nr] = new cNapoj(nr, rs.getString(2),rs.getDouble(3),rs.getInt(4));
            }
        }catch(SQLException e){
            System.out.println("Wyj (przeniesienie): " + e);
        }
        zamknijBaze(connection);


        return tabtym;
    }
}
