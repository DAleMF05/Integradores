package org.example.dto;

import lombok.*;

/**
 * \brief DTO de Cliente
 *
 * Representa una vista personalizada de la entidad
 * Cliente, utilizada para transportar datos.
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO {

    /** Identificador único del cliente. */
    private int idCliente;

    /** Nombre del cliente. */
    private String nombre;

    /** Total facturado por el cliente. */
    private Float total;
}
