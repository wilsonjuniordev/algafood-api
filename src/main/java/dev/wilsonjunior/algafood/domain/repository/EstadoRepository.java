package dev.wilsonjunior.algafood.domain.repository;

import java.util.List;

import dev.wilsonjunior.algafood.domain.model.Estado;

public interface EstadoRepository {

	List<Estado> listar();

	Estado buscarPorId(Long id);

	Estado salvar(Estado estado);

	void remover(Estado estado);
}
