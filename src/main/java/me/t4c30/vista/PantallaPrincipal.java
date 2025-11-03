package me.t4c30.vista;

import java.awt.Button;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PantallaPrincipal extends JFrame{
    private class Pane extends JPanel{
        private JButton botonSaldo = new JButton("1. Consultar saldo");
        private JButton botonDeposito = new JButton("2. Depositar dinero");
        private JButton botonRetiro = new JButton("3. Retirar dinero");
        private JButton botonSalir = new JButton("4. Salir");

        public Pane() {
            setLayout(new GridLayout(2,2));

            ActionListener accionSaldo = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // TODO Auto-generated method stub
                    throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
                } 
            };
            botonSaldo.addActionListener(accionSaldo);
            add(botonSaldo);
            
            ActionListener accionDeposito = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // TODO Auto-generated method stub
                    throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
                }
            };
            botonDeposito.addActionListener(accionDeposito);
            add(botonDeposito);
            
            ActionListener accionRetiro = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // TODO Auto-generated method stub
                    throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
                }
            };
            botonRetiro.addActionListener(accionRetiro);
            add(botonRetiro);

            ActionListener accionSalir = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            };
            botonSalir.addActionListener(accionSalir);
            add(botonSalir);
            
            setVisible(true);
        }   
    }

    public PantallaPrincipal(){
        setSize(500,500);
        add(new Pane());
        setTitle("Cajero Automático");
        setVisible(true);
    }

}
