package org.example.dao;

import org.example.dto.ProductoDTO;
import org.example.entities.Producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lombok.*;

@AllArgsConstructor
public class ProductoDAO {
    private Connection conn;

    public void insertProducto(Producto prod) {
        String query = "INSERT INTO Producto (idProducto, nombre, valor) VALUES (?, ?, ?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1,prod.getIdProducto());
            ps.setString(2, prod.getNombre());
            ps.setFloat(3,prod.getValor());
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

    public Producto getProducto(Integer pk) {
        String query = "SELECT p.nombre, p.valor " +
                "FROM Producto p " +
                "WHERE p.idProducto = ?";
        Producto productoById = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, pk);
            rs = ps.executeQuery();
            if (rs.next()) { // Verificar si hay resultados
                String nombre = rs.getString("nombre");
                Float valor = rs.getFloat("valor");


                productoById = new Producto(pk, nombre, valor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                conn.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return productoById;
    }

    public boolean delete(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    public List<Producto> selectList() {
        String query = "SELECT * " +
                "FROM Producto ";
        PreparedStatement ps = null;
        ResultSet rs = null;
        List <Producto> listado = null;
        try {
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            // Crear una nueva instancia de Producto con los datos recuperados de la consulta
            listado = new ArrayList<Producto>();
            while (rs.next()) { // Verificar si hay resultados
                int idProducto = rs.getInt("idProducto");
                String nombre= rs.getString("nombre");
                Float valor = rs.getFloat("valor");
                Producto producto = new Producto(idProducto, nombre, valor);
                listado.add(producto);
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

    public ProductoDTO getProductoMasRecaudo(){
        String query = "SELECT p.idProducto, p.nombre, p.valor, SUM(fp.cantidad * p.valor) AS total " +
                "FROM Producto p " +
                "JOIN Factura_Producto fp ON p.idProducto = fp.idProducto " +
                "GROUP BY p.idProducto, p.nombre " +
                "ORDER BY total DESC " +
                "LIMIT 1 ";

        ProductoDTO productoRec = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            if (rs.next()) { // Verificar si hay resultados
                int idProducto = rs.getInt("p.idProducto");
                String nombre = rs.getString("p.nombre");
                Float valor = rs.getFloat("valor");
                Float recaudado = rs.getFloat("total");

                productoRec = new ProductoDTO(idProducto, nombre, valor, recaudado);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                conn.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return productoRec;


    }
}
