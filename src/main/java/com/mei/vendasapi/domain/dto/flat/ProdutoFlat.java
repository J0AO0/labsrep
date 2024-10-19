package com.mei.vendasapi.domain.dto.flat;

import com.mei.vendasapi.domain.Categoria;
import com.mei.vendasapi.domain.Produto;

import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class ProdutoFlat {
    private Integer id;
    private String name;
    private Categoria categoria;
    private BigDecimal preco;
    private String descricao;
    private Boolean status;
    private String qrCode;
    private OffsetDateTime datagravacao;
    private String emailusuario;
    
    private String sku;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public OffsetDateTime getDatagravacao() {
        return datagravacao;
    }

    public void setDatagravacao(OffsetDateTime datagravacao) {
        this.datagravacao = datagravacao;
    }

    public String getEmailusuario() {
        return emailusuario;
    }

    public void setEmailusuario(String emailusuario) {
        this.emailusuario = emailusuario;
    }

    public ProdutoFlat(Integer id, String name, Categoria categoria, BigDecimal preco, String descricao, Boolean status, String qrCode, OffsetDateTime datagravacao, String emailusuario,
    		String sku) {
        this.id = id;
        this.name = name;
        this.categoria = categoria;
        this.preco = preco;
        this.descricao = descricao;
        this.status = status;
        this.qrCode = qrCode;
        this.datagravacao = datagravacao;
        this.emailusuario = emailusuario;
        this.sku = sku;
    }
    public ProdutoFlat() {
    }

    public ProdutoFlat(Produto prod, String ok){
        this.id = prod.getId();
        this.name = prod.getName();
        this.categoria = prod.getCategoria();
        this.preco = prod.getPreco();
        this.descricao = prod.getDescricao();
        this.status = prod.getStatus();
        this.qrCode = prod.getQrCode();
        this.datagravacao = prod.getLogs().getDatagravacao();
        this.emailusuario = prod.getLogs().getEmailUsuario();
        this.sku = prod.getSku();
    }

    public ProdutoFlat(Produto prod){
        this.id = prod.getId();
        this.name = prod.getName();
        this.categoria = prod.getCategoria();
        this.preco = prod.getPreco();
        this.descricao = prod.getDescricao();
        this.status = prod.getStatus();
        this.qrCode = prod.getQrCode();
        this.datagravacao = prod.getLogs().getDatagravacao();
        this.emailusuario = prod.getLogs().getEmailUsuario();
        this.sku = prod.getSku();
    }
}
