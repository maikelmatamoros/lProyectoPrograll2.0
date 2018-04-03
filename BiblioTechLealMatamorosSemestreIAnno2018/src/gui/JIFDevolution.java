package gui;

import business.LoanBusiness;
import business.MaterialBusiness;
import business.StudentBusiness;
import domain.Loan;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import javax.swing.table.DefaultTableModel;

public class JIFDevolution extends JInternalFrame implements InternalFrameListener, MouseListener, ActionListener {

    private JTable jtbTable;
    private DefaultTableModel dtmModelTable;
    private JScrollPane scrollPane;
    private JLabel jlStudentID;
    private JTextField jtfStudentID;
    private JButton jbCheck;
    private int position;
    private StudentBusiness studentBusiness;
    private MaterialBusiness materialBusiness;
    private LoanBusiness loanBusiness;
    private String ID;
    private JButton jbtnDevolution;
    private List<Loan> list;

    public JIFDevolution() {
        super("Devolution", false, true, false, false);
        this.setLocation(20, 40);
        this.setLayout(null);
        try {
            this.studentBusiness = new StudentBusiness();
        } catch (IOException ex) {
            Logger.getLogger(JIFDevolution.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.materialBusiness=new MaterialBusiness();
        init();
        this.addInternalFrameListener(this);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    } // constructor

    private void init() {
        this.setSize(250, 160);

        this.loanBusiness = new LoanBusiness();

        this.jlStudentID = new JLabel("Student ID");
        this.jtfStudentID = new JTextField();
        this.jbCheck = new JButton("Check");
        
        this.jbCheck.addActionListener(this);
        this.jbtnDevolution = new JButton("Devolution");

        this.jlStudentID.setBounds(30, 20, 120, 15);
        this.jtfStudentID.setBounds(30, 40, 170, 25);
        this.jbCheck.setBounds(30, 70, 90, 30);
        this.jbtnDevolution.setBounds(300, 300, 100, 30);

        this.jbtnDevolution.setVisible(false);
        this.jbtnDevolution.addActionListener(this);

        this.add(this.jlStudentID);
        this.add(this.jtfStudentID);
        this.add(this.jbCheck);
        this.add(this.jbtnDevolution);
    } // init

    private void initTable() {
        try {
            this.setSize(700, 500);
            this.list = loanBusiness.getPersonLoans(this.ID);
            Object[][] objects = new Object[0][0];
            String[] columNames = {"Code", "Type", "Loan date", "Return date"};
            this.dtmModelTable = new DefaultTableModel(objects, columNames);

            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getID().equalsIgnoreCase(this.ID)) {
                    this.dtmModelTable.addRow(new Object[]{list.get(i).getCode(), list.get(i).getType(),
                        list.get(i).getLoanDate(), list.get(i).getReturnDate()});
                }
            } // for

            this.jtbTable = new JTable(this.dtmModelTable);
            this.scrollPane = new JScrollPane(this.jtbTable);
            this.scrollPane.setBounds(5, 5, 680, 285);
            this.jtbTable.setSelectionBackground(Color.GREEN);
            this.add(this.scrollPane);
            this.jtbTable.addMouseListener(this);
        } catch (IOException ex) {
            Logger.getLogger(JIFDevolution.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JIFDevolution.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void refresh() {
        this.remove(this.jlStudentID);
        this.remove(this.jtfStudentID);
        this.remove(this.jbCheck);
        this.repaint();

        //ARREGLAR
//        try {
//            this.materialBusiness = new MaterialBusiness();
//            this.list = (ArrayList<Material>) this.materialBusiness.getAllMaterials();
//        } catch (IOException ex) {
//            Logger.getLogger(JIFStudentDelete.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(JIFDevolution.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        initTable(this.list);
    } // refresh

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.jbCheck) {
            try {
                if (this.studentBusiness.validLogin(this.jtfStudentID.getText())) {
                    this.ID = this.jtfStudentID.getText();
                    JOptionPane.showMessageDialog(rootPane, "Succes");
                    refresh();
                    initTable();
                    this.jtbTable.addMouseListener(this);
                    this.jbtnDevolution.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Your ID no registered");
                }
            } catch (IOException ex) {
                Logger.getLogger(JIFDevolution.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (e.getSource() == this.jbtnDevolution) {
            try {
                if (this.list.get(this.position).getType().equalsIgnoreCase("Book")) {
                    this.materialBusiness.update(this.list.get(this.position).getCode(), 0, 1);
                } else {
                    this.materialBusiness.update(this.list.get(this.position).getCode(), 1, 1);
                }
                this.loanBusiness.rewrite(this.list.get(this.position).getCode());
            } catch (IOException | ClassNotFoundException ex) {
                Logger.getLogger(JIFDevolution.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.remove(this.scrollPane);
            initTable();
        }

    }// actionPerformed

    @Override
    public void internalFrameOpened(InternalFrameEvent e) {
    }

    @Override
    public void internalFrameClosing(InternalFrameEvent e) {
        MainWindows.jmiDevolution.setEnabled(true);
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

    @Override
    public void mouseClicked(MouseEvent e) {
        this.position = this.jtbTable.getSelectedRow();
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

} // fin de la clase
