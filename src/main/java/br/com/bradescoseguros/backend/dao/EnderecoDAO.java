package br.com.bradescoseguros.backend.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import br.com.bradescoseguros.backend.connection.SingleConnection;
import br.com.bradescoseguros.backend.entity.Endereco;
import br.com.bradescoseguros.backend.entity.Segurado;

@Repository
public class EnderecoDAO implements CrudInterface<Endereco, Segurado> {

    private Connection connection;

    public EnderecoDAO() {
        connection = SingleConnection.getConnection();
    }

    @Override
    public void salvar(Endereco a) {
        // TODO Auto-generated method stub

    }

    @Override
    public void salvar(Endereco endereco, Segurado segurado) {
        String sql = "INSERT INTO endereco (segurado_id, cep, complemento, descricao_tipo_logradouro, logradouro, numero, tipo_logradouro, cidade, uf, bairro) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, segurado.getId());
            statement.setString(2, endereco.getCep());
            statement.setString(3, endereco.getComplemento());
            statement.setString(4, endereco.getDescricaoTipoLogradouro());
            statement.setString(5, endereco.getLogradouro());
            statement.setString(6, endereco.getNumero());
            statement.setString(7, endereco.getTipoLogradouro());
            statement.setString(8, endereco.getCidade());
            statement.setString(9, endereco.getUf());
            statement.setString(10, endereco.getBairro());

            statement.executeUpdate();

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Endereco> buscar() {
        ArrayList<Endereco> retorno = new ArrayList<Endereco>();

        try {
            String sql = "SELECT * FROM endereco";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultado = statement.executeQuery();
            while (resultado.next()) {
                Endereco endereco = new Endereco(
                        resultado.getInt("id"),
                        resultado.getInt("segurado_id"),
                        resultado.getString("cep"),
                        resultado.getString("complemento"),
                        resultado.getString("descricao_tipo_logradouro"),
                        resultado.getString("logradouro"),
                        resultado.getString("numero"),
                        resultado.getString("tipo_logradouro"),
                        resultado.getString("cidade"),
                        resultado.getString("uf"),
                        resultado.getString("bairro"));

                retorno.add(endereco);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return retorno;
    }

    @Override
    public Endereco buscarPorId(Integer id) {
        try {
            String sql = "SELECT * FROM endereco WHERE segurado_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultado = statement.executeQuery();

            while (resultado.next()) {
                Endereco endereco = new Endereco(
                        resultado.getInt("id"),
                        resultado.getInt("segurado_id"),
                        resultado.getString("cep"),
                        resultado.getString("complemento"),
                        resultado.getString("descricao_tipo_logradouro"),
                        resultado.getString("logradouro"),
                        resultado.getString("numero"),
                        resultado.getString("tipo_logradouro"),
                        resultado.getString("cidade"),
                        resultado.getString("uf"),
                        resultado.getString("bairro"));

                return endereco;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Endereco editar(Endereco endereco, Integer id) {
        try {
            String sql = "UPDATE endereco SET cep=?, complemento=?, descricao_tipo_logradouro=?, logradouro=?, numero=?, tipo_logradouro=?, cidade=?, uf=?, bairro=? WHERE segurado_id=?";
            

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, endereco.getCep());
            ps.setString(2, endereco.getComplemento());
            ps.setString(3, endereco.getDescricaoTipoLogradouro());
            ps.setString(4, endereco.getLogradouro());
            ps.setString(5, endereco.getNumero());
            ps.setString(6, endereco.getTipoLogradouro());
            ps.setString(7, endereco.getCidade());
            ps.setString(8, endereco.getUf());
            ps.setString(9, endereco.getBairro());
            ps.setInt(10, id);

            ResultSet resultado = ps.executeQuery();

            while (resultado.next()) {
                Endereco enderecoAtualizado = new Endereco(
                        resultado.getInt("id"),
                        resultado.getInt("segurado_id"),
                        resultado.getString("cep"),
                        resultado.getString("complemento"),
                        resultado.getString("descricao_tipo_logradouro"),
                        resultado.getString("logradouro"),
                        resultado.getString("numero"),
                        resultado.getString("tipo_logradouro"),
                        resultado.getString("cidade"),
                        resultado.getString("uf"),
                        resultado.getString("bairro"));

                return enderecoAtualizado;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deletar(Integer id) {
        String sql = "DELETE FROM endereco WHERE segurado_id = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
