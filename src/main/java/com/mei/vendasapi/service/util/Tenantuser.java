package com.mei.vendasapi.service.util;

import com.mei.vendasapi.domain.Tenant;
import com.mei.vendasapi.domain.Usuario;
import com.mei.vendasapi.repository.TenantRepository;
import com.mei.vendasapi.repository.UsuarioRepository;

import com.mei.vendasapi.security.MEISecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class Tenantuser {
	@Autowired
	private MEISecurity MEISecurity;
	@Autowired
	private UsuarioRepository repoUsu;
	@Autowired
	private TenantRepository repotenant;

	public Tenant buscarOuFalhar() {
		String email = MEISecurity.getUsuario();
		Integer codigo = repoUsu.buscarTenant(email);
		Tenant t = repotenant.findPorId(codigo);		
		return t;
	}
	public String buscarEmailUsuToken() {
		String email = MEISecurity.getUsuario();
		return email;
	}
	public Integer buscarOuFalharGtenant() {
		String email = MEISecurity.getUsuario();
		Integer codigo = repoUsu.buscarGTenant(email);
		
		return codigo;
	}
	public Integer buscarOuFalharGtenantID() {
		String email = MEISecurity.getUsuario();
		Integer codigo = repoUsu.buscarGTenantId(email);		
		return codigo;
	}
	
	
	public Integer buscarOuFalharInt() {
		String email = MEISecurity.getUsuario();
		Integer codigo = repoUsu.buscarTenant(email);
		Tenant t = new Tenant();
		t.setId(codigo);
		return codigo;
	}
	
	public Usuario buscarUsuario() {
		String email = MEISecurity.getUsuario();
		Usuario usu = repoUsu.findPorEmail(email);	
		return usu;
	}
	
	public String tenantOuGtenant() {
		String retorno = "tenant_id";
		String email = MEISecurity.getUsuario();
		Usuario usu = repoUsu.findPorEmail(email);
		if(usu.getGtenantAtivo() == 0 ) {
			retorno = "tenant_id";
		}else {
			retorno = "gtenant_id";
		}
		return retorno;
	}
	public Integer buscarGtenantdoCadastrodeUsu() {
		String email = MEISecurity.getUsuario();
		Integer gtenant = repoUsu.findPorGtwenantPeloEmail(email);
		return gtenant;
	}

}
