package com.mei.vendasapi.resource;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.google.common.io.Files;
import com.mei.vendasapi.domain.Produto;
import com.mei.vendasapi.domain.dto.ProdutoDTO;
import com.mei.vendasapi.domain.dto.ProdutoNewDTO;
import com.mei.vendasapi.domain.dto.flat.ProdutoFlat;
import com.mei.vendasapi.repository.ProdutoRepository;
import com.mei.vendasapi.repository.filter.ProdutoFilter;
import com.mei.vendasapi.security.resource.CheckSecurity;
import com.mei.vendasapi.service.ProdutoService;

import java.nio.file.Path;
import java.nio.file.Paths;

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
    public ResponseEntity<Produto> criarProduto(@Valid @RequestBody ProdutoNewDTO objNewDTO, @RequestParam("file") MultipartFile arquivo) {
        Produto novoObj = modelMapper.map(objNewDTO, Produto.class);
        Produto objNovo = produtoService.insert(objNewDTO, arquivo);
        
        
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}").buildAndExpand(objNovo.getId()).toUri();

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

//    @PostMapping("/upload-foto")
//    public ResponseEntity<String> uploadFoto(@RequestParam("file") MultipartFile file,
//                                             @RequestParam("produtoId") Integer produtoId) {
//        try {
//            // Verifique se o arquivo é PNG
//            if (!file.getContentType().equals("image/png")) {
//                return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
//                        .body("Apenas arquivos PNG são suportados");
//            }
//
//            // Gera um nome aleatório para a foto
//            String uniqueFileName = UUID.randomUUID().toString() + ".jpeg";
//
//            // Converta a imagem PNG para WebP (ou JPEG) usando o webp-imageio
//            BufferedImage bufferedImage = ImageIO.read(file.getInputStream());
//            File outputFile = new File("C:\\Users\\joaoc\\OneDrive\\Área de Trabalho\\projeto\\labsrep-ui\\src\\assets\\fotos_produto\\" + uniqueFileName);
//
//            // Obtenha um escritor para o formato WebP
//            ImageWriter writer = ImageIO.getImageWritersByFormatName("jpeg").next();
//            ImageOutputStream ios = ImageIO.createImageOutputStream(outputFile);
//            writer.setOutput(ios);
//
//            // Configurações opcionais de parâmetros de escrita (exemplo: qualidade)
//            ImageWriteParam param = writer.getDefaultWriteParam();
//            // Descomente a linha abaixo se quiser configurar a qualidade da compressão
//            // param.setCompressionQuality(0.85f); // Define a qualidade para 85%
//
//            // Escreve a imagem
//            writer.write(null, new IIOImage(bufferedImage, null, null), param);
//            ios.close();
//            writer.dispose();
//
//            // Busca o produto pelo ID
//            Optional<Produto> produtoOptional = produtoRepo.findById(produtoId);
//            if (!produtoOptional.isPresent()) {
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado");
//            }
//
//            // Atualiza o produto com o nome do arquivo da imagem
//            Produto produto = produtoOptional.get();
//            produto.setQrCode(uniqueFileName); // Salva o nome da imagem no banco de dados
//
//            // Salva as alterações no banco de dados
//            produtoRepo.save(produto);
//
//            return ResponseEntity.ok("Foto salva com sucesso e associada ao produto!");
//        } catch (IOException e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao salvar a imagem");
//        }
//    }
@PostMapping("/upload-foto")
public ResponseEntity<String> uploadFoto(@RequestParam("file") MultipartFile file) {
    try {
        // Verifique se o arquivo é PNG
        if (!file.getContentType().equals("image/png")) {
            return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
                    .body("Apenas arquivos PNG são suportados");
        }

        String uniqueFileName = UUID.randomUUID().toString() + ".jpeg";
        // Converta a imagem PNG para WebP usando o webp-imageio
        BufferedImage bufferedImage = ImageIO.read(file.getInputStream());
        File outputFile = new File("C:\\Users\\joaoc\\OneDrive\\Área de Trabalho\\projeto\\labsrep-ui\\src\\assets\\fotos_produto\\" + uniqueFileName);

        // Obtenha um escritor para o formato WebP
        ImageWriter writer = ImageIO.getImageWritersByFormatName("jpeg").next();
        ImageOutputStream ios = ImageIO.createImageOutputStream(outputFile);
        writer.setOutput(ios);

        // Configurações opcionais de parâmetros de escrita (por exemplo, qualidade)
        ImageWriteParam param = writer.getDefaultWriteParam();
        // Se quiser configurar a qualidade da compressão, descomente a linha abaixo
        // param.setCompressionQuality(0.85f); // Define a qualidade para 85%

        // Escreve a imagem
        writer.write(null, new IIOImage(bufferedImage, null, null), param);
        ios.close();
        writer.dispose();

        return ResponseEntity.ok("Foto salva com sucesso!");
    } catch (IOException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao salvar a imagem");
    }
}

}
