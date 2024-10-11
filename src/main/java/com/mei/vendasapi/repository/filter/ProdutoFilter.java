package com.mei.vendasapi.repository.filter;

import com.mei.vendasapi.domain.Categoria;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.util.Date;

public class ProdutoFilter {
    private Integer id;
    private String name;
    private Categoria categoria;
    private BigDecimal preco;
    private String descricao;
    private String status;
    private String qrCode;
    private String emailusuario;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date datagravacaode;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date datagravacaoate;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public String getEmailusuario() {
        return emailusuario;
    }

    public void setEmailusuario(String emailusuario) {
        this.emailusuario = emailusuario;
    }

    public Date getDatagravacaode() {
        return datagravacaode;
    }

    public void setDatagravacaode(Date datagravacaode) {
        this.datagravacaode = datagravacaode;
    }

    public Date getDatagravacaoate() {
        return datagravacaoate;
    }

    public void setDatagravacaoate(Date datagravacaoate) {
        this.datagravacaoate = datagravacaoate;
    }
}
