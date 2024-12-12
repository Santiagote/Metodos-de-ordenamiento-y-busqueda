package com.trabajo.controller.dao.services;

import com.trabajo.controller.tda.list.LinkedList;
import com.trabajo.models.Familia;

import java.util.Arrays;
import java.util.Comparator;

import com.trabajo.controller.dao.FamiliaDao;
import com.trabajo.controller.exception.ListEmptyException;

public class FamiliaService{
    private FamiliaDao obj;

    public FamiliaService(){
        obj = new FamiliaDao();
    }




    public Boolean save() throws Exception{
        return obj.save();
    }

    public Boolean update() throws Exception{
        return obj.update();
    }

    public LinkedList<Familia> listAll() throws Exception{
        return obj.getListAll();
    }

    public Familia getFamilia(){
        return obj.getFamilia();
    }

    public void setFamilia(Familia Familia){
        obj.setFamilia(Familia);
    }

    public Familia get(Integer id) throws Exception{
        return obj.get(id);
    }

    public LinkedList order(Integer type_order, String atributo) {
        return obj.order(type_order, atributo);
    }

    public LinkedList order_object(Integer type, String atributo) throws Exception {
        return obj.listAll().order(atributo, type);
    }



    public LinkedList<Familia> buscar_apellidos(String texto) {
        return obj.buscar_apellidos(texto);
    }

    //Nuevos metodos de ordenamiento
    public LinkedList<Familia> orderByQuickSort(Integer type_order, String atributo) throws Exception {
        return obj.orderByQuickSort(type_order, atributo);
    }

    public LinkedList<Familia> orderByMergeSort(Integer type_order, String atributo) throws Exception {
        return obj.mergeSort(type_order, atributo);
    }

    public LinkedList<Familia> orderByShellSort(Integer type_order, String atributo) throws Exception {
        return obj.shellSort(type_order, atributo);
    }
    //Fin metodos de ordenamiento

    //nuevos metodos de busqueda
    public LinkedList<Familia> findLinearSearch(String atributo, Object valor) {
        return obj.findLinearSearch(atributo, valor);
    }

    public LinkedList<Familia> findBinarySearch(String atributo, Object valor, Integer type) {
        return obj.findBinarySearch(atributo, valor, type);
    }
    

    //fin de los metodos de busqueda

}
