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
import java.awt.Rectangle;
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
    
    public Application() throws IOException{
        
        final JFrame frame = new JFrame("Aplicación de cine y series");
        //Este jpanel nos servira para hacer despues el cambio de cards(una para pelis y otra para series)
        CardLayout cardLayout= new CardLayout();
        JPanel cards = new JPanel(cardLayout);
        Dimension dm = new Dimension(1250, 800);
        frame.setPreferredSize(dm);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panelbotones = new JPanel();

        //Añadimos los elementos al CardLayout
        ListaElementos elementos = new ListaElementos(9);
        cards.add(elementos, "peliculas");
        cardLayout.show(cards, "peliculas");
        
        //Panel donde se añadiran todos los botones principales
        panelbotones.setLayout(new BoxLayout(panelbotones, BoxLayout.Y_AXIS));

        //Boton para ver las peliculas
        JButton botonPeliculas = new JButton("Peliculas");
        botonPeliculas.setBounds(new Rectangle(0,40,150,40));
       
        //Boton para ver las series
        JButton botonSeries = new JButton("Series");
        botonSeries.setBounds(new Rectangle(0,80,150,40));

        
        //Añadimos dos nuevos botones para vistas y peliculas
        //a determinar
        JButton botonVistasSeries = new JButton("Vistas");
        botonVistasSeries.setBounds(new Rectangle(0,120,150,40));
        panelbotones.add(new JButton("Pendientes"));
        
        //Añadimos los botones al panel
        panelbotones.add(botonPeliculas);
        panelbotones.add(botonSeries);
        panelbotones.add(botonVistasSeries);

        //Añadimos a la interfaz el panel de botones
        Dimension dimPanelBoton =new Dimension(150,80);
        panelbotones.setPreferredSize(dimPanelBoton);
        panelbotones.setLayout(null);
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
