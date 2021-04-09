package dev.wilsonjunior.algafood.domain.repository;

import java.util.List;

import dev.wilsonjunior.algafood.domain.model.Restaurante;

public interface RestauranteRepository {

	List<Restaurante> listar();

	Restaurante buscarPorId(Long id);

	Restaurante salvar(Restaurante restaurante);

	void remover(Restaurante restaurante);
}
