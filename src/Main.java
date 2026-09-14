
import javax.swing.JOptionPane;

public class Main {
    
     public static void main(String[] args) {
            
         conectaDAO c = new conectaDAO();
            c.connectDB();
            if (c.conn!= null) {
              JOptionPane.showMessageDialog(null, "Conexão bem sucedida!", "Sucesso", 
                      JOptionPane.INFORMATION_MESSAGE);
            }
            
            
         java.awt.EventQueue.invokeLater(()-> {
             new cadastroVIEW().setVisible(true);
    
    });
    
    }
}
