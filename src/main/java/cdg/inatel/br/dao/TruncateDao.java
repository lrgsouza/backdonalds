package cdg.inatel.br.dao;

import java.sql.SQLException;

public class TruncateDao extends Database{

    private Boolean check = false;
    public boolean truncateTable(String table){
        connect();
        String sql = "TRUNCATE backdonalds." + table + ";";
        try {
            pst = connection.prepareStatement(sql);
            //pst.setString(1, table);
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
}
