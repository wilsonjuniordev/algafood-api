package dev.wilsonjunior.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import dev.wilsonjunior.algafood.domain.exception.EntidadeEmUsoException;
import dev.wilsonjunior.algafood.domain.exception.EntidadeNaoEncontradaException;
import dev.wilsonjunior.algafood.domain.model.Cidade;
import dev.wilsonjunior.algafood.domain.model.Estado;
import dev.wilsonjunior.algafood.domain.repository.CidadeRepository;
import dev.wilsonjunior.algafood.domain.repository.EstadoRepository;

@Service
public class CadastroCidadeService {

	public static final String MSG_ESTADO_NAO_EXISTE = "Não existe cadastro de estado com código %d";
	public static final String MSG_CIDADE_NAO_EXISTE = "Não existe um cadastro de cidade com código %d";
	public static final String MSG_CIDADE_ESTA_EM_USO = "Cidade de código %d não pode ser removida, pois está em uso";

	@Autowired
	CidadeRepository cidadeRepository;

	@Autowired
	EstadoRepository estadoRepository;

	public Cidade salvar(Cidade cidade) {
		Long estadoId = cidade.getEstado().getId();
		Estado estado = estadoRepository.findById(estadoId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException(
						String.format(MSG_ESTADO_NAO_EXISTE, estadoId)));

		cidade.setEstado(estado);

		return cidadeRepository.save(cidade);
	}

	public void excluir(Long cidadeId) {
		try {
			cidadeRepository.deleteById(cidadeId);
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(String.format(MSG_CIDADE_NAO_EXISTE, cidadeId));
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(MSG_CIDADE_ESTA_EM_USO, cidadeId));
		}
	}

}
