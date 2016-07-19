/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacioncine;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.MatteBorder;

/**
 *
 * @author ANTONIO DAVID LÓPEZ MACHADO
 */
public class Application {
    
    public Application(){
        
        final JFrame frame = new JFrame("Aplicación de cine y series");
        //Este jpanel nos servira para hacer despues el cambio de cards(una para pelis y otra para series)
        final JPanel cards = new JPanel(new CardLayout());
        Dimension dm = new Dimension(1250, 800);
        frame.setPreferredSize(dm);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panelbotones = new JPanel();


        //Panel donde se añadiran todos los botones principales
        panelbotones.setLayout(new BoxLayout(panelbotones, BoxLayout.Y_AXIS));

        //Boton para ver las peliculas
        JButton botonpeliculas = new JButton("  Peliculas  ");
        Dimension boton = new Dimension(50, 50);
        botonpeliculas.setPreferredSize(boton);
       
        //Boton para ver las series
        JButton botonseries = new JButton("    Series     ");

        //Añadimos ambos botones
        panelbotones.add(botonpeliculas, BorderLayout.CENTER);
        panelbotones.add(botonseries);
        //Añadimos dos nuevos botones para vistas y peliculas
        //a determinar
        panelbotones.add(new JButton("    Vistas     "));
        panelbotones.add(new JButton("Pendientes"));

        //Añadimos a la interfaz el panel de botones
        frame.add(panelbotones, BorderLayout.WEST);
        frame.add(cards, BorderLayout.CENTER);
        
        //Creamos la zona de buscador
        JPanel Buscador = new JPanel();
        Buscador.add(new JLabel("Buscador:"));
        Dimension dimbusca = new Dimension(0, 40);
        Buscador.setPreferredSize(dimbusca);

        //Campo de texto para realizar la busqueda
        final JTextField campobus = new JTextField(20);
        Buscador.add(campobus);
        //Boton para realizar la busqueda
        JButton botonbuscar = new JButton("Buscar");
        
       //Añadimos ambos elementos al buscador y añadimos el buscador a la interfaz
        Buscador.add(botonbuscar);
        frame.add(Buscador, BorderLayout.NORTH);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

}
