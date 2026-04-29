package org.example.entities;

import lombok.*;

/**
 * \brief Representa la relación entre una factura y un producto.
 *
 * Permite registrar qué productos pertenecen a una factura
 * y la cantidad de cada uno.
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Factura_Producto {

    private int idFactura; /// Identificador de la factura.
    private int idProducto; /// Identificador del producto.
    private int cantidad; /// Cantidad del producto en la factura.
}