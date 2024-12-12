package com.trabajo.controller.dao;

import com.google.gson.Gson;
import com.trabajo.controller.dao.implement.AdapterDao;
import com.trabajo.controller.tda.list.LinkedList;
import com.trabajo.models.Generador;

public class GeneradorDao extends AdapterDao<Generador>{
    private Generador generador;
    private LinkedList listAll;

    private Gson g = new Gson();

    public GeneradorDao(){
        super(Generador.class);
    }

    public Generador getGenerador(){
        if(generador == null){
            generador = new Generador();
        }
        return this.generador;
    }

    public void setGenerador(Generador generador){
        this.generador = generador;
    }

    public LinkedList<Generador> getListAll() throws Exception{
        if(listAll == null){
            this.listAll = listAll();
        }
        return listAll;
    }

    public Boolean save() throws Exception{
        Integer id = getListAll().getSize()+1;
        generador.setId(id);
        this.persist(this.generador);
        this.listAll = listAll();
        return true;
    }

    public Boolean update() throws Exception{
        this.merge(getGenerador(), getGenerador().getId() -1);
        this.listAll = listAll();
        return true;
    }
}
