package com.mei.vendasapi.validation.tipoPedido;

import com.mei.vendasapi.domain.TipoPedido;
import com.mei.vendasapi.domain.dto.TipoPedidoDTO;
import com.mei.vendasapi.repository.TipoPedidoRepository;
import com.mei.vendasapi.resource.exception.FieldMessage;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class TipoPedidoUpdateValidator implements ConstraintValidator<TipoPedidoUpdate, TipoPedidoDTO> {
    @Autowired
    private TipoPedidoRepository repo;
    @Override
    public void initialize(TipoPedidoUpdate ann){

    }

    @Override
    public boolean isValid(TipoPedidoDTO value, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();

        TipoPedido cat = repo.findByNome(value.getNome());
        if (cat != null) {
            list.add(new FieldMessage("nome", " Tipo de pedido já existente "));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMensagem()).addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }
        return list.isEmpty();
    }
}
