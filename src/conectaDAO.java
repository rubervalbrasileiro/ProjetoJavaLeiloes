
import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;



/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */
public class conectaDAO {
    public Connection conn;
    
    
    public Connection connectDB(){
        Connection conn = null;
        
        try {
        
            conn = DriverManager.getConnection("jdbc:mysql://localhost/uc11?user=root&password=#senac9865");
            
        } catch (SQLException erro){
            JOptionPane.showMessageDialog(null, "Erro ConectaDAO" + erro.getMessage());
        }
        return conn;
    }
    
    public Connection getConn(){
        return conn;
    }
    
    public void desconectar(Connection conn, Statement stmt) {
        try{
            conn.close();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Não foi possível se conectar ao banco de dados");
        }
    }
}
