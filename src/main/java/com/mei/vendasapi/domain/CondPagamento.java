package com.mei.vendasapi.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mei.vendasapi.domain.dto.CondPagamentoDTO;
import com.mei.vendasapi.domain.dto.CondPagamentoNewDTO;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class CondPagamento {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    private String descricao;
    @ManyToOne
    private Tenant tenant;
    private Boolean status = Boolean.TRUE;

    public CondPagamento() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Tenant getTenant() {
        return tenant;
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }

    public Boolean getStatus() {
        return status;
    }



    public void setStatus(Boolean status) {
        this.status = status;
    }

    public CondPagamento(Integer id, String descricao, Tenant tenant, Boolean status) {
        this.id = id;
        this.descricao = descricao;
        this.tenant = tenant;
        this.status = status;
    }

    public CondPagamento(CondPagamentoDTO obj){
        this.id = obj.getId();
        this.descricao = obj.getDescricao();
    }

    public CondPagamento(CondPagamentoNewDTO obj){
        this.id = obj.getId();
        this.descricao = obj.getDescricao();
    }

    @JsonIgnore
    @OneToMany(mappedBy = "condPagamento")
    private List<LogSistema> logs = new ArrayList<LogSistema>();

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
        if (indice==-1) {
            return ultimo;
        }else {
            return ultimo = logs.get(indice);
        }

    }

    public void setLogs(List<LogSistema> logs) {
        this.logs = logs;
    }
}
