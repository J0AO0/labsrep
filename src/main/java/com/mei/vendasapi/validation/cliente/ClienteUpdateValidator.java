package com.mei.vendasapi.validation.cliente;

import com.mei.vendasapi.domain.Cliente;
import com.mei.vendasapi.domain.dto.ClienteDTO;
import com.mei.vendasapi.repository.ClienteRepository;
import com.mei.vendasapi.resource.exception.FieldMessage;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {
    @Autowired
    private ClienteRepository repo;
    @Override
    public void initialize(ClienteUpdate ann){

    }

    @Override
    public boolean isValid(ClienteDTO value, ConstraintValidatorContext context) {
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
