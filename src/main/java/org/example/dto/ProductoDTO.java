package org.example.dto;
import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class ProductoDTO {
    private int idProducto;
    private String nombre;
    private Float valor;
    private Float recaudado;
}
