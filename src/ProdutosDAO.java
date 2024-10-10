
/**
 *
 * @author Adm
 */
import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;

public class ProdutosDAO {

    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();

    public void cadastrarProduto(ProdutosDTO produto) {

        String sql = "INSERT INTO produtos(nome, valor, status) VALUES(?,?, ?) ";
        conn = new conectaDAO().connectDB();

        try {
            prep = conn.prepareStatement(sql);
            prep.setString(1, produto.getNome());
            prep.setInt(2, produto.getValor());
            prep.setString(3, produto.getStatus());

            prep.execute();

            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar produto" + e.getMessage());
        } finally {
            // Fecha os recursos no bloco finally
            try {
                if (prep != null & conn != null) {
                    prep.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao fechar conexão: " + e.getMessage());
            }
        }
    }

    public ArrayList<ProdutosDTO> listarProdutos() {
        String sql = "SELECT * FROM produtos";
        conn = new conectaDAO().connectDB();
        
        try{
            prep = conn.prepareStatement(sql);
            resultset = prep.executeQuery();
            
            while (resultset.next()){
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(resultset.getInt("id"));
                produto.setNome(resultset.getString("nome"));
                produto.setValor(resultset.getInt("valor"));
                produto.setStatus(resultset.getString("status"));
                listagem.add(produto);
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Erro ao listar produtos" + e.getMessage());
        }finally{
            try{
                if(resultset != null){
                    resultset.close();
                }
                if(prep != null){
                    prep.close();
                }
                if(conn != null){
                    conn.close();
                }
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, "Erro ao fechar conexão" + e.getMessage());
            }
        }
        return listagem;
    }
    
    public void venderProduto(int idProduto) {
        conn = new conectaDAO().connectDB();
        PreparedStatement stmt = null;
        
        String sql = "UPDATE produtos SET status = ? WHERE id = ?";
        
        try{
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, "Vendido");
            stmt.setInt(2, idProduto);
            
            stmt.execute();
            JOptionPane.showMessageDialog(null, "Produto Vendido con sucesso!");
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Erro ao vender o produto" + e.getMessage());
        }finally{
            try{
                if(stmt != null){
                stmt.close();
            }
                if(conn != null){
                    conn.close();
                }
            }catch(SQLException e){
                
            }
        }
    }
    
    public ArrayList<ProdutosDTO> listarVendas(){
        String sql = "SELECT * FROM produtos WHERE status = 'Vendido'";
        conn = new conectaDAO().connectDB();
        ArrayList<ProdutosDTO> listagemVendas = new ArrayList<>();
        
        try{
            prep = conn.prepareStatement(sql);
            resultset = prep.executeQuery();
            
            while(resultset.next()){
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(resultset.getInt("id"));
                produto.setNome(resultset.getString("nome"));
                produto.setValor(resultset.getInt("valor"));
                produto.setStatus(resultset.getString("status"));
                
                listagemVendas.add(produto);
            }
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Erro ao listar vendas:" + e.getMessage());
        }finally{
            try{
                if(resultset != null){
                    resultset.close();
                }
                if(conn != null){
                    conn.close();
                }
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, "Erro ao fechar conexão." + e.getMessage());
            }
        }
        return listagemVendas;
    }
    
    public ProdutosDTO buscarProdutoPorId(int id){
        String sql = "SELECT * FROM produtos WHERE id =?";
        conn = new conectaDAO().connectDB();
        try{
            prep = conn.prepareStatement(sql);
            prep.setInt(1, id);
            resultset = prep.executeQuery();
            
            if(resultset.next()){
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(resultset.getInt("id"));
                produto.setNome(resultset.getString("nome"));
                produto.setValor(resultset.getInt("valor"));
                produto.setStatus(resultset.getString("status"));
                return produto;
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Erro ao buscar o produto!");
        }finally{
            try{
                if(resultset != null){
                    resultset.close();
                }
                if(conn != null){
                    conn.close();
                }
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, "Erro ao fechar a conexão!" + e.getMessage());
            }
        }
        return null;
    }

}
