
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class conectaDAO {
    
    Connection conn = null;
          
    public Connection connectDB(){
       
       
        try {
        
            conn = DriverManager.getConnection("jdbc:mysql://localhost/leiloestdsat", "root", "320185");
            
        } catch (SQLException erro){
            JOptionPane.showMessageDialog(null, "Erro ConectaDAO" + erro.getMessage());
        }
        return conn;
    }

public void desconectar() {
      
    try {
        
            if(conn != null && !conn.isClosed()) {
                conn.close();
               // JOptionPane.showMessageDialog(null,"Desconectado com sucesso!");
                
            }
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Erro ao desconectar");
        }
    
}
}
