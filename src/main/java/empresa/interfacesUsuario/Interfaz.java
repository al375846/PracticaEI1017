package empresa.interfacesUsuario;


import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Interfaz {
    JFrame ventana = new JFrame("Holis");
    JTabbedPane tabbedPane = new JTabbedPane();
   public void crearVentana() {
       /*Container contentPane = ventana.getContentPane();
       SpringLayout layout = new SpringLayout();
       contentPane.setLayout(layout);
       contentPane.add(new JLabel("Label: "));
       contentPane.add(new JTextField("Text field", 15));
       ventana.pack();
       ventana.setVisible(true);*/
       //JFrame ventana = new JFrame("Holis");
       ventana.add(tabbedPane);
        JPanel panel1 = new JPanel();
       JButton agregar = new JButton("+");
       JButton borrar = new JButton("-");
       panel1.setLayout(null);
       agregar.setBounds(10, 10,50,50);
       borrar.setBounds(80,10,50,50);
       panel1.add(agregar);
       panel1.add(borrar);
       String[] clientes = new String[30];
       for(int i=0;i<15;i=i+2) {
           clientes[i] = "Cosa" + i;
           clientes[i + 1] = "Hola" + i;
       }
       JList listaPanel1 = new JList(clientes);
       listaPanel1.setLayout(null);
       listaPanel1.setLayoutOrientation(JList.VERTICAL);
       JScrollPane scroller = new JScrollPane(listaPanel1);
       scroller.setBounds(10,80,150,200);
       panel1.add(scroller);
       JComboBox listas = new JComboBox(clientes);
       listas.setBounds(200, 80, 100,20);
       //listas.setMaximumRowCount(8);
       panel1.add(listas);
       //panel1.setSize(400, 400);
       //panel1.setVisible(true);
       tabbedPane.addTab("Pestaña 1", null, panel1, "No hace nada");
       tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
       //tabbedPane.setEnabledAt(0, true);
       JComponent label3 = new JLabel("Text-Only Label");
       tabbedPane.addTab("Pestaña 2", label3);
       tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
       //tabbedPane.setEnabledAt(1, true);
       //tabbedPane.setVisible(true);
       tabbedPane.setForegroundAt(0, Color.red);
       ventana.setSize(600,600);
       ventana.setResizable(false);
       ventana.setVisible(true);



    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Interfaz().crearVentana();
            }
        });
    }
/*
    public static void main(String[] args) {

        JFrame ventana = new JFrame("Holis");
        ventana.setSize(400,400);

        Color c=Color.pink;
        ventana.getContentPane().setBackground(c);

        ventana.setVisible(true);

    }
*/
}
