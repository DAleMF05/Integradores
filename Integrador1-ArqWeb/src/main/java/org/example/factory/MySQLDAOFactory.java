package org.example.factory;

import org.example.dao.*;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * \brief Implementación concreta de la fábrica para MySQL.
 *
 * Crea los DAOs específicos para MySQL, así como tambíen gestiona
 * la conexión a la base de datos.
 *
 * Implementa el patrón singleton para asegurar una única instancia
 * de la fábrica durante la ejecución.
 */

public class MySQLDAOFactory extends AbstractFactory {

    /** Instancia única de la fábrica. */
    private static MySQLDAOFactory instance = null;

    /** Driver de MySQL. */
    public static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    /** URI de conexión a la base de datos. */
    public static final String uri = "jdbc:mysql://localhost:3306/integrador1";

    /** Conexión a la base de datos. */
    public static Connection conn;

    /**
     * \brief Constructor vacío privado.
     *
     * Se utiliza para implementar singleton.
     */
    private MySQLDAOFactory() {
    }

    /**
     * \brief Obtiene la instancia única de la fábrica.
     * @return Instancia de MySQLDAOFactory.
     */
    public static synchronized MySQLDAOFactory getInstance() {
        if (instance == null) {
            instance = new MySQLDAOFactory();
        }
        return instance;
    }


    /**
     * \brief Crea o retorna una conexión existente a la base de datos.
     * @return Conexión a la base de datos.
     */
    public static Connection createConnection() {
        if (conn != null) {
            return conn;
        }
        String driver = DRIVER;
        try {
            Class.forName(driver).getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                 | NoSuchMethodException | SecurityException | ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }

        try {
            conn = DriverManager.getConnection(uri, "root", "");
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * \brief Cierra la conexión a la base de datos.
     */
    public void closeConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * \brief Obtiene el DAO de Cliente.
     * @return Instancia de ClienteDAO.
     */
    @Override
    public ClienteDAO getClienteDAO() {
        return new ClienteDAO(createConnection());
    }

    /**
     * \brief Obtiene el DAO de Producto.
     * @return Instancia de ProductoDAO.
     */
    @Override
    public ProductoDAO getProductoDAO() {
        return new ProductoDAO(createConnection());
    }

    /**
     * \brief Obtiene el DAO de Factura.
     * @return Instancia de FacturaDAO.
     */
    @Override
    public FacturaDAO getFacturaDAO() {
        return new FacturaDAO(createConnection());
    }

    /**
     * \brief Obtiene el DAO de Factura_Producto.
     * @return Instancia de Factura_ProductoDAO.
     */
    @Override
    public Factura_ProductoDAO getFactura_ProductoDAO(){
        return new Factura_ProductoDAO(createConnection());
    }


}
