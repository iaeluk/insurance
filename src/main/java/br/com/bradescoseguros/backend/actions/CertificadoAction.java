package br.com.bradescoseguros.backend.actions;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.bradescoseguros.backend.entity.ApoliceAuto;
import br.com.bradescoseguros.backend.entity.Certificado;
import br.com.bradescoseguros.backend.facade.ApoliceAutoFacade;
import br.com.bradescoseguros.backend.facade.CertificadoFacade;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CertificadoAction {

    private Certificado certificado;
    private ArrayList<Certificado> certificados;
    private ArrayList<ApoliceAuto> apolices;


    @Autowired
    private CertificadoFacade certificadoFacade;

    @Autowired
    private ApoliceAutoFacade apoliceAutoFacade;
    
    private Integer id;
    private String tipoFormulario = "salvarApolice";
    private Boolean verDetalhes = false;
    private Boolean verEdicao = false;

    public CertificadoAction() {
    }
    
    public String execute() {
        buscarCertificado();
        tipoFormulario = "salvarCertificado";         
        verDetalhes = false;
        verEdicao = false;
        return "SUCCESS";
    }
    
    public String salvarCertificado() {
        try {
            certificadoFacade.salvarCertificado(certificado, certificado.getApoliceAutoId());
            tipoFormulario = "salvarCertificado";         
            return "SUCCESS";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "ERROR";
    }

    public String buscarCertificado() {
        try {
            certificados = certificadoFacade.buscarCertificado();
            apolices = apoliceAutoFacade.buscarApoliceAuto();
            tipoFormulario = "buscarCertificado";
            return "SUCCESS";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "ERROR";
    }

    public String buscarCertificadoPorId() {
        try {
            certificado = certificadoFacade.buscarCertificadoPorId(id);

            tipoFormulario = "buscarCertificado";
            verDetalhes = true;
            verEdicao = false;
            buscarCertificado();
            return "SUCCESS";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "ERROR";
    }

    public String paginaEdicaoCertificado() {
        buscarCertificadoPorId();
        verDetalhes = false;
        verEdicao = true;
        return "SUCCESS";
    }

    public String editarCertificado() {
        try {
            certificadoFacade.editarCertificado(certificado, id);
            buscarCertificadoPorId();
            verDetalhes = false;
            verEdicao = true;
            return "SUCCESS";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "ERROR";
    }

    public String deletarCertificado() {
        try {
            certificadoFacade.deletarCertificado(id);
            buscarCertificado();
            tipoFormulario = "buscarCertificado";
            return "SUCCESS";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "ERROR";
    }
}
