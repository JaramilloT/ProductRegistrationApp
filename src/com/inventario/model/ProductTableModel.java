package com.inventario.model;

import javax.swing.table.AbstractTableModel;
import java.util.List;
import java.util.ArrayList;

public abstract class  ProductTableModel extends AbstractTableModel {
    private final List<Product> productList;
    private final String[] columnNames = {"ID", "Nombre", "Precio", "Cantidad"};

    public ProductTableModel() {
        this.productList = new ArrayList<>();
    }

    // Implementa los métodos requeridos (getRowCount, getColumnCount, getValueAt)
    // Agrega el método addProduct
}