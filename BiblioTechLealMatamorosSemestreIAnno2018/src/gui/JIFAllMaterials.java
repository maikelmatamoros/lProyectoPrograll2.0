package gui;

import business.MaterialBusiness;
import domain.Audiovisual;
import domain.Book;
import domain.Material;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import javax.swing.table.DefaultTableModel;

public class JIFAllMaterials extends javax.swing.JInternalFrame implements InternalFrameListener {

    private DefaultTableModel dtmModelTable;
    ;
    private ArrayList<Material> list;
    private MaterialBusiness materialBusiness;

    public JIFAllMaterials() {
        this.setLocation(20, 40);
        this.addInternalFrameListener(this);
        initComponents();
        initTable();
    } // constructor

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setClosable(true);
        setTitle("All Materials");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 609, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 408, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void initTable() {
        try {
            Object[][] material = new Object[0][0];
            String[] columNames1 = {"Code", "Type", "Description"};
            this.dtmModelTable = new DefaultTableModel(material, columNames1);
            this.materialBusiness = new MaterialBusiness();
            this.list = (ArrayList<Material>) materialBusiness.getAllMaterials();
            
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getType().equals("Book")) {
                    this.dtmModelTable.addRow(new Object[]{list.get(i).getCode(), list.get(i).getType(),
                        ((Book) list.get(i)).getName()});
                } else {
                    this.dtmModelTable.addRow(new Object[]{list.get(i).getCode(), list.get(i).getType(),
                        ((Audiovisual) list.get(i)).getDescription()});
                }
            } // for
            this.jTable1.setModel(dtmModelTable);
            this.jTable1.setEnabled(false);
        } // initTable
        catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(JIFAllMaterials.class.getName()).log(Level.SEVERE, null, ex);
        }
    } // initTable

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void internalFrameOpened(InternalFrameEvent e) {
    }

    @Override
    public void internalFrameClosing(InternalFrameEvent e) {
        MainWindows.jmiAllMaterials.setEnabled(true);
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
