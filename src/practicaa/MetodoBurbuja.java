package practicaa;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import org.jfree.chart.*;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import static practicaa.Inicio.n;

public class MetodoBurbuja implements Runnable {

    JPanel bpanel;
    ChartPanel panel;
    JFreeChart barChart;
    public VentanaBurbuja ventana;
    public Grafica[] grafica;
    public static Float[] arreglo = new Float[n];
    public static String[] nombreX = new String[n];
    public static int posX, velocidad_;
    public static cronometro1 reloj;

    MetodoBurbuja(Grafica[] grafica, VentanaBurbuja ventana, int velocidad_) {

        this.velocidad_ = velocidad_;
        this.ventana = ventana;
        this.grafica = grafica;
        // crearGrafica();
        reloj = new cronometro1();
        for (int i = 1; i < grafica.length; i++) {
            arreglo[i] = Float.parseFloat(grafica[i].getEjeY());
            nombreX[i] = grafica[i].getEjeX();
        }

    }

    public void quicksort(Float[] datos, int inferior, int superior) {
        try {
            int izquierda, derecha;
            Float mitad, x;
            String y;
            izquierda = inferior;
            derecha = superior;
            mitad = datos[(izquierda + derecha) / 2];
            do {
                while (datos[izquierda] < mitad) {
                    izquierda++;
                }
                while (datos[derecha] > mitad) {
                    derecha--;
                }

                if (izquierda <= derecha) {
                    x = datos[izquierda];
                    datos[izquierda] = datos[derecha];
                    datos[derecha] = x;
                    
                    y = this.nombreX[izquierda];
                    this.nombreX[izquierda] = this.nombreX[derecha];
                    this.nombreX[derecha] = y;
                    
                    izquierda++;
                    derecha--;
                    VentanaBurbuja.pasos++;
                    Thread.sleep(velocidad_);
                    ventana.AgregarGrafico();

                }
            } while (izquierda <= derecha);

            if (inferior < derecha) {
                Thread.sleep(velocidad_);
                quicksort(datos, inferior, derecha);
                ventana.AgregarGrafico();
            }
            if (izquierda < superior) {
                Thread.sleep(velocidad_);
                quicksort(datos, izquierda, superior);
                ventana.AgregarGrafico();
            }
            ventana.AgregarGrafico();

        } catch (Exception e) {

        }
    }
    
    public void quicksortdesc(Float[] arreglo, int low, int high) {
            
            if (low < high) {
                int pi = partition(arreglo, low, high);
                quicksortdesc(arreglo, low, pi - 1);
                quicksortdesc(arreglo, pi + 1, high);
                

            }     
    }
            
    public int partition(Float arreglo[], int low, int high) {
        
        Float pivot = arreglo[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (arreglo[j] >= pivot) {
                i++;
                Float temp = arreglo[i];
                arreglo[i] = arreglo[j];
                arreglo[j] = temp;
                
                String y = this.nombreX[i];
                this.nombreX[i] = this.nombreX[j];
                this.nombreX[j] = y;
                
                try {
                    ventana.AgregarGrafico();
                    Thread.sleep(velocidad_);
                    VentanaBurbuja.pasos++;
                }catch (Exception e) {

                }
            }
        }
        Float temp = arreglo[i + 1];
        arreglo[i + 1] = arreglo[high];
        arreglo[high] = temp;
        
        String y = this.nombreX[i + 1];
        this.nombreX[i + 1] = this.nombreX[high];
        this.nombreX[high] = y;
        
                try {
                    ventana.AgregarGrafico();
                    Thread.sleep(velocidad_);
                }catch (Exception e) {

                }        

        return i + 1;

    }

    @Override
    public void run() {

        if (MenuOrdenar.algoritmo_.equals("Bubble Sort")) {
            if (MenuOrdenar.tipo_.equals("Ascendente")) {
                for (int i = 1; i < arreglo.length - 1; i++) {
                    for (int j = 1; j < arreglo.length - 1; j++) {
                        if (arreglo[j] > arreglo[j + 1]) {
                            Float tmp = arreglo[j + 1];
                            arreglo[j + 1] = arreglo[j];
                            arreglo[j] = tmp;

                            //titulos
                            String tem = nombreX[j + 1];
                            nombreX[j + 1] = nombreX[j];
                            nombreX[j] = tem;

                            VentanaBurbuja.pasos++;
                            try {
                                Thread.sleep(velocidad_);
                                ventana.AgregarGrafico();
                            } catch (Exception e) {

                            }
                        }

                    }
                }
                System.out.println("------------ORDENADO------------------");
                                reloj.stop();
                VentanaBurbuja.pasos = 0;
                ventana.crearReporte();
                ventana.crearImagen();

            } else if (MenuOrdenar.tipo_.equals("Descendente")) {
                for (int i = 1; i < arreglo.length - 1; i++) {
                    for (int j = 1; j < arreglo.length - 1; j++) {
                        if (arreglo[j] < arreglo[j + 1]) {

                            Float tmp = arreglo[j + 1];
                            arreglo[j + 1] = arreglo[j];
                            arreglo[j] = tmp;

                            //titulos
                            String tem = nombreX[j + 1];
                            nombreX[j + 1] = nombreX[j];
                            nombreX[j] = tem;

                            VentanaBurbuja.pasos++;

                            try {
                                Thread.sleep(velocidad_);
                                ventana.AgregarGrafico();
                            } catch (Exception e) {
                            }
                        }

                    }
                }
                System.out.println("------------ORDENADO------------------");
                                reloj.stop();
                VentanaBurbuja.pasos = 0;
                ventana.crearImagen();
                ventana.crearReporte();
            }
        } else if (MenuOrdenar.algoritmo_.equals("Shellsort")) {
            if (MenuOrdenar.tipo_.equals("Ascendente")) {

                int inta = arreglo.length, i;
                boolean band;
                while (inta > 0) {
                    inta = inta / 2;
                    band = true;
                    while (band) {
                        band = false;
                        i = 1;
                        while ((i + inta) <= arreglo.length - 1) {
                            if (arreglo[i] > arreglo[i + inta]) {
                                Float aux = arreglo[i];
                                arreglo[i] = arreglo[i + inta];
                                arreglo[i + inta] = aux;

                                String tem = nombreX[i];
                                nombreX[i] = nombreX[i + inta];
                                nombreX[i + inta] = tem;

                                band = true;
                                VentanaBurbuja.pasos++;
                                try {
                                    Thread.sleep(MenuOrdenar.velocidad_);
                                    ventana.AgregarGrafico();
                                } catch (Exception e) {

                                }
                            }
                            i = i + 1;
                        }
                    }
                }
                reloj.stop();
                ventana.crearImagen();
                VentanaBurbuja.pasos = 0;
                ventana.crearReporte();

            } else if (MenuOrdenar.tipo_.equals("Descendente")) {
                int inta = arreglo.length, i;
                boolean band;
                while (inta > 0) {
                    inta = inta / 2;
                    band = true;
                    while (band) {
                        band = false;
                        i = 1;
                        while ((i + inta) <= arreglo.length - 1) {
                            if (arreglo[i] < arreglo[i + inta]) {
                                Float aux = arreglo[i];
                                arreglo[i] = arreglo[i + inta];
                                arreglo[i + inta] = aux;

                                String tem = nombreX[i];
                                nombreX[i] = nombreX[i + inta];
                                nombreX[i + inta] = tem;

                                band = true;
                                VentanaBurbuja.pasos++;
                                try {
                                    Thread.sleep(MenuOrdenar.velocidad_);
                                    ventana.AgregarGrafico();
                                } catch (Exception e) {

                                }
                            }
                            i = i + 1;
                        }
                    }
                }
                reloj.stop();
                VentanaBurbuja.pasos = 0;
                ventana.crearImagen();
                ventana.crearReporte();
            }
        } else if (MenuOrdenar.algoritmo_.equals("Quicksort")) {
            if (MenuOrdenar.tipo_.equals("Ascendente")) {
                try {
                    int izq = 1;
                    int der = arreglo.length - 1;

                    this.quicksort(arreglo, izq, der);
                    ventana.AgregarGrafico();

                } catch (Exception e) {

                }

                reloj.stop();
                ventana.crearImagen();
                VentanaBurbuja.pasos = 0;
                ventana.crearReporte();
                
            } else if (MenuOrdenar.tipo_.equals("Descendente")) {
                    int low = 1;
                    int high = arreglo.length - 1;
                    
                    this.quicksortdesc(arreglo, low, high);
                    
                reloj.stop();
                VentanaBurbuja.pasos = 0;
                ventana.crearImagen();
                ventana.crearReporte();
            }
        }
    }
}
