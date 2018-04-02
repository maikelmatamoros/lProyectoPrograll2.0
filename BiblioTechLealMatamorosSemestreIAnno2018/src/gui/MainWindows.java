package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MainWindows extends JFrame implements ActionListener {

    private JMenuBar jMenuBar;
    private JMenu jmStudent, jmLoans, jmMaterial;
    private JInternalFrame jifNewStudent, jifShowAllStudents, jifStudentUpdate, jifStudentDelete,
            jifLoanApplication, jifNewMaterial, jifAddBookExisting, jifDevolution, jifAllMaterials;
    public static JDesktopPane jDesktopPane;
    public static JMenuItem jmiNewMaterial, jmiAddBookExisting, jmiNewStudent,
            jmiDeleteStudent, jmiUpdateStudent, jmiShowStudents, jmiLoanApplication,
            jmiDevolution, jmiAllMaterials;

    public MainWindows() {
        super("BiblioTech");
        this.setResizable(false);
        jDesktopPane=new JDesktopPane();
        jDesktopPane.setLayout(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(800, 600);
        init();
        this.add(jDesktopPane);
        this.setVisible(true);
    } // Constructor

    public void init() {
        this.jMenuBar = new JMenuBar();

        this.jmStudent = new JMenu("Student");
        this.jmLoans = new JMenu("Loans");
        this.jmMaterial = new JMenu("Material");

        jmiNewStudent = new JMenuItem("New Student");
        jmiDeleteStudent = new JMenuItem("Delete Student");
        jmiUpdateStudent = new JMenuItem("Update Student");
        jmiShowStudents = new JMenuItem("Show Students");
        jmiLoanApplication = new JMenuItem("Loan Application");
        jmiDevolution = new JMenuItem("Devolution");
        jmiNewMaterial = new JMenuItem("New Material");
        jmiAddBookExisting = new JMenuItem("Add Book Existing");
        jmiAllMaterials = new JMenuItem("All Materials");

        this.jmStudent.setMnemonic('S');
        this.jmMaterial.setMnemonic('M');
        this.jmLoans.setMnemonic('L');

        jmiUpdateStudent.setMnemonic('U');
        jmiNewStudent.setMnemonic('N');
        jmiDeleteStudent.setMnemonic('D');
        jmiShowStudents.setMnemonic('S');
        jmiNewMaterial.setMnemonic('N');
        jmiAddBookExisting.setMnemonic('A');
        jmiDevolution.setMnemonic('D');
        jmiAllMaterials.setMnemonic('M');

        jmiNewStudent.addActionListener(this);
        jmiDeleteStudent.addActionListener(this);
        jmiUpdateStudent.addActionListener(this);
        jmiShowStudents.addActionListener(this);
        jmiLoanApplication.addActionListener(this);
        jmiNewMaterial.addActionListener(this);
        jmiAddBookExisting.addActionListener(this);
        jmiDevolution.addActionListener(this);
        jmiAllMaterials.addActionListener(this);

        this.jMenuBar.setSize(800, 30);

        this.jmStudent.add(jmiNewStudent);
        this.jmStudent.add(jmiDeleteStudent);
        this.jmStudent.add(jmiUpdateStudent);
        this.jmStudent.add(jmiShowStudents);
        this.jmLoans.add(jmiLoanApplication);
        this.jmLoans.add(jmiDevolution);
        this.jmMaterial.add(jmiNewMaterial);
        this.jmMaterial.add(jmiAddBookExisting);
        this.jmMaterial.add(jmiAllMaterials);
        this.jMenuBar.add(this.jmLoans);
        this.jMenuBar.add(this.jmStudent);
        this.jMenuBar.add(this.jmMaterial);
        this.add(this.jMenuBar);
    } // init

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jmiNewStudent) {
            jmiNewStudent.setEnabled(false);
            this.jifNewStudent = new JIFNewStudent();
            jDesktopPane.add(this.jifNewStudent);
            jifNewStudent.setVisible(true);
        } else if (e.getSource() == jmiDeleteStudent) {
            jmiDeleteStudent.setEnabled(false);
            this.jifStudentDelete = new JIFStudentDelete();
            jDesktopPane.add(this.jifStudentDelete);
            jifStudentDelete.setVisible(true);
        } else if (e.getSource() == jmiUpdateStudent) {
            jmiUpdateStudent.setEnabled(false);
            this.jifStudentUpdate = new JIFStudentUpdate();
            jDesktopPane.add(this.jifStudentUpdate);
            this.jifStudentUpdate.setVisible(true);
        } else if (e.getSource() == jmiShowStudents) {
            jmiShowStudents.setEnabled(false);
            this.jifShowAllStudents = new JIFShowAllStudents();
            jDesktopPane.add(this.jifShowAllStudents);
            this.jifShowAllStudents.setVisible(true);
        } else if(e.getSource()==this.jmiLoanApplication){
            jmiLoanApplication.setEnabled(false);
            this.jifLoanApplication=new JIFVerify();
            jDesktopPane.add(jifLoanApplication);
            jifLoanApplication.setVisible(true);
        } else if (e.getSource() == jmiNewMaterial) {
            jmiNewMaterial.setEnabled(false);
            this.jifNewMaterial = new JIFNewMaterial();
            jDesktopPane.add(this.jifNewMaterial);
            this.jifNewMaterial.setVisible(true);
        } else if (e.getSource() == jmiAddBookExisting) {
            jmiAddBookExisting.setEnabled(false);
            this.jifAddBookExisting = new JIFAddBookExisting();
            jDesktopPane.add(this.jifAddBookExisting);
            this.jifAddBookExisting.setVisible(true);
        } else if(e.getSource() == jmiDevolution){
            jmiDevolution.setEnabled(false);
            this.jifDevolution = new JIFDevolution();
            jDesktopPane.add(this.jifDevolution);
            this.jifDevolution.setVisible(true);
        } else if(e.getSource() == jmiAllMaterials){
            jmiAllMaterials.setEnabled(false);
            this.jifAllMaterials = new JIFAllMaterials();
            jDesktopPane.add(this.jifAllMaterials);
            this.jifAllMaterials.setVisible(true);
        }
    } // actionPerformed

} // fin de la clase
