package com.trabajo.controller.dao.services;

import com.trabajo.controller.tda.list.LinkedList;
import com.trabajo.models.Generador;
import com.trabajo.controller.dao.GeneradorDao;

public class GeneradorService {
    private GeneradorDao obj;

    public GeneradorService(){
        obj = new GeneradorDao();
    }

    public Boolean save() throws Exception{
        return obj.save();
    }

    public Boolean update() throws Exception{
        return obj.update();
    }

    public LinkedList<Generador> listAll() throws Exception{
        return obj.listAll();
    }

    public Generador getGenerador(){
        return obj.getGenerador();
    }

    public void setGenerador(Generador Generador){
        obj.setGenerador(Generador);
    }

    public Generador get(Integer id) throws Exception{
        return obj.get(id);
    }
}
