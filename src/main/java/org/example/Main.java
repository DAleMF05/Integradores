package org.example;


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
        dbMySQL.closeConnection();
        dbMySQL.leerClientes();
        dbMySQL.leerProductos();
        dbMySQL.leerFacturas();
        dbMySQL.leerFacturasProductos();

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


        List<Cliente> clientes = cliente.selectList();

        for (Cliente c : clientes){
            c.toString();
        }


        System.out.println("////////////////////////////////////////////");
        System.out.println("////////////////////////////////////////////");

//        System.out.println("Lista de direcciones: ");

//        List<Direccion> listadoDirecciones = direccion.selectList();
//        for (Direccion dir : listadoDirecciones) {
//            System.out.println(dir);
//        }

        System.out.println("////////////////////////////////////////////");
        System.out.println("////////////////////////////////////////////");

//        Persona p = new Persona(6,"Sergio",50,2);
//        persona.insertPersona(p);

//        ClienteDTO personaDTO = cliente.getClienteDTO(2);
//        System.out.println(personaDTO);
    }
}