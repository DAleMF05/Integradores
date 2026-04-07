package org.example.entities;
import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class Producto {
    private int idProducto;
    private String nombre;
    private Float valor;


}
