package com.trabajo.models;


public class Familia{
    private Integer id; 
    private String apellidos;
    private String direccion; 
    private Double consumoPorHora;
    private Integer cantidadGeneradores;
    private String usoGenerador; 

//getters and setters

    public Integer getId() {
    	return this.id;
    }
    public void setId(Integer id) {
    	this.id = id;
    }

    public String getApellidos() {
    	return this.apellidos;
    }
    public void setApellidos(String apellidos) {
    	this.apellidos = apellidos;
    }
     
    public String getDireccion() {
 	    return this.direccion;
    }
    public void setDireccion(String direccion) {
 	    this.direccion = direccion;
    }

    public Double getConsumoPorHora() {
    	return this.consumoPorHora;
    }
    public void setConsumoPorHora(Double consumoPorHora) {
    	this.consumoPorHora = consumoPorHora;
    }

    public Integer getCantidadGeneradores() {
    	return this.cantidadGeneradores;
    }
    public void setCantidadGeneradores(Integer cantidadGeneradores) {
    	this.cantidadGeneradores = cantidadGeneradores;
    }

    public String getUsoGenerador() {
    	return this.usoGenerador;
    }
    public void setUsoGenerador(String usoGenerador) {
    	this.usoGenerador = usoGenerador;
    }
}




