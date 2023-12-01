    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Proveedor;
import Modelo.Coche;
import Modelo.Gasolina;
import Modelo.Electrico;
import Modelo.Cliente;
import Modelo.Revisores;
import Modelo.Revisar;
import Modelo.Comprar;
import Modelo.Proveer;
import Modelo.Basededatosserializable;
import Modelo.BasededatosSQLite;
import Interfaz.Ventana1;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

/**
 *
 * @author adrian
 */
public class Control {
    
    private BasededatosSQLite bd = new BasededatosSQLite();

    
    
    public void aniadirCliente(String dni, String nombre, String direccion, String ciudad, String numero_telefono){

        Cliente cliente = new Cliente(dni, nombre, direccion, ciudad, numero_telefono);
        bd.aniadirCLienteBD(cliente);
                
    }
    
    public void eliminarCliente(String dni){
        
        bd.borrarClienteBD(dni);

    }
    
    public void modificarCliente(String dni, String nombre, String direccion, String ciudad, String telefono){
        
        Cliente cliente = new Cliente(dni, nombre, direccion, ciudad, telefono);
        bd.modificarClienteBD(cliente);
        
    }
    
    public boolean comprobarClienteExiste(String dni){
    
        boolean existe = false;
        ArrayList<Cliente> vector_clientes = new ArrayList<>();
        vector_clientes = bd.getClientes();
        
        for(Cliente cli : vector_clientes){
        
            if(cli.getdni_cliente().equalsIgnoreCase(dni))
            {         
                existe = true;
            }
        
        }
    
        return existe;
    }
    
    public Cliente buscarCliente(String dni){
    
        Cliente cliente = new Cliente("", "", "", "", "");
        ArrayList<Cliente> vector_clientes = new ArrayList<>();
        vector_clientes = bd.getClientes();
        int i = 0;
        
        for(Cliente cli : vector_clientes){
        
            if(cli.getdni_cliente().equalsIgnoreCase(dni))
            {         
                cliente = vector_clientes.get(i);
            }
        i++;
        }
    
        return cliente;
    }
    
    public ArrayList<Cliente> getClientes(){
    
        ArrayList<Cliente> vector_clientes = new ArrayList<>();
        vector_clientes = bd.getClientes();
                
    return vector_clientes;
    }
    
    public void aniadirGasolina(String vin, String marca, String modelo, float precio, String color, int deposito){
    
        Gasolina gasolina = new Gasolina(deposito, vin, marca, modelo, precio, color);
        bd.aniadirGasolinaBD(gasolina);
    }
    
    public void aniadirElectrico(String vin, String marca, String modelo, float precio, String color, double bateria){
    
        Electrico electrico = new Electrico(bateria, vin, marca, modelo, precio, color);
        bd.aniadirElectricoBD(electrico);
    }
    
    public void eliminarCoche(String vin){
        
        bd.borrarElectricoBD(vin);
        bd.borrarGasolinaBD(vin);

    }
    
    public void modificarCoche(double bateria, int deposito, String vin, String marca, String modelo, float precio, String color){
        
        if(deposito != 0)
        {
            Gasolina gasolina = new Gasolina(deposito, vin, marca, modelo, precio, color);
            bd.modificarGasolinaBD(gasolina);
        }
        else if (bateria != 0.0)
        {
            Electrico electrico = new Electrico(bateria, vin, marca, modelo, precio, color);
            bd.modificarElectricoBD(electrico);
        }
        
    }
    
    public boolean comprobarCocheExiste(String vin){

        boolean existe = false, existeGasolina = false, existeElectrico = false;
        ArrayList<Coche> vector_cochesElectrico = new ArrayList<>();
        ArrayList<Coche> vector_cochesGasolina = new ArrayList<>();
        vector_cochesElectrico = bd.getElectrico();
        vector_cochesGasolina = bd.getGasolina();
        
        for(Coche co : vector_cochesElectrico){
        
            if(co.getvin_coche().equalsIgnoreCase(vin))
            {         
                existeElectrico = true;
            }
        
        }
        
        for(Coche co : vector_cochesGasolina){
        
            if(co.getvin_coche().equalsIgnoreCase(vin))
            {         
                existeGasolina = true;
            }
        
        }
        
        if(existeGasolina == true || existeElectrico == true)
        {
            existe = true;
        }
    
        return existe;
    }
    
    public ArrayList<Coche> getCochesElectrico(){
    
        ArrayList<Coche> vector_coches = new ArrayList<>();
        vector_coches = bd.getElectrico();
        
    return vector_coches;
    }
    
    public ArrayList<Coche> getCochesGasolina(){
    
        ArrayList<Coche> vector_coches = new ArrayList<>();
        vector_coches = bd.getGasolina();
        
    return vector_coches;
    }
    
    public void aniadirProveedor(String codigo, String nombre){
    
        Proveedor proveedor = new Proveedor(codigo, nombre);
        bd.aniadirProveedorBD(proveedor);
    
    }
    
    public void eliminarProveedor(String codigo){
        
       bd.borrarProveedorBD(codigo);

    }
    
    public void modificarProveedor(String codigo, String nombre){
        
        Proveedor proveedor = new Proveedor(codigo, nombre);
        bd.modificarProveedorBD(proveedor);

    }
    
    public boolean comprobarProveedorExiste(String codigo){
    
        boolean existe = false;
        ArrayList<Proveedor> vector_proveedores = new ArrayList<>();
        vector_proveedores = bd.getProveedor();
        
        for(Proveedor pro : vector_proveedores){
        
            if(pro.getcod_proveedor().equalsIgnoreCase(codigo))
            {         
                existe = true;
            }
        
        }
    
        return existe;
    }
    
    public ArrayList<Proveedor> getProveedores(){
    
        ArrayList<Proveedor> vector_proveedores = new ArrayList<>();
        vector_proveedores = bd.getProveedor();
        
    return vector_proveedores;
    }
    
    public void aniadirRevisor(String codigo, String nombre){
    
        Revisores revisor = new Revisores(codigo, nombre);
        bd.aniadirRevisorBD(revisor);
    
    }
    
    public void eliminarRevisor(String codigo){
        
        bd.borrarRevisorBD(codigo);

    }
    
    public void modificarRevisor(String codigo, String nombre){
 
        Revisores revisor = new Revisores(codigo, nombre);
        bd.modificarRevisorBD(revisor);

    }
    
    public boolean comprobarRevisorExiste(String codigo){
    
        boolean existe = false;
        ArrayList<Revisores> vector_revisores = new ArrayList<>();
        vector_revisores = bd.getRevisor();
        
        for(Revisores revi : vector_revisores){
        
            if(revi.getcod_revisor_revisores().equalsIgnoreCase(codigo))
            {        
                existe = true;
                return existe;
            }
        
        }
        return existe;
    }
    
    public ArrayList<Revisores> getRevisores(){
    
        ArrayList<Revisores> vector_revisores = new ArrayList<>();
        vector_revisores = bd.getRevisor();
        
    return vector_revisores;
    }
    
    public void aniadirCompra(String matricula, LocalDateTime fechayhora, String dni, String vin){

        Comprar compra = new Comprar(matricula, fechayhora, dni, vin);
        bd.aniadirComprarBD(compra);
    }
    
    public void eliminarCompra(String matricula){
        
        bd.borrarComprarBD(matricula);

    }
    
    public void modificarCompra(String matricula, LocalDateTime fechayhora, String dni, String vin){
        
        Comprar compra = new Comprar(matricula, fechayhora, dni, vin);
        bd.modificarComprarBD(compra);

    }
    
    public boolean comprobarCompraExiste(String matricula, LocalDateTime fecha){
    
        boolean existe = false;
        ArrayList<Comprar> vector_compras = new ArrayList<>();
        vector_compras = bd.getCompra();
        
        for(Comprar com : vector_compras){
        
            if(com.getmatricula_comprar().equalsIgnoreCase(matricula))
            {         
                return true;
            }
        
        }
        
        return existe;
    }
    
    public boolean comprobarCompraCocheCliente(String matricula, String dni, String vin){
    
        boolean puedeComprarlo = false;
        ArrayList<Comprar> vector_compras = new ArrayList<>();
        vector_compras = bd.getCompra();
        ArrayList<Comprar> vector_compras_ordenado = new ArrayList<>();
        
        for(int i = vector_compras.size() - 1; i > -1; i--){
        
            vector_compras_ordenado.add(vector_compras.get(i));
        
        }
        
        for(Comprar com : vector_compras_ordenado){
        
            if((com.getvin_comprar().equalsIgnoreCase(vin)) && (com.getdni_comprar().equalsIgnoreCase(dni) == true) && (com.getmatricula_comprar().equalsIgnoreCase(matricula) == true))
            {
                puedeComprarlo = false;
                return puedeComprarlo;
            }
             
            if((((com.getvin_comprar().equalsIgnoreCase(vin) == false) && (com.getdni_comprar().equalsIgnoreCase(dni) == true) && (com.getmatricula_comprar().equalsIgnoreCase(matricula) == true))) || (((com.getvin_comprar().equalsIgnoreCase(vin)) && (com.getdni_comprar().equalsIgnoreCase(dni) == false) && (com.getmatricula_comprar().equalsIgnoreCase(matricula) == true))) || (((com.getvin_comprar().equalsIgnoreCase(vin)) && (com.getdni_comprar().equalsIgnoreCase(dni) == true) && (com.getmatricula_comprar().equalsIgnoreCase(matricula) == false))))
            {
                puedeComprarlo = true;
                return puedeComprarlo;
            }
        
        }
        
    return puedeComprarlo;
    }
    
    public Comprar buscarCompra(String matricula, LocalDateTime fecha){
    
        Comprar compra = new Comprar();
        ArrayList<Comprar> vector_compras = new ArrayList<>();
        vector_compras = bd.getCompra();
        int i = 0;
        
        for(Comprar com : vector_compras){
        
            if((com.getmatricula_comprar().equalsIgnoreCase(matricula)) && (com.getFechayHora() == fecha))
            {         
                compra = vector_compras.get(i);
            }
        i++;
        }
        JOptionPane.showMessageDialog(null, "No se ha encontrado la compra.");
        return compra;
    }
    
    public ArrayList<Comprar> getCompras(){
    
        ArrayList<Comprar> vector_compras = new ArrayList<>();
        vector_compras = bd.getCompra();
                
    return vector_compras;
    }
        
   public void aniadirRevision(String codigo_revision, String codigo_revisor, String vin){

       Revisar revision = new Revisar(codigo_revision, vin, codigo_revisor);
       bd.aniadirRevisarBD(revision);
    
    }
    
    public void eliminarRevision(String codigo){

        bd.borrarRevisarBD(codigo);
        
    }
    
    public void modificarRevision(String codigo_revision, String codigo_revisor, String vin){
        
        Revisar revision = new Revisar(codigo_revision, vin, codigo_revisor);
        bd.modificarRevisarBD(revision);
        
    }
    
    public boolean comprobarRevisionExiste(String codigo){
    
        boolean existe = false;
        ArrayList<Revisar> vector_revisiones = new ArrayList<>();
        vector_revisiones = bd.getRevisiones();
        
        for(Revisar revi : vector_revisiones){
        
            if(revi.getcod_revision_revisar().equalsIgnoreCase(codigo))
            {         
                existe = true;
            }
        
        }
    
        return existe;
    }
    
    public boolean comprobarRevisionCocheRevisor(String codigo_revision, String codigo_revisor, String vin){
    
        boolean revisado = false;
        ArrayList<Revisar> vector_revisiones = new ArrayList<>();
        vector_revisiones = bd.getRevisiones();
        
        for(Revisar revi : vector_revisiones){
        
            if((revi.getcod_revisor_revisar().equalsIgnoreCase(codigo_revisor) == true) || (revi.getvin_revisar().equalsIgnoreCase(vin)))
            {
                revisado = true;
                return revisado;
            }
            
        }
        
    return revisado;
    }
    
    public Revisar buscarRevision(String codigo){
    
        Revisar revision = new Revisar("", "", "");
        ArrayList<Revisar> vector_revisiones = new ArrayList<>();
        vector_revisiones = bd.getRevisiones();
        int i = 0;
        
        for(Revisar revi : vector_revisiones){
        
            if((revi.getcod_revision_revisar().equalsIgnoreCase(codigo)))
            {         
                revision = vector_revisiones.get(i);
            }
        i++;
        }
    
        return revision;
    }
    
    public ArrayList<Revisar> getRevisiones(){
    
        ArrayList<Revisar> vector_revisiones = new ArrayList<>();
        vector_revisiones = bd.getRevisiones();
                
    return vector_revisiones;
    }
    
    public void aniadirProvision(String codigo, String codigo_proveedor, String vin){
    
        Proveer provision = new Proveer(codigo, codigo_proveedor, vin);
        bd.aniadirProveerBD(provision);
    
    }
    
    public void eliminarProvision(String codigo){
        
      bd.borrarProveerBD(codigo);
    }
    
    public void modificarProvision(String codigo, String codigo_proveedor, String vin){
        
        Proveer provision = new Proveer(codigo, codigo_proveedor, vin);
        bd.modificarProveerBD(provision);

    }
    
    public boolean comprobarProvisionExiste(String codigo_proveedor, String vin){
    
        boolean existe_proveedor = false, existe_coche = false, existe = false;
        ArrayList<Proveer> vector_provisiones = new ArrayList<>();
        vector_provisiones = bd.getProvisiones();
        ArrayList<Coche> vector_electrico = new ArrayList<>();
        ArrayList<Coche> vector_gasolina = new ArrayList<>();
        vector_electrico = bd.getElectrico();
        vector_gasolina = bd.getGasolina();
        
        for(Proveer prove : vector_provisiones){
        
            if((prove.getcod_proveedor_proveer().equalsIgnoreCase(codigo_proveedor)))
            {         
                existe_proveedor = true;
            }
        
        }
        
        for(Coche coche : vector_electrico){
        
            if((coche.getvin_coche().equalsIgnoreCase(vin)))
            {         
                existe_coche = true;
            }
        
        }
        
        for(Coche coche : vector_gasolina){
        
            if((coche.getvin_coche().equalsIgnoreCase(vin)))
            {         
                existe_coche = true;
            }
        
        }
        
        if((existe_proveedor == true) && (existe_coche == true))
        {
            existe = true;
        }
    
        return existe;
    }
    
    public boolean comprobarProvisionCocheProveedor(String codigo_proveedor, String vin){
    
        boolean proveido = false;
        ArrayList<Proveer> vector_provisiones = new ArrayList<>();
        vector_provisiones = bd.getProvisiones();
        
        for(Proveer prove : vector_provisiones){
        
            if((prove.getcod_proveedor_proveer().equalsIgnoreCase(codigo_proveedor)) && (prove.getvin_proveer().equalsIgnoreCase(vin)))
            {
                proveido = true;
                return proveido;
            }
        
        }
    return proveido;
    }
    
    public Proveer buscarProvision(String codigo){
    
        Proveer provision = new Proveer("", "", "");
        ArrayList<Proveer> vector_provisiones = new ArrayList<>();
        vector_provisiones = bd.getProvisiones();
        int i = 0;
        
        for(Proveer prove : vector_provisiones){
        
            if((prove.getcod_proveedor_proveer().equalsIgnoreCase(codigo)))
            {         
                provision = vector_provisiones.get(i);
            }
        i++;
        }
        
        return provision;
    }
    
    public ArrayList<Proveer> getProvisiones(){
    
        ArrayList<Proveer> vector_provisiones = new ArrayList<>();
        vector_provisiones = bd.getProvisiones();
                
    return vector_provisiones;
    }
    
    public boolean filtrarDni(String dni){
        
        if (dni.length() != 9) 
        {
            JOptionPane.showMessageDialog(null, "El DNI esta compuesto por 8 dígitos y una letra mayuscula al final");
            return false;
        }

        // Extraer el número y la letra del DNI
        String numeroStr = dni.substring(0, 8);
        String letra = dni.substring(8, 9);

        // Comprobar que el número es numérico
        try 
        {
            int numero = Integer.parseInt(numeroStr);
        } 
        catch (NumberFormatException e)
        {
            return false;
        }

        // Calcular la letra correspondiente al número
        String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
        int numeroDNI = Integer.parseInt(numeroStr);
        int indiceLetra = numeroDNI % 23;
        char letraCalculada = letras.charAt(indiceLetra);

        // Comparar la letra calculada con la letra proporcionada
        return letra.equalsIgnoreCase(String.valueOf(letraCalculada));
    }
    
    
    public boolean filtrarNumeroTelefono(String telefono){
    
        if(telefono.length() != 9)
        {
            JOptionPane.showMessageDialog(null, "El número de telefono esta compuesto por 9 dígitos");
            return false;
        }
        
        // Comprobar que el número es numérico
        try 
        {
            int numero = Integer.parseInt(telefono);
        }
        catch (NumberFormatException e) 
        {
            JOptionPane.showMessageDialog(null, "El número de telefono esta compuesto por 9 dígitos");
            return false;
        }
        
    return true;
    }
    
    public boolean filtrarVin(String vin){
    
        if(vin.length() != 4)
        {
            JOptionPane.showMessageDialog(null, "El VIN esta compuesto por 4 dígitos");
            return false;
        }
        
        // Comprobar que el número es numérico
        try 
        {
            int numero = Integer.parseInt(vin);
        }
        catch (NumberFormatException e) 
        {
            JOptionPane.showMessageDialog(null, "El VIN esta compuesto por 4 dígitos");
            return false;
        }
        
    return true;
    }
    
    public boolean filtrarCodigoProveedor(String codigo){
    
        if(codigo.length() != 5)
        {
            JOptionPane.showMessageDialog(null, "El código esta compuesto por 5 dígitos");
            return false;
        }
        
        // Comprobar que el número es numérico
        try 
        {
            int numero = Integer.parseInt(codigo);
        }
        catch (NumberFormatException e) 
        {
            JOptionPane.showMessageDialog(null, "El código esta compuesto por 5 dígitos");
            return false;
        }
        
    return true;
    }
    
    public boolean filtrarCodigoRevisor(String codigo){
    
        if (codigo.length() != 4)
        {
            JOptionPane.showMessageDialog(null, "El código esta compuesto por 3 dígitos y 1 letra al final");
            return false;
        }

        // Verificar los primeros 3 caracteres (deben ser dígitos)
        for (int i = 0; i < 3; i++)
        {
            char c = codigo.charAt(i);
            
            if (!Character.isDigit(c)) 
            {
                JOptionPane.showMessageDialog(null, "El código esta compuesto por 3 dígitos y 1 letra al final");
                return false;
            }
        }

        // Verificar el último carácter (debe ser una letra)
        char lastChar = codigo.charAt(codigo.length() - 1);
        
    return Character.isLetter(lastChar);
    }
    
    public boolean filtrarCodigoProvision(String codigo){
    
        if (codigo.length() != 5)
        {
            JOptionPane.showMessageDialog(null, "El código esta compuesto por 4 dígitos y 1 letra al final");
            return false;
        }

        // Verificar los primeros 4 caracteres (deben ser dígitos)
        for (int i = 0; i < 4; i++)
        {
            char c = codigo.charAt(i);
            
            if (!Character.isDigit(c)) 
            {
                JOptionPane.showMessageDialog(null, "El código esta compuesto por 4 dígitos y 1 letra al final");
                return false;
            }
        }

        // Verificar el último carácter (debe ser una letra)
        char lastChar = codigo.charAt(codigo.length() - 1);
        
    return Character.isLetter(lastChar);
    }
    
    public boolean filtrarCodigoRevision(String codigo){
    
        if (codigo.length() != 5)
        {
            JOptionPane.showMessageDialog(null, "El código esta compuesto por 4 dígitos y 1 letra al final");
            return false;
        }

        // Verificar los primeros 4 caracteres (deben ser dígitos)
        for (int i = 0; i < 4; i++)
        {
            char c = codigo.charAt(i);
            
            if (!Character.isDigit(c)) 
            {
                JOptionPane.showMessageDialog(null, "El código esta compuesto por 4 dígitos y 1 letra al final");
                return false;
            }
        }

        // Verificar el último carácter (debe ser una letra)
        char lastChar = codigo.charAt(codigo.length() - 1);
        
    return Character.isLetter(lastChar);
    }
    
    public boolean filtrarMatricula(String matricula){
    
        if (matricula.length() != 7) 
        {
            JOptionPane.showMessageDialog(null, "La matricula esta compuesta por 4 dígitos y 3 letras");
            return false;
        }

        // Comprueba los primeros 4 caracteres (deben ser dígitos)
        for (int i = 0; i < 4; i++) 
        {
            char c = matricula.charAt(i);
            
            if (!Character.isDigit(c)) 
            {
                JOptionPane.showMessageDialog(null, "La matricula esta compuesta por 4 dígitos y 3 letras");
                return false;
            }
        }

        // Comprueba los últimos 3 caracteres (deben ser letras mayúsculas)
        for (int i = 4; i < 7; i++)
        {
            char c = matricula.charAt(i);
            
            if (!Character.isLetter(c) || !Character.isUpperCase(c)) 
            {
                JOptionPane.showMessageDialog(null, "La matricula esta compuesta por 4 dígitos y 3 letras");
                return false;
            }
        }
        
    return true;
    }
}
