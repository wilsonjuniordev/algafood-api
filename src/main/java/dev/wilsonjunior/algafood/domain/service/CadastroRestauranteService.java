package dev.wilsonjunior.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import dev.wilsonjunior.algafood.domain.exception.EntidadeEmUsoException;
import dev.wilsonjunior.algafood.domain.exception.EntidadeNaoEncontradaException;
import dev.wilsonjunior.algafood.domain.model.Cozinha;
import dev.wilsonjunior.algafood.domain.model.Restaurante;
import dev.wilsonjunior.algafood.domain.repository.RestauranteRepository;

@Service
public class CadastroRestauranteService {

	public static final String MSG_COZINHA_NAO_EXISTE = "Não existe cadastro de cozinha com código %d";
	public static final String MSG_RESTAURANTE_NAO_EXISTE = "Não existe cadastro de restaurante com código %d";
	public static final String MSG_RESTAURANTE_ESTA_EM_USO = "Restaurante de código %d não pode ser removida, pois está em uso";

	@Autowired
	RestauranteRepository restauranteRepository;

	@Autowired
	private CadastroCozinhaService cadastroCozinhaService;

	public Restaurante salvar(Restaurante restaurante) {
		Long cozinhaId = restaurante.getCozinha().getId();

		Cozinha cozinha = cadastroCozinhaService.buscarOuFalhar(cozinhaId);

		restaurante.setCozinha(cozinha);

		return restauranteRepository.save(restaurante);
	}

	public void excluir(Long restauranteId) {
		try {
			restauranteRepository.deleteById(restauranteId);
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(//
					String.format(MSG_RESTAURANTE_NAO_EXISTE, restauranteId));
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(//
					String.format(MSG_RESTAURANTE_ESTA_EM_USO, restauranteId));
		}
	}

	public Restaurante buscarOuFalhar(Long restauranteId) {
		return restauranteRepository.findById(restauranteId).//
				orElseThrow(() -> new EntidadeNaoEncontradaException(//
						String.format(MSG_RESTAURANTE_NAO_EXISTE, restauranteId)));
	}

}
