package me.t4c30.vista;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.concurrent.atomic.AtomicLong;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Timer;

import me.t4c30.GestorBD;

public class PantallaPrincipal extends JFrame{
    private class Pane extends JPanel{
        private JButton botonSaldo = new JButton("1. Consultar saldo");
        private JButton botonDeposito = new JButton("2. Depositar dinero");
        private JButton botonRetiro = new JButton("3. Retirar dinero");
        private JButton botonSalir = new JButton("4. Salir");
        private JDialog ventana = new JDialog();
        private JLabel mostrarInformacion = new JLabel();
        private int retiro = 0;
        private int ingreso = 0;
        private long importetotal = 0;
        private long retirado = 0;

        public Pane() {
            setLayout(new GridLayout(2,2));
            ventana.setSize(250,250);

            botonSaldo.addActionListener((ActionEvent e) -> {
                mostrarInformacion.setText("Tienes un total de: " + GestorBD.consultarSaldo());
                ventana.add(mostrarInformacion);
                ventana.setVisible(true);
            });
            add(botonSaldo);
            
            botonDeposito.addActionListener((ActionEvent e) -> {
                ingreso++;
                importetotal=+retorno().get();
                GestorBD.añadirSaldo(importetotal);
            });
            add(botonDeposito);
            
            botonRetiro.addActionListener((ActionEvent e) -> {
                retiro++;
                retirado=+retorno().get();
                GestorBD.retirarSaldo(retirado);
            });
            add(botonRetiro);


            botonSalir.addActionListener((ActionEvent e) -> {
                JTextArea areaTexto = new JTextArea();
                areaTexto.setText("Gracias por usa el cajero automático. ¡Hasta luego!\n"+
                        "------------ESTADÍSTICAS DE USO----------------\n"+
                        "Número total de Ingresos: " +ingreso+ "\n"+
                        //"Importe total Ingresado:" +importetotal+ "\n"+
                        "Número total de Retiradas:" +retiro+ "\n"+
                        //"Importe total Retirado: "+retirado+ "\n" +
                        "Saldo Final en Cuenta: "+GestorBD.consultarSaldo()+ "\n"+
                        "-----------------------------------------------");
                areaTexto.setEditable(false);
                ventana.add(areaTexto);
                ventana.setVisible(true);

                Timer tiempoPausado = new Timer(10000, (ActionEvent e1) -> {
                    System.exit(0);
                });
                tiempoPausado.start(); 
            });
            add(botonSalir);
            
            setVisible(true);
        }

        private AtomicLong retorno(){
            AtomicLong dinero = new AtomicLong(0);
            JTextField textoVentana = new JTextField();
            JButton boton = new JButton("Enviar");
            ventana.add(textoVentana);
            ventana.add(boton);
            ventana.setVisible(true);
            boton.addActionListener((ActionEvent arg0) -> {
                Long dinerod = Long.valueOf(textoVentana.getText());
                dinero.set(dinerod);
            });
            return dinero;
        }
    }

    public PantallaPrincipal(){
        setSize(500,500);
        add(new Pane());
        setTitle("Cajero Automático");
        setVisible(true);
    }

}
