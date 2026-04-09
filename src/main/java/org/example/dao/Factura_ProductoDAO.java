package org.example.dao;
import org.example.entities.Factura_Producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lombok.*;

@AllArgsConstructor
public class Factura_ProductoDAO {

    private Connection conn;

    public void insertFacturaProducto(Factura_Producto fp) {
        String query = "INSERT INTO Factura_Producto (idFactura, idProducto, cantidad) VALUES (?, ?, ?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, fp.getIdFactura());
            ps.setInt(2, fp.getIdProducto());
            ps.setInt(3, fp.getCantidad());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                conn.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Factura_Producto getFacturaProducto(Integer pk1, Integer pk2) {
        String query = "SELECT  fp.idFactura, fp.idProducto " +
                "FROM Factura_Producto fp " +
                "WHERE fp.idFactura = ? AND fp.idProducto = ?";
        Factura_Producto facturaProductoById = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, pk1);
            ps.setInt(2, pk2);
            rs = ps.executeQuery();
            if (rs.next()) {
                int idFactura = rs.getInt("idFactura");
                int idProducto = rs.getInt("idProducto");
                int cantidad = rs.getInt("cantidad");

                facturaProductoById = new Factura_Producto(pk1, pk2, cantidad);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                conn.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return facturaProductoById;
        }
    }

    public List<Factura_Producto> selectList() {
        String query = "SELECT * " +
                "FROM Factura_Producto ";
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Factura_Producto> listado = null;
        try {
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            // Crear una nueva instancia de Factura_Producto con los datos recuperados de la consulta
            listado = new ArrayList<Factura_Producto>();
            while (rs.next()) { // Verificar si hay resultados
                int idFactura = rs.getInt("idFactura");
                int idProducto = rs.getInt("idProducto");
                int cantidad = rs.getInt("cantidad");

                Factura_Producto facturaProducto= new Factura_Producto(idFactura, idProducto, cantidad);
                listado.add(facturaProducto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                conn.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return listado;
    }
}
