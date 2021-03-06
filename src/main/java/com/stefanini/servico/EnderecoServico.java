package com.stefanini.servico;

import com.stefanini.dao.EnderecoDao;
import com.stefanini.dto.EnderecoDto;
import com.stefanini.model.Endereco;
import com.stefanini.parsers.EnderecoParserDTO;
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
public class EnderecoServico implements IGenericService<Endereco, Long> {

	@Inject
	private EnderecoDao dao;

	@Inject
	private EnderecoParserDTO parser;

	// @Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Endereco salvar(@Valid Endereco entity) {
		return dao.salvar(entity);
	}

	// @Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Endereco atualizar(@Valid Endereco entity) {
		return dao.atualizar(entity);
	}

	// @Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void remover(Long id) {
		dao.remover(id);
	}

	@Override
	public Optional<List<Endereco>> getList() {
		// return Optional.empty();
		return dao.getList();
	}

	@Override
	public Optional<Endereco> encontrar(Long id) {
		return dao.encontrar(id);
	}

	public Optional<List<Endereco>> getListParametros(@Valid EnderecoDto endereco) {
		List<EnderecoDto> enderecos = parser.toDtoList(dao.getListParametros(endereco).get());
		return Optional.of(parser.toEntityList(enderecos));
	}
}
