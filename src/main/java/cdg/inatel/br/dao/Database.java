package cdg.inatel.br.dao;
import java.sql.*;


public abstract class Database {
    //
    Connection connection;
    Statement statement;
    ResultSet result;
    PreparedStatement pst;

    //user pass db
    static final String user = "root";
    static final String password = "root";
    static final String database = "backdonalds";

    //URL
    static final String url = "jdbc:mysql://localhost:3306/" + database + "?useTimezone=true&serverTimezone=UTC&useSSL=false";
    private boolean check = false;

    // =====> connect <=====
    public void connect(){
        try {
            connection = DriverManager.getConnection(url,user,password);

        } catch (SQLException e) {
            System.out.println("Erro de conexão!" + e.getMessage());
        }
    }

    public void closeAllSql(){
        try{
            if(connection!=null)
                connection.close();
            if(statement!=null)
                statement.close();
            if(result!=null)
                result.close();
            if(pst!=null)
                pst.close();
        } catch (SQLException e){
            System.out.println("Erro ao fechar conexão: " + e.getMessage());
        }
    }
}
