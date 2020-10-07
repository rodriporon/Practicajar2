package practicaa;

import java.awt.EventQueue;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class IniciarMenu extends Thread {
    
    public volatile boolean alive;
    public Grafica[] grafica;
    
    public IniciarMenu(Grafica[] grafica) {
        this.grafica = grafica;
        alive = true;
    }
    
    @Override
    public void run() {
        int i = 0;
        while (alive) {
            try {
                sleep(2000);
                int n = JOptionPane.showConfirmDialog(null, "¿Ordenar la gráfica?", "USAC Processing Data", JOptionPane.YES_NO_OPTION);
                if (n == JOptionPane.YES_OPTION) {
                    action();
                    
                } else if (n == JOptionPane.NO_OPTION) {
                } else {
                    
                }
                i++;
            } catch (Exception e) {
                
            }
            
        }
        
    }
    private void action() {
        EventQueue.invokeLater(new Runnable() {
            @Override
                public void run() {
                MenuOrdenar menu = new MenuOrdenar(grafica);
                }
        });
    }
    
}
