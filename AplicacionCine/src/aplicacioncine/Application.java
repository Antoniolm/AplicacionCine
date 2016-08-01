
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
import java.sql.ResultSet;
import java.sql.SQLException;
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
    DataBase baseDatos;
    boolean tipoActivado; //boolean para saber que card utilizar al pulsar los botones
                         // de Todas,vistas y pendientes
    private JPanel cards;
    public Application() throws IOException, SQLException{
        
        final JFrame frame = new JFrame("Aplicación de cine y series");
        //Este jpanel nos servira para hacer despues el cambio de cards(una para pelis y otra para series)
        CardLayout cardLayout= new CardLayout();
        cards = new JPanel(cardLayout);
        Dimension dm = new Dimension(1250, 800);
        frame.setPreferredSize(dm);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panelbotones = new JPanel();

        //Abrimos la bd
        baseDatos=new DataBase();    
        //baseDatos.operacion("INSERT INTO peliculas (nombre,imagen,anio,descripcion) VALUES ('"+prueba+"','peliculas/"+prueba+"',1900,'This is a description' );");     
        
        //Obtenemos el numero de filas de la tabla
        int numElementosPeli=numRows("PELICULAS","");
        int numElementosSeries=numRows("SERIES","");
        
        //Añadimos los elementos al CardLayout   |      Seleccionamos las peliculas de nuestra base de datos                    //true para peliculas
        ListaElementos elementosPeliculas = new ListaElementos(baseDatos.select("SELECT * FROM PELICULAS ORDER BY nombre DESC;"),numElementosPeli,true);
        ListaElementos elementosSeries = new ListaElementos(baseDatos.select("SELECT * FROM SERIES ORDER BY nombre DESC;"),numElementosSeries,false);
                                                                                                                            //false para peliculas
        //Creamos 4 elementos mas, 2 por cada tipo de elemento
        numElementosPeli=numRows("PELICULAS","WHERE vista=1");
        ListaElementos elementosPeliVistas = new ListaElementos(baseDatos.select("SELECT * FROM PELICULAS WHERE vista=1 ORDER BY nombre DESC;"),numElementosPeli,true);
        numElementosPeli=numRows("PELICULAS","WHERE pendiente=1");
        ListaElementos elementosPeliPendiente = new ListaElementos(baseDatos.select("SELECT * FROM PELICULAS WHERE pendiente=1 ORDER BY nombre DESC;"),numElementosPeli,true);
        numElementosSeries=numRows("SERIES","WHERE vista=1");
        ListaElementos elementosSeriesVistas = new ListaElementos(baseDatos.select("SELECT * FROM SERIES WHERE vista=1 ORDER BY nombre DESC;"),numElementosSeries,false);
        numElementosSeries=numRows("SERIES","WHERE pendiente=1");
        ListaElementos elementosSeriesPendiente = new ListaElementos(baseDatos.select("SELECT * FROM SERIES WHERE pendiente=1 ORDER BY nombre DESC;"),numElementosSeries,false);
        
        //Añadimos las cartas
        cards.add(elementosPeliculas, "peliculas");
        cards.add(elementosSeries, "series");
        cards.add(elementosPeliVistas,"pelisVistas");
        cards.add(elementosPeliPendiente,"pelisPendi");
        cards.add(elementosSeriesVistas,"seriesVistas");
        cards.add(elementosSeriesPendiente,"seriesPendi");
        //Visualizamos el cards que contiene las peliculas
        cardLayout.show(cards, "peliculas");
        tipoActivado=true;
        
        //Panel donde se añadiran todos los botones principales
        panelbotones.setLayout(new BoxLayout(panelbotones, BoxLayout.Y_AXIS));

        //Boton para ver las peliculas
        JButton botonPeliculas = new JButton("Peliculas");
        botonPeliculas.setBounds(new Rectangle(0,40,150,40));
        botonPeliculas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cards, "peliculas");
                tipoActivado=true;
            }
        });
        
        //Boton para ver las series
        JButton botonSeries = new JButton("Series");
        botonSeries.setBounds(new Rectangle(0,80,150,40));
        botonSeries.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cards, "series");
                tipoActivado=false;
            }
        });
        
        //Añadimos dos nuevos botones para vistas y peliculas
        //a determinar
        JButton botonNuevaPelicula = new JButton("Añadir Pelicula");
        botonNuevaPelicula.setBounds(new Rectangle(0,240,150,40));
        JButton botonNuevaSeries = new JButton("Añadir Serie");
        botonNuevaSeries.setBounds(new Rectangle(0,280,150,40));
        //Añadimos los botones al panel
        panelbotones.add(botonPeliculas);
        panelbotones.add(botonSeries);
        panelbotones.add(botonNuevaPelicula);
        panelbotones.add(botonNuevaSeries);
        //Añadimos a la interfaz el panel de botones
        Dimension dimPanelBoton =new Dimension(150,80);
        panelbotones.setPreferredSize(dimPanelBoton);
        panelbotones.setBackground(new Color(94, 94, 209));
        panelbotones.setLayout(null);
        frame.add(panelbotones, BorderLayout.WEST);
        frame.add(cards, BorderLayout.CENTER);
        
        //Creamos la zona de buscador
        JPanel Buscador = new JPanel();
        Buscador.add(new JLabel("Buscador:"));
        Dimension dimbusca = new Dimension(0, 80);
        Buscador.setPreferredSize(dimbusca);
        Buscador.setBackground(new Color(119, 119, 230));
        //Buscador.setLayout(null);

        //Campo de texto para realizar la busqueda
        final JTextField campobus = new JTextField(20);
        //Boton para realizar la busqueda
        JButton botonbuscar = new JButton("Buscar");
        

       //Añadimos ambos elementos al buscador y añadimos el buscador a la interfaz
        Buscador.add(campobus);
        Buscador.add(botonbuscar);
        
        JPanel Botones=new JPanel();
        Botones.setBackground(new Color(119, 119, 230));
        Botones.setPreferredSize(new Dimension(2000, 50));

        //Creamos los botones 
        JButton botonTodas = new JButton("Todas");
        botonTodas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(tipoActivado)cardLayout.show(cards, "peliculas");
                else cardLayout.show(cards,"series");
                
            }
        });
        JButton botonVistas = new JButton("Vistas");
        botonVistas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(tipoActivado)cardLayout.show(cards, "pelisVistas");
                else cardLayout.show(cards,"seriesVistas");
            }
        });
        JButton botonPen = new JButton("Pendientes");
        botonPen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(tipoActivado)cardLayout.show(cards, "pelisPendi");
                else cardLayout.show(cards,"seriesPendi");
            }
        });
        
        //Añadimos los botones al Jpanel
        Botones.add(botonTodas);
        Botones.add(botonVistas);
        Botones.add(botonPen);

        //Añadimos a nuestro buscador los botones y lo añadimos a la aplicacion
        Buscador.add(Botones);
        frame.add(Buscador, BorderLayout.NORTH);
        
        
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
    
    /**
     * 
     * Devuelve el numero de elementos de una tabla 
     * 
     */
     public int numRows(String tabla,String Where) throws SQLException{
         int salida=0;
        ResultSet tam=baseDatos.select("SELECT COUNT(*) FROM "+tabla+" "+Where+";");
        tam.next();
        salida = (int) tam.getLong(1);
        return salida;
     }
}
