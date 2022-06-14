package cdg.inatel.br.dao;
import java.sql.*;


public abstract class Database {
    //
    static Connection connection;
    static Statement statement;
    static ResultSet result;
    static PreparedStatement pst;

    //user pass db
    static final String user = "root";
    static final String password = "root";
    static final String database = "backdonalds";

    //URL
    static final String url = "jdbc:mysql://localhost:3306/" + database + "?useTimezone=true&serverTimezone=UTC&useSSL=false";

    // =====> connect <=====
    public static void connect(){
        try {
            connection = DriverManager.getConnection(url,user,password);

        } catch (SQLException e) {
            System.out.println("Erro de conexão!" + e.getMessage());
        }
    }

    public static void closeAllSql(){
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
