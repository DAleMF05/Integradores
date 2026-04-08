package org.example.entities;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class Factura_Producto {
    private int IdFactura;
    private int IdProducto;
    private int cantidad;

}
