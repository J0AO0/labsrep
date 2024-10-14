package com.mei.vendasapi.resource;

import com.mei.vendasapi.domain.TipoFrete;
import com.mei.vendasapi.domain.dto.TipoFreteDTO;
import com.mei.vendasapi.domain.dto.TipoFreteNewDTO;
import com.mei.vendasapi.domain.dto.flat.CondPagamentoFlat;
import com.mei.vendasapi.domain.dto.flat.FormaPagamentoFlat;
import com.mei.vendasapi.domain.dto.flat.TipoFreteFlat;
import com.mei.vendasapi.repository.TipoFreteRepository;
import com.mei.vendasapi.repository.TipoFreteRepository;
import com.mei.vendasapi.security.resource.CheckSecurity;
import com.mei.vendasapi.service.TipoFreteService;
import com.mei.vendasapi.service.TipoFreteService;
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
@RequestMapping(value = "/tipoFrete")
public class TipoFreteResource {

    @Autowired
    private TipoFreteService tipoFreteService;

    @Autowired
    private ModelMapper modelMapper;

    @CheckSecurity.TipoFrete.PodeConsultar
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> lista() {

    	List<TipoFreteFlat> list = tipoFreteService.findAllSql();
        return ResponseEntity.ok(list);
    }

    @CheckSecurity.TipoFrete.PodeConsultar
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id) {
        TipoFrete obj = tipoFreteService.buscarOuFalhar(id);
        return ResponseEntity.ok(obj);
    }


    @CheckSecurity.TipoFrete.PodeCadastrar
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<TipoFrete> criarTipoFrete(@Valid @RequestBody TipoFreteNewDTO objNewDTO) {
        TipoFrete novoObj = modelMapper.map(objNewDTO, TipoFrete.class);
        TipoFrete objNovo = tipoFreteService.insert(objNewDTO);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
                path("/{id}").buildAndExpand(objNovo.getId()).toUri();

        return ResponseEntity.created(uri).body(novoObj);
    }

    @CheckSecurity.TipoFrete.PodeAtualizar
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<TipoFrete> update(@Valid @RequestBody TipoFreteDTO obj, @PathVariable Integer id) {
        obj.setId(id);
        TipoFrete novoobj = new TipoFrete(obj);
        TipoFrete obj1 = tipoFreteService.atualiza(novoobj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
                path("/{id}").buildAndExpand(obj1.getId()).toUri();
        return ResponseEntity.created(uri).body(obj1);

    }

    @CheckSecurity.TipoFrete.PodeAlterarStatus
    @RequestMapping(value ="/{id}/status",method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void inativar(@RequestBody Boolean obj,@PathVariable int id)	{
        tipoFreteService.status(obj,id);
    }

    @CheckSecurity.TipoFrete.PodeExcluir
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        tipoFreteService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @CheckSecurity.TipoFrete.PodeConsultar
	@RequestMapping(value = "/inativos", method = RequestMethod.GET)
	public ResponseEntity<List<TipoFreteFlat>> findAllInativo() {
		List<TipoFreteFlat> list = tipoFreteService.findAllSqlInativo();
		return ResponseEntity.ok().body(list);
	}
}
