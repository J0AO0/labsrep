package com.mei.vendasapi.resource;

import com.mei.vendasapi.domain.CondPagamento;
import com.mei.vendasapi.domain.dto.CondPagamentoDTO;
import com.mei.vendasapi.domain.dto.CondPagamentoNewDTO;
import com.mei.vendasapi.domain.dto.flat.CondPagamentoFlat;
import com.mei.vendasapi.repository.CondPagamentoRepository;
import com.mei.vendasapi.service.CondPagamentoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@Controller
@RequestMapping(value = "/condPagamento")
public class CondPagamentoResource {

    @Autowired
    private CondPagamentoService condPagamentoService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CondPagamentoRepository catRepo;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> lista() {

        List<CondPagamento> lista =  condPagamentoService.lista();
        return ResponseEntity.ok(lista);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id) {
        CondPagamento obj = condPagamentoService.buscarOuFalhar(id);
        return ResponseEntity.ok(obj);
    }


    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<CondPagamento> criarCondPagamento(@Valid @RequestBody CondPagamentoNewDTO objNewDTO) {
        CondPagamento novoObj = modelMapper.map(objNewDTO, CondPagamento.class);
        CondPagamento objNovo = condPagamentoService.insert(novoObj);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
                path("/{id}").buildAndExpand(objNovo.getId()).toUri();

        return ResponseEntity.created(uri).body(novoObj);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<CondPagamento> update(@Valid @RequestBody CondPagamentoDTO obj, @PathVariable Integer id) {
        obj.setId(id);
        CondPagamento novoobj = new CondPagamento(obj);
        CondPagamento obj1 = condPagamentoService.atualiza(novoobj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
                path("/{id}").buildAndExpand(obj1.getId()).toUri();
        return ResponseEntity.created(uri).body(obj1);

    }

    @RequestMapping(value ="/{id}/status",method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void inativar(@RequestBody Boolean obj,@PathVariable int id)	{
        condPagamentoService.status(obj,id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        condPagamentoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}