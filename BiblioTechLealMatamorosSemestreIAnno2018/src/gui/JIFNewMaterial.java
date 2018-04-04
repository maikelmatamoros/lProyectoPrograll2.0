package gui;

import business.MaterialBusiness;
import domain.Audiovisual;
import domain.Book;
import domain.CustomPanel;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

public class JIFNewMaterial extends JInternalFrame implements ActionListener, InternalFrameListener {

    //atributos
    private MaterialBusiness materialBusiness;
    private JComboBox jcbOption, jcbTheme, jcbBrand, jcbType;
    private JLabel jlName, jlAuthor, jlYear, jlTheme, jlLanguage, jlCountry, jlFormat, jlBrand, jlDescription, jlType;
    private JTextField jtfName, jtfAuthor, jtfYear, jtfLanguage, jtfCountry, jtfDescription;
    private JRadioButton jrbPhysical, jrbDigital;
    private ButtonGroup bgFormat;
    private JButton jbOkBook, jbOkAudiovisual, jbOkDisk, jbOkOther;

    public JIFNewMaterial() {
        super("New Material", false, true, false, false);
        this.setSize(360, 150);
        this.setLocation(20, 40);
        this.materialBusiness = new MaterialBusiness();
        this.setContentPane(new CustomPanel(1, 0, -160, 360, 580));
        this.setLayout(null);
        init();
        this.addInternalFrameListener(this);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    } // constructor

    //inicia los elementos graficos
    private void init() {
        initComponets();
        this.jcbOption.addItem("Book");
        this.jcbOption.addItem("Disk");
        this.jcbOption.addItem("Laptop");
        this.jcbOption.addItem("Mouse");
        this.jcbOption.addItem("Projector");
        this.jcbOption.addItem("Other");

        this.jcbOption.setBounds(30, 20, 120, 25);
        this.jcbOption.addActionListener(this);

        this.add(jcbOption);
        this.initBook();
    } // init

    private void initComponets() {
        this.jlYear = new JLabel("Year");
        this.jlType = new JLabel("Type");
        this.jlName = new JLabel("Name");
        this.jlBrand = new JLabel("Brand");
        this.jlTheme = new JLabel("Theme");
        this.jlAuthor = new JLabel("Author");
        this.jlFormat = new JLabel("Format");
        this.jlCountry = new JLabel("Country");
        this.jlLanguage = new JLabel("Language");
        this.jlDescription = new JLabel("Description");

        this.jlYear.setForeground(Color.BLACK);
        this.jlType.setForeground(Color.BLACK);
        this.jlName.setForeground(Color.BLACK);
        this.jlBrand.setForeground(Color.BLACK);
        this.jlTheme.setForeground(Color.BLACK);
        this.jlAuthor.setForeground(Color.BLACK);
        this.jlFormat.setForeground(Color.BLACK);
        this.jlCountry.setForeground(Color.BLACK);
        this.jlLanguage.setForeground(Color.BLACK);
        this.jlDescription.setForeground(Color.BLACK);

        this.jtfName = new JTextField();
        this.jtfYear = new JTextField();
        this.jtfAuthor = new JTextField();
        this.jtfCountry = new JTextField();
        this.jtfLanguage = new JTextField();
        this.jtfDescription = new JTextField();

        this.jbOkBook = new JButton();
        this.jbOkAudiovisual = new JButton();
        this.jbOkDisk = new JButton();
        this.jbOkOther = new JButton();

        this.jrbDigital = new JRadioButton("Digital");
        this.jrbPhysical = new JRadioButton("Physical");

        this.jrbDigital.setForeground(Color.BLACK);
        this.jrbPhysical.setForeground(Color.BLACK);

        this.jrbDigital.setContentAreaFilled(false);
        this.jrbPhysical.setContentAreaFilled(false);

        this.jcbType = new JComboBox();
        this.jcbTheme = new JComboBox();
        this.jcbBrand = new JComboBox();
        this.jcbOption = new JComboBox();

        this.jcbType.setForeground(Color.BLACK);
        this.jcbTheme.setForeground(Color.BLACK);
        this.jcbBrand.setForeground(Color.BLACK);
        this.jcbOption.setForeground(Color.BLACK);

        this.bgFormat = new ButtonGroup();

        this.jbOkBook.setFocusable(false);

        this.jbOkBook.addActionListener(this);

        this.jbOkAudiovisual.addActionListener(this);
        this.jbOkAudiovisual.setFocusable(false);

        this.jbOkDisk.addActionListener(this);
        this.jbOkDisk.setFocusable(false);

        this.jbOkOther.addActionListener(this);
        this.jbOkOther.setFocusable(false);

        String themes[] = {"Other", "Agronomy", "Anthropology", "Biology", "Chemistry", "Computing",
            "Economic Sciences", "Essay", "Geography", "Geology", "History", "Languages", "Law",
            "Math", "Medicine", "Music", "Pedagogy", "Philology", "Philosophy", "Psychology", "Science",
            "Sociology", "Tesis", "Thesis"};
        String brands[] = {"Hp", "Toshiba", "Dell"};

        this.jcbTheme.setModel(new javax.swing.DefaultComboBoxModel<>(themes));
        this.jcbBrand.setModel(new javax.swing.DefaultComboBoxModel<>(brands));

        this.jcbType.addItem("CD");
        this.jcbType.addItem("DVD");

        this.bgFormat.add(this.jrbDigital);
        this.bgFormat.add(this.jrbPhysical);
    } // initComponets
    
    //inicializa los componentes por si se selecciona libros
    private void initBook() {
        this.setSize(360, 450);
        refresh();

        this.jlName.setBounds(30, 80, 40, 15);
        this.jlAuthor.setBounds(30, 120, 50, 15);
        this.jlYear.setBounds(30, 160, 70, 15);
        this.jlTheme.setBounds(30, 200, 70, 15);
        this.jlLanguage.setBounds(30, 240, 100, 15);
        this.jlCountry.setBounds(30, 280, 70, 15);
        this.jlFormat.setBounds(30, 320, 70, 15);
        this.jtfName.setBounds(130, 75, 170, 25);
        this.jtfAuthor.setBounds(130, 115, 170, 25);
        this.jtfYear.setBounds(130, 155, 170, 25);
        this.jcbTheme.setBounds(130, 195, 170, 25);
        this.jtfLanguage.setBounds(130, 235, 170, 25);
        this.jtfCountry.setBounds(130, 275, 170, 25);
        this.jrbDigital.setBounds(125, 320, 73, 15);
        this.jrbPhysical.setBounds(210, 320, 85, 15);
        this.jbOkBook.setBounds(240, 360, 80, 30);

        ImageIcon image = new ImageIcon("src/assets/jbOk.png");
        Icon icon = new ImageIcon(image.getImage().getScaledInstance(this.jbOkBook.getWidth(), this.jbOkBook.getHeight(), Image.SCALE_DEFAULT));
        this.jbOkBook.setIcon(icon);
        this.jbOkBook.setContentAreaFilled(false);
        this.jbOkBook.setBorderPainted(false);

        this.jrbDigital.setSelected(true);

        this.add(this.jlName);
        this.add(this.jlAuthor);
        this.add(this.jlYear);
        this.add(this.jlTheme);
        this.add(this.jlLanguage);
        this.add(this.jlCountry);
        this.add(this.jlFormat);
        this.add(this.jtfName);
        this.add(this.jtfAuthor);
        this.add(this.jtfYear);
        this.add(this.jcbTheme);
        this.add(this.jtfLanguage);
        this.add(this.jtfCountry);
        this.add(this.jrbDigital);
        this.add(this.jrbPhysical);
        this.add(this.jbOkBook);
    } // initBook
    
    private void initAudiovisual() {
        this.setSize(360, 240);
        refresh();

        this.jlBrand.setBounds(30, 70, 50, 15);
        this.jlDescription.setBounds(30, 110, 90, 15);
        this.jcbBrand.setBounds(130, 65, 170, 25);
        this.jtfDescription.setBounds(130, 105, 170, 25);
        this.jbOkAudiovisual.setBounds(230, 160, 80, 30);

        ImageIcon image = new ImageIcon("src/assets/jbOk.png");
        Icon icon = new ImageIcon(image.getImage().getScaledInstance(this.jbOkAudiovisual.getWidth(), this.jbOkAudiovisual.getHeight(), Image.SCALE_DEFAULT));
        this.jbOkAudiovisual.setIcon(icon);
        this.jbOkAudiovisual.setContentAreaFilled(false);
        this.jbOkAudiovisual.setBorderPainted(false);

        this.add(this.jlBrand);
        this.add(this.jlDescription);
        this.add(this.jcbBrand);
        this.add(this.jtfDescription);
        this.add(this.jbOkAudiovisual);
    } // initAudiovisual: laptop, proyector, Mouse
    
    private void initDisk() {
        this.setSize(360, 280);
        refresh();

        this.jlType.setBounds(30, 70, 50, 15);
        this.jlName.setBounds(30, 110, 50, 15);
        this.jlDescription.setBounds(30, 150, 90, 15);
        this.jcbType.setBounds(130, 65, 170, 25);
        this.jtfName.setBounds(130, 105, 170, 25);
        this.jtfDescription.setBounds(130, 140, 170, 25);
        this.jbOkDisk.setBounds(230, 190, 80, 30);

        ImageIcon image = new ImageIcon("src/assets/jbOk.png");
        Icon icon = new ImageIcon(image.getImage().getScaledInstance(this.jbOkDisk.getWidth(), this.jbOkDisk.getHeight(), Image.SCALE_DEFAULT));
        this.jbOkDisk.setIcon(icon);
        this.jbOkDisk.setContentAreaFilled(false);
        this.jbOkDisk.setBorderPainted(false);

        this.add(this.jlType);
        this.add(this.jlName);
        this.add(this.jlDescription);
        this.add(this.jcbType);
        this.add(this.jtfName);
        this.add(this.jtfDescription);
        this.add(this.jbOkDisk);
    } // initDisk: CD DVD

    private void initOther() {
        this.setSize(360, 240);
        refresh();

        this.jlName.setBounds(30, 70, 50, 15);
        this.jlDescription.setBounds(30, 110, 90, 15);
        this.jtfName.setBounds(130, 65, 170, 25);
        this.jtfDescription.setBounds(130, 100, 170, 25);
        this.jbOkOther.setBounds(230, 160, 80, 30);

        ImageIcon image = new ImageIcon("src/assets/jbOk.png");
        Icon icon = new ImageIcon(image.getImage().getScaledInstance(this.jbOkOther.getWidth(), this.jbOkOther.getHeight(), Image.SCALE_DEFAULT));
        this.jbOkOther.setIcon(icon);
        this.jbOkOther.setContentAreaFilled(false);
        this.jbOkOther.setBorderPainted(false);

        this.add(this.jlName);
        this.add(this.jlDescription);
        this.add(this.jtfName);
        this.add(this.jtfDescription);
        this.add(this.jbOkOther);
    } // initOther

    private void refresh() {
        if (this.jcbTheme.isShowing()) {
            this.remove(this.jcbTheme);
        }
        if (this.jcbBrand.isShowing()) {
            this.remove(this.jcbBrand);
        }
        if (this.jcbType.isShowing()) {
            this.remove(this.jcbType);
        }
        if (this.jlName.isShowing()) {
            this.remove(this.jlName);
        }
        if (this.jlAuthor.isShowing()) {
            this.remove(this.jlAuthor);
        }
        if (this.jlYear.isShowing()) {
            this.remove(this.jlYear);
        }
        if (this.jlTheme.isShowing()) {
            this.remove(this.jlTheme);
        }
        if (this.jlLanguage.isShowing()) {
            this.remove(this.jlLanguage);
        }
        if (this.jlCountry.isShowing()) {
            this.remove(this.jlCountry);
        }
        if (this.jlFormat.isShowing()) {
            this.remove(this.jlFormat);
        }
        if (this.jlBrand.isShowing()) {
            this.remove(this.jlBrand);
        }
        if (this.jlDescription.isShowing()) {
            this.remove(this.jlDescription);
        }
        if (this.jlType.isShowing()) {
            this.remove(this.jlType);
        }
        if (this.jtfName.isShowing()) {
            this.remove(this.jtfName);
        }
        if (this.jtfAuthor.isShowing()) {
            this.remove(this.jtfAuthor);
        }
        if (this.jtfYear.isShowing()) {
            this.remove(this.jtfYear);
        }
        if (this.jtfLanguage.isShowing()) {
            this.remove(this.jtfLanguage);
        }
        if (this.jtfCountry.isShowing()) {
            this.remove(this.jtfCountry);
        }
        if (this.jtfDescription.isShowing()) {
            this.remove(this.jtfDescription);
        }
        if (this.jrbPhysical.isShowing()) {
            this.remove(this.jrbPhysical);
        }
        if (this.jrbDigital.isShowing()) {
            this.remove(this.jrbDigital);
        }
        if (this.jbOkBook.isShowing()) {
            this.remove(this.jbOkBook);
        }
        if (this.jbOkAudiovisual.isShowing()) {
            this.remove(this.jbOkAudiovisual);
        }
        if (this.jbOkDisk.isShowing()) {
            this.remove(this.jbOkDisk);
        }
        if (this.jbOkOther.isShowing()) {
            this.remove(this.jbOkOther);
        }
    } // refresh

    private void cleanTextFields() {
        this.jtfName.setText("");
        this.jtfYear.setText("");
        this.jtfAuthor.setText("");
        this.jtfCountry.setText("");
        this.jtfLanguage.setText("");
        this.jtfDescription.setText("");
    } // cleanTextFields

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.jcbOption) {
            Object selection = this.jcbOption.getSelectedItem();
            if (selection.equals("Book")) {
                initBook();
            } else if (selection.equals("Laptop") || selection.equals("Projector") || selection.equals("Mouse")) {
                initAudiovisual();
            } else if (selection.equals("Disk")) {
                initDisk();
            } else if (selection.equals("Other")) {
                initOther();
            }
        } else if (e.getSource() == this.jbOkBook) {
            try {
                String name = this.jtfName.getText();
                String author = this.jtfAuthor.getText();
                String language = this.jtfLanguage.getText();
                String country = this.jtfCountry.getText();
                int year = Integer.parseInt(this.jtfYear.getText());
                if (name.equals("") || author.equals("") || language.equals("") || country.equals("")) {
                    JOptionPane.showMessageDialog(this, "All spaces are required", "Error", 0);
                }else if(year>2018){
                    JOptionPane.showMessageDialog(this, "The date can not be greater than the current year ", "Error", 0);
                } else {
                    Book book = new Book(name, author, year, this.jcbTheme.getSelectedItem().toString(),
                            language, country, "", 1, -1, this.jcbOption.getSelectedItem().toString());

                    if (this.jrbDigital.isSelected()) {
                        book.setFormat("Digital");
                    } else {
                        book.setFormat("Physical");
                    }
                    try {
                        this.materialBusiness.addMaterial(book, 2);
                        JOptionPane.showMessageDialog(this, "Success");
                        cleanTextFields();
                    } catch (IOException | ClassNotFoundException ex) {
                        Logger.getLogger(JIFNewMaterial.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(this, "The year must be a number.", "Error", 0);
            }
        } else if (e.getSource() == this.jbOkAudiovisual) {
            try {
                this.materialBusiness.addMaterial(new Audiovisual(this.jcbBrand.getSelectedItem().toString(),
                        this.jtfDescription.getText(), -1, this.jcbOption.getSelectedItem().toString(),
                        true), 1);

                JOptionPane.showMessageDialog(this, "Success");
                cleanTextFields();
            } catch (IOException | ClassNotFoundException ex) {
                Logger.getLogger(JIFNewMaterial.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (e.getSource() == this.jbOkDisk) {
            if (this.jtfName.equals("")) {
                JOptionPane.showMessageDialog(this, "The name is required", "Error", 0);
            } else {
                try {
                    this.materialBusiness.addMaterial(new Audiovisual(this.jtfName.getText(),
                            this.jtfDescription.getText(), -1, this.jcbType.getSelectedItem().toString(),
                            true), 1);
                    JOptionPane.showMessageDialog(this, "Success");
                    cleanTextFields();
                } catch (IOException | ClassNotFoundException ex) {
                    Logger.getLogger(JIFNewMaterial.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else if (e.getSource() == this.jbOkOther) {
            try {
                if (this.jtfName.equals("") || this.jtfDescription.equals("")) {
                    JOptionPane.showMessageDialog(this, "All spaces are required", "Error", 0);
                } else {
                    this.materialBusiness.addMaterial(new Audiovisual(this.jtfName.getText(),
                            this.jtfDescription.getText(), -1, "Other", true), 1);
                    JOptionPane.showMessageDialog(this, "Success");
                    cleanTextFields();
                }
            } catch (IOException | ClassNotFoundException ex) {
                Logger.getLogger(JIFNewMaterial.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    } // actionPerformed

    @Override
    public void internalFrameOpened(InternalFrameEvent e) {
    }

    @Override
    public void internalFrameClosing(InternalFrameEvent e) {
        MainWindows.jmiNewMaterial.setEnabled(true);
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
