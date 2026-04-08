package org.example.dto;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class Factura_ProductoDTO {
    private int cantidad;
    private int IdFactura;
    private int IdProducto;

}
