import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class ProdutosDAO {

    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
        
    public boolean cadastrarProduto(ProdutosDTO produto) {

        conectaDAO conexao = new conectaDAO();

        try {
            conn = conexao.connectDB();

            String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";

            prep = conn.prepareStatement(sql);
            
            prep.setString(1, produto.getNome());
            prep.setInt(2, produto.getValor());
            prep.setString(3, produto.getStatus());
            prep.execute();
            
            return true;
            
        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null,"Erro ao cadastrar produto: " + e.getMessage(),"Atenção",
                    JOptionPane.WARNING_MESSAGE
            );

        } finally {

            conexao.desconectar();

        }

        return false;
    }

    //vender produto
    
    public boolean venderProduto(int id) {

    conectaDAO conexao = new conectaDAO();

    try {
        conn = conexao.connectDB();

        String sql = "UPDATE produtos SET status = 'Vendido' WHERE id = ?";

        prep = conn.prepareStatement(sql);
        prep.setInt(1, id);

        prep.executeUpdate();

        return true;

    } catch (SQLException e) {

        JOptionPane.showMessageDialog(null,"Erro ao vender produto: " + e.getMessage(),"Atenção",
                JOptionPane.WARNING_MESSAGE
        );
        return false;

    } finally {
        conexao.desconectar();

    }
}
    
    public ArrayList<ProdutosDTO> listarProdutos() {
        
    conectaDAO conexao = new conectaDAO();
        listagem = new ArrayList<>();
        
        try {
         conn = conexao.connectDB();
       
        String sql = "SELECT id, nome, valor, status FROM produtos";

            
            prep = conn.prepareStatement(sql);
            resultset = prep.executeQuery();

            while (resultset.next()) {

                ProdutosDTO produto = new ProdutosDTO();

                produto.setId(resultset.getInt("id"));
                produto.setNome(resultset.getString("nome"));
                produto.setValor(resultset.getInt("valor"));
                produto.setStatus(resultset.getString("status"));

                listagem.add(produto);
            }

        } catch (SQLException e) {
             
           JOptionPane.showMessageDialog(null,"Erro ao listar produtos: " + e.getMessage(),"Atenção",
                    JOptionPane.WARNING_MESSAGE
            );
        
        } finally {

            conexao.desconectar();

        }

        return listagem; 
    }
    
    public ArrayList<ProdutosDTO> listarVendidos() {

        conectaDAO conexao = new conectaDAO();
        ArrayList<ProdutosDTO> vendidos = new ArrayList<>();

        try {
            conn = conexao.connectDB();

            String sql = "SELECT id, nome, valor, status FROM produtos WHERE status = 'Vendido'";
            prep = conn.prepareStatement(sql);
            resultset = prep.executeQuery();

            while (resultset.next()) {

                ProdutosDTO produto = new ProdutosDTO();

                produto.setId(resultset.getInt("id"));
                produto.setNome(resultset.getString("nome"));
                produto.setValor(resultset.getInt("valor"));
                produto.setStatus(resultset.getString("status"));

                vendidos.add(produto);
            }

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, "Erro ao listar produtos vendidos: " + e.getMessage(),
                    "Atenção",JOptionPane.WARNING_MESSAGE
            );

        } finally {

            conexao.desconectar();

        }

        return vendidos;
    }
    
    // update
public boolean atualizarProduto(ProdutosDTO produto) {

    conectaDAO conexao = new conectaDAO();

    try {
        conn = conexao.connectDB();

        String sql = "UPDATE produtos SET nome = ?, valor = ?, status = ? WHERE id = ?";

        prep = conn.prepareStatement(sql);
        prep.setString(1, produto.getNome());
        prep.setInt(2, produto.getValor());
        prep.setString(3, produto.getStatus());
        prep.setInt(4, produto.getId());

        prep.execute();

        return true;

    } catch (SQLException e) {

        JOptionPane.showMessageDialog(null, "Erro ao atualizar produto: " + e.getMessage(), "Atenção",
                JOptionPane.WARNING_MESSAGE
        );

        return false;

    } finally {

        conexao.desconectar();

    }
}
}