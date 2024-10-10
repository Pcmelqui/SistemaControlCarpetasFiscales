/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemacontrolcarpetasfiscales;

/**
 *
 * @author Wilson
 */
import java.sql.*;

public class LoginDAO {
    private DatabaseConnection dbConnection;

    public LoginDAO() {
        dbConnection = new DatabaseConnection();
    }

    public Usuario autenticarUsuario(String username, String password) {
        String sql = "SELECT * FROM Usuario WHERE Username = ? AND Password = ?";
        try (Connection conn = dbConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, password); // Aquí se recomienda encriptar la contraseña
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Usuario(
                            rs.getInt("IDUsuario"),
                            rs.getString("Nombre"),
                            rs.getString("Apellido"),
                            rs.getString("Username"),
                            rs.getString("Password"),
                            rs.getString("Rol")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Si no se encuentra el usuario
    }
}
