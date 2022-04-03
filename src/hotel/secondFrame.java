package hotel;



import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import PaqC07.*;

import java.io.*;
import java.nio.file.Path;

import static java.nio.file.Files.exists;


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
    private JButton gestionarButton;
    private JTextField tfFechaAlta;
    private JTextField tfFechaBaja;
    private JLabel lbFechaAlta;
    private JLabel lbFechaBaja;
    protected static Registro H;
    private int tipo;
    private int numEstandar;
    private int numBalcon;
    private int numSuite;


    public secondFrame(){
        setContentPane(Reservas);
        try {
            H = Leer();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
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
                tfFechaAlta.setText("");
                tfFechaBaja.setText("");
            }
        });


        btnConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String regimen;
                if (comboBoxRegimen.getSelectedIndex() == 0) {
                    regimen = "sin desayuno";
                } else if (comboBoxRegimen.getSelectedIndex() == 1) {
                    regimen = "con desayuno";
                } else if (comboBoxRegimen.getSelectedIndex() == 2) {
                    regimen = "media pension";
                } else {
                    regimen = "pension completa";
                }
                ////////////////*NUM RESERVAS Y TIPOS*///////////////////////////////////////////////////////////////////
                numEstandar = Integer.parseInt(tfEstandar.getText());
                numBalcon = Integer.parseInt(tfBalcon.getText());
                numSuite = Integer.parseInt(tfSuite.getText());
                int totalHabitaciones = numEstandar+numBalcon+numSuite;
                int tipo = 0;
                ///////////////*INTRODUCIR DATOS EN EL HOTEL*/////////////////////////////////////////////////////////
                for (int i = 0;i < totalHabitaciones;i++) {
                    if (numEstandar > 0){
                        tipo = 1;
                        numEstandar--;
                    }
                    else if (numBalcon > 0){
                        tipo = 2;
                        numBalcon--;
                    }
                    else{
                        tipo = 3;
                        numSuite--;
                    }
                    int coordenadas[] = H.encuentraHab(tipo);
                    if (coordenadas[0] == -1){
                        JOptionPane.showMessageDialog(null,"No hay suficientes habitaciones disponibles para realizar todas las reservas solicitadas.");
                        return;
                    }
                    else {
                        String nombre = String.valueOf(tfNombre);
                        String apellidos = String.valueOf(tfApellidos);
                        int DNI = Integer.parseInt(tfDni.getText());
                        int telefono = Integer.parseInt(tfTelefono.getText());
                        int tarjetaPago = Integer.parseInt(tfTarjeta.getText());
                        String fechaAlta = String.valueOf(tfFechaAlta);
                        String fechaBaja = String.valueOf(tfFechaBaja);
                        Cliente cl = new Cliente(nombre, apellidos, DNI, telefono, tarjetaPago, fechaAlta, fechaBaja, regimen);
                        H.almacenaReserva(coordenadas, cl);
                        try {
                            Serializar(H);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
                JOptionPane.showMessageDialog(null,"Todas las reservas han sido realizadas con exito.");
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
                tfFechaAlta.setText("");
                tfFechaBaja.setText("");
                lbSalida.setText("");

            }
        });
        btnCalcular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int precio=0;
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
                    tfEstandar.setText("1");
                }
                else {
                    tfEstandar.setText("0");
                }
            }
        });
        cbBalcon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(cbBalcon.isSelected()){
                    JOptionPane.showMessageDialog(null,"Has pulsado 'balcón'.");
                    tfBalcon.setText("1");
                }
                else {
                    tfBalcon.setText("0");
                }
            }
        });

        cbSuite.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(cbSuite.isSelected()){
                    JOptionPane.showMessageDialog(null,"Has pulsado 'suite'.");
                    tfSuite.setText("1");
                }
                else {
                    tfSuite.setText("0");
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
        gestionarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean mens=true;
                for (int i=0;i<8;i++){
                    for (int j=0;j<6;j++){
                        if(H.habitaciones[i][j]!=null){
                            mens = false;
                            break;
                        }
                    }
                    if (mens == false)break;;
                }
                if (mens)JOptionPane.showMessageDialog(null,"No se ha realizado ninguna reserva actualmente");
                else {
                    thirdFrame third = new thirdFrame();
                    dispose();
                }
            }
        });
    }

    public static void main(String[] args) {
        secondFrame second = new secondFrame();
    }

    public static void Serializar(Registro r) throws IOException{
        FileOutputStream fos = new FileOutputStream("reg.dat");
        ObjectOutputStream salida = new ObjectOutputStream(fos);
        salida.writeObject(r);
        fos.close();
        salida.close();
    }



    public static Registro Leer() throws IOException, ClassNotFoundException {
        if ((exists(Path.of("reg.dat")) == true)){
            FileInputStream fis = new FileInputStream("reg.dat");
            ObjectInputStream entrada = new ObjectInputStream(fis);
            Registro salida = (Registro) entrada.readObject();
            fis.close();
            entrada.close();
            return salida;
        }
        else{
            return new Registro();
        }
    }


}
