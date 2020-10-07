package practicaa;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

public class Inicio extends JFrame implements ActionListener {

    
    public Float[] dataOrdenada;
    public static Grafica[] grafica;
    public static String rutagrafica, titulografica;
    public static int n, contador = 0;
    public String[] tituloX;
    BufferedImage image;
    JPanel bpanel, panelbarras;
    JButton buscar, aceptar;
    JTextField ruta, titulo;
    ChartPanel panel;
    
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    JFreeChart barChart;

    public Inicio() {

        crearVentana();
        componentes();
        //crearGrafica();
    }

    public void crearVentana() {

        this.setSize(1200, 900);
        this.setLocationRelativeTo(null);
        this.setTitle("USAC Processing Data");
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
    }

    public void componentes() {
        
        JLabel t1 = new JLabel("Ruta del archivo:");
        t1.setBounds(20, 20, 100, 20);
        this.add(t1);

        ruta = new JTextField();
        ruta.setBounds(20, 40, 400, 20);
        this.add(ruta);

        buscar = new JButton("Buscar");
        buscar.setBounds(460, 40, 100, 20);
        buscar.addActionListener(this);
        this.add(buscar);

        JLabel t2 = new JLabel("Título de la gráfica:");
        t2.setBounds(20, 80, 250, 20);
        this.add(t2);

        titulo = new JTextField();
        titulo.setBounds(20, 100, 400, 20);
        this.add(titulo);

        aceptar = new JButton("Aceptar");
        aceptar.setBounds(460, 100, 100, 20);
        aceptar.addActionListener(this);
        this.add(aceptar);

    }
    
    public void recorrerEjes() {
        for (int i = 0; i < grafica.length; i++) {
            System.out.print("Eje X: " + grafica[i].getEjeX());
            System.out.print(" Eje Y: " + grafica[i].getEjeY());
            System.out.println();
        }
    }
    

    @Override
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == buscar) {
            JFileChooser fc = new JFileChooser();
            int op = fc.showOpenDialog(this);
            if (op == JFileChooser.APPROVE_OPTION) {
                rutagrafica = fc.getSelectedFile().toString();
                System.out.println(rutagrafica);
                ruta.setText(rutagrafica);
            }
        } else if (ae.getSource() == aceptar) {
            
            LecturadeDatos ld = new LecturadeDatos(rutagrafica); //Llamamos a la función que devuelve el número líneas en el archivo CSV.
            this.n = ld.numeroFilas();
            
            try {
                grafica = new Grafica[n];
                String ruta = rutagrafica;
                File archivo = new File(ruta);
                FileReader fr = new FileReader(archivo);
                BufferedReader br = new BufferedReader(fr);

                // Lectura del fichero
                String linea;
                while ((linea = br.readLine()) != null) {
                    String[] datos = linea.split(",");
                    String ejeX = datos[0];
                    String ejeY = datos[1];
                    grafica[contador] = new Grafica();
                    grafica[contador].setEjeX(ejeX);
                    grafica[contador].setEjeY(ejeY);
                    contador++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            crearGrafica();
            IniciarMenu iniciar = new IniciarMenu(grafica);
            iniciar.start();
            try {
                Thread.sleep(100);
            } catch(InterruptedException ie) {
                System.out.println(ie.getMessage());
            }
            iniciar.alive = false;
            iniciar = null;
            Float[] arreglo = new Float[this.n];
            for (int i = 1; i < grafica.length; i++) {
                arreglo[i] = Float.parseFloat(grafica[i].getEjeY());
                System.out.println(arreglo[i]);
            }
            /*
            MetodoBurbuja metodoburbuja = new MetodoBurbuja();
            dataOrdenada = new Float [n]; //en dataOrdenada se guardan los datos ordenados por el
            //método burbuja.
            dataOrdenada = metodoburbuja.Burbuja(arreglo);
            for (int i = 1; i < dataOrdenada.length; i++) {
                System.out.println(dataOrdenada[i]);
            }
*/
        }
    }
    
    
    public void crearGrafica() {
        titulografica = titulo.getText();
        tituloX = new String[grafica.length];
        for (int i = 1; i < grafica.length; i++) {
            tituloX[i] = grafica[i].getEjeX();
        }
        for (int i = 1; i < grafica.length; i++) {
            System.out.println("Los datos son: " + grafica[i].getEjeX());
            dataset.addValue(Float.parseFloat(grafica[i].getEjeY()), "Datos", grafica[i].getEjeX());
        }
        barChart = ChartFactory.createBarChart3D(titulografica, grafica[0].getEjeX(), grafica[0].getEjeY(),
                dataset, PlotOrientation.VERTICAL, true, true, false);
        
        image = barChart.createBufferedImage(1160,650);
                try {
            File outputfile = new File("Reportes/Imagenes/GraficaDes.png");
            ImageIO.write(image, "png", outputfile);
        }catch (Exception e) {
            
        }
        panel = new ChartPanel(barChart);
        bpanel = new JPanel();
        panel.setBounds(0, 0, 1160, 650);
        this.getContentPane().add(bpanel);
        bpanel.setLayout(null);
        bpanel.setBounds(20, 150, 1160, 650);
        bpanel.add(panel);
        
        
    }
}
