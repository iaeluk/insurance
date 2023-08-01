package br.com.seguros.backend.actions;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;

import br.com.seguros.backend.entity.Endereco;
import br.com.seguros.backend.entity.Segurado;
import br.com.seguros.backend.entity.Telefone;
import br.com.seguros.backend.facade.SeguradoFacade;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SeguradoAction extends ActionSupport{

    private Segurado segurado;
    private Endereco endereco;
    private Telefone telefone;
    private ArrayList<Segurado> segurados;

    @Autowired
    private SeguradoFacade seguradoFacade;
    
    private Integer id;
    private String tipoFormulario = "salvarSegurado";
    private Boolean verDetalhes = false;
    private Boolean verEdicao = false;

    public SeguradoAction() {
    }
    
    public String execute() {
        buscarSegurado();
        tipoFormulario = "salvarSegurado";         
        verDetalhes = false;
        verEdicao = false;
        return SUCCESS;
    }

    public String salvarSegurado() {
        try {
            seguradoFacade.salvarSegurado(segurado, endereco, telefone);
            tipoFormulario = "salvarSegurado";
            return SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ERROR;
    }

    public String buscarSegurado() {
        try {
            segurados = seguradoFacade.buscarSegurado();
            tipoFormulario = "buscarSegurado";
            return SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ERROR;
    }

    public String buscarSeguradoPorId() {
        try {
            segurado = seguradoFacade.buscarSeguradoPorId(id);
            telefone = seguradoFacade.buscarTelefonePorId(id);
            endereco = seguradoFacade.buscarEnderecoPorId(id);

            tipoFormulario = "buscarSegurado";
            verDetalhes = true;
            verEdicao = false;
            buscarSegurado();
            return SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ERROR;
    }

    public String editarSegurado() {
        try {
            seguradoFacade.editarSegurado(segurado, telefone, endereco, id);
            buscarSeguradoPorId();
            verDetalhes = false;
            verEdicao = true;
            return SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ERROR;
    }

    public String deletarSegurado() {
        try {
            seguradoFacade.deletarSegurado(id);
            buscarSegurado();
            tipoFormulario = "buscarSegurado";
            return SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ERROR;
    }

    public String relatorioSegurado() {
        try {
            segurados = seguradoFacade.buscarSegurado();
            tipoFormulario = "buscarSegurado";
            seguradoFacade.relatorio();
            return SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ERROR;
    }
}
