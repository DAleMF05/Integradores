package org.example.dto;
import lombok.*;

/**
 * \brief DTO de Producto
 *
 * Representa una vista personalizada de la entidad
 * Producto, utilizada para transportar datos.
 */
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ProductoDTO {

    /** Identificador único del producto. */
    private int idProducto;

    /** Nombre del producto. */
    private String nombre;

    /** Valor unitario del producto. */
    private Float valor;

    /** Total recaudado por el producto. */
    private Float recaudado;
}
