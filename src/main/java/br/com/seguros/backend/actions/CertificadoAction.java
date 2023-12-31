package br.com.seguros.backend.actions;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;

import br.com.seguros.backend.entity.ApoliceAuto;
import br.com.seguros.backend.entity.Certificado;
import br.com.seguros.backend.facade.ApoliceAutoFacade;
import br.com.seguros.backend.facade.CertificadoFacade;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CertificadoAction extends ActionSupport {

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
        return SUCCESS;
    }
    
    public String salvarCertificado() {
        try {
            certificadoFacade.salvarCertificado(certificado, certificado.getApoliceAutoId());
            tipoFormulario = "salvarCertificado";         
            return SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ERROR;
    }

    public String buscarCertificado() {
        try {
            certificados = certificadoFacade.buscarCertificado();
            apolices = apoliceAutoFacade.buscarApoliceAuto();
            tipoFormulario = "buscarCertificado";
            return SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ERROR;
    }

    public String buscarCertificadoPorId() {
        try {
            certificado = certificadoFacade.buscarCertificadoPorId(id);

            tipoFormulario = "buscarCertificado";
            verDetalhes = true;
            verEdicao = false;
            buscarCertificado();
            return SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ERROR;
    }

    public String paginaEdicaoCertificado() {
        buscarCertificadoPorId();
        verDetalhes = false;
        verEdicao = true;
        return SUCCESS;
    }

    public String editarCertificado() {
        try {
            certificadoFacade.editarCertificado(certificado, id);
            buscarCertificadoPorId();
            verDetalhes = false;
            verEdicao = true;
            return SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ERROR;
    }

    public String deletarCertificado() {
        try {
            certificadoFacade.deletarCertificado(id);
            buscarCertificado();
            tipoFormulario = "buscarCertificado";
            return SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ERROR;
    }

    public String relatorioCertificado() {
        try {
            certificados = certificadoFacade.buscarCertificado();
            tipoFormulario = "buscarCertificado";
            certificadoFacade.relatorio();
            return SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ERROR;
    }
}
