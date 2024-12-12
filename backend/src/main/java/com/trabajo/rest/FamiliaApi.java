package com.trabajo.rest;

import java.util.HashMap;

import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import java.io.FileInputStream;

import com.trabajo.controller.dao.services.FamiliaService;
import com.trabajo.controller.exception.ListEmptyException;
import com.trabajo.controller.tda.list.LinkedList;
import com.trabajo.models.Familia;


@Path("family")
public class FamiliaApi {
    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllFamilies()throws ListEmptyException, Exception{
        HashMap<String, Object> map = new HashMap<>();
        FamiliaService fs = new FamiliaService();
        map.put("msg", "OK");
        map.put("data", fs.listAll().toArray());
        if (fs.listAll().isEmpty()) {
            map.put("data", new Object[] {});
        }
        return Response.ok(map).build();
    }
    
    @Path("/list/search/{texto}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFamiliesLastName(@PathParam("texto") String texto) {
        HashMap map = new HashMap<>();
        FamiliaService fs = new FamiliaService();
        map.put("msg", "OK");
        try {
            LinkedList listita = fs.buscar_apellidos(texto);
            map.put("data", listita.toArray());
            if (fs.listAll().isEmpty()) {
                map.put("data", new Object[] {});
            }
        } catch (Exception e) {
            map.put("msg", "Error: " + e.getMessage());
            map.put("data", new Object[] {});
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(map).build();
        }
        return Response.ok(map).build();
    }

    

    
    @Path("/list/order/{attribute}/{type}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFamiliesLastName(@PathParam("attribute") String attribute, @PathParam("type") Integer type) {
        HashMap map = new HashMap<>();
        FamiliaService fs = new FamiliaService();
        map.put("msg", "OK");
        
        try {
            LinkedList lsita = fs.order_object(type, attribute);
            map.put("data", lsita.toArray());
            if (lsita.isEmpty()) {
                map.put("data", new Object[] {});
            }
        } catch (Exception e) {
           
        }

        return Response.ok(map).build();
    }



    @Path("/get/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFamily(@PathParam("id") Integer id) {
        HashMap map = new HashMap<>();
        FamiliaService fs = new FamiliaService();
        try {
            fs.setFamilia(fs.get(id));
        } catch (Exception e) {
            // TODO: handle exception
        }
        map.put("msg", "OK");
        map.put("data", fs.getFamilia());
        if (fs.getFamilia().getId() == null) {
            map.put("data", "No existe la persona con ese identificador");
            return Response.status(Status.BAD_REQUEST).entity(map).build();
        }
        return Response.ok(map).build();
    }

    @Path("/save")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(HashMap map){
        HashMap res = new HashMap<>();
        Gson g = new Gson();
        String a = g.toJson(map);
        System.out.println("*********" + a);    

        try{
            FamiliaService fs = new FamiliaService();
            fs.getFamilia().setApellidos(map.get(("apellidos")).toString());
            fs.getFamilia().setDireccion(map.get(("direccion")).toString());
            fs.getFamilia().setConsumoPorHora(Double.valueOf(map.get(("ConsumoHora")).toString()));
            fs.getFamilia().setCantidadGeneradores(Integer.valueOf(map.get(("CantidadGeneradores")).toString()));
            fs.getFamilia().setUsoGenerador(map.get(("Uso")).toString());

            fs.save();
            res.put("msg", "OK");
            res.put("data", "Familia registrada correctamente");
            return Response.ok(res).build();
        } catch(Exception e){
            System.out.println("Error en save data" + e.toString());
            res.put("msg", "Error");
            res.put("data", e.toString());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
        }
    }

    @Path("/update")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(HashMap map){
        HashMap res = new HashMap<>();
        
        try {
            FamiliaService fs = new FamiliaService();
            fs.setFamilia(fs.get(Integer.valueOf(map.get("id").toString())));
            fs.getFamilia().setApellidos(map.get(("apellidos")).toString());
            fs.getFamilia().setDireccion(map.get(("direccion")).toString());
            fs.getFamilia().setConsumoPorHora(Double.valueOf(map.get(("ConsumoHora")).toString()));
            fs.getFamilia().setCantidadGeneradores(Integer.parseInt(map.get(("CantidadGeneradores")).toString()));
            fs.getFamilia().setUsoGenerador(map.get(("Uso")).toString());
            
            fs.update();
            res.put("msg", "OK");
            res.put("data", "Familia registrada correctamente");
            return Response.ok(res).build();
        } catch (Exception e) {
            System.out.println("Error en save data " + e.toString());
            res.put("msg", "Error");
            
            res.put("data", e.toString());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
           
        }
    }

    //Nuevo ordenamiento
    @Path("/list/orderByQuickSort/{type_order}/{atributo}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response orderByQuickSort(@PathParam("type_order") Integer type_order, @PathParam("atributo") String atributo) {
        HashMap<String, Object> map = new HashMap<>();
        FamiliaService fs = new FamiliaService();
        
        try {
            LinkedList<Familia> lista = fs.orderByQuickSort(type_order, atributo);
            
            map.put("msg", "OK");
            map.put("data", lista.toArray());
            
            if (lista.isEmpty()) {
                map.put("data", new Object[] {});
            }
        } catch (Exception e) {
            map.put("msg", "Error: " + e.getMessage());
            map.put("data", new Object[] {});
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(map).build();
        }

        return Response.ok(map).build();
    }
    

    @Path("/list/orderByMergeSort/{type_order}/{atributo}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response orderByMergeSort(@PathParam("type_order") Integer type_order, @PathParam("atributo") String atributo) {
        HashMap<String, Object> map = new HashMap<>();
        FamiliaService fs = new FamiliaService();
        
        try {
            LinkedList<Familia> lista = fs.orderByMergeSort(type_order, atributo);
            
            map.put("msg", "OK");
            
            // Si la lista está vacía, ponemos un array vacío
            map.put("data", lista.isEmpty() ? new Object[] {} : lista.toArray());
            
        } catch (Exception e) {
            map.put("msg", "Error: " + e.getMessage());
            map.put("data", new Object[] {});
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(map).build();
        }
        
        return Response.ok(map).build();
    }
    
    
    
    

    @Path("/list/orderByShellSort/{type_order}/{atributo}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response orderByShellSort(@PathParam("type_order") Integer type_order, @PathParam("atributo") String atributo) {
        HashMap<String, Object> map = new HashMap<>();
        FamiliaService fs = new FamiliaService();
        
        try {
            LinkedList<Familia> lista = fs.orderByShellSort(type_order, atributo);
            
            map.put("msg", "OK");
            map.put("data", lista.toArray());
            
            if (lista.isEmpty()) {
                map.put("data", new Object[] {});
            }
        } catch (Exception e) {
            map.put("msg", "Error: " + e.getMessage());
            map.put("data", new Object[] {});
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(map).build();
        }
        
        return Response.ok(map).build();
    }
    
    //Fin orndennamiento

    //metodos de busqueda
    @Path("/list/findLinearSearch/{atributo}/{valor}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findLinearSearch(@PathParam("atributo") String atributo, @PathParam("valor") String valor) {
        HashMap<String, Object> map = new HashMap<>();
        FamiliaService fs = new FamiliaService();
        map.put("msg", "OK");
        try {
            LinkedList<Familia> lista = fs.findLinearSearch(atributo, valor);
            map.put("data", lista.toArray());
            if (lista.isEmpty()) {
                map.put("data", new Object[] {});
            }
        } catch (Exception e) {
            map.put("msg", "Error: " + e.getMessage());
            map.put("data", new Object[] {});
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(map).build();
        }
        return Response.ok(map).build();
    }

    @Path("/list/findBinarySearch/{atributo}/{valor}/{type}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findBinarySearch(@PathParam("atributo") String atributo, 
                                      @PathParam("valor") String valor,
                                      @PathParam("type") Integer type) {
        HashMap<String, Object> map = new HashMap<>();
        FamiliaService fs = new FamiliaService();
        map.put("msg", "OK");
    
        try {
            
            LinkedList<Familia> lista = fs.findBinarySearch(atributo, valor, type);
            map.put("data", lista.toArray());
            
            if (lista.isEmpty()) {
                map.put("data", new Object[] {});
            }
        } catch (Exception e) {
            map.put("msg", "Error: " + e.getMessage());
            map.put("data", new Object[] {});
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(map).build();
        }
        
        return Response.ok(map).build();
    }
    
    

    
    //fin metodos de busqueda
    
}