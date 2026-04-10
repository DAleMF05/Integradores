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

@AllArgsConstructor
public class ClienteDAO {
    private Connection conn;


    public void insertCliente(Cliente cliente) {
        String query = "INSERT INTO Cliente (idCliente, nombre, email) VALUES (?, ?, ?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1,cliente.getIdCliente());
            ps.setString(2, cliente.getNombre());
            ps.setString(3,cliente.getEmail());
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

    public Cliente getCliente(Integer pk) {
        String query = "SELECT c.nombre, c.email " +
                "FROM Cliente c " +
                "WHERE c.idCliente = ?";
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

        return clienteById;
    }

    public ClienteDTO getClienteDTO(Integer pk) {
//        String query = "SELECT p.nombre, p.edad, d.ciudad, d.calle, d.numero " +
//                "FROM Persona p " +
//                "JOIN Direccion d ON p.idDireccion = d.idDireccion " +
//                "WHERE p.idPersona = ?";
       Cliente clienteById = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        ClienteDTO clienteDTO = null;
        try {
//            ps = conn.prepareStatement(query);
            ps.setInt(1, pk); // Establecer el parámetro en la consulta SQL
            rs = ps.executeQuery();
            if (rs.next()) { // Verificar si hay resultados
//                String nombre = rs.getString("nombre");
//                int edad = rs.getInt("edad");
//                String ciudad = rs.getString("ciudad");
//                String calle = rs.getString("calle");
//                int numero = rs.getInt("numero");

                // Crear una nueva instancia de PersonaDTO con los datos recuperados de la consulta
//                personaDTO = new PersonaDTO(nombre, edad, ciudad, calle, numero);
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

        return clienteDTO;
    }

    public boolean delete(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    public List<Cliente> selectList() {
        String query = "SELECT * " +
                "FROM Cliente ";
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Cliente> listado = null;
        try {
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            // Crear una nueva instancia de Cliente con los datos recuperados de la consulta
            listado = new ArrayList<Cliente>();
            while (rs.next()) { // Verificar si hay resultados
                int idCliente = rs.getInt("idCliente");
                String nombre= rs.getString("nombre");
                String email = rs.getString("email");
                Cliente cliente = new Cliente(idCliente, nombre, email);
                listado.add(cliente);
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

    public List<ClienteDTO> getClientesOrdFactura(){
        String query = "SELECT c.idCliente, c.nombre, SUM(fp.cantidad * p.valor) AS total " +
                "FROM Cliente c " +
                "JOIN Factura f ON c.idCliente = f.idCliente " +
                "JOIN Factura_Producto fp ON f.idFactura = fp.idFactura " +
                "JOIN Producto p ON fp.idProducto = p.idProducto " +
                "GROUP BY c.idCliente, c.nombre " +
                "ORDER BY total DESC";

        List<ClienteDTO> listaC = new ArrayList<ClienteDTO>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) { // Verificar si hay resultados
                int idCliente = rs.getInt("idCliente");
                String nombre = rs.getString("nombre");
                Float total = rs.getFloat("total");

                ClienteDTO cliente= new ClienteDTO(idCliente, nombre, total);
                listaC.add(cliente);
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

        return listaC;
    }

}

