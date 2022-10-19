package br.com.bradescoseguros.backend.ws;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.xml.ws.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.bradescoseguros.backend.dao.ApoliceAutoDAO;
import br.com.bradescoseguros.backend.entity.ApoliceAuto;


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

	@WebMethod(operationName = "add")
	public int add(@WebParam(name = "i") int i, @WebParam(name = "j") int j) {
		return i+j;
	}
	
}
