package org.example.logica;

public class Recorrido {
    private String origen,destino;
    public Recorrido(Recorridos ciudad1,Recorridos ciudad2){
        origen = ciudad1.toString();
        destino = ciudad2.toString();
    }
    public String getRecorrido(){
        return (origen+destino);
    }
}