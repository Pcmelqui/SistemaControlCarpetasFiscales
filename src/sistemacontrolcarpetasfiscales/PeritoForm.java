/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemacontrolcarpetasfiscales;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class PeritoForm extends JFrame {
    private JTextField txtNombres, txtApellidos, txtLugarTrabajo, txtBuscarApellido;
    private JButton btnNuevo, btnGuardar, btnModificar, btnEliminar, btnBuscar;
    private JTable tablePeritos;
    private DefaultTableModel model;
    private PeritoDAO peritoDAO;

    public PeritoForm() {
        peritoDAO = new PeritoDAO();
        setTitle("Gestión de Peritos");
        setLayout(new BorderLayout());

        // Panel de entrada de datos
        JPanel panelDatos = new JPanel(new GridLayout(4, 2));
        panelDatos.add(new JLabel("Nombres:"));
        txtNombres = new JTextField();
        panelDatos.add(txtNombres);

        panelDatos.add(new JLabel("Apellidos:"));
        txtApellidos = new JTextField();
        panelDatos.add(txtApellidos);

        panelDatos.add(new JLabel("Lugar de Trabajo:"));
        txtLugarTrabajo = new JTextField();
        panelDatos.add(txtLugarTrabajo);

        panelDatos.add(new JLabel("Buscar por Apellido:"));
        txtBuscarApellido = new JTextField();
        panelDatos.add(txtBuscarApellido);

        // Panel de botones
        JPanel panelBotones = new JPanel();
        btnNuevo = new JButton("Nuevo");
        btnGuardar = new JButton("Guardar");
        btnModificar = new JButton("Modificar");
        btnEliminar = new JButton("Eliminar");
        btnBuscar = new JButton("Buscar");

        panelBotones.add(btnNuevo);
        panelBotones.add(btnGuardar);
        panelBotones.add(btnModificar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnBuscar);

        // Tabla
        model = new DefaultTableModel(new String[]{"ID", "Nombres", "Apellidos", "Lugar de Trabajo"}, 0);
        tablePeritos = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(tablePeritos);

        // Agregar componentes a la ventana
        add(panelDatos, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        // Eventos de los botones
        btnNuevo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtNombres.setText("");
                txtApellidos.setText("");
                txtLugarTrabajo.setText("");
            }
        });

        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Perito perito = new Perito(0, txtNombres.getText(), txtApellidos.getText(), txtLugarTrabajo.getText());
                peritoDAO.agregarPerito(perito);
                cargarPeritos();
            }
        });

        btnModificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tablePeritos.getSelectedRow();
                if (selectedRow != -1) {
                    int idPerito = (int) model.getValueAt(selectedRow, 0);
                    Perito perito = new Perito(idPerito, txtNombres.getText(), txtApellidos.getText(), txtLugarTrabajo.getText());
                    peritoDAO.modificarPerito(perito);
                    cargarPeritos();
                }
            }
        });

        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tablePeritos.getSelectedRow();
                if (selectedRow != -1) {
                    int idPerito = (int) model.getValueAt(selectedRow, 0);
                    peritoDAO.eliminarPerito(idPerito);
                    cargarPeritos();
                }
            }
        });

        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String apellido = txtBuscarApellido.getText();
                List<Perito> peritos = peritoDAO.buscarPeritoPorApellido(apellido);
                model.setRowCount(0);
                for (Perito perito : peritos) {
                    model.addRow(new Object[]{perito.getIdPerito(), perito.getNombres(), perito.getApellidos(), perito.getLugarTrabajo()});
                }
            }
        });

        // Configurar la ventana
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        cargarPeritos();
        setVisible(true);
    }

    private void cargarPeritos() {
        List<Perito> peritos = peritoDAO.obtenerPeritos();
        model.setRowCount(0);
        for (Perito perito : peritos) {
            model.addRow(new Object[]{perito.getIdPerito(), perito.getNombres(), perito.getApellidos(), perito.getLugarTrabajo()});
        }
    }

    public static void main(String[] args) {
        new PeritoForm();
    }
}
