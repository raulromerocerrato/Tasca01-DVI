/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.paucasesnovescifp.tasca01_dvi.ui;
import com.paucasesnovescifp.tasca01_dvi.dades.GestorDades;
import com.paucasesnovescifp.tasca01_dvi.i18n.Idioma;
import com.paucasesnovescifp.tasca01_dvi.model.Joc;
import com.paucasesnovescifp.tasca01_dvi.model.Lloguer;
import com.paucasesnovescifp.tasca01_dvi.model.Plataforma;
import com.paucasesnovescifp.tasca01_dvi.model.Usuari;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

/**
 *
 * @author alumne
 */
public class DialegRegistrarLloguer extends javax.swing.JDialog {
    
    private final static DateTimeFormatter FORMAT_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private GestorDades gestor;
    private List<Usuari> usuaris;
    private List<Joc> jocs;
    private JRadioButton[] radios;
    private boolean carregant = true;

    /**
     * Creates new form DialegRegistrarLloguer
     */
    public DialegRegistrarLloguer(java.awt.Frame parent, boolean modal, GestorDades gestor, Usuari usuariInicial, Joc jocInicial) {
        super(parent, modal);
        this.gestor = gestor;
        initComponents();
        // mateix ordre que Plataforma.values(): PS5, XBOX, PC, SWITCH_2
        radios = new JRadioButton[] {radioPS5, radioXbox, radioPC, radioSwitch2};
        usuaris = gestor.getUsuaris();
        jocs = gestor.getJocs();

        aplicarTextos();
        carregarCombos(usuariInicial, jocInicial);

        // Dades automàtiques: l'usuari no les pot modificar
        LocalDate avui = LocalDate.now();
        campRegistre.setText(String.format("%02d", gestor.getProperNumRegistre()));
        campDataInici.setText(avui.format(FORMAT_DATA));
        campDataTornada.setText(
                avui.plusDays(Lloguer.DIES_LLOGUER).format(FORMAT_DATA));
        campRegistre.setEnabled(false);
        campDataInici.setEnabled(false);
        campDataTornada.setEnabled(false);

        carregant = false;
        actualitzarPlataformes();
        getRootPane().setDefaultButton(botoConfirmar);
        setLocationRelativeTo(parent);
    }
    
    private void aplicarTextos() {
        setTitle(Idioma.get("lloguer.titol"));
        etiquetaRegistre.setText(Idioma.get("lloguer.num.registre"));
        etiquetaUsuari.setText(Idioma.get("lloguer.usuari"));
        etiquetaJoc.setText(Idioma.get("lloguer.joc"));
        etiquetaDataInici.setText(Idioma.get("lloguer.data.inici"));
        etiquetaDataTornada.setText(Idioma.get("lloguer.data.tornada"));
        panellPlataforma.setBorder(
                BorderFactory.createTitledBorder(Idioma.get("lloguer.plataforma")));
        panellInfo.setBorder(
                BorderFactory.createTitledBorder(Idioma.get("lloguer.info.joc")));
        botoConfirmar.setText(Idioma.get("boto.confirmar"));
        botoCancellar.setText(Idioma.get("boto.cancellar"));
    }
    private void carregarCombos(Usuari usuariInicial, Joc jocInicial) {
        comboUsuari.removeAllItems();
        comboJoc.removeAllItems();
        for (Usuari u : usuaris) {
            comboUsuari.addItem(u.getNomComplet());
        }
        for (Joc j : jocs) {
            comboJoc.addItem(j.getTitol());
        }
        int iu = usuaris.indexOf(usuariInicial);
        if (iu >= 0) comboUsuari.setSelectedIndex(iu);
        int ij = jocs.indexOf(jocInicial);
        if (ij >= 0) comboJoc.setSelectedIndex(ij);
    }
    private void actualitzarPlataformes() {
        grupPlataforma.clearSelection();
        int idx = comboJoc.getSelectedIndex();
        Plataforma[] plataformes = Plataforma.values();

        if (idx < 0) {
            for (int i = 0; i < radios.length; i++) {
                radios[i].setText(plataformes[i].toString());
                radios[i].setEnabled(false);
            }
            etiquetaGenere.setText("");
            botoConfirmar.setEnabled(false);
            return;
        }

        Joc joc = jocs.get(idx);
        etiquetaGenere.setText(Idioma.get("joc.genere") + ": " + joc.getGenere());

        boolean algunaDisponible = false;
        for (int i = 0; i < radios.length; i++) {
            int unitats = joc.getUnitatsDisponibles(plataformes[i]);
            radios[i].setText(plataformes[i] + " " + textUnitats(unitats));
            radios[i].setEnabled(unitats > 0);
            if (unitats > 0 && !algunaDisponible) {
                radios[i].setSelected(true); // primera disponible per defecte
                algunaDisponible = true;
            }
        }
        // Sense estoc o sense usuaris, no es pot confirmar
        botoConfirmar.setEnabled(algunaDisponible && !usuaris.isEmpty());
    }

    private String textUnitats(int unitats) {
        return unitats == 1
                ? Idioma.get("lloguer.unitat.disponible", unitats)
                : Idioma.get("lloguer.unitats.disponibles.n", unitats);
    }

    private void mostrarError(String missatge) {
        JOptionPane.showMessageDialog(this, missatge,
                Idioma.get("titol.error"), JOptionPane.ERROR_MESSAGE);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupPlataforma = new javax.swing.ButtonGroup();
        etiquetaRegistre = new javax.swing.JLabel();
        campRegistre = new javax.swing.JTextField();
        etiquetaUsuari = new javax.swing.JLabel();
        comboUsuari = new javax.swing.JComboBox<>();
        etiquetaJoc = new javax.swing.JLabel();
        comboJoc = new javax.swing.JComboBox<>();
        etiquetaDataInici = new javax.swing.JLabel();
        campDataInici = new javax.swing.JTextField();
        etiquetaDataTornada = new javax.swing.JLabel();
        campDataTornada = new javax.swing.JTextField();
        panellPlataforma = new javax.swing.JPanel();
        radioPS5 = new javax.swing.JRadioButton();
        radioXbox = new javax.swing.JRadioButton();
        radioPC = new javax.swing.JRadioButton();
        radioSwitch2 = new javax.swing.JRadioButton();
        panellInfo = new javax.swing.JPanel();
        etiquetaGenere = new javax.swing.JLabel();
        botoConfirmar = new javax.swing.JButton();
        botoCancellar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        etiquetaRegistre.setText("jLabel1");

        etiquetaUsuari.setText("jLabel1");

        comboUsuari.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        etiquetaJoc.setText("jLabel2");

        comboJoc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboJoc.addActionListener(this::comboJocActionPerformed);

        etiquetaDataInici.setText("jLabel1");

        etiquetaDataTornada.setText("jLabel1");

        campDataTornada.addActionListener(this::campDataTornadaActionPerformed);

        grupPlataforma.add(radioPS5);
        radioPS5.setText("jRadioButton1");

        grupPlataforma.add(radioXbox);
        radioXbox.setText("jRadioButton1");

        grupPlataforma.add(radioPC);
        radioPC.setText("jRadioButton1");
        radioPC.addActionListener(this::radioPCActionPerformed);

        grupPlataforma.add(radioSwitch2);
        radioSwitch2.setText("jRadioButton1");
        radioSwitch2.addActionListener(this::radioSwitch2ActionPerformed);

        javax.swing.GroupLayout panellPlataformaLayout = new javax.swing.GroupLayout(panellPlataforma);
        panellPlataforma.setLayout(panellPlataformaLayout);
        panellPlataformaLayout.setHorizontalGroup(
            panellPlataformaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panellPlataformaLayout.createSequentialGroup()
                .addContainerGap(74, Short.MAX_VALUE)
                .addGroup(panellPlataformaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(radioSwitch2)
                    .addComponent(radioPC)
                    .addComponent(radioXbox)
                    .addComponent(radioPS5))
                .addGap(66, 66, 66))
        );
        panellPlataformaLayout.setVerticalGroup(
            panellPlataformaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panellPlataformaLayout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addComponent(radioPS5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(radioXbox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(radioPC)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(radioSwitch2)
                .addContainerGap(96, Short.MAX_VALUE))
        );

        etiquetaGenere.setText("jLabel1");

        botoConfirmar.setBackground(new java.awt.Color(51, 255, 51));
        botoConfirmar.setText("jButton1");
        botoConfirmar.addActionListener(this::botoConfirmarActionPerformed);

        botoCancellar.setBackground(new java.awt.Color(255, 0, 0));
        botoCancellar.setText("jButton2");
        botoCancellar.addActionListener(this::botoCancellarActionPerformed);

        javax.swing.GroupLayout panellInfoLayout = new javax.swing.GroupLayout(panellInfo);
        panellInfo.setLayout(panellInfoLayout);
        panellInfoLayout.setHorizontalGroup(
            panellInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panellInfoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(etiquetaGenere)
                .addGap(93, 93, 93))
            .addGroup(panellInfoLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(botoConfirmar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(botoCancellar)
                .addGap(20, 20, 20))
        );
        panellInfoLayout.setVerticalGroup(
            panellInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panellInfoLayout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addComponent(etiquetaGenere)
                .addGap(33, 33, 33)
                .addGroup(panellInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botoConfirmar)
                    .addComponent(botoCancellar))
                .addContainerGap(49, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(etiquetaJoc)
                    .addComponent(comboJoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(etiquetaDataInici)
                    .addComponent(etiquetaRegistre)
                    .addComponent(campRegistre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(etiquetaUsuari)
                    .addComponent(comboUsuari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campDataInici, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campDataTornada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(etiquetaDataTornada))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 198, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panellPlataforma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(panellInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(etiquetaRegistre)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campRegistre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(etiquetaUsuari)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboUsuari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(etiquetaJoc)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboJoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(etiquetaDataInici))
                    .addComponent(panellPlataforma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(campDataInici, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(etiquetaDataTornada)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campDataTornada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(panellInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void campDataTornadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campDataTornadaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campDataTornadaActionPerformed

    private void radioPCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioPCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radioPCActionPerformed

    private void radioSwitch2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioSwitch2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radioSwitch2ActionPerformed

    private void comboJocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboJocActionPerformed
        if (carregant) return;
        actualitzarPlataformes();
    }//GEN-LAST:event_comboJocActionPerformed

    private void botoCancellarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botoCancellarActionPerformed
        dispose();
    }//GEN-LAST:event_botoCancellarActionPerformed

    private void botoConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botoConfirmarActionPerformed
        int iu = comboUsuari.getSelectedIndex();
        int ij = comboJoc.getSelectedIndex();

        Plataforma plataforma = null;
        Plataforma[] plataformes = Plataforma.values();
        for (int i = 0; i < radios.length; i++) {
            if (radios[i].isSelected()) plataforma = plataformes[i];
        }

        try {
            gestor.registrarLloguer(
                    iu < 0 ? null : usuaris.get(iu),
                    ij < 0 ? null : jocs.get(ij),
                    plataforma);
            JOptionPane.showMessageDialog(this, Idioma.get("ok.lloguer.registrat"),
                    Idioma.get("titol.informacio"), JOptionPane.INFORMATION_MESSAGE);
            dispose();
        } catch (IllegalArgumentException | IllegalStateException ex) {
            mostrarError(Idioma.get(ex.getMessage()));
            actualitzarPlataformes(); // l'estoc pot haver canviat
        }
    }//GEN-LAST:event_botoConfirmarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botoCancellar;
    private javax.swing.JButton botoConfirmar;
    private javax.swing.JTextField campDataInici;
    private javax.swing.JTextField campDataTornada;
    private javax.swing.JTextField campRegistre;
    private javax.swing.JComboBox<String> comboJoc;
    private javax.swing.JComboBox<String> comboUsuari;
    private javax.swing.JLabel etiquetaDataInici;
    private javax.swing.JLabel etiquetaDataTornada;
    private javax.swing.JLabel etiquetaGenere;
    private javax.swing.JLabel etiquetaJoc;
    private javax.swing.JLabel etiquetaRegistre;
    private javax.swing.JLabel etiquetaUsuari;
    private javax.swing.ButtonGroup grupPlataforma;
    private javax.swing.JPanel panellInfo;
    private javax.swing.JPanel panellPlataforma;
    private javax.swing.JRadioButton radioPC;
    private javax.swing.JRadioButton radioPS5;
    private javax.swing.JRadioButton radioSwitch2;
    private javax.swing.JRadioButton radioXbox;
    // End of variables declaration//GEN-END:variables
}
