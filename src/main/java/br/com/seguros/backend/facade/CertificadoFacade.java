package br.com.seguros.backend.facade;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.seguros.backend.dao.ApoliceAutoDAO;
import br.com.seguros.backend.dao.CertificadoDAO;
import br.com.seguros.backend.entity.ApoliceAuto;
import br.com.seguros.backend.entity.Certificado;

@Component
public class CertificadoFacade {

    @Autowired
    private CertificadoDAO certificadoDAO;

    @Autowired
    private ApoliceAutoDAO apoliceAutoDAO;

    public CertificadoFacade() {
    }

    public void salvarCertificado(Certificado certificado, Integer id) {
            ApoliceAuto apoliceAuto = apoliceAutoDAO.buscarPorId(id);
            certificadoDAO.salvar(certificado, apoliceAuto);
            buscarCertificado();
    }

    public ArrayList<Certificado> buscarCertificado() {
        return certificadoDAO.buscar();
    }

    public Certificado buscarCertificadoPorId(Integer id) {
        return certificadoDAO.buscarPorId(id);
    }

    public void editarCertificado(Certificado apoliceAuto, Integer id) {
        certificadoDAO.editar(apoliceAuto, id);
    }

    public void deletarCertificado(Integer id) {
        certificadoDAO.deletar(id);
    }

    public void relatorio() {
        certificadoDAO.gerarRelatorio();
    }
}