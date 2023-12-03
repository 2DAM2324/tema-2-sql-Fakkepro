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
import javax.swing.JOptionPane;

/**
 *
 * @author adrian
 */
public class BasededatosSQLite {
    
    private final static String DB_URL = "jdbc:sqlite:/home/adrian/Escritorio/ConcesionarioBD/ConcesionarioV2";
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
            JOptionPane.showMessageDialog(null, "Error al abrir la base de datos: " + e.getMessage());
            return;
        }
        
        abrirBasedeDatos();
        
    }
    
        //Métedo para abrir la conexión con la base de datos.
        public void abrirBasedeDatos(){
        
            try
            {
                setConnection(DriverManager.getConnection(getDB_URL() + "?foreign_keys=true"));
                JOptionPane.showMessageDialog(null, "Base de datos abierta correctamente");
            }
            catch (SQLException e)
            {
                JOptionPane.showMessageDialog(null, "Error al abrir la base de datos: " + e.getMessage());
                System.exit(0);
            }
        
        }
        
        //Metodo para cerrar la conexión con la base de datos.
        public void cerrarBasedeDatos(){
        
             try
            {
                if (getConnection() != null)
                {
                    getConnection().close();
                    JOptionPane.showMessageDialog(null, "Base de datos cerrada correctamente");
                }
            } 
            catch (SQLException e) 
            {
                JOptionPane.showMessageDialog(null, "Error al cerrar la base de datos: " + e.getMessage());
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
                JOptionPane.showMessageDialog(null, "Error al ejecutar la consulta: " + e.getMessage());
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
                JOptionPane.showMessageDialog(null, "Error al traer los clientes de la base de datos: " + e.getMessage());
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
                    JOptionPane.showMessageDialog(null, "Error al cerrar el ResulSet:  " + e.getMessage());
                }
            }
        
        return listaCliente;
        }
        
        public void aniadirCLienteBD(Cliente cliente){
        
            try 
            {
                getConnection().setAutoCommit(false);
                // Preparar la sentencia SQL para la inserción
                String sentencia = "INSERT INTO Cliente (dni, nombre, direccion, ciudad, numero_telefono) " +
                        "VALUES (?, ?, ?, ?, ?)";

                // Utilizar PreparedStatement para evitar problemas de seguridad con consultas parametrizadas
                PreparedStatement preparedStatement = getConnection().prepareStatement(sentencia);

                // Establecer los valores de los parámetros
                preparedStatement.setString(1, cliente.getdni_cliente());
                preparedStatement.setString(2, cliente.getnombre_cliente());
                preparedStatement.setString(3, cliente.getdireccion_cliente());
                preparedStatement.setString(4, cliente.getciudad_cliente());
                preparedStatement.setString(5, cliente.getnumero_telefono_cliente());

                // Ejecutar la inserción
                preparedStatement.executeUpdate();

                // Confirmar la transacción
                getConnection().commit();
                JOptionPane.showMessageDialog(null, "Cliente añadido correctamente.");
            } 
            catch (SQLException e) 
            {
                // Deshacer la transacción en caso de error
                try 
                {
                    getConnection().rollback();
                } 
                catch (SQLException ex) 
                {
                    ex.printStackTrace();
                }
                
                e.printStackTrace();
            }
            finally
            {
                // Restaurar el modo de auto-commit al finalizar la transacción
                try 
                {
                    connection.setAutoCommit(true);
                }
                catch (SQLException e) 
                {
                    e.printStackTrace();
                }
            }
        
        }
        
        public void borrarClienteBD(String dni) {
            try 
            {
                getConnection().setAutoCommit(false);
                // Preparar la sentencia SQL para la eliminación
                String sentencia = "DELETE FROM Cliente WHERE dni = ?";

                // Utilizar PreparedStatement para evitar problemas de seguridad con consultas parametrizadas
                PreparedStatement preparedStatement = getConnection().prepareStatement(sentencia);

                // Establecer el valor del parámetro
                preparedStatement.setString(1, dni);

                // Ejecutar la eliminación
                preparedStatement.executeUpdate();

                // Confirmar la transacción
                getConnection().commit();
                JOptionPane.showMessageDialog(null, "Cliente borrado correctamente.");
            } 
            catch (SQLException e) 
            {
                // Deshacer la transacción en caso de error
                try 
                {
                    getConnection().rollback();
                } 
                catch (SQLException ex)
                {
                    ex.printStackTrace();
                }
                
                e.printStackTrace();
            }
        }
        
        public void modificarClienteBD(Cliente cliente) {
            try 
            {
                getConnection().setAutoCommit(false);
                // Preparar la sentencia SQL para la actualización
                String sentencia = "UPDATE Cliente SET nombre = ?, direccion = ?, ciudad = ?, numero_telefono = ? " +
                        "WHERE dni = ?";

                // Utilizar PreparedStatement para evitar problemas de seguridad con consultas parametrizadas
                PreparedStatement preparedStatement = getConnection().prepareStatement(sentencia);

                // Establecer los valores de los parámetros
                preparedStatement.setString(1, cliente.getnombre_cliente());
                preparedStatement.setString(2, cliente.getdireccion_cliente());
                preparedStatement.setString(3, cliente.getciudad_cliente());
                preparedStatement.setString(4, cliente.getnumero_telefono_cliente());
                preparedStatement.setString(5, cliente.getdni_cliente());

                // Ejecutar la actualización
                preparedStatement.executeUpdate();

                // Confirmar la transacción
                getConnection().commit();
                JOptionPane.showMessageDialog(null, "Cliente modificado correctamente.");
            }
            catch (SQLException e) 
            {
                // Deshacer la transacción en caso de error
                try 
                {
                    getConnection().rollback();
                } 
                catch (SQLException ex) 
                {
                    ex.printStackTrace();
                }
                
                e.printStackTrace();
            }
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
                JOptionPane.showMessageDialog(null, "Error traer los datos de los gasolina: " + e.getMessage());
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
                    JOptionPane.showMessageDialog(null, "Error al cerrar el ResultSet: " + e.getMessage());
                }
            }
        
        return listaCoche;
        }
        
        public void aniadirGasolinaBD(Gasolina gasolina) {
            try 
            {
                getConnection().setAutoCommit(false);
                // Preparar la sentencia SQL para la inserción
                String sentencia = "INSERT INTO Gasolina (vin, marca, modelo, precio, color, deposito) VALUES (?, ?, ?, ?, ?, ?)";

                // Utilizar PreparedStatement para evitar problemas de seguridad con consultas parametrizadas
                PreparedStatement preparedStatement = getConnection().prepareStatement(sentencia);

                // Establecer los valores de los parámetros
                preparedStatement.setString(1, gasolina.getvin_coche());
                preparedStatement.setString(2, gasolina.getmarca_coche());
                preparedStatement.setString(3, gasolina.getmodelo_coche());
                preparedStatement.setFloat(4, gasolina.getprecio_coche());
                preparedStatement.setString(5, gasolina.getcolor_coche());
                preparedStatement.setInt(6, gasolina.getdeposito_gasolina());

                // Ejecutar la inserción
                preparedStatement.executeUpdate();

                // Confirmar la transacción
                getConnection().commit();
                JOptionPane.showMessageDialog(null, "Coche añadido correctamente.");
            } 
            catch (SQLException e) 
            {
                // Deshacer la transacción en caso de error
                try 
                {
                    getConnection().rollback();
                }
                catch (SQLException ex) 
                {
                    ex.printStackTrace();
                }
                
                e.printStackTrace();
            }
        }
        
        public void aniadirCocheBD(String vin) {
            try 
            {
                getConnection().setAutoCommit(false);
                // Preparar la sentencia SQL para la inserción
                String sentencia = "INSERT INTO Coche (vin) VALUES (?)";

                // Utilizar PreparedStatement para evitar problemas de seguridad con consultas parametrizadas
                PreparedStatement preparedStatement = getConnection().prepareStatement(sentencia);

                // Establecer los valores de los parámetros
                preparedStatement.setString(1, vin);

                // Ejecutar la inserción
                preparedStatement.executeUpdate();

                // Confirmar la transacción
                getConnection().commit();
            } 
            catch (SQLException e) 
            {
                // Deshacer la transacción en caso de error
                try 
                {
                    getConnection().rollback();
                }
                catch (SQLException ex) 
                {
                    ex.printStackTrace();
                }
                
                e.printStackTrace();
            }
        }

        public void borrarGasolinaBD(String vin) {
            borrarCocheBD("Gasolina", vin);
        }

        public void modificarGasolinaBD(Gasolina gasolina) {
            modificarCocheBD("Gasolina", gasolina);
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
                JOptionPane.showMessageDialog(null, "Error al cargar los electricos: " + e.getMessage());
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
                    JOptionPane.showMessageDialog(null, "Error al cerrar el ResultSet: " + e.getMessage());
                }
            }
        
        return listaCoche;
        }
        
        public void aniadirElectricoBD(Electrico electrico) {
            try 
            {
                getConnection().setAutoCommit(false);
                // Preparar la sentencia SQL para la inserción
                String sentencia = "INSERT INTO Electrico (vin, marca, modelo, precio, color, bateria) VALUES (?, ?, ?, ?, ?, ?)";

                // Utilizar PreparedStatement para evitar problemas de seguridad con consultas parametrizadas
                PreparedStatement preparedStatement = getConnection().prepareStatement(sentencia);

                // Establecer los valores de los parámetros
                preparedStatement.setString(1, electrico.getvin_coche());
                preparedStatement.setString(2, electrico.getmarca_coche());
                preparedStatement.setString(3, electrico.getmodelo_coche());
                preparedStatement.setFloat(4, electrico.getprecio_coche());
                preparedStatement.setString(5, electrico.getcolor_coche());
                preparedStatement.setDouble(6, electrico.getbateria_electrico());

                // Ejecutar la inserción
                preparedStatement.executeUpdate();

                // Confirmar la transacción
                getConnection().commit();
                JOptionPane.showMessageDialog(null, "Coche añadido correctamente.");
            } 
            catch (SQLException e) 
            {
                // Deshacer la transacción en caso de error
                try 
                {
                    getConnection().rollback();
                } 
                catch (SQLException ex) 
                {
                    ex.printStackTrace();
                }
                
                e.printStackTrace();
            }
        }

        public void borrarElectricoBD(String vin) {
            borrarCocheBD("Electrico", vin);
        }

        public void modificarElectricoBD(Electrico electrico) {
            modificarCocheBD("Electrico", electrico);
        }
        
        public void borrarCocheBD(String vin) {
            try 
            {
                getConnection().setAutoCommit(false);
                // Preparar la sentencia SQL para la eliminación
                String sentencia = "DELETE FROM Coche WHERE vin = ?";

                // Utilizar PreparedStatement para evitar problemas de seguridad con consultas parametrizadas
                PreparedStatement preparedStatement = getConnection().prepareStatement(sentencia);

                // Establecer el valor del parámetro
                preparedStatement.setString(1, vin);

                // Ejecutar la eliminación
                preparedStatement.executeUpdate();

                // Confirmar la transacción
                getConnection().commit();
                JOptionPane.showMessageDialog(null, "Coche borrado correctamente.");
            } 
            catch (SQLException e) 
            {
                // Deshacer la transacción en caso de error
                try 
                {
                    getConnection().rollback();
                }
                catch (SQLException ex)
                {
                    ex.printStackTrace();
                }
                
                e.printStackTrace();
            }
        }
        
        private void borrarCocheBD(String tipoCoche, String vin) {
            try 
            {
                getConnection().setAutoCommit(false);
                // Preparar la sentencia SQL para la eliminación
                String sentencia = "DELETE FROM " + tipoCoche + " WHERE vin = ?";

                // Utilizar PreparedStatement para evitar problemas de seguridad con consultas parametrizadas
                PreparedStatement preparedStatement = getConnection().prepareStatement(sentencia);

                // Establecer el valor del parámetro
                preparedStatement.setString(1, vin);

                // Ejecutar la eliminación
                preparedStatement.executeUpdate();

                // Confirmar la transacción
                getConnection().commit();
                JOptionPane.showMessageDialog(null, "Coche borrado correctamente.");
            } 
            catch (SQLException e) 
            {
                // Deshacer la transacción en caso de error
                try 
                {
                    getConnection().rollback();
                }
                catch (SQLException ex)
                {
                    ex.printStackTrace();
                }
                
                e.printStackTrace();
            }
        }
        
        private void modificarCocheBD(String tipoCoche, Coche coche) {
            try 
            {
                getConnection().setAutoCommit(false);
                // Preparar la sentencia SQL para la actualización
                String sentencia = "UPDATE " + tipoCoche + " SET marca = ?, modelo = ?, precio = ?, color = ? WHERE vin = ?";

                // Utilizar PreparedStatement para evitar problemas de seguridad con consultas parametrizadas
                PreparedStatement preparedStatement = getConnection().prepareStatement(sentencia);

                // Establecer los valores de los parámetros
                preparedStatement.setString(1, coche.getmarca_coche());
                preparedStatement.setString(2, coche.getmodelo_coche());
                preparedStatement.setFloat(3, coche.getprecio_coche());
                preparedStatement.setString(4, coche.getcolor_coche());
                preparedStatement.setString(5, coche.getvin_coche());
                 // Ejecutar la actualización
                preparedStatement.executeUpdate();

                // Confirmar la transacción
                getConnection().commit();
                JOptionPane.showMessageDialog(null, "Coche modificado correctamente.");
            } 
            catch (SQLException e) {
                // Deshacer la transacción en caso de error
                try
                {
                    getConnection().rollback();
                }
                catch (SQLException ex)
                {
                    ex.printStackTrace();
                }
                
                e.printStackTrace();
            }
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
                JOptionPane.showMessageDialog(null, "Error al cargar los revisores: " + e.getMessage());
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
                    JOptionPane.showMessageDialog(null, "Error al cerrar el ResultSet: " + e.getMessage());
                }
            }
        
        return listaRevisor;
        }
        
        public void aniadirRevisorBD(Revisores revisor) {
            try
            {
                getConnection().setAutoCommit(false);
                // Preparar la sentencia SQL para la inserción
                String sentencia = "INSERT INTO Revisor (codigo, nombre) VALUES (?, ?)";

                // Utilizar PreparedStatement para evitar problemas de seguridad con consultas parametrizadas
                PreparedStatement preparedStatement = getConnection().prepareStatement(sentencia);

                // Establecer los valores de los parámetros
                preparedStatement.setString(1, revisor.getcod_revisor_revisores());
                preparedStatement.setString(2, revisor.getNombre_revisores());

                // Ejecutar la inserción
                preparedStatement.executeUpdate();

                // Confirmar la transacción
                getConnection().commit();
                JOptionPane.showMessageDialog(null, "Revisor añadido correctamente.");
            }
            catch (SQLException e)
            {
                // Deshacer la transacción en caso de error
                try 
                {
                    getConnection().rollback();
                } 
                catch (SQLException ex) 
                {
                    ex.printStackTrace();
                }
                
                e.printStackTrace();
            }
        }
        
        public void borrarRevisorBD(String codigo) {
            try
            {
                getConnection().setAutoCommit(false);
                // Preparar la sentencia SQL para la eliminación
                String sentencia = "DELETE FROM Revisor WHERE codigo = ?";

                // Utilizar PreparedStatement para evitar problemas de seguridad con consultas parametrizadas
                PreparedStatement preparedStatement = getConnection().prepareStatement(sentencia);

                // Establecer el valor del parámetro
                preparedStatement.setString(1, codigo);

                // Ejecutar la eliminación
                preparedStatement.executeUpdate();

                // Confirmar la transacción
                getConnection().commit();
                JOptionPane.showMessageDialog(null, "Revisor borrado correctamente.");
            } 
            catch (SQLException e) 
            {
                // Deshacer la transacción en caso de error
                try 
                {
                    getConnection().rollback();
                }
                catch (SQLException ex) 
                {
                    ex.printStackTrace();
                }
                
                e.printStackTrace();
            }
        }
        
        public void modificarRevisorBD(Revisores revisor) {
            try
            {
                getConnection().setAutoCommit(false);
                // Preparar la sentencia SQL para la actualización
                String sentencia = "UPDATE Revisor SET nombre = ? WHERE codigo = ?";

                // Utilizar PreparedStatement para evitar problemas de seguridad con consultas parametrizadas
                PreparedStatement preparedStatement = getConnection().prepareStatement(sentencia);

                // Establecer los valores de los parámetros
                preparedStatement.setString(1, revisor.getNombre_revisores());
                preparedStatement.setString(2, revisor.getcod_revisor_revisores());

                // Ejecutar la actualización
                preparedStatement.executeUpdate();

                // Confirmar la transacción
                getConnection().commit();
                JOptionPane.showMessageDialog(null, "Revisor modificado correctamente.");
            }
            catch (SQLException e)
            {
                // Deshacer la transacción en caso de error
                try 
                {
                    getConnection().rollback();
                }
                catch (SQLException ex) 
                {
                    ex.printStackTrace();
                }
                
                e.printStackTrace();
            }
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
                JOptionPane.showMessageDialog(null, "Error al cargar los proveedores: " + e.getMessage());
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
                    JOptionPane.showMessageDialog(null, "Error al cerrar el ResultSet: " + e.getMessage());
                }
            }
        
        return listaProveedor;
        }
        
        public void aniadirProveedorBD(Proveedor proveedor) {
            try 
            {
                getConnection().setAutoCommit(false);
                // Preparar la sentencia SQL para la inserción
                String sentencia = "INSERT INTO Proveedor (codigo, nombre) VALUES (?, ?)";

                // Utilizar PreparedStatement para evitar problemas de seguridad con consultas parametrizadas
                PreparedStatement preparedStatement = getConnection().prepareStatement(sentencia);

                // Establecer los valores de los parámetros
                preparedStatement.setString(1, proveedor.getcod_proveedor());
                preparedStatement.setString(2, proveedor.getnombre_provedor());

                // Ejecutar la inserción
                preparedStatement.executeUpdate();

                // Confirmar la transacción
                getConnection().commit();
                JOptionPane.showMessageDialog(null, "Proveedor añadido correctamente.");

            } 
            catch (SQLException e) 
            {
                // Deshacer la transacción en caso de error
                try
                {
                    getConnection().rollback();
                } 
                catch (SQLException ex) 
                {
                    ex.printStackTrace();
                }
                
                e.printStackTrace();
            }
        }
        
        public void borrarProveedorBD(String codigo) {
            try 
            {
                getConnection().setAutoCommit(false);
                // Preparar la sentencia SQL para la eliminación
                String sentencia = "DELETE FROM Proveedor WHERE codigo = ?";

                // Utilizar PreparedStatement para evitar problemas de seguridad con consultas parametrizadas
                PreparedStatement preparedStatement = getConnection().prepareStatement(sentencia);

                // Establecer el valor del parámetro
                preparedStatement.setString(1, codigo);

                // Ejecutar la eliminación
                preparedStatement.executeUpdate();

                // Confirmar la transacción
                getConnection().commit();
                JOptionPane.showMessageDialog(null, "Proveedor borrado correctamente.");
            } 
            catch (SQLException e) 
            {
                // Deshacer la transacción en caso de error
                try 
                {
                    getConnection().rollback();
                } 
                catch (SQLException ex)
                {
                    ex.printStackTrace();
                }
                
                e.printStackTrace();
            }
        }
        
        public void modificarProveedorBD(Proveedor proveedor) {
            try 
            {
                getConnection().setAutoCommit(false);
                // Preparar la sentencia SQL para la actualización
                String sentencia = "UPDATE Proveedor SET nombre = ? WHERE codigo = ?";

                // Utilizar PreparedStatement para evitar problemas de seguridad con consultas parametrizadas
                PreparedStatement preparedStatement = getConnection().prepareStatement(sentencia);

                // Establecer los valores de los parámetros
                preparedStatement.setString(1, proveedor.getnombre_provedor());
                preparedStatement.setString(2, proveedor.getcod_proveedor());

                // Ejecutar la actualización
                preparedStatement.executeUpdate();

                // Confirmar la transacción
                getConnection().commit();
                JOptionPane.showMessageDialog(null, "Proveedor modificado correctamente.");
            } 
            catch (SQLException e) 
            {
                // Deshacer la transacción en caso de error
                try 
                {
                    getConnection().rollback();
                }
                catch (SQLException ex) 
                {
                    ex.printStackTrace();
                }
                
                e.printStackTrace();
            }
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
                JOptionPane.showMessageDialog(null, "Error al traer las compras de la base de datos");
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
                    JOptionPane.showMessageDialog(null, "Error al cerrar el ResultSet: " + e.getMessage());
                }
            }
        
        return listaCompra;
        }
        
        public void aniadirComprarBD(Comprar compra) {
            try 
            {
                getConnection().setAutoCommit(false);
                // Preparar la sentencia SQL para la inserción
                String sentencia = "INSERT INTO Comprar(matricula, fecha, vin_coche, dni_cliente) VALUES (?, ?, ?, ?)";

                // Utilizar PreparedStatement para evitar problemas de seguridad con consultas parametrizadas
                PreparedStatement preparedStatement = getConnection().prepareStatement(sentencia);

                // Establecer los valores de los parámetros
                preparedStatement.setString(1, compra.getmatricula_comprar());
                preparedStatement.setString(2, compra.getFechayHora().toString());
                preparedStatement.setString(3, compra.getvin_comprar());
                preparedStatement.setString(4, compra.getdni_comprar());

                // Ejecutar la inserción
                preparedStatement.executeUpdate();

                // Confirmar la transacción
                getConnection().commit();
                JOptionPane.showMessageDialog(null, "Compra añadida correctamente.");
            }
            catch (SQLException e) 
            {
                // Deshacer la transacción en caso de error
                try 
                {
                    getConnection().rollback();
                }
                catch (SQLException ex) 
                {
                    ex.printStackTrace();
                }

                e.printStackTrace();
            }
        }

        public void borrarComprarBD(String matricula) 
        {
            try 
            {
                getConnection().setAutoCommit(false);
                // Preparar la sentencia SQL para la eliminación
                String sentencia = "DELETE FROM Comprar WHERE matricula = ?";

                // Utilizar PreparedStatement para evitar problemas de seguridad con consultas parametrizadas
                PreparedStatement preparedStatement = getConnection().prepareStatement(sentencia);

                // Establecer el valor del parámetro
                preparedStatement.setString(1, matricula);

                // Ejecutar la eliminación
                preparedStatement.executeUpdate();

                // Confirmar la transacción
                getConnection().commit();
                JOptionPane.showMessageDialog(null, "Compra borrada correctamente.");
            } 
            catch (SQLException e) 
            {
                // Deshacer la transacción en caso de error
                try 
                {
                    getConnection().rollback();
                } 
                catch (SQLException ex) 
                {
                    ex.printStackTrace();
                }

                e.printStackTrace();
            }
        }

        public void modificarComprarBD(Comprar compra) 
        {
            try
            {
                getConnection().setAutoCommit(false);
                // Preparar la sentencia SQL para la actualización
                String sentencia = "UPDATE Comprar SET fecha = ?, vin_coche = ?, dni_cliente = ? WHERE matricula = ?";

                // Utilizar PreparedStatement para evitar problemas de seguridad con consultas parametrizadas
                PreparedStatement preparedStatement = getConnection().prepareStatement(sentencia);

                // Establecer los valores de los parámetros
                preparedStatement.setString(1, compra.getFechayHora().toString());
                preparedStatement.setString(2, compra.getvin_comprar());
                preparedStatement.setString(3, compra.getdni_comprar());
                preparedStatement.setString(4, compra.getmatricula_comprar());

                // Ejecutar la actualización
                preparedStatement.executeUpdate();

                // Confirmar la transacción
                getConnection().commit();
                JOptionPane.showMessageDialog(null, "Compra modificada correctamente.");
            }
            catch (SQLException e) {
                // Deshacer la transacción en caso de error
                try 
                {
                    getConnection().rollback();
                } 
                catch (SQLException ex)
                {
                    ex.printStackTrace();
                }

                e.printStackTrace();
            }
        }
        
        public ArrayList<Proveer> getProvisiones(){
        
            ResultSet resultSet = null;
            ArrayList<Proveer> listaProvisiones = new ArrayList<>();
            
            resultSet = ejecutarConsultaBD("SELECT * FROM Proveer");
            
            try
            {
                while (resultSet.next())
                {
                    String codigo = resultSet.getString("codigo");
                    String codigoProveedor = resultSet.getString("codigo_proveedor");
                    String vin = resultSet.getString("vin_coche");
                    
                    Proveer provision = new Proveer(codigo, codigoProveedor, vin);
                    
                    listaProvisiones.add(provision);
                }
            }
            catch (SQLException e)
            {
                JOptionPane.showMessageDialog(null, "Error al cargar las provisiones : " + e.getMessage());
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
                    JOptionPane.showMessageDialog(null, "Error al cerrar el ResultSet: " + e.getMessage());
                }
            }
        
        return listaProvisiones;
        }
        
        public void aniadirProveerBD(Proveer proveer) {
            try 
            {
                getConnection().setAutoCommit(false);
                // Preparar la sentencia SQL para la inserción
                String sentencia = "INSERT INTO Proveer(codigo, codigo_proveedor, vin_coche) VALUES (?, ?, ?)";

                // Utilizar PreparedStatement para evitar problemas de seguridad con consultas parametrizadas
                PreparedStatement preparedStatement = getConnection().prepareStatement(sentencia);

                // Establecer los valores de los parámetros
                preparedStatement.setString(1, proveer.getCodigo_proveer());
                preparedStatement.setString(2, proveer.getcod_proveedor_proveer());
                preparedStatement.setString(3, proveer.getvin_proveer());

                // Ejecutar la inserción
                preparedStatement.executeUpdate();

                // Confirmar la transacción
                getConnection().commit();
                JOptionPane.showMessageDialog(null, "Registro de proveedor añadido correctamente.");
            } 
            catch (SQLException e) 
            {
                // Deshacer la transacción en caso de error
                try 
                {
                    getConnection().rollback();
                }
                catch (SQLException ex) 
                {
                    ex.printStackTrace();
                }

                e.printStackTrace();
            }
        }

        public void borrarProveerBD(String codigo) {
            try 
            {
                getConnection().setAutoCommit(false);
                // Preparar la sentencia SQL para la eliminación
                String sentencia = "DELETE FROM Proveer WHERE codigo = ?";

                // Utilizar PreparedStatement para evitar problemas de seguridad con consultas parametrizadas
                PreparedStatement preparedStatement = getConnection().prepareStatement(sentencia);

                // Establecer el valor del parámetro
                preparedStatement.setString(1, codigo);

                // Ejecutar la eliminación
                preparedStatement.executeUpdate();

                // Confirmar la transacción
                getConnection().commit();
                JOptionPane.showMessageDialog(null, "Registro de proveedor borrado correctamente.");
            } 
            catch (SQLException e) 
            {
                // Deshacer la transacción en caso de error
                try 
                {
                    getConnection().rollback();
                } 
                catch (SQLException ex) 
                {
                    ex.printStackTrace();
                }

                e.printStackTrace();
            }
        }

        public void modificarProveerBD(Proveer proveer) {
            try
            {
                getConnection().setAutoCommit(false);
                // Preparar la sentencia SQL para la actualización
                String sentencia = "UPDATE Proveer SET vin_coche = ?, codigo_proveedor = ? WHERE codigo = ?";

                // Utilizar PreparedStatement para evitar problemas de seguridad con consultas parametrizadas
                PreparedStatement preparedStatement = getConnection().prepareStatement(sentencia);

                // Establecer los valores de los parámetros
                preparedStatement.setString(1, proveer.getvin_proveer());
                preparedStatement.setString(2, proveer.getcod_proveedor_proveer());
                preparedStatement.setString(3, proveer.getCodigo_proveer());

                // Ejecutar la actualización
                preparedStatement.executeUpdate();

                // Confirmar la transacción
                getConnection().commit();
                JOptionPane.showMessageDialog(null, "Registro de proveedor modificado correctamente.");
            } 
            catch (SQLException e)
            {
                // Deshacer la transacción en caso de error
                try 
                {
                    getConnection().rollback();
                } 
                catch (SQLException ex) 
                {
                    ex.printStackTrace();
                }

                e.printStackTrace();
            }
        }
        
        public ArrayList<Revisar> getRevisiones(){
        
            ResultSet resultSet = null;
            ArrayList<Revisar> listaRevisiones = new ArrayList<>();
            
            resultSet = ejecutarConsultaBD("SELECT * FROM Revisar");
            
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
                JOptionPane.showMessageDialog(null, "Error al cargar las revisiones : " + e.getMessage());
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
                    JOptionPane.showMessageDialog(null, "Error al cerrar el ResultSet: " + e.getMessage());
                }
            }
        
        return listaRevisiones;
        }
        
        public void aniadirRevisarBD(Revisar revisar) {
        try {
            getConnection().setAutoCommit(false);
            // Preparar la sentencia SQL para la inserción
            String sentencia = "INSERT INTO Revisar(codigo, vin_coche, codigo_revisor) VALUES (?, ?, ?)";

            // Utilizar PreparedStatement para evitar problemas de seguridad con consultas parametrizadas
            PreparedStatement preparedStatement = getConnection().prepareStatement(sentencia);

            // Establecer los valores de los parámetros
            preparedStatement.setString(1, revisar.getcod_revision_revisar());
            preparedStatement.setString(2, revisar.getvin_revisar());
            preparedStatement.setString(3, revisar.getcod_revisor_revisar());

            // Ejecutar la inserción
            preparedStatement.executeUpdate();

            // Confirmar la transacción
            getConnection().commit();
                JOptionPane.showMessageDialog(null, "Revisión añadida correctamente.");
        } catch (SQLException e) {
            // Deshacer la transacción en caso de error
            try {
                getConnection().rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            e.printStackTrace();
        }
    }

    public void borrarRevisarBD(String codigoRevision) {
        try {
            getConnection().setAutoCommit(false);
            // Preparar la sentencia SQL para la eliminación
            String sentencia = "DELETE FROM Revisar WHERE codigo = ?";

            // Utilizar PreparedStatement para evitar problemas de seguridad con consultas parametrizadas
            PreparedStatement preparedStatement = getConnection().prepareStatement(sentencia);

            // Establecer el valor del parámetro
            preparedStatement.setString(1, codigoRevision);

            // Ejecutar la eliminación
            preparedStatement.executeUpdate();

            // Confirmar la transacción
            getConnection().commit();
            JOptionPane.showMessageDialog(null, "Revisión borrada correctamente.");
        } catch (SQLException e) {
            // Deshacer la transacción en caso de error
            try {
                getConnection().rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            e.printStackTrace();
        }
    }

    public void modificarRevisarBD(Revisar revisar) {
        try {
            getConnection().setAutoCommit(false);
            // Preparar la sentencia SQL para la actualización
            String sentencia = "UPDATE Revisar SET vin_coche = ?, codigo_revisor = ? WHERE codigo = ?";

            // Utilizar PreparedStatement para evitar problemas de seguridad con consultas parametrizadas
            PreparedStatement preparedStatement = getConnection().prepareStatement(sentencia);

            // Establecer los valores de los parámetros
            preparedStatement.setString(1, revisar.getvin_revisar());
            preparedStatement.setString(2, revisar.getcod_revisor_revisar());
            preparedStatement.setString(3, revisar.getcod_revision_revisar());

            // Ejecutar la actualización
            preparedStatement.executeUpdate();

            // Confirmar la transacción
            getConnection().commit();
            JOptionPane.showMessageDialog(null, "Revisión modificada correctamente.");
        } catch (SQLException e) {
            // Deshacer la transacción en caso de error
            try {
                getConnection().rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            e.printStackTrace();
        }
    }
        
}
