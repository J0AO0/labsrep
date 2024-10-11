package com.mei.vendasapi.domain.dto.flat;

import com.mei.vendasapi.domain.TipoFrete;
import com.mei.vendasapi.domain.Tenant;
import com.mei.vendasapi.domain.TipoFrete;

import javax.persistence.ManyToOne;
import java.time.OffsetDateTime;

public class TipoFreteFlat {

    private Integer id;
    private String descricao;
    private Boolean status = Boolean.TRUE;
    private OffsetDateTime datagravacao;
    private String emailusuario;

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

    public TipoFreteFlat(Integer id, String descricao, Boolean status, OffsetDateTime datagravacao, String emailusuario) {
        this.id = id;
        this.descricao = descricao;
        this.status = status;
        this.datagravacao = datagravacao;
        this.emailusuario = emailusuario;
    }

    public TipoFreteFlat() {
    }

    public TipoFreteFlat(TipoFrete cat, String ok) {
        this.id = cat.getId();
        this.descricao = cat.getDescricao();
        this.status = cat.getStatus();
        this.datagravacao = cat.getLogs().getDatagravacao();
        this.emailusuario = cat.getLogs().getEmailUsuario();
    }

    public TipoFreteFlat(TipoFrete cat) {
        this.id = cat.getId();
        this.descricao = cat.getDescricao();
        this.status = cat.getStatus();
        this.datagravacao = cat.getLogs().getDatagravacao();
        this.emailusuario = cat.getLogs().getEmailUsuario();
    }
}
