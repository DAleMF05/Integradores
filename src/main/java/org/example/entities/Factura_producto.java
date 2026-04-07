package org.example.entities;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class Factura_producto {
    private int cantidad;
    private int IdFactura;
    private int IdProducto;

}
