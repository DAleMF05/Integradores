package org.example.entities;

import lombok.*;

/**
 * \brief Representa un cliente dentro del sistema.
 *
 * Un cliente puede realizar compras que se registran mediante facturas.
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {

    private int idCliente; /// Identificador único del cliente.
    private String nombre; /// Nombre del cliente.
    private String email; /// Correo electrónico del cliente.
}