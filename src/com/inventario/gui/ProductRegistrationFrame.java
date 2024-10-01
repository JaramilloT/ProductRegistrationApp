package com.inventario.gui;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.inventario.model.Product;

public class ProductRegistrationFrame extends JFrame {
    private JTextField nameField;
    private JTextField priceField;
    private JTextField quantityField;
    private JButton saveButton;
    private JTable productTable;
    private DefaultTableModel tableModel;

    public ProductRegistrationFrame() {
        setTitle("Registro de Productos");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        // Crear panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8, 3));

        // Crear campos de texto
        JLabel nameLabel = new JLabel("Nombre del Producto:");
        nameField = new JTextField();

        JLabel priceLabel = new JLabel("Precio:");
        priceField = new JTextField();

        JLabel quantityLabel = new JLabel("Cantidad:");
        quantityField = new JTextField();

        // Crear botón de guardar
        saveButton = new JButton("Guardar");
        saveButton.addActionListener(new SaveProductAction());

        // Añadir componentes al panel
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(priceLabel);
        panel.add(priceField);
        panel.add(quantityLabel);
        panel.add(quantityField);
        panel.add(new JLabel()); // Espacio vacío
        panel.add(saveButton);

        // Crear la tabla
        String[] columnNames = {"ID", "Nombre", "Precio", "Cantidad"};
        tableModel = new DefaultTableModel(columnNames, 0);
        productTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(productTable);

        // Añadir panel y tabla a la ventana
        add(panel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    private class SaveProductAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String name = nameField.getText();
                double price = Double.parseDouble(priceField.getText());
                int quantity = Integer.parseInt(quantityField.getText());

                if (name.isEmpty()) {
                    throw new IllegalArgumentException("El nombre no puede estar vacío.");
                }

                // Generar un ID simple
                int id = tableModel.getRowCount() + 1;
                Product product = new Product(id, name, price, quantity);

                // Agregar producto a la tabla
                tableModel.addRow(new Object[]{id, name, price, quantity});

                // Limpiar campos
                nameField.setText("");
                priceField.setText("");
                quantityField.setText("");

                JOptionPane.showMessageDialog(ProductRegistrationFrame.this, "Producto guardado exitosamente.");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(ProductRegistrationFrame.this, "Por favor, ingresa un precio o una cantidad válida.");
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(ProductRegistrationFrame.this, ex.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ProductRegistrationFrame().setVisible(true);
        });
    }
}