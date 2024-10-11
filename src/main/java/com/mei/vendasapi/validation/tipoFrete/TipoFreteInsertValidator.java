package com.mei.vendasapi.validation.tipoFrete;

import com.mei.vendasapi.domain.TipoFrete;
import com.mei.vendasapi.domain.dto.TipoFreteNewDTO;
import com.mei.vendasapi.domain.dto.TipoFreteNewDTO;
import com.mei.vendasapi.repository.TipoFreteRepository;
import com.mei.vendasapi.repository.TipoFreteRepository;
import com.mei.vendasapi.resource.exception.FieldMessage;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class TipoFreteInsertValidator implements ConstraintValidator<TipoFreteInsert, TipoFreteNewDTO> {

    @Autowired
    private TipoFreteRepository repo;
    @Override
    public void initialize(TipoFreteInsert ann){

    }

    @Override
    public boolean isValid(TipoFreteNewDTO value, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();

        TipoFrete cat = repo.findByDescricao(value.getDescricao());
        if (cat != null) {
            list.add(new FieldMessage("nome", " Tipo de Frete já existente "));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMensagem()).addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }
        return list.isEmpty();
    }

}
