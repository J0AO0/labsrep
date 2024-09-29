package com.mei.vendasapi.service;


import com.mei.vendasapi.domain.Empresa;
import com.mei.vendasapi.domain.Cliente;
import com.mei.vendasapi.domain.Empresa;
import com.mei.vendasapi.domain.dto.ClienteDTO;
import com.mei.vendasapi.domain.dto.ClienteNewDTO;
import com.mei.vendasapi.domain.dto.EmpresaDTO;
import com.mei.vendasapi.domain.dto.EmpresaNewDTO;
import com.mei.vendasapi.repository.ClienteRepository;
import com.mei.vendasapi.repository.EmpresaRepository;
import com.mei.vendasapi.service.exception.EntidadeNaoEncontradaExcepition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping(value = "/empresa")
public class EmpresaService {

    @Autowired
    private EmpresaRepository repo;

    public Page<Empresa> findAll(Pageable pageable) {
        return repo.findAll(pageable);
    }
    public Empresa findPorId(Integer id) {
        Empresa cat = repo.findPorId(id);
        return cat;
    }

    public Empresa insert(EmpresaNewDTO obj){
        Empresa resEst = new Empresa(obj);
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
}
