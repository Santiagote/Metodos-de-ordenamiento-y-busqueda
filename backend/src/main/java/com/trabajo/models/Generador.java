package com.trabajo.models;

public class Generador{
    private Integer id;
    private Double costeGenerador;
    private Double electricidadGenerada;


    public Generador(){
        
    }
//getters and setters

    public Integer getId() {
    	return this.id;
    }
    public void setId(Integer id) {
    	this.id = id;
    }

    public Double getCosteGenerador() {
    	return this.costeGenerador;
    }
    public void setCosteGenerador(Double costeGenerador) {
    	this.costeGenerador = costeGenerador;
    }

    public Double getElectricidadGenerada() {
    	return this.electricidadGenerada;
    }
    public void setElectricidadGenerada(Double electricidadGenerada) {
    	this.electricidadGenerada = electricidadGenerada;
    }

}