package com.mei.vendasapi.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mei.vendasapi.domain.dto.FormaPagamentoDTO;
import org.jfree.util.Log;

import javax.persistence.*;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Entity
public class FormaPagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String descricao;
    private Boolean status = Boolean.TRUE;

    @ManyToOne
    private Tenant tenant;

    @JsonIgnore
    @OneToMany(mappedBy = "formaPagamento")
    private List<LogSistema> logs = new ArrayList<LogSistema>();

    public FormaPagamento() {}

    public FormaPagamento(Integer id, String descricao, Boolean status, Tenant tenant) {  }

    public FormaPagamento(@Valid FormaPagamentoDTO obj) {
        this.id = obj.getId();
        this.descricao = obj.getDescricao();
        this.status = obj.getStatus();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Tenant getTenant() {
        return tenant;
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }

    public void addLogs(LogSistema log) {
        logs.add(log);
    }

    public LogSistema getLogs() {
        Integer codigo = 0;
        Integer indice = -1;
        LogSistema ultimo = new LogSistema();
        for (int i = 0; i < logs.size(); i++) {
            if (codigo < logs.get(i).getId()) {
                codigo = logs.get(i).getId();
                indice = i;
            }
        }
        if (indice == -1) {
            return ultimo;
        } else {
            return ultimo = logs.get(indice);
        }
    }
}

