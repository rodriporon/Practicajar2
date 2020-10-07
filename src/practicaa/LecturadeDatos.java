package practicaa;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class LecturadeDatos {
    
    public int n;
    public Inicio inicio = new Inicio();
    public String rutagrafica;

    public LecturadeDatos(String rutagrafica) {
        this.rutagrafica = rutagrafica;
        System.out.println(rutagrafica);
    }
    
    public int numeroFilas(){ 
        try {
                String ruta = rutagrafica;
                File archivo = new File(ruta);
                FileReader fr = new FileReader(archivo);
                BufferedReader br = new BufferedReader(fr);

                // Lectura del fichero
                String linea;
                while ((linea = br.readLine()) != null) {
                    String[] datos = linea.split("\\n");
                    n++;
                    System.out.println(n);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        return n;   
    }
    
    
}
