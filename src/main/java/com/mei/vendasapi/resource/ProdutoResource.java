package com.mei.vendasapi.resource;

import com.mei.vendasapi.domain.Produto;
import com.mei.vendasapi.domain.Produto;
import com.mei.vendasapi.domain.dto.ProdutoDTO;
import com.mei.vendasapi.domain.dto.ProdutoDTO;
import com.mei.vendasapi.domain.dto.ProdutoNewDTO;
import com.mei.vendasapi.domain.dto.flat.ProdutoFlat;
import com.mei.vendasapi.repository.ProdutoRepository;
import com.mei.vendasapi.repository.filter.ProdutoFilter;
import com.mei.vendasapi.security.resource.CheckSecurity;
import com.mei.vendasapi.service.ProdutoService;
import org.hibernate.annotations.Check;
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
@RequestMapping(value = "/produtos")
public class ProdutoResource {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ProdutoRepository produtoRepo;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> lista() {

        List<Produto> lista =  produtoService.lista();

        return ResponseEntity.ok(lista);
    }


    @CheckSecurity.Produto.PodeConsultar
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id) {
        Produto obj = produtoService.buscarOuFalhar(id);
        return ResponseEntity.ok(obj);
    }


    @CheckSecurity.Produto.PodeCadastrar
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Produto> criarProduto(@Valid @RequestBody ProdutoNewDTO objNewDTO) {
        Produto novoObj = modelMapper.map(objNewDTO, Produto.class);
        Produto objNovo = produtoService.insert(objNewDTO);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
                path("/{id}").buildAndExpand(objNovo.getId()).toUri();

        return ResponseEntity.created(uri).body(novoObj);
    }

    @CheckSecurity.Produto.PodeAtualizar
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Produto> update(@Valid @RequestBody ProdutoDTO obj, @PathVariable Integer id) {
        obj.setId(id);
        Produto obj1 = produtoService.atualiza(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
                path("/{id}").buildAndExpand(obj1.getId()).toUri();
        return ResponseEntity.created(uri).body(obj1);

    }

    @CheckSecurity.Produto.PodeAlterarStatus
    @RequestMapping(value="/{id}/status",method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void inativar(@RequestBody Boolean obj,@PathVariable int id)	{
        produtoService.status(obj,id);
    }

    @CheckSecurity.Produto.PodeExcluir
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        produtoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @CheckSecurity.Produto.PodeConsultar
    @RequestMapping(value = "/filtro", method = RequestMethod.GET)
    public Page<ProdutoFlat> findAllPag(ProdutoFilter produtoFilter, Pageable pageable) {
        Page<Produto> prods = produtoRepo.filtrar(produtoFilter, pageable);
        Page<ProdutoFlat> prodsflat = produtoService.mudarProdutoParaFlat(prods);
        return prodsflat;

    }

}
