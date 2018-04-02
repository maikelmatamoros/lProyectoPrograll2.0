package gui;

import business.StudentBusiness;
import domain.Student;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

public class JIFNewStudent extends JInternalFrame implements ActionListener, InternalFrameListener{

    private JComboBox jComboBox;
    private JTextField jtfName, jtfLast, jtfYear;
    private JLabel jlblName, jlblLast, jlblYear, jlblCareer;
    private JButton jbtnSave;
    private StudentBusiness studentBusiness;

    public JIFNewStudent() {
        super("Stundent Register", false, true, false, false);
        this.addInternalFrameListener(this);
        this.setSize(465, 280);
        this.setLocation(20, 40);
        this.setLayout(null);
        try {
            this.studentBusiness = new StudentBusiness();
        } catch (IOException ex) {
            Logger.getLogger(JIFNewStudent.class.getName()).log(Level.SEVERE, null, ex);
        }
        init();
    } // constructor

    public void init() {
        this.jComboBox = new JComboBox();
        this.jComboBox.addItem("Agronomy");
        this.jComboBox.addItem("Education");
        this.jComboBox.addItem("Computing");

        this.jtfName = new JTextField();
        this.jlblName = new JLabel("Name");
        this.jtfLast = new JTextField();
        this.jlblLast = new JLabel("Lastname");
        this.jtfYear = new JTextField();
        this.jlblYear = new JLabel("Year");
        this.jlblCareer = new JLabel("Career");
        this.jbtnSave = new JButton("Save");

        this.jComboBox.setBounds(300, 80, 110, 30);
        this.jtfName.setBounds(120, 40, 110, 30);
        this.jlblName.setBounds(40, 40, 100, 30);
        this.jtfLast.setBounds(120, 80, 110, 30);
        this.jlblLast.setBounds(40, 80, 100, 30);
        this.jtfYear.setBounds(120, 120, 110, 30);
        this.jlblYear.setBounds(40, 120, 100, 30);
        this.jlblCareer.setBounds(240, 80, 100, 30);
        this.jbtnSave.setBounds(120, 170, 100, 30);

        this.jbtnSave.setFocusable(false);
        this.jbtnSave.addActionListener(this);

        this.add(this.jComboBox);
        this.add(this.jtfName);
        this.add(this.jlblName);
        this.add(this.jtfLast);
        this.add(this.jlblLast);
        this.add(this.jtfYear);
        this.add(this.jlblYear);
        this.add(this.jlblCareer);
        this.add(this.jbtnSave);
    } // init

    @Override
    public void actionPerformed(ActionEvent e) {
        if (validation()) {
            try {
                String first = String.valueOf(this.jComboBox.getSelectedItem().toString().charAt(0));
                String second = String.valueOf(this.jtfYear.getText().charAt(this.jtfYear.getText().length() - 1));
                String third;
                int quantity = this.studentBusiness.getQuantCareer(this.jComboBox.getSelectedItem().toString());
                if (quantity < 10) {
                    third = "00" + quantity;
                } else if (quantity < 100) {
                    third = "0" + quantity;
                } else {
                    third = String.valueOf(quantity);
                }
                String id = first + second + third;
                if (this.studentBusiness.addEndRecord(new Student(id, this.jtfName.getText(),
                        this.jtfLast.getText(), this.jComboBox.getSelectedItem().toString(),
                        Integer.parseInt(this.jtfYear.getText())))) {
                    this.jtfLast.setText("");
                    this.jtfName.setText("");
                    this.jtfYear.setText("");
                    JOptionPane.showMessageDialog(rootPane, "Success, your institucional ID is: " + id);
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Error");
                }
            } catch (IOException ex) {
                Logger.getLogger(JIFNewStudent.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    } // actionPerformed

    private boolean validation() {
        if (this.jtfName.getText().equals("") || this.jtfLast.getText().equals("") || this.jtfYear.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "All spaces are required", "Error", 0);
            return false;
        } else {
            try {
                if (Integer.parseInt(this.jtfYear.getText()) < 1941 || Integer.parseInt(this.jtfYear.getText()) > 2018) {
                    String message = "Invalid data";
                    if (Integer.parseInt(this.jtfYear.getText()) < 1941) {
                        message = message + ", the first student entered in 1941";
                    } else if (Integer.parseInt(this.jtfYear.getText()) > 2018) {
                        message = message + ", the year can not be greater than the current";
                    }
                    JOptionPane.showMessageDialog(this, message, "Error", 2);
                    return false;
                }
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(this, "The year must be a number.", "Error", 0);
                return false;
            }
        } // else
        return true;
    } // validation

    @Override
    public void internalFrameOpened(InternalFrameEvent e) {}

    @Override
    public void internalFrameClosing(InternalFrameEvent e) {
        MainWindows.jmiNewStudent.setEnabled(true);
    }

    @Override
    public void internalFrameClosed(InternalFrameEvent e) {}

    @Override
    public void internalFrameIconified(InternalFrameEvent e) {}

    @Override
    public void internalFrameDeiconified(InternalFrameEvent e) {}

    @Override
    public void internalFrameActivated(InternalFrameEvent e) {}

    @Override
    public void internalFrameDeactivated(InternalFrameEvent e) {}

} // fin de la clase
