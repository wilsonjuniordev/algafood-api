package dev.wilsonjunior.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;

import dev.wilsonjunior.algafood.domain.repository.RestauranteRepository;

public class CadastroRestauranteService {

	public static final String MSG_COZINHA_NAO_EXISTE = "Não existe um cadastro de cozinha com código %d";
	public static final String MSG_COZINHA_ESTA_EM_USO = "Cozinha de código %d não pode ser removida, pois está em uso";

	@Autowired
	RestauranteRepository restauranteRepository;
	
	
}
