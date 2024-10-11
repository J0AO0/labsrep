package com.mei.vendasapi.validation.formaPagamento;

import com.mei.vendasapi.domain.FormaPagamento;
import com.mei.vendasapi.domain.dto.FormaPagamentoNewDTO;
import com.mei.vendasapi.repository.FormaPagamentoRepository;
import com.mei.vendasapi.resource.exception.FieldMessage;

import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class FormaPagamentoInsertValidator implements ConstraintValidator<FormaPagamentoInsert, FormaPagamentoNewDTO> {
    @Autowired
    private FormaPagamentoRepository repo;
    @Override
    public void initialize(FormaPagamentoInsert ann){

    }

    @Override
    public boolean isValid(FormaPagamentoNewDTO value, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();

        FormaPagamento cat = repo.findByDescricao(value.getDescricao());
        if (cat != null) {
            list.add(new FieldMessage("nome", " Forma de pagamento já existente "));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMensagem()).addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }
        return list.isEmpty();
    }

}
