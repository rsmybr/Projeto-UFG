/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import bean.Instituicao;
import dao.InstituicaoDAO;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import util.ConectaException;
import util.StatusTable;

/**
 *
 * @author Renilson
 */
@ManagedBean
//@ViewScoped
@SessionScoped

public class InstituicaoControle implements Serializable {

    private Instituicao instituicao;
    private InstituicaoDAO instituicaoDao;
    private List<Instituicao> instituicoes;
    private String pesquisa;
    private StatusTable status;
    private String titulo;

    //Construtor
    public InstituicaoControle() throws ConectaException {
        status = StatusTable.EXIBICAO;
        instituicao = new Instituicao();
        instituicaoDao = new InstituicaoDAO();
        this.pesquisa = "";
    }

    //Métodos de acesso ao Banco
    public String addInstituicao() throws ConectaException {
        status = StatusTable.EXIBICAO;
        instituicaoDao.incluir(instituicao);
        return "instituicao.jsf";
    }

    public String deleteInstituicao() throws ConectaException {
        instituicaoDao.excluir(instituicao);
        return "instituicao.jsf";
    }

    public String updateInstituicao() throws ConectaException {
        status = StatusTable.EXIBICAO;
        instituicaoDao.atualizar(instituicao);
        return "instituicao.jsf";
    }

    public void procurarInstituicao(int codinstituicao) throws ConectaException {
        instituicao = instituicaoDao.procurarInstituicao(codinstituicao);
    }

    //Outros métodos
    public Boolean isModoInclusao(){
        return status == StatusTable.INCLUSAO;
    }
    
    public Boolean isModoAlteracao(){
        return status == StatusTable.ALTERACAO;
    }
    
    public String inclusaoInstituicao() {
        status = StatusTable.INCLUSAO;
        instituicao = new Instituicao();
        return "iuInstituicao.jsf";
    }

    public String pesquisaInstituicao() {
        return "instituicao.jsf";
    }

    public String editInstituicao() throws ConectaException {
        status = StatusTable.ALTERACAO;
        return "iuInstituicao.jsf";
    }

    public String cancelaEdicao() {
        return "instituicao.jsf";
    }

    public String goMenu() {
        return "index?faces-redirect=true";
    }

    //Métodos Getters and Setters
    public Instituicao getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(Instituicao instituicao) {
        this.instituicao = instituicao;
    }

    public InstituicaoDAO getInstituicaoDao() {
        return instituicaoDao;
    }

    public void setInstituicaoDao(InstituicaoDAO instituicaoDao) {
        this.instituicaoDao = instituicaoDao;
    }

    public List<Instituicao> getInstituicoes() throws ConectaException {
        instituicoes = instituicaoDao.todasInstituicoes(pesquisa);
        return instituicoes;
    }

    public void setInstituicoes(List<Instituicao> instituicoes) {
        this.instituicoes = instituicoes;
    }

    public String getPesquisa() {
        return pesquisa;
    }

    public void setPesquisa(String pesquisa) {
        this.pesquisa = pesquisa;
    }

    public StatusTable getStatus() {
        return status;
    }

    public void setStatus(StatusTable status) {
        this.status = status;
    }

    public String getTitulo() {
        titulo = status == StatusTable.INCLUSAO ? "Cadastro de Instituição" : "Alteração de Instituição";
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    
    

}
