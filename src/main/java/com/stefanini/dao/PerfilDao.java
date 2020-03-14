package com.stefanini.dao;

import java.time.LocalDate;
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
import com.stefanini.dto.PerfilDto;
import com.stefanini.model.Perfil;

/**
 * O Unico objetivo da Dao
 */
public class PerfilDao extends GenericDao<Perfil, Long> {

	public PerfilDao() {
		super(Perfil.class);
	}

	public Optional<List<Perfil>> getListParametros(PerfilDto dto) {

		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Perfil> query = criteriaBuilder.createQuery(Perfil.class);
		Root<Perfil> root = query.from(Perfil.class);

		Path<Long> idPath = root.<Long>get("id");
		Path<String> nomePath = root.<String>get("nome");
		Path<String> descricaoPath = root.<String>get("descricao");
		Path<LocalDate> dataHoraInclusaoPath = root.<LocalDate>get("dataHoraInclusao");
		Path<LocalDate> dataHoraAlteracaoPath = root.<LocalDate>get("dataHoraAlteracao");

		List<Predicate> predicates = new ArrayList<>();

		if (dto.getId() != null) {
			Predicate idIgual = criteriaBuilder.equal(idPath, dto.getId());
			predicates.add(idIgual);
		}
		if (dto.getNome() != null) {
			Predicate nomeIgual = criteriaBuilder.equal(nomePath, dto.getNome());
			predicates.add(nomeIgual);
		}
		if (dto.getDescricao() != null) {
			Predicate descricaoIgual = criteriaBuilder.equal(descricaoPath, dto.getDescricao());
			predicates.add(descricaoIgual);
		}
		if (dto.getDataHoraInclusao() != null) {
			Predicate dataInclusaoIgual = criteriaBuilder.equal(dataHoraInclusaoPath, dto.getDataHoraInclusao());
			predicates.add(dataInclusaoIgual);
		}
		if (dto.getDataHoraAlteracao() != null) {
			Predicate dataHoraAlteracao = criteriaBuilder.equal(dataHoraAlteracaoPath, dto.getDataHoraInclusao());
			predicates.add(dataHoraAlteracao);
		}

		// query.where(ufIgual);
		query.where((Predicate[]) predicates.toArray(new Predicate[0]));
		TypedQuery<Perfil> typedQuery = entityManager.createQuery(query);
		return Optional.of(typedQuery.getResultList());
	}

}
