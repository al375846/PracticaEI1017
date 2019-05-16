package empresa.interfacesUsuario;


import com.sun.deploy.panel.JreTableModel;
import empresa.clientes.Cliente;
import empresa.clientes.ClienteEmpresa;
import empresa.clientes.ClienteParticular;
import empresa.clientes.Direccion;
import empresa.factoria.Factory;
import empresa.llamadas.Llamada;
import empresa.operaciones.Controlador;
import empresa.operaciones.ImplementacionControlador;
import empresa.operaciones.ImplementacionModelo;
import empresa.operaciones.Modelo;
import empresa.tarifas.Tarifa;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static javax.swing.SwingConstants.CENTER;

public class Interfaz extends JFrame implements Vista{
    private Controlador controlador;
    private Modelo modelo;
    private JFrame ventana = new JFrame("Principal");
    private JFrame data;
    private JTabbedPane tabbedPane = new JTabbedPane();
    private JTabbedPane tabla = new JTabbedPane();
    private JFrame altacliente;
    private JFrame altaLlamada;
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
    String[] columnasLlamadas = {"Número", "Duración", "Fecha", "Hora"};
    DefaultTableModel modeloLlamadas = new DefaultTableModel(columnasLlamadas, 1);

    public Interfaz() {}

    public void principal() {
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
       baja.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_K, ActionEvent.CTRL_MASK));
       menu.add(baja);
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

       JList listaPanel2 = new JList(modeloClientes);
       listaPanel2.setLayout(null);
       listaPanel2.setLayoutOrientation(JList.VERTICAL);
       JScrollPane scroller2 = new JScrollPane(listaPanel2);
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
       JComboBox filtro = new JComboBox(tipos);
       filtro.setBounds(70, 300, 100, 20);
       panel1.add(filtro);
       JTextField codigo = new JTextField("Introduzca el código", 50);
       codigo.setBounds(180, 300, 150, 20);
       codigo.setHorizontalAlignment(JTextField.TRAILING);
       panel1.add(codigo);
       JButton buscar = new JButton("Buscar");
       buscar.setBounds(340, 300, 100, 20);
       panel1.add(buscar);
       String[] columnNames = {"Codigo"};
       Object[][] data = {{"20"}, {"30"}, {"40"}, {"50"}, {"60"}, {"70"}, {"80"}, {"90"}, {"100"}};
       JTable resultado = new JTable(data, columnNames);

      for (int c = 0; c < resultado.getColumnCount(); c++) {
           Class<?> col_class = resultado.getColumnClass(c);
           resultado.setDefaultEditor(col_class, null);        // remove editor
       }
       JScrollPane scrollPane = new JScrollPane(resultado);
       scrollPane.setBounds(10, 350, 200, 150);
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

       //setear modelos
        llamadas.setModel(modeloLlamadas);
        llamadasPerfil.setModel(modeloLlamadas);
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
        ajustes.setBounds(720, 400, 40, 40);
        panelUsuario.add(ajustes);
        ImageIcon llamada = new ImageIcon("src/media/telefono.png");
        ImageIcon im_llamada = new ImageIcon(llamada.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
        JButton alta_llamada = new JButton(im_llamada);
        alta_llamada.addActionListener(new addLlamadaCliente(cliente));
        alta_llamada.setBounds(50, 350, 50, 50);
        panel_1.add(alta_llamada);
        ImageIcon factura = new ImageIcon("src/media/facturas.png");
        ImageIcon im_factura = new ImageIcon(factura.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
        JButton emitir_factura = new JButton(im_factura);
        emitir_factura.setBounds(150, 350, 50, 50);
        panel_1.add(emitir_factura);
        ImageIcon buscar = new ImageIcon("src/media/buscar.png");
        ImageIcon im_buscar = new ImageIcon(buscar.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
        JButton buscar_Periodo = new JButton(im_buscar);
        buscar_Periodo.setBounds(250, 350, 50, 50);
        panel_1.add(buscar_Periodo);

        //Visor de datos
        JPanel panel_2 = new JPanel();
        datos.setEditable(false);
        datos.setPreferredSize(new Dimension(300, 300));
        panel_2.add(datos);
        tabla.addTab("Datos", null, panel_2, "Datos del cliente");

        //Llamadas
        JPanel panel_3 = new JPanel();
        llamadasPerfil.setPreferredSize(new Dimension(300, 300));
        //setModelLlamadas();
        panel_3.add(llamadasPerfil);
        tabla.addTab("Llamadas", null, panel_3);

        JPanel panel_4 = new JPanel();
        tabla.addTab("Facturas", null, panel_4);
        JButton salir = new JButton("Salir");
        salir.addActionListener(new FinFichaUsuario());
        salir.setBounds(560, 400, 100, 50);
        panelUsuario.add(salir);

        panel_1.setBounds(0, 0, 400, 500);
        tabla.setBounds(400, 50, 300, 300);
        panelUsuario.add(panel_1);
        //panel_0.add(label2);
        panelUsuario.add(tabla);
        tabla.setVisible(true);
        data.add(panelUsuario);
        data.setSize(800, 500);
        data.setResizable(false);
        data.setVisible(true);
        data.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                tabla.remove(0);
                tabla.remove(0);
                tabla.remove(0);
            }
        });
        //data.addWindowListener(new CerrarFichaUsuario());
    }

    private JTextPane createClientePartPane(ClienteParticular clienteParticular) {
        String fecha = (clienteParticular.getFecha().get(Calendar.DAY_OF_MONTH) + "/" + (clienteParticular.getFecha().get(Calendar.MONTH) + 1)+ "/" + clienteParticular.getFecha().get(Calendar.YEAR));
        String[] initString =
                { "Nombre: ", clienteParticular.getNombre() + "\n",
                        "Apellidos: ",  clienteParticular.getApellidos() + "\n",
                        "DNI: ", clienteParticular.getCodigo() + "\n",
                        "Correo: ",  clienteParticular.getCorreo() + "\n",
                        "Dirección: " + "\n",
                        "\t" + "Cp: ", clienteParticular.getDireccion().getCp() + "\n",
                        "\t" + "Población: ", clienteParticular.getDireccion().getPoblacion() + "\n",
                        "\t" + "Provincia: ", clienteParticular.getDireccion().getProvincia() + "\n",
                        "Fecha alta: ", fecha + "\n",
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
                { "Empresa: ", clienteEmpresa.getNombre() + "\n",
                        "Nif: ", clienteEmpresa.getCodigo() + "\n",
                        "Correo: ",  clienteEmpresa.getCorreo() + "\n",
                        "Dirección: " + "\n",
                        "\t" + "Cp: ", clienteEmpresa.getDireccion().getCp() + "\n",
                        "\t" + "Población: ", clienteEmpresa.getDireccion().getPoblacion() + "\n",
                        "\t" + "Provincia: ", clienteEmpresa.getDireccion().getProvincia() + "\n",
                        "Fecha alta: ", fecha + "\n",
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
        JButton add = new JButton("Añadir");
        add.addActionListener(new AltaLlamada());
        add.setBounds(560, 130, 20, 20);
        panel_llamada.add(add);
        llamadas.setPreferredSize(new Dimension(550, 250));
        llamadas.setBounds(10, 210, 550, 250);
        panel_llamada.add(llamadas);
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

    protected void addStylesToDocument(StyledDocument doc) {
        //Initialize some styles.
        Style def = StyleContext.getDefaultStyleContext().
                getStyle(StyleContext.DEFAULT_STYLE);

        Style regular = doc.addStyle("regular", def);
        StyleConstants.setFontFamily(def, "SansSerif");

        Style s = doc.addStyle("bold", regular);
        StyleConstants.setBold(s, true);

        s = doc.addStyle("italic", regular);
        StyleConstants.setItalic(s, true);
    }

    public void setTodo(ImplementacionModelo model, ImplementacionControlador control) {
        this.controlador = control;
        this.modelo = model;
    }
    public ClienteParticular getClientePart() {
        return this.clienteParticular;
    }
    public ClienteEmpresa getClienteEmp() {
        return this.clienteEmpresa;
    }

    public void setModelLista(){
        listaClientes.setModel(modelo.getClientes());
    }

    public Cliente getClienteAdd() {
        return this.clienteAdd;
    }

    public Llamada getLlamadaAdd() {
        return this.llamadaAdd;
    }

    public void setModelLlamadas(){
        llamadas.setModel(modelo.getLlamadas(clienteAdd));
        llamadasPerfil.setModel(modelo.getLlamadas(clienteAdd));
    }

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
                //datos.setPreferredSize(new Dimension(500, 400));
                datos.setBounds(0, 0,300,450);
                printearAlta.add(datos);
            } else {
                setClienteEmpresa();
                datos = createClienteEmpPane(clienteEmpresa);
                datos.setEditable(false);
                datos.setVisible(true);
                //datos.setPreferredSize(new Dimension(500, 400));
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
        Cliente clienteSel = modelo.datosCliente(listaClientes.getSelectedValue().toString());
        if(clienteSel.isParticular()) {
            clienteParticular = (ClienteParticular) clienteSel;
            datos  = createClientePartPane(clienteParticular);
            datos.setEditable(false);
            llamadasPerfil.setModel(modelo.getLlamadas(clienteParticular));
            fichaUsuario(clienteParticular);
        } else {
            clienteEmpresa = (ClienteEmpresa) clienteSel;
            datos = createClienteEmpPane(clienteEmpresa);
            datos.setEditable(false);
            llamadasPerfil.setModel(modelo.getLlamadas(clienteEmpresa));
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
    private Cliente cliente;
    public addLlamadaCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        clienteAdd = cliente;
        altaLlamada();
    }
}

    private class AltaLlamada implements ActionListener {
        public AltaLlamada() {
        }

    @Override
    public void actionPerformed(ActionEvent e) {
        llamadaAdd = new Llamada(setNumLlamo.getText(), Double.parseDouble(setDuracion.getText()), new GregorianCalendar());
        llamadaAdd.setFecha(Integer.parseInt(setYear.getText()), Integer.parseInt(setMonth.getText()), Integer.parseInt(setDay.getText()), Integer.parseInt(setHour.getText()), Integer.parseInt(setMinute.getText()));
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
}
