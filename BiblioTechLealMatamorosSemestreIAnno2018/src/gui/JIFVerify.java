package gui;

import business.StudentBusiness;
import domain.CustomPanel;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

public class JIFVerify extends JInternalFrame implements ActionListener, InternalFrameListener {

    private StudentBusiness studentBusiness;
    private JLabel jlblID;
    private JButton jbtnVerify;
    private JTextField jTextField;
    public static String ID;

    public JIFVerify() {
        super("Loan Material", false, true, false, false);
        this.setContentPane(new CustomPanel(1, 0, 0, 240, 170));
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
        this.setSize(230, 160);
        this.jlblID = new JLabel("ID");
        this.jTextField = new JTextField();
        this.jbtnVerify = new JButton();

        this.jlblID.setBackground(Color.BLACK);
        this.jlblID.setBounds(35, 20, 50, 30);
        this.jTextField.setBounds(70, 20, 100, 25);
        this.jbtnVerify.setBounds(65, 70, 90, 30);

        ImageIcon image = new ImageIcon("src/assets/jbOk.png");
        Icon icon = new ImageIcon(image.getImage().getScaledInstance(this.jbtnVerify.getWidth(), this.jbtnVerify.getHeight(), Image.SCALE_DEFAULT));
        this.jbtnVerify.setIcon(icon);
        this.jbtnVerify.setContentAreaFilled(false);
        this.jbtnVerify.setBorderPainted(false);

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
                int amount = this.studentBusiness.getStudent(this.studentBusiness.getPosition(this.jTextField.getText())).getPenalty();
                if (amount == 0) {
                    JOptionPane.showMessageDialog(this.rootPane, "Succes");
                    this.dispose();
                    JIFLoan jIFLoan = new JIFLoan(this.jTextField.getText());

                    MainWindows.jDesktopPane.add(jIFLoan);
                    jIFLoan.setVisible(true);
                    MainWindows.flag = 2;
                } else {
                    JOptionPane.showMessageDialog(rootPane, "You can not access a loan because you are delinquent at this time, pay and try again");
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Your ID no registered");
            }
        } // actionPerformed // actionPerformed
        catch (IOException ex) {
            Logger.getLogger(JIFVerify.class.getName()).log(Level.SEVERE, null, ex);
        }

    } // actionPerformed

    //evento de internalFrame, se usa para volver a hacer usable el JMitem que abre la ventana
    @Override
    public void internalFrameOpened(InternalFrameEvent e) {
    }

    @Override
    public void internalFrameClosing(InternalFrameEvent e) {
    }

    @Override
    public void internalFrameClosed(InternalFrameEvent e) {
        MainWindows.flag = 1;
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
