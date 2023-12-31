package br.com.seguros.backend.ws;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.seguros.backend.dao.ApoliceAutoDAO;
import br.com.seguros.backend.entity.ApoliceAuto;


@WebService(serviceName="buscarApolices")
@SOAPBinding(style=Style.RPC)
@Component(value="apoliceService")
public class ApoliceService {

    @Autowired
    private ApoliceAutoDAO apoliceAutoDAO;
	
	@WebMethod(operationName="buscarApolices")
	public List<ApoliceAuto> listarTodas(){
		return apoliceAutoDAO.buscar();
	}

}
