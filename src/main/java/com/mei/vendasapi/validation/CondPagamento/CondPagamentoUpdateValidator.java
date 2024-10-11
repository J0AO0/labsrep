package com.mei.vendasapi.validation.CondPagamento;

import com.mei.vendasapi.domain.CondPagamento;
import com.mei.vendasapi.domain.dto.CondPagamentoDTO;
import com.mei.vendasapi.repository.CondPagamentoRepository;
import com.mei.vendasapi.resource.exception.FieldMessage;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class CondPagamentoUpdateValidator implements ConstraintValidator<CondPagamentoUpdate, CondPagamentoDTO> {
    @Autowired
    private CondPagamentoRepository repo;
    @Override
    public void initialize(CondPagamentoUpdate ann){

    }

    @Override
    public boolean isValid(CondPagamentoDTO value, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();

        CondPagamento cat = repo.findByDescricao(value.getDescricao());
        if (cat != null) {
            list.add(new FieldMessage("nome", " Condição de Pagamento já existente "));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMensagem()).addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }
        return list.isEmpty();
    }
}
