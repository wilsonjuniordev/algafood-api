package dev.wilsonjunior.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import dev.wilsonjunior.algafood.domain.exception.CozinhaNaoEncontradaException;
import dev.wilsonjunior.algafood.domain.exception.EntidadeEmUsoException;
import dev.wilsonjunior.algafood.domain.model.Cozinha;
import dev.wilsonjunior.algafood.domain.repository.CozinhaRepository;

@Service
public class CadastroCozinhaService {

	public static final String MSG_COZINHA_ESTA_EM_USO = "Cozinha de código %d não pode ser removida, pois está em uso";

	@Autowired
	private CozinhaRepository cozinhaRepository;

	public Cozinha salvar(Cozinha cozinha) {
		return cozinhaRepository.save(cozinha);
	}

	public void excluir(Long cozinhaId) {
		try {
			cozinhaRepository.deleteById(cozinhaId);

		} catch (EmptyResultDataAccessException e) {
			throw new CozinhaNaoEncontradaException(cozinhaId);
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(//
					MSG_COZINHA_ESTA_EM_USO, cozinhaId));
		}
	}

	public Cozinha buscarOuFalhar(Long cozinhaId) {
		return cozinhaRepository.findById(cozinhaId)//
				.orElseThrow(() -> new CozinhaNaoEncontradaException(cozinhaId));
	}
}
