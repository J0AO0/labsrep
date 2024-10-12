package com.mei.vendasapi.service;

import com.mei.vendasapi.domain.CondPagamento;
import com.mei.vendasapi.domain.LogSistema;
import com.mei.vendasapi.domain.dto.CondPagamentoNewDTO;
import com.mei.vendasapi.domain.dto.flat.CondPagamentoFlat;
import com.mei.vendasapi.repository.CondPagamentoRepository;
import com.mei.vendasapi.repository.LogSistemaRepository;
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
public class CondPagamentoService {
    @Autowired
    private CondPagamentoRepository repo;

    @Autowired
    private Tenantuser tenantUsuario;

    @Autowired
    private LogSistemaRepository repolog;

    @Autowired
    private LogSistemaService log;

    public Page<CondPagamento> findAll(Pageable pageable) {
        return repo.findAll(pageable);
    }

    public CondPagamento findPorId(Integer id) {
        CondPagamento cat = repo.findPorId(id);


        return cat;
    }

    public Page<CondPagamento> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return repo.findAll(pageRequest);
    }

    @Transactional
    public CondPagamento insert(CondPagamentoNewDTO obj){
        obj.setId(null);
        CondPagamento resEst = new CondPagamento(obj);
        resEst.setDescricao(obj.getDescricao());
        resEst.setStatus(obj.getStatus());
        resEst.setTenant(tenantUsuario.buscarOuFalhar());
        repo.save(resEst);
        logCondPagamento(resEst, "insert");
        return resEst;
    }

    public CondPagamento atualiza(CondPagamento obj) {
        CondPagamento resEst =  repo.findPorId(obj.getId());
        resEst.setTenant(tenantUsuario.buscarOuFalhar());

        BeanUtils.copyProperties(obj, resEst, "id");
        resEst.setTenant(tenantUsuario.buscarOuFalhar());
        logCondPagamento(resEst, "Update");
        return repo.save(resEst);
    }

    public void delete (Integer id) {
        try {
            repo.deleteById(id);

        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaExcepition(String.format("CondPagamento não encontrada", id));
        }
    }

    public List<CondPagamento> lista() {

        List<CondPagamento> buscarTodas = repo.findAllCat();
        return buscarTodas;
    }

    public CondPagamento buscarOuFalhar(int id) {
        return repo.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaExcepition(String.format("Condição de Pagamento  não encontrada", id)));
    }

    @Transactional
    public void status(Boolean obj, int id) {
        CondPagamento categoria = buscarOuFalhar(id);
        categoria.setStatus(obj);

    }

    public Page<CondPagamentoFlat> mudarCondPagamentoParaFlat(Page<CondPagamento> pacs) {
        List<CondPagamentoFlat> cFlats = new ArrayList<CondPagamentoFlat>();
        for (CondPagamento c : pacs.getContent()) {
            CondPagamentoFlat cFlat = new CondPagamentoFlat(c);
            cFlats.add(cFlat);

        }
        Page<CondPagamentoFlat> page = new PageImpl<>(cFlats, pacs.getPageable(),
                pacs.getTotalElements());

        return page;
    }

    private void logCondPagamento(CondPagamento obj, String string) {
        LogSistema logsistema = log.insert(obj, string);
        logsistema.setCondPagamento(obj);
        repolog.save(logsistema);

    }
}
