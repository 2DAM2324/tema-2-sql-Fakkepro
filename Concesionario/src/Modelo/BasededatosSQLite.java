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
import java.time.LocalDateTime;
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
        
        try
        {
            Class.forName("org.sqlite.JDBC");
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
            return;
        }
        
        abrirBasedeDatos();
        
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
            
            resultSet = ejecutarConsultaBD("SELECT * FROM Cliente");
            
            try
            {
                while (resultSet.next())
                {
                    String dni = resultSet.getString("dni");
                    String nombre = resultSet.getString("nombre");
                    String direccion = resultSet.getString("direccion");
                    String ciudad = resultSet.getString("ciudad");
                    String numeroTelefono = resultSet.getString("numero_telefono");
                    
                    Cliente cliente = new Cliente(dni, nombre, direccion, ciudad, numeroTelefono);
                    
                    listaCliente.add(cliente);
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
        
        public ArrayList<Coche> getGasolina(){
        
            ResultSet resultSet = null;
            ArrayList<Coche> listaCoche = new ArrayList<>();
            
            resultSet = ejecutarConsultaBD("SELECT * FROM Gasolina");
            
            try
            {
                while (resultSet.next())
                {
                    String vin = resultSet.getString("vin");
                    String marca = resultSet.getString("marca");
                    String modelo = resultSet.getString("modelo");
                    float precio = resultSet.getFloat("precio");
                    String color = resultSet.getString("color");
                    int deposito = resultSet.getInt("deposito");
                    
                    Gasolina coche = new Gasolina(deposito, vin, marca, modelo, precio, color);
                    
                    listaCoche.add(coche);
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
        
        return listaCoche;
        }
        
        public ArrayList<Coche> getElectrico(){
        
            ResultSet resultSet = null;
            ArrayList<Coche> listaCoche = new ArrayList<>();
            
            resultSet = ejecutarConsultaBD("SELECT * FROM Electrico");
            
            try
            {
                while (resultSet.next())
                {
                    String vin = resultSet.getString("vin");
                    String marca = resultSet.getString("marca");
                    String modelo = resultSet.getString("modelo");
                    float precio = resultSet.getFloat("precio");
                    String color = resultSet.getString("color");
                    double bateria = resultSet.getDouble("bateria");
                    
                    Electrico coche = new Electrico(bateria, vin, marca, modelo, precio, color);
                    
                    listaCoche.add(coche);
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
        
        return listaCoche;
        }
        
        public ArrayList<Revisores> getRevisor(){
        
            ResultSet resultSet = null;
            ArrayList<Revisores> listaRevisor = new ArrayList<>();
            
            resultSet = ejecutarConsultaBD("SELECT * FROM Revisor");
            
            try
            {
                while (resultSet.next())
                {
                    String codigo = resultSet.getString("codigo");
                    String nombre = resultSet.getString("nombre");
                    
                    Revisores revisor = new Revisores(codigo, nombre);
                    
                    listaRevisor.add(revisor);
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
        
        return listaRevisor;
        }
        
        public ArrayList<Proveedor> getProveedor(){
        
            ResultSet resultSet = null;
            ArrayList<Proveedor> listaProveedor = new ArrayList<>();
            
            resultSet = ejecutarConsultaBD("SELECT * FROM Proveedor");
            
            try
            {
                while (resultSet.next())
                {
                    String codigo = resultSet.getString("codigo");
                    String nombre = resultSet.getString("nombre");
                    
                    Proveedor proveedor = new Proveedor(codigo, nombre);
                    
                    listaProveedor.add(proveedor);
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
        
        return listaProveedor;
        }
        
        public ArrayList<Comprar> getCompra(){
        
            ResultSet resultSet = null;
            ArrayList<Comprar> listaCompra = new ArrayList<>();
            
            resultSet = ejecutarConsultaBD("SELECT * FROM Comprar");
            
            try
            {
                while (resultSet.next())
                {
                    String matricula = resultSet.getString("matricula");
                    LocalDateTime fecha = LocalDateTime.parse(resultSet.getString("fecha"));
                    String vin = resultSet.getString("vin_coche");
                    String dni = resultSet.getString("dni_cliente");
                    
                    Comprar compra = new Comprar(matricula, fecha, dni, vin);
                    
                    listaCompra.add(compra);
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
        
        return listaCompra;
        }
        
        public ArrayList<Proveer> getProvisiones(){
        
            ResultSet resultSet = null;
            ArrayList<Proveer> listaProvisiones = new ArrayList<>();
            
            resultSet = ejecutarConsultaBD("SELECT * FROM Proveer");
            
            try
            {
                while (resultSet.next())
                {
                    String codigo = resultSet.getString("codigo_proveedor");
                    String vin = resultSet.getString("vin_coche");
                    
                    Proveer provision = new Proveer(codigo, vin);
                    
                    listaProvisiones.add(provision);
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
        
        return listaProvisiones;
        }
        
        public ArrayList<Revisar> getRevisiones(){
        
            ResultSet resultSet = null;
            ArrayList<Revisar> listaRevisiones = new ArrayList<>();
            
            resultSet = ejecutarConsultaBD("SELECT * FROM Proveer");
            
            try
            {
                while (resultSet.next())
                {
                    String codigo = resultSet.getString("codigo");
                    String codigoRevisor = resultSet.getString("codigo_revisor");
                    String vin = resultSet.getString("vin_coche");
                    
                    Revisar revision = new Revisar(codigo, vin, codigoRevisor);
                    
                    listaRevisiones.add(revision);
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
        
        return listaRevisiones;
        }
        
}
