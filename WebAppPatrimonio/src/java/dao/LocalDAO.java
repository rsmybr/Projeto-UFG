/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bean.Local;
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
public class LocalDAO {
        private Connection con;

    public LocalDAO() throws ConectaException {
        try {
            this.con = ConectaBD.getConnection();
        } catch (Exception e) {
            throw new ConectaException("Erro: " + e.getMessage());
        }
    }

    public void incluir(Local local) throws ConectaException {
        PreparedStatement ps = null;
        Connection con = null;

        if (local == null) {
            throw new ConectaException(
                    "Método incluir: o Local passado não pode ser null");
        }
        try {
            String SQL = "INSERT INTO LOCAL (CODINSTITUICAO, DESCRICAO) VALUES (?,?)";
            con = ConectaBD.getConnection();
            ps = con.prepareStatement(SQL);
            ps.setInt(1, local.getCodinstituicao());
            ps.setString(2, local.getDescricao());
            ps.executeUpdate();

        } catch (SQLException sqle) {
            throw new ConectaException("Erro ao inserir o Local: " + sqle);
        } finally {
            ConectaBD.closeConnection(con, ps);
        }
    }

    public List<Local> todosLocais() throws ConectaException {
        PreparedStatement ps = null;
        Connection con = null;
        ResultSet rs = null;

        try {
            con = ConectaBD.getConnection();
            ps = con.prepareStatement("SELECT LOCAL.CODLOCAL, LOCAL.CODINSTITUICAO, LOCAL.DESCRICAO, INSTITUICAO.DESCRICAO DESCINSTITUICAO " + 
                                      "FROM LOCAL " +
                                      "LEFT JOIN INSTITUICAO ON INSTITUICAO.CODINSTITUICAO = LOCAL.CODINSTITUICAO ");  
            rs = ps.executeQuery();
            List<Local> lista = new ArrayList<Local>();

            while (rs.next()) {
                lista.add(new Local(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4)));
            }
            return lista;
        } catch (SQLException sqle) {
            throw new ConectaException("Erro ao listar todos os Locais: " + sqle);
        } finally {
            ConectaBD.closeConnection(con, ps, rs);
        }
    }
    
    public List<Local> todosLocais(String descricao) throws ConectaException {
        PreparedStatement ps = null;
        Connection con = null;
        ResultSet rs = null;
        
        descricao = '%' + descricao + '%';

        try {
            con = ConectaBD.getConnection();
            ps = con.prepareStatement("SELECT LOCAL.CODLOCAL, LOCAL.CODINSTITUICAO, LOCAL.DESCRICAO, INSTITUICAO.DESCRICAO DESCINSTITUICAO " + 
                                      "FROM LOCAL " +
                                      "LEFT JOIN INSTITUICAO ON INSTITUICAO.CODINSTITUICAO = LOCAL.CODINSTITUICAO " +
                                      "WHERE UPPER(LOCAL.DESCRICAO) LIKE UPPER(?) ORDER BY LOCAL.DESCRICAO ");
            ps.setString(1, descricao);
            rs = ps.executeQuery();
            List<Local> lista = new ArrayList<Local>();

            while (rs.next()) {
                lista.add(new Local(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4)));
            }
            return lista;
        } catch (SQLException sqle) {
            throw new ConectaException("Erro ao listar todos os Lugares: " + sqle);
        } finally {
            ConectaBD.closeConnection(con, ps, rs);
        }
    }

    public void excluir(Local local) throws ConectaException {
        PreparedStatement ps = null;
        Connection con = null;

        if (local == null) {
            throw new ConectaException(
                    "Método excluir: o Local passado não pode estar nulo!");
        }

        try {
            con = ConectaBD.getConnection();
            ps = con.prepareStatement("DELETE FROM LOCAL WHERE CODLOCAL = ?");
            ps.setInt(1, local.getCodlocal());
            ps.executeUpdate();

        } catch (SQLException sqle) {
            throw new ConectaException("Erro ao excluir o Local: " + sqle);
        } finally {
            ConectaBD.closeConnection(con, ps);
        }

    }

    public void atualizar(Local local) throws ConectaException {
        PreparedStatement ps = null;
        Connection con = null;

        if (local == null) {
            throw new ConectaException("Método atualizar: o Local passado não pode ser nulo!");
        }

        try {
            String SQL = "UPDATE LOCAL SET DESCRICAO = ?, CODINSTITUICAO = ? WHERE CODLOCAL = ?";
            con = ConectaBD.getConnection();
            ps = con.prepareStatement(SQL);
            ps.setString(1, local.getDescricao());
            ps.setInt(2, local.getCodinstituicao());
            ps.setInt(3,local.getCodlocal());
            ps.executeUpdate();

        } catch (SQLException sqle) {
            throw new ConectaException("Erro ao atualizar o Local: " + sqle);
        } finally {
            ConectaBD.closeConnection(con, ps);
        }

    }

    
}
