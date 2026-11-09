/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.paucasesnovescifp.tasca01_dvi.ui;
import com.paucasesnovescifp.tasca01_dvi.dades.GestorDades;
import com.paucasesnovescifp.tasca01_dvi.i18n.Idioma;
import com.paucasesnovescifp.tasca01_dvi.model.Lloguer;
import java.awt.Color;
import java.awt.Component;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import com.paucasesnovescifp.tasca01_dvi.model.Joc;
import com.paucasesnovescifp.tasca01_dvi.model.Usuari;

/**
 *
 * @author alumne
 */
public class FinestraPrincipal extends javax.swing.JFrame {    
    private static final DateTimeFormatter FORMAT_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final Color VERD = new Color(198, 239, 206);
    private static final Color VERMELL = new Color(255, 199, 206);

    private final GestorDades gestor = new GestorDades();
    private final List<Lloguer> lloguersMostrats = new ArrayList<>();
    private DefaultTableModel modelTaula;
  
    /**
     * Creates new form FinestraPrincipal
     */
    public FinestraPrincipal() {
        initComponents();
        aplicarTextos();
        configurarTaula();
        setLocationRelativeTo(null);
        refrescarTaula();
    }
    
    private void aplicarTextos() {
        setTitle(Idioma.get("app.titol"));
        menuLloguer.setText(Idioma.get("menu.lloguer"));
        itemRegistrar.setText(Idioma.get("menu.registrar"));
        itemTornar.setText(Idioma.get("menu.tornar"));
        menuUsuari.setText(Idioma.get("menu.usuari"));
        itemCrearUsuari.setText(Idioma.get("menu.crear"));
        itemBorrarUsuari.setText(Idioma.get("menu.borrar"));
        menuJoc.setText(Idioma.get("menu.joc"));
        itemCrearJoc.setText(Idioma.get("menu.crear"));
        itemBorrarJoc.setText(Idioma.get("menu.borrar"));
    }

    private void configurarTaula() {
        modelTaula = (DefaultTableModel) taula.getModel();
        modelTaula.setColumnIdentifiers(new String[] {
            Idioma.get("taula.registre"),
            Idioma.get("taula.titol.joc"),
            Idioma.get("taula.usuari"),
            Idioma.get("taula.plataforma"),
            Idioma.get("taula.data.inici"),
            Idioma.get("taula.data.prevista"),
            Idioma.get("taula.genere"),
            Idioma.get("taula.estat")
        });
        taula.setDefaultEditor(Object.class, null);
        taula.setRowHeight(26);
        taula.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        taula.getTableHeader().setReorderingAllowed(false);
        taula.setDefaultRenderer(Object.class, new RendererLloguer());
        taula.getColumnModel().getColumn(0).setMaxWidth(110);
    }

    public void refrescarTaula() {
        modelTaula.setRowCount(0);
        lloguersMostrats.clear();

        for (Lloguer l : gestor.getLloguers()) {
            lloguersMostrats.add(l);
            modelTaula.addRow(new Object[] {
                String.format("%02d", l.getNumRegistre()),
                l.getJoc().getTitol(),
                l.getUsuari().getNomComplet(),
                l.getPlataforma().toString(),
                l.getDataInici().format(FORMAT_DATA),
                l.getDataPrevista().format(FORMAT_DATA),
                l.getJoc().getGenere(),
                textEstat(l)
            });
        }
        etiquetaTotal.setText(
                Idioma.get("etiqueta.total") + " " + gestor.getTotalLloguers());
    }

    private String textEstat(Lloguer l) {
        if (l.isRetornat()) return Idioma.get("estat.retornat");
        if (l.isForaDeTermini()) return Idioma.get("estat.fora.termini");
        return Idioma.get("estat.actiu");
    }

    public Lloguer getLloguerSeleccionat() {
        int fila = taula.getSelectedRow();
        return fila < 0 ? null : lloguersMostrats.get(fila);
    }

    public GestorDades getGestor() {
        return gestor;
    }

    private class RendererLloguer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable t, Object valor, boolean seleccionada, boolean focus, int fila, int columna) {
            Component c = super.getTableCellRendererComponent(
                    t, valor, seleccionada, focus, fila, columna);
            if (!seleccionada) {
                Lloguer l = lloguersMostrats.get(fila);
                c.setForeground(Color.BLACK);
                if (l.isRetornat()) {
                    c.setBackground(VERD);
                } else if (l.isForaDeTermini()) {
                    c.setBackground(VERMELL);
                } else {
                    c.setBackground(Color.WHITE);
                }
            }
            return c;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        taula = new javax.swing.JTable();
        etiquetaTotal = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuLloguer = new javax.swing.JMenu();
        itemRegistrar = new javax.swing.JMenuItem();
        itemTornar = new javax.swing.JMenuItem();
        menuUsuari = new javax.swing.JMenu();
        itemCrearUsuari = new javax.swing.JMenuItem();
        itemBorrarUsuari = new javax.swing.JMenuItem();
        menuJoc = new javax.swing.JMenu();
        itemCrearJoc = new javax.swing.JMenuItem();
        itemBorrarJoc = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        taula.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(taula);

        menuLloguer.setText("Lloguer");

        itemRegistrar.setText("Registrar");
        itemRegistrar.addActionListener(this::itemRegistrarActionPerformed);
        menuLloguer.add(itemRegistrar);

        itemTornar.setText("Tornar");
        itemTornar.addActionListener(this::itemTornarActionPerformed);
        menuLloguer.add(itemTornar);

        jMenuBar1.add(menuLloguer);

        menuUsuari.setText("Usuari");

        itemCrearUsuari.setText("Crear Usuari");
        itemCrearUsuari.addActionListener(this::itemCrearUsuariActionPerformed);
        menuUsuari.add(itemCrearUsuari);

        itemBorrarUsuari.setText("Borrar Usuari");
        itemBorrarUsuari.addActionListener(this::itemBorrarUsuariActionPerformed);
        menuUsuari.add(itemBorrarUsuari);

        jMenuBar1.add(menuUsuari);

        menuJoc.setText("Joc");

        itemCrearJoc.setText("Crear Joc");
        itemCrearJoc.addActionListener(this::itemCrearJocActionPerformed);
        menuJoc.add(itemCrearJoc);

        itemBorrarJoc.setText("Borrar Joc");
        menuJoc.add(itemBorrarJoc);

        jMenuBar1.add(menuJoc);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(etiquetaTotal)
                .addContainerGap(691, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 697, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(424, Short.MAX_VALUE)
                .addComponent(etiquetaTotal)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 28, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void itemRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemRegistrarActionPerformed
        Lloguer seleccionat = getLloguerSeleccionat();
        Usuari usuari = seleccionat == null ? null : seleccionat.getUsuari();
        Joc joc = seleccionat == null ? null : seleccionat.getJoc();
        new DialegRegistrarLloguer(this, true, gestor, usuari, joc).setVisible(true);
        refrescarTaula();
    }//GEN-LAST:event_itemRegistrarActionPerformed

    private void itemTornarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemTornarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_itemTornarActionPerformed

    private void itemBorrarUsuariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemBorrarUsuariActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_itemBorrarUsuariActionPerformed

    private void itemCrearUsuariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemCrearUsuariActionPerformed
        new DialegCrearUsuari(this, true, gestor).setVisible(true);
    }//GEN-LAST:event_itemCrearUsuariActionPerformed

    private void itemCrearJocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemCrearJocActionPerformed
        new DialegCrearJoc(this, true, gestor).setVisible(true);
    }//GEN-LAST:event_itemCrearJocActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Locale.setDefault(Idioma.LOCALE);
        JComponent.setDefaultLocale(Idioma.LOCALE);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            // si falla, es queda el look and feel per defecte
        }
        java.awt.EventQueue.invokeLater(() -> new FinestraPrincipal().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel etiquetaTotal;
    private javax.swing.JMenuItem itemBorrarJoc;
    private javax.swing.JMenuItem itemBorrarUsuari;
    private javax.swing.JMenuItem itemCrearJoc;
    private javax.swing.JMenuItem itemCrearUsuari;
    private javax.swing.JMenuItem itemRegistrar;
    private javax.swing.JMenuItem itemTornar;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenu menuJoc;
    private javax.swing.JMenu menuLloguer;
    private javax.swing.JMenu menuUsuari;
    private javax.swing.JTable taula;
    // End of variables declaration//GEN-END:variables
}
