package org.example;

import org.example.dto.ClienteDTO;
import org.example.dto.ProductoDTO;
import org.example.entities.*;
import org.example.dao.*;
import org.example.factory.AbstractFactory;
import org.example.utils.HelperMySQL;

import java.sql.SQLException;
import java.util.List;

/**
 * \brief Clase principal del sistema.
 *
 * Se encarga de inicializar la base de datos, crear las tablas,
 * cargar los datos desde archivos CSV y ejecutar distintas consultas
 * utilizando los DAOs.
 */
public class Main {
    /**
     * \brief Método principal de ejecución.
     *
     * Realiza las siguientes operaciones:
     * - Inicializa la base de datos.
     * - Crea y elimina tablas.
     * - Carga datos desde archivos CSV.
     * - Obtiene instancias de los DAOs mediante la fábrica.
     * - Ejecuta consultas de prueba:
     *   - Buscar cliente por ID.
     *   - Obtener el producto con mayor recaudación.
     *   - Listar clientes ordenados por total facturado.
     *
     * @param args Argumentos de línea de comandos (no utilizados).
     * @throws SQLException En caso de error en la base de datos.
     */
    public static void main(String[] args) throws SQLException {

        // Inicialización de base de datos.
        HelperMySQL dbMySQL = new HelperMySQL();
        dbMySQL.dropTables();
        dbMySQL.createTables();
        dbMySQL.leerClientes();
        dbMySQL.leerProductos();
        dbMySQL.leerFacturas();
        dbMySQL.leerFacturasProductos();
        dbMySQL.closeConnection();

        // Obtención de fábrica de DAOs
        AbstractFactory chosenFactory = AbstractFactory.getDAOFactory(1);

        ClienteDAO cliente = chosenFactory.getClienteDAO();
        ProductoDAO producto = chosenFactory.getProductoDAO();
        FacturaDAO factura = chosenFactory.getFacturaDAO();
        Factura_ProductoDAO facturaProducto = chosenFactory.getFactura_ProductoDAO();

        System.out.println("////////////////////////////////////////////");
        System.out.println("////////////////////////////////////////////");

        // Buscar cliente por ID
        System.out.println("Busco un cliente por id: ");
        Cliente clienteById = cliente.getCliente(2);
        System.out.println(clienteById);

        System.out.println("////////////////////////////////////////////");
        System.out.println("////////////////////////////////////////////");

        // Producto con mayor recaudación
        System.out.println("Producto que mas recaudó : ");
        ProductoDTO productoRec = producto.getProductoMasRecaudo();
        System.out.println(productoRec.toString());

        System.out.println("////////////////////////////////////////////");
        System.out.println("////////////////////////////////////////////");

        // Clientes ordenados por total facturado
        System.out.println("Lista de clientes que mas facturaron ordenada: ");
        List<ClienteDTO> listaClientes = cliente.getClientesOrdFactura();
        for (ClienteDTO c : listaClientes) {
            System.out.println(c.toString());
        }

        System.out.println("////////////////////////////////////////////");
        System.out.println("////////////////////////////////////////////");

    }
}