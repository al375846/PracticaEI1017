package empresa.interfacesUsuario;


import empresa.clientes.Cliente;
import empresa.clientes.ClienteEmpresa;
import empresa.clientes.ClienteParticular;
import empresa.clientes.Direccion;
import empresa.factoria.Factory;
import empresa.facturas.Factura;
import empresa.llamadas.Llamada;
import empresa.operaciones.Controlador;
import empresa.operaciones.ImplementacionControlador;
import empresa.operaciones.ImplementacionModelo;
import empresa.operaciones.Modelo;
import empresa.tarifas.Diaria;
import empresa.tarifas.FranjaHoraria;
import empresa.tarifas.Tarifa;
import empresa.tarifas.TarifaBasica;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static javax.swing.SwingConstants.CENTER;
import static javax.swing.SwingConstants.TRAILING;

public class Interfaz extends JFrame implements Vista{
    private Controlador controlador;
    private Modelo modelo;
    private JFrame ventana = new JFrame("Principal");
    JDialog cargar;
    JDialog guardar;
    private JFrame data;
    private JTabbedPane tabbedPane = new JTabbedPane();
    private JTabbedPane tabla = new JTabbedPane();
    private JFrame altacliente;
    private JFrame altaLlamada;
    private JFrame configuracion;
    private JFrame factura;
    private JTabbedPane tablaCliente = new JTabbedPane();
    private JTable llamadas = new JTable();
    private JTable llamadasPerfil = new JTable();
    private JTextField setnombre;
    private JTextField setapellidos;
    private JTextField setcodigo;
    private JTextField setcorreo;
    private JTextField setcp;
    private JTextField setpoblacion;
    private JTextField setprovincia;
    private JTextField setbasica;
    private JTextField setdiaria;
    private JTextField sethoraria;
    private JComboBox setdiaAplicable;
    private JComboBox sethoraInicio;
    private JComboBox sethoraFin;
    private JTextPane datos;
    private JPanel printearAlta;
    private ClienteParticular clienteParticular;
    private ClienteEmpresa clienteEmpresa;
    private DefaultListModel modeloClientes;
    private JList listaClientes;
    private JPanel panelTarifa;
    private JButton datosCli;
    private JButton datosFac;
    private JButton periodo;
    private JPanel panelUsuario = new JPanel();
    private JTextField setNumLlamo;
    private JTextField setDuracion;
    private JTextField setYear;
    private JTextField setMonth;
    private JTextField setDay;
    private JTextField setHour;
    private JTextField setMinute;
    private Cliente clienteAdd;
    private Llamada llamadaAdd;
    private Tarifa tarifaNueva;
    private JPanel panelDatos;
    private JFrame datosFactura;
    private JFrame tarifaGeneral;
    String[] columnasLlamadas = {"Número", "Duración", "Fecha", "Hora"};
    DefaultTableModel modeloLlamadas = new DefaultTableModel(columnasLlamadas, 1);
    String[] columnasFactura = {"Código", "Importe", "Emisión", "Inicio", "Fin"};
    DefaultTableModel modeloFacturasCliente = new DefaultTableModel(columnasFactura, 1);
    private JTextField setCodigoFactura;
    private JTextField setYearInicio;
    private JTextField setMonthInicio;
    private JTextField setDayInicio;
    private JTextField setYearFin;
    private JTextField setMonthFin;
    private JTextField setDayFin;
    private Factura facturaAdd;
    private JList listaFacturas;
    private DefaultListModel modeloFacturas;
    private JTable facturasCliente = new JTable();
    private JPanel panel_basica;
    private JPanel panel_diaria;
    private JPanel panel_horaria;
    private Double nueva_tarifaBasica;
    private Double nueva_tarifaDiaria;
    private int nuevo_dia;
    private Double nueva_tarifaHoraria;
    private int nueva_horaInicio;
    private int nueva_horaFin;
    private String clienteBaja;
    private JTable resultado;
    private JTextField setbusqueda;
    private JComboBox filtro;


    public Interfaz() {}

    public void principal() {
        cargar();
       //Definir el menu
       JMenuBar menuBar = new JMenuBar();
       JMenu menu = new JMenu("Opciones");
       menu.setMnemonic(KeyEvent.VK_O);
       menu.getAccessibleContext().setAccessibleDescription("Gestión");
       menuBar.add(menu);
       menu.addSeparator();
       JMenu alta = new JMenu("Alta");
       alta.setPreferredSize(new Dimension(100,20));
       alta.setMnemonic(KeyEvent.VK_A);
       //Definir submenu alta
            JMenuItem particular = new JMenuItem("Alta Cliente Particular");
            particular.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
            alta.add(particular);
            particular.addActionListener(new Alta("P"));
            JMenuItem empresa = new JMenuItem("Alta Cliente Empresa");
            empresa.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
            empresa.addActionListener(new Alta("E"));
       alta.add(empresa);
       //Submenu alta definido
       menu.add(alta);
       menu.addSeparator();
        JMenuItem baja = new JMenuItem("Baja", KeyEvent.VK_B);
        baja.addActionListener(new Baja());
       baja.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_K, ActionEvent.CTRL_MASK));
       menu.add(baja);
       menu.addSeparator();
       JMenuItem cambiarTarifa = new JMenuItem(("Cambiar tarifa general"));
       cambiarTarifa.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, ActionEvent.CTRL_MASK));
       cambiarTarifa.addActionListener(new NuevaTarifaGeneral());
       menu.add(cambiarTarifa);
       menu.addSeparator();
        JMenuItem guardar = new JMenuItem("Guardar");
       guardar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
       menu.add(guardar);
       menu.addSeparator();
        JMenuItem cargar = new JMenuItem("Cargar");
        cargar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.CTRL_MASK));
        menu.add(cargar);
        ventana.setJMenuBar(menuBar);
       //Menu definido


        ventana.add(tabbedPane);
        JPanel panel1 = new JPanel();
       //Botones de accion

        datosCli = new JButton("Datos Cliente");
        datosCli.addActionListener(new DatosCliente());
        datosCli.setEnabled(false);
        datosFac = new JButton("Datos Factura");
        datosFac.addActionListener(new DatosFactura());
        datosFac.setEnabled(false);
        periodo = new JButton("Periodo");
        periodo.setEnabled(false);
        panel1.setLayout(null);
        datosCli.setBounds(10, 10,120,20);
        datosFac.setBounds(140,10,120,20);
        periodo.setBounds(270, 10, 120, 20);
        panel1.add(datosCli);
        panel1.add(datosFac);
        panel1.add(periodo);
        //Fin botones de accion

        //Listas
        modeloClientes = new DefaultListModel();
        listaClientes = new JList();
        listaClientes.setModel(modeloClientes);
        listaClientes.setLayout(null);
        listaClientes.setLayoutOrientation(JList.VERTICAL);
        JScrollPane scroller = new JScrollPane(listaClientes);
        scroller.setBounds(10,80,150,200);
        panel1.add(scroller);
        JLabel lisCli = new JLabel("Lista de clientes");
        lisCli.setBounds(35, 60, 120, 20);
        panel1.add(lisCli);

        modeloFacturas = new DefaultListModel();
        listaFacturas = new JList();
        listaFacturas.setModel(modeloFacturas);
        listaFacturas.setLayout(null);
        listaFacturas.setLayoutOrientation(JList.VERTICAL);
        JScrollPane scroller2 = new JScrollPane(listaFacturas);
        scroller2.setBounds(170,80,150,200);
        panel1.add(scroller2);
        JLabel lisFac = new JLabel("Lista de facturas");
       lisFac.setBounds(195, 60, 120, 20);
       panel1.add(lisFac);
       //Fin listas

       //Buscar
       JLabel busqueda = new JLabel("Buscar:");
       busqueda.setBounds(10, 300, 50, 20);
       panel1.add(busqueda);
       String[] tipos = { "Cliente", "Factura"};
       filtro = new JComboBox(tipos);
       filtro.setSelectedIndex(0);
       filtro.setBounds(70, 300, 100, 20);
       panel1.add(filtro);
       setbusqueda = new JTextField("Introduzca el código", 50);
        setbusqueda.setBounds(180, 300, 150, 20);
        setbusqueda.setHorizontalAlignment(TRAILING);
       panel1.add(setbusqueda);
       JButton buscar = new JButton("Buscar");
       buscar.addActionListener(new BusquedaCodigo());
       buscar.setBounds(340, 300, 100, 20);
       panel1.add(buscar);
       resultado = new JTable();
        JScrollPane scrollPane = new JScrollPane(resultado);
      scrollPane.setBounds(10, 350, 570, 150);
        resultado.setFillsViewportHeight(true);
        panel1.add(scrollPane);


        tabbedPane.addTab("Gestor", null, panel1, "No hace nada");
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
        //tabbedPane.setEnabledAt(0, true);
        JComponent label3 = new JLabel("Text-Only Label");
        tabbedPane.addTab("Perfil de cliente", label3);
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
        //tabbedPane.setEnabledAt(1, true);
        //tabbedPane.setVisible(true);
        tabbedPane.setForegroundAt(0, Color.red);
        ventana.setSize(600,600);
        ventana.setResizable(false);
        ventana.setVisible(true);
        modelo.actualizar();
        ventana.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        ventana.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                guardar();
            }
        });
    }

    public void cargar() {
        cargar = new JDialog(ventana, "Cargar", true);
        JPanel panelCargar = new JPanel();
        panelCargar.setLayout(null);
        JButton yes = new JButton("Si");
        yes.addActionListener(new Cargar("Y"));
        JButton no = new JButton("No");
        no.addActionListener(new Cargar("N"));
        yes.setBounds(55, 40, 60, 30);
        no.setBounds(125, 40, 60, 30);
        JLabel pregunta = new JLabel("¿Desea cargar los datos guardados?");
        pregunta.setBounds(10, 10, 240, 30);
        panelCargar.add(yes);
        panelCargar.add(no);
        panelCargar.add(pregunta);
        cargar.add(panelCargar);
        cargar.setSize(240, 110);
        cargar.setResizable(false);
        cargar.setVisible(true);
    }

    public void guardar() {
        guardar = new JDialog(ventana, "Guardar", true);
        JPanel panelGuardar = new JPanel();
        panelGuardar.setLayout(null);
        JButton yes = new JButton("Si");
        yes.addActionListener(new Guardar("Y"));
        JButton no = new JButton("No");
        no.addActionListener(new Guardar("N"));
        yes.setBounds(55, 40, 60, 30);
        no.setBounds(125, 40, 60, 30);
        JLabel pregunta = new JLabel("¿Desea guardar los datos creados?");
        pregunta.setBounds(10, 10, 240, 30);
        panelGuardar.add(yes);
        panelGuardar.add(no);
        panelGuardar.add(pregunta);
        guardar.add(panelGuardar);
        guardar.setSize(240, 110);
        guardar.setResizable(false);
        guardar.setVisible(true);
    }

    public void altaCliente(String tipo){
        altacliente = new JFrame("Alta Cliente");
        //Datos del cliente
        //Nombre
        JPanel panel1 = new JPanel();
        panel1.setLayout(null);
        if (tipo.equals("P")){
            JLabel particular = new JLabel("CLIENTE PARTICULAR");
            particular.setBounds(200, 20, 200, 20);
            JLabel nombre = new JLabel("Nombre: ");
            nombre.setBounds(20, 60, 80, 20);
            panel1.add(particular);
            panel1.add(nombre);
        } else{
            JLabel empresa = new JLabel("CLIENTE EMPRESA");
            empresa.setBounds(200, 20, 200, 20);
            JLabel nombre = new JLabel("Empresa: ");
            nombre.setBounds(20, 60, 80, 20);
            panel1.add(empresa);
            panel1.add(nombre);
        }
        setnombre = new JTextField("Introduzca el nombre", 300);
        setnombre.setBounds(130, 60, 300, 20);
        setnombre.setHorizontalAlignment(JTextField.TRAILING);
        panel1.add(setnombre);
        //Apellidos
        JLabel apellidos = new JLabel("Apellidos: ");
        apellidos.setBounds(20, 100, 80, 20);
        setapellidos = new JTextField("Introduzca los apellidos", 300);
        setapellidos.setBounds(130, 100, 300, 20);
        setapellidos.setHorizontalAlignment(JTextField.TRAILING);
        panel1.add(apellidos);
        panel1.add(setapellidos);
        if(tipo.equals("E")) {
            apellidos.setEnabled(false);
            setapellidos.setEnabled(false);
        }
        //Codigo
        JLabel codigo = new JLabel("Código: ");
        codigo.setBounds(20, 140, 80, 20);
        setcodigo = new JTextField("Código", 100);
        setcodigo.setBounds(130, 140, 100, 20);
        setcodigo.setHorizontalAlignment(JTextField.TRAILING);
        panel1.add(codigo);
        panel1.add(setcodigo);
        //Correo
        JLabel correo = new JLabel("Correo: ");
        correo.setBounds(20, 180, 80, 20);
        setcorreo = new JTextField("Introduzca el correo", 300);
        setcorreo.setBounds(130, 180, 300, 20);
        setcorreo.setHorizontalAlignment(JTextField.TRAILING);
        panel1.add(correo);
        panel1.add(setcorreo);
        //Direccion
        JLabel direccion = new JLabel("Dirección: ");
        direccion.setBounds(20, 220, 80, 20);
        JLabel cp = new JLabel("CP: ");
        cp.setBounds(100, 260, 80, 20);
        setcp = new JTextField("Cp", 80);
        setcp.setBounds(200, 260, 80, 20);
        setcp.setHorizontalAlignment(JTextField.TRAILING);
        JLabel poblacion = new JLabel("Población: ");
        poblacion.setBounds(100, 300, 80, 20);
        setpoblacion = new JTextField("Población", 120);
        setpoblacion.setBounds(200, 300, 120, 20);
        setpoblacion.setHorizontalAlignment(JTextField.TRAILING);
        JLabel provincia = new JLabel("Provincia: ");
        provincia.setBounds(100, 340, 80, 20);
        setprovincia = new JTextField("Provincia", 120);
        setprovincia.setBounds(200, 340, 120, 20);
        setprovincia.setHorizontalAlignment(JTextField.TRAILING);
        panel1.add(direccion);
        panel1.add(cp);
        panel1.add(setcp);
        panel1.add(poblacion);
        panel1.add(setpoblacion);
        panel1.add(provincia);
        panel1.add(setprovincia);
        //Siguiente
        JButton siguiente = new JButton("Siguiente");
        siguiente.setBounds(480, 340, 100, 40);
        siguiente.addActionListener(new Siguiente(1));
        panel1.add(siguiente);
        //Tarifa
        //RadioButtons
        panelTarifa = new JPanel();
        JPanel panel3 = new JPanel();
        panel3.setLayout(null);
        panelTarifa.setLayout(null);
        JRadioButton genereal = new JRadioButton("Añadir cliente con la tarifa genereal");
        genereal.setSelected(true);
        genereal.addActionListener(new TarifaNormal());
        genereal.setBounds(20, 20, 400, 20);
        JRadioButton personalizada = new JRadioButton("Añadir cliente con una tarifa personalizada");
        personalizada.addActionListener(new TarifaPersonalizada());
        personalizada.setBounds(20, 60, 400, 20);
        ButtonGroup group = new ButtonGroup();
        group.add(genereal);
        group.add(personalizada);
        panel3.add(genereal);
        panel3.add(personalizada);
        //Tarifa personalizada
        JLabel basica = new JLabel("Tarifa Básica: ");
        basica.setBounds(50, 100, 105, 20);
        setbasica = new JTextField( 80);
        setbasica.setBounds(165, 100, 80, 20);
        setbasica.setHorizontalAlignment(JTextField.TRAILING);
        JLabel diaria = new JLabel("Tarifa Diaria: ");
        diaria.setBounds(50, 140, 105, 20);
        setdiaria = new JTextField( 80);
        setdiaria.setBounds(165, 140, 80, 20);
        setdiaria.setHorizontalAlignment(JTextField.TRAILING);
        JLabel diaApliacable = new JLabel("Dia aplicable: ");
        diaApliacable.setBounds(100, 180, 100, 20);
        String[] dias = { "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"};
        setdiaAplicable = new JComboBox(dias);
        setdiaAplicable.setBounds(210, 180, 120, 20);
        JLabel horaria = new JLabel("Tarifa Horaria: ");
        horaria.setBounds(50, 220, 105, 20);
        sethoraria = new JTextField( 80);
        sethoraria.setBounds(165, 220, 100, 20);
        sethoraria.setHorizontalAlignment(JTextField.TRAILING);
        String[] horas = { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10",
                "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"};
        JLabel horaInicio = new JLabel("Hora Inicio: ");
        horaInicio.setBounds(100, 260, 85, 20);
        sethoraInicio = new JComboBox(horas);
        sethoraInicio.setBounds(200, 260, 60, 20);
        JLabel horaFin = new JLabel("Hora fin: ");
        horaFin.setBounds(280, 260, 70, 20);
        sethoraFin = new JComboBox(horas);
        sethoraFin.setBounds(365, 260, 60, 20);
        //Siguiente
        JButton siguiente2 = new JButton("Siguiente");
        siguiente2.setBounds(480, 340, 100, 40);
        siguiente2.addActionListener(new Siguiente(2));
        panel1.add(siguiente);
        panelTarifa.add(basica);
        panelTarifa.add(setbasica);
        panelTarifa.add(diaria);
        panelTarifa.add(setdiaria);
        panelTarifa.add(diaApliacable);
        panelTarifa.add(setdiaAplicable);
        panelTarifa.add(horaria);
        panelTarifa.add(sethoraria);
        panelTarifa.add(horaInicio);
        panelTarifa.add(sethoraInicio);
        panelTarifa.add(horaFin);
        panelTarifa.add(sethoraFin);
        panelTarifa.setBounds(0, 0, 550, 450);
        panelTarifa.setVisible(false);
        panel3.add(siguiente2);
        panel3.add(panelTarifa);
        //Cliente
        printearAlta = new JPanel();
        printearAlta.setLayout(null);
        JButton terminar = new JButton("Finalizar");
        terminar.setBounds(480, 340, 100, 40);
        terminar.addActionListener(new Fin(tipo));
        //panel3.add(datos);
        printearAlta.add(terminar);

        tablaCliente.addTab("Datos", null, panel1, "Introduce los datos personales del cliente");
        tablaCliente.addTab("Tarifa", null, panel3, "Introduce los datos de la tarifa");
        tablaCliente.addTab("Cliente", null, printearAlta, "No hace nada");
        tablaCliente.setEnabledAt(1, false);
        tablaCliente.setEnabledAt(2, false);
        altacliente.add(tablaCliente);
        altacliente.setSize(600, 450);
        altacliente.setResizable(false);
        altacliente.setVisible(true);
    }

    public void fichaUsuario(Cliente cliente) {
        data = new JFrame("Ficha Usuario");
        panelUsuario.setLayout(null);
        //Logo
        JPanel panel_1 = new JPanel();
        panel_1.setLayout(null);
        ImageIcon imagen = new ImageIcon("src/media/logoUsuario.png");
        ImageIcon icon = new ImageIcon(imagen.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH));
        JLabel label = new JLabel(icon, CENTER);
        label.setBounds(50, 50, 250, 250);
        JLabel label2 = new JLabel(cliente.getNombre(), CENTER);
        label2.setBounds(50, 310, 250, 30);
        panel_1.add(label);
        panel_1.add(label2);

        //Acciones
        ImageIcon configuracion = new ImageIcon("src/media/ajustes.jpeg");
        ImageIcon im_configuracion = new ImageIcon(configuracion.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH));
        JButton ajustes = new JButton(im_configuracion);
        ajustes.addActionListener(new Configuracion());
        ajustes.setBounds(275, 350, 50, 50);
        panelUsuario.add(ajustes);
        ImageIcon llamada = new ImageIcon("src/media/telefono.png");
        ImageIcon im_llamada = new ImageIcon(llamada.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
        JButton alta_llamada = new JButton(im_llamada);
        alta_llamada.addActionListener(new addLlamadaCliente());
        alta_llamada.setBounds(50, 350, 50, 50);
        panel_1.add(alta_llamada);
        ImageIcon factura = new ImageIcon("src/media/facturas.png");
        ImageIcon im_factura = new ImageIcon(factura.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
        JButton emitir_factura = new JButton(im_factura);
        emitir_factura.addActionListener(new AltaFactura());
        emitir_factura.setBounds(125, 350, 50, 50);
        panel_1.add(emitir_factura);
        ImageIcon buscar = new ImageIcon("src/media/buscar.png");
        ImageIcon im_buscar = new ImageIcon(buscar.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
        JButton buscar_Periodo = new JButton(im_buscar);
        buscar_Periodo.setBounds(200, 350, 50, 50);
        panel_1.add(buscar_Periodo);

        //Visor de datos
        panelDatos = new JPanel();
        datos.setEditable(false);
        datos.setPreferredSize(new Dimension(400, 400));
        panelDatos.add(datos);
        tabla.addTab("Datos", null, panelDatos, "Datos del cliente");

        //Llamadas
        JPanel panel_3 = new JPanel();
        llamadasPerfil.setPreferredSize(new Dimension(290, 400));
        JScrollPane scrollPane = new JScrollPane(llamadasPerfil);
        scrollPane.setPreferredSize(new Dimension(395, 400));
        panel_3.add(scrollPane);
        tabla.addTab("Llamadas", null, panel_3);

        //Facturas
        JPanel panel_4 = new JPanel();
        facturasCliente.setPreferredSize(new Dimension(290, 400));
        JScrollPane scrollPane2 = new JScrollPane(facturasCliente);
        scrollPane2.setPreferredSize(new Dimension(395, 400));
        panel_4.add(scrollPane2);
        tabla.addTab("Facturas", null, panel_4);

        /*JButton salir = new JButton("Salir");
        salir.addActionListener(new FinFichaUsuario());
        salir.setBounds(560, 400, 100, 50);
        panelUsuario.add(salir);
         */

        panel_1.setBounds(0, 0, 350, 500);
        tabla.setBounds(350, 20, 400, 380);
        panelUsuario.add(panel_1);
        panelUsuario.add(tabla);
        tabla.setVisible(true);
        data.add(panelUsuario);
        data.setSize(800, 500);
        data.setResizable(false);
        data.setVisible(true);
        data.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        data.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                tabla.remove(0);
                tabla.remove(0);
                tabla.remove(0);
            }
        });
    }


    public void altaLlamada() {
        altaLlamada = new JFrame("Alta Llamada");
        llamadas = new JTable();
        llamadas.setModel(modeloLlamadas);
        JPanel panel_llamada = new JPanel();
        panel_llamada.setLayout(null);
        JLabel num_llamo = new JLabel("Número al que llamó: ");
        JLabel duracion = new JLabel("Duración de la llamada: ");
        JLabel fecha = new JLabel("Fecha en la que se efectuó: ");
        JLabel hour = new JLabel("Hora en la que se efectuó: ");
        num_llamo.setBounds(10,10, 200, 30);
        duracion.setBounds(10,50, 200, 30);
        fecha.setBounds(10,90, 200, 30);
        hour.setBounds(10,130, 200, 30);
        panel_llamada.add(num_llamo);
        panel_llamada.add(duracion);
        panel_llamada.add(fecha);
        panel_llamada.add(hour);
        setNumLlamo = new JTextField(80);
        setDuracion = new JTextField(80);
        setYear = new JTextField(4);
        setMonth = new JTextField(2);
        setDay = new JTextField(2);
        setHour = new JTextField(2);
        setMinute = new JTextField(2);
        setNumLlamo.setBounds(220, 10, 100, 30);
        setDuracion.setBounds(220, 50, 100, 30);
        setDay.setBounds(220, 90, 40, 30);
        setMonth.setBounds(280, 90, 40, 30);
        setYear.setBounds(340, 90, 80, 30);
        setHour.setBounds(220, 130, 40, 30);
        setMinute.setBounds(280, 130, 40, 30);
        panel_llamada.add(setNumLlamo);
        panel_llamada.add(setDuracion);
        panel_llamada.add(setYear);
        panel_llamada.add(setMonth);
        panel_llamada.add(setDay);
        panel_llamada.add(setHour);
        panel_llamada.add(setMinute);
        JLabel slash1 = new JLabel("/", CENTER);
        JLabel slash2 = new JLabel("/", CENTER);
        JLabel puntos = new JLabel(":", CENTER);
        slash1.setBounds(260, 90, 20, 30);
        slash2.setBounds(320, 90, 20, 30);
        puntos.setBounds(260, 130, 20, 30);
        panel_llamada.add(slash1);
        panel_llamada.add(slash2);
        panel_llamada.add(puntos);
        ImageIcon plus = new ImageIcon("src/media/añadir.jpg");
        ImageIcon im_add = new ImageIcon(plus.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH));
        JButton add = new JButton(im_add);
        add.addActionListener(new AltaLlamada());
        add.setBounds(560, 130, 25, 25);
        panel_llamada.add(add);
        JScrollPane scrollPane = new JScrollPane(llamadas);
        scrollPane.setPreferredSize(new Dimension(550, 250));
        scrollPane.setBounds(10, 210, 550, 250);
        panel_llamada.add(scrollPane);
        altaLlamada.add(panel_llamada);
        altaLlamada.setSize(600, 500);
        altaLlamada.setVisible(true);
        altaLlamada.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        altaLlamada.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                llamadas.setModel(new DefaultTableModel());
                llamadas.removeAll();
            }
        });
    }
    private void configuracionUsuario(Cliente cliente){
        configuracion = new JFrame(cliente.getNombre() + " - Configuración");
        JPanel panel_1 = new JPanel();
        panel_1.setLayout(null);
        JLabel basica = new JLabel("Tarifa Básica: ");
        basica.setBounds(20, 20, 105, 20);
        setbasica = new JTextField( 80);
        setbasica.setBounds(135, 20, 80, 20);
        setbasica.setHorizontalAlignment(JTextField.TRAILING);
        JLabel diaria = new JLabel("Tarifa Diaria: ");
        diaria.setBounds(20, 60, 105, 20);
        setdiaria = new JTextField( 80);
        setdiaria.setBounds(135, 60, 80, 20);
        setdiaria.setHorizontalAlignment(JTextField.TRAILING);
        JLabel diaApliacable = new JLabel("Dia aplicable: ");
        diaApliacable.setBounds(60, 100, 100, 20);
        String[] dias = { "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"};
        setdiaAplicable = new JComboBox(dias);
        setdiaAplicable.setBounds(175, 100, 100, 20);
        JLabel horaria = new JLabel("Tarifa Horaria: ");
        horaria.setBounds(20, 140, 105, 20);
        sethoraria = new JTextField( 80);
        sethoraria.setBounds(135, 140, 80, 20);
        sethoraria.setHorizontalAlignment(JTextField.TRAILING);
        String[] horas = { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10",
                "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"};
        JLabel horaInicio = new JLabel("Hora Inicio: ");
        horaInicio.setBounds(60, 180, 85, 20);
        sethoraInicio = new JComboBox(horas);
        sethoraInicio.setBounds(155, 180, 60, 20);
        JLabel horaFin = new JLabel("Hora fin: ");
        horaFin.setBounds(225, 180, 70, 20);
        sethoraFin = new JComboBox(horas);
        sethoraFin.setBounds(305, 180, 60, 20);
        //Aplicar
        JButton aplicar = new JButton("Aplicar");
        aplicar.setBounds(295, 220, 85, 30);
        aplicar.addActionListener(new NuevaTarifa());
        panel_1.add(basica);
        panel_1.add(setbasica);
        panel_1.add(diaria);
        panel_1.add(setdiaria);
        panel_1.add(diaApliacable);
        panel_1.add(setdiaAplicable);
        panel_1.add(horaria);
        panel_1.add(sethoraria);
        panel_1.add(horaInicio);
        panel_1.add(sethoraInicio);
        panel_1.add(horaFin);
        panel_1.add(sethoraFin);
        panel_1.add(aplicar);

        configuracion.add(panel_1);
        configuracion.setSize(400, 300);
        configuracion.setResizable(false);
        configuracion.setVisible(true);
    }

    public void altaFactura() {
        factura = new JFrame("Alta Factura");
        JPanel panelFactura = new JPanel();
        panelFactura.setLayout(null);
        JLabel codigoFactura = new JLabel("Código de la factura: ");
        codigoFactura.setBounds(10, 10, 150, 30);
        JLabel periodoFactura = new JLabel("Periodo de facturación");
        periodoFactura.setBounds(10, 50, 150, 30);
        JLabel fechaInicio = new JLabel("Fecha de inicio:");
        fechaInicio.setBounds(50, 90, 100, 30);
        JLabel fechaFinal = new JLabel("Fecha de fin: ");
        fechaFinal.setBounds(50, 130, 100, 30);
        panelFactura.add(codigoFactura);
        panelFactura.add(periodoFactura);
        panelFactura.add(fechaInicio);
        panelFactura.add(fechaFinal);
        setCodigoFactura = new JTextField(80);
        setCodigoFactura.setBounds(170, 10, 100, 30);
        setCodigoFactura.setHorizontalAlignment(TRAILING);
        setYearInicio = new JTextField(4);
        setMonthInicio = new JTextField(2);
        setDayInicio = new JTextField(2);
        setDayInicio.setBounds(170, 90, 40, 30);
        setMonthInicio.setBounds(230, 90, 40, 30);
        setYearInicio.setBounds(290, 90, 80, 30);
        panelFactura.add(setCodigoFactura);
        panelFactura.add(setDayInicio);
        panelFactura.add(setMonthInicio);
        panelFactura.add(setYearInicio);
        JLabel slash1 = new JLabel("/", CENTER);
        JLabel slash2 = new JLabel("/", CENTER);
        slash1.setBounds(210, 90, 20, 30);
        slash2.setBounds(270, 90, 20, 30);
        panelFactura.add(slash1);
        panelFactura.add(slash2);
        setYearFin = new JTextField(4);
        setMonthFin = new JTextField(2);
        setDayFin = new JTextField(2);
        setDayFin.setBounds(170, 130, 40, 30);
        setMonthFin.setBounds(230, 130, 40, 30);
        setYearFin.setBounds(290, 130, 80, 30);
        panelFactura.add(setDayFin);
        panelFactura.add(setMonthFin);
        panelFactura.add(setYearFin);
        JLabel slash3 = new JLabel("/", CENTER);
        JLabel slash4 = new JLabel("/", CENTER);
        slash3.setBounds(210, 130, 20, 30);
        slash4.setBounds(270, 130, 20, 30);
        panelFactura.add(slash3);
        panelFactura.add(slash4);
        JButton aplicar = new JButton("Añadir");
        aplicar.addActionListener(new NuevaFactura());
        aplicar.setBounds(295, 220, 85, 30);
        panelFactura.add(aplicar);
        factura.add(panelFactura);
        factura.setSize(500, 500);
        factura.setVisible(true);
        factura.setResizable(false);
    }

    public void datosFactura(Factura factura){
        datosFactura = new JFrame("Factura: " + factura.getCodigo());
        JPanel panel_1 = new JPanel();
        JTextPane datos = createFacturaPane(factura);
        datos.setPreferredSize(new Dimension(400, 400));
        datos.setEditable(false);
        panel_1.add(datos);
        datosFactura.add(panel_1);
        datosFactura.setSize(400, 400);
        datosFactura.setVisible(true);
        datosFactura.setResizable(false);
    }

    public void cambiarTarifaGeneral(){
        tarifaGeneral = new JFrame ("Tarifas");
        tarifaGeneral.setLayout(null);
        JPanel panel_1 = new JPanel();
        panel_1.setLayout(null);
        JLabel texto = new JLabel("Seleccione la tarifa que desea cambiar");
        texto.setBounds(20, 20, 350,20);
        JRadioButton tarifa_básica = new JRadioButton("Tarifa Básica");
        tarifa_básica.addActionListener(new ModificarTarifaBasica());
        tarifa_básica.setBounds(20, 60, 150, 20);
        JRadioButton tarifa_diaria = new JRadioButton("Tarifa Diaria");
        tarifa_diaria.addActionListener( new ModificarTarifaDiaria());
        tarifa_diaria.setBounds(20, 100, 150, 20);
        JRadioButton tarifa_horaria = new JRadioButton("Tarifa Horaria");
        tarifa_horaria.addActionListener(new ModificarTarifaHoraria());
        tarifa_horaria.setBounds(20, 140, 150, 20);
        ButtonGroup group = new ButtonGroup();
        group.add(tarifa_básica);
        group.add(tarifa_diaria);
        group.add(tarifa_horaria);
        panel_1.add(texto);
        panel_1.add(tarifa_básica);
        panel_1.add(tarifa_diaria);
        panel_1.add(tarifa_horaria);
        panel_1.setBounds(0, 0, 350, 160);

        panel_basica = new JPanel();
        panel_basica.setLayout(null);
        JLabel basica = new JLabel("------------------TARIFA BÁSICA------------------");
        basica.setBounds(20, 10, 320, 20);
        JLabel precio_basica = new JLabel("Precio: ");
        precio_basica.setBounds(50, 50, 60, 20);
        setbasica = new JTextField( 70);
        setbasica.setBounds(130, 50, 70, 20);
        setbasica.setHorizontalAlignment(JTextField.TRAILING);
        JButton aplicar_basica = new JButton("Aplicar");
        aplicar_basica.setBounds(245, 160, 85, 30);
        aplicar_basica.addActionListener(new NuevaTarifaBasica());
        panel_basica.add(basica);
        panel_basica.add(precio_basica);
        panel_basica.add(setbasica);
        panel_basica.add(aplicar_basica);
        panel_basica.setBounds(0, 160, 350, 210);
        panel_basica.setVisible(false);

        panel_diaria = new JPanel();
        panel_diaria.setLayout(null);
        JLabel diaria = new JLabel("-------------------TARIFA DIARIA-------------------");
        diaria.setBounds(20, 10, 320, 20);
        JLabel precio_diaria = new JLabel("Precio: ");
        precio_diaria.setBounds(20, 50, 60, 20);
        setdiaria = new JTextField( 70);
        setdiaria.setBounds(100, 50, 70, 20);
        setdiaria.setHorizontalAlignment(JTextField.TRAILING);
        JLabel diaApliacable = new JLabel("Dia aplicable: ");
        diaApliacable.setBounds(20, 90, 100, 20);
        String[] dias = { "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"};
        setdiaAplicable = new JComboBox(dias);
        setdiaAplicable.setBounds(140, 90, 100, 20);
        JButton aplicar_diaria = new JButton("Aplicar");
        aplicar_diaria.setBounds(245, 160, 85, 30);
        aplicar_diaria.addActionListener(new NuevaTarifaDiaria());
        panel_diaria.add(diaria);
        panel_diaria.add(precio_diaria);
        panel_diaria.add(setdiaria);
        panel_diaria.add(diaApliacable);
        panel_diaria.add(setdiaAplicable);
        panel_diaria.add(aplicar_diaria);
        panel_diaria.setBounds(0, 160, 350, 210);
        panel_diaria.setVisible(false);

        panel_horaria = new JPanel();
        panel_horaria.setLayout(null);
        JLabel horaria = new JLabel("-----------------TARIFA HORARIA-----------------");
        horaria.setBounds(20, 10, 320, 20);
        JLabel precio_horaria = new JLabel("Precio: ");
        precio_horaria.setBounds(20, 50, 60, 20);
        sethoraria = new JTextField( 70);
        sethoraria.setBounds(100, 50, 70, 20);
        sethoraria.setHorizontalAlignment(JTextField.TRAILING);
        String[] horas = { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10",
                "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"};
        JLabel horaInicio = new JLabel("Hora Inicio: ");
        horaInicio.setBounds(20, 90, 85, 20);
        sethoraInicio = new JComboBox(horas);
        sethoraInicio.setBounds(115, 90, 50, 20);
        JLabel horaFin = new JLabel("Hora fin: ");
        horaFin.setBounds(185, 90, 70, 20);
        sethoraFin = new JComboBox(horas);
        sethoraFin.setBounds(265, 90, 50, 20);
        JButton aplicar_horaria = new JButton("Aplicar");
        aplicar_horaria.setBounds(245, 160, 85, 30);
        aplicar_horaria.addActionListener(new NuevaTarifaHoraria());
        panel_horaria.add(horaria);
        panel_horaria.add(precio_horaria);
        panel_horaria.add(sethoraria);
        panel_horaria.add(horaInicio);
        panel_horaria.add(sethoraInicio);
        panel_horaria.add(horaFin);
        panel_horaria.add(sethoraFin);
        panel_horaria.add(aplicar_horaria);
        panel_horaria.setBounds(0, 160, 350, 210);
        panel_horaria.setVisible(false);

        tarifaGeneral.add(panel_1);
        tarifaGeneral.add(panel_basica);
        tarifaGeneral.add(panel_diaria);
        tarifaGeneral.add(panel_horaria);
        tarifaGeneral.setSize(350, 400);
        tarifaGeneral.setVisible(true);

    }


    // ---------------------------TEXTPANES-----------------------------------------------------
    private JTextPane createClientePartPane(ClienteParticular clienteParticular) {
        String fecha = (clienteParticular.getFecha().get(Calendar.DAY_OF_MONTH) + "/" + (clienteParticular.getFecha().get(Calendar.MONTH) + 1)+ "/" + clienteParticular.getFecha().get(Calendar.YEAR));
        String[] initString =
                { "Nombre: ", clienteParticular.getNombre() + "\n\n",
                        "Apellidos: ",  clienteParticular.getApellidos() + "\n\n",
                        "DNI: ", clienteParticular.getCodigo() + "\n\n",
                        "Correo: ",  clienteParticular.getCorreo() + "\n\n",
                        "Dirección: " + "\n",
                        "\t" + "Cp: ", clienteParticular.getDireccion().getCp() + "\n",
                        "\t" + "Población: ", clienteParticular.getDireccion().getPoblacion() + "\n",
                        "\t" + "Provincia: ", clienteParticular.getDireccion().getProvincia() + "\n\n",
                        "Fecha alta: ", fecha + "\n\n",
                        "Tarifa: " + "\n", clienteParticular.getTarifa().toString()
                };

        String[] initStyles =
                { "bold", "regular", "bold", "regular", "bold", "regular", "bold", "regular", "bold", "italic", "regular", "italic", "regular", "italic", "regular", "bold", "regular", "bold", "regular"
                };
        JTextPane textPane = new JTextPane();
        StyledDocument doc = textPane.getStyledDocument();
        addStylesToDocument(doc);

        try {
            for (int i=0; i < initString.length; i++) {
                doc.insertString(doc.getLength(), initString[i],
                        doc.getStyle(initStyles[i]));
            }
        } catch (BadLocationException ble) {
            System.err.println("Couldn't insert initial text into text pane.");
        }

        return textPane;
    }
    private JTextPane createClienteEmpPane(ClienteEmpresa clienteEmpresa) {
        String fecha = (clienteEmpresa.getFecha().get(Calendar.DAY_OF_MONTH) + "/" + (clienteEmpresa.getFecha().get(Calendar.MONTH) + 1)+ "/" + clienteEmpresa.getFecha().get(Calendar.YEAR));
        String[] initString =
                { "Empresa: ", clienteEmpresa.getNombre() + "\n\n",
                        "Nif: ", clienteEmpresa.getCodigo() + "\n\n",
                        "Correo: ",  clienteEmpresa.getCorreo() + "\n\n",
                        "Dirección: " + "\n",
                        "\t" + "Cp: ", clienteEmpresa.getDireccion().getCp() + "\n",
                        "\t" + "Población: ", clienteEmpresa.getDireccion().getPoblacion() + "\n",
                        "\t" + "Provincia: ", clienteEmpresa.getDireccion().getProvincia() + "\n\n",
                        "Fecha alta: ", fecha + "\n\n",
                        "Tarifa: " + "\n", clienteEmpresa.getTarifa().toString()
                };

        String[] initStyles =
                { "bold", "regular", "bold", "regular", "bold", "regular", "bold", "italic", "regular", "italic", "regular", "italic", "regular", "bold", "regular", "bold", "regular"
                };
        JTextPane textPane = new JTextPane();
        StyledDocument doc = textPane.getStyledDocument();
        addStylesToDocument(doc);

        try {
            for (int i=0; i < initString.length; i++) {
                doc.insertString(doc.getLength(), initString[i],
                        doc.getStyle(initStyles[i]));
            }
        } catch (BadLocationException ble) {
            System.err.println("Couldn't insert initial text into text pane.");
        }

        return textPane;
    }
    private JTextPane createFacturaPane(Factura factura) {
        String[] initString =
                { "Código de la factura: ", factura.getCodigo() + "\n\n",
                        "Importe: ", factura.getImporte() + "\n\n",
                        "Fecha inicio: ",  factura.impFechaInicio() + "\n\n",
                        "Fecha fin: ", factura.impFechaFin() + "\n\n",
                        "Fecha emisión: ", factura.impFecha() + "\n\n",
                        "Tarifa: " + "\n", factura.getTarifa().toString()
                };

        String[] initStyles =
                { "bold", "regular", "bold", "regular", "bold", "regular", "bold", "regular", "bold", "regular", "bold", "regular"
                };
        JTextPane textPane = new JTextPane();
        StyledDocument doc = textPane.getStyledDocument();
        addStylesToDocument(doc);

        try {
            for (int i=0; i < initString.length; i++) {
                doc.insertString(doc.getLength(), initString[i],
                        doc.getStyle(initStyles[i]));
            }
        } catch (BadLocationException ble) {
            System.err.println("Couldn't insert initial text into text pane.");
        }

        return textPane;
    }
    protected void addStylesToDocument(StyledDocument doc) {
        //Initialize some styles.
        Style def = StyleContext.getDefaultStyleContext().
                getStyle(StyleContext.DEFAULT_STYLE);

        Style regular = doc.addStyle("regular", def);
        StyleConstants.setFontFamily(def, "SansSerif");
        StyleConstants.setFontSize(def, 14);

        Style s = doc.addStyle("bold", regular);
        StyleConstants.setBold(s, true);

        s = doc.addStyle("italic", regular);
        StyleConstants.setItalic(s, true);
    }

    // -----------------------------GETTERS------------------------------------------------------

    public ClienteParticular getClientePart() {
        return this.clienteParticular;
    }

    public ClienteEmpresa getClienteEmp() {
        return this.clienteEmpresa;
    }

    public Cliente getClienteAdd() {
        return this.clienteAdd;
    }

    public Llamada getLlamadaAdd() {
        return this.llamadaAdd;
    }

    public Tarifa getTarifaNueva() {
        return this.tarifaNueva;
    }

    public Factura getFacturaAdd() {
        return this.facturaAdd;
    }

    public Double getNueva_tarifaBasica() {
        return this.nueva_tarifaBasica;
    }

    public Double getNueva_tarifaDiaria() {
        return this.nueva_tarifaDiaria;
    }

    public int getNuevo_dia() {
        return this.nuevo_dia;
    }

    public Double getNueva_tarifaHoraria() {
        return this.nueva_tarifaHoraria;
    }

    public int getNueva_horaInicio() {
        return this.nueva_horaInicio;
    }

    public int getNueva_horaFin() {
        return this.nueva_horaFin;
    }

    public String getClienteBaja() {return this.clienteBaja;}

    // -----------------------------SETTERS------------------------------------------------------

    public void setClienteParticular() {
        Factory factoria = new Factory();
        if(panelTarifa.isVisible()) {
            int selecDia = setdiaAplicable.getSelectedIndex();
            if (selecDia >= 0)
                selecDia += 2;
            if (selecDia > 7)
                selecDia = 1;
            Tarifa tarifa = factoria.tarifaPersonalizada(Double.parseDouble(setbasica.getText()), Double.parseDouble(setdiaria.getText()),selecDia, Double.parseDouble(sethoraria.getText()), sethoraInicio.getSelectedIndex(), sethoraFin.getSelectedIndex());
            clienteParticular = factoria.crearClienteParticularPersonalizado(setnombre.getText(), setapellidos.getText(), tarifa, setcorreo.getText(), setcodigo.getText(), new Direccion(setcp.getText(), setpoblacion.getText(), setprovincia.getText()));
        }
        else
            clienteParticular = factoria.crearClienteParticular(setnombre.getText(), setapellidos.getText(), setcorreo.getText(), setcodigo.getText(), new Direccion(setcp.getText(), setpoblacion.getText(), setprovincia.getText()));
    }

    public void setClienteEmpresa() {
        Factory factoria = new Factory();
        if(panelTarifa.isVisible()) {
            int selecDia = setdiaAplicable.getSelectedIndex();
            if (selecDia >= 0)
                selecDia += 2;
            if (selecDia > 7)
                selecDia = 1;
            Tarifa tarifa = factoria.tarifaPersonalizada(Double.parseDouble(setbasica.getText()), Double.parseDouble(setdiaria.getText()),selecDia, Double.parseDouble(sethoraria.getText()), sethoraInicio.getSelectedIndex(), sethoraFin.getSelectedIndex());
            clienteEmpresa = factoria.crearClienteEmpresaPersonalizado(setnombre.getText(), tarifa, setcorreo.getText(), setcodigo.getText(), new Direccion(setcp.getText(), setpoblacion.getText(), setprovincia.getText()));
        }
        else
            clienteEmpresa = factoria.crearClienteEmpresa(setnombre.getText(), setcorreo.getText(), setcodigo.getText(), new Direccion(setcp.getText(), setpoblacion.getText(), setprovincia.getText()));
    }

    public void setTodo(ImplementacionModelo model, ImplementacionControlador control) {
        this.controlador = control;
        this.modelo = model;
    }

    public void setModelLista(){
        listaClientes.setModel(modelo.getClientes());
        if(modelo.getClientes().isEmpty()) {
            datosCli.setEnabled(false);
            periodo.setEnabled(false);
        }
    }

    public void setModelLlamadas(){
        llamadas.setModel(modelo.getLlamadas(clienteAdd));
        llamadasPerfil.setModel(modelo.getLlamadas(clienteAdd));
    }

    public void setModelFacturas() {
        listaFacturas.setModel(modelo.getFacturas());
        facturasCliente.setModel(modelo.getFacturasCliente(clienteAdd));
    }

    public void setModelIniciar() {
        listaClientes.setModel(modelo.getClientes());
        listaFacturas.setModel(modelo.getFacturas());
        for(String cliente: modelo.totalClientes()) {
            llamadasPerfil.setModel(modelo.getLlamadas(modelo.datosCliente(cliente)));
            facturasCliente.setModel(modelo.getFacturasCliente(modelo.datosCliente(cliente)));
        }
        if(!modelo.getClientes().isEmpty()) {
            datosCli.setEnabled(true);
            periodo.setEnabled(true);
        }
        if(!modelo.getFacturas().isEmpty())
            datosFac.setEnabled(true);
    }

    // -----------------------------ACTIONLISTENERS-----------------------------------------------


    private class Fin implements ActionListener{
        private String tipo;
        public Fin(String tipo){
            this.tipo = tipo;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if(tipo.equals("P")){
                setClienteParticular();
                controlador.altaClienteParticular();
            } else {
                setClienteEmpresa();
                controlador.altaClienteEmpresa();
            }
            altacliente.setVisible(false);
            altacliente.dispose();
            ventana.setVisible(true);
            datosCli.setEnabled(true);
            periodo.setEnabled(true);
            tablaCliente.remove(0);
            tablaCliente.remove(0);
            tablaCliente.remove(0);
        }

    }

    private  class Configuracion implements ActionListener{
        public Configuracion(){ }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            configuracionUsuario(clienteAdd);
        }
    }

    /*
    private class FinFichaUsuario implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            data.setVisible(false);
            tabla.remove(0);
            tabla.remove(0);
            tabla.remove(0);
            llamadasPerfil.setModel(modeloLlamadas);
        }
    }

    */

    private class Siguiente implements ActionListener {
    private int i;
    public Siguiente(int i) {
        this.i = i;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        tablaCliente.setEnabledAt(i, true);
        tablaCliente.setSelectedIndex(i);
        if(i == 2) {
            if (setapellidos.isEnabled()) {
                setClienteParticular();
                datos = createClientePartPane(clienteParticular);
                datos.setEditable(false);
                datos.setVisible(true);
                datos.setBounds(0, 0,300,450);
                printearAlta.add(datos);
            } else {
                setClienteEmpresa();
                datos = createClienteEmpPane(clienteEmpresa);
                datos.setEditable(false);
                datos.setVisible(true);
                datos.setBounds(0, 0,300,450);
                printearAlta.add(datos);
            }
        }
    }
}

    private class Alta implements ActionListener {
        private String tipo;
        public Alta(String tipo) {
            this.tipo = tipo;
        }

    @Override
    public void actionPerformed(ActionEvent e) {
        altaCliente(tipo);
    }
}

    private class DatosCliente implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        Cliente clienteSel;
        if(resultado.getSelectedRow()!= -1)
            clienteSel = modelo.datosCliente(resultado.getValueAt(resultado.getSelectedRow(), 0).toString());
        else
            clienteSel = modelo.datosCliente(listaClientes.getSelectedValue().toString());
        resultado.clearSelection();
        if(clienteSel.isParticular()) {
            clienteParticular = (ClienteParticular) clienteSel;
            datos  = createClientePartPane(clienteParticular);
            datos.setEditable(false);
            llamadasPerfil.setModel(modelo.getLlamadas(clienteParticular));
            clienteAdd = clienteParticular;
            fichaUsuario(clienteParticular);
        } else {
            clienteEmpresa = (ClienteEmpresa) clienteSel;
            datos = createClienteEmpPane(clienteEmpresa);
            datos.setEditable(false);
            llamadasPerfil.setModel(modelo.getLlamadas(clienteEmpresa));
            clienteAdd = clienteEmpresa;
            fichaUsuario(clienteEmpresa);
        }
    }
}

    private class TarifaPersonalizada implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        panelTarifa.setVisible(true);
    }
}

    private class TarifaNormal implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        panelTarifa.setVisible(false);
    }
}

    private class addLlamadaCliente implements ActionListener {
    public addLlamadaCliente(){
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        altaLlamada();
    }
}

    private class AltaLlamada implements ActionListener {
        public AltaLlamada() {
        }

    @Override
    public void actionPerformed(ActionEvent e) {
        llamadaAdd = new Llamada(setNumLlamo.getText(), Double.parseDouble(setDuracion.getText()), new GregorianCalendar());
        llamadaAdd.setFecha(Integer.parseInt(setYear.getText()), Integer.parseInt(setMonth.getText()) - 1, Integer.parseInt(setDay.getText()), Integer.parseInt(setHour.getText()), Integer.parseInt(setMinute.getText()));
        setNumLlamo.setText("");
        setDuracion.setText("");
        setYear.setText("");
        setMonth.setText("");
        setDay.setText("");
        setHour.setText("");
        setMinute.setText("");
        controlador.altaLlamada();
    }
}

    private class NuevaTarifa implements ActionListener {
        public NuevaTarifa(){
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            Factory factoria = new Factory();
            int selecDia = setdiaAplicable.getSelectedIndex();
            if (selecDia >= 0)
                selecDia += 2;
            if (selecDia > 7)
                selecDia = 1;
            tarifaNueva = factoria.tarifaPersonalizada(Double.parseDouble(setbasica.getText()), Double.parseDouble(setdiaria.getText()),selecDia, Double.parseDouble(sethoraria.getText()), sethoraInicio.getSelectedIndex(), sethoraFin.getSelectedIndex());
            controlador.modificarTarifa();
            panelDatos.remove(datos);
            if(clienteAdd.isParticular())
                datos = createClientePartPane((ClienteParticular) clienteAdd);
            else
                datos = createClienteEmpPane((ClienteEmpresa) clienteAdd);
            datos.setPreferredSize(new Dimension(400, 300));
            datos.setEditable(false);
            panelDatos.add(datos);
            //No se actualizada de ninguna manera, por eso fuerzo el cambio de datos y con esto simulo un click del raton para que pueda actualizarse
            Robot bot = null;
            try {
                bot = new Robot();
            } catch (AWTException e) {
                e.printStackTrace();
            }
            bot.mouseMove(460, 180);
            bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
            configuracion.dispose();
        }
    }

    private class AltaFactura implements ActionListener{
        public AltaFactura() {

        }

        @Override
        public void actionPerformed(ActionEvent e) {
            altaFactura();
        }
    }

    private class NuevaFactura implements ActionListener{
        public NuevaFactura() {

        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Cliente cliente = modelo.datosCliente(clienteAdd.getCodigo());
            String codigo = setCodigoFactura.getText();
            Calendar fecha_inicio = new GregorianCalendar();
            fecha_inicio.set(Integer.parseInt(setYearInicio.getText()), Integer.parseInt(setMonthInicio.getText()) -1, Integer.parseInt(setDayInicio.getText()));
            Calendar fecha_fin = new GregorianCalendar();
            fecha_fin.set(Integer.parseInt(setYearFin.getText()), Integer.parseInt(setMonthFin.getText()) -1, Integer.parseInt(setDayFin.getText()));
            facturaAdd = Factura.emitirFactura(cliente, codigo, fecha_inicio, fecha_fin);
            controlador.altaFactura();
            datosFac.setEnabled(true);
            factura.dispose();
        }
    }

    private class DatosFactura implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            Factura facturaSel;
            if(resultado.getSelectedRow() != -1)
                facturaSel = modelo.datosFactura(resultado.getValueAt(resultado.getSelectedRow(), 0).toString());
            else
                facturaSel = modelo.datosFactura(listaFacturas.getSelectedValue().toString());
            resultado.clearSelection();
            datosFactura(facturaSel);

        }
    }

    private class NuevaTarifaGeneral implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
        cambiarTarifaGeneral();
        }
    }

    private class ModificarTarifaBasica implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            panel_diaria.setVisible(false);
            panel_horaria.setVisible(false);
            panel_basica.setVisible(true);

        }
    }
    private class ModificarTarifaDiaria implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            panel_horaria.setVisible(false);
            panel_basica.setVisible(false);
            panel_diaria.setVisible(true);

        }
    }
    private class ModificarTarifaHoraria implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            panel_basica.setVisible(false);
            panel_diaria.setVisible(false);
            panel_horaria.setVisible(true);
        }
    }

    private class NuevaTarifaBasica implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            nueva_tarifaBasica = Double.parseDouble(setbasica.getText());
            controlador.modificarTarifaBasica();
            sethoraInicio.setSelectedIndex(0);
            JOptionPane.showMessageDialog(tarifaGeneral, "La tarifa se ha cambiado correctamente.");
            setbasica.setText("");
        }
    }
    private class NuevaTarifaDiaria implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            nueva_tarifaDiaria = Double.parseDouble(setdiaria.getText());
            int selecDia = setdiaAplicable.getSelectedIndex();
            if (selecDia >= 0)
                selecDia += 2;
            if (selecDia > 7)
                selecDia = 1;
            nuevo_dia = selecDia;
            controlador.modificarTarifaDiaria();
            JOptionPane.showMessageDialog(tarifaGeneral, "La tarifa se ha cambiado correctamente.");

            setdiaria.setText("");
            setdiaAplicable.setSelectedIndex(0);
        }
    }
    private class NuevaTarifaHoraria implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            nueva_tarifaHoraria = Double.parseDouble(sethoraria.getText());
            nueva_horaInicio =  sethoraInicio.getSelectedIndex();
            nueva_horaFin = sethoraFin.getSelectedIndex();
            controlador.modificarTarifaHoraria();
            JOptionPane.showMessageDialog(tarifaGeneral, "La tarifa se ha cambiado correctamente.");
            sethoraria.setText("");
            sethoraInicio.setSelectedIndex(0);
            sethoraFin.setSelectedIndex(0);
        }
    }

    private class Baja implements ActionListener {
        public Baja() {

        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if(resultado.getSelectedRow() != -1)
                clienteBaja = resultado.getValueAt(resultado.getSelectedRow(), 0).toString();
            else
                clienteBaja = listaClientes.getSelectedValue().toString();
            resultado.clearSelection();
            controlador.baja();
            resultado.setModel(modelo.getClientesBusqueda(setbusqueda.getText()));
        }
    }

    private class Cargar implements ActionListener {
        String respuesta;
        public Cargar(String respuesta) {
            this.respuesta = respuesta;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (this.respuesta.equals("Y")) {
                modelo.load();
                cargar.dispose();
            }
            else
                cargar.dispose();
        }
    }

    private class Guardar implements ActionListener {
        String respuesta;
        public Guardar(String respuesta) {
            this.respuesta = respuesta;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (this.respuesta.equals("Y")) {
                modelo.save();
                guardar.dispose();
            }
            else
                guardar.dispose();
        }
    }

    private class BusquedaCodigo implements ActionListener {
        public BusquedaCodigo() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if(filtro.getSelectedItem().toString().equals("Cliente"))
                filtro.setSelectedIndex(0);
            else
                filtro.setSelectedIndex(1);
            if(filtro.getSelectedIndex() == 0) {
                resultado.setModel(modelo.getClientesBusqueda(setbusqueda.getText()));
            }
            if (filtro.getSelectedIndex() == 1) {
                resultado.setModel(modelo.getFacturasBusqueda(setbusqueda.getText()));
            }
        }
    }
}
