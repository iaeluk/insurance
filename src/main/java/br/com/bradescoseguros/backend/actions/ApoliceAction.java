package br.com.bradescoseguros.backend.actions;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.bradescoseguros.backend.entity.ApoliceAuto;
import br.com.bradescoseguros.backend.entity.Segurado;
import br.com.bradescoseguros.backend.facade.ApoliceAutoFacade;
import br.com.bradescoseguros.backend.facade.SeguradoFacade;

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

    public ApoliceAuto getApoliceAuto() {
        return apoliceAuto;
    }

    public void setApoliceAuto(ApoliceAuto apoliceAuto) {
        this.apoliceAuto = apoliceAuto;
    }

    public ArrayList<ApoliceAuto> getApolices() {
        return apolices;
    }

    public void setApolices(ArrayList<ApoliceAuto> apolices) {
        this.apolices = apolices;
    }

    public ArrayList<Segurado> getSegurados() {
        return segurados;
    }

    public void setSegurados(ArrayList<Segurado> segurados) {
        this.segurados = segurados;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipoFormulario() {
        return tipoFormulario;
    }

    public void setTipoFormulario(String tipoFormulario) {
        this.tipoFormulario = tipoFormulario;
    }

    public Boolean getVerDetalhes() {
        return verDetalhes;
    }

    public void setVerDetalhes(Boolean verDetalhes) {
        this.verDetalhes = verDetalhes;
    }

    public Boolean getVerEdicao() {
        return verEdicao;
    }

    public void setVerEdicao(Boolean verEdicao) {
        this.verEdicao = verEdicao;
    }

}
