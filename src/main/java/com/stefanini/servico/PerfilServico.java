package com.stefanini.servico;

import com.stefanini.dao.PerfilDao;
import com.stefanini.dto.PerfilDto;
import com.stefanini.model.Perfil;
import com.stefanini.parsers.PerfilParserDTO;
import com.stefanini.util.IGenericService;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * Classe de servico, as regras de negocio devem estar nessa classe
 */
public class PerfilServico implements IGenericService<Perfil, Long> {

	@Inject
	private PerfilDao dao;

	@Inject
	private PerfilParserDTO parser;

	// @Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Perfil salvar(@Valid Perfil entity) {
		return dao.salvar(entity);
	}

	// @Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Perfil atualizar(@Valid Perfil entity) {
		return dao.atualizar(entity);
	}

	// @Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void remover(Long id) {
		dao.remover(id);
	}

	@Override
	public Optional<List<Perfil>> getList() {
		// return Optional.empty();
		return dao.getList();
	}

	@Override
	public Optional<Perfil> encontrar(Long id) {
		return dao.encontrar(id);
	}

	public Optional<List<Perfil>> getListParametros(@Valid PerfilDto perfil) {
		List<PerfilDto> perfils = parser.toDtoList(dao.getListParametros(perfil).get());
		return Optional.of(parser.toEntityList(perfils));
	}
}
