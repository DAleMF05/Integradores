package org.example.entities;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {
    private  int idCliente;
    private String nombre;
    private String email;

    public Cliente(int idCliente, String nombre){
        this.idCliente = idCliente;
        this.nombre = nombre;
    }
}
