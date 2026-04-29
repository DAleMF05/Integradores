package org.example.dto;
import lombok.*;

/**
 * \brief DTO de Factura_Producto
 *
 * Representa una vista personalizada sobre la relación
 * entre una factura y un producto, utilizada para transportar datos.
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Factura_ProductoDTO {

    /** Cantidad de unidades del producto en la factura. */
    private int cantidad;

    /** Identificador de la factura. */
    private int IdFactura;

    /** Identificador del producto. */
    private int IdProducto;
}