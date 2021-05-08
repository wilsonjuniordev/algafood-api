package dev.wilsonjunior.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import dev.wilsonjunior.algafood.domain.exception.CidadeNaoEncontradaException;
import dev.wilsonjunior.algafood.domain.exception.EntidadeEmUsoException;
import dev.wilsonjunior.algafood.domain.model.Cidade;
import dev.wilsonjunior.algafood.domain.model.Estado;
import dev.wilsonjunior.algafood.domain.repository.CidadeRepository;

@Service
public class CadastroCidadeService {

	public static final String MSG_CIDADE_ESTA_EM_USO = "Cidade de código %d não pode ser removida, pois está em uso";

	@Autowired
	CidadeRepository cidadeRepository;

	@Autowired
	CadastroEstadoService cadastroEstadoService;

	public Cidade salvar(Cidade cidade) {
		Long estadoId = cidade.getEstado().getId();
		Estado estado = cadastroEstadoService.buscarOuFalhar(estadoId);

		cidade.setEstado(estado);

		return cidadeRepository.save(cidade);
	}

	public void excluir(Long cidadeId) {
		try {
			cidadeRepository.deleteById(cidadeId);
		} catch (EmptyResultDataAccessException e) {
			throw new CidadeNaoEncontradaException(cidadeId);
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(//
					String.format(MSG_CIDADE_ESTA_EM_USO, cidadeId));
		}
	}

	public Cidade buscarOuFalhar(Long cidadeId) {
		return cidadeRepository.findById(cidadeId)//
				.orElseThrow(() -> new CidadeNaoEncontradaException(cidadeId));
	}

}
