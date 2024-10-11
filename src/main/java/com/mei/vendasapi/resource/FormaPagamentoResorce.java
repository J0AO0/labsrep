package com.mei.vendasapi.resource;

import com.mei.vendasapi.domain.Categoria;
import com.mei.vendasapi.domain.FormaPagamento;
import com.mei.vendasapi.domain.dto.CategoriaDTO;
import com.mei.vendasapi.domain.dto.CategoriaNewDTO;
import com.mei.vendasapi.domain.dto.FormaPagamentoDTO;
import com.mei.vendasapi.domain.dto.FormaPagamentoNewDTO;
import com.mei.vendasapi.domain.dto.flat.CategoriaFlat;
import com.mei.vendasapi.repository.FormaPagamentoRepository;
import com.mei.vendasapi.repository.filter.CategoriaFilter;
import com.mei.vendasapi.service.FormaPagamentoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/formapagamentos")
public class FormaPagamentoResorce {

    @Autowired
    public FormaPagamentoRepository formaPagamentoRepository;

    @Autowired
    public FormaPagamentoService formaPagamentoService;

    @Autowired
    private ModelMapper modelMapper;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> lista() {

        List<FormaPagamento> lista =  formaPagamentoService.lista();
        return ResponseEntity.ok(lista);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id) {
        FormaPagamento obj = formaPagamentoService.buscarOuFalhar(id);
        return ResponseEntity.ok(obj);
    }


    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<FormaPagamento> criar(@Valid @RequestBody FormaPagamentoNewDTO objNewDTO) {
        FormaPagamento novoObj = modelMapper.map(objNewDTO, FormaPagamento.class);
        FormaPagamento objNovo = formaPagamentoService.insert(objNewDTO);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
                path("/{id}").buildAndExpand(objNovo.getId()).toUri();

        return ResponseEntity.created(uri).body(novoObj);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<FormaPagamento> update(@Valid @RequestBody FormaPagamentoDTO obj, @PathVariable Integer id) {
        obj.setId(id);
        FormaPagamento novoobj = new FormaPagamento(obj);
        FormaPagamento obj1 = formaPagamentoService.atualiza(novoobj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
                path("/{id}").buildAndExpand(obj1.getId()).toUri();
        return ResponseEntity.created(uri).body(obj1);

    }

    @RequestMapping(value ="/{id}/status",method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void inativar(@RequestBody Boolean obj,@PathVariable int id)	{
        formaPagamentoService.status(obj,id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        formaPagamentoService.delete(id);
        return ResponseEntity.noContent().build();
    }
    
}