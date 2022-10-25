package br.com.seguros.backend.ws;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.seguros.backend.dao.SeguradoDAO;
import br.com.seguros.backend.entity.Segurado;

@Service
@Path("/segurado")
@Produces(MediaType.APPLICATION_JSON)
public class SeguradoService {

    @Autowired
    private SeguradoDAO seguradoDAO;
  
    @GET
    @Path("/id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Segurado> seguradoId(@PathParam("id") int id) {
        ArrayList<Segurado> segurados = new ArrayList<Segurado>();
        segurados.add(seguradoDAO.buscarPorId(id));
        return segurados;
    }

    @GET
    @Path("/todos")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Segurado> listaSegurados() {
        return seguradoDAO.buscar();
    }

}
