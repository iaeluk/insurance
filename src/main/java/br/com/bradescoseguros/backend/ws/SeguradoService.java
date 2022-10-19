package br.com.bradescoseguros.backend.ws;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import br.com.bradescoseguros.backend.dao.SeguradoDAO;

@Service
@Path("/segurado")
@Produces(MediaType.APPLICATION_JSON)
public class SeguradoService {

    @Autowired
    private SeguradoDAO seguradoDAO;
  
    @GET
    @Path("/id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String seguradoId(@PathParam("id") int id) {
        return seguradoDAO.buscar().get(id).getNome();
    }

}
