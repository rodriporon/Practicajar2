package practicaa;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class MenuOrdenar extends JFrame implements ActionListener {
    
    public Grafica[] grafica;
    public static int velocidad_;
    public static String algoritmo_, tipo_;
    
    ButtonGroup tipo, velocidad, algoritmo;
    JLabel l1, l2, l3, l4;
    JRadioButton r1, r2, r3, r4, r5, r6, r7, r8;
    JButton cancelar, ordenar;
    
    MenuOrdenar(Grafica[] grafica){
        this.grafica = grafica;
        crearVentana();
        inicializarComponentes();
    }

    
    public void crearVentana() {

        this.setSize(600, 450);
        this.setLocationRelativeTo(null);
        this.setTitle("Opciones de Ordenamiento");
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
    }
    
    public void inicializarComponentes() {
        
        l1 = new JLabel("Tipo de Ordenamiento");
        l1.setBounds(20,20,300,20);
        this.add(l1);
        
        l2 = new JLabel("Velocidad de Ordenamiento");
        l2.setBounds(20,150,300,20);
        this.add(l2);
        
        l3 = new JLabel("Algoritmo de Ordenamiento");
        l3.setBounds(330,150,300,20);
        this.add(l3);

        r1 = new JRadioButton("Ascendente");
        r1.setBounds(60,60,100,20);
        this.add(r1);
        
        r2 = new JRadioButton("Descendente");
        r2.setBounds(350,60,100,20);
        this.add(r2);
        
        tipo = new ButtonGroup();
        tipo.add(r1);
        tipo.add(r2);
        
        r3 = new JRadioButton("Baja");
        r3.setBounds(40,190,80,20);
        this.add(r3);
        
        r4 = new JRadioButton("Media");
        r4.setBounds(40,220,80,20);
        this.add(r4);
        
        r5 = new JRadioButton("Alta");
        r5.setBounds(40,250,80,20);
        this.add(r5);
        
        velocidad = new ButtonGroup();
        velocidad.add(r3);
        velocidad.add(r4);
        velocidad.add(r5);
        
        r6 = new JRadioButton("Bubble Sort");
        r6.setBounds(350, 190, 120, 20);
        this.add(r6);
        
        r7 = new JRadioButton("Quicksort");
        r7.setBounds(350, 220, 120, 20);
        this.add(r7);
        
        r8 = new JRadioButton("Shellsort");
        r8.setBounds(350, 250, 120, 20);
        this.add(r8);
        
        algoritmo = new ButtonGroup();
        algoritmo.add(r6);
        algoritmo.add(r7);
        algoritmo.add(r8);
        
        cancelar = new JButton("Cancelar");
        cancelar.setBounds(250,320,150,30);
        cancelar.addActionListener(this);
        this.add(cancelar);
        
        ordenar = new JButton("Aceptar");
        ordenar.setBounds(420,320,140,30);
        ordenar.addActionListener(this);
        this.add(ordenar);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == cancelar) {
            this.dispose();
        } else if (ae.getSource() == ordenar) {
            if (r6.isSelected() == true) {
                algoritmo_ = "Bubble Sort";
            } else if (r7.isSelected() == true) {
                algoritmo_ = "Quicksort";
            } else if (r8.isSelected() == true) {
                algoritmo_ = "Shellsort";
            }
            if (r1.isSelected() == true) {
                tipo_ = "Ascendente";
            } else if (r2.isSelected() == true) {
                tipo_ = "Descendente";
            }
            if (r3.isSelected() == true) {
                velocidad_ = 1000;
            } else if (r4.isSelected() == true) {
                velocidad_ = 450;
            } else if (r5.isSelected() == true) {
                velocidad_ = 100;
            }
            VentanaBurbuja ventana = new VentanaBurbuja(grafica, velocidad_);
            ventana.setVisible(true);
            
        }
        
    }
    
}
