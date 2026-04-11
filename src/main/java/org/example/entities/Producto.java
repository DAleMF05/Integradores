package org.example.entities;

import lombok.*;

/**
 * \brief Representa un producto dentro del sistema.
 *
 * Un producto puede ser incluido en múltiples facturas
 * y tiene un valor que indica su precio.
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Producto {

    private int idProducto; /// Identificador único del producto.
    private String nombre; /// Nombre del producto.
    private Float valor; /// Precio del producto.
}