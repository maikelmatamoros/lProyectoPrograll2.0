package gui;

import business.StudentBusiness;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
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


public class JIFVerify extends JInternalFrame implements ActionListener, InternalFrameListener {

    private StudentBusiness studentBusiness;
    private JLabel jlblID;
    public static JButton jbtnVerify;
    private JTextField jTextField;
    public static String ID;

    public JIFVerify() {
        super("Loan Material", false, true, false, false);
        this.setLayout(null);
        try {
            this.studentBusiness = new StudentBusiness();

        } catch (IOException ex) {
            Logger.getLogger(JIFStudentDelete.class.getName()).log(Level.SEVERE, null, ex);
        }
        init();

        this.setLocation(20, 40);
        this.addInternalFrameListener(this);
    }//constructor

    public void init() {
        this.setSize(300, 200);
        jbtnVerify = new JButton("Login");
        this.jTextField = new JTextField();
        this.jlblID = new JLabel("ID");

        jbtnVerify.setBounds(90, 90, 90, 30);
        this.jTextField.setBounds(150, 20, 90, 30);
        this.jlblID.setBounds(80, 20, 50, 30);
        this.jbtnVerify.addActionListener(this);

        add(this.jbtnVerify);
        this.add(this.jlblID);
        this.add(this.jTextField);

    } // inicializa el boton

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            Boolean idExist = this.studentBusiness.validLogin(this.jTextField.getText());

            if (idExist) {
                int amount = studentBusiness.getStudent(studentBusiness.getPosition(this.jTextField.getText())).getPenalty();
                if (amount == 0) {
                    JOptionPane.showMessageDialog(rootPane, "Succes");
                    this.dispose();
                    JIFLoan jIFLoan = new JIFLoan(this.jTextField.getText());
                    
                    MainWindows.jDesktopPane.add(jIFLoan);
                    jIFLoan.setVisible(true);
                    MainWindows.flag=2;
                }else{
                        JOptionPane.showMessageDialog(rootPane, "You can not access a loan because you are delinquent at this time, pay and try again");
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Your ID no registered");
            }
        } // actionPerformed // actionPerformed
        catch (IOException ex) {
            Logger.getLogger(JIFVerify.class.getName()).log(Level.SEVERE, null, ex);
        }

    }


    //evento de internalFrame, se usa para volver a hacer usable el JMitem que abre la ventana

    @Override
    public void internalFrameOpened(InternalFrameEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void internalFrameClosing(InternalFrameEvent e) {
       
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void internalFrameClosed(InternalFrameEvent e) {
       MainWindows.flag=1;
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
