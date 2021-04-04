package dev.wilsonjunior.algafood.domain.repository;

import java.util.List;

import dev.wilsonjunior.algafood.domain.model.Permissao;

public interface PermissaoRepository {

	List<Permissao> listar();

	Permissao buscarPorId(Long id);

	Permissao salvar(Permissao permissao);

	void remover(Permissao permissao);
}
