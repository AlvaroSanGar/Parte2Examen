package hotel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class mainFrame extends JFrame {
    private JLabel lbUsuario;
    private JLabel lbContraseña;
    private JButton btnLimpiar;
    private JButton btnEntrar;
    private JTextField tfUsuario;
    private JPasswordField tfContraseña;
    private JPanel Hotel;
    private JLabel lbMensaje;


    public mainFrame(){
        setContentPane(Hotel);
        setTitle("Hotel");
        setSize(450,300);
        setVisible(true);
        btnLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tfUsuario.setText("");
                tfContraseña.setText("");
                lbMensaje.setText(" ");
            }
        });
        btnEntrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(tfUsuario.getText().equals("Pepe") && tfContraseña.getText().equals("abc")){
                    secondFrame second = new secondFrame();
                    dispose();
                }else{
                    JOptionPane.showMessageDialog(null, "Credenciales incorrectas.");
                }
            }
        });
    }

    public static void main(String[] args) {
        mainFrame primerFrame = new mainFrame();
    }
}
