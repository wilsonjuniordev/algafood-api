package dev.wilsonjunior.algafood.di.notificacao;

import dev.wilsonjunior.algafood.di.modelo.Cliente;

public interface Notificador {

	void notificar(Cliente cliente, String mensagem);

}