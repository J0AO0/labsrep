package com.mei.vendasapi.resource;

import com.mei.vendasapi.domain.Item;
import com.mei.vendasapi.domain.dto.ItemDTO;
import com.mei.vendasapi.domain.dto.ItemNewDTO;
import com.mei.vendasapi.service.ItemService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/itens")
public class ItemResource {

    @Autowired
    private ItemService itemService;

    @Autowired
    private ModelMapper modelMapper;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> lista() {

        List<Item> lista =  itemService.lista();

        return ResponseEntity.ok(lista);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id) {
        Item obj = itemService.findPorId(id);
        return ResponseEntity.ok(obj);
    }


    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Item> criarItem(@RequestBody ItemNewDTO objNewDTO) {
        Item novoObj = modelMapper.map(objNewDTO, Item.class);
        Item objNovo = itemService.insert(objNewDTO);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
                path("/{id}").buildAndExpand(objNovo.getId()).toUri();

        return ResponseEntity.created(uri).body(novoObj);
    }


//    @RequestMapping(value = "/{id} " , method = RequestMethod.PUT)

//    public ResponseEntity<Item> atualizarItem(@PathVariable Integer id,@RequestBody ItemDTO objDTO) {
//        objDTO.setId(null);
//        Item objNovo = new Item(objDTO);
//        Item atividadeAtualizado =  itemService.atualiza(objNovo);
//
//        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
//                path("/{id}").buildAndExpand(atividadeAtualizado.getId()).toUri();
//
//        return ResponseEntity.created(uri).body(atividadeAtualizado);
//    }

    @RequestMapping(value="/{id}/status",method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void inativar(@RequestBody Boolean obj,@PathVariable int id)	{
        itemService.status(obj,id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        itemService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
