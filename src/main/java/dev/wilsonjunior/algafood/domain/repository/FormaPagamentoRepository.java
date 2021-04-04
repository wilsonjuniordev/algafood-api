package dev.wilsonjunior.algafood.domain.repository;

import java.util.List;

import dev.wilsonjunior.algafood.domain.model.FormaPagamento;

public interface FormaPagamentoRepository {

	List<FormaPagamento> listar();

	FormaPagamento buscarPorId(Long id);

	FormaPagamento salvar(FormaPagamento formaPagamento);

	void remover(FormaPagamento formaPagamento);
}
