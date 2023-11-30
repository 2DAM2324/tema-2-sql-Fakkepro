/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import java.awt.Color;
import java.awt.Font;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import org.w3c.dom.Text;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;
import Controlador.Control;
import Modelo.Proveedor;
import Modelo.Coche;
import Modelo.Gasolina;
import Modelo.Electrico;
import Modelo.Cliente;
import Modelo.Revisores;
import Modelo.Revisar;
import Modelo.Comprar;
import Modelo.Proveer;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Patricia Burgos
 */
public class Ventana1 extends javax.swing.JFrame {
    
    private Control controlador;
    private DefaultTableModel ModeloTablaClientes;
    private DefaultTableModel ModeloTablaCoches;
    private DefaultTableModel ModeloTablaProveedores;
    private DefaultTableModel ModeloTablaRevisores;
    private DefaultTableModel ModeloTablaCompras;
    private DefaultTableModel ModeloTablaRevisiones;
    private DefaultTableModel ModeloTablaProvisiones;
    
    /**
     * Creates new form Ventana1
     * @throws java.io.IOException
     * @throws java.io.FileNotFoundException
     * @throws java.lang.ClassNotFoundException
     * @throws java.io.NotSerializableException
     * @throws org.xml.sax.SAXException
     */
    public Ventana1() throws IOException, FileNotFoundException, ClassNotFoundException, NotSerializableException, SAXException {
        initComponents();
        controlador = new Control();
        
        ModeloTablaClientes = new DefaultTableModel();
        ModeloTablaClientes.addColumn("DNI");
        ModeloTablaClientes.addColumn("Nombre");
        ModeloTablaClientes.addColumn("Teléfono");
        ModeloTablaClientes.addColumn("Dirección");
        ModeloTablaClientes.addColumn("Ciudad");
        this.Tabla_Clientes.setModel(ModeloTablaClientes);
        
        ModeloTablaCoches = new DefaultTableModel();
        ModeloTablaCoches.addColumn("VIN");
        ModeloTablaCoches.addColumn("Marca");
        ModeloTablaCoches.addColumn("Modelo");
        ModeloTablaCoches.addColumn("Color");
        ModeloTablaCoches.addColumn("Precio");
        ModeloTablaCoches.addColumn("Deposito");
        ModeloTablaCoches.addColumn("Bateria");
        this.Tabla_Coches.setModel(ModeloTablaCoches);
        
        ModeloTablaRevisores = new DefaultTableModel();
        ModeloTablaRevisores.addColumn("Identificador");
        ModeloTablaRevisores.addColumn("Nombre");
        this.Tabla_Revisores.setModel(ModeloTablaRevisores);
        
        ModeloTablaProveedores = new DefaultTableModel();
        ModeloTablaProveedores.addColumn("Identificador");
        ModeloTablaProveedores.addColumn("Nombre");
        this.Tabla_Provedores.setModel(ModeloTablaProveedores);
        
        ModeloTablaCompras = new DefaultTableModel();
        ModeloTablaCompras.addColumn("DNI");
        ModeloTablaCompras.addColumn("VIN");
        ModeloTablaCompras.addColumn("Matricula");
        ModeloTablaCompras.addColumn("Fecha");
        this.Tabla_Compras.setModel(ModeloTablaCompras);
        
        ModeloTablaRevisiones = new DefaultTableModel();
        ModeloTablaRevisiones.addColumn("Codigo");
        ModeloTablaRevisiones.addColumn("Codigo_revisor");
        ModeloTablaRevisiones.addColumn("VIN");
        this.Tabla_Reviones.setModel(ModeloTablaRevisiones);
        
        ModeloTablaProvisiones = new DefaultTableModel();
        ModeloTablaProvisiones.addColumn("Codigo");
        ModeloTablaProvisiones.addColumn("Codigo_proveedor");
        ModeloTablaProvisiones.addColumn("VIN");
        this.Tabla_Provisiones.setModel(ModeloTablaProvisiones);
        
       
        this.cargarClientes();
        this.cargarCoches();
        this.cargarRevisores();
        this.cargarProvedores();
        this.cargarCompras();
        this.cargarRevisiones();
        this.cargarProvisiones();
        
    }
    
    public DefaultTableModel getModeloTablaClientes() {
        return ModeloTablaClientes;
    }

    public void setModeloTablaClientes(DefaultTableModel ModeloTablaClientes) {
        
        this.ModeloTablaClientes = ModeloTablaClientes;
        
    }

    public DefaultTableModel getModeloTablaCoches() {
        
        return ModeloTablaCoches;
        
    }

    public void setModeloTablaCoches(DefaultTableModel ModeloTablaCoches) {
        
        this.ModeloTablaCoches = ModeloTablaCoches;
        
    }

    public DefaultTableModel getModeloTablaProveedores() {
        
        return ModeloTablaProveedores;
        
    }

    public void setModeloTablaProveedores(DefaultTableModel ModeloTablaProveedores) {
        
        this.ModeloTablaProveedores = ModeloTablaProveedores;
        
    }

    public DefaultTableModel getModeloTablaRevisores() {
        
        return ModeloTablaRevisores;
        
    }

    public void setModeloTablaRevisores(DefaultTableModel ModeloTablaRevisores) {
        
        this.ModeloTablaRevisores = ModeloTablaRevisores;
        
    }
    
    public void cargarClientes(){
    
        ArrayList<Cliente> vector_clientes = controlador.getClientes();
        
        ModeloTablaClientes.setRowCount(0);
        
        for(int i = 0; i < vector_clientes.size(); i++)
        {
            this.ModeloTablaClientes.addRow(new Object[] {vector_clientes.get(i).getdni_cliente(), vector_clientes.get(i).getnombre_cliente(), vector_clientes.get(i).getnumero_telefono_cliente(), vector_clientes.get(i).getdireccion_cliente(), vector_clientes.get(i).getciudad_cliente()});  
        }
        this.ModeloTablaClientes.fireTableDataChanged();
    
    }
    
    public void cargarCoches(){
    
        cargarCochesGasolina();
        cargarCochesElectricos();
    
    }
    
    public void cargarCochesElectricos(){
    
        ArrayList<Coche> vector_coches = controlador.getCochesElectrico();
        
        for(int i = 0; i < vector_coches.size(); i++)
        {
            if(vector_coches.get(i) instanceof Gasolina)
            {
                this.ModeloTablaCoches.addRow(new Object[] {vector_coches.get(i).getvin_coche(), vector_coches.get(i).getmarca_coche(), vector_coches.get(i).getmodelo_coche(), vector_coches.get(i).getcolor_coche(), vector_coches.get(i).getprecio_coche(), ((Gasolina) vector_coches.get(i)).getdeposito_gasolina(), ""});  
            }
            else if(vector_coches.get(i) instanceof Electrico)
            {
                this.ModeloTablaCoches.addRow(new Object[] {vector_coches.get(i).getvin_coche(), vector_coches.get(i).getmarca_coche(), vector_coches.get(i).getmodelo_coche(), vector_coches.get(i).getcolor_coche(), vector_coches.get(i).getprecio_coche(), "", ((Electrico) vector_coches.get(i)).getbateria_electrico()});  
            }
            
            }
        this.ModeloTablaCoches.fireTableDataChanged();
    
    }
    
    public void cargarCochesGasolina(){
    
        ArrayList<Coche> vector_coches = controlador.getCochesGasolina();
        
        ModeloTablaCoches.setRowCount(0);
        
        for(int i = 0; i < vector_coches.size(); i++)
        {
            if(vector_coches.get(i) instanceof Gasolina)
            {
                this.ModeloTablaCoches.addRow(new Object[] {vector_coches.get(i).getvin_coche(), vector_coches.get(i).getmarca_coche(), vector_coches.get(i).getmodelo_coche(), vector_coches.get(i).getcolor_coche(), vector_coches.get(i).getprecio_coche(), ((Gasolina) vector_coches.get(i)).getdeposito_gasolina(), ""});  
            }
            else if(vector_coches.get(i) instanceof Electrico)
            {
                this.ModeloTablaCoches.addRow(new Object[] {vector_coches.get(i).getvin_coche(), vector_coches.get(i).getmarca_coche(), vector_coches.get(i).getmodelo_coche(), vector_coches.get(i).getcolor_coche(), vector_coches.get(i).getprecio_coche(), "", ((Electrico) vector_coches.get(i)).getbateria_electrico()});  
            }
            
            }
        this.ModeloTablaCoches.fireTableDataChanged();
    
    }
    
    public void cargarRevisores(){
    
        ArrayList<Revisores> vector_revisores = controlador.getRevisores();
        
        ModeloTablaRevisores.setRowCount(0);
        
        for(int i = 0; i < vector_revisores.size(); i++)
        {
            this.ModeloTablaRevisores.addRow(new Object[] {vector_revisores.get(i).getcod_revisor_revisores(), vector_revisores.get(i).getNombre_revisores()});  
        } 
        this.ModeloTablaRevisores.fireTableDataChanged();
    
    }
    
    public void cargarProvedores(){
    
        ArrayList<Proveedor> vector_proveedores = controlador.getProveedores();
        
        ModeloTablaProveedores.setRowCount(0);
       
        for(int i = 0; i < vector_proveedores.size(); i++)
        {
            this.ModeloTablaProveedores.addRow(new Object[] {vector_proveedores.get(i).getcod_proveedor(), vector_proveedores.get(i).getnombre_provedor()});  
        }
        this.ModeloTablaProveedores.fireTableDataChanged();
    
    }
    public void cargarCompras(){
    
        ArrayList<Comprar> vector_compras = controlador.getCompras();
        
        ModeloTablaCompras.setRowCount(0);
        
        //this.ModeloTablaCompras.addRow(new Object[] {"Prueba", "Prueba", "Prueba", 3});  
        
        for(int i = 0; i < vector_compras.size(); i++)
        {
            this.ModeloTablaCompras.addRow(new Object[] {vector_compras.get(i).getdni_comprar(), vector_compras.get(i).getvin_comprar(), vector_compras.get(i).getmatricula_comprar(), vector_compras.get(i).getFechayHora().toString()});  
        }
        this.ModeloTablaCompras.fireTableDataChanged();
    
    }
    public void cargarRevisiones(){
    
        ArrayList<Revisar> vector_revisiones = controlador.getRevisiones();
        
        ModeloTablaRevisiones.setRowCount(0);
        
        for(int i = 0; i < vector_revisiones.size(); i++)
        {
            this.ModeloTablaRevisiones.addRow(new Object[] {vector_revisiones.get(i).getcod_revision_revisar(), vector_revisiones.get(i).getcod_revisor_revisar(), vector_revisiones.get(i).getvin_revisar()});  
        }
        this.ModeloTablaRevisiones.fireTableDataChanged();
    
    }
    public void cargarProvisiones(){
    
        ArrayList<Proveer> vector_provisiones = controlador.getProvisiones();
        
        ModeloTablaProvisiones.setRowCount(0);
        
        for(int i = 0; i < vector_provisiones.size(); i++)
        {
            this.ModeloTablaProvisiones.addRow(new Object[] {vector_provisiones.get(i).getCodigo_proveer(), vector_provisiones.get(i).getcod_proveedor_proveer(), vector_provisiones.get(i).getvin_proveer()});  
        }
        this.ModeloTablaProvisiones.fireTableDataChanged();
    
    }
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane = new javax.swing.JTabbedPane();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel_ciudad = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Tabla_Clientes = new javax.swing.JTable();
        jButton_aniadir_ciudad = new javax.swing.JButton();
        jButton_modificar_ciudad = new javax.swing.JButton();
        jButton_borrar_ciudad = new javax.swing.JButton();
        jLabel_nombre_ciudad = new javax.swing.JLabel();
        jLabel_pais = new javax.swing.JLabel();
        jLabel_num_habitantes = new javax.swing.JLabel();
        dni_cliente = new javax.swing.JTextField();
        nombre_cliente = new javax.swing.JTextField();
        telefono_cliente = new javax.swing.JTextField();
        guardarCliente = new javax.swing.JButton();
        cancelarCliente = new javax.swing.JButton();
        direccion_cliente = new javax.swing.JTextField();
        jLabel_num_habitantes1 = new javax.swing.JLabel();
        ciudad_cliente = new javax.swing.JTextField();
        jLabel_num_habitantes2 = new javax.swing.JLabel();
        jPanel_biblioteca = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        Tabla_Coches = new javax.swing.JTable();
        jLabel_nombre_biblioteca = new javax.swing.JLabel();
        jLabel_telefono = new javax.swing.JLabel();
        jLabel_direccion = new javax.swing.JLabel();
        vin_coche = new javax.swing.JTextField();
        marca_coche = new javax.swing.JTextField();
        modelo_coche = new javax.swing.JTextField();
        guardarCoche = new javax.swing.JButton();
        cancelarCoche = new javax.swing.JButton();
        jButton_aniadir_biblioteca = new javax.swing.JButton();
        jButton_modificar_biblioteca = new javax.swing.JButton();
        jButton_borrar_biblioteca = new javax.swing.JButton();
        color_coche = new javax.swing.JTextField();
        jLabel_direccion5 = new javax.swing.JLabel();
        precio_coche = new javax.swing.JTextField();
        jLabel_direccion6 = new javax.swing.JLabel();
        deposito_coche = new javax.swing.JTextField();
        jLabel_direccion7 = new javax.swing.JLabel();
        jLabel_direccion8 = new javax.swing.JLabel();
        bateria_coche = new javax.swing.JTextField();
        jPanel_libro = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        Tabla_Revisores = new javax.swing.JTable();
        jLabel_nombre_libro = new javax.swing.JLabel();
        id_revisor = new javax.swing.JTextField();
        guardarRevisor = new javax.swing.JButton();
        cancelarRevisor = new javax.swing.JButton();
        jButton_borrar_libro = new javax.swing.JButton();
        jButton_modificar_libro = new javax.swing.JButton();
        jButton_aniadir_libro = new javax.swing.JButton();
        jLabel_nombre_libro1 = new javax.swing.JLabel();
        nombre_revisor = new javax.swing.JTextField();
        jPanel_persona = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        Tabla_Provedores = new javax.swing.JTable();
        jLabel_nombre_persona = new javax.swing.JLabel();
        id_provedor = new javax.swing.JTextField();
        guardarProvedor = new javax.swing.JButton();
        cancelarProvedor = new javax.swing.JButton();
        jButton_borrar_persona = new javax.swing.JButton();
        jButton_modificar_persona = new javax.swing.JButton();
        jButton_aniadir_persona = new javax.swing.JButton();
        nombre_provedor = new javax.swing.JTextField();
        jLabel_nombre_persona4 = new javax.swing.JLabel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel_persona1 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        Tabla_Compras = new javax.swing.JTable();
        jLabel_DNI1 = new javax.swing.JLabel();
        jLabel_edad_persona1 = new javax.swing.JLabel();
        vin_compra = new javax.swing.JTextField();
        dni_compra = new javax.swing.JTextField();
        guardarCompra = new javax.swing.JButton();
        cancelarCompra = new javax.swing.JButton();
        jButton_borrar_persona1 = new javax.swing.JButton();
        jButton_modificar_persona1 = new javax.swing.JButton();
        jButton_aniadir_persona1 = new javax.swing.JButton();
        jLabel_ciudad_natal1 = new javax.swing.JLabel();
        matricula_compra = new javax.swing.JTextField();
        jPanel_persona2 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        Tabla_Reviones = new javax.swing.JTable();
        jLabel_DNI2 = new javax.swing.JLabel();
        jLabel_edad_persona2 = new javax.swing.JLabel();
        vin_revisar = new javax.swing.JTextField();
        codigo_revisar = new javax.swing.JTextField();
        guardarRevision = new javax.swing.JButton();
        cancelarRevision = new javax.swing.JButton();
        jButton_borrar_persona2 = new javax.swing.JButton();
        jButton_modificar_persona2 = new javax.swing.JButton();
        jButton_aniadir_persona2 = new javax.swing.JButton();
        jLabel_ciudad_natal2 = new javax.swing.JLabel();
        codigo_revisor_revisar = new javax.swing.JTextField();
        jPanel_persona3 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        Tabla_Provisiones = new javax.swing.JTable();
        jLabel_DNI3 = new javax.swing.JLabel();
        codigo_proveedor_provision = new javax.swing.JTextField();
        jButton_guardar_persona3 = new javax.swing.JButton();
        jButton_cancelar_persona3 = new javax.swing.JButton();
        jButton_borrar_persona3 = new javax.swing.JButton();
        jButton_modificar_persona3 = new javax.swing.JButton();
        jButton_aniadir_persona3 = new javax.swing.JButton();
        jLabel_ciudad_natal3 = new javax.swing.JLabel();
        vin_provision = new javax.swing.JTextField();
        jLabel_DNI4 = new javax.swing.JLabel();
        codigo_provision = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cancelarRevisor(evt);
            }
        });

        Tabla_Clientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null}
            },
            new String [] {
                "DNI", "Nombre", "Teléfono"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(Tabla_Clientes);
        if (Tabla_Clientes.getColumnModel().getColumnCount() > 0) {
            Tabla_Clientes.getColumnModel().getColumn(0).setResizable(false);
            Tabla_Clientes.getColumnModel().getColumn(1).setResizable(false);
            Tabla_Clientes.getColumnModel().getColumn(2).setResizable(false);
        }

        jButton_aniadir_ciudad.setText("Añadir");
        jButton_aniadir_ciudad.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                aniadirCliente(evt);
            }
        });

        jButton_modificar_ciudad.setText("Modificar");
        jButton_modificar_ciudad.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                modificarCliente(evt);
            }
        });

        jButton_borrar_ciudad.setText("Borrar");
        jButton_borrar_ciudad.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                eliminarCliente(evt);
            }
        });

        jLabel_nombre_ciudad.setText("DNI:");

        jLabel_pais.setText("Nombre:");

        jLabel_num_habitantes.setText("Número telefono:");

        guardarCliente.setText("Guardar");
        guardarCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                guardarCliente(evt);
            }
        });

        cancelarCliente.setText("Cancelar");
        cancelarCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cancelarCliente(evt);
            }
        });

        jLabel_num_habitantes1.setText("Dirección:");

        jLabel_num_habitantes2.setText("Ciudad:");

        javax.swing.GroupLayout jPanel_ciudadLayout = new javax.swing.GroupLayout(jPanel_ciudad);
        jPanel_ciudad.setLayout(jPanel_ciudadLayout);
        jPanel_ciudadLayout.setHorizontalGroup(
            jPanel_ciudadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_ciudadLayout.createSequentialGroup()
                .addGroup(jPanel_ciudadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_ciudadLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPanel_ciudadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel_nombre_ciudad)
                            .addComponent(jLabel_pais)
                            .addComponent(jLabel_num_habitantes)
                            .addComponent(jLabel_num_habitantes1)
                            .addComponent(jLabel_num_habitantes2))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel_ciudadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(dni_cliente, javax.swing.GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE)
                            .addComponent(nombre_cliente, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(direccion_cliente, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(telefono_cliente)
                            .addComponent(ciudad_cliente))
                        .addGap(35, 35, 35)
                        .addGroup(jPanel_ciudadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(guardarCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cancelarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel_ciudadLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel_ciudadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton_modificar_ciudad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton_aniadir_ciudad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton_borrar_ciudad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(107, Short.MAX_VALUE))
        );
        jPanel_ciudadLayout.setVerticalGroup(
            jPanel_ciudadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_ciudadLayout.createSequentialGroup()
                .addGroup(jPanel_ciudadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_ciudadLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jButton_aniadir_ciudad)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_modificar_ciudad, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_borrar_ciudad))
                    .addGroup(jPanel_ciudadLayout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(42, 42, 42)
                .addGroup(jPanel_ciudadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_nombre_ciudad)
                    .addComponent(dni_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel_ciudadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_ciudadLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel_ciudadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel_pais)
                            .addComponent(nombre_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel_ciudadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel_num_habitantes)
                            .addComponent(telefono_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel_ciudadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel_num_habitantes1)
                            .addComponent(direccion_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel_ciudadLayout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(guardarCliente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cancelarCliente)))
                .addGap(18, 18, 18)
                .addGroup(jPanel_ciudadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_num_habitantes2)
                    .addComponent(ciudad_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(86, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Clientes", jPanel_ciudad);

        Tabla_Coches.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "VIN", "Marca", "Modelo", "Color", "Precio", "Bateria", "Deposito"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Float.class, java.lang.Double.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(Tabla_Coches);
        if (Tabla_Coches.getColumnModel().getColumnCount() > 0) {
            Tabla_Coches.getColumnModel().getColumn(0).setResizable(false);
            Tabla_Coches.getColumnModel().getColumn(1).setResizable(false);
            Tabla_Coches.getColumnModel().getColumn(2).setResizable(false);
            Tabla_Coches.getColumnModel().getColumn(3).setResizable(false);
            Tabla_Coches.getColumnModel().getColumn(4).setResizable(false);
            Tabla_Coches.getColumnModel().getColumn(5).setResizable(false);
            Tabla_Coches.getColumnModel().getColumn(6).setResizable(false);
        }

        jLabel_nombre_biblioteca.setText("VIN:");

        jLabel_telefono.setText("Marca:");

        jLabel_direccion.setText("Modelo:");

        guardarCoche.setText("Guardar");
        guardarCoche.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                guardarCoche(evt);
            }
        });

        cancelarCoche.setText("Cancelar");
        cancelarCoche.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cancelarCoche(evt);
            }
        });

        jButton_aniadir_biblioteca.setText("Añadir");
        jButton_aniadir_biblioteca.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                aniadirCoche(evt);
            }
        });

        jButton_modificar_biblioteca.setText("Modificar");
        jButton_modificar_biblioteca.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                modificarCoche(evt);
            }
        });

        jButton_borrar_biblioteca.setText("Borrar");
        jButton_borrar_biblioteca.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                borrarCoche(evt);
            }
        });

        jLabel_direccion5.setText("Color:");

        jLabel_direccion6.setText("Precio:");

        jLabel_direccion7.setText("Deposito");

        jLabel_direccion8.setText("Bateria:");

        javax.swing.GroupLayout jPanel_bibliotecaLayout = new javax.swing.GroupLayout(jPanel_biblioteca);
        jPanel_biblioteca.setLayout(jPanel_bibliotecaLayout);
        jPanel_bibliotecaLayout.setHorizontalGroup(
            jPanel_bibliotecaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_bibliotecaLayout.createSequentialGroup()
                .addGroup(jPanel_bibliotecaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel_bibliotecaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel_bibliotecaLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPanel_bibliotecaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel_bibliotecaLayout.createSequentialGroup()
                                .addGroup(jPanel_bibliotecaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel_direccion8)
                                    .addComponent(jLabel_direccion7))
                                .addGap(21, 21, 21)
                                .addGroup(jPanel_bibliotecaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(deposito_coche, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(bateria_coche, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel_bibliotecaLayout.createSequentialGroup()
                                .addGroup(jPanel_bibliotecaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel_nombre_biblioteca)
                                    .addComponent(jLabel_telefono)
                                    .addComponent(jLabel_direccion)
                                    .addComponent(jLabel_direccion5)
                                    .addComponent(jLabel_direccion6))
                                .addGap(24, 24, 24)
                                .addGroup(jPanel_bibliotecaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(vin_coche, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
                                    .addComponent(marca_coche)
                                    .addComponent(modelo_coche)
                                    .addComponent(color_coche)
                                    .addComponent(precio_coche))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel_bibliotecaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(guardarCoche, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cancelarCoche, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(18, 18, 18)
                .addGroup(jPanel_bibliotecaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton_modificar_biblioteca, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton_aniadir_biblioteca, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton_borrar_biblioteca, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(122, 122, 122))
        );
        jPanel_bibliotecaLayout.setVerticalGroup(
            jPanel_bibliotecaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_bibliotecaLayout.createSequentialGroup()
                .addGroup(jPanel_bibliotecaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_bibliotecaLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jButton_aniadir_biblioteca)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_modificar_biblioteca, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_borrar_biblioteca))
                    .addGroup(jPanel_bibliotecaLayout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(42, 42, 42)
                .addGroup(jPanel_bibliotecaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_nombre_biblioteca)
                    .addComponent(vin_coche, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel_bibliotecaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_telefono)
                    .addComponent(marca_coche, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel_bibliotecaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_bibliotecaLayout.createSequentialGroup()
                        .addGroup(jPanel_bibliotecaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel_direccion)
                            .addComponent(modelo_coche, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel_bibliotecaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel_direccion5)
                            .addComponent(color_coche, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel_bibliotecaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel_direccion6)
                            .addComponent(precio_coche, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_bibliotecaLayout.createSequentialGroup()
                        .addComponent(guardarCoche)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cancelarCoche)
                        .addGap(24, 24, 24)))
                .addGroup(jPanel_bibliotecaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_direccion8)
                    .addComponent(bateria_coche, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel_bibliotecaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_direccion7)
                    .addComponent(deposito_coche, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Coches", jPanel_biblioteca);

        Tabla_Revisores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Identificador", "Nombre"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(Tabla_Revisores);
        if (Tabla_Revisores.getColumnModel().getColumnCount() > 0) {
            Tabla_Revisores.getColumnModel().getColumn(0).setResizable(false);
            Tabla_Revisores.getColumnModel().getColumn(1).setResizable(false);
        }

        jLabel_nombre_libro.setText("Identificador:");

        guardarRevisor.setText("Guardar");
        guardarRevisor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                guardarRevisor(evt);
            }
        });

        cancelarRevisor.setText("Cancelar");
        cancelarRevisor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cancelarRevision(evt);
            }
        });

        jButton_borrar_libro.setText("Borrar");
        jButton_borrar_libro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                borrarRevisor(evt);
            }
        });

        jButton_modificar_libro.setText("Modificar");
        jButton_modificar_libro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                modificarRevisor(evt);
            }
        });

        jButton_aniadir_libro.setText("Añadir");
        jButton_aniadir_libro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                aniadirRevisor(evt);
            }
        });

        jLabel_nombre_libro1.setText("Nombre:");

        javax.swing.GroupLayout jPanel_libroLayout = new javax.swing.GroupLayout(jPanel_libro);
        jPanel_libro.setLayout(jPanel_libroLayout);
        jPanel_libroLayout.setHorizontalGroup(
            jPanel_libroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_libroLayout.createSequentialGroup()
                .addGroup(jPanel_libroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_libroLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel_libroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton_modificar_libro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton_aniadir_libro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton_borrar_libro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel_libroLayout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(jPanel_libroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel_libroLayout.createSequentialGroup()
                                .addComponent(jLabel_nombre_libro1)
                                .addGap(58, 58, 58)
                                .addComponent(nombre_revisor, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel_libroLayout.createSequentialGroup()
                                .addComponent(jLabel_nombre_libro)
                                .addGap(58, 58, 58)
                                .addComponent(id_revisor, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(33, 33, 33)
                        .addGroup(jPanel_libroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(guardarRevisor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cancelarRevisor, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(122, Short.MAX_VALUE))
        );
        jPanel_libroLayout.setVerticalGroup(
            jPanel_libroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_libroLayout.createSequentialGroup()
                .addGroup(jPanel_libroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_libroLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jButton_aniadir_libro)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_modificar_libro, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_borrar_libro))
                    .addGroup(jPanel_libroLayout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel_libroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_libroLayout.createSequentialGroup()
                        .addGap(104, 104, 104)
                        .addGroup(jPanel_libroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel_nombre_libro)
                            .addComponent(id_revisor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel_libroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel_nombre_libro1)
                            .addComponent(nombre_revisor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel_libroLayout.createSequentialGroup()
                        .addGap(113, 113, 113)
                        .addComponent(guardarRevisor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cancelarRevisor)))
                .addContainerGap(136, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Revisores", jPanel_libro);

        Tabla_Provedores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Identificador", "Nombre"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(Tabla_Provedores);
        if (Tabla_Provedores.getColumnModel().getColumnCount() > 0) {
            Tabla_Provedores.getColumnModel().getColumn(0).setResizable(false);
            Tabla_Provedores.getColumnModel().getColumn(1).setResizable(false);
        }

        jLabel_nombre_persona.setText("Identificador:");

        guardarProvedor.setText("Guardar");
        guardarProvedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                guardarProveedor(evt);
            }
        });

        cancelarProvedor.setText("Cancelar");
        cancelarProvedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cancelarProveedor(evt);
            }
        });

        jButton_borrar_persona.setText("Borrar");
        jButton_borrar_persona.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                borrarProveedor(evt);
            }
        });

        jButton_modificar_persona.setText("Modificar");
        jButton_modificar_persona.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                modificarProveedor(evt);
            }
        });

        jButton_aniadir_persona.setText("Añadir");
        jButton_aniadir_persona.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                aniadirProveedor(evt);
            }
        });

        jLabel_nombre_persona4.setText("Nombre:");

        javax.swing.GroupLayout jPanel_personaLayout = new javax.swing.GroupLayout(jPanel_persona);
        jPanel_persona.setLayout(jPanel_personaLayout);
        jPanel_personaLayout.setHorizontalGroup(
            jPanel_personaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_personaLayout.createSequentialGroup()
                .addGroup(jPanel_personaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_personaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel_personaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton_modificar_persona, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton_aniadir_persona, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton_borrar_persona, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel_personaLayout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(jPanel_personaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel_personaLayout.createSequentialGroup()
                                .addComponent(jLabel_nombre_persona)
                                .addGap(24, 24, 24)
                                .addComponent(id_provedor, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel_personaLayout.createSequentialGroup()
                                .addComponent(jLabel_nombre_persona4)
                                .addGap(24, 24, 24)
                                .addComponent(nombre_provedor, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(31, 31, 31)
                        .addGroup(jPanel_personaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(guardarProvedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cancelarProvedor, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
                .addContainerGap(122, Short.MAX_VALUE))
        );
        jPanel_personaLayout.setVerticalGroup(
            jPanel_personaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_personaLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel_personaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel_personaLayout.createSequentialGroup()
                        .addComponent(jButton_aniadir_persona)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_modificar_persona, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_borrar_persona)))
                .addGap(99, 99, 99)
                .addGroup(jPanel_personaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel_personaLayout.createSequentialGroup()
                        .addGroup(jPanel_personaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel_nombre_persona)
                            .addComponent(id_provedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel_personaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel_nombre_persona4)
                            .addComponent(nombre_provedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel_personaLayout.createSequentialGroup()
                        .addComponent(guardarProvedor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cancelarProvedor)))
                .addContainerGap(165, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Provedores", jPanel_persona);

        jTabbedPane.addTab("Base de Datos", jTabbedPane1);

        Tabla_Compras.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "DNI", "VIN", "Matricula", "Fecha"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(Tabla_Compras);
        if (Tabla_Compras.getColumnModel().getColumnCount() > 0) {
            Tabla_Compras.getColumnModel().getColumn(0).setResizable(false);
            Tabla_Compras.getColumnModel().getColumn(1).setResizable(false);
            Tabla_Compras.getColumnModel().getColumn(2).setResizable(false);
            Tabla_Compras.getColumnModel().getColumn(2).setHeaderValue("Matricula");
            Tabla_Compras.getColumnModel().getColumn(3).setResizable(false);
            Tabla_Compras.getColumnModel().getColumn(3).setHeaderValue("Fecha");
        }

        jLabel_DNI1.setText("DNI:");

        jLabel_edad_persona1.setText("VIN:");

        guardarCompra.setText("Guardar");
        guardarCompra.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                guardarCompra(evt);
            }
        });

        cancelarCompra.setText("Cancelar");
        cancelarCompra.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cancelarCompra(evt);
            }
        });

        jButton_borrar_persona1.setText("Borrar");
        jButton_borrar_persona1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                borrarCompra(evt);
            }
        });

        jButton_modificar_persona1.setText("Modificar");
        jButton_modificar_persona1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                modificarCompra(evt);
            }
        });

        jButton_aniadir_persona1.setText("Añadir");
        jButton_aniadir_persona1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                aniadirCompra(evt);
            }
        });

        jLabel_ciudad_natal1.setText("Matricula:");

        javax.swing.GroupLayout jPanel_persona1Layout = new javax.swing.GroupLayout(jPanel_persona1);
        jPanel_persona1.setLayout(jPanel_persona1Layout);
        jPanel_persona1Layout.setHorizontalGroup(
            jPanel_persona1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_persona1Layout.createSequentialGroup()
                .addGroup(jPanel_persona1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_persona1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel_persona1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton_modificar_persona1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton_aniadir_persona1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton_borrar_persona1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel_persona1Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPanel_persona1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel_DNI1)
                            .addComponent(jLabel_edad_persona1)
                            .addComponent(jLabel_ciudad_natal1))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel_persona1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(dni_compra, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
                            .addComponent(vin_compra)
                            .addComponent(matricula_compra, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(36, 36, 36)
                        .addGroup(jPanel_persona1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(guardarCompra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cancelarCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(122, Short.MAX_VALUE))
        );
        jPanel_persona1Layout.setVerticalGroup(
            jPanel_persona1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_persona1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel_persona1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel_persona1Layout.createSequentialGroup()
                        .addComponent(jButton_aniadir_persona1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_modificar_persona1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_borrar_persona1)))
                .addGroup(jPanel_persona1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_persona1Layout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addGroup(jPanel_persona1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel_DNI1)
                            .addComponent(dni_compra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel_persona1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel_edad_persona1)
                            .addComponent(vin_compra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel_persona1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel_ciudad_natal1)
                            .addComponent(matricula_compra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel_persona1Layout.createSequentialGroup()
                        .addGap(101, 101, 101)
                        .addComponent(guardarCompra)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cancelarCompra)))
                .addContainerGap(136, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Comprar", jPanel_persona1);

        Tabla_Reviones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo_revision", "VIN", "Codigo_revisor"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane6.setViewportView(Tabla_Reviones);
        if (Tabla_Reviones.getColumnModel().getColumnCount() > 0) {
            Tabla_Reviones.getColumnModel().getColumn(0).setResizable(false);
            Tabla_Reviones.getColumnModel().getColumn(1).setResizable(false);
            Tabla_Reviones.getColumnModel().getColumn(2).setResizable(false);
        }

        jLabel_DNI2.setText("Codigo revision:");

        jLabel_edad_persona2.setText("VIN:");

        guardarRevision.setText("Guardar");
        guardarRevision.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                guardarRevision(evt);
            }
        });

        cancelarRevision.setText("Cancelar");
        cancelarRevision.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cancelarRevision(evt);
            }
        });

        jButton_borrar_persona2.setText("Borrar");
        jButton_borrar_persona2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                borrarRevision(evt);
            }
        });

        jButton_modificar_persona2.setText("Modificar");
        jButton_modificar_persona2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                modificarRevision(evt);
            }
        });

        jButton_aniadir_persona2.setText("Añadir");
        jButton_aniadir_persona2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                aniadirRevision(evt);
            }
        });

        jLabel_ciudad_natal2.setText("Codigo revisor");

        javax.swing.GroupLayout jPanel_persona2Layout = new javax.swing.GroupLayout(jPanel_persona2);
        jPanel_persona2.setLayout(jPanel_persona2Layout);
        jPanel_persona2Layout.setHorizontalGroup(
            jPanel_persona2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_persona2Layout.createSequentialGroup()
                .addGroup(jPanel_persona2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_persona2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel_persona2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton_modificar_persona2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton_aniadir_persona2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton_borrar_persona2, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel_persona2Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPanel_persona2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel_DNI2)
                            .addComponent(jLabel_edad_persona2)
                            .addComponent(jLabel_ciudad_natal2))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel_persona2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(codigo_revisar, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
                            .addComponent(vin_revisar)
                            .addComponent(codigo_revisor_revisar, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(36, 36, 36)
                        .addGroup(jPanel_persona2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(guardarRevision, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cancelarRevision, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(122, Short.MAX_VALUE))
        );
        jPanel_persona2Layout.setVerticalGroup(
            jPanel_persona2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_persona2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel_persona2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel_persona2Layout.createSequentialGroup()
                        .addComponent(jButton_aniadir_persona2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_modificar_persona2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_borrar_persona2)))
                .addGroup(jPanel_persona2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_persona2Layout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addGroup(jPanel_persona2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel_DNI2)
                            .addComponent(codigo_revisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel_persona2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel_edad_persona2)
                            .addComponent(vin_revisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel_persona2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel_ciudad_natal2)
                            .addComponent(codigo_revisor_revisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel_persona2Layout.createSequentialGroup()
                        .addGap(101, 101, 101)
                        .addComponent(guardarRevision)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cancelarRevision)))
                .addContainerGap(136, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Revisar", jPanel_persona2);

        Tabla_Provisiones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo_proveedor", "VIN"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane7.setViewportView(Tabla_Provisiones);
        if (Tabla_Provisiones.getColumnModel().getColumnCount() > 0) {
            Tabla_Provisiones.getColumnModel().getColumn(0).setResizable(false);
            Tabla_Provisiones.getColumnModel().getColumn(1).setResizable(false);
        }

        jLabel_DNI3.setText("Codigo proveedor:");

        jButton_guardar_persona3.setText("Guardar");
        jButton_guardar_persona3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                guardarProvision(evt);
            }
        });
        jButton_guardar_persona3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_guardar_persona3ActionPerformed(evt);
            }
        });

        jButton_cancelar_persona3.setText("Cancelar");
        jButton_cancelar_persona3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cancelarProvision(evt);
            }
        });
        jButton_cancelar_persona3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_cancelar_persona3ActionPerformed(evt);
            }
        });

        jButton_borrar_persona3.setText("Borrar");
        jButton_borrar_persona3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                borrarProvision(evt);
            }
        });

        jButton_modificar_persona3.setText("Modificar");
        jButton_modificar_persona3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                modificarProvision(evt);
            }
        });

        jButton_aniadir_persona3.setText("Añadir");
        jButton_aniadir_persona3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                aniadirProvision(evt);
            }
        });

        jLabel_ciudad_natal3.setText("VIN:");

        jLabel_DNI4.setText("Codigo:");

        javax.swing.GroupLayout jPanel_persona3Layout = new javax.swing.GroupLayout(jPanel_persona3);
        jPanel_persona3.setLayout(jPanel_persona3Layout);
        jPanel_persona3Layout.setHorizontalGroup(
            jPanel_persona3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_persona3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_persona3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_persona3Layout.createSequentialGroup()
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel_persona3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton_borrar_persona3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton_aniadir_persona3, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton_modificar_persona3, javax.swing.GroupLayout.Alignment.LEADING)))
                    .addGroup(jPanel_persona3Layout.createSequentialGroup()
                        .addGroup(jPanel_persona3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_persona3Layout.createSequentialGroup()
                                .addComponent(jLabel_DNI3)
                                .addGap(13, 13, 13))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_persona3Layout.createSequentialGroup()
                                .addComponent(jLabel_DNI4)
                                .addGap(18, 18, 18))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_persona3Layout.createSequentialGroup()
                                .addComponent(jLabel_ciudad_natal3)
                                .addGap(18, 18, 18)))
                        .addGroup(jPanel_persona3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(codigo_proveedor_provision, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
                            .addComponent(codigo_provision)
                            .addComponent(vin_provision))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel_persona3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton_guardar_persona3)
                            .addComponent(jButton_cancelar_persona3, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(122, Short.MAX_VALUE))
        );
        jPanel_persona3Layout.setVerticalGroup(
            jPanel_persona3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_persona3Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel_persona3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel_persona3Layout.createSequentialGroup()
                        .addComponent(jButton_aniadir_persona3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_modificar_persona3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_borrar_persona3)))
                .addGroup(jPanel_persona3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_persona3Layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addGroup(jPanel_persona3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel_DNI4)
                            .addComponent(codigo_provision, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel_persona3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel_DNI3)
                            .addComponent(codigo_proveedor_provision, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel_persona3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel_ciudad_natal3)
                            .addComponent(vin_provision, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel_persona3Layout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addComponent(jButton_guardar_persona3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton_cancelar_persona3)))
                .addContainerGap(168, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Proveer", jPanel_persona3);

        jTabbedPane.addTab("Acciones", jTabbedPane2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 690, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane)
        );

        jTabbedPane.getAccessibleContext().setAccessibleName("Cliente");
        jTabbedPane.getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    private void aniadirProveedor(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_aniadirProveedor

        if(controlador != null)
        {
            if((this.id_provedor.getText().isEmpty() == false) && (this.nombre_provedor.getText().isEmpty() == false))
            {
                if(controlador.comprobarProveedorExiste(this.id_provedor.getText()) == false)
                {
                    controlador.aniadirProveedor(this.id_provedor.getText(), this.nombre_provedor.getText());
                    this.cargarProvedores();
                }
            }
        }

    }//GEN-LAST:event_aniadirProveedor

    private void modificarProveedor(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_modificarProveedor
        
        if(this.Tabla_Provedores.getSelectedRow() != -1)
        {
            this.id_provedor.setText(this.ModeloTablaProveedores.getValueAt(this.Tabla_Provedores.getSelectedRow(), 0).toString());
            this.nombre_provedor.setText(this.ModeloTablaProveedores.getValueAt(this.Tabla_Provedores.getSelectedRow(), 1).toString());
        }
        else
        {
            JOptionPane.showMessageDialog(null, "No se ha seleccionado ninguna fila.");
        }
    }//GEN-LAST:event_modificarProveedor

    private void borrarProveedor(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_borrarProveedor

        if(controlador != null && this.Tabla_Provedores.getSelectedRow() != -1)
        {
                if(controlador.comprobarProveedorExiste(this.ModeloTablaProveedores.getValueAt(this.Tabla_Provedores.getSelectedRow(), 0).toString()))
                {
                    controlador.eliminarProveedor(this.ModeloTablaProveedores.getValueAt(this.Tabla_Provedores.getSelectedRow(), 0).toString());
                    this.cargarProvedores();
                }
            
        }
        else
        {
            JOptionPane.showMessageDialog(null, "No se ha seleccionado ninguna fila.");
        }

    }//GEN-LAST:event_borrarProveedor

    private void cancelarProveedor(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelarProveedor

        this.id_provedor.setText("");
        this.nombre_provedor.setText("");

    }//GEN-LAST:event_cancelarProveedor

    private void guardarProveedor(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_guardarProveedor

        

        if(controlador != null && this.Tabla_Provedores.getSelectedRow() != -1)
        {
            String codAntiguo = this.ModeloTablaProveedores.getValueAt(this.Tabla_Provedores.getSelectedRow(), 0).toString();
            if((this.id_provedor.getText().isEmpty() == false) && (this.nombre_provedor.getText().isEmpty() == false))
            {
                if(codAntiguo.equals(this.id_provedor.getText()))
                {
                    controlador.modificarProveedor(id_provedor.getText() , nombre_provedor.getText());
                    this.cargarProvedores();
                }
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "No se ha seleccionado ninguna fila.");
        }

    }//GEN-LAST:event_guardarProveedor

    private void aniadirRevisor(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_aniadirRevisor

        if(controlador != null)
        {
            if((this.id_revisor.getText().isEmpty() == false) && (this.nombre_revisor.getText().isEmpty() == false))
            {
                if(controlador.comprobarRevisorExiste(this.id_revisor.getText()) == false)
                {
                    controlador.aniadirRevisor(this.id_revisor.getText(), this.nombre_revisor.getText());
                    this.cargarRevisores();
                }
            }
        }
    }//GEN-LAST:event_aniadirRevisor

    private void modificarRevisor(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_modificarRevisor

        if(this.Tabla_Revisores.getSelectedRow() != -1)
        {
            this.id_revisor.setText(this.ModeloTablaRevisores.getValueAt(this.Tabla_Revisores.getSelectedRow(), 0).toString());
            this.nombre_revisor.setText(this.ModeloTablaRevisores.getValueAt(this.Tabla_Revisores.getSelectedRow(), 1).toString());
        }
        else
        {
            JOptionPane.showMessageDialog(null, "No se ha seleccionado ninguna fila.");
        }
    }//GEN-LAST:event_modificarRevisor

    private void borrarRevisor(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_borrarRevisor

        if(controlador != null && this.Tabla_Revisores.getSelectedRow() != -1)
        {
                if(controlador.comprobarRevisorExiste(this.ModeloTablaRevisores.getValueAt(this.Tabla_Revisores.getSelectedRow(), 0).toString()))
                {
                    controlador.eliminarRevisor(this.ModeloTablaRevisores.getValueAt(this.Tabla_Revisores.getSelectedRow(), 0).toString());
                    this.cargarRevisores();
                }
            
        }
        else
        {
            JOptionPane.showMessageDialog(null, "No se ha seleccionado ninguna fila.");
        }
    }//GEN-LAST:event_borrarRevisor

    private void cancelarRevision(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelarRevision

        this.codigo_revisar.setText("");
        this.vin_revisar.setText("");
        this.codigo_revisor_revisar.setText("");
        
    }//GEN-LAST:event_cancelarRevision

    private void guardarRevisor(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_guardarRevisor

        if(controlador != null && this.Tabla_Revisores.getSelectedRow() != -1)
        {
            String codAntiguo = this.ModeloTablaRevisores.getValueAt(this.Tabla_Revisores.getSelectedRow(), 0).toString();
            
            if((this.id_revisor.getText().isEmpty() == false) && (this.nombre_revisor.getText().isEmpty() == false))
            {
                if(codAntiguo.equals(this.id_revisor.getText()))
                {
                    controlador.modificarRevisor(id_revisor.getText() , nombre_revisor.getText());
                    this.cargarRevisores();
                }
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "No se ha seleccionado ninguna fila.");
        }
    }//GEN-LAST:event_guardarRevisor

    private void borrarCoche(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_borrarCoche

        if(controlador != null && this.Tabla_Coches.getSelectedRow() != -1)
        {
                if(controlador.comprobarCocheExiste(this.ModeloTablaCoches.getValueAt(this.Tabla_Coches.getSelectedRow(), 0).toString()) == true)
                {
                    controlador.eliminarCoche(this.ModeloTablaCoches.getValueAt(this.Tabla_Coches.getSelectedRow(), 0).toString());
                    this.cargarCoches();
                }
            
        }
        else
        {
            JOptionPane.showMessageDialog(null, "No se ha seleccionado ninguna fila.");
        }
    }//GEN-LAST:event_borrarCoche

    private void modificarCoche(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_modificarCoche

        if(this.Tabla_Coches.getSelectedRow() != -1){
            if(this.ModeloTablaCoches.getValueAt(this.Tabla_Coches.getSelectedRow(), 6).equals("") == false || this.ModeloTablaCoches.getValueAt(this.Tabla_Coches.getSelectedRow(), 6).equals("0") == false)
            {
                this.vin_coche.setText(this.ModeloTablaCoches.getValueAt(this.Tabla_Coches.getSelectedRow(), 0).toString());
                this.marca_coche.setText(this.ModeloTablaCoches.getValueAt(this.Tabla_Coches.getSelectedRow(), 1).toString());
                this.modelo_coche.setText(this.ModeloTablaCoches.getValueAt(this.Tabla_Coches.getSelectedRow(), 2).toString());
                this.precio_coche.setText(this.ModeloTablaCoches.getValueAt(this.Tabla_Coches.getSelectedRow(), 4).toString());
                this.color_coche.setText(this.ModeloTablaCoches.getValueAt(this.Tabla_Coches.getSelectedRow(), 3).toString());
                this.deposito_coche.setText(this.ModeloTablaCoches.getValueAt(this.Tabla_Coches.getSelectedRow(), 5).toString());
                this.bateria_coche.setText("0.0");
            }
            else if(this.ModeloTablaCoches.getValueAt(this.Tabla_Coches.getSelectedRow(), 5).equals("") == false || this.ModeloTablaCoches.getValueAt(this.Tabla_Coches.getSelectedRow(), 5).equals("0.0") == false)
            {
                this.vin_coche.setText(this.ModeloTablaCoches.getValueAt(this.Tabla_Coches.getSelectedRow(), 0).toString());
                this.marca_coche.setText(this.ModeloTablaCoches.getValueAt(this.Tabla_Coches.getSelectedRow(), 1).toString());
                this.modelo_coche.setText(this.ModeloTablaCoches.getValueAt(this.Tabla_Coches.getSelectedRow(), 2).toString());
                this.precio_coche.setText(this.ModeloTablaCoches.getValueAt(this.Tabla_Coches.getSelectedRow(), 4).toString());
                this.color_coche.setText(this.ModeloTablaCoches.getValueAt(this.Tabla_Coches.getSelectedRow(), 3).toString());
                this.deposito_coche.setText("0");
                this.bateria_coche.setText(this.ModeloTablaCoches.getValueAt(this.Tabla_Coches.getSelectedRow(), 6).toString());
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "No se ha seleccionado ninguna fila.");
        }
    }//GEN-LAST:event_modificarCoche

    private void aniadirCoche(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_aniadirCoche

        if(controlador != null)
        {
            if((this.vin_coche.getText().isEmpty() == false) && (this.marca_coche.getText().isEmpty() == false) && (this.modelo_coche.getText().isEmpty() == false) && (this.precio_coche.getText().isEmpty() == false) && (this.color_coche.getText().isEmpty() == false) && (this.deposito_coche.getText().isEmpty() == false) && (this.bateria_coche.getText().isEmpty() == true))
            {
                if(controlador.comprobarCocheExiste(this.vin_coche.getText()) == false)
                {
                    controlador.aniadirGasolina(this.vin_coche.getText(), this.marca_coche.getText(), this.modelo_coche.getText(), Float.parseFloat(this.precio_coche.getText()), this.color_coche.getText(), Integer.parseInt(this.deposito_coche.getText()));
                    this.cargarCoches();
                }
            }else if ((this.vin_coche.getText().isEmpty() == false) && (this.marca_coche.getText().isEmpty() == false) && (this.modelo_coche.getText().isEmpty() == false) && (this.precio_coche.getText().isEmpty() == false) && (this.color_coche.getText().isEmpty() == false) && (this.deposito_coche.getText().isEmpty() == true) && (this.bateria_coche.getText().isEmpty() == false))
            {
                if(controlador.comprobarCocheExiste(this.vin_coche.getText()) == false)
                {
                    controlador.aniadirElectrico(this.vin_coche.getText(), this.marca_coche.getText(), this.modelo_coche.getText(), Float.parseFloat(this.precio_coche.getText()), this.color_coche.getText(), Double.parseDouble(this.bateria_coche.getText()));
                    this.cargarCoches();
                }

            }
        }
    }//GEN-LAST:event_aniadirCoche

    private void cancelarCoche(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelarCoche

        this.vin_coche.setText("");
        this.marca_coche.setText("");
        this.modelo_coche.setText("");
        this.color_coche.setText("");
        this.precio_coche.setText("");
        this.deposito_coche.setText("");
        this.bateria_coche.setText("");
    }//GEN-LAST:event_cancelarCoche

    private void guardarCoche(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_guardarCoche


        if(controlador != null && this.Tabla_Coches.getSelectedRow() != -1)
        {
            int vinAntiguo = Integer.parseInt(this.ModeloTablaCoches.getValueAt(this.Tabla_Coches.getSelectedRow(), 0).toString());
            
            if((this.vin_coche.getText().isEmpty() == false) && (this.marca_coche.getText().isEmpty() == false) && (this.modelo_coche.getText().isEmpty() == false) && (this.precio_coche.getText().isEmpty() == false) && (this.color_coche.getText().isEmpty() == false) && (this.deposito_coche.getText().isEmpty() == false) || (this.bateria_coche.getText().isEmpty() == false))
            {
                if(vinAntiguo == Integer.parseInt(this.vin_coche.getText()))
                {
                    controlador.modificarCoche(Double.parseDouble(bateria_coche.getText()), Integer.parseInt(deposito_coche.getText()), vin_coche.getText(), marca_coche.getText(), modelo_coche.getText(),Float.parseFloat(precio_coche.getText()), color_coche.getText());
                    this.cargarCoches();
                    
                }
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "No se ha seleccionado ninguna fila.");
        }
    }//GEN-LAST:event_guardarCoche

    private void cancelarCliente(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelarCliente

        this.dni_cliente.setText("");
        this.nombre_cliente.setText("");
        this.direccion_cliente.setText("");
        this.ciudad_cliente.setText("");
        this.telefono_cliente.setText("");
    }//GEN-LAST:event_cancelarCliente

    private void guardarCliente(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_guardarCliente

        if(controlador != null && this.Tabla_Clientes.getSelectedRow() != -1)
        {
            String dniAntiguo = this.ModeloTablaClientes.getValueAt(this.Tabla_Clientes.getSelectedRow(), 0).toString();
            if((this.dni_cliente.getText().isEmpty() == false) && (this.nombre_cliente.getText().isEmpty() == false) && (this.direccion_cliente.getText().isEmpty() == false) && (this.ciudad_cliente.getText().isEmpty() == false) && (this.telefono_cliente.getText().isEmpty() == false))
            {
                if(dniAntiguo.equals(this.dni_cliente.getText()))
                {
                    controlador.modificarCliente(this.dni_cliente.getText(), this.nombre_cliente.getText(), this.direccion_cliente.getText(), this.ciudad_cliente.getText(), this.telefono_cliente.getText());
                    cargarClientes();
                }
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "No se ha seleccionado ninguna fila.");
        }
    }//GEN-LAST:event_guardarCliente

    private void eliminarCliente(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_eliminarCliente

        if(controlador != null && this.Tabla_Clientes.getSelectedRow() != -1)
        {
            if(controlador.comprobarClienteExiste(this.ModeloTablaClientes.getValueAt(this.Tabla_Clientes.getSelectedRow(), 0).toString()) == true)
            {
                controlador.eliminarCliente(this.ModeloTablaClientes.getValueAt(this.Tabla_Clientes.getSelectedRow(), 0).toString());
                cargarClientes();
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "No se ha seleccionado ninguna fila.");
        }
        
    }//GEN-LAST:event_eliminarCliente

    private void modificarCliente(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_modificarCliente
        
        if(this.Tabla_Clientes.getSelectedRow() != -1){
            Cliente cliente = controlador.buscarCliente(this.ModeloTablaClientes.getValueAt(this.Tabla_Clientes.getSelectedRow(), 0).toString());

            this.dni_cliente.setText(cliente.getdni_cliente());
            this.nombre_cliente.setText(cliente.getnombre_cliente());
            this.direccion_cliente.setText(cliente.getdireccion_cliente());
            this.ciudad_cliente.setText(cliente.getciudad_cliente());
            this.telefono_cliente.setText(cliente.getnumero_telefono_cliente());
        }
        else
        {
            JOptionPane.showMessageDialog(null, "No se ha seleccionado ninguna fila.");
        }
    }//GEN-LAST:event_modificarCliente

    private void aniadirCliente(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_aniadirCliente

        if(controlador != null)
        {
            if((this.dni_cliente.getText().isEmpty() == false) && (this.nombre_cliente.getText().isEmpty() == false) && (this.direccion_cliente.getText().isEmpty() == false) && (this.ciudad_cliente.getText().isEmpty() == false) && (this.telefono_cliente.getText().isEmpty() == false))
            {
                if(controlador.comprobarClienteExiste(this.dni_cliente.getText()) == false)
                {
                    controlador.aniadirCliente(this.dni_cliente.getText(), this.nombre_cliente.getText(), this.direccion_cliente.getText(), this.ciudad_cliente.getText(), this.telefono_cliente.getText());
                    cargarClientes();
                }
            }
        }
    }//GEN-LAST:event_aniadirCliente

    private void aniadirCompra(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_aniadirCompra
        
        if(controlador != null)
        {
            if((this.vin_compra.getText().isEmpty() == false) && (this.dni_compra.getText().isEmpty() == false) && (this.matricula_compra.getText().isEmpty() == false))
            {
                LocalDateTime fechaHoraActual = LocalDateTime.now();
                if((controlador.comprobarCompraExiste(this.matricula_compra.getText(), fechaHoraActual) == false) && (controlador.comprobarClienteExiste(this.dni_compra.getText()) == true) && (controlador.comprobarCocheExiste(this.vin_compra.getText()) == true) && (controlador.comprobarCompraCocheCliente(this.matricula_compra.getText(), this.dni_compra.getText(), this.vin_compra.getText()) == true))
                {
                    controlador.aniadirCompra(this.matricula_compra.getText(), fechaHoraActual, this.dni_compra.getText(),this.vin_compra.getText());
                    this.cargarCompras();
                }
            }
        }
        
    }//GEN-LAST:event_aniadirCompra

    private void modificarCompra(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_modificarCompra
        
        if(this.Tabla_Compras.getSelectedRow() != -1)
        {
            this.dni_compra.setText(this.ModeloTablaCompras.getValueAt(this.Tabla_Compras.getSelectedRow(), 0).toString());
            this.vin_compra.setText(this.ModeloTablaCompras.getValueAt(this.Tabla_Compras.getSelectedRow(), 1).toString());
            this.matricula_compra.setText(this.ModeloTablaCompras.getValueAt(this.Tabla_Compras.getSelectedRow(), 2).toString());
        }
        else
        {
            JOptionPane.showMessageDialog(null, "No se ha seleccionado ninguna fila.");
        }
    }//GEN-LAST:event_modificarCompra

    private void borrarCompra(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_borrarCompra
        
        if(controlador != null && this.Tabla_Compras.getSelectedRow() != -1)
        {
            if(controlador.comprobarCompraExiste(this.ModeloTablaCompras.getValueAt(this.Tabla_Compras.getSelectedRow(), 0).toString(), LocalDateTime.parse(this.ModeloTablaCompras.getValueAt(this.Tabla_Compras.getSelectedRow(), 3).toString())) == false)
            {
                controlador.eliminarCompra(this.ModeloTablaCompras.getValueAt(this.Tabla_Compras.getSelectedRow(), 0).toString());
                this.cargarCompras();
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "No se ha seleccionado ninguna fila.");
        }
        
    }//GEN-LAST:event_borrarCompra

    private void guardarCompra(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_guardarCompra
        
        

        if(controlador != null && this.Tabla_Compras.getSelectedRow() != -1)
        {
            String matricula = this.ModeloTablaCompras.getValueAt(this.Tabla_Compras.getSelectedRow(), 2).toString();
            if((this.vin_compra.getText().isEmpty() == false) && (this.dni_compra.getText().isEmpty() == false) && (this.matricula_compra.getText().isEmpty() == false))
            {
                if(((matricula.equals(this.matricula_compra.getText())) && (controlador.comprobarClienteExiste(this.dni_compra.getText()) == true) && (this.vin_compra.getText().equalsIgnoreCase(this.ModeloTablaCompras.getValueAt(this.Tabla_Compras.getSelectedRow(), 1).toString()) == true) && (controlador.comprobarCompraCocheCliente(this.matricula_compra.getText(), this.dni_compra.getText(), this.vin_compra.getText()) == true)) || ((matricula.equals(this.matricula_compra.getText()) == false) && (controlador.comprobarClienteExiste(this.dni_compra.getText()) == true) && (controlador.comprobarCocheExiste(this.vin_compra.getText()) == true) && (controlador.comprobarCompraCocheCliente(this.matricula_compra.getText(), this.dni_compra.getText(), this.vin_compra.getText()) == true)))
                {
                    LocalDateTime fechaHoraActual = LocalDateTime.now();
                    controlador.modificarCompra(this.matricula_compra.getText(), fechaHoraActual, this.dni_compra.getText(), this.vin_compra.getText());
                    this.cargarCompras();
                }
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "No se ha seleccionado ninguna fila.");
        }
        
    }//GEN-LAST:event_guardarCompra

    private void cancelarCompra(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelarCompra
        
        this.vin_compra.setText("");
        this.dni_compra.setText("");
        this.matricula_compra.setText("");
        
    }//GEN-LAST:event_cancelarCompra

    private void aniadirRevision(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_aniadirRevision
        
        if(controlador != null)
        {
            if((this.codigo_revisar.getText().isEmpty() == false) && (this.vin_revisar.getText().isEmpty() == false) && (this.codigo_revisor_revisar.getText().isEmpty() == false))
            {
                if((controlador.comprobarRevisionExiste(this.codigo_revisar.getText()) == false) && (controlador.comprobarRevisorExiste(this.codigo_revisor_revisar.getText()) == true) && (controlador.comprobarCocheExiste(this.vin_revisar.getText()) == true) && (controlador.comprobarRevisionCocheRevisor(this.codigo_revisar.getText().toString(), this.codigo_revisor_revisar.getText().toString(), this.vin_revisar.getText()) == false))
                {
                    controlador.aniadirRevision(this.codigo_revisar.getText(), this.codigo_revisor_revisar.getText(), this.vin_revisar.getText());
                    this.cargarRevisiones();
                }
            }
        }
        
    }//GEN-LAST:event_aniadirRevision

    private void modificarRevision(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_modificarRevision
        
        if(this.Tabla_Reviones.getSelectedRow() != -1)
        {
            this.codigo_revisar.setText(this.ModeloTablaRevisiones.getValueAt(this.Tabla_Reviones.getSelectedRow(), 0).toString());
            this.vin_revisar.setText(this.ModeloTablaRevisiones.getValueAt(this.Tabla_Reviones.getSelectedRow(), 2).toString());
            this.codigo_revisor_revisar.setText(this.ModeloTablaRevisiones.getValueAt(this.Tabla_Reviones.getSelectedRow(), 1).toString());
        }
        else
        {
            JOptionPane.showMessageDialog(null, "No se ha seleccionado ninguna fila.");
        }
        
    }//GEN-LAST:event_modificarRevision

    private void borrarRevision(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_borrarRevision
        
        if(controlador != null && this.Tabla_Reviones.getSelectedRow() != -1)
        {
            if(controlador.comprobarRevisionExiste(this.ModeloTablaRevisiones.getValueAt(this.Tabla_Reviones.getSelectedRow(), 0).toString()) == true)
            {
                controlador.eliminarRevision(this.ModeloTablaRevisiones.getValueAt(this.Tabla_Reviones.getSelectedRow(), 0).toString());
                this.cargarRevisiones();
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "No se ha seleccionado ninguna fila.");
        }
        
    }//GEN-LAST:event_borrarRevision

    private void guardarRevision(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_guardarRevision
        
        if(controlador != null && this.Tabla_Reviones.getSelectedRow() != -1)
        {
            String codigo_revision = this.ModeloTablaRevisiones.getValueAt(this.Tabla_Reviones.getSelectedRow(), 0).toString();
            if((this.codigo_revisar.getText().isEmpty() == false) && (this.vin_revisar.getText().isEmpty() == false) && (this.codigo_revisor_revisar.getText().isEmpty() == false))
            {
                if((codigo_revision.equals(this.codigo_revisar.getText().toString()) == true) && (controlador.comprobarRevisorExiste(this.codigo_revisor_revisar.getText().toString()) == true) && (controlador.comprobarCocheExiste(this.vin_revisar.getText()) == true))
                {
                    controlador.modificarRevision(this.codigo_revisar.getText().toString(), this.codigo_revisor_revisar.getText().toString(), this.vin_revisar.getText());
                    this.cargarRevisiones();
                }
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "No se ha seleccionado ninguna fila.");
        }
        
    }//GEN-LAST:event_guardarRevision

    private void cancelarRevisor(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelarRevisor
        
        this.id_revisor.setText("");
        this.nombre_revisor.setText("");
        
    }//GEN-LAST:event_cancelarRevisor

    private void cancelarProvision(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelarProvision
        
        this.codigo_proveedor_provision.setText("");
        this.vin_provision.setText("");
        
    }//GEN-LAST:event_cancelarProvision

    private void guardarProvision(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_guardarProvision
        
        

        if(controlador != null && this.Tabla_Provisiones.getSelectedRow() != -1)
        {
            String codigo = this.ModeloTablaProvisiones.getValueAt(this.Tabla_Provisiones.getSelectedRow(), 0).toString();
            if((this.codigo_proveedor_provision.getText().isEmpty() == false) && (this.vin_provision.getText().isEmpty() == false))
            {
                if((codigo.equals(this.codigo_proveedor_provision.getText().toString()) == true) && (controlador.comprobarProveedorExiste(this.codigo_proveedor_provision.getText().toString()) == true) && (controlador.comprobarCocheExiste(this.vin_provision.getText()) == true) && (controlador.comprobarProvisionCocheProveedor(this.codigo_proveedor_provision.getText().toString(), this.vin_provision.getText()) == false))
                {
                    controlador.modificarProvision(this.codigo_provision.getText(),this.codigo_proveedor_provision.getText().toString(), this.vin_provision.getText());
                    this.cargarProvisiones();
                }
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "No se ha seleccionado ninguna fila.");
        }
        
    }//GEN-LAST:event_guardarProvision

    private void borrarProvision(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_borrarProvision
        
        if(controlador != null && this.Tabla_Provisiones.getSelectedRow() != -1)
        {
                controlador.eliminarProvision(this.ModeloTablaProvisiones.getValueAt(this.Tabla_Provisiones.getSelectedRow(), 0).toString());
                this.cargarProvisiones();
        }
        else
        {
            JOptionPane.showMessageDialog(null, "No se ha seleccionado ninguna fila.");
        }
        
    }//GEN-LAST:event_borrarProvision

    private void modificarProvision(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_modificarProvision
        
        if(this.Tabla_Provisiones.getSelectedRow() != -1)
        {
            this.codigo_provision.setText(this.ModeloTablaProvisiones.getValueAt(this.Tabla_Provisiones.getSelectedRow(), 0).toString());
            this.codigo_proveedor_provision.setText(this.ModeloTablaProvisiones.getValueAt(this.Tabla_Provisiones.getSelectedRow(), 1).toString());
            this.vin_provision.setText(this.ModeloTablaProvisiones.getValueAt(this.Tabla_Provisiones.getSelectedRow(), 2).toString());
        }
        else
        {
            JOptionPane.showMessageDialog(null, "No se ha seleccionado ninguna fila.");
        }
    }//GEN-LAST:event_modificarProvision

    private void aniadirProvision(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_aniadirProvision
        
        if(controlador != null)
        {
            if((this.codigo_proveedor_provision.getText().isEmpty() == false) && (this.vin_provision.getText().isEmpty() == false) && (this.codigo_provision.getText().isEmpty() == false))
            {
                if((controlador.comprobarProvisionExiste(this.codigo_proveedor_provision.getText().toString(), this.vin_provision.getText()) == false) && (controlador.comprobarProveedorExiste(this.codigo_proveedor_provision.getText().toString()) == true) && (controlador.comprobarCocheExiste(this.vin_provision.getText()) == true) && (controlador.comprobarProvisionCocheProveedor(this.codigo_proveedor_provision.getText().toString(), this.vin_provision.getText()) == false))
                {
                    controlador.aniadirProvision(this.codigo_provision.getText(), this.codigo_proveedor_provision.getText(), this.vin_provision.getText());
                    this.cargarProvisiones();
                }
            }
        }
        
    }//GEN-LAST:event_aniadirProvision

    private void jButton_guardar_persona3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_guardar_persona3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton_guardar_persona3ActionPerformed

    private void jButton_cancelar_persona3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_cancelar_persona3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton_cancelar_persona3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JTable Tabla_Clientes;
    private javax.swing.JTable Tabla_Coches;
    public javax.swing.JTable Tabla_Compras;
    public javax.swing.JTable Tabla_Provedores;
    public javax.swing.JTable Tabla_Provisiones;
    public javax.swing.JTable Tabla_Reviones;
    private javax.swing.JTable Tabla_Revisores;
    public javax.swing.JTextField bateria_coche;
    private javax.swing.JButton cancelarCliente;
    private javax.swing.JButton cancelarCoche;
    private javax.swing.JButton cancelarCompra;
    private javax.swing.JButton cancelarProvedor;
    private javax.swing.JButton cancelarRevision;
    public javax.swing.JButton cancelarRevisor;
    public javax.swing.JTextField ciudad_cliente;
    private javax.swing.JTextField codigo_proveedor_provision;
    private javax.swing.JTextField codigo_provision;
    private javax.swing.JTextField codigo_revisar;
    private javax.swing.JTextField codigo_revisor_revisar;
    public javax.swing.JTextField color_coche;
    public javax.swing.JTextField deposito_coche;
    public javax.swing.JTextField direccion_cliente;
    public javax.swing.JTextField dni_cliente;
    private javax.swing.JTextField dni_compra;
    private javax.swing.JButton guardarCliente;
    private javax.swing.JButton guardarCoche;
    private javax.swing.JButton guardarCompra;
    private javax.swing.JButton guardarProvedor;
    private javax.swing.JButton guardarRevision;
    public javax.swing.JButton guardarRevisor;
    public javax.swing.JTextField id_provedor;
    public javax.swing.JTextField id_revisor;
    private javax.swing.JButton jButton_aniadir_biblioteca;
    private javax.swing.JButton jButton_aniadir_ciudad;
    private javax.swing.JButton jButton_aniadir_libro;
    public javax.swing.JButton jButton_aniadir_persona;
    public javax.swing.JButton jButton_aniadir_persona1;
    public javax.swing.JButton jButton_aniadir_persona2;
    public javax.swing.JButton jButton_aniadir_persona3;
    private javax.swing.JButton jButton_borrar_biblioteca;
    private javax.swing.JButton jButton_borrar_ciudad;
    private javax.swing.JButton jButton_borrar_libro;
    public javax.swing.JButton jButton_borrar_persona;
    public javax.swing.JButton jButton_borrar_persona1;
    public javax.swing.JButton jButton_borrar_persona2;
    public javax.swing.JButton jButton_borrar_persona3;
    private javax.swing.JButton jButton_cancelar_persona3;
    private javax.swing.JButton jButton_guardar_persona3;
    private javax.swing.JButton jButton_modificar_biblioteca;
    private javax.swing.JButton jButton_modificar_ciudad;
    private javax.swing.JButton jButton_modificar_libro;
    public javax.swing.JButton jButton_modificar_persona;
    public javax.swing.JButton jButton_modificar_persona1;
    public javax.swing.JButton jButton_modificar_persona2;
    public javax.swing.JButton jButton_modificar_persona3;
    private javax.swing.JLabel jLabel_DNI1;
    private javax.swing.JLabel jLabel_DNI2;
    private javax.swing.JLabel jLabel_DNI3;
    private javax.swing.JLabel jLabel_DNI4;
    private javax.swing.JLabel jLabel_ciudad_natal1;
    private javax.swing.JLabel jLabel_ciudad_natal2;
    private javax.swing.JLabel jLabel_ciudad_natal3;
    private javax.swing.JLabel jLabel_direccion;
    private javax.swing.JLabel jLabel_direccion5;
    private javax.swing.JLabel jLabel_direccion6;
    private javax.swing.JLabel jLabel_direccion7;
    private javax.swing.JLabel jLabel_direccion8;
    private javax.swing.JLabel jLabel_edad_persona1;
    private javax.swing.JLabel jLabel_edad_persona2;
    private javax.swing.JLabel jLabel_nombre_biblioteca;
    private javax.swing.JLabel jLabel_nombre_ciudad;
    private javax.swing.JLabel jLabel_nombre_libro;
    private javax.swing.JLabel jLabel_nombre_libro1;
    private javax.swing.JLabel jLabel_nombre_persona;
    private javax.swing.JLabel jLabel_nombre_persona4;
    private javax.swing.JLabel jLabel_num_habitantes;
    private javax.swing.JLabel jLabel_num_habitantes1;
    private javax.swing.JLabel jLabel_num_habitantes2;
    private javax.swing.JLabel jLabel_pais;
    private javax.swing.JLabel jLabel_telefono;
    private javax.swing.JPanel jPanel_biblioteca;
    private javax.swing.JPanel jPanel_ciudad;
    private javax.swing.JPanel jPanel_libro;
    private javax.swing.JPanel jPanel_persona;
    private javax.swing.JPanel jPanel_persona1;
    private javax.swing.JPanel jPanel_persona2;
    private javax.swing.JPanel jPanel_persona3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTabbedPane jTabbedPane;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    public javax.swing.JTextField marca_coche;
    private javax.swing.JTextField matricula_compra;
    public javax.swing.JTextField modelo_coche;
    public javax.swing.JTextField nombre_cliente;
    public javax.swing.JTextField nombre_provedor;
    public javax.swing.JTextField nombre_revisor;
    public javax.swing.JTextField precio_coche;
    public javax.swing.JTextField telefono_cliente;
    public javax.swing.JTextField vin_coche;
    private javax.swing.JTextField vin_compra;
    private javax.swing.JTextField vin_provision;
    private javax.swing.JTextField vin_revisar;
    // End of variables declaration//GEN-END:variables
    //private ArrayList<Ciudad> ciudades;
    //private Ciudad ciudad_modificar;
    //private DefaultTableModel table_model_ciudad;    
    //private Boolean modif_ciudad;
    //private int id_ciudad;
    
    //private ArrayList<Biblioteca> bibliotecas;
    //private Biblioteca biblioteca_modificar;
    //private DefaultTableModel table_model_biblioteca;    
    //private Boolean modif_biblioteca;
    //private int id_biblioteca;
    
    //private ArrayList<Libro> libros;
    //private Libro libro_modificar;
    //private DefaultTableModel table_model_libro;    
    //private Boolean modif_libro;
    //private int id_libro;
    
    //private ArrayList<Persona> personas;
    //private Persona persona_modificar;
    //private DefaultTableModel table_model_persona;    
    //private Boolean modif_persona;
    //private int id_persona;
}
