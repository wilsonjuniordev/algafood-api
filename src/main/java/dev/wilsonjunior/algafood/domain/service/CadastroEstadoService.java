package dev.wilsonjunior.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import dev.wilsonjunior.algafood.domain.exception.EntidadeEmUsoException;
import dev.wilsonjunior.algafood.domain.exception.EntidadeNaoEncontradaException;
import dev.wilsonjunior.algafood.domain.model.Estado;
import dev.wilsonjunior.algafood.domain.repository.EstadoRepository;

@Service
public class CadastroEstadoService {

	public static final String MSG_ESTADO_NAO_EXISTE = "Não existe um cadastro de estado com código %d";
	public static final String MSG_ESTADO_ESTA_EM_USO = "Estado de código %d não pode ser removido, pois está em uso";

	@Autowired
	EstadoRepository estadoRepository;

	public Estado salvar(Estado estado) {
		return estadoRepository.save(estado);
	}

	public void excluir(Long estadoId) {
		try {
			estadoRepository.deleteById(estadoId);
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(String.format(MSG_ESTADO_NAO_EXISTE, estadoId));
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(MSG_ESTADO_ESTA_EM_USO, estadoId));
		}
	}

	public Estado buscarOuFalhar(Long estadoId) {
		return estadoRepository.findById(estadoId)//
				.orElseThrow(() -> new EntidadeNaoEncontradaException(//
						String.format(MSG_ESTADO_NAO_EXISTE, estadoId)));
	}
}
