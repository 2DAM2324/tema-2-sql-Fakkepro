/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import java.time.LocalDateTime;import Modelo.BasededatosSQLite;
import Modelo.Cliente;
import Modelo.Coche;
import Modelo.Comprar;
import Modelo.Proveedor;
import Modelo.Proveer;
import Modelo.Revisar;
import Modelo.Revisores;
import java.time.LocalDateTime;
import java.util.ArrayList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author adrian
 */
public class ControlTest {
    
    Control controlador = new Control();
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }
    
    @Test
    public void crearCliente(){
        
        controlador.aniadirCliente("00000000A", "Jose", "Joya", "Peligros", "653953465");
        
    }
    @Test
    public void crearGasolina(){
    
        controlador.aniadirGasolina("1234", "Hyundai", "Kona", 20000, "Negro", 55);
        
    }
    @Test
    public void crearElectrico(){
    
        controlador.aniadirElectrico("1235", "Tesla", "Model X", 55000, "Blanco", 550);
        
    }
    @Test
    public void crearRevisor(){
    
        controlador.aniadirRevisor("1234", "Pepe");
    }
    @Test
    public void crearProveedor(){
    
        controlador.aniadirProveedor("1234", "Juan");
        
    }
    @Test
    public void crearCompra(){
    
        controlador.aniadirCompra("0000AAA", LocalDateTime.now(), "00000000A", "1234");
        
    }
    @Test
    public void crearProvision(){
    
        controlador.aniadirProvision("1234", "1234", "1234");
        
    }
    @Test
    public void crearRevision(){
    
        controlador.aniadirRevision("1234", "1234", "1234");
        
    }
    @Test
    public void modificarCliente(){
    
        controlador.modificarCliente("00000000A", "Adrian", "Joya", "Peligros", "653953465");
    
    }
    @Test
    public void modificarCoche(){
    
        controlador.modificarCoche(0, 55, "1234", "Mercedes", "C63s", 1300000, "Negro");
    
    }
    @Test
    public void modificarProveedor(){
    
        controlador.modificarProveedor("1234", "Paco");
    
    }
    @Test
    public void modificarRevisor(){
    
        controlador.modificarRevisor("1234", "Manolo");
    
    }
    @Test
    public void modificarCompra(){
    
        controlador.modificarCompra("0000AAA", LocalDateTime.now(), "00000000A", "12345");
    
    }
    @Test
    public void modificarRevision(){
    
        controlador.modificarRevision("1234", "1234", "12345");
    
    }
    @Test
    public void modificarProvision(){
    
        controlador.modificarProvision("1234", "1234", "1235");
    
    }
    @Test
    public void borrarCliente(){
    
        controlador.eliminarCliente("00000000A");
    
    }
    @Test
    public void borrarGasolina(){
    
        controlador.eliminarCoche("1234");
    
    }
    @Test
    public void borrarElectrico(){
    
        controlador.eliminarCoche("1235");
    
    }
    @Test
    public void borrarProveedor(){
    
        controlador.eliminarProveedor("1234");
    
    }
    @Test
    public void borrarRevisor(){
    
        controlador.eliminarRevisor("1234");
    
    }
    @Test
    public void borrarCompra(){
    
        controlador.eliminarCompra("0000AAA");
    
    }
   @Test 
    public void borrarProvision(){
    
        controlador.eliminarProvision("1234");
    
    }
    @Test
    public void borrarRevision(){
    
        controlador.eliminarRevision("1234");
    
    }
    
    
}
