package org.example.utils;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.example.entities.*;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


/**
 * \brief Clase Helper de MYSQL
 * Esta clase administra la conexión a la base de datos con MYSQL
 */

public class HelperMySQL {
    private Connection conn = null; /// Variable donde se guarda la conexión utilizando la interfaz Connection


    /**
     *  \brief Constructor del Helper
     *  Carga el driver de MYSQL y crea la conexión a la bd
     */
    public HelperMySQL() {
        String driver = "com.mysql.cj.jdbc.Driver";
        String uri = "jdbc:mysql://localhost:3306/integrador1";

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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * \brief Cierra la conexión a la base de datos.
     */
    public void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * \brief Elimina las tablas existentes en la base de datos.
     * @throws SQLException
     */
    public void dropTables() throws SQLException {

        String dropFactura_Producto = "DROP TABLE IF EXISTS Factura_Producto";
        this.conn.prepareStatement(dropFactura_Producto).execute();
        this.conn.commit();

        String dropProducto = "DROP TABLE IF EXISTS Producto";
        this.conn.prepareStatement(dropProducto).execute();
        this.conn.commit();

        String dropFactura = "DROP TABLE IF EXISTS Factura";
        this.conn.prepareStatement(dropFactura).execute();
        this.conn.commit();

        String dropCliente = "DROP TABLE IF EXISTS Cliente";
        this.conn.prepareStatement(dropCliente).execute();
        this.conn.commit();

    }

    /**
     * \brief crea las tablas en la base de datos.
     * @throws SQLException
     */
    public void createTables() throws SQLException {
        String tableCliente = "CREATE TABLE IF NOT EXISTS Cliente(" +
                "idCliente INT NOT NULL, " +
                "nombre VARCHAR(500), " +
                "email VARCHAR(150), " +
                "CONSTRAINT Cliente_pk PRIMARY KEY (idCliente));";
        this.conn.prepareStatement(tableCliente).execute();
        this.conn.commit();

        String tableProducto = "CREATE TABLE IF NOT EXISTS Producto(" +
                "idProducto INT NOT NULL, " +
                "nombre VARCHAR(45) NOT NULL, " +
                "valor FLOAT NOT NULL, " +
                "CONSTRAINT Producto_pk PRIMARY KEY (idProducto))";
        this.conn.prepareStatement(tableProducto).execute();
        this.conn.commit();

        String tableFactura = "CREATE TABLE IF NOT EXISTS Factura(" +
                "idFactura INT NOT NULL, " +
                "idCliente INT NOT NULL, " +
                "CONSTRAINT Factura_pk PRIMARY KEY (idFactura), " +
                "CONSTRAINT FK_idCliente FOREIGN KEY (idCliente) REFERENCES Cliente (idCliente))";
        this.conn.prepareStatement(tableFactura).execute();
        this.conn.commit();

        String tableFactura_Producto = "CREATE TABLE IF NOT EXISTS Factura_Producto(" +
                "idFactura INT NOT NULL, " +
                "idProducto INT NOT NULL, " +
                "cantidad INT NOT NULL, " +
                "CONSTRAINT Factura_Producto_pk PRIMARY KEY (idFactura, idProducto), " +
                "CONSTRAINT FK_idFactura FOREIGN KEY (idFactura) REFERENCES Factura(idFactura), " +
                "CONSTRAINT FK_idProducto FOREIGN KEY (idProducto) REFERENCES Producto(idProducto)" +
                ")";
        this.conn.prepareStatement(tableFactura_Producto).execute();
        this.conn.commit();
    }

    /**
     * \brief Inserta un cliente en la base de datos.
     * @param cliente [in] datos a insertar de la clase Cliente.
     * @param conn [in] conexión a la base de datos.
     * @return un 0.
     * @throws Exception
     */
    private int insertCliente(Cliente cliente, Connection conn) throws Exception {
        String insert = "INSERT INTO Cliente (idCliente, nombre, email) VALUES (?, ?, ?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(insert);
            ps.setInt(1, cliente.getIdCliente());
            ps.setString(2, cliente.getNombre());
            ps.setString(3, cliente.getEmail());
            if (ps.executeUpdate() == 0) {
                throw new Exception("No se pudo insertar el Cliente");
            }
       }catch (SQLException e) {
                throw new RuntimeException("Error insertando cliente", e);

        } finally {
            closePsAndCommit(conn, ps);
        }
        return 0;
    }


    /**
     * \brief Inserta un producto en la base de datos.
     * @param producto [in] datos a insertar de la clase Producto.
     * @param conn [in] conexión a la base de datos.
     * @return un 0.
     * @throws Exception
     */
    private int insertProducto(Producto producto, Connection conn) throws Exception {

        String insert = "INSERT INTO Producto (idProducto, nombre, valor) VALUES (?, ?, ?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(insert);
            ps.setInt(1, producto.getIdProducto());
            ps.setString(2, producto.getNombre());
            ps.setFloat(3, producto.getValor());
            if (ps.executeUpdate() == 0) {
                throw new Exception("No se pudo insertar");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closePsAndCommit(conn, ps);
        }
        return 0;
    }


    /**
     * \brief Inserta una factura en la base de datos.
     * @param factura [in] datos a insertar de la clase Factura.
     * @param conn [in] conexión a la base de datos.
     * @return un 0.
     * @throws Exception
     */
    private int insertFactura(Factura factura, Connection conn) throws Exception {

        String insert = "INSERT INTO Factura (idFactura, idCliente) VALUES (?, ?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(insert);
            ps.setInt(1, factura.getIdFactura());
            ps.setInt(2, factura.getIdCliente());
            if (ps.executeUpdate() == 0) {
                throw new Exception("No se pudo insertar");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closePsAndCommit(conn, ps);
        }
        return 0;
    }


    /**
     * \brief Inserta una factura_producto en la base de datos.
     * @param fp [in] datos a insertar de la clase Factura_producto.
     * @param conn [in] conexión a la base de datos.
     * @return un 0.
     * @throws Exception
     */
    private int insertFacturaProducto(Factura_Producto fp, Connection conn) throws Exception {

        String insert = "INSERT INTO Factura_Producto (idFactura, idProducto, cantidad) VALUES (?, ?, ?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(insert);
            ps.setInt(1, fp.getIdFactura());
            ps.setInt(2, fp.getIdProducto());
            ps.setInt(3, fp.getCantidad());
            if (ps.executeUpdate() == 0) {
                throw new Exception("No se pudo insertar");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closePsAndCommit(conn, ps);
        }
        return 0;
    }


    /**
     * \brief Cierra el preparedStatement y guarda cambios en la base de datos.
     * @param conn [in] Conexión a la base de datos.
     * @param ps [in] Interfaz que utiliza para ejecutar consultas.
     */
    private void closePsAndCommit(Connection conn, PreparedStatement ps) {
        if (conn != null) {
            try {
                ps.close();
                conn.commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * \brief Lee archivo csv para cargar productos e insertarlos.
     */
    public void leerProductos() {
        try {
            InputStream input = getClass().getClassLoader().getResourceAsStream("productos.csv");

            if (input == null) {
                throw new RuntimeException("No se encontró el archivo");
            }

            Reader reader = new InputStreamReader(input);
            CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(reader);

            for (CSVRecord row : parser) {
                String idProd = row.get("idProducto");
                String nombre = row.get("nombre");
                String valor = row.get("valor");

                if (!idProd.isEmpty() && !nombre.isEmpty() && !valor.isEmpty()) {
                    try {
                        int id = Integer.parseInt(idProd);
                        float valorFloat = Float.parseFloat(valor);


                        Producto prod = new Producto(id, nombre, valorFloat);
                        insertProducto(prod, conn);
                    } catch (NumberFormatException e) {
                        System.err.println("Error de formato en datos de persona: " + e.getMessage());
                    }
                }

            }

            System.out.println("Productos leídos correctamente");

        } catch (Exception e) {
            System.out.println("Error leyendo productos");
            throw new RuntimeException(e);
        }
    }

    /**
     * \brief Lee archivo csv para cargar clientes e insertarlos.
     */
    public void leerClientes() {

        try {
            InputStream input = getClass().getClassLoader().getResourceAsStream("clientes.csv");

            if (input == null) {
                throw new RuntimeException("No se encontró el archivo");
            }

            Reader reader = new InputStreamReader(input);
            CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(reader);

            for (CSVRecord row : parser) {
                String idString = row.get("idCliente");
                String nombreString = row.get("nombre");
                String emailString = row.get("email");

                if (!idString.isEmpty() && !nombreString.isEmpty() && !emailString.isEmpty()) {
                    try {
                        int id = Integer.parseInt(idString);


                        Cliente cliente = new Cliente(id, nombreString, emailString);
                        insertCliente(cliente, conn);
                    } catch (NumberFormatException e) {
                        System.err.println("Error de formato en datos de persona: " + e.getMessage());
                    }
                }
}
                System.out.println("Clientes leídos correctamente");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * \brief Lee archivo csv para cargar facturas e insertarlas.
     */
    public void leerFacturas(){
        try {
            InputStream input = getClass().getClassLoader().getResourceAsStream("facturas.csv");

            if (input == null) {
                throw new RuntimeException("No se encontró el archivo");
            }

            Reader reader = new InputStreamReader(input);
            CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(reader);

            for (CSVRecord row : parser) {
                String idFact = row.get("idFactura");
                String idCli = row.get("idCliente");

                if (!idFact.isEmpty() && !idCli.isEmpty()) {
                    try {
                        int idF = Integer.parseInt(idFact);
                        int idC = Integer.parseInt(idCli);


                        Factura fact = new Factura(idF, idC);
                        insertFactura(fact, conn);
                    } catch (NumberFormatException e) {
                        System.err.println("Error de formato en datos de persona: " + e.getMessage());
                    }
                }


            }

            System.out.println("facturas leídas correctamente");
        } catch (Exception e) {
            throw new RuntimeException(e);

        }

    }

    /**
     * \brief Lee archivo csv para cargar facturas_productos e insertarlas.
     */
    public void leerFacturasProductos() {

        try {
            InputStream input = getClass().getClassLoader().getResourceAsStream("facturas-productos.csv");

            if (input == null) {
                throw new RuntimeException("No se encontró el archivo");
            }

            Reader reader = new InputStreamReader(input);
            CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(reader);

            for (CSVRecord row : parser) {
                String idFact = row.get("idFactura");
                String idProd = row.get("idProducto");
                String cantidad = row.get("cantidad");

                if (!idFact.isEmpty() && !idProd.isEmpty() && !cantidad.isEmpty()) {
                    try {
                        int idF = Integer.parseInt(idFact);
                        int idP = Integer.parseInt(idProd);
                        int cantidadP = Integer.parseInt(cantidad);


                        Factura_Producto factprod =  new Factura_Producto(idF, idP, cantidadP);
                        insertFacturaProducto(factprod, conn);
                    } catch (NumberFormatException e) {
                        System.err.println("Error de formato en datos de persona: " + e.getMessage());
                    }
                }

            }

            System.out.println("facturas-productos leídos correctamente");

        } catch (Exception e) {
            System.out.println("Error leyendo fact-prod");
            throw new RuntimeException(e);
        }

    }
}
