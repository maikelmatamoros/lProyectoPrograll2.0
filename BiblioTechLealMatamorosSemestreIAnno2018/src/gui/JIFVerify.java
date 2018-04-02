package gui;

import business.StudentBusiness;
import domain.Student;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class JIFVerify extends JInternalFrame implements ActionListener, MouseListener, KeyListener, InternalFrameListener {

    private boolean aux = false;
    private StudentBusiness studentBusiness;
    private JTable jtbTable1;
    private DefaultTableModel dtmModelTable;
    private JScrollPane scrollPane;
    private JLabel jlblID;
    private JButton jbtnLogin;
    private JTextField jTextField;
    private JComboBox jComboMaterial;
    public static String ID;
    private TableRowSorter trs;
    private String path;

    public JIFVerify() {
        super("Loan Material", false, true, false, false);
        this.setLayout(null);
        try {
            this.studentBusiness = new StudentBusiness();

        } catch (IOException ex) {
            Logger.getLogger(JIFStudentDelete.class.getName()).log(Level.SEVERE, null, ex);
        }
        initLogin();
        //this.refresh();

        this.setLocation(20, 40);
        this.addInternalFrameListener(this);
    }//constructor

    public void initLogin() {
        this.setSize(300, 200);
        this.jbtnLogin = new JButton("Login");
        this.jTextField = new JTextField();
        this.jlblID = new JLabel("ID");

        this.jbtnLogin.setBounds(90, 90, 90, 30);
        this.jTextField.setBounds(150, 20, 90, 30);
        this.jlblID.setBounds(80, 20, 50, 30);
        this.jbtnLogin.addActionListener(this);

        this.add(this.jbtnLogin);
        this.add(this.jlblID);
        this.add(this.jTextField);

    } // inicializa el boton

    public void initTable(ArrayList<Student> list, String subString) {

        Object[][] vehicles = new Object[0][0];
        String[] columNames1 = {"Name", "LastName", "Year", "Career", "ID"};
        this.dtmModelTable = new DefaultTableModel(vehicles, columNames1);
        for (int i = 0; i < list.size(); i++) {

            this.dtmModelTable.addRow(new Object[]{list.get(i).getName(),
                list.get(i).getLastName(), list.get(i).getYear(), list.get(i).getCarrera(), list.get(i).getId()});

        }//for

        this.jtbTable1 = new JTable(this.dtmModelTable);
        trs = new TableRowSorter(dtmModelTable);
        jtbTable1.setRowSorter(trs);
        trs.setRowFilter(RowFilter.regexFilter(subString, 0));
        this.scrollPane = new JScrollPane(this.jtbTable1);
        scrollPane.setBounds(5, 5, 300, 285);
        this.jtbTable1.setSelectionBackground(Color.GREEN);
        this.add(scrollPane);
        this.jtbTable1.addMouseListener(this);
    } // inicializa el modelo de la tabla cargando los valores del archivo

    private int change = 0;

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (this.studentBusiness.validLogin(this.jTextField.getText())) {
                JOptionPane.showMessageDialog(rootPane, "Succes");
                this.dispose();
                JIFLoan jIFLoan = new JIFLoan(this.jTextField.getText());
                jIFLoan.setVisible(true);
                MainWindows.jDesktopPane.add(jIFLoan);
                //refresh(this.change);

            } else {
                JOptionPane.showMessageDialog(rootPane, "Your ID no registered");
            }
        } catch (IOException ex) {
            Logger.getLogger(JIFVerify.class.getName()).log(Level.SEVERE, null, ex);
        }
    } // actionPerformed

    //Eventos para el mouse, se usa para saber cual es el vehiculo que se selecciona en la tabla
    @Override
    public void mouseClicked(MouseEvent e) {
       // this.position = this.jtbTable1.getSelectedRow();
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    //metodo para refrescar la Tabla
    public void refresh(int aux) {
        if (aux == 0) {

            //System.out.println(this.getContentPane().getComponentCount());
            //this.getContentPane().remove(0);
            this.getContentPane().removeAll();
            this.getContentPane().repaint();
            this.jComboMaterial = new JComboBox(new String[]{"Libros", "Audiovisuales"});
            this.add(this.jComboMaterial);
        }

    } // refresh

    //evento de internalFrame, se usa para volver a hacer usable el JMitem que abre la ventana
    private String name = "";

    @Override
    public void keyTyped(KeyEvent e) {
//        int keycode = e.getKeyChar();
//
//        if (keycode != 8) {
//            name = name + (char) keycode;
//            refresh(1);
//        } else {
//            if (name.length() >= 1) {
//                name = name.substring(0, name.length() - 1);
//                refresh(1);
//            }
//        }

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void internalFrameOpened(InternalFrameEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void internalFrameClosing(InternalFrameEvent e) {
        System.out.println("Colsing");
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void internalFrameClosed(InternalFrameEvent e) {
        System.out.println("Colsed");
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void internalFrameIconified(InternalFrameEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void internalFrameDeiconified(InternalFrameEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void internalFrameActivated(InternalFrameEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void internalFrameDeactivated(InternalFrameEvent e) {
    

    }

} // fin de la clase
