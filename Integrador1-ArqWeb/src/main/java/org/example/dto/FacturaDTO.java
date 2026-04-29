package org.example.dto;
import lombok.*;

/**
 * \brief DTO de Factura
 *
 * Representa una vista personalizada de la entidad
 * Factura, utilizada para transportar datos.
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FacturaDTO {

    /** Identificador único de la factura. */
    private int idFactura;

    /** Identificador del cliente asociado. */
    private int idCliente;
}
