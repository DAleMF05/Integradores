package org.example.dao;
import org.example.entities.Factura_Producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lombok.*;

/**
 * \brief DAO de Factura_Producto
 *
 * Gestiona las operaciones de acceso a datos para la entidad Factura_Producto, que representa
 * la relación entre facturas y productos junto con la cantidad vendida.
 */

@AllArgsConstructor
public class Factura_ProductoDAO {

    /** Conexión a la base de datos. */
    private Connection conn;

    /**
     * \brief Inserta una relación entre factura y producto en la base de datos.
     * @param fp [in] Factura_Producto a insertar.
     */
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

    /**
     *\brief Obtiene una relación Factura-Producto por sus claves.
     * @param pk1 [in] Identificador único de la factura.
     * @param pk2 [in] Identificador único del producto.
     * @return Factura_Producto encontrada o null si no existe.
     */

    public Factura_Producto getFacturaProducto(Integer pk1, Integer pk2) {
        String query = "SELECT  fp.idFactura, fp.idProducto, fp.cantidad " +
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
                System.out.println(cantidad);

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
        }
        return facturaProductoById;
    }


    /**
     * \brief Obtiene todas las relaciones Factura_Producto de la base de datos.
     * @return Listado de todas las relaciones Factura_Producto.
     */
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
