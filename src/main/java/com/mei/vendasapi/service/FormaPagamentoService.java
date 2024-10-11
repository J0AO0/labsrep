package com.mei.vendasapi.service;

import com.mei.vendasapi.domain.Categoria;
import com.mei.vendasapi.domain.FormaPagamento;
import com.mei.vendasapi.domain.LogSistema;
import com.mei.vendasapi.domain.dto.CategoriaNewDTO;
import com.mei.vendasapi.domain.dto.FormaPagamentoNewDTO;
import com.mei.vendasapi.domain.dto.flat.CategoriaFlat;
import com.mei.vendasapi.repository.FormaPagamentoRepository;
import com.mei.vendasapi.repository.LogSistemaRepository;
import com.mei.vendasapi.service.exception.EntidadeNaoEncontradaExcepition;
import com.mei.vendasapi.service.util.Tenantuser;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class FormaPagamentoService {
    @Autowired
    public FormaPagamentoRepository formaPagamentoRepository;


    @Autowired
    private Tenantuser tenantUsuario;

    @Autowired
    private LogSistemaRepository repolog;

    @Autowired
    private LogSistemaService log;

    public List<FormaPagamento> listar() {
        return formaPagamentoRepository.findAll();
    }


    @Transactional
    public FormaPagamento insert(FormaPagamentoNewDTO obj){
        obj.setId(null);
        FormaPagamento resEst = new FormaPagamento();
        resEst.setDescricao(obj.getDescricao());
        resEst.setStatus(obj.getStatus());
        resEst.setTenant(tenantUsuario.buscarOuFalhar());
        formaPagamentoRepository.save(resEst);
        logFormaPagamento(resEst, "insert");
        return resEst;
    }

    public FormaPagamento atualiza(FormaPagamento obj) {
        FormaPagamento resEst =  formaPagamentoRepository.findPorId(obj.getId());
        resEst.setTenant(tenantUsuario.buscarOuFalhar());

        BeanUtils.copyProperties(obj, resEst, "id");
        resEst.setTenant(tenantUsuario.buscarOuFalhar());
        logFormaPagamento(resEst, "Update");
        return formaPagamentoRepository.save(resEst);
    }

    public void delete (Integer id) {
        try {
            formaPagamentoRepository.deleteById(id);

        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaExcepition(String.format("Forma Pagamento não encontrada", id));
        }
    }

    public List<FormaPagamento> lista() {

        List<FormaPagamento> buscarTodas = formaPagamentoRepository.findAllForm();
        return buscarTodas;
    }

    public FormaPagamento buscarOuFalhar(int id) {
        return formaPagamentoRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaExcepition(String.format("Forma Pagamento  não encontrada", id)));
    }

    @Transactional
    public void status(Boolean obj, int id) {
        FormaPagamento formaPagamento = buscarOuFalhar(id);
        formaPagamento.setStatus(obj);

    }

    private void logFormaPagamento(FormaPagamento obj, String string) {
        LogSistema logsistema = log.insert(obj, string);
        logsistema.setFormaPagamento(obj);
        repolog.save(logsistema);

    }
}
