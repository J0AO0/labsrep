package com.mei.vendasapi.domain;

import com.mei.vendasapi.domain.dto.ItemDTO;
import com.mei.vendasapi.domain.dto.ItemNewDTO;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Item implements Serializable{

	private static final long serialVersionUID = 1L;
	@EmbeddedId
    public ProdutoPedidoPK id = new ProdutoPedidoPK();
    private Integer quantidade;
    private Boolean status;
    
    @ManyToOne 
    private Tenant tenant;
    
    public Item() {
        super();
    }
    public Item(ProdutoPedidoPK id, Integer quantidade, Boolean status, Tenant tenant) {
        super();
        this.id = id;
        this.quantidade = quantidade;
        this.status = status;
        this.tenant = tenant;
    }
    public ProdutoPedidoPK getId() {
        return id;
    }
    public void setId(ProdutoPedidoPK id) {
        this.id = id;
    }
    public Integer getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
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

    public Item(ItemDTO obj){
        this.id = obj.getId();
        this.quantidade = obj.getQuantidade();
        this.status = obj.getStatus();
    }

    public Item(ItemNewDTO obj){
        this.id = obj.getId();
        this.quantidade = obj.getQuantidade();
        this.status = obj.getStatus();
    }
    
    
}
