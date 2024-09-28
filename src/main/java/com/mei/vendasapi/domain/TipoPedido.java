package com.mei.vendasapi.domain;

import com.mei.vendasapi.domain.dto.ProdutoDTO;
import com.mei.vendasapi.domain.dto.ProdutoNewDTO;
import com.mei.vendasapi.domain.dto.TipoPedidoDTO;
import com.mei.vendasapi.domain.dto.TipoPedidoNewDTO;

import javax.persistence.*;
import javax.validation.Valid;
import java.io.Serializable;

@Entity
public class TipoPedido implements Serializable {

    public static final Long seralVersionUID = 123456L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    private String nome;

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

    public TipoPedido(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public TipoPedido() {
    }

    public TipoPedido(@Valid TipoPedidoDTO obj){
        this.id = obj.getId();
        this.nome = obj.getNome();

    }

    public TipoPedido(@Valid TipoPedidoNewDTO obj){
        this.id = obj.getId();
        this.nome = obj.getNome();

    }
}
