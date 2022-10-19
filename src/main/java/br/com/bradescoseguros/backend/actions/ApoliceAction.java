package br.com.bradescoseguros.backend.actions;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.bradescoseguros.backend.entity.ApoliceAuto;
import br.com.bradescoseguros.backend.entity.Segurado;
import br.com.bradescoseguros.backend.facade.ApoliceAutoFacade;
import br.com.bradescoseguros.backend.facade.SeguradoFacade;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApoliceAction {

    private ApoliceAuto apoliceAuto;
    private ArrayList<ApoliceAuto> apolices;
    private ArrayList<Segurado> segurados;


    @Autowired
    private ApoliceAutoFacade apoliceAutoFacade;

    @Autowired
    private SeguradoFacade seguradoFacade;
    
    private Integer id;
    private String tipoFormulario = "salvarApoliceAuto";
    private Boolean verDetalhes = false;
    private Boolean verEdicao = false;

    public ApoliceAction() {
    }
    
    public String execute() {
        buscarApoliceAuto();
        tipoFormulario = "salvarApoliceAuto";         
        verDetalhes = false;
        verEdicao = false;
        return "SUCCESS";
    }
    
    public String salvarApoliceAuto() {
        try {
            apoliceAutoFacade.salvarApoliceAuto(apoliceAuto, apoliceAuto.getSeguradoId());
            tipoFormulario = "salvarApoliceAuto";         
            return "SUCCESS";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "ERROR";
    }

    public String buscarApoliceAuto() {
        try {
            apolices = apoliceAutoFacade.buscarApoliceAuto();
            segurados = seguradoFacade.buscarSegurado();
            tipoFormulario = "buscarApoliceAuto";
            return "SUCCESS";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "ERROR";
    }

    public String buscarApoliceAutoPorId() {
        try {
            apoliceAuto = apoliceAutoFacade.buscarApoliceAutoPorId(id);

            tipoFormulario = "buscarApoliceAuto";
            verDetalhes = true;
            verEdicao = false;
            buscarApoliceAuto();
            return "SUCCESS";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "ERROR";
    }

    public String paginaEdicaoApolice() {
        buscarApoliceAutoPorId();
        verDetalhes = false;
        verEdicao = true;
        return "SUCCESS";
    }

    public String editarApoliceAuto() {
        try {
            apoliceAutoFacade.editarApoliceAuto(apoliceAuto, id);
            buscarApoliceAutoPorId();
            verDetalhes = false;
            verEdicao = true;
            return "SUCCESS";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "ERROR";
    }

    public String deletarApoliceAuto() {
        try {
            apoliceAutoFacade.deletarApoliceAuto(id);
            buscarApoliceAuto();
            tipoFormulario = "buscarApoliceAuto";
            return "SUCCESS";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "ERROR";
    }

    public String relatorioApolice() {
        try {
            apolices = apoliceAutoFacade.buscarApoliceAuto();
            tipoFormulario = "buscarApoliceAuto";
            apoliceAutoFacade.relatorio();
            return "SUCCESS";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "ERROR";
    }
}
