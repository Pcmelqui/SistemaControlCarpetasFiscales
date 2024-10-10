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
import java.util.ArrayList;
import java.util.List;

public class PeritoDAO {
    private DatabaseConnection dbConnection;

    public PeritoDAO() {
        dbConnection = new DatabaseConnection();
    }

    public void agregarPerito(Perito perito) {
        String sql = "INSERT INTO Perito (Nombres, Apellidos, LugarTrabajo) VALUES (?, ?, ?)";
        try (Connection conn = dbConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, perito.getNombres());
            stmt.setString(2, perito.getApellidos());
            stmt.setString(3, perito.getLugarTrabajo());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void modificarPerito(Perito perito) {
        String sql = "UPDATE Perito SET Nombres = ?, Apellidos = ?, LugarTrabajo = ? WHERE IDPerito = ?";
        try (Connection conn = dbConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, perito.getNombres());
            stmt.setString(2, perito.getApellidos());
            stmt.setString(3, perito.getLugarTrabajo());
            stmt.setInt(4, perito.getIdPerito());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarPerito(int idPerito) {
        String sql = "DELETE FROM Perito WHERE IDPerito = ?";
        try (Connection conn = dbConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idPerito);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Perito> obtenerPeritos() {
        List<Perito> peritos = new ArrayList<>();
        String sql = "SELECT * FROM Perito";
        try (Connection conn = dbConnection.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Perito perito = new Perito(
                        rs.getInt("IDPerito"),
                        rs.getString("Nombres"),
                        rs.getString("Apellidos"),
                        rs.getString("LugarTrabajo")
                );
                peritos.add(perito);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return peritos;
    }

    public List<Perito> buscarPeritoPorApellido(String apellido) {
        List<Perito> peritos = new ArrayList<>();
        String sql = "SELECT * FROM Perito WHERE Apellidos LIKE ?";
        try (Connection conn = dbConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + apellido + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Perito perito = new Perito(
                            rs.getInt("IDPerito"),
                            rs.getString("Nombres"),
                            rs.getString("Apellidos"),
                            rs.getString("LugarTrabajo")
                    );
                    peritos.add(perito);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return peritos;
    }
}
