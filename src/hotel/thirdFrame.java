package hotel;

import PaqC07.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class thirdFrame extends JFrame{
    private JPanel thirdPanel;
    private JTextArea taMapaHotel;
    private JTextField tfNum;
    private JTextField tfDNI;
    private JLabel lbDNI;
    private JLabel lbNum;
    private JButton btAnular;
    private JButton btCancelar;
    private JLabel lbTipo;
    private JTextField tfTipo;
    private JButton btMostrar;
    protected Registro H;

    thirdFrame(){
        H = secondFrame.H;
        setContentPane(thirdPanel);
        setTitle("Getsión de reservas");
        setSize(650,300);
        this.mostrarMapa();
        setVisible(true);

        btCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                secondFrame A = new secondFrame();
                dispose();
            }
        });

        btAnular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int [] aux1 ={-1,-1};
                int cont1 = 0;
                String Tipo = new String();
                Tipo = tfTipo.getText();
                int tipo = 0;
                int DNI = Integer.parseInt(tfDNI.getText());
                int num = Integer.parseInt(tfNum.getText());
                if (Tipo.equals("Estandar") || Tipo.equals("estandar")){
                    tipo = 1;
                }
                else if (Tipo.equals("Balcones") || Tipo.equals("balcones")){
                    tipo = 2;
                }
                else if (Tipo.equals("Suites") || Tipo.equals("suites")){
                    tipo = 3;
                }
                else {
                    JOptionPane.showMessageDialog(null,"El tipo que ha escrito no existe, por favor asegurese de escribirlo como se muestra en el mapa.");
                    return;

                }
                for (int i = 0;i < num;i++) {
                    int [] aux2 = H.buscarReserva(DNI,tipo);
                    if (aux2[0] == aux1[0]) {
                        JOptionPane.showMessageDialog(null,"No se ha podido anular su reserva, compruebe que los datos son correctos y concuerdan con sus reservas.");
                        return;
                    }
                }
                if (tipo == 1){
                    for (int i = 0;i < 5;i++) {
                        for (int j = 0;j < H.numHab;j++){
                            if (H.habitaciones[i][j] != null){
                                cont1++;
                            }
                        }
                    }
                    if (cont1 < num){
                        JOptionPane.showMessageDialog(null,"No se ha podido anular su reserva, compruebe que los datos son correctos y concuerdan con sus reservas.");
                        return;
                    }
                }
                else if (tipo == 2){
                    for (int i = 5;i < 7;i++) {
                        for (int j = 0;j < H.numHab;j++){
                            if (H.habitaciones[i][j] != null){
                                cont1++;
                            }
                        }
                    }
                    if (cont1 < num){
                        JOptionPane.showMessageDialog(null,"No se ha podido anular su reserva, compruebe que los datos son correctos y concuerdan con sus reservas.");
                        return;
                    }
                }
                else {
                    for (int i = 7;i < 8;i++) {
                        for (int j = 0;j < H.numHab;j++){
                            if (H.habitaciones[i][j] != null){
                                cont1++;
                            }
                        }
                    }
                    if (cont1 < num){
                        JOptionPane.showMessageDialog(null,"No se ha podido anular su reserva, compruebe que los datos son correctos y concuerdan con sus reservas.");
                        return;
                    }
                }
                H.anulaReserva(DNI,tipo,num);
                try {
                    secondFrame.Serializar(H);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                mostrarMapa();
                JOptionPane.showMessageDialog(null,"El proceso de anulación ha finalizado.");
                tfTipo.setText("");
                tfNum.setText("");
            }
        });

        btMostrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tfDNI.getText() == ""){
                    JOptionPane.showMessageDialog(null,"Introduzca su DNI.");
                    return;
                }
                int dni = Integer.parseInt(tfDNI.getText());
                mostrarMapa2(dni);
            }
        });
    }

    public static void main(String[] args) {thirdFrame A = new thirdFrame();}

    public void mostrarMapa(){
        String mostrar = new String();
        for (int i = H.numPisos-1; i >= 0; i--) {
            for (int j = 0; j < H.numHab; ++j) {
                if (H.habitaciones[i][j] == null) {
                    mostrar = mostrar + " L ";
                } else {
                    mostrar = mostrar + " R ";
                }
            }
            if (i == (H.numPisos-1)){
                mostrar = mostrar+" --->Suites";
            }
            else if (i > 4 ){
                mostrar = mostrar+" --->Balcones";
            }
            else {
                mostrar = mostrar+" --->Estandar";
            }
            mostrar = mostrar + "\n";
        }
        taMapaHotel.setText(mostrar);
    }

    public void mostrarMapa2(int dni){
        String mostrar = new String();
        for (int i = H.numPisos-1; i >= 0; i--) {
            for (int j = 0; j < H.numHab; ++j) {
                if (H.habitaciones[i][j] != null) {
                    if (H.habitaciones[i][j].getDni() == dni){
                        mostrar = mostrar + " X ";
                    }
                    else {
                        mostrar = mostrar + " R ";
                    }
                } else {
                    mostrar = mostrar + " L ";
                }
            }
            if (i == (H.numPisos-1)){
                mostrar = mostrar+" --->Suites";
            }
            else if (i > 4 ){
                mostrar = mostrar+" --->Balcones";
            }
            else {
                mostrar = mostrar+" --->Estandar";
            }
            mostrar = mostrar + "\n";
        }
        taMapaHotel.setText(mostrar);
    }
}
