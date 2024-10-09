package com.mei.vendasapi.repository.imp;

import com.mei.vendasapi.domain.Categoria;
import com.mei.vendasapi.domain.Tenant;
import com.mei.vendasapi.repository.filter.CategoriaFilter;
import com.mei.vendasapi.repository.query.CategoriaRepositoryQuery;
import com.mei.vendasapi.service.util.Tenantuser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

@Primary
@Component
public class CategoriaRepositoryImpl implements CategoriaRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;
    @Autowired
    private Tenantuser tenantUsuario;


    @Override
    public Page<Categoria> filtrar(CategoriaFilter categoriaFilter, Pageable pageable) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Categoria> criteria = builder.createQuery(Categoria.class);
        Root<Categoria> root = criteria.from(Categoria.class);
        From<?, ?> logJoin = root.join("logs", JoinType.LEFT);
        criteria.distinct(true);
        List<Order> orderList = new ArrayList();
        orderList.add(builder.desc(root.get("id")));
        criteria.orderBy(orderList);


        Predicate[] predicates = criarRestricoes(categoriaFilter, builder, root, logJoin);
        criteria.where(predicates);

        TypedQuery<Categoria> query = manager.createQuery(criteria);
        List<Categoria> listacarga = new ArrayList<>();
        listacarga = query.getResultList();
        adicionarRestricoesDePaginacao(query, pageable);

        return new PageImpl<>(query.getResultList(), pageable, listacarga.size());
    }

    private Predicate[] criarRestricoes(CategoriaFilter categoriaFilter, CriteriaBuilder builder, Root<Categoria> root,
                                        From<?, ?> logJoin) {
        List<Predicate> predicates = new ArrayList<>();
        Tenant t = tenantUsuario.buscarOuFalhar();
        predicates.add(builder.equal(builder.lower(root.get("tenant")), t));
        if (categoriaFilter.getId() != null) {

            try {
                Integer valor = Integer.parseInt(String.valueOf(categoriaFilter.getId()));
                predicates.add(builder.equal(builder.lower(root.get("id")), valor));
            } catch (Exception e) {
                predicates.add(builder.equal(builder.lower(root.get("id")), 0));
            }

        }

        if (categoriaFilter.getNome() != null) {
        	predicates.add(
					builder.like(builder.lower(root.get("nome")), "%" + categoriaFilter.getNome() + "%"));
        }


        if (categoriaFilter.getDatagravacaode() != null) {
            Instant instant = categoriaFilter.getDatagravacaode().toInstant();
            ZoneOffset zoneOffset = ZoneId.systemDefault().getRules().getOffset(Instant.now());
            OffsetDateTime offsetDateTime = instant.atOffset(zoneOffset);
            predicates.add(builder.greaterThanOrEqualTo(logJoin.get("datagravacao"), offsetDateTime));
        }
        if (categoriaFilter.getDatagravacaoate() != null) {
            Instant instant = categoriaFilter.getDatagravacaoate().toInstant();
            ZoneOffset zoneOffset = ZoneId.systemDefault().getRules().getOffset(Instant.now());
            OffsetDateTime offsetDateTime = instant.atOffset(zoneOffset);
            predicates.add(builder.lessThanOrEqualTo(logJoin.get("datagravacao"), offsetDateTime));
        }

            if (categoriaFilter.getStatus().equals("Ativos")) {
                predicates.add(builder.equal(root.get("status"), true)); // Retorna categorias ativas (status = true)
            } else {
                predicates.add(builder.equal(root.get("status"), false)); // Retorna categorias inativas (status = false)
            }


		


        return predicates.toArray(new Predicate[predicates.size()]);
    }

    private void adicionarRestricoesDePaginacao(TypedQuery<Categoria> query, Pageable pageable) {

        int paginaAtual = pageable.getPageNumber();

        int totalRegistrosPorPagina = pageable.getPageSize();

        int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;
        query.setFirstResult(primeiroRegistroDaPagina);
        query.setMaxResults(totalRegistrosPorPagina);

    }

    private Long total(CategoriaFilter categoriaFilter) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<Categoria> root = criteria.from(Categoria.class);

        criteria.select(builder.count(root));
        return manager.createQuery(criteria).getSingleResult();
    }
}

