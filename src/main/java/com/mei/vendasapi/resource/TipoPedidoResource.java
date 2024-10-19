package com.mei.vendasapi.resource;

import com.mei.vendasapi.domain.TipoPedido;
import com.mei.vendasapi.domain.dto.TipoPedidoDTO;
import com.mei.vendasapi.domain.dto.TipoPedidoNewDTO;
import com.mei.vendasapi.domain.dto.flat.CondPagamentoFlat;
import com.mei.vendasapi.domain.dto.flat.TipoPedidoFlat;
import com.mei.vendasapi.security.resource.CheckSecurity;
import com.mei.vendasapi.service.TipoPedidoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/tipoPedidos")
public class TipoPedidoResource {
    @Autowired
    private TipoPedidoService tipoPedidoService;

    @Autowired
    private ModelMapper modelMapper;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> lista() {
    	List<TipoPedidoFlat> list = tipoPedidoService.findAllSql();

        return ResponseEntity.ok(list);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id) {
        TipoPedido obj = tipoPedidoService.buscarOuFalhar(id);
        return ResponseEntity.ok(obj);
    }


    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<TipoPedido> criarTipoPedido(@Valid @RequestBody TipoPedidoNewDTO objNewDTO) {
        TipoPedido novoObj = modelMapper.map(objNewDTO, TipoPedido.class);
        TipoPedido objNovo = tipoPedidoService.insert(objNewDTO);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
                path("/{id}").buildAndExpand(objNovo.getId()).toUri();

        return ResponseEntity.created(uri).body(novoObj);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<TipoPedido> update(@Valid @RequestBody TipoPedidoDTO obj, @PathVariable Integer id) {
        obj.setId(id);
        TipoPedido obj1 = tipoPedidoService.atualiza(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
                path("/{id}").buildAndExpand(obj1.getId()).toUri();
        return ResponseEntity.created(uri).body(obj1);

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        tipoPedidoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
