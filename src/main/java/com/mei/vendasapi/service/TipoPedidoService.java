package com.mei.vendasapi.service;

import com.mei.vendasapi.domain.TipoPedido;
import com.mei.vendasapi.domain.FormaPagamento;
import com.mei.vendasapi.domain.Produto;
import com.mei.vendasapi.domain.dto.TipoPedidoDTO;
import com.mei.vendasapi.domain.dto.TipoPedidoNewDTO;
import com.mei.vendasapi.domain.dto.flat.FormaPagamentoFlat;
import com.mei.vendasapi.domain.dto.flat.TipoPedidoFlat;
import com.mei.vendasapi.domain.dto.ProdutoDTO;
import com.mei.vendasapi.domain.dto.ProdutoNewDTO;
import com.mei.vendasapi.repository.TipoPedidoRepository;
import com.mei.vendasapi.repository.LogSistemaRepository;
import com.mei.vendasapi.repository.ProdutoRepository;
import com.mei.vendasapi.service.exception.EntidadeEmUsoException;
import com.mei.vendasapi.service.exception.EntidadeNaoEncontradaExcepition;
import com.mei.vendasapi.service.util.Tenantuser;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class TipoPedidoService {

    @Autowired
    private TipoPedidoRepository repo;
    
    @Autowired
    private Tenantuser tenantUsuario;

    @Autowired
    private LogSistemaRepository repolog;

    @Autowired
    private LogSistemaService log;


	public List<TipoPedidoFlat> findAllSql() {
		List<TipoPedido> operadores = repo.findAllSql(tenantUsuario.buscarOuFalharInt());
		List<TipoPedidoFlat> operadorFlat = new ArrayList<>();
		for (TipoPedido obj : operadores) {
			TipoPedidoFlat opeFlat = new TipoPedidoFlat(obj);
			operadorFlat.add(opeFlat);
		}
		return operadorFlat;
	}
    public TipoPedido findPorId(Integer id) {
        TipoPedido cat = repo.findPorId(id);


        return cat;
    }

    @Transactional
    public TipoPedido insert(TipoPedidoNewDTO obj){
        obj.setId(null);
        TipoPedido resEst = new TipoPedido();
        resEst.setNome(obj.getNome());
        return repo.save(resEst);
    }

    public TipoPedido atualiza(TipoPedidoDTO obj) {
        TipoPedido resEst =  repo.findPorId(obj.getId());
        resEst.setNome(obj.getNome());
        return repo.save(resEst);
    }

    public void delete (Integer id) {
        try {
            repo.deleteById(id);

        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaExcepition(String.format("TipoPedido não encontrada", id));
        }
    }

    public List<TipoPedido> lista() {

        List<TipoPedido> buscarTodas = repo.findAllCat();
        return buscarTodas;
    }

    public TipoPedido buscarOuFalhar(int id) {
        return repo.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaExcepition(String.format("Tipo de Pedido não encontrado", id)));
    }
}
