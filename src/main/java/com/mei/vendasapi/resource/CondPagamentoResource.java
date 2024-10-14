package com.mei.vendasapi.resource;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import com.mei.vendasapi.security.resource.CheckSecurity;
import org.hibernate.annotations.Check;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mei.vendasapi.domain.CondPagamento;
import com.mei.vendasapi.domain.dto.CondPagamentoDTO;
import com.mei.vendasapi.domain.dto.CondPagamentoNewDTO;
import com.mei.vendasapi.domain.dto.flat.CondPagamentoFlat;
import com.mei.vendasapi.domain.dto.flat.FormaPagamentoFlat;
import com.mei.vendasapi.service.CondPagamentoService;

@Controller
@RequestMapping(value = "/condPagamento")
public class CondPagamentoResource {

    @Autowired
    private CondPagamentoService condPagamentoService;

    @Autowired
    private ModelMapper modelMapper;

    @CheckSecurity.CondPagamento.PodeConsultar
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> lista() {
    	List<CondPagamentoFlat> list = condPagamentoService.findAllSql();

        return ResponseEntity.ok(list);
    }

    @CheckSecurity.CondPagamento.PodeConsultar
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id) {
        CondPagamento obj = condPagamentoService.buscarOuFalhar(id);
        return ResponseEntity.ok(obj);
    }

    @CheckSecurity.CondPagamento.PodeCadastrar
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<CondPagamento> criarCondPagamento(@Valid @RequestBody CondPagamentoFlat objNewDTO) {
        CondPagamento novoObj = modelMapper.map(objNewDTO, CondPagamento.class);
        CondPagamento objNovo = condPagamentoService.insert(objNewDTO);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
                path("/{id}").buildAndExpand(objNovo.getId()).toUri();

        return ResponseEntity.created(uri).body(novoObj);
    }

    @CheckSecurity.CondPagamento.PodeAtualizar
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<CondPagamento> update(@Valid @RequestBody CondPagamentoDTO obj, @PathVariable Integer id) {
        obj.setId(id);
        CondPagamento novoobj = new CondPagamento(obj);
        CondPagamento obj1 = condPagamentoService.atualiza(novoobj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
                path("/{id}").buildAndExpand(obj1.getId()).toUri();
        return ResponseEntity.created(uri).body(obj1);

    }

    @CheckSecurity.CondPagamento.PodeAlterarStatus
    @RequestMapping(value ="/{id}/status",method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void inativar(@RequestBody Boolean obj,@PathVariable int id)	{
        condPagamentoService.status(obj,id);
    }

    @CheckSecurity.CondPagamento.PodeExcluir
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        condPagamentoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @CheckSecurity.CondPagamento.PodeConsultar
	@RequestMapping(value = "/inativos", method = RequestMethod.GET)
	public ResponseEntity<List<CondPagamentoFlat>> findAllInativo() {
		List<CondPagamentoFlat> list = condPagamentoService.findAllSqlInativo();
		return ResponseEntity.ok().body(list);
	}

}
