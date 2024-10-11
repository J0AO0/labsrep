package com.mei.vendasapi.service;


import com.mei.vendasapi.domain.*;
import com.mei.vendasapi.security.MEISecurity;
import com.mei.vendasapi.service.util.Tenantuser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
public class LogSistemaService {
    @Autowired
	private MEISecurity meiSecurity;
    
    @Autowired
    private Tenantuser tenantUsuario;

	public LogSistema insert(Categoria obj, String acao) {
        String usuarioLogado = meiSecurity.getUsuario();
        Categoria cat = new Categoria();
        
        cat.setTenant(tenantUsuario.buscarOuFalhar());
	    String comando = (acao + "  " + obj.toString());
		LogSistema log = new LogSistema(null,usuarioLogado,comando,OffsetDateTime.now(),obj, cat.getTenant());
		return log;		
	}
	public LogSistema insert(Cliente obj, String acao) {
        String usuarioLogado = meiSecurity.getUsuario();
	    String comando = (acao + "  " + obj.toString());
		LogSistema log = new LogSistema(null,usuarioLogado,comando,OffsetDateTime.now(),obj);
		return log;		
	}

	public LogSistema insert(Pedido obj, String acao) {
        String usuarioLogado = meiSecurity.getUsuario();
	    String comando = (acao + "  " + obj.toString());
		LogSistema log = new LogSistema(null,usuarioLogado,comando,OffsetDateTime.now(),obj);
		return log;		
	}
	
	public LogSistema insert(Produto obj, String acao) {
        String usuarioLogado = meiSecurity.getUsuario();
	    String comando = (acao + "  " + obj.toString());
	    Produto prod = new Produto();
	    
	    prod.setTenant(tenantUsuario.buscarOuFalhar());
	    
		LogSistema log = new LogSistema(null,usuarioLogado,comando,OffsetDateTime.now(),obj);
		return log;		
	}

	public LogSistema insert(Usuario obj, String string) {
        String usuarioLogado = meiSecurity.getUsuario();
        
	    String comando = (string + "  " + obj.toString());
		LogSistema log = new LogSistema(null,usuarioLogado,comando,OffsetDateTime.now(),obj);
		return log;	
	}
	public LogSistema insert(Empresa obj, String acao) {
		String usuarioLogado = meiSecurity.getUsuario();
	    String comando = (acao + obj.toString());
		LogSistema log = new LogSistema(null,usuarioLogado,comando,OffsetDateTime.now(),obj);
		return log;
	}

	public LogSistema insert(FormaPagamento obj, String acao) {
		String usuarioLogado = meiSecurity.getUsuario();
		FormaPagamento formaPagamento = new FormaPagamento();
		formaPagamento.setTenant(tenantUsuario.buscarOuFalhar());

	    String comando = (acao + obj.toString());
		LogSistema log = new LogSistema(null,usuarioLogado,comando,OffsetDateTime.now(),obj);
		return log;
	}


	public LogSistema insert(TipoFrete obj, String acao) {
		String usuarioLogado = meiSecurity.getUsuario();
		TipoFrete tipoFrete = new TipoFrete();
		tipoFrete.setTenant(tenantUsuario.buscarOuFalhar());
	    String comando = (acao + obj.toString());
		LogSistema log = new LogSistema(null,usuarioLogado,comando,OffsetDateTime.now(),obj);
		return log;
	}
	public LogSistema insert(CondPagamento obj, String acao) {
		String usuarioLogado = meiSecurity.getUsuario();
		CondPagamento condPagamento = new CondPagamento();
		condPagamento.setTenant(tenantUsuario.buscarOuFalhar());
	    String comando = (acao + obj.toString());
		LogSistema log = new LogSistema(null,usuarioLogado,comando,OffsetDateTime.now(),obj);
		return log;
	}




}
