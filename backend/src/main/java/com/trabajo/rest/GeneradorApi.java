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
import com.trabajo.controller.dao.services.GeneradorService;
import com.trabajo.controller.exception.ListEmptyException;
import com.trabajo.controller.tda.list.LinkedList;

@Path("generate")
public class GeneradorApi {
    @Path("list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllGenerates() throws ListEmptyException, Exception{
        HashMap<String, Object> map = new HashMap<>();
        GeneradorService gs = new GeneradorService();
        map.put("msg", "OK");
        map.put("data", gs.listAll().toArray());
        if (gs.listAll().isEmpty()) {
            map.put("data", new Object[] {});
        }
        return Response.ok(map).build();
    }

    @Path("/get/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGenerate(@PathParam("id") Integer id) {
        HashMap map = new HashMap<>();
        GeneradorService gs = new GeneradorService();
        try {
            gs.setGenerador(gs.get(id));
        } catch (Exception e) {
            // TODO: handle exception
        }
        map.put("msg", "OK");
        map.put("data", gs.getGenerador());
        if (gs.getGenerador().getId() == null) {
            map.put("data", "No existe la generador con ese identificador");
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
        
        try {
            GeneradorService gs = new GeneradorService();
            gs.getGenerador().setCosteGenerador(Double.valueOf(map.get(("CostoGenerador")).toString()));
            gs.getGenerador().setElectricidadGenerada(Double.valueOf(map.get(("ElectricidadGenerada")).toString()));

            gs.save();
            res.put("msg", "OK");
            res.put("data", "Generador registrado correctamente");
            return Response.ok(res).build();

        } catch (Exception e) {
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
        Gson g = new Gson();
        String a = g.toJson(map);
        System.out.println("*********" + a);
        
        try {
            GeneradorService gs = new GeneradorService();
            gs.setGenerador(gs.get(Integer.valueOf(map.get("id").toString())));
            gs.getGenerador().setCosteGenerador(Double.valueOf(map.get(("CostoGenerador")).toString()));
            gs.getGenerador().setElectricidadGenerada(Double.valueOf(map.get(("ElectricidadGenerada")).toString()));

            gs.update();
            res.put("msg", "OK");
            res.put("data", "Generador registrado correctamente");
            return Response.ok(res).build();

        } catch (Exception e) {
            System.out.println("Error en save data" + e.toString());
            res.put("msg", "Error");
            res.put("data", e.toString());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
        }


    }
}
