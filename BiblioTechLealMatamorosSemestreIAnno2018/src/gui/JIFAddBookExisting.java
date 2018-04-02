package gui;

import business.MaterialBusiness;
import domain.Book;
import domain.Material;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import javax.swing.table.DefaultTableModel;

public class JIFAddBookExisting extends javax.swing.JInternalFrame implements InternalFrameListener {

    private MaterialBusiness materialBusiness;

    public JIFAddBookExisting() {
        this.materialBusiness = new MaterialBusiness();
        this.setLocation(20, 40);
        this.addInternalFrameListener(this);
        initComponents();
        init();
    } // constructor

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jtBooks = new javax.swing.JTable();
        jbAdd = new javax.swing.JButton();
        jtfQuantity = new javax.swing.JTextField();
        jlCode = new javax.swing.JLabel();
        jcbCodes = new javax.swing.JComboBox<>();
        jlQuantity = new javax.swing.JLabel();

        setClosable(true);
        setTitle("Add Book Existing");

        jtBooks.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jtBooks);

        jbAdd.setText("Add");
        jbAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbAddActionPerformed(evt);
            }
        });

        jlCode.setText("Code");

        jcbCodes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jlQuantity.setText("Quantity");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 414, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlCode)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jlQuantity)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jbAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jtfQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(54, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jcbCodes, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(37, 37, 37))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(88, 88, 88)
                        .addComponent(jlCode)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcbCodes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jlQuantity))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addComponent(jtfQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jbAdd))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbAddActionPerformed
        try {
            int number = Integer.parseInt(this.jtfQuantity.getText());
            if (number < 1) {
                JOptionPane.showMessageDialog(this, "The number is invalid, please enter it again.", "Error", 0);
            } else {
                int code = Integer.parseInt(this.jcbCodes.getSelectedItem().toString());
                int quantity = Integer.parseInt(this.jtfQuantity.getText());
                this.materialBusiness.addBookExixting(code, quantity);
                JOptionPane.showMessageDialog(this, "Success");
                this.jtfQuantity.setText("");
                initTable();
            }
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(this, "You must enter a number.", "Error", 0);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(JIFAddBookExisting.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jbAddActionPerformed

    private ArrayList<Material> removeDigitals(ArrayList<Material> list) {
        ArrayList<Material> newArrayList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (!((Book) list.get(i)).getFormat().equals("Digital")) {
                newArrayList.add(list.get(i));
            }
        }
        return newArrayList;
    } // removeDigitals

    private void init() {
        try {
            ArrayList<Material> list = removeDigitals(this.materialBusiness.getBooksAndAudiovisual().get(0));

            String codes[] = new String[list.size()];
            for (int i = 0; i < codes.length; i++) {
                codes[i] = "" + list.get(i).getCode();
            }
            jcbCodes.setModel(new javax.swing.DefaultComboBoxModel<>(codes));
            initTable();
        } // init
        catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(JIFAddBookExisting.class.getName()).log(Level.SEVERE, null, ex);
        }
    } // init

    private void initTable() {
        try {
            ArrayList<Material> list = removeDigitals(this.materialBusiness.getBooksAndAudiovisual().get(0));
            Object[][] employeeDates = new Object[0][0];
            String[] columNames1 = {"Code", "Name", "Format", "Amount"};
            DefaultTableModel dtmModelTable = new DefaultTableModel(employeeDates, columNames1);
            Book book = new Book();
            for (int i = 0; i < list.size(); i++) {
                book = (Book) list.get(i);
                dtmModelTable.addRow(new Object[]{book.getCode(), book.getName(),
                    book.getFormat(), book.getAmount()});
            } // for
            this.jtBooks.setModel(dtmModelTable);
            this.jtBooks.setEnabled(false);
            jtBooks.getColumnModel().getColumn(0).setPreferredWidth(10);
            jtBooks.getColumnModel().getColumn(2).setPreferredWidth(10);
            jtBooks.getColumnModel().getColumn(3).setPreferredWidth(10);
        } // initTable
        catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(JIFAddBookExisting.class.getName()).log(Level.SEVERE, null, ex);
        }
    } // initTable: inicializa la tabla

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbAdd;
    private javax.swing.JComboBox<String> jcbCodes;
    private javax.swing.JLabel jlCode;
    private javax.swing.JLabel jlQuantity;
    private javax.swing.JTable jtBooks;
    private javax.swing.JTextField jtfQuantity;
    // End of variables declaration//GEN-END:variables

    @Override
    public void internalFrameOpened(InternalFrameEvent e) {
    }

    @Override
    public void internalFrameClosing(InternalFrameEvent e) {
        MainWindows.jmiAddBookExisting.setEnabled(true);
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
