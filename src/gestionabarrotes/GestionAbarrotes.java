
package gestionabarrotes;

import com.formdev.flatlaf.FlatLightLaf;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class GestionAbarrotes {

    public static void main(String[] args) {
        try {
            
            //Configura el estilo de toda la aplicación antes de crear
            // los componentes graficos
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        }

        // Crea la interfaz en el hilo seguro de Swing EDT
        SwingUtilities.invokeLater(() -> {
            Index interfaz = null;
            try {
                interfaz = new Index();
            } catch (IOException ex) {
                Logger.getLogger(GestionAbarrotes.class.getName()).log(Level.SEVERE, null, ex);
            }
            interfaz.setLocationRelativeTo(null);
            interfaz.setVisible(true);
        });

    }
    
}
