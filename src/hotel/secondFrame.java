package hotel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class secondFrame extends JFrame {
    private JPanel Reservas;
    private JLabel lbNombre;
    private JLabel lbApellidos;
    private JLabel lbDireccion;
    private JLabel lbTelefono;
    private JLabel lbEmail;
    private JLabel lbDNI;
    private JLabel lbTarjeta;
    private JTextField tfNombre;
    private JTextField tfApellidos;
    private JTextField tfDireccion;
    private JTextField tfTelefono;
    private JTextField tfEmail;
    private JTextField tfDni;
    private JTextField tfTarjeta;
    private JLabel lbDatos;
    private JLabel lbTIpo;
    private JCheckBox cbEstandar;
    private JCheckBox cbBalcon;
    private JCheckBox cbSuite;
    private JTextField tfEstandar;
    private JTextField tfBalcon;
    private JTextField tfSuite;
    private JLabel lbRegimen;
    private JComboBox comboBoxRegimen;
    private JLabel lbPrecio;
    private JTextField tfPrecio;
    private JButton btnCalcular;
    private JButton btnCancelar;
    private JButton btnLimpiar;
    private JButton btnConfirmar;
    private JLabel lbSalida;
    private JLabel lbInfo;
    private JLabel lbInfo2;


    public secondFrame(){
        setContentPane(Reservas);
        setTitle("Reservas");
        setSize(900,400);
        setVisible(true);
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame primerFrame = new mainFrame();
                dispose();
            }
        });
        btnLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tfNombre.setText("");
                tfApellidos.setText("");
                tfDireccion.setText("");
                tfTelefono.setText("");
                tfEmail.setText("");
                tfDni.setText("");
                tfTarjeta.setText("");
                cbEstandar.setSelected(false);
                cbBalcon.setSelected(false);
                cbSuite.setSelected(false);
                tfEstandar.setText("0");
                tfBalcon.setText("0");
                tfSuite.setText("0");
                tfPrecio.setText("");
                lbSalida.setText("");
            }
        });
        btnConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"Reserva confirmada.");

                tfNombre.setText("");
                tfApellidos.setText("");
                tfDireccion.setText("");
                tfTelefono.setText("");
                tfEmail.setText("");
                tfDni.setText("");
                tfTarjeta.setText("");
                cbEstandar.setSelected(false);
                cbBalcon.setSelected(false);
                cbSuite.setSelected(false);
                tfEstandar.setText("0");
                tfBalcon.setText("0");
                tfSuite.setText("0");
                tfPrecio.setText("");
                lbSalida.setText("");

            }
        });
        btnCalcular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int precio=0, numSuite=0, numBalcon=0, numEstandar=0;
                if(cbEstandar.isSelected()){
                    numEstandar=Integer.parseInt(tfEstandar.getText());
                }
                if(cbBalcon.isSelected()){
                    numBalcon=Integer.parseInt(tfBalcon.getText());
                }
                if(cbSuite.isSelected()){
                    numSuite=Integer.parseInt(tfSuite.getText());
                }

                if(comboBoxRegimen.getSelectedIndex()==1){
                    precio+=50;
                }else if(comboBoxRegimen.getSelectedIndex()==2){
                    precio+=100;
                }else if(comboBoxRegimen.getSelectedIndex()==3){
                    precio+=200;
                }

                precio+=numBalcon*15+numSuite*30+numEstandar*7;
                tfPrecio.setText(Integer.toString(precio));

            }
        });
        cbEstandar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(cbEstandar.isSelected()){
                    JOptionPane.showMessageDialog(null,"Has pulsado tipo 'estándar'.");
                }
            }
        });
        cbBalcon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(cbSuite.isSelected()){
                    JOptionPane.showMessageDialog(null,"Has pulsado 'balcón'.");
                }
            }
        });

        cbSuite.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(cbSuite.isSelected()){
                    JOptionPane.showMessageDialog(null,"Has pulsado 'suite'.");
                }
            }
        });
        comboBoxRegimen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(comboBoxRegimen.getSelectedIndex()==0){
                    JOptionPane.showMessageDialog(null, "Seleccionaste sin desayuno.");
                }else if(comboBoxRegimen.getSelectedIndex()==1){
                    JOptionPane.showMessageDialog(null, "Seleccionaste con desayuno.");
                }else if(comboBoxRegimen.getSelectedIndex()==2){
                    JOptionPane.showMessageDialog(null, "Seleccionaste media pensión.");
                }else{
                    JOptionPane.showMessageDialog(null, "Seleccionaste pensión completa.");
                }
            }
        });
    }

    public static void main(String[] args) {
        secondFrame second = new secondFrame();
    }


}
