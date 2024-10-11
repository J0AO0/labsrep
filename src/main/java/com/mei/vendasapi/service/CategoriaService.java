package com.mei.vendasapi.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import com.mei.vendasapi.domain.dto.flat.CategoriaFlat;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.mei.vendasapi.domain.Categoria;
import com.mei.vendasapi.domain.LogSistema;
import com.mei.vendasapi.domain.dto.CategoriaDTO;
import com.mei.vendasapi.domain.dto.CategoriaNewDTO;
import com.mei.vendasapi.repository.CategoriaRepository;
import com.mei.vendasapi.repository.LogSistemaRepository;
import com.mei.vendasapi.service.exception.EntidadeNaoEncontradaExcepition;
import com.mei.vendasapi.service.util.Tenantuser;

@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository repo;
    
    @Autowired
    private Tenantuser tenantUsuario;
    
	@Autowired
	private LogSistemaRepository repolog;

	@Autowired
	private LogSistemaService log;

    public Page<Categoria> findAll(Pageable pageable) {
        return repo.findAll(pageable);
    }

    public Categoria findPorId(Integer id) {
        Categoria cat = repo.findPorId(id);
        
        
        return cat;
    }

    public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
        return repo.findAll(pageRequest);
    }

    @Transactional
    public Categoria insert(CategoriaNewDTO obj){
    	obj.setId(null);
         Categoria resEst = new Categoria();
         resEst.setNome(obj.getNome());
         resEst.setStatus(obj.getStatus());
         resEst.setTenant(tenantUsuario.buscarOuFalhar());
         repo.save(resEst);
         logCategoria(resEst, "insert");
         return resEst;
    }

    public Categoria atualiza(Categoria obj) {
        Categoria resEst =  repo.findPorId(obj.getId());
        resEst.setTenant(tenantUsuario.buscarOuFalhar());

        BeanUtils.copyProperties(obj, resEst, "id");
        resEst.setTenant(tenantUsuario.buscarOuFalhar());
        logCategoria(resEst, "Update");
        return repo.save(resEst);
    }

    public void delete (Integer id) {
    	try {
    		repo.deleteById(id);
			
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaExcepition(String.format("Categoria não encontrada", id));
		}
    }

	public List<Categoria> lista() {
		
		List<Categoria> buscarTodas = repo.findAllCat();		
		return buscarTodas;
	}
	
	public Categoria buscarOuFalhar(int id) {
		return repo.findById(id)
				.orElseThrow(() -> new EntidadeNaoEncontradaExcepition(String.format("Categoria  não encontrada", id)));
	}
	
	@Transactional
	public void status(Boolean obj, int id) {
	Categoria categoria = buscarOuFalhar(id);
	categoria.setStatus(obj);
		
	}

    public Page<CategoriaFlat> mudarCategoriaParaFlat(Page<Categoria> pacs) {
        List<CategoriaFlat> cFlats = new ArrayList<CategoriaFlat>();
        for (Categoria c : pacs.getContent()) {
            CategoriaFlat cFlat = new CategoriaFlat(c);
            cFlats.add(cFlat);

        }
        Page<CategoriaFlat> page = new PageImpl<>(cFlats, pacs.getPageable(),
                pacs.getTotalElements());

        return page;
    }

	private void logCategoria(Categoria obj, String string) {
		LogSistema logsistema = log.insert(obj, string);
		logsistema.setCategoria(obj);
		repolog.save(logsistema);

	}
}
