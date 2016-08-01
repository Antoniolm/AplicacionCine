
package aplicacioncine;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

/**
 * Clase que nos permitirá crear un elemento(pelicula o serie) el cual tendra un
 * nombre,una imagen y una interfaz.
 * 
 * @author ANTONIO DAVID LÓPEZ MACHADO
 */
public class Elemento {
    String nombre,imagen;
    int id;
    boolean tipoElem;
    private JPanel panelElemento;
   
    public Elemento(int idEle,String nom,String img,boolean tipoElemento) throws IOException{
        nombre=nom;
        imagen=img;
        id=idEle;
        tipoElem=tipoElemento;
        
        panelElemento = new JPanel();
        
        Dimension dm=new Dimension(105,150); //Le damos la dimension que queramos
        panelElemento.setPreferredSize(dm);
        panelElemento.setBorder(new MatteBorder(1, 1, 1, 1, Color.GRAY));
        panelElemento.setLayout(new BoxLayout(panelElemento, BoxLayout.Y_AXIS));        
        panelElemento.setPreferredSize(dm);
        
        //Añadimos al panel tanto la imagen como el nombre del 
        panelElemento.add(new JLabel(cargarImagen())); 
        panelElemento.add(cargarNombre());
        
        //Añadimos funcionalidad al panel, podremos pulsarlo para que nos muestra una 
        //pantalla con toda la informacion del elemento
        panelElemento.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                        String salida;
                        if(tipoElemento) salida="peliculas";
                        else salida="series";
                    
                    try {
                        new InterfazElemento(id,salida).setVisible(true);
                    } catch (SQLException ex) {
                        Logger.getLogger(Elemento.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
        });
    }
    public JPanel getelemento(){
        return panelElemento;
    }
    
    /**
     * Método que nos permite cargar la imagen del objeto
     * @return nos devuelve la imagen creada
     */
    public ImageIcon cargarImagen(){
        //Creamos la imagen pero sin ninguna de momento
        ImageIcon imagenElemento= new ImageIcon("imgs/"+imagen+".jpg");
        Image image = imagenElemento.getImage(); // transform it
        Image newimg = image.getScaledInstance(105, 135,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way 
        imagenElemento = new ImageIcon(newimg);  // transform it back
        return imagenElemento;
    }
    
    /**
     * Metodo que nos permite cargar el nombre del objeto
     * @return 
     */
    public JLabel cargarNombre(){
        JLabel nom=new JLabel(nombre);
        nom.setFont(new java.awt.Font("Tahoma", 0, 11)); 
        return nom;
    }

    public String getNombre() {
        return nombre;
    }

    public String getImagen() {
        return imagen;
    }
    
}
