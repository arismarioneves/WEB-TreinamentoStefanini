package com.stefanini.resource;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.websocket.server.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.stefanini.dto.EnderecoDto;
import com.stefanini.model.Endereco;
import com.stefanini.servico.EnderecoServico;

@Path(value = "enderecos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EnderecoResource {

	@Inject
	private EnderecoServico enderecoServico;

	@GET
	public Response obterListaEndereco() {
		return Response.ok(enderecoServico.getList().get()).build();
	}

	@POST
	public Response salvarEndereco(Endereco entity) {
		return Response.ok(enderecoServico.salvar(entity)).build();
	}

	@DELETE
	@Path("{id}")
	public Response deletarEndereco(@PathParam("id") long id) {
		enderecoServico.remover(id);
		return Response.ok().build();
	}

	@GET
	@Path("{id}")
	public Response procurarPorId(@PathParam("id") long id) {
		return Response.ok(enderecoServico.encontrar(id).get()).build();
	}

	@GET
	@Path("parametros")
	public Response getListAll(@Valid EnderecoDto endereco) {
		return Response.ok(enderecoServico.getListParametros(endereco).get()).build();
	}

}
