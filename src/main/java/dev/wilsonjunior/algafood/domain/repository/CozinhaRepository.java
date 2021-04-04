package dev.wilsonjunior.algafood.domain.repository;

import java.util.List;

import dev.wilsonjunior.algafood.domain.model.Cozinha;

public interface CozinhaRepository {

	List<Cozinha> listar();

	Cozinha buscarPorId(Long id);

	Cozinha salvar(Cozinha cozinha);

	void remover(Cozinha cozinha);
}
