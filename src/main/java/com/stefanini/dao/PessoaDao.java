package com.stefanini.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.stefanini.dao.abstracao.GenericDao;
import com.stefanini.model.Pessoa;;

/**
 * O Unico objetivo da Dao
 */
public class PessoaDao extends GenericDao<Pessoa, Long> {

	public PessoaDao() {
		super(Pessoa.class);
	}

	public Optional<List<Pessoa>> getListParametros(String uf) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Pessoa> query = criteriaBuilder.createQuery(Pessoa.class);
		Root<Pessoa> root = query.from(Pessoa.class);

		// Path<String> nomePath = root.<String>get("nome");
		Path<String> ufPath = root.join("enderecos").<String>get("uf");

		List<Predicate> predicates = new ArrayList<>();

		// if(!nome.isEmpty()) {
		// Predicate nomeIgual = criteriaBuilder.like(nomePath, nome);
		// predicates.add(nomeIgual);
		// }
		if (!uf.isEmpty()) {
			Predicate ufIgual = criteriaBuilder.equal(ufPath, uf);
			predicates.add(ufIgual);
		}

		query.where((Predicate[]) predicates.toArray(new Predicate[0]));
		TypedQuery<Pessoa> typedQuery = entityManager.createQuery(query);
		return Optional.of(typedQuery.getResultList());
	}

}
