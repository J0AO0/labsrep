package com.mei.vendasapi.service;

import com.mei.vendasapi.domain.TipoFrete;
import com.mei.vendasapi.domain.LogSistema;
import com.mei.vendasapi.domain.dto.TipoFreteNewDTO;
import com.mei.vendasapi.domain.dto.flat.TipoFreteFlat;
import com.mei.vendasapi.repository.TipoFreteRepository;
import com.mei.vendasapi.repository.LogSistemaRepository;
import com.mei.vendasapi.repository.TipoFreteRepository;
import com.mei.vendasapi.service.exception.EntidadeNaoEncontradaExcepition;
import com.mei.vendasapi.service.util.Tenantuser;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class TipoFreteService {

    @Autowired
    private TipoFreteRepository repo;

    @Autowired
    private Tenantuser tenantUsuario;

    @Autowired
    private LogSistemaRepository repolog;

    @Autowired
    private LogSistemaService log;

    public Page<TipoFrete> findAll(Pageable pageable) {
        return repo.findAll(pageable);
    }

    public TipoFrete findPorId(Integer id) {
        TipoFrete cat = repo.findPorId(id);


        return cat;
    }

    public Page<TipoFrete> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return repo.findAll(pageRequest);
    }

    @Transactional
    public TipoFrete insert(TipoFreteNewDTO obj){
        obj.setId(null);
        TipoFrete resEst = new TipoFrete();
        resEst.setDescricao(obj.getDescricao());
        resEst.setStatus(obj.getStatus());
        resEst.setTenant(tenantUsuario.buscarOuFalhar());
        repo.save(resEst);
        logTipoFrete(resEst, "insert");
        return resEst;
    }

    public TipoFrete atualiza(TipoFrete obj) {
        TipoFrete resEst =  repo.findPorId(obj.getId());
        resEst.setTenant(tenantUsuario.buscarOuFalhar());

        BeanUtils.copyProperties(obj, resEst, "id");
        resEst.setTenant(tenantUsuario.buscarOuFalhar());
        logTipoFrete(resEst, "Update");
        return repo.save(resEst);
    }

    public void delete (Integer id) {
        try {
            repo.deleteById(id);

        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaExcepition(String.format("TipoFrete não encontrada", id));
        }
    }

    public List<TipoFrete> lista() {

        List<TipoFrete> buscarTodas = repo.findAllCat();
        return buscarTodas;
    }

    public TipoFrete buscarOuFalhar(int id) {
        return repo.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaExcepition(String.format("TipoFrete  não encontrada", id)));
    }

    @Transactional
    public void status(Boolean obj, int id) {
        TipoFrete categoria = buscarOuFalhar(id);
        categoria.setStatus(obj);

    }

    public Page<TipoFreteFlat> mudarTipoFreteParaFlat(Page<TipoFrete> pacs) {
        List<TipoFreteFlat> cFlats = new ArrayList<TipoFreteFlat>();
        for (TipoFrete c : pacs.getContent()) {
            TipoFreteFlat cFlat = new TipoFreteFlat(c);
            cFlats.add(cFlat);

        }
        Page<TipoFreteFlat> page = new PageImpl<>(cFlats, pacs.getPageable(),
                pacs.getTotalElements());

        return page;
    }

    private void logTipoFrete(TipoFrete obj, String string) {
        LogSistema logsistema = log.insert(obj, string);
        logsistema.setTipoFrete(obj);
        repolog.save(logsistema);

    }

}
