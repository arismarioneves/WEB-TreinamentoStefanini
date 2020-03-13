package com.stefanini.parsers;

import java.util.ArrayList;
import java.util.List;

import com.stefanini.dto.PerfilDto;
import com.stefanini.model.Perfil;

public class PerfilParserDTO extends AbstractParser<PerfilDto, Perfil> {

	@Override
	public PerfilDto toDTO(Perfil entity) {
		PerfilDto dto = new PerfilDto();
		dto.setId(entity.getId());
		dto.setNome(entity.getNome());
		dto.setDescricao(entity.getDescricao());
		dto.setDataHoraAlteracao(entity.getDataHoraAlteracao());
		dto.setDataHoraInclusao(entity.getDataHoraInclusao());
		return dto;
	}

	@Override
	public Perfil toEntity(PerfilDto dto) {
		Perfil entity = new Perfil();
		entity.setId(dto.getId());
		entity.setNome(dto.getNome());
		entity.setDescricao(dto.getDescricao());
		entity.setDataHoraAlteracao(dto.getDataHoraAlteracao());
		entity.setDataHoraInclusao(dto.getDataHoraInclusao());
		return entity;
	}

	public List<Perfil> toEntityList(List<PerfilDto> dtos) {
		List<Perfil> entities = new ArrayList<>();

		for (PerfilDto dto : dtos) {
			entities.add(toEntity(dto));
		}
		return entities;
	}

	public List<PerfilDto> toDtoList(List<Perfil> perfils) {
		List<PerfilDto> dtos = new ArrayList<>();

		for (Perfil perfil : perfils) {
			dtos.addAll(toDTO(perfils));
		}

		return dtos;
	}

}