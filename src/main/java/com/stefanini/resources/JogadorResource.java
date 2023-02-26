package com.stefanini.resources;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import com.stefanini.dto.ComprarStefamonDTO;
import com.stefanini.dto.LoginRequestDTO;
import com.stefanini.dto.LoginResponseDTO;
import com.stefanini.entity.Jogador;
import com.stefanini.service.JogadorService;

@Path("/jogadores")
public class JogadorResource {

    @Inject
    JogadorService jogadorService;

    @GET
    @Path("/{id}")
    public Response pegarPorId(@PathParam("id") Long id){
        return Response.status(Response.Status.OK).entity(jogadorService.pegarPorId(id)).build();
    }

    @GET
    @Path("/todos")
    public Response listarTodos(){
        return Response.status(Response.Status.OK).entity(jogadorService.listarTodos()).build();
    }

    @POST
    public Response salvar(@Valid Jogador jogador) {
        jogadorService.salvar(jogador);
        return Response.status(Response.Status.CREATED).build();
    }

    @POST
	@Path("/auth")
	public LoginResponseDTO autenticar(LoginRequestDTO loginDTO) {
		return jogadorService.autenticar(loginDTO);
	}
    
    @PUT
    @Path("/comprar")
    public Response comprarStefamon(ComprarStefamonDTO comprarStefamonDTO) {
    	jogadorService.comprarStefamon(comprarStefamonDTO);
    	return Response.status(Response.Status.OK).build();
    }
    
    @PUT
    public Response alterar(@Valid Jogador jogador) {
        jogadorService.alterar(jogador);
        return Response.status(Response.Status.OK).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletar(@PathParam("id") Long id) {
        jogadorService.deletar(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

}
