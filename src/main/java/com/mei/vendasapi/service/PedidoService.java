package com.mei.vendasapi.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mei.vendasapi.domain.Cliente;
import com.mei.vendasapi.domain.Pedido;
import com.mei.vendasapi.domain.dto.PedidoDTO;
import com.mei.vendasapi.domain.dto.PedidoNewDTO;
import com.mei.vendasapi.repository.ClienteRepository;
import com.mei.vendasapi.repository.PedidoRepository;
import com.mei.vendasapi.service.exception.EntidadeEmUsoException;
import com.mei.vendasapi.service.exception.EntidadeNaoEncontradaExcepition;

@Service
public class PedidoService {
    @Autowired
    private PedidoRepository repo;
    
    @Autowired
    private ClienteRepository clienteRepo;

    public Page<Pedido> findAll(Pageable pageable) {
        return repo.findAll(pageable);
    }

    public Pedido findPorId(Integer id) {
        Pedido cat = repo.findPorId(id);
        return cat;
    }
    
    @Transactional
    public Pedido insert(PedidoNewDTO obj){
    	obj.setId(null);
        Pedido resEst = new Pedido();
        Cliente c = obj.getCliente();
        resEst.setCliente(c);
        return repo.save(resEst);
    }

    public Pedido atualiza(PedidoDTO obj) {
    	try {
        Pedido resEst =  repo.findPorId(obj.getId());
        Cliente cli =  clienteRepo.findPorId(obj.getCliente().getId());
			
        BeanUtils.copyProperties(obj, resEst, "id");
        return repo.save(resEst);
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeNaoEncontradaExcepition(String.format("Cliente não encontrado", obj.getId()));
		}
    }

    public void delete (Integer id) {
    	try {
    		if(!repo.existsById(id)) {
    		throw new EntidadeNaoEncontradaExcepition(String.format("Pedido não encontrada", id));
    		}
    		repo.deleteById(id);
    		
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format("Pedido não pode ser deletado tem relacionamento com Cliente", id));
		}
    }

    public List<Pedido> lista() {

        List<Pedido> buscarTodas = repo.findAllCat();
        return buscarTodas;
    }

    public Pedido buscarOuFalhar(int id) {
        return repo.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaExcepition(String.format("Pedido  não encontrado", id)));
    }
}
