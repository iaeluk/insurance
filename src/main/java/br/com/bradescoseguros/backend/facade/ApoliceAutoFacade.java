package br.com.bradescoseguros.backend.facade;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.bradescoseguros.backend.dao.ApoliceAutoDAO;
import br.com.bradescoseguros.backend.dao.SeguradoDAO;
import br.com.bradescoseguros.backend.entity.ApoliceAuto;
import br.com.bradescoseguros.backend.entity.Segurado;

@Component
public class ApoliceAutoFacade {

    @Autowired
    private ApoliceAutoDAO apoliceAutoDAO;

    @Autowired
    private SeguradoDAO seguradoDAO;

    public ApoliceAutoFacade() {
    }

    public void salvarApoliceAuto(ApoliceAuto apoliceAuto, Integer id) {
            Segurado segurado = seguradoDAO.buscarPorId(id);
            apoliceAutoDAO.salvar(apoliceAuto, segurado);
            buscarApoliceAuto();
    }

    public ArrayList<ApoliceAuto> buscarApoliceAuto() {
        return apoliceAutoDAO.buscar();
    }

    public ApoliceAuto buscarApoliceAutoPorId(Integer id) {
        return apoliceAutoDAO.buscarPorId(id);
    }

    public void editarApoliceAuto(ApoliceAuto apoliceAuto, Integer id) {
        apoliceAutoDAO.editar(apoliceAuto, id);
    }

    public void deletarApoliceAuto(Integer id) {
        apoliceAutoDAO.deletar(id);
    }

    public void relatorio() {
        apoliceAutoDAO.gerarRelatorio();
    }
}