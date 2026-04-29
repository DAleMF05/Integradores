package org.example.dao;

import org.example.dto.ClienteDTO;
import org.example.entities.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lombok.*;

/**
 * \brief DAO de Cliente.
 *
 * Gestioona las operaciones de acceso a datos
 * relacionadas con la entidad Cliente en la base de datos.
 */
@AllArgsConstructor
public class ClienteDAO {

    /** Conexión a la base de datos. */
    private Connection conn;

    /**
     * \brief Inserta un cliente en la base de datos.
     * @param cliente [in] Cliente a insertar.
     */
    public void insertCliente(Cliente cliente) {
        String query = "INSERT INTO Cliente (idCliente, nombre, email) VALUES (?, ?, ?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, cliente.getIdCliente());
            ps.setString(2, cliente.getNombre());
            ps.setString(3, cliente.getEmail());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) ps.close();
                conn.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * \brief Obtiene un cliente por su ID.
     * @param pk [in] Identificador único del cliente.
     * @return Cliente encontrado o null si no existe.
     */
    public Cliente getCliente(Integer pk) {
        String query = "SELECT c.nombre, c.email FROM Cliente c WHERE c.idCliente = ?";
        Cliente clienteById = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, pk);
            rs = ps.executeQuery();

            if (rs.next()) {
                String nombre = rs.getString("nombre");
                String email = rs.getString("email");
                clienteById = new Cliente(pk, nombre, email);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) ps.close();
                conn.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return clienteById;
    }

    /**
     * \brief Obtiene todos los clientes de la base de datos.
     * @return Lista de clientes.
     */
    public List<Cliente> selectList() {
        String query = "SELECT * FROM Cliente";
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Cliente> listado = new ArrayList<>();

        try {
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                int idCliente = rs.getInt("idCliente");
                String nombre = rs.getString("nombre");
                String email = rs.getString("email");

                Cliente cliente = new Cliente(idCliente, nombre, email);
                listado.add(cliente);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                conn.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return listado;
    }

    /**
     * \brief Obtiene una lista de clientes ordenados por el total facturado.
     *
     * El total se calcula como la suma de (cantidad * valor) del producto.
     *
     * @return Lista de ClienteDTO ordenada de mayor a menor facturación.
     */
    public List<ClienteDTO> getClientesOrdFactura() {
        String query = "SELECT c.idCliente, c.nombre, SUM(fp.cantidad * p.valor) AS total " +
                "FROM Cliente c " +
                "JOIN Factura f ON c.idCliente = f.idCliente " +
                "JOIN Factura_Producto fp ON f.idFactura = fp.idFactura " +
                "JOIN Producto p ON fp.idProducto = p.idProducto " +
                "GROUP BY c.idCliente, c.nombre " +
                "ORDER BY total DESC";

        List<ClienteDTO> listaC = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                int idCliente = rs.getInt("idCliente");
                String nombre = rs.getString("nombre");
                Float total = rs.getFloat("total");

                ClienteDTO cliente = new ClienteDTO(idCliente, nombre, total);
                listaC.add(cliente);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) ps.close();
                conn.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return listaC;
    }
}
