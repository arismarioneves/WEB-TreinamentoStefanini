package com.stefanini.resource;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.stefanini.dto.PessoaDto;
import com.stefanini.model.Pessoa;
import com.stefanini.servico.PessoaServico;

@Path("pessoas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PessoaResource {

	@Inject
	private PessoaServico pessoaServico;

	@GET
	public Response obterListaPessoa() {
		return Response.ok(pessoaServico.getList().get()).build();
	}

	@POST
	public Response salvarPessoa(@Valid PessoaDto pessoa) {
		return Response.ok(pessoaServico.salvar(pessoa)).build();
	}

	@PUT
	public Response alterarPessoa(@Valid PessoaDto pessoa) {
		return Response.ok(pessoaServico.atualizar(pessoa)).build();
	}

	@DELETE
	@Path("{id}")
	public Response excluirPessoa(@PathParam("id") Long id) {
		pessoaServico.remover(id);
		return Response.ok().build();
	}

	@GET
	@Path("{id}")
	public Response obterPessoa(@PathParam("id") Long id) {
		return Response.ok(pessoaServico.encontrar(id).get()).build();
	}

	@GET
	@Path("parametros")
	public Response getListAll(@Valid PessoaDto pessoa) {
		return Response.ok(pessoaServico.getListParametros(pessoa).get()).build();
	}

}
