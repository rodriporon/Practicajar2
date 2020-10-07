
package practicaa;
import java.awt.Font;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import static practicaa.VentanaBurbuja.t8;

public class cronometro1 extends Thread {
    
    public static int minutos = 0;
    public static int segundos = 0;
    public static int milisegundos = 0;
    JLabel lblCronos;
    boolean x =false;
    
    private JLabel cronometro;
    
    
    public cronometro1() {
        this.start();
    }

    public void reset() {
        this.milisegundos = 0;
        this.segundos = 0;
        this.minutos = 0;
        
        t8.setText(minutos + " : " + segundos + " : " + milisegundos);
        
    }

    public void run() {
        while(true)
        {
            // Sera un metodo que siempre esta corriendo
            try{
                // Se hace un Sleep de ese proceso, pero ese proceso no esta haciendo nada
                // visual por lo tanto no afecta en nada
                // Guardamos las milesimas que van transcurriendo
            //Thread.sleep(5);
            //milisegundos += 5;
            milisegundos+= 20;
            // Antes de repetir el ciclo verificamos,
            // RECUERDEN: 1000 milesimas -> 1 segundo
            if(milisegundos == 1000)
               {
                   segundos++;
                   milisegundos = 0;
            } if (segundos == 59) {
                        minutos++;
                        segundos = 0;
            }
            Thread.sleep(18);
            t8.setText(minutos + " : " + segundos + " : " + milisegundos);
            
            
            // Imprimimos el tiempo que va corriendo
            System.out.println("Minutos: "+minutos +"Segundos: " + segundos +" Milesimas: " + milisegundos);    
            }catch(Exception e)
            {
                
            }
            
        }
    } 
    
    
    
}
