package com.mei.vendasapi.service;


import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import com.mei.vendasapi.domain.Categoria;
import com.mei.vendasapi.domain.LogSistema;
import com.mei.vendasapi.domain.Usuario;
import com.mei.vendasapi.domain.dto.flat.CategoriaFlat;
import com.mei.vendasapi.repository.LogSistemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mei.vendasapi.domain.Empresa;
import com.mei.vendasapi.domain.dto.EmpresaDTO;
import com.mei.vendasapi.domain.dto.EmpresaNewDTO;
import com.mei.vendasapi.domain.dto.flat.EmpresaFlat;
import com.mei.vendasapi.repository.EmpresaRepository;
import com.mei.vendasapi.service.exception.EntidadeNaoEncontradaExcepition;
import com.mei.vendasapi.service.util.Tenantuser;

@Service
public class EmpresaService {

    @Autowired
    private EmpresaRepository repo;

	@Autowired
	private Tenantuser tenantUsuario;

    @Autowired
    private LogSistemaRepository repolog;

    @Autowired
    private LogSistemaService log;

    public Page<Empresa> findAll(Pageable pageable) {
        return repo.findAll(pageable);
    }
    public Empresa findPorId(Integer id) {
        Empresa cat = repo.findPorId(id);
        return cat;
    }

    public Empresa insert(EmpresaNewDTO obj){
        Empresa resEst = new Empresa(obj);
        logEmpresa(resEst, "insert");
        return repo.save(resEst);
    }

    public Empresa atualiza(EmpresaDTO obj) {
        Empresa resEst =  repo.findPorId(obj.getId());
        resEst.setEmail(obj.getEmail());
        resEst.setTelefone(obj.getTelefone());
        resEst.setStatus(obj.getStatus());
        resEst.setBairro(obj.getBairro());
        resEst.setCidade(obj.getCidade());
        resEst.setComplemento(obj.getComplemento());
        resEst.setBairro(obj.getBairro());
        resEst.setCpfoucnpj(obj.getCpfoucnpj()); //
        resEst.setLogradouro(obj.getLogradouro());
        resEst.setNomecontato(obj.getNomecontato());
        resEst.setNumero(obj.getNumero());
        resEst.setRazaosocial(obj.getRazaosocial());
        resEst.setWhats(obj.getWhats());
        resEst.setValor(obj.getValor());
        resEst.setTelefone(obj.getTelefone());
        resEst.setStatus(obj.getStatus());
        resEst.setNaturezapessoa(obj.getNaturezapessoa());
        return repo.save(resEst);
    }

    public void delete (Integer id) {
        repo.deleteById(id);
    }

    public List<Empresa> lista() {

        List<Empresa> buscarTodas = repo.findAll();
        return buscarTodas;
    }

    public Empresa buscarOuFalhar(int id) {
        return repo.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaExcepition(String.format("EmpresaFlat  não encontrada", id)));
    }

    @Transactional
    public void status(Boolean obj, int id) {
        Empresa empresa = buscarOuFalhar(id);
        empresa.setStatus(obj);

    }
    
    
	public List<EmpresaFlat> findAllUsuario() {
		List<Empresa> empresas = repo.findAllSqlEmpUsuario(tenantUsuario.buscarUsuario().getId());
		List<EmpresaFlat> empresasF = new ArrayList<EmpresaFlat>();
		for (Empresa emp : empresas) {
			EmpresaFlat empFlat = new EmpresaFlat(emp);
			empresasF.add(empFlat);
		}

		return empresasF;
	}

    public Page<EmpresaFlat> mudarEmpresaParaFlat(Page<Empresa> pacs) {
        List<EmpresaFlat> cFlats = new ArrayList<EmpresaFlat>();
        for (Empresa c : pacs.getContent()) {
            EmpresaFlat cFlat = new EmpresaFlat(c);
            cFlats.add(cFlat);

        }
        Page<EmpresaFlat> page = new PageImpl<>(cFlats, pacs.getPageable(),
                pacs.getTotalElements());

        return page;
    }

    public Page<Empresa> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return repo.findAll(pageRequest);
    }

    private void logEmpresa(Empresa obj, String string) {
        LogSistema logsistema = log.insert(obj, string);
        logsistema.setEmpresa(obj);
        repolog.save(logsistema);

    }
    
}
