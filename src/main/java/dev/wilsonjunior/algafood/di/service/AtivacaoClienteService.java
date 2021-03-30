package dev.wilsonjunior.algafood.di.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import dev.wilsonjunior.algafood.di.modelo.Cliente;
import dev.wilsonjunior.algafood.di.notificacao.NivelUrgencia;
import dev.wilsonjunior.algafood.di.notificacao.Notificador;
import dev.wilsonjunior.algafood.di.notificacao.TipoDoNotificador;

@Component
public class AtivacaoClienteService {

	@TipoDoNotificador(NivelUrgencia.SEM_URGENCIA)
	@Autowired
	private ApplicationEventPublisher eventPublisher;

	@TipoDoNotificador(NivelUrgencia.SEM_URGENCIA)
	@Autowired
	private Notificador notificador;

	public void ativar(Cliente cliente) {
		cliente.ativar();

		eventPublisher.publishEvent(new ClienteAtivadoEvent(cliente));
	}

}
