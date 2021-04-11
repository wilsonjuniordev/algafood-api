package dev.wilsonjunior.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.wilsonjunior.algafood.domain.exception.EntidadeNaoEncontradaException;
import dev.wilsonjunior.algafood.domain.model.Cozinha;
import dev.wilsonjunior.algafood.domain.model.Restaurante;
import dev.wilsonjunior.algafood.domain.repository.CozinhaRepository;
import dev.wilsonjunior.algafood.domain.repository.RestauranteRepository;

@Service
public class CadastroRestauranteService {

	public static final String MSG_COZINHA_NAO_EXISTE = "Não existe um cadastro de cozinha com código %d";
	public static final String MSG_COZINHA_ESTA_EM_USO = "Cozinha de código %d não pode ser removida, pois está em uso";

	@Autowired
	RestauranteRepository restauranteRepository;
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	public Restaurante salvar(Restaurante restaurante) {
		Long cozinhaId = restaurante.getCozinha().getId();
		Cozinha cozinha = cozinhaRepository.buscar(cozinhaId);
		
		if (cozinha == null) {
			throw new EntidadeNaoEncontradaException(String.format(MSG_COZINHA_NAO_EXISTE, cozinhaId));
		}
		restaurante.setCozinha(cozinha);
		
		return restauranteRepository.salvar(restaurante);
	}
	
}
