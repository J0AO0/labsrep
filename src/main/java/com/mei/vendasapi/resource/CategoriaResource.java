package com.mei.vendasapi.resource;

import java.net.URI;
import java.util.List;

import com.mei.vendasapi.domain.dto.flat.CategoriaFlat;
import com.mei.vendasapi.repository.CategoriaRepository;
import com.mei.vendasapi.repository.filter.CategoriaFilter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mei.vendasapi.domain.Categoria;
import com.mei.vendasapi.domain.dto.CategoriaDTO;
import com.mei.vendasapi.domain.dto.CategoriaNewDTO;
import com.mei.vendasapi.service.CategoriaService;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaService  categoriaService;
	
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private CategoriaRepository catRepo;
	
//	@RequestMapping(method = RequestMethod.GET)
//	public ResponseEntity<?> lista() {
//	
//	List<Categoria> lista =  categoriaService.lista();
//		
//	return ResponseEntity.ok(lista);	
//	}
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> buscarPorId(@PathVariable Integer id) {
		Categoria obj = categoriaService.buscarOuFalhar(id);
		return ResponseEntity.ok(obj);
	}


	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ResponseEntity<Page<Categoria>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
												   @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
												   @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
												   @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		Page<Categoria> list = categoriaService.findPage(page, linesPerPage, orderBy, direction);
		return ResponseEntity.ok().body(list);
	}


	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Categoria> criarCategoria(@Valid @RequestBody CategoriaNewDTO objNewDTO) {
	Categoria novoObj = modelMapper.map(objNewDTO, Categoria.class);
	Categoria objNovo = categoriaService.insert(objNewDTO);
	
	URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
			path("/{id}").buildAndExpand(objNovo.getId()).toUri();
	
	return ResponseEntity.created(uri).body(novoObj);
	}


	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Categoria> update(@Valid @RequestBody CategoriaDTO obj, @PathVariable Integer id) {
		obj.setId(id);
		Categoria obj1 = categoriaService.atualiza(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
				path("/{id}").buildAndExpand(obj1.getId()).toUri();
		return ResponseEntity.created(uri).body(obj1);

	}
	
	@RequestMapping(value ="/{id}/status",method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.NO_CONTENT)
    public void inativar(@RequestBody Boolean obj,@PathVariable int id)	{
		categoriaService.status(obj,id);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		categoriaService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping( method = RequestMethod.GET)
	public Page<CategoriaFlat> findAllPag(CategoriaFilter pacienteFilter, Pageable pageable) {

		Page<Categoria> cats = catRepo.filtrar(pacienteFilter, pageable);
		Page<CategoriaFlat> catsflat = categoriaService.mudarCategoriaParaFlat(cats);
		return catsflat;

	}
}
