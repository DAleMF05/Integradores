package org.example.factory;
import org.example.dao.*;

/**
 * \brief Fábrica abstracta de DAOs
 *
 * Define la interfaz para la creación de objetos DAO sin especificar su implementación.
 *
 * Permite desacoplar la lógica de acceso a datos del resto del sistema,
 * facilitando el cambio de tecnología de persistencia (MySQL, Derby...).
 */

public abstract class AbstractFactory {

    /** Identificador para la implementación MySQL*/
    public static final int MYSQL_JDBC = 1;

    /** Identificador para la implementación Derby*/
    public static final int DERBY_JDBC = 2;


    /**
     * \brief Obtiene el DAO de Cliente.
     * @return Implementación concreta de ClientDAO.
     */
    public abstract ClienteDAO getClienteDAO();

    /**
     * \brief Obtiene el DAO de producto.
     * @return Implementación concreta de ProductoDAO.
     */
    public abstract ProductoDAO getProductoDAO();

    /**
     * \brief Obtiene el DAO de Factura.
     * @return Implementación concreta de FacturaDAO.
     */
    public abstract FacturaDAO getFacturaDAO();

    /**
     * \brief Obtiene el DAO de Factura_Producto.
     * @return Implementación concreta de Factura_ProductoDAO.
     */
    public abstract Factura_ProductoDAO getFactura_ProductoDAO();

    /**
     * \brief Retorna una fábrica concreta según el tipo indicado.
     *
     * Permite seleccionar dinámicamente la implementación de acceso a datos
     * sin dirigir todo el código hacia una base específica.
     *
     * @param whichFactory [in] Identificador de la fábrica a utilizar.
     * @return Instancia de una fábrica concreta o null si el parámetro no es válido.
     */
    public static AbstractFactory getDAOFactory(int whichFactory) {
        switch (whichFactory) {
            case MYSQL_JDBC : {
                return MySQLDAOFactory.getInstance();
            }
            case DERBY_JDBC: return null;
            default: return null;
        }
    }

}
