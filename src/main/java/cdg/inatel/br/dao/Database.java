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
    static final String password = "luc@S2017";
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
/*
    // =====> TRUNCATE TABLE <======= /
    public boolean truncateTable(String table){
        connect();
        String sql = "TRUNCATE ?;";
        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, table);
            pst.execute();
            check = true;
        } catch (SQLException e) {
            System.out.println("Erro de conexão!" + e.getMessage());
            check = false;
        }
        finally {
            try {
                connection.close();
                pst.close();
            } catch (SQLException e) {
                System.out.println("Erro de conexão!" + e.getMessage());
            }
        }

        return check;
    }

    // =====> INSERIR DADOS <=====
    public boolean insertUser(User user){
        connect();
        String sql = "INSERT INTO user(nome, cpf) VALUES (?,?)";
        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, user.getNome());
            pst.setString(2, user.getCpf());
            pst.execute();
            check = true;
        } catch (SQLException e) {
            System.out.println("Erro de conexão!" + e.getMessage());
            check = false;
        }
        finally {
            try {
                connection.close();
                pst.close();
            } catch (SQLException e) {
                System.out.println("Erro de conexão!" + e.getMessage());
            }
        }
        return check;
    }

    // =====> BUSCANDO DADOS <=====
    public ArrayList<User> searchUser(){
        connect();
        ArrayList<User> users = new ArrayList<>();
        String sql = "SELECT * FROM user;";
        try {
            statement = connection.createStatement();
            result = statement.executeQuery(sql);
            while(result.next()){
                User userTemp = new User(result.getString("nome"), result.getString("cpf"));
                userTemp.id = result.getInt("id");
                System.out.println("ID = " + userTemp.id);
                System.out.println("Nome = " + userTemp.getNome());
                System.out.println("CPF = " + userTemp.getCpf());
                System.out.println("---------------------------------");
                users.add(userTemp);
            }
        }catch(SQLException e){
            System.out.println("Erro de operação: " + e.getMessage());
        }
        finally {
            try{
                connection.close();
                statement.close();
                result.close();
            } catch (SQLException e){
                System.out.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
        return users;
    }


    // =====> ATUALIZANDO DADOS <=====
    public boolean updateUser(int id, String nome){
        connect();
        String sql = "UPDATE user SET nome = ? WHERE id = ?;";
        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, nome);
            pst.setInt(2,id);
            pst.execute();
            check = true;
        } catch (SQLException e) {
            System.out.println("Erro de conexão!" + e.getMessage());
            check = false;
        }
        finally {
            try {
                connection.close();
                pst.close();
            } catch (SQLException e) {
                System.out.println("Erro de conexão!" + e.getMessage());
            }
        }
        return check;
    }

    // =====> DELETANDO DADOS <=====
    public boolean deleteUser(int id){
        connect();
        String sql = "DELETE from user WHERE id = ?;";
        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1,id);
            pst.execute();
            check = true;
        } catch (SQLException e) {
            System.out.println("Erro de conexão!" + e.getMessage());
            check = false;
        }
        finally {
            try {
                connection.close();
                pst.close();
            } catch (SQLException e) {
                System.out.println("Erro de conexão!" + e.getMessage());
            }
        }
        return check;
    }*/
}
