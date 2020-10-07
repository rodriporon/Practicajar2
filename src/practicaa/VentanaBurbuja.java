package practicaa;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import java.io.PrintWriter;
import static practicaa.cronometro1.milisegundos;
import static practicaa.cronometro1.minutos;
import static practicaa.cronometro1.segundos;



public class VentanaBurbuja extends JFrame implements Runnable {
    
    public int posX;
    public Grafica[] grafica;
    JPanel bpanel;
    ChartPanel panel;
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    JFreeChart barChart; //grafico barras
    Thread hilo1, hilo2;
    JLabel t1, t2, t3, t4, t5, t6, t7, t9, t10;
    public static JLabel t8;
    BufferedImage image = new BufferedImage(1160, 650, BufferedImage.TYPE_INT_RGB);
    public int velocidad_;
    public String[] nombreEjeX;
    public static int pasos = 0;
    

   
   public VentanaBurbuja(Grafica[] grafica, int velocidad_) {
        
        this.velocidad_ = velocidad_;
        this.grafica = grafica;
        this.setSize(1200, 900);
        this.setLocationRelativeTo(null);
        this.setTitle("Proceso de Ordenamiento");
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        
        barChart = ChartFactory.createBarChart3D(Inicio.titulografica, grafica[0].getEjeX(), grafica[0].getEjeY(),
        dataset, PlotOrientation.VERTICAL, true, true, false);
        panel = new ChartPanel(barChart);
        bpanel = new JPanel();

        panel.setBounds(0, 0, 1160, 650);
        this.getContentPane().add(bpanel);
        bpanel.setLayout(null);
        bpanel.setBounds(20, 150, 1160, 650);
        bpanel.add(panel);
        
        t1 = new JLabel("Algoritmo:");
        t1.setBounds(20,20,150,20);
        this.add(t1);
        
        t2 = new JLabel(MenuOrdenar.algoritmo_);
        t2.setBounds(110,20,150,20);
        this.add(t2);
        
        t3 = new JLabel("Velocidad:");
        t3.setBounds(20,60,150,20);
        this.add(t3);
        
        t4 = new JLabel();
        t4.setBounds(110,60,150,20);
        this.add(t4);
        
        t5 = new JLabel("Orden:");
        t5.setBounds(20,100,150,20);
        this.add(t5);
        
        t6 = new JLabel(MenuOrdenar.tipo_);
        t6.setBounds(110,100,150,20);
        this.add(t6);
        
        t7 = new JLabel("Tiempo:");
        t7.setBounds(400,20,150,20);
        this.add(t7);
        
        t8 = new JLabel();
        t8.setBounds(490,20,150,20);
        this.add(t8);
        
        t9 = new JLabel("Pasos: ");
        t9.setBounds(400,60,150,20);
        this.add(t9);
        
        t10 = new JLabel();
        t10.setBounds(490,60,150,20);
        this.add(t10);
        
        if (velocidad_ == 100) {
           t4.setText("Alta");
        } else if (velocidad_ == 450) {
           t4.setText("Media");
        } else if (velocidad_ == 1000) {
           t4.setText("Baja");
        }
        
        MetodoBurbuja metodoburbuja = new MetodoBurbuja(grafica, this, velocidad_);
        
        hilo1 = new Thread(metodoburbuja);
        hilo1.start();
        
        hilo2 = new Thread(this);
        hilo2.start();

    }
    
    public void AgregarGrafico() {
        t10.setText(String.valueOf(pasos));
        dataset.clear();
        for (int i = 1; i < MetodoBurbuja.arreglo.length; i++) {
            //System.out.println(grafica[i].getEjeX());
            dataset.setValue(MetodoBurbuja.arreglo[i], "Datos", MetodoBurbuja.nombreX[i]);
        }

        
    }
    
    public void crearImagen() {
        image = barChart.createBufferedImage(1160,650);
        try {
            File outputfile = new File("Reportes/Imagenes/GraficaOrd.png");
            ImageIO.write(image, "png", outputfile);
        }catch (Exception e) {
            
        }
        
    }
    
    public void crearReporte() {
        try {
            String ruta = "Reportes/Reporte.html";
            PrintWriter writer = new PrintWriter(ruta, "UTF-8");
        writer.println("<!DOCTYPE HTML>");
        writer.println("<html>");
	writer.println("<head>");
		writer.println("<title>Metodos de Ordenamiento</title>");
		writer.println("<meta charset=\"utf-8\" />");
		writer.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1, user-scalable=no\" />");
		writer.println("<meta name=\"description\" content=\"\" />");
		writer.println("<meta name=\"keywords\" content=\"\" />");
		writer.println("<link rel=\"stylesheet\" href=\"assets/css/main.css\" />");
	writer.println("</head>");
	writer.println("<body class=\"is-preload\">");
                writer.println("<header id=\"header\">");
                
                
			writer.println("<a class=\"logo\" href=\"Reportes/ReporteCifrado.html\">Reporte Ordenamiento</a>");
         
                        
                        
                writer.println("</header>");
                writer.println("<section id=\"banner\">");
				writer.println("<div class=\"inner\">");
					writer.println("<h1>MANEJO DE HILOS</h1>");
					writer.println("<p>Gráfica ordenadas e implementada utilizando hilos en java. <br />");
					writer.println("</div>");
				writer.println("<video autoplay loop muted playsinline src=\"Imagenes/banner2.mp4\"></video>");
			writer.println("</section>");
                        
            writer.println("<section class = \"wrapper\">");
                writer.println("<div class = \"inner\">");
                    writer.println("<header class = \"special\">");

            writer.println("<p>");
                    writer.println("ALGORITMO: " + MenuOrdenar.algoritmo_);
                    writer.println("</p>");
                            if (velocidad_ == 100) {
                                writer.println("<p>");
                               writer.println("VELOCIDAD: Alta");
                               writer.println("</p>");

        } else if (velocidad_ == 450) {
            writer.println("<p>");
           writer.println("VELOCIDAD: Media");
           writer.println("</p>");
        } else if (velocidad_ == 1000) {
            writer.println("<p>");
           writer.println("VELOCIDAD: Baja");
           writer.println("</p>");
        }
                    writer.println("<p>");        
                    writer.println("ORDEN: " + MenuOrdenar.tipo_);
                    writer.println("</p>");
                    writer.println("<p>");
                    writer.println("TIEMPO: " + minutos + " : " + segundos + " : " + milisegundos);
                    writer.println("</p>");
                    writer.println("<p>");
                    writer.println("PASOS: " + t10.getText());
                    writer.println("</p>");
            
            writer.println("<div class = \"table-wrapper\">");
            writer.println("<table class = \"alt\">");
            writer.println("<tbody>");

                writer.println("<tr>");
                
                writer.println("<th></th>");
                
                    writer.println("<th colspan = \"2\"> Dato Mínimo </th>");
                    writer.println("<th colspan = \"2\"> Dato Máximo </th>");
                    
                    writer.println("</tr>");
                    
                    writer.println("<tr>");
                    
                    writer.println("<th></th>");
                    
                    if (MenuOrdenar.tipo_.equals("Ascendente")) {
                                    writer.println("<th>"+ MetodoBurbuja.nombreX[1] + "</th>");
                    writer.println("<th>"+ MetodoBurbuja.arreglo[1] + "</th>");
                    writer.println("<th>"+ MetodoBurbuja.nombreX[MetodoBurbuja.arreglo.length - 1] + "</th>");
                    writer.println("<th>"+ MetodoBurbuja.arreglo[MetodoBurbuja.arreglo.length - 1] + "</th>");
            } else if (!MenuOrdenar.tipo_.equals("Ascendente")) {
                    writer.println("<th>"+ MetodoBurbuja.nombreX[MetodoBurbuja.arreglo.length - 1] + "</th>");
                    writer.println("<th>"+ MetodoBurbuja.arreglo[MetodoBurbuja.arreglo.length - 1] + "</th>");
                    writer.println("<th>"+ MetodoBurbuja.nombreX[1] + "</th>");
                    writer.println("<th>"+ MetodoBurbuja.arreglo[1] + "</th>");
                
            }
       
            writer.println("</tr>");   
            
            writer.println("</tbody>");
            writer.println("</table>");
            writer.println("</div>");
            
            
            writer.println("<h1>");
            writer.println("DATOS DESORDENADOS");
            writer.println("</h1>");
            writer.println("<p>");
            
            writer.println("<div class = \"table-wrapper\">");
            writer.println("<table class = \"alt\">");
            writer.println("<tbody>");
            
            writer.println("</tr>");
            writer.println("<td>" + Inicio.grafica[0].getEjeX() + "</td>");
            for (int i = 1; i < Inicio.grafica.length; i++) {
                writer.println("<td>" + Inicio.grafica[i].getEjeX() + "</td>");
            }
            writer.println("</tr>");
            writer.println("<tr>");
            writer.println("<td>" + Inicio.grafica[0].getEjeY() + "</td>");
            for (int i = 1; i < Inicio.grafica.length; i++) {
                writer.println("<td>" + Inicio.grafica[i].getEjeY() + "</td>");
            }
            writer.println("</tr>");
            
            writer.println("</tbody>");
            writer.println("</table>");
            writer.println("</div>");
            
            writer.println("<img src=\"Imagenes/GraficaDes.png\">");

            writer.println("<h1>");
            writer.println("DATOS ORDENADOS");
            writer.println("</h1>");
            writer.println("<p>");
            
            writer.println("<div class = \"table-wrapper\">");
            writer.println("<table class = \"alt\">");
            writer.println("<tbody>");
            
            writer.println("</tr>");
            writer.println("<td>" + Inicio.grafica[0].getEjeX() + "</td>");
            for (int i = 1; i < MetodoBurbuja.nombreX.length; i++) {
                writer.println("<td>" + MetodoBurbuja.nombreX[i] + "</td>");
            }
            writer.println("</tr>");
            writer.println("<tr>");
            writer.println("<td>" + Inicio.grafica[0].getEjeY() + "</td>");
            for (int i = 1; i < MetodoBurbuja.arreglo.length; i++) {
                writer.println("<td>" + MetodoBurbuja.arreglo[i] + "</td>");
            }
            writer.println("</tr>");
            
            writer.println("</tbody>");
            writer.println("</table>");
            writer.println("</div>");
            
            
                        writer.println("<img src=\"Imagenes/GraficaOrd.png\">");
           
            
            writer.println("</header>");
            writer.println("</div>");
            writer.println("</section>");
           
            
            writer.println("<footer id=\"footer\">");
		writer.println("<div class=\"inner\">");
                    writer.println("<div class=\"content\">");
			writer.println("<section>");
                            writer.println("<h3>Rodrigo Antonio Porón De León 201902781</h3>");
                                writer.println("<p>Laboratorio de Introducción a la Programación y Computación</p>");
                                writer.println("<p>Sección D</p>");
			writer.println("</section>");
                writer.println("</div>");
                writer.println("</div>");
            writer.println("</footer>");
            
            
            			writer.println("<script src=\"assets/js/jquery.min.js\"></script>");
			writer.println("<script src=\"assets/js/browser.min.js\"></script>");
			writer.println("<script src=\"assets/js/breakpoints.min.js\"></script>");
			writer.println("<script src=\"assets/js/util.js\"></script>");
			writer.println("<script src=\"assets/js/main.js\"></script>");
            writer.println("</body>");
            writer.println("</html>");
            
            
            writer.close();
        
        }catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    
    @Override
    public void run() {
        
    }
    
}
