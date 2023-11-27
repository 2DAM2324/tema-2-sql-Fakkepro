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
    
        //Métedo para abrir la conexión con la base de datos.
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
        
        //Metodo para cerrar la conexión con la base de datos.
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
        
        //Método que ejecuta una sentencia en la base de datos.
        public void ejecutarSentenciasBD(String sentencia){
        
            try
            {
                // Utilizamos una transacción para asegurar la consistencia de la base de datos
                getConnection().setAutoCommit(false);
                PreparedStatement preparedStatement = getConnection().prepareStatement(sentencia);
            }
            catch(SQLException e)
            {
                e.printStackTrace();
            }
            
        }
        
        //Método que ejecuta una consulta en la base de datos y te devuelve el resultado.
        public ResultSet ejecutarConsultaBD(String consulta){
        
            ResultSet resultSet = null;
            try
            {
                // Se utiliza PreparedStatement para evitar problemas de seguridad con consultas parametrizadas
                PreparedStatement statement = getConnection().prepareStatement(consulta);
                resultSet = statement.executeQuery();
            }
            catch (SQLException e)
            {
                System.err.println("Error al ejecutar la consulta: " + e.getMessage());
            }
            
            return resultSet;
        
        }
        
        //Me devuelve todos los clientes de la base de datos.
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
        
        public void insertarClientes(ArrayList<Cliente> clientes) {
        
        try 
        {
            // Utilizamos una transacción para asegurar la consistencia de la base de datos
            getConnection().setAutoCommit(false);

            // Preparamos la sentencia de inserción
            String sql = "INSERT INTO Cliente(dni, nombre, direccion, ciudad, numero_telefono) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
            
            preparedStatement.setString(1, clientes.get(clientes.size()).getdni_cliente());
            preparedStatement.setString(2, clientes.get(clientes.size()).getnombre_cliente());
            preparedStatement.setString(3, clientes.get(clientes.size()).getdireccion_cliente());
            preparedStatement.setString(4, clientes.get(clientes.size()).getciudad_cliente());
            preparedStatement.setString(5, clientes.get(clientes.size()).getnumero_telefono_cliente());

            // Ejecutamos la inserción
            preparedStatement.executeUpdate();

            // Confirmamos la transacción
            getConnection().commit();

        }
        catch (SQLException e) 
        {
            // En caso de error, deshacemos la transacción
            try 
            {
                getConnection().rollback();
                e.printStackTrace();
            } 
            catch (SQLException e1) 
            {
                e1.printStackTrace();
            }
        }
        finally
        {
            // Restauramos el modo de auto-commit
            try
            {
                getConnection().setAutoCommit(true);
            }
            catch (SQLException e) 
            {
                e.printStackTrace();
            }

            cerrarBasedeDatos();
        }
    }
    
}
