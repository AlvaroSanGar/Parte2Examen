package hotel;

import PaqC07.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

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
                int tipo = Integer.parseInt(tfTipo.getText());
                int DNI = Integer.parseInt(tfDNI.getText());
                int num = Integer.parseInt(tfNum.getText());
                H.anulaReserva(DNI,tipo,num);
                try {
                    Serializar(H);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                mostrarMapa();
                JOptionPane.showMessageDialog(null,"El proceso de anulación ha finalizado.");
            }
        });

    }

    public static void main(String[] args) {thirdFrame A = new thirdFrame();}

    public void mostrarMapa(){
        String mostrar = new String();
        for (int i = 0; i < H.numPisos; ++i) {
            for (int j = 0; j < H.numHab; ++j) {
                if (H.habitaciones[i][j] == null) {
                    mostrar = mostrar + " L ";
                } else {
                    mostrar = mostrar + " R ";
                }
            }

            mostrar = mostrar + "\n";
        }
        taMapaHotel.setText(mostrar);
    }

    private static void Serializar(Registro r) throws IOException {
        FileOutputStream fos = new FileOutputStream("reg.dat");
        ObjectOutputStream salida = new ObjectOutputStream(fos);
        salida.writeObject(r);
        fos.close();
        salida.close();
    }

}
