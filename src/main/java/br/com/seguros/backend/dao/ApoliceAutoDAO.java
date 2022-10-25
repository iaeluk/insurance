package br.com.seguros.backend.dao;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import br.com.seguros.backend.connection.SingleConnection;
import br.com.seguros.backend.entity.ApoliceAuto;
import br.com.seguros.backend.entity.Segurado;
import br.com.seguros.backend.entity.TipoApolice;
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
public class ApoliceAutoDAO implements CrudInterface<ApoliceAuto, Segurado> {

    private static final Logger logger = LogManager.getLogger(ApoliceAutoDAO.class);

    private Connection connection;

    public ApoliceAutoDAO() {
        connection = SingleConnection.getConnection();
    }

    @Override
    public void salvar(ApoliceAuto a) {
        // TODO Auto-generated method stub

    }

    @Override
    public void salvar(ApoliceAuto apoliceAuto, Segurado segurado) {
        String sql = "INSERT INTO apolice_auto (segurado_id, cia, numero_documento_corretor, email_corretor, data_inicio_vigencia, data_fim_vigencia, descricao, item, nome_corretor, quantidade_dias_renovacao, ramo, segmento, sucursal, tipo_apolice, marca_veiculo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, segurado.getId());
            ps.setString(2, apoliceAuto.getCia());
            ps.setString(3, apoliceAuto.getNumeroDocumentoCorretor());
            ps.setString(4, apoliceAuto.getEmailCorretor());
            ps.setDate(5, new Date(apoliceAuto.getDataInicioVigencia().getTime()));
            ps.setDate(6, new Date(apoliceAuto.getDataFimVigencia().getTime()));
            ps.setString(7, apoliceAuto.getDescricao());
            ps.setString(8, apoliceAuto.getItem());
            ps.setString(9, apoliceAuto.getNomeCorretor());
            ps.setString(10, apoliceAuto.getQuantidadeDiasRenovacao());
            ps.setString(11, apoliceAuto.getRamo());
            ps.setString(12, apoliceAuto.getSegmento());
            ps.setString(13, apoliceAuto.getSucursal());
            ps.setString(14, String.valueOf(apoliceAuto.getTipoApolice()));
            ps.setString(15, apoliceAuto.getMarcaVeiculo());

            ps.executeUpdate();

            logger.info("Apólice salva com sucesso.");

        } catch (SQLException e) {
            logger.error("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            logger.error(e.getStackTrace());
        }

    }

    @Override
    public ArrayList<ApoliceAuto> buscar() {
        ArrayList<ApoliceAuto> retorno = new ArrayList<ApoliceAuto>();

        try {
            String sql = "SELECT * FROM apolice_auto";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet resultado = ps.executeQuery();
            while (resultado.next()) {
                ApoliceAuto apoliceAuto = new ApoliceAuto(
                        resultado.getInt("id"),
                        resultado.getInt("segurado_id"),
                        resultado.getString("cia"),
                        resultado.getString("numero_documento_corretor"),
                        resultado.getString("email_corretor"),
                        resultado.getDate("data_inicio_vigencia"),
                        resultado.getDate("data_fim_vigencia"),
                        resultado.getString("descricao"),
                        resultado.getString("item"),
                        resultado.getString("nome_corretor"),
                        resultado.getString("quantidade_dias_renovacao"),
                        resultado.getString("ramo"),
                        resultado.getString("segmento"),
                        resultado.getString("sucursal"),
                        TipoApolice.valueOf(resultado.getString("tipo_apolice")),
                        resultado.getString("marca_veiculo"));

                retorno.add(apoliceAuto);
            }

        } catch (SQLException e) {
            logger.error("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            logger.error(e.getStackTrace());
        }

        return retorno;
    }

    @Override
    public ApoliceAuto buscarPorId(Integer id) {
        try {
            String sql = "SELECT * FROM apolice_auto WHERE id=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet resultado = ps.executeQuery();

            while (resultado.next()) {
                ApoliceAuto apoliceAuto = new ApoliceAuto(
                        resultado.getInt("id"),
                        resultado.getInt("segurado_id"),
                        resultado.getString("cia"),
                        resultado.getString("numero_documento_corretor"),
                        resultado.getString("email_corretor"),
                        resultado.getDate("data_inicio_vigencia"),
                        resultado.getDate("data_fim_vigencia"),
                        resultado.getString("descricao"),
                        resultado.getString("item"),
                        resultado.getString("nome_corretor"),
                        resultado.getString("quantidade_dias_renovacao"),
                        resultado.getString("ramo"),
                        resultado.getString("segmento"),
                        resultado.getString("sucursal"),
                        TipoApolice.valueOf(resultado.getString("tipo_apolice")),
                        resultado.getString("marca_veiculo"));

                return apoliceAuto;
            }
        }   catch (SQLException e) {
            logger.error("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            logger.error(e.getStackTrace());
        }

        return null;
    }

    @Override
    public ApoliceAuto editar(ApoliceAuto apoliceAuto, Integer id) {
        try {
            String sql = "UPDATE apolice_auto SET segurado_id=?, cia=?, numero_documento_corretor=?, email_corretor=?, data_inicio_vigencia=?, data_fim_vigencia=?, descricao=?, item=?, nome_corretor=?, quantidade_dias_renovacao=?, ramo=?, segmento=?, sucursal=?, tipo_apolice=?, marca_veiculo=? WHERE id=?";

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, apoliceAuto.getSeguradoId());
            ps.setString(2, apoliceAuto.getCia());
            ps.setString(3, apoliceAuto.getNumeroDocumentoCorretor());
            ps.setString(4, apoliceAuto.getEmailCorretor());
            ps.setDate(5, new Date(apoliceAuto.getDataInicioVigencia().getTime()));
            ps.setDate(6, new Date(apoliceAuto.getDataFimVigencia().getTime()));
            ps.setString(7, apoliceAuto.getDescricao());
            ps.setString(8, apoliceAuto.getItem());
            ps.setString(9, apoliceAuto.getNomeCorretor());
            ps.setString(10, apoliceAuto.getQuantidadeDiasRenovacao());
            ps.setString(11, apoliceAuto.getRamo());
            ps.setString(12, apoliceAuto.getSegmento());
            ps.setString(13, apoliceAuto.getSucursal());
            ps.setString(14, String.valueOf(apoliceAuto.getTipoApolice()));
            ps.setString(15, apoliceAuto.getMarcaVeiculo());
            ps.setInt(16, id);

            ResultSet resultado = ps.executeQuery();

            while (resultado.next()) {
                ApoliceAuto apoliceAutoAtualizada = new ApoliceAuto(
                        resultado.getInt("id"),
                        resultado.getInt("segurado_id"),
                        resultado.getString("cia"),
                        resultado.getString("numero_documento_corretor"),
                        resultado.getString("email_corretor"),
                        resultado.getDate("data_inicio_vigencia"),
                        resultado.getDate("data_fim_vigencia"),
                        resultado.getString("descricao"),
                        resultado.getString("item"),
                        resultado.getString("nome_corretor"),
                        resultado.getString("quantidade_dias_renovacao"),
                        resultado.getString("ramo"),
                        resultado.getString("segmento"),
                        resultado.getString("sucursal"),
                        TipoApolice.valueOf(resultado.getString("tipo_apolice")),
                        resultado.getString("marca_veiculo"));

                return apoliceAutoAtualizada;
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
        String sql = "DELETE FROM apolice_auto WHERE id=?";

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
            JasperDesign jdesign = JRXmlLoader.load("C:\\Users\\lucasant\\IdeaProjects\\insurance\\src\\main\\webapp\\reports\\Apolice.jrxml");
            String query = "select * from apolice_auto";

            JRDesignQuery updateQuery = new JRDesignQuery();
            updateQuery.setText(query);

            jdesign.setQuery(updateQuery);

            JasperReport jReport = JasperCompileManager.compileReport(jdesign);
            JasperPrint jprint = JasperFillManager.fillReport(jReport, null, connection);
            
            OutputStream outputStream = null;
            String userHomeDirectory = "C:\\Impressao";

            String outputFile = userHomeDirectory + File.separatorChar + "RelatorioDeApolices.pdf";
            outputStream = new FileOutputStream(new File(outputFile));   

            JasperExportManager.exportReportToPdfStream(jprint, outputStream);

        } catch (Exception e) {
            logger.error(e.getStackTrace());
        }
    }
}
