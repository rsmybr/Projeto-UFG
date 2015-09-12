/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bean.Instituicao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import util.ConectaBD;
import util.ConectaException;

/**
 *
 * @author Renilson
 */
public class InstituicaoDAO {

    private Connection con;

    public InstituicaoDAO() throws ConectaException {
        try {
            this.con = ConectaBD.getConnection();
        } catch (Exception e) {
            throw new ConectaException("Erro: " + e.getMessage());
        }
    }

    public void incluir(Instituicao instituicao) throws ConectaException {
        PreparedStatement ps = null;
        Connection con = null;

        if (instituicao == null) {
            throw new ConectaException(
                    "Método incluir: a Instituição passada não pode ser null");
        }
        try {
            String SQL = "INSERT INTO INSTITUICAO (DESCRICAO) VALUES (?)";
            con = ConectaBD.getConnection();
            ps = con.prepareStatement(SQL);
            ps.setString(1, instituicao.getDescricao());
            ps.executeUpdate();

        } catch (SQLException sqle) {
            throw new ConectaException("Erro ao inserir a Instituição: " + sqle);
        } finally {
            ConectaBD.closeConnection(con, ps);
        }
    }

    public List<Instituicao> todasInstituicoes() throws ConectaException {
        PreparedStatement ps = null;
        Connection con = null;
        ResultSet rs = null;

        try {
            con = ConectaBD.getConnection();
            ps = con.prepareStatement("SELECT CODINSTITUICAO, DESCRICAO FROM INSTITUICAO ORDER BY DESCRICAO");
            rs = ps.executeQuery();
            List<Instituicao> lista = new ArrayList<Instituicao>();

            while (rs.next()) {
                lista.add(new Instituicao(rs.getInt(1), rs.getString(2)));
            }
            return lista;
        } catch (SQLException sqle) {
            throw new ConectaException("Erro ao listar todas as Instituições; " + sqle);
        } finally {
            ConectaBD.closeConnection(con, ps, rs);
        }
    }
    
    public List<Instituicao> todasInstituicoes(String descricao) throws ConectaException {
        PreparedStatement ps = null;
        Connection con = null;
        ResultSet rs = null;
        
        descricao = '%' + descricao + '%';

        try {
            con = ConectaBD.getConnection();
            ps = con.prepareStatement("SELECT CODINSTITUICAO, DESCRICAO FROM INSTITUICAO WHERE UPPER(DESCRICAO) LIKE UPPER(?) ORDER BY DESCRICAO ");
            ps.setString(1, descricao);
            rs = ps.executeQuery();
            List<Instituicao> lista = new ArrayList<Instituicao>();

            while (rs.next()) {
                lista.add(new Instituicao(rs.getInt(1), rs.getString(2)));
            }
            return lista;
        } catch (SQLException sqle) {
            throw new ConectaException("Erro ao listar todas as Instituições; " + sqle);
        } finally {
            ConectaBD.closeConnection(con, ps, rs);
        }
    }

    public void excluir(Instituicao instituicao) throws ConectaException {
        PreparedStatement ps = null;
        Connection con = null;

        if (instituicao == null) {
            throw new ConectaException(
                    "Método excluir: a Instituição passada não pode estar nula");
        }

        try {
            con = ConectaBD.getConnection();
            ps = con.prepareStatement("DELETE FROM INSTITUICAO WHERE CODINSTITUICAO = ?");
            ps.setInt(1, instituicao.getCodigo());
            ps.executeUpdate();

        } catch (SQLException sqle) {
            throw new ConectaException("Erro ao excluir a Instituição: " + sqle);
        } finally {
            ConectaBD.closeConnection(con, ps);
        }

    }

    public void atualizar(Instituicao instituicao) throws ConectaException {
        PreparedStatement ps = null;
        Connection con = null;

        if (instituicao == null) {
            throw new ConectaException("Método atualizar: a Instituição passada não pode ser nula");
        }

        try {
            String SQL = "UPDATE INSTITUICAO SET DESCRICAO = ? WHERE CODINSTITUICAO = ?";
            con = ConectaBD.getConnection();
            ps = con.prepareStatement(SQL);
            ps.setString(1, instituicao.getDescricao());
            ps.setInt(2, instituicao.getCodigo());
            ps.executeUpdate();

        } catch (SQLException sqle) {
            throw new ConectaException("Erro ao atualizar a Instituição: " + sqle);
        } finally {
            ConectaBD.closeConnection(con, ps);
        }

    }

    public Instituicao procurarInstituicao(int codinstituicao) throws ConectaException {
        PreparedStatement ps = null;
        Connection con = null;
        ResultSet rs = null;

        try {
            con = this.con;
            ps = con.prepareStatement("SELECT CODINSTITUICAO, DESCRICAO FROM INSTITUICAO WHERE CODINSTITUICAO = ? ");
            ps.setInt(1, codinstituicao);
            rs = ps.executeQuery();

            if (!rs.next()) {
                throw new ConectaException("Nenhuma Instituição com o código " + codinstituicao);
            }
            
            return new Instituicao(rs.getInt(1), rs.getString(2));

        } catch (SQLException sqle) {
            throw new ConectaException("Erro ao procurar a Instituição: " + sqle);
        } finally {
            ConectaBD.closeConnection(con, ps, rs);
        }
    }

}
