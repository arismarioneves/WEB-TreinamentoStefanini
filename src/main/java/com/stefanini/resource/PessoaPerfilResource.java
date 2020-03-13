package com.stefanini.resource;

import javax.inject.Inject;
import javax.websocket.server.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.stefanini.model.PessoaPerfil;
import com.stefanini.servico.PessoaPerfilServico;

@Path(value = "pessoa-perfil")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PessoaPerfilResource {

	@Inject
	private PessoaPerfilServico pessoaPerfilServico;

	@GET
	public Response listarPessoaPerfil() {
		return Response.ok(pessoaPerfilServico.listar().get()).build();
	}

	@POST
	public Response adicionarPessoaPerfil(PessoaPerfil entity) {
		return Response.ok(pessoaPerfilServico.salvar(entity)).build();
	}

	@GET
	@Path("{id}")
	public Response acharPessoaServicoPeloId(@PathParam("id") long id) {
		return Response.ok(pessoaPerfilServico).build();
	}

	@DELETE
	@Path("{id}")
	public Response deletarPessoaPerfil(@PathParam("id") long id) {
		pessoaPerfilServico.remover(id);
		return Response.ok().build();
	}

	@PUT
	public Response atualizarPessoaPerfil(PessoaPerfil perfilPessoa) {
		return Response.ok(pessoaPerfilServico.atualizar(perfilPessoa)).build();
	}

}