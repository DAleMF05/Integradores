package org.example.entities;

import lombok.*;

/**
 * \brief Representa una factura dentro del sistema.
 *
 * Una factura registra una compra realizada por un cliente.
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Factura {

    private int idFactura; /// Identificador único de la factura.
    private int idCliente; /// Identificador del cliente asociado.
}
