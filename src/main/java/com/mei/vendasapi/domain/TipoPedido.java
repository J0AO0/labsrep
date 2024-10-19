package com.mei.vendasapi.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mei.vendasapi.domain.dto.ProdutoDTO;
import com.mei.vendasapi.domain.dto.ProdutoNewDTO;
import com.mei.vendasapi.domain.dto.TipoPedidoDTO;
import com.mei.vendasapi.domain.dto.TipoPedidoNewDTO;

import javax.persistence.*;
import javax.validation.Valid;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class TipoPedido implements Serializable {

	public static final Long seralVersionUID = 123456L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	private String nome;
	private Boolean status = Boolean.TRUE;

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



	public TipoPedido(Integer id, String nome, Boolean status, List<LogSistema> logs) {
		super();
		this.id = id;
		this.nome = nome;
		this.status = status;
		this.logs = logs;
	}
	

	public TipoPedido() {
	}

	public TipoPedido(@Valid TipoPedidoDTO obj) {
		this.id = obj.getId();
		this.nome = obj.getNome();

	}

	public TipoPedido(@Valid TipoPedidoNewDTO obj) {
		this.id = obj.getId();
		this.nome = obj.getNome();

	}

	@JsonIgnore
	@OneToMany(mappedBy = "tipopedido")
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
		if (indice == -1) {
			return ultimo;
		} else {
			return ultimo = logs.get(indice);
		}

	}

	public void setLogs(List<LogSistema> logs) {
		this.logs = logs;
	}

}
