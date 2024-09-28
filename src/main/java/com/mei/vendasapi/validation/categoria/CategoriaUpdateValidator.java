package com.mei.vendasapi.validation.categoria;

import com.mei.vendasapi.domain.Categoria;
import com.mei.vendasapi.domain.dto.CategoriaDTO;
import com.mei.vendasapi.repository.CategoriaRepository;
import com.mei.vendasapi.resource.exception.FieldMessage;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class CategoriaUpdateValidator implements ConstraintValidator<CategoriaUpdate, CategoriaDTO> {
    @Autowired
    private CategoriaRepository repo;
    @Override
    public void initialize(CategoriaUpdate ann){

    }

    @Override
    public boolean isValid(CategoriaDTO value, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();

        Categoria cat = repo.findByNome(value.getNome());
        if (cat != null) {
            list.add(new FieldMessage("nome", " Categoria já existente "));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMensagem()).addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }
        return list.isEmpty();
    }
}
