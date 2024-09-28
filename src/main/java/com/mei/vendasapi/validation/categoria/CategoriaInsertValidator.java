package com.mei.vendasapi.validation.categoria;

import com.mei.vendasapi.domain.Categoria;
import com.mei.vendasapi.domain.EmpresaNew;
import com.mei.vendasapi.domain.dto.CategoriaNewDTO;
import com.mei.vendasapi.repository.CategoriaRepository;
import com.mei.vendasapi.repository.EmpresaRepository;
import com.mei.vendasapi.resource.exception.FieldMessage;
import com.mei.vendasapi.validation.empresa.EmpresaInsert;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class CategoriaInsertValidator implements ConstraintValidator<CategoriaInsert, CategoriaNewDTO> {
    @Autowired
    private CategoriaRepository repo;
    @Override
    public void initialize(CategoriaInsert ann){

    }

    @Override
    public boolean isValid(CategoriaNewDTO value, ConstraintValidatorContext context) {
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
