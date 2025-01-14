package com.mei.vendasapi.resource;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mei.vendasapi.domain.Empresa;
import com.mei.vendasapi.domain.dto.EmpresaDTO;
import com.mei.vendasapi.domain.dto.EmpresaNewDTO;
import com.mei.vendasapi.domain.dto.flat.EmpresaFlat;
import com.mei.vendasapi.service.EmpresaService;

@RestController
@RequestMapping("/empresas")
public class EmpresaResource {

    @Autowired
    private EmpresaService empresaService;

    @Autowired
    private ModelMapper modelMapper;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> lista() {

        List<Empresa> lista =  empresaService.lista();

        return ResponseEntity.ok(lista);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id) {
        Empresa obj = empresaService.findPorId(id);
        return ResponseEntity.ok(obj);
    }


    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Empresa> criarEmpresa(@RequestBody EmpresaNewDTO objNewDTO) {
        Empresa novoObj = modelMapper.map(objNewDTO, Empresa.class);
        Empresa objNovo = empresaService.insert(objNewDTO);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
                path("/{id}").buildAndExpand(objNovo.getId()).toUri();

        return ResponseEntity.created(uri).body(novoObj);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Empresa> update(@Valid @RequestBody EmpresaDTO obj, @PathVariable Integer id) {
        obj.setId(id);
        Empresa obj1 = empresaService.atualiza(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
                path("/{id}").buildAndExpand(obj1.getId()).toUri();
        return ResponseEntity.created(uri).body(obj1);

    }

    @RequestMapping(value="/{id}/status",method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void inativar(@RequestBody Boolean obj,@PathVariable int id)	{
        empresaService.status(obj,id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        empresaService.delete(id);
        return ResponseEntity.noContent().build();
    }
    
	@RequestMapping(value = "/usuarios", method = RequestMethod.GET)
	public ResponseEntity<List<EmpresaFlat>> findAllUsuario() {
		
		List<EmpresaFlat> list = empresaService.findAllUsuario();
		return ResponseEntity.ok().body(list);
	}
    

}
