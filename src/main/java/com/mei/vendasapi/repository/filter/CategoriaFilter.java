package com.mei.vendasapi.repository.filter;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class CategoriaFilter {
    private Integer id;
    private String nome;
    private String status;
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
