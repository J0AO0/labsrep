package com.mei.vendasapi.resource;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import com.mei.vendasapi.domain.Empresa;
import com.mei.vendasapi.domain.dto.flat.EmpresaFlat;
import com.mei.vendasapi.repository.filter.EmpresaFilter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mei.vendasapi.assembler.FotoProdutoModelAssembler;
import com.mei.vendasapi.domain.FotoProduto;
import com.mei.vendasapi.domain.Produto;
import com.mei.vendasapi.domain.dto.FotoProdutoDTO;
import com.mei.vendasapi.domain.dto.ProdutoDTO;
import com.mei.vendasapi.domain.dto.ProdutoNewDTO;
import com.mei.vendasapi.domain.dto.flat.ProdutoFlat;
import com.mei.vendasapi.domain.model.input.FotoProdutoInput;
import com.mei.vendasapi.repository.ProdutoRepository;
import com.mei.vendasapi.repository.filter.ProdutoFilter;
import com.mei.vendasapi.security.resource.CheckSecurity;
import com.mei.vendasapi.service.CatalogoFotoProdutoService;
import com.mei.vendasapi.service.FotoStorageService;
import com.mei.vendasapi.service.ProdutoService;
import com.mei.vendasapi.service.exception.EntidadeNaoEncontradaExcepition;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResource {
	
	

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ProdutoRepository produtoRepo;
    
    
    @Autowired
	private CatalogoFotoProdutoService catalogoFotoProduto;
	
	@Autowired
	private FotoStorageService fotoStorage;
	
	@Autowired
	private FotoProdutoModelAssembler fotoProdutoModelAssembler;

//    @RequestMapping(method = RequestMethod.GET)
//    public ResponseEntity<?> lista() {
//
//        List<ProdutoFlat> lista =  produtoService.findAllSql();
//
//        return ResponseEntity.ok(lista);
//    }


    @CheckSecurity.Produto.PodeConsultar
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id) {
        Produto obj = produtoService.buscarOuFalhar(id);
        return ResponseEntity.ok(obj);
    }

    @CheckSecurity.Produto.PodeConsultar
    @RequestMapping( method = RequestMethod.GET)
    public Page<ProdutoFlat> findAllPag(ProdutoFilter produtoFilter, Pageable pageable) {
        Page<Produto> produtos = produtoRepo.filtrar(produtoFilter, pageable);
        Page<ProdutoFlat> produtosFlat = produtoService.mudarProdutoParaFlat(produtos);
        return produtosFlat;
    }

    @CheckSecurity.Produto.PodeConsultar
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ResponseEntity<Page<Produto>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                    @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
                                                    @RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
                                                    @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        Page<Produto> list = produtoService.findPage(page, linesPerPage, orderBy, direction);
        return ResponseEntity.ok().body(list);
    }

    @CheckSecurity.Produto.PodeCadastrar
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Produto> criarProduto(@Valid @RequestBody ProdutoNewDTO objNewDTO) {
        Produto novoObj = modelMapper.map(objNewDTO, Produto.class);
        Produto objNovo = produtoService.insert(objNewDTO);
        
        
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}").buildAndExpand(objNovo.getId()).toUri();

        return ResponseEntity.created(uri).body(novoObj);
    }

    @CheckSecurity.Produto.PodeAtualizar
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Produto> update(@Valid @RequestBody ProdutoDTO obj, @PathVariable Integer id) {
        obj.setId(id);

        Produto novoObj = new Produto(obj);
        Produto obj1 = produtoService.atualiza(novoObj);
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

    @CheckSecurity.Produto.PodeCadastrar
    @PutMapping(value = "/{produtoId}/foto" , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public FotoProdutoDTO atualizarFoto(@PathVariable Integer produtoId, @Valid FotoProdutoInput fotoProdutoInput) throws IOException {
		Produto produto = produtoService.buscarOuFalhar( produtoId);
		
		MultipartFile arquivo = fotoProdutoInput.getArquivo();
		
		FotoProduto foto = new FotoProduto();
		foto.setProduto(produto);
		foto.setDescricao(fotoProdutoInput.getDescricao());
		foto.setContentType(arquivo.getContentType());
		foto.setTamanho(arquivo.getSize());
		foto.setNomeArquivo(arquivo.getOriginalFilename());
		
		FotoProduto fotoSalva = catalogoFotoProduto.salvar(foto, arquivo.getInputStream());
		
		return fotoProdutoModelAssembler.toModel(fotoSalva);
	}

    @CheckSecurity.Produto.PodeExcluir
	@DeleteMapping(value = "/{produtoId}/foto")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable Integer produtoId) {
		catalogoFotoProduto.excluir(produtoId);
	}
	
	@GetMapping(value = "/teste"  ,produces = MediaType.APPLICATION_JSON_VALUE)
	public FotoProdutoDTO buscar( @PathVariable Integer produtoId) {
		FotoProduto fotoProduto = catalogoFotoProduto.buscarOuFalhar( produtoId);
		
		return fotoProdutoModelAssembler.toModel(fotoProduto);
	}

    @CheckSecurity.Produto.PodeConsultar
	@GetMapping(value = "/{produtoId}/fotos")
	public ResponseEntity<InputStreamResource> servir(@PathVariable Integer produtoId, @RequestHeader(name = "accept") String acceptHeader) 
					throws HttpMediaTypeNotAcceptableException {
		try {
			FotoProduto fotoProduto = catalogoFotoProduto.buscarOuFalhar(produtoId);
			
			MediaType mediaTypeFoto = MediaType.parseMediaType(fotoProduto.getContentType());
			List<MediaType> mediaTypesAceitas = MediaType.parseMediaTypes(acceptHeader);
			
			verificarCompatibilidadeMediaType(mediaTypeFoto, mediaTypesAceitas);
			
			InputStream inputStream = fotoStorage.recuperar(fotoProduto.getNomeArquivo());
			
			return ResponseEntity.ok()
					.contentType(mediaTypeFoto)
					.body(new InputStreamResource(inputStream));
		} catch (EntidadeNaoEncontradaExcepition e) {
			return ResponseEntity.notFound().build();
		}
	}

	private void verificarCompatibilidadeMediaType(MediaType mediaTypeFoto, 
			List<MediaType> mediaTypesAceitas) throws HttpMediaTypeNotAcceptableException {
		
		boolean compativel = mediaTypesAceitas.stream()
				.anyMatch(mediaTypeAceita -> mediaTypeAceita.isCompatibleWith(mediaTypeFoto));
		
		if (!compativel) {
			throw new HttpMediaTypeNotAcceptableException(mediaTypesAceitas);
		}
	}

}
