package com.mei.vendasapi.validation.produto;

import com.mei.vendasapi.domain.Produto;
import com.mei.vendasapi.domain.dto.ProdutoNewDTO;
import com.mei.vendasapi.repository.ProdutoRepository;
import com.mei.vendasapi.resource.exception.FieldMessage;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class ProdutoInsertValidator implements ConstraintValidator<ProdutoInsert, ProdutoNewDTO> {

    @Autowired
    private ProdutoRepository repo;
    @Override
    public void initialize(ProdutoInsert ann){

    }

    @Override
    public boolean isValid(ProdutoNewDTO value, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();

        Produto cat = repo.findByName(value.getName());
        if (cat != null) {
            list.add(new FieldMessage("name", " Produto já existente "));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMensagem()).addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }
        return list.isEmpty();
    }

}
