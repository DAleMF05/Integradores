package org.example;

import org.example.dto.ClienteDTO;
import org.example.dto.ProductoDTO;
import org.example.entities.*;
import org.example.dao.*;
import org.example.factory.AbstractFactory;
import org.example.utils.HelperMySQL;

import java.sql.SQLException;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws SQLException {
        HelperMySQL dbMySQL = new HelperMySQL();
        dbMySQL.dropTables();
        dbMySQL.createTables();
       dbMySQL.leerClientes();
       dbMySQL.leerProductos();
        dbMySQL.leerFacturas();
       dbMySQL.leerFacturasProductos();
        dbMySQL.closeConnection();

        AbstractFactory chosenFactory = AbstractFactory.getDAOFactory(1);
        System.out.println();
        System.out.println("/////////////////////////////////////////   ///");

        System.out.println("////////////////////////////////////////////");
        System.out.println();
        ClienteDAO cliente = chosenFactory.getClienteDAO();
        ProductoDAO producto = chosenFactory.getProductoDAO();
        FacturaDAO factura = chosenFactory.getFacturaDAO();
        Factura_ProductoDAO facturaProducto = chosenFactory.getFactura_ProductoDAO();


     System.out.println("Busco un cliente por id: ");
        Cliente clienteById = cliente.getCliente(2);
        System.out.println(clienteById);




        System.out.println("////////////////////////////////////////////");
        System.out.println("////////////////////////////////////////////");

        System.out.println("Producto que mas recaudó : ");
        ProductoDTO productoRec = producto.getProductoMasRecaudo();
        System.out.println(productoRec.toString());

        System.out.println("////////////////////////////////////////////");
        System.out.println("////////////////////////////////////////////");

        System.out.println("Lista de clientes que mas facturaron ordenada: ");

//        List<Cliente> listaClientes = cliente.getClientesOrdFactura();
//        for (Cliente c : listaClientes) {
//            System.out.println(c);
//        }


        List<ClienteDTO> listaClientes = cliente.getClientesOrdFactura();
        for (ClienteDTO c : listaClientes) {
            System.out.println(c.toString());
        }

        System.out.println("////////////////////////////////////////////");
        System.out.println("////////////////////////////////////////////");


//        ClienteDTO clienteDTO = cliente.getClienteDTO(2);
//        System.out.println(clienteDTO);
    }
}