/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author adrian
 */
public class BasededatosSQLite {
    
    private final static String DB_URL = "jdbc:sqlite:/home/adrian/Escritorio/ConcesionarioBD/Concesionario";
    private static Connection connection;

    public static String getDB_URL() {
        return DB_URL;
    }

    public static void setConnection(Connection connection) {
        BasededatosSQLite.connection = connection;
    }
    
    public static Connection getConnection() {
        return connection;
    }
    
    
    
    public BasededatosSQLite(){
        
        setConnection(null);
        
        try
        {
            Class.forName("org.sqlite.JDBC");
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
            return;
        }
        
    }
    
        public void abrirBasedeDatos(){
        
            try
            {
                setConnection(DriverManager.getConnection(getDB_URL()));
                System.out.println("Conexión exitosa");
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        
        }
        
        public void cerrarBasedeDatos(){
        
             try
            {
                if (getConnection() != null)
                {
                    getConnection().close();
                }
            } 
            catch (SQLException e) 
            {
                e.printStackTrace();
            }

        }
        
        public void ejecutarSentenciasBD(String sentencia){
        
            try
            {
                PreparedStatement preparedStatement = getConnection().prepareStatement(sentencia);
            }
            catch(SQLException e)
            {
                e.printStackTrace();
            }
            
        }
        
        public ResultSet ejecutarConsultaBD(String consulta){
        
            ResultSet resultSet = null;
            try
            {
                // Se utiliza PreparedStatement para evitar problemas de seguridad con consultas parametrizadas
                PreparedStatement statement = getConnection().prepareStatement(consulta);
                resultSet = statement.executeQuery();

                // Puedes procesar los resultados en el lugar donde llames a este método
            }
            catch (SQLException e)
            {
                System.err.println("Error al ejecutar la consulta: " + e.getMessage());
            }
            
            return resultSet;
        
        }
        
        public ArrayList<Cliente> getClientes(){
        
            ResultSet resultSet = null;
            ArrayList<Cliente> listaCliente = new ArrayList<>();
            
            resultSet = ejecutarConsultaBD("SELECT * FROM Alumnos");
            
            try
            {
                while (resultSet.next())
                {
                    System.out.println("Nombre: " + resultSet.getString("nombre"));
                }
            }
            catch (SQLException e)
            {
                System.err.println("Error al procesar los resultados: " + e.getMessage());
            }
            finally
            {
                // Cierra el ResultSet al finalizar
                try
                {
                    if (resultSet != null)
                    {
                        resultSet.close();
                    }
                }
                catch (SQLException e)
                {
                    System.err.println("Error al cerrar el ResultSet: " + e.getMessage());
                }
                
                cerrarBasedeDatos();
            }
        
        return listaCliente;
        }
    
}
