package br.com.bradescoseguros.backend.dao;

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

import br.com.bradescoseguros.backend.connection.SingleConnection;
import br.com.bradescoseguros.backend.entity.Segurado;
import br.com.bradescoseguros.backend.entity.TipoPessoa;
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
public class SeguradoDAO implements CrudInterface<Segurado, Segurado> {

    private static final Logger logger = LogManager.getLogger(SeguradoDAO.class);

    private Connection connection;

    public SeguradoDAO() {
        connection = SingleConnection.getConnection();
    }

    @Override
    public void salvar(Segurado segurado) {
        String sql = "INSERT INTO segurado (nome, numero_documento, tipo_pessoa, data_nascimento, email) VALUES (?, ?, ?, ?, ?)";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, segurado.getNome());
            ps.setString(2, segurado.getNumeroDocumento());
            ps.setString(3, String.valueOf(segurado.getTipoPessoa()));
            ps.setDate(4, new Date(segurado.getDataNascimento().getTime()));
            ps.setString(5, segurado.getEmail());

            ps.executeUpdate();
            logger.error("Segurado salvo com sucesso.");

        } catch (SQLException e) {
            logger.error("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            logger.error(e.getStackTrace());
        }

    }

    @Override
    public void salvar(Segurado a, Segurado b) {
    }

    @Override
    public ArrayList<Segurado> buscar() {
        ArrayList<Segurado> listaDeSegurados = new ArrayList<Segurado>();

        try {
            String sql = "SELECT * FROM segurado";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet resultado = ps.executeQuery();
            while (resultado.next()) {
                Segurado segurado = new Segurado(
                        resultado.getInt("id"),
                        resultado.getString("nome"),
                        resultado.getString("numero_documento"),
                        TipoPessoa.valueOf(resultado.getString("tipo_pessoa")),
                        resultado.getDate("data_nascimento"),
                        resultado.getString("email"));

                listaDeSegurados.add(segurado);
            }
        } catch (SQLException e) {
            logger.error("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            logger.error(e.getStackTrace());
        }

        return listaDeSegurados;
    }

    @Override
    public Segurado buscarPorId(Integer id) {
        try {
            String sql = "SELECT * FROM segurado WHERE id=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet resultado = ps.executeQuery();

            while (resultado.next()) {
                Segurado segurado = new Segurado(
                        resultado.getInt("id"),
                        resultado.getString("nome"),
                        resultado.getString("numero_documento"),
                        TipoPessoa.valueOf(resultado.getString("tipo_pessoa")),
                        resultado.getDate("data_nascimento"),
                        resultado.getString("email"));

                return segurado;
            }

        } catch (SQLException e) {
            logger.error("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            logger.error(e.getStackTrace());
        }

        return null;
    }

    @Override
    public Segurado editar(Segurado segurado, Integer id) {
        try {
            String sql = "UPDATE segurado SET nome=?, numero_documento=?, tipo_pessoa=?, data_nascimento=?, email=? WHERE id=?";

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, segurado.getNome());
            ps.setString(2, segurado.getNumeroDocumento());
            ps.setString(3, segurado.getTipoPessoa().toString());
            ps.setDate(4, new Date(segurado.getDataNascimento().getTime()));
            ps.setString(5, segurado.getEmail());
            ps.setInt(6, id);

            ResultSet resultado = ps.executeQuery();

            while (resultado.next()) {
                Segurado seguradoAtualizado = new Segurado(
                        resultado.getInt("id"),
                        resultado.getString("nome"),
                        resultado.getString("numero_documento"),
                        TipoPessoa.valueOf(resultado.getString("tipo_pessoa")),
                        resultado.getDate("data_nascimento"),
                        resultado.getString("email"));

                return seguradoAtualizado;
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
        String sql = "DELETE FROM segurado WHERE id=?";

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
            JasperDesign jdesign = JRXmlLoader.load("C:\\Users\\lucasant\\IdeaProjects\\insurance\\src\\main\\webapp\\reports\\Segurado.jrxml");
            String query = "select * from segurado";

            JRDesignQuery updateQuery = new JRDesignQuery();
            updateQuery.setText(query);

            jdesign.setQuery(updateQuery);

            JasperReport jReport = JasperCompileManager.compileReport(jdesign);
            JasperPrint jprint = JasperFillManager.fillReport(jReport, null, connection);
            
            OutputStream outputStream = null;
            String userHomeDirectory = "C:\\Impressao";

            String outputFile = userHomeDirectory + File.separatorChar + "RelatorioDeSegurados.pdf";
            outputStream = new FileOutputStream(new File(outputFile));   

            JasperExportManager.exportReportToPdfStream(jprint, outputStream);

        } catch (Exception e) {
            logger.error(e.getStackTrace());
        }
    }
}
