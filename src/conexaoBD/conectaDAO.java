
import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Adm
 */
public class conectaDAO {

    public Connection conn;
    

    private final String url = "jdbc:mysql://localhost:3306/uc11?useSSL=false";
    private final String user = "root";
    private final String password = "#SENAC986525";

    Connection connectDB(){
        try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection(url, user, password);
        System.out.println("Conexão realizada com sucesso");
        return conn;
        
    }catch(ClassNotFoundException | SQLException ex){
            System.out.println("Falha na conexao com o banco de dados" + ex.getMessage());
            return null;
        }
    }

    public Connection getConn() {
        if(conn == null){
            conn = connectDB();
        }
        return conn;
    }

    public void desconectar(Connection conn, Statement stmt) {
        try {
            if (stmt != null) stmt.close();  
            if (conn != null) conn.close();  
            System.out.println("Desconexão realizada com sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Não foi possível se conectar ao banco de dados");
        }
    }
}
