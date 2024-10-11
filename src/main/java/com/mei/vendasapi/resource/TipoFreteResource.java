package com.mei.vendasapi.resource;

import com.mei.vendasapi.domain.TipoFrete;
import com.mei.vendasapi.domain.dto.TipoFreteDTO;
import com.mei.vendasapi.domain.dto.TipoFreteNewDTO;
import com.mei.vendasapi.domain.dto.flat.TipoFreteFlat;
import com.mei.vendasapi.repository.TipoFreteRepository;
import com.mei.vendasapi.repository.TipoFreteRepository;
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

    @Autowired
    private TipoFreteRepository tipRepo;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> lista() {

        List<TipoFrete> lista =  tipoFreteService.lista();
        return ResponseEntity.ok(lista);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id) {
        TipoFrete obj = tipoFreteService.buscarOuFalhar(id);
        return ResponseEntity.ok(obj);
    }



    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<TipoFrete> criarTipoFrete(@Valid @RequestBody TipoFreteNewDTO objNewDTO) {
        TipoFrete novoObj = modelMapper.map(objNewDTO, TipoFrete.class);
        TipoFrete objNovo = tipoFreteService.insert(objNewDTO);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
                path("/{id}").buildAndExpand(objNovo.getId()).toUri();

        return ResponseEntity.created(uri).body(novoObj);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<TipoFrete> update(@Valid @RequestBody TipoFreteDTO obj, @PathVariable Integer id) {
        obj.setId(id);
        TipoFrete novoobj = new TipoFrete(obj);
        TipoFrete obj1 = tipoFreteService.atualiza(novoobj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
                path("/{id}").buildAndExpand(obj1.getId()).toUri();
        return ResponseEntity.created(uri).body(obj1);

    }

    @RequestMapping(value ="/{id}/status",method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void inativar(@RequestBody Boolean obj,@PathVariable int id)	{
        tipoFreteService.status(obj,id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        tipoFreteService.delete(id);
        return ResponseEntity.noContent().build();
    }
    
}