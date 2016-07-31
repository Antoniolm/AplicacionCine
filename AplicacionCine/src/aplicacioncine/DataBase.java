

package aplicacioncine;
import java.sql.*;
/**
 *  Clase que nos permitira gestionar nuestra base de datos de peliculas y de series
 * @author ANTONIO DAVID LÓPEZ MACHADO
 */
public class DataBase {
    Connection conexion;
    Statement stmt;
    public DataBase() {
        Connection c = null;
        try {
            Class.forName("org.sqlite.JDBC");
            conexion = DriverManager.getConnection("jdbc:sqlite:BD/DataBase.db");
            stmt=conexion.createStatement();
            
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        
        System.out.println("Conexion establecida");

    }
    
    /**
     * Metodo que nos permite realizar todo tipo de operaciones sobre la base de datos
     * excepto selects
     * @param operation
     * 
     */
    public void operacion(String operation) throws SQLException{
        stmt.executeUpdate(operation);
    }
    
    /**
     * Metodo que nos permite realizar un select y obtener los resultados de este
     * @param operation
     * @return El conjunto de elementos que el select ha obtenido
     * 
     */
    public ResultSet select(String operation) throws SQLException{
        ResultSet salida;
        salida = stmt.executeQuery(operation);
        return salida;
    }
    
    /**
     * Cerramos la conexion de la base de datos
     * 
     */
    public void cerrarConexion() throws SQLException{
        stmt.close();
        conexion.close();
    
    
    }
}
