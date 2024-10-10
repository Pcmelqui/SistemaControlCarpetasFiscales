/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemacontrolcarpetasfiscales;

/**
 *
 * @author Wilson
 */
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame {
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private LoginDAO loginDAO;

    public Login() {
        loginDAO = new LoginDAO();

        setTitle("Login");
        setLayout(null);
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setBounds(20, 20, 80, 25);
        add(lblUsername);

        txtUsername = new JTextField();
        txtUsername.setBounds(100, 20, 165, 25);
        add(txtUsername);

        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setBounds(20, 60, 80, 25);
        add(lblPassword);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(100, 60, 165, 25);
        add(txtPassword);

        btnLogin = new JButton("Login");
        btnLogin.setBounds(100, 100, 80, 25);
        add(btnLogin);

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = txtUsername.getText();
                String password = new String(txtPassword.getPassword());
                Usuario usuario = loginDAO.autenticarUsuario(username, password);
                if (usuario != null) {
                    JOptionPane.showMessageDialog(null, "Bienvenido, " + usuario.getNombre());
                    // Aquí puedes abrir la ventana principal según el rol
                    if (usuario.getRol().equals("Administrador")) {
                        // Abrir ventana de administrador
                    } else {
                        // Abrir ventana de usuario
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos");
                }
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new Login();
    }
}

