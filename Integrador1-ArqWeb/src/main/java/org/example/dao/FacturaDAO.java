package org.example.dao;

import org.example.entities.Factura;
import org.example.entities.Producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lombok.*;

/**
 * \brief DAO de Factura
 *
 * Gestiona las operaciones de acceso a datos relacionadas
 * a la entidad Factura en la base de datos.
 */

@AllArgsConstructor
public class FacturaDAO {

    /** Conexión a la base de datos */
    private Connection conn;


    /**
     * \brief Inserta una factura en la base de datos.
     * @param fact [in] factura a insertar.
     */

    public void insertFactura(Factura fact) {
        String query = "INSERT INTO Factura (idFactura, idCliente) VALUES (?, ?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1,fact.getIdFactura());
            ps.setInt(2, fact.getIdCliente());
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
     * \brief Obtiene una factura por su ID.
     * @param pk Identificador único de la factura.
     * @return Factura encontrada o null si no existe.
     */
    public Factura getFactura(Integer pk) {
        String query = "SELECT  f.idCliente " +
                "FROM Factura f " +
                "WHERE f.idFactura = ?";
        Factura facturaById = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, pk);
            rs = ps.executeQuery();
            if (rs.next()) {
                int idCliente = rs.getInt("idCliente");


               facturaById = new Factura(pk, idCliente);
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

        return facturaById;
    }

    /**
     * \brief Obtiene todas las facturas de la base de datos.
     * @return Lista de facturas.
     */
    public List<Factura> selectList() {
        String query = "SELECT * " +
                "FROM Factura ";
        PreparedStatement ps = null;
        ResultSet rs = null;
        List <Factura> listado = null;
        try {
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            // Crear una nueva instancia de Factura con los datos recuperados de la consulta
            listado = new ArrayList<Factura>();
            while (rs.next()) { // Verificar si hay resultados
                int idFactura = rs.getInt("idFactura");
                int idCliente = rs.getInt("idCliente");

                Factura factura= new Factura(idFactura, idCliente);
                listado.add(factura);
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
