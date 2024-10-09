package com.mei.vendasapi.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mei.vendasapi.domain.dto.CategoriaDTO;
import com.mei.vendasapi.domain.dto.CategoriaNewDTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.validation.Valid;

@Entity
public class Categoria implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	private String nome;

	private Boolean status = Boolean.TRUE;
	@ManyToOne
	private Tenant tenant;

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

	
	public Categoria(Integer id, String nome, Boolean status, Tenant tenant) {
		this.id = id;
		this.nome = nome;
		this.status = status;
		this.tenant = tenant;
	}

	public Categoria() {
	}

	public Categoria(CategoriaDTO obj) {
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.status = obj.getStatus();
	}

	public Categoria(@Valid CategoriaNewDTO obj) {
		this.id = obj.getId();
		this.nome = obj.getNome();
	}

	@Override
	public String toString() {
		return "Categoria [id=" + id + ", nome=" + nome + ", status=" + status + "]";
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
		Categoria other = (Categoria) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@JsonIgnore
	@OneToMany(mappedBy = "categoria")
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
