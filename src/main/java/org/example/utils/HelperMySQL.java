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


public class HelperMySQL {
    private Connection conn = null;

    public HelperMySQL() {//Constructor
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
            conn = DriverManager.getConnection(uri, "user", "1234");
            conn.setAutoCommit(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        if (conn != null){
            try {
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
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

    public void createTables() throws SQLException {
        String tableCliente = "CREATE TABLE IF NOT EXISTS Cliente(" +
                "idCliente INT NOT NULL, " +
                "nombre VARCHAR(500), " +
                "email VARCHAR(150), " +
                "CONSTRAINT Cliente_pk PRIMARY KEY (idCliente));" ;
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
                "CONSTRAINT Factura_Producto_pk PRIMARY KEY (idFactura, idProducto), "+
                "CONSTRAINT FK_idFactura FOREIGN KEY (idFactura) REFERENCES Factura (idFactura))";
        this.conn.prepareStatement(tableFactura_Producto).execute();
        this.conn.commit();
    }

    private int insertCliente (Cliente cliente, Connection conn) throws Exception{
        String insert = "INSERT INTO Cliente (idCliente, nombre, email) VALUES (?, ?, ?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(insert);
            ps.setInt(1,cliente.getIdCliente());
            ps.setString(2, cliente.getNombre());
            ps.setString(3,cliente.getEmail());
            if (ps.executeUpdate() == 0) {
                throw new Exception("No se pudo insertar el Cliente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closePsAndCommit(conn, ps);
        }
        return 0;
    }

    private int insertProducto(Producto producto, Connection conn) throws Exception {

        String insert = "INSERT INTO Producto (idProducto, nombre, valor) VALUES (?, ?, ?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(insert);
            ps.setInt(1,producto.getIdProducto());
            ps.setString(2, producto.getNombre());
            ps.setFloat(3,producto.getValor());
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

    private int insertFactura(Factura factura, Connection conn) throws Exception {

        String insert = "INSERT INTO Factura (idFactura, idCliente) VALUES (?, ?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(insert);
            ps.setInt(1,factura.getIdFactura());
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


    private int insertFacturaProducto(Factura_Producto fp, Connection conn) throws Exception {

        String insert = "INSERT INTO Factura_Producto (idFactura, idProducto, cantidad) VALUES (?, ?, ?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(insert);
            ps.setInt(1,fp.getIdFactura());
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





    private void closePsAndCommit(Connection conn, PreparedStatement ps) {
        if (conn != null){
            try {
                ps.close();
                conn.commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public void leerProductos() {
        try {
            InputStream input = getClass().getClassLoader().getResourceAsStream("productos.csv");

            if (input == null) {
                throw new RuntimeException("No se encontró el archivo");
            }

            Reader reader = new InputStreamReader(input);
            CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(reader);

            for (CSVRecord row : parser) {
                System.out.println(row.get("idProducto"));
                System.out.println(row.get("nombre"));
                System.out.println(row.get("valor"));
            }

            System.out.println("Productos leídos correctamente");

        } catch (Exception e) {
            System.out.println("Error leyendo productos");
            e.printStackTrace();
        }
    }

    public void leerClientes(){

        try {
            InputStream input = getClass().getClassLoader().getResourceAsStream("clientes.csv");

            if (input == null) {
                throw new RuntimeException("No se encontró el archivo");
            }

            Reader reader = new InputStreamReader(input);
            CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(reader);

            for (CSVRecord row : parser) {
                System.out.println(row.get("idCliente"));
                System.out.println(row.get("nombre"));
                System.out.println(row.get("email"));
            }

            System.out.println("Clientes leídos correctamente");

        } catch (Exception e) {
            System.out.println("Error leyendo clientes");
            e.printStackTrace();
        }
    }

    public void leerFacturas(){
        try {
            InputStream input = getClass().getClassLoader().getResourceAsStream("facturas.csv");

            if (input == null) {
                throw new RuntimeException("No se encontró el archivo");
            }

            Reader reader = new InputStreamReader(input);
            CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(reader);

            for (CSVRecord row : parser) {
                System.out.println(row.get("idFactura"));
                System.out.println(row.get("idCliente"));
            }

            System.out.println("facturas leídas correctamente");

        } catch (Exception e) {
            System.out.println("Error leyendo facturas");
            e.printStackTrace();
        }

    }

    public void leerFacturasProductos() {

        try {
            InputStream input = getClass().getClassLoader().getResourceAsStream("facturas-productos.csv");

            if (input == null) {
                throw new RuntimeException("No se encontró el archivo");
            }

            Reader reader = new InputStreamReader(input);
            CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(reader);

            for (CSVRecord row : parser) {
                System.out.println(row.get("idFactura"));
                System.out.println(row.get("idProducto"));
                System.out.println(row.get("cantidad"));
            }

            System.out.println("facturas-productos leídos correctamente");

        } catch (Exception e) {
            System.out.println("Error leyendo fact-prod");
            e.printStackTrace();
        }

    }
}
