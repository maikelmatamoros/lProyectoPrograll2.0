package gui;

import business.LoanBusiness;
import business.MaterialBusiness;
import com.toedter.calendar.JDateChooser;
import domain.Audiovisual;
import domain.Book;
import domain.CustomPanel;
import domain.Loan;
import domain.Material;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
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
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import javax.swing.table.DefaultTableModel;

public class JIFLoan extends JInternalFrame implements ActionListener, MouseListener, KeyListener,InternalFrameListener {
    //atributos
    private JComboBox jComboSelection, jComboBox;
    private JButton jbtnOk;
    private JDateChooser jdcLoanDate,jdcReturnDate;
    private JTable jtbTable;
    private DefaultTableModel dtmModelTable;
    private JScrollPane scrollPane;
    private ArrayList<Material> subList;
    private ArrayList<Book> list1;
    private ArrayList<Audiovisual> list2;
    private String format;
    private JTextField jtfText, jtfCode, jtfCode1;
    private JLabel jlblText, jlblCode,jlblLoanDate,jlblReturnLoan;
    private String studenID;
    private MaterialBusiness materialBusiness;
    
    public JIFLoan(String id) {
        super("Loan", false, true, false, false);
        this.setSize(700, 500);
        this.setContentPane(new CustomPanel(1,0,0,700,500));
        this.setLayout(null);
        this.setLocation(30, 30);
        this.subList = new ArrayList<>();
        this.studenID = id;
        this.materialBusiness=new MaterialBusiness();
        init();
    } // constructor

    //init: inicializa los componentes que se ven al abrir la ventana
    public void init() {
        this.addInternalFrameListener(this);
        this.jComboSelection = new JComboBox(new String[]{"Book", "Material"});
        this.jdcLoanDate = new JDateChooser();
        this.jdcReturnDate = new JDateChooser();
        this.jtfText = new JTextField();
        this.jtfCode = new JTextField();
        this.jtfCode1 = new JTextField();
        this.jComboBox = new JComboBox();
        this.jlblCode = new JLabel("Insert Code");
        this.jlblText = new JLabel("Insert Name");
        this.jbtnOk = new JButton("OK");
        this.jlblLoanDate=new JLabel("Loan Date");
        this.jlblReturnLoan=new JLabel("Return Date");

        this.jComboSelection.setBounds(30, 30, 100, 30);
        this.jdcLoanDate.setBounds(445, 190, 100, 25);
        this.jdcReturnDate.setBounds(555, 190, 100, 25);
        this.jtfText.setBounds(555, 120, 100, 25);
        this.jtfCode.setBounds(445, 120, 100, 25);
        this.jtfCode1.setBounds(445, 120, 100, 25);
        this.jlblCode.setBounds(445, 80, 100, 25);
        this.jlblText.setBounds(555, 80, 100, 25);
        this.jlblLoanDate.setBounds(450, 160, 100, 25);
        this.jlblReturnLoan.setBounds(555, 160, 100, 25);
        
        this.jbtnOk.setBounds(510, 240, 70, 40);

        this.jtfText.setVisible(true);
        this.jtfCode.setVisible(true);
        this.jlblCode.setVisible(true);
        this.jlblText.setVisible(true);
        this.jtfCode1.setVisible(false);

        this.jComboSelection.addActionListener(this);
        this.jbtnOk.addActionListener(this);
        this.jtfText.addKeyListener(this);
        this.jtfCode.addKeyListener(this);
        this.jtfCode1.addKeyListener(this);
        this.scrollPane = new JScrollPane();
        MaterialBusiness materialBusiness = new MaterialBusiness();
        this.jComboBox = new JComboBox(new String[]{"Physical", "Digital"});
        this.jComboBox.setBounds(150, 30, 100, 30);
        this.jComboBox.addActionListener(this);
        this.add(this.jComboBox);
        try {
            this.list1=materialBusiness.getBooksAndAudiovisual().get(0);
            System.out.println(this.list1.size());
        } catch (IOException ex) {
            Logger.getLogger(JIFLoan.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JIFLoan.class.getName()).log(Level.SEVERE, null, ex);
        }
        
            initTableBook(list1, jComboBox.getSelectedItem().toString());
        
        this.add(this.jdcLoanDate);
        this.add(this.jdcReturnDate);
        this.add(this.jComboSelection);
        this.add(this.jtfText);
        this.add(this.jtfCode);
        this.add(this.jComboBox);
        this.add(this.jlblCode);
        this.add(this.jlblText);
        this.add(this.jtfCode1);
        this.add(this.jbtnOk);
        this.add(this.jlblLoanDate);
        this.add(this.jlblReturnLoan);
    } // init

    public void clearTextFields() {
        this.name = "";
        this.code = "";
        this.jtfCode.setText("");
        this.jtfText.setText("");
        this.jtfCode1.setText("");
    } // clearTextFields y variables name y code

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == this.jComboSelection) {

            if (this.jComboBox.isShowing()) {
                this.remove(this.jComboBox);
            }
            clearTextFields();
            
            if (this.jComboSelection.getSelectedIndex() == 0) {

                try {
                    this.list1 = this.materialBusiness.getBooksAndAudiovisual().get(0);
                } catch (IOException ex) {
                    Logger.getLogger(JIFLoan.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(JIFLoan.class.getName()).log(Level.SEVERE, null, ex);
                }
                initTableBook(list1,format);
                if (this.jtfCode1.isShowing()) {
                    this.remove(this.jtfCode1);
                }
                this.jComboBox = new JComboBox(new String[]{"Physical", "Digital"});
                this.jComboBox.setBounds(150, 30, 100, 30);
                this.jComboBox.addActionListener(this);
                this.add(this.jComboBox);
                this.jtfText.setVisible(true);
                this.jtfCode.setVisible(true);
                this.jlblCode.setVisible(true);
                this.jlblText.setVisible(true);
            } else {
                clearTextFields();
                try {
                    this.list2 = this.materialBusiness.getBooksAndAudiovisual().get(1);
                } catch (IOException | ClassNotFoundException ex) {
                    Logger.getLogger(JIFLoan.class.getName()).log(Level.SEVERE, null, ex);
                }
                initTableMaterial(list2,format);
                if (this.jlblText.isShowing()) {
                    this.jtfText.setVisible(false);
                    this.jlblText.setVisible(false);
                    this.jtfCode1.setVisible(true);

                } else {
                    this.jtfCode1.setVisible(true);
                    this.jlblCode.setVisible(true);
                }
                this.jComboBox = new JComboBox(new String[]{"Disk", "Laptop", "Mouse", "Projector", "Other"});
                this.jComboBox.setBounds(150, 30, 100, 30);
                this.jComboBox.addActionListener(this);
                this.add(this.jComboBox);
            }
        } else if (e.getSource() == this.jComboBox) {
            if (this.jComboSelection.getSelectedIndex() == 0) {
                this.format = this.jComboBox.getSelectedItem().toString();
                initTableBook(list1, format);

            } else {
                this.format = this.jComboBox.getSelectedItem().toString();
                initTableMaterial(list2, format);

            }

        } else if (e.getSource() == this.jbtnOk) {
            //agarra las fechas de los JDateChooser y las guarda en variables y luego guarda los pedidos
            LoanBusiness loanBusiness = new LoanBusiness();    
            String dayLoan = Integer.toString(this.jdcLoanDate.getCalendar().get(Calendar.DAY_OF_MONTH));
            String monthLoan = Integer.toString(this.jdcLoanDate.getCalendar().get(Calendar.MONTH) + 1);
            String yearLoan = Integer.toString(this.jdcLoanDate.getCalendar().get(Calendar.YEAR));
            String dayReturn = Integer.toString(this.jdcReturnDate.getCalendar().get(Calendar.DAY_OF_MONTH));
            String monthReturn = Integer.toString(this.jdcReturnDate.getCalendar().get(Calendar.MONTH) + 1);
            String yearReturn = Integer.toString(this.jdcReturnDate.getCalendar().get(Calendar.YEAR));
            String loanDate=yearLoan+"-"+monthLoan+"-"+dayLoan;
            String returnDate =yearReturn+"-"+monthReturn+"-"+dayReturn;
            try {
                if (loanBusiness.addLoan(new Loan(this.subList.get(this.rowObjetIndex).getCode(), loanDate, returnDate, this.studenID,this.jComboSelection.getSelectedItem().toString()))) {
                    JOptionPane.showMessageDialog(rootPane, "Success");
                    //recarga las tablas por si algún pedido dejá sin un producto a la biblioteca
                    if (this.jComboSelection.getSelectedIndex() == 0) {
                    
                        this.materialBusiness.update(this.subList.get(this.rowObjetIndex).getCode(), 0,0);
                        list1=this.materialBusiness.getBooksAndAudiovisual().get(0);
                        initTableBook(list1, format);
                    } else {
                    
                        this.materialBusiness.update(this.subList.get(this.rowObjetIndex).getCode(), 1,0);
                        list2=this.materialBusiness.getBooksAndAudiovisual().get(1);
                        initTableMaterial(list2, format);
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(JIFLoan.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(JIFLoan.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    } // actionPerformed

    public void initTableBook(ArrayList<Book> list, String subString, String format, int var) {
        this.remove(this.scrollPane);
        int tam = subString.length();
        this.subList=new ArrayList<>();
        Object[][] vehicles = new Object[0][0];
        String[] columNames1 = {"Name", "Author", "Year", "Language", "Theme"};
        this.dtmModelTable = new DefaultTableModel(vehicles, columNames1);
        for (int i = 0; i < list.size(); i++) {
            if (tam <= list.get(i).getName().length()&&list.get(i).getAmountAvaiable()>0 && list.get(i).getFormat().equals(format) && var == 0 && 
            list.get(i).getName().substring(0, subString.length()).equalsIgnoreCase(subString)) {
                this.subList.clear();
                this.subList.add(list.get(i));
                this.dtmModelTable.addRow(new Object[]{list.get(i).getName(),
                    list.get(i).getAuthor(), list.get(i).getYear(), list.get(i).getLanguage(), list.get(i).getTheme()});
            } else if (list.get(i).getFormat().equals(format) && tam <= 9 && var == 1 && String.valueOf(list.get(i).getCode()).substring(0, subString.length()).equalsIgnoreCase(subString)) {
                this.subList.clear();
                this.subList.add(list.get(i));
                this.dtmModelTable.addRow(new Object[]{list.get(i).getName(),
                    list.get(i).getAuthor(), list.get(i).getYear(), list.get(i).getLanguage(), list.get(i).getTheme()});
            }

        }//for

        this.jtbTable = new JTable(this.dtmModelTable);

        this.scrollPane = new JScrollPane(this.jtbTable);

        scrollPane.setBounds(
                10, 80, 400, 285);

        this.jtbTable.setSelectionBackground(Color.GREEN);

        this.add(scrollPane);

                 
        this.jtbTable.addMouseListener(
                this);
    } // inicializa el modelo de la tabla cargando los valores del archivo

    public void initTableBook(ArrayList<Book> list, String format) {
        this.remove(this.scrollPane);
        Object[][] vehicles = new Object[0][0];
        String[] columNames1 = {"Name", "Author", "Year", "Language", "Theme"};
        this.dtmModelTable = new DefaultTableModel(vehicles, columNames1);
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getFormat().equalsIgnoreCase(format)&&list.get(i).getAmountAvaiable()>0) {
                this.subList.clear();
                this.subList.add(list.get(i));
                this.dtmModelTable.addRow(new Object[]{list.get(i).getName(),
                    list.get(i).getAuthor(), list.get(i).getYear(), list.get(i).getLanguage(), list.get(i).getTheme()});
            }
        }//for

        this.jtbTable = new JTable(this.dtmModelTable);
        this.scrollPane = new JScrollPane(this.jtbTable);
        scrollPane.setBounds(10, 80, 400, 285);
        this.jtbTable.setSelectionBackground(Color.GREEN);

        this.add(scrollPane);
        this.jtbTable.addMouseListener(this);
    } // inicializa el modelo de la tabla cargando los valores del archivo    

    public void initTableMaterial(ArrayList<Audiovisual> list, String subString, String format) {
        this.remove(this.scrollPane);

        Object[][] vehicles = new Object[0][0];
        String[] columNames1 = {"Type", "ID", "Description", "Code"};
        this.dtmModelTable = new DefaultTableModel(vehicles, columNames1);
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getType().equals(format)
                    && list.get(i).isAvailability()&& subString.length() <= 5 && String.valueOf(this.list2.get(i).getCode()).substring(0, subString.length()).equalsIgnoreCase(subString)) {
                this.subList.add(list.get(i));
                this.dtmModelTable.addRow(new Object[]{list.get(i).getType(),
                    list.get(i).getId(), list.get(i).getDescription(), list.get(i).getCode()});
            }
        }//for

        this.jtbTable = new JTable(this.dtmModelTable);
        this.scrollPane = new JScrollPane(this.jtbTable);
        scrollPane.setBounds(10, 80, 400, 285);
        this.jtbTable.setSelectionBackground(Color.GREEN);
        this.add(scrollPane);
        this.jtbTable.addMouseListener(this);
    } // inicializa el modelo de la tabla cargando los valores del archivo

    public void initTableMaterial(ArrayList<Audiovisual> list, String format) {
        this.remove(this.scrollPane);

        Object[][] vehicles = new Object[0][0];
        String[] columNames1 = {"Type", "ID", "Description", "Code"};
        this.dtmModelTable = new DefaultTableModel(vehicles, columNames1);
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getType().equals(format) && list.get(i).isAvailability()) {
                this.subList.clear();
                this.subList.add(list.get(i));
                this.dtmModelTable.addRow(new Object[]{list.get(i).getType(),
                    list.get(i).getId(), list.get(i).getDescription(), list.get(i).getCode()});
            }
        }//for

        this.jtbTable = new JTable(this.dtmModelTable);
        this.scrollPane = new JScrollPane(this.jtbTable);
        scrollPane.setBounds(10, 80, 400, 285);
        this.jtbTable.setSelectionBackground(Color.GREEN);
        this.add(scrollPane);
        this.jtbTable.addMouseListener(this);
    } // inicializa el modelo de la tabla cargando los valores del archivo


    private int rowObjetIndex;

    @Override
    public void mouseClicked(MouseEvent e) {
        this.rowObjetIndex = this.jtbTable.getSelectedRow();
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
    private String name = "";
    private String code = "";

    @Override
    public void keyTyped(KeyEvent e) {
        this.format=this.jComboBox.getSelectedItem().toString();
        if (e.getSource() == this.jtfText) {
            int keycode = e.getKeyChar();
            if (keycode != 8) {
                this.name = name + (char) keycode;
                initTableBook(this.list1, name, format, 0);
            } else {
                if (name.length() >= 1) {
                    this.name = name.substring(0, name.length() - 1);
                    initTableBook(this.list1, name, this.format, 0);
                }
            }
        } else if (e.getSource() == this.jtfCode) {

            int keycode = e.getKeyChar();

            if (keycode != 8) {
                this.code = code + (char) keycode;

                initTableBook(this.list1, code, format, 1);

            } else {
                if (code.length() >= 1) {
                    this.code = code.substring(0, code.length() - 1);

                    initTableBook(this.list1, code, format, 1);

                }
            }
        } else if (e.getSource() == this.jtfCode1) {
            int keycode = e.getKeyChar();

            if (keycode != 8) {
                this.code = code + (char) keycode;

                initTableMaterial(this.list2, code, format);

            } else {
                if (code.length() >= 1) {
                    this.code = code.substring(0, code.length() - 1);

                    initTableMaterial(this.list2, code, format);
                }
            }

        }

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void internalFrameOpened(InternalFrameEvent e) {
        
    }

    @Override
    public void internalFrameClosing(InternalFrameEvent e) {
        
    }

    @Override
    public void internalFrameClosed(InternalFrameEvent e) {
       MainWindows.flag=1;
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
