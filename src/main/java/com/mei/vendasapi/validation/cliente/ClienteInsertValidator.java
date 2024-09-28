package com.mei.vendasapi.validation.cliente;

import com.mei.vendasapi.domain.Categoria;
import com.mei.vendasapi.domain.Cliente;
import com.mei.vendasapi.domain.dto.CategoriaNewDTO;
import com.mei.vendasapi.domain.dto.ClienteNewDTO;
import com.mei.vendasapi.repository.CategoriaRepository;
import com.mei.vendasapi.repository.ClienteRepository;
import com.mei.vendasapi.resource.exception.FieldMessage;
import com.mei.vendasapi.validation.categoria.CategoriaInsert;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {
    @Autowired
    private ClienteRepository repo;
    @Override
    public void initialize(ClienteInsert ann){

    }

    @Override
    public boolean isValid(ClienteNewDTO value, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();

        Cliente cat = repo.findByCpfoucnpj(value.getCpfoucnpj());
        if (cat != null) {
            list.add(new FieldMessage("cpfoucnpj", " Cliente já existente "));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMensagem()).addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }
        return list.isEmpty();
    }
}
