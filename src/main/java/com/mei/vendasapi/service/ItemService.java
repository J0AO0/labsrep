package com.mei.vendasapi.service;

import com.mei.vendasapi.domain.Item;
import com.mei.vendasapi.domain.Cliente;
import com.mei.vendasapi.domain.Item;
import com.mei.vendasapi.domain.dto.ClienteDTO;
import com.mei.vendasapi.domain.dto.ClienteNewDTO;
import com.mei.vendasapi.domain.dto.ItemDTO;
import com.mei.vendasapi.domain.dto.ItemNewDTO;
import com.mei.vendasapi.repository.ClienteRepository;
import com.mei.vendasapi.repository.ItemRepository;
import com.mei.vendasapi.service.exception.EntidadeNaoEncontradaExcepition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ItemService {
    @Autowired
    private ItemRepository repo;

    public Page<Item> findAll(Pageable pageable) {
        return repo.findAll(pageable);
    }

    public Item findPorId(Integer id) {
        Item cat = repo.findPorId(id);
        return cat;
    }
    
    @Transactional
    public Item insert(ItemNewDTO obj){
    	obj.setId(null);
        Item resEst = new Item(obj);
        resEst.setQuantidade(obj.getQuantidade());
        resEst.setStatus(obj.getStatus());
        
        return repo.save(resEst);
    }

//    public Item atualiza(ItemDTO obj) {
//        Item resEst =  repo.findPorId(obj.getId());
//        resEst.setQuantidade(obj.getQuantidade());
//        resEst.setStatus(obj.getStatus());
//        return repo.save(resEst);
//    }

    public void delete (Integer id) {
        repo.deleteById(id);
    }

    public List<Item> lista() {

        List<Item> buscarTodas = repo.findAllCat();
        return buscarTodas;
    }

    public Item buscarOuFalhar(int id) {
        return repo.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaExcepition(String.format("Item não encontrado", id)));
    }

    @Transactional
    public void status(Boolean obj, int id) {
        Item item = buscarOuFalhar(id);
        item.setStatus(obj);

    }
}
