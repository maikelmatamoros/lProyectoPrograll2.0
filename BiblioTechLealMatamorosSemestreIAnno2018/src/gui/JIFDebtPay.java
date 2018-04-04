package gui;

import business.StudentBusiness;
import domain.CustomPanel;
import domain.Student;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

public class JIFDebtPay extends JInternalFrame implements ActionListener,InternalFrameListener{

    private JButton jbtnPay;
    private JLabel jlblID;
    private JTextField jtfID;
    private StudentBusiness studentBusiness;

    public JIFDebtPay() {
        super("Devolution", false, true, false, false);
        this.setLocation(20, 40);
        this.setContentPane(new CustomPanel(1, 0, 0, 710, 410));
        this.setLayout(null);
        try {
            this.studentBusiness = new StudentBusiness();
        } catch (IOException ex) {
            Logger.getLogger(JIFDebtPay.class.getName()).log(Level.SEVERE, null, ex);
        }

        init();
        this.addInternalFrameListener(this);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    } // constructor

    public void init() {
        this.setSize(240, 170);
        this.jbtnPay = new JButton("Pay");
        this.jtfID = new JTextField();
        this.jlblID = new JLabel("ID");

        this.jbtnPay.setBounds(80, 70, 70, 30);
        this.jlblID.setBounds(35, 20, 50, 30);
        this.jtfID.setBounds(70, 20, 120, 25);
        
        this.jbtnPay.setFont(new java.awt.Font("DejaVu Sans Condensed", 3, 15));
        this.jbtnPay.setForeground(new java.awt.Color(19, 135, 196));
        this.jbtnPay.setBorderPainted(false);
        this.jbtnPay.setFocusable(false);
        
        this.jbtnPay.addActionListener(this);
        
        this.add(this.jlblID);
        this.add(this.jtfID);
        this.add(this.jbtnPay);
    } // init

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!this.jtfID.getText().equals("")) {
            try {
                if (this.studentBusiness.validLogin(this.jtfID.getText())) {
                    int pos=this.studentBusiness.getPosition(this.jtfID.getText());
                    Student student=this.studentBusiness.getStudent(pos);
                    student.clearPenalty();
                    this.studentBusiness.updateRecord(student, pos);
                    JOptionPane.showMessageDialog(rootPane, "Success");
                }else{
                    JOptionPane.showMessageDialog(rootPane, "The ID not is registered");
                }
            } catch (IOException ex) {
                Logger.getLogger(JIFDebtPay.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            JOptionPane.showMessageDialog(rootPane, "The space for ID is requerided");
        }
    } // actionPerformed

    @Override
    public void internalFrameOpened(InternalFrameEvent e) {     
    }

    @Override
    public void internalFrameClosing(InternalFrameEvent e) {
        MainWindows.jmiDebtsPay.setEnabled(true);
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
    
} // fin de la clases
