package br.com.seguros.backend.dao;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import br.com.seguros.backend.connection.SingleConnection;
import br.com.seguros.backend.entity.ApoliceAuto;
import br.com.seguros.backend.entity.Certificado;
import br.com.seguros.backend.entity.TipoDescricaoSituacao;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Repository
public class CertificadoDAO implements CrudInterface<Certificado, ApoliceAuto> {

    private static final Logger logger = LogManager.getLogger(CertificadoDAO.class);

    private Connection connection;

    public CertificadoDAO() {
        connection = SingleConnection.getConnection();
    }

    @Override
    public void salvar(Certificado a) {
        // TODO Auto-generated method stub

    }

    @Override
    public void salvar(Certificado certificado, ApoliceAuto apoliceAuto) {
        String sql = "INSERT INTO certificado (apolice_auto_id, cd_produto_ret, chave_negocio, descricao_situacao, nome_produto, ramo) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, apoliceAuto.getId());
            ps.setString(2, certificado.getCdProdutoRet());
            ps.setString(3, certificado.getChaveNegocio());
            ps.setString(4, certificado.getDescricaoSituacao().toString());
            ps.setString(5, certificado.getNomeProduto());
            ps.setString(6, certificado.getRamo());

            ps.executeUpdate();
            logger.info("Certificado salvo com sucesso.");

        } catch (SQLException e) {
            logger.error("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            logger.error(e.getStackTrace());
        }

    }

    @Override
    public ArrayList<Certificado> buscar() {
        ArrayList<Certificado> retorno = new ArrayList<Certificado>();

        try {
            String sql = "SELECT * FROM certificado";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet resultado = ps.executeQuery();
            while (resultado.next()) {
                Certificado certificado = new Certificado(
                        resultado.getInt("id"),
                        resultado.getInt("apolice_auto_id"),
                        resultado.getString("cd_produto_ret"),
                        resultado.getString("chave_negocio"),
                        TipoDescricaoSituacao.valueOf(resultado.getString("descricao_situacao")),
                        resultado.getString("nome_produto"),
                        resultado.getString("ramo"));

                retorno.add(certificado);
            }

        } catch (SQLException e) {
            logger.error("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            logger.error(e.getStackTrace());
        }

        return retorno;
    }

    @Override
    public Certificado buscarPorId(Integer id) {
        try {
            String sql = "SELECT * FROM certificado WHERE id=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet resultado = ps.executeQuery();

            while (resultado.next()) {
                Certificado certificado = new Certificado(
                        resultado.getInt("id"),
                        resultado.getInt("apolice_auto_id"),
                        resultado.getString("cd_produto_ret"),
                        resultado.getString("chave_negocio"),
                        TipoDescricaoSituacao.valueOf(resultado.getString("descricao_situacao")),
                        resultado.getString("nome_produto"),
                        resultado.getString("ramo"));

                return certificado;
            }

        } catch (SQLException e) {
            logger.error("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            logger.error(e.getStackTrace());
        }

        return null;
    }

    @Override
    public Certificado editar(Certificado certificado, Integer id) {
        try {
            String sql = "UPDATE certificado SET apolice_auto_id=?, cd_produto_ret=?, chave_negocio=?, descricao_situacao=?, nome_produto=?, ramo=? WHERE id=?";

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, certificado.getApoliceAutoId());
            ps.setString(2, certificado.getCdProdutoRet());
            ps.setString(3, certificado.getChaveNegocio());
            ps.setString(4, certificado.getDescricaoSituacao().toString());
            ps.setString(5, certificado.getNomeProduto());
            ps.setString(6, certificado.getRamo());
            ps.setInt(7, id);

            ResultSet resultado = ps.executeQuery();

            while (resultado.next()) {
                Certificado certificadoAtualizado = new Certificado(
                        resultado.getInt("id"),
                        resultado.getInt("apolice_auto_id"),
                        resultado.getString("cd_produto_ret"),
                        resultado.getString("chave_negocio"),
                        TipoDescricaoSituacao.valueOf(resultado.getString("descricao_situacao")),
                        resultado.getString("nome_produto"),
                        resultado.getString("ramo"));

                return certificadoAtualizado;
            }

        } catch (SQLException e) {
            logger.error("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            logger.error(e.getStackTrace());
        }

        return null;
    }

    @Override
    public void deletar(Integer id) {
        String sql = "DELETE FROM certificado WHERE id=?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            logger.error(e.getStackTrace());
        }
    }

    public void gerarRelatorio() {
        try {
            JasperDesign jdesign = JRXmlLoader.load("C:\\Users\\lucasant\\IdeaProjects\\insurance\\src\\main\\webapp\\reports\\Certificado.jrxml");
            String query = "select * from certificado";

            JRDesignQuery updateQuery = new JRDesignQuery();
            updateQuery.setText(query);

            jdesign.setQuery(updateQuery);

            JasperReport jReport = JasperCompileManager.compileReport(jdesign);
            JasperPrint jprint = JasperFillManager.fillReport(jReport, null, connection);
            
            OutputStream outputStream = null;
            String userHomeDirectory = "C:\\Impressao";

            String outputFile = userHomeDirectory + File.separatorChar + "RelatorioDeCertificados.pdf";
            outputStream = new FileOutputStream(new File(outputFile));   

            JasperExportManager.exportReportToPdfStream(jprint, outputStream);

        } catch (Exception e) {
            logger.error(e.getStackTrace());
        }
    }
}
