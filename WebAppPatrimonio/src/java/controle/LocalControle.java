/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import bean.Instituicao;
import bean.Local;
import dao.InstituicaoDAO;
import dao.LocalDAO;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;
import util.ConectaException;
import util.StatusTable;

/**
 *
 * @author Renilson
 */

@ManagedBean
@SessionScoped

public class LocalControle {
    private Local local;
    private LocalDAO localDao;
    private List<Local> locais;
    private String pesquisa;
    private StatusTable status;
    private String titulo;
    private List<SelectItem> listaInstituicoes;
    private InstituicaoDAO instituicaoDao;
    
    //Construtor
    public LocalControle() throws ConectaException {
        status = StatusTable.EXIBICAO;
        local = new Local();
        localDao = new LocalDAO();
        instituicaoDao = new InstituicaoDAO();
        this.pesquisa = "";
    }
    
    //Métodos de acesso ao Banco
    public String addLocal() throws ConectaException {
        status = StatusTable.EXIBICAO;
        localDao.incluir(local);
        return "local.jsf";
    }
    
    public String deleteLocal() throws ConectaException {
        localDao.excluir(local);
        return "local.jsf";
    }
    
    public String updateLocal() throws ConectaException {
        status = StatusTable.EXIBICAO;
        localDao.atualizar(local);
        return "local.jsf";
    }
    
    
    public void carregaInstituicoes() throws ConectaException {
        List<Instituicao> instituicoes = instituicaoDao.todasInstituicoes();
        this.listaInstituicoes = new ArrayList<SelectItem>();
        for (int i = 0; i <= instituicoes.size() - 1; i++) {
            this.listaInstituicoes.add(new SelectItem(instituicoes.get(i).getCodigo(),
                                                      instituicoes.get(i).getDescricao()));
        }
    }
    
    //Outros métodos
    public Boolean isModoInclusao(){
        return status == StatusTable.INCLUSAO;
    }
    
    public Boolean isModoAlteracao(){
        return status == StatusTable.ALTERACAO;
    }
    
    public String inclusaoLocal() throws ConectaException {
        status = StatusTable.INCLUSAO;
        carregaInstituicoes();
        local = new Local();
        return "iuLocal.jsf";
    }
    
    public String pesquisaLocal(){
        return "local.jsf";
    }
    
    public String editLocal() throws ConectaException {
        status = StatusTable.ALTERACAO;
        carregaInstituicoes();
        return "iuLocal.jsf";
    }
    
    public String cancelaEdicao(){
        return "local.jsf";
    }
    
    public String goMenu(){
        return "index?faces-redirect=true";
    }
    
    //Métodos Getters and Setters

    public Local getLocal() {
        return local;
    }

    public void setLocal(Local local) {
        this.local = local;
    }

    public LocalDAO getLocalDao() {
        return localDao;
    }

    public void setLocalDao(LocalDAO localDao) {
        this.localDao = localDao;
    }

    public List<Local> getLocais() throws ConectaException {
        locais = localDao.todosLocais(pesquisa);
        return locais;
    }

    public void setLocais(List<Local> locais) {
        this.locais = locais;
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
        titulo = status == StatusTable.INCLUSAO ? "Cadastro de Local" : "Alteração de Local";
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<SelectItem> getListaInstituicoes() {
        return listaInstituicoes;
    }

    public void setListaInstituicoes(List<SelectItem> listaInstituicoes) {
        this.listaInstituicoes = listaInstituicoes;
    }

    public InstituicaoDAO getInstituicaoDao() {
        return instituicaoDao;
    }

    public void setInstituicaoDao(InstituicaoDAO instituicaoDao) {
        this.instituicaoDao = instituicaoDao;
    }
    
    
}
