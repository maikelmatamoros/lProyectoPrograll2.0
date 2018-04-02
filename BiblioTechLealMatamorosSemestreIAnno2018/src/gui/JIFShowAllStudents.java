package gui;

import business.StudentBusiness;
import domain.Student;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import javax.swing.table.DefaultTableModel;

public class JIFShowAllStudents extends JInternalFrame implements InternalFrameListener {

    //Atributos
    private StudentBusiness studentBusiness;
    private JTable jtbTable;
    private DefaultTableModel dtmModelTable;
    private JScrollPane scrollPane;
    private ArrayList<Student> list;

    //Constructor
    public JIFShowAllStudents() {
        super("Show Students", false, true, false, false);
        this.addInternalFrameListener(this);
        this.setLayout(null);
        this.refresh();
        this.setLocation(20, 40);
        this.setSize(600, 350);
        this.addInternalFrameListener(this);
    } // constructor

    //inicializa la tabla
    public void initTable(ArrayList<Student> list) {
        Object[][] studens = new Object[0][0];
        String[] columNames1 = {"Name", "LastName", "Year", "Career", "ID"};
        this.dtmModelTable = new DefaultTableModel(studens, columNames1);
        for (int i = 0; i < list.size(); i++) {
            this.dtmModelTable.addRow(new Object[]{list.get(i).getName(),
                list.get(i).getLastName(), list.get(i).getYear(), list.get(i).getCarrera(), list.get(i).getId()});
        } // for
        this.jtbTable = new JTable(this.dtmModelTable);
        this.jtbTable.setEnabled(false);
        this.scrollPane = new JScrollPane(this.jtbTable);
        this.scrollPane.setBounds(5, 5, 580, 310);
        this.add(scrollPane);
    } // inicializa el modelo de la tabla cargando los valores del archivo

    //refresca los valores de la tabla
    public void refresh() {
        try {
            this.studentBusiness = new StudentBusiness();
            this.list = this.studentBusiness.getAllStudents();

        } catch (IOException ex) {
            Logger.getLogger(JIFShowAllStudents.class.getName()).log(Level.SEVERE, null, ex);
        }
        initTable(this.list);
    } // refresh

    //Evento internalFrame para que cuando se cierre habilite el jmItem con el que se abre la ventana
    @Override
    public void internalFrameOpened(InternalFrameEvent e) {
    }

    @Override
    public void internalFrameClosing(InternalFrameEvent e) {
        MainWindows.jmiShowStudents.setEnabled(true);
    }

    @Override
    public void internalFrameClosed(InternalFrameEvent e) {
    }

    @Override
    public void internalFrameIconified(InternalFrameEvent e) {
    }

    @Override
    public void internalFrameDeiconified(InternalFrameEvent e) {
    }

    @Override
    public void internalFrameActivated(InternalFrameEvent e) {
    }

    @Override
    public void internalFrameDeactivated(InternalFrameEvent e) {
    }

} // fin de la clase
