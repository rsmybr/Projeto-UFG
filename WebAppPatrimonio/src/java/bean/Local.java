/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

/**
 *
 * @author Renilson
 */
public class Local {
    private Integer codlocal;
    private String descricao;
    private Integer codinstituicao;
    private String descinstituicao;
    
    
    //Construtor
    public Local(){
        
    }
    
    public Local(Integer codlocal, Integer codinstituicao, String descricao, String descinstituicao){
        this.codlocal = codlocal;
        this.codinstituicao = codinstituicao;
        this.descricao = descricao;
        this.descinstituicao = descinstituicao;
    }

    public Integer getCodlocal() {
        return codlocal;
    }

    public void setCodlocal(Integer codlocal) {
        this.codlocal = codlocal;
    }

    public Integer getCodinstituicao() {
        return codinstituicao;
    }

    public void setCodinstituicao(Integer codinstituicao) {
        this.codinstituicao = codinstituicao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescinstituicao() {
        return descinstituicao;
    }

    public void setDescinstituicao(String descinstituicao) {
        this.descinstituicao = descinstituicao;
    }
    
}
