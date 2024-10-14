package com.mei.vendasapi.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mei.vendasapi.domain.dto.TipoFreteDTO;
import com.mei.vendasapi.domain.dto.TipoFreteNewDTO;
import com.mei.vendasapi.domain.dto.TipoFreteDTO;

import javax.persistence.*;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Entity
public class TipoFrete {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    private String descricao;
    @ManyToOne
    private Tenant tenant;
    private Boolean status = Boolean.TRUE;

    public TipoFrete() {
    }

    public TipoFrete(Integer id, String descricao, Tenant tenant) {
        this.id = id;
        this.descricao = descricao;
        this.tenant = tenant;
        this.status = Boolean.TRUE;
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

    public TipoFrete(TipoFreteDTO obj) {
        this.id = obj.getId();
        this.descricao = obj.getDescricao();
        this.status = obj.getStatus();
    }

    public TipoFrete(@Valid TipoFreteNewDTO obj) {
        this.id = obj.getId();
        this.descricao = obj.getDescricao();
        this.status = obj.getStatus();
    }

    @JsonIgnore
    @OneToMany(mappedBy = "tipoFrete")
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
