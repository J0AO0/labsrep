package com.mei.vendasapi.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mei.vendasapi.domain.dto.PedidoDTO;
import com.mei.vendasapi.domain.dto.PedidoNewDTO;
import com.mei.vendasapi.domain.dto.ProdutoDTO;
import com.mei.vendasapi.domain.dto.ProdutoNewDTO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.validation.Valid;

@Entity
public class Produto implements Serializable {


	public static final Long seralVersionUID = 123456L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    
    private String name;
    
    @ManyToOne
    private Categoria categoria;
    
    private BigDecimal preco;
    
    private String descricao;
    
    private Boolean status;

	private String qrCode;

    @ManyToOne
    private Tenant tenant;

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

	public Tenant getTenant() {
		return tenant;
	}

	public void setTenant(Tenant tenant) {
		this.tenant = tenant;
	}

	public Produto(Integer id, String name, Categoria categoria, BigDecimal preco, String descricao, Boolean status, Tenant tenant, String qrCode) {
		super();
		this.id = id;
		this.name = name;
		this.categoria = categoria;
		this.preco = preco;
		this.descricao = descricao;
		this.status = status;
		this.tenant = tenant;
		this.qrCode = qrCode;
	}

	public Produto() {
		super();
	}

	public Produto(@Valid ProdutoDTO obj){
		this.id = obj.getId();
		this.name = obj.getName();
		this.categoria = obj.getCategoria();
		this.preco = obj.getPreco();
		this.descricao = obj.getDescricao();
		this.qrCode = obj.getQrCode();
	}

	public Produto(@Valid ProdutoNewDTO obj){
		this.id = obj.getId();
		this.name = obj.getName();
		this.categoria = obj.getCategoria();
		this.preco = obj.getPreco();
		this.descricao = obj.getDescricao();
		this.status = obj.getStatus();
		this.qrCode = obj.getQrCode();
	}

	@Override
	public String toString() {
		return "Produto [id=" + id + ", descricao=" + descricao + ", status=" + status + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@JsonIgnore
	@OneToMany(mappedBy = "produto")
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
