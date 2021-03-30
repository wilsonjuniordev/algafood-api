package dev.wilsonjunior.algafood.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import dev.wilsonjunior.algafood.di.notificacao.NivelUrgencia;
import dev.wilsonjunior.algafood.di.notificacao.Notificador;
import dev.wilsonjunior.algafood.di.notificacao.TipoDoNotificador;
import dev.wilsonjunior.algafood.di.service.ClienteAtivadoEvent;

@Component
public class NotificacaoService {

	@TipoDoNotificador(NivelUrgencia.SEM_URGENCIA)
	@Autowired
	private Notificador notificador;

	@EventListener
	public void clienteAtivadoListener(ClienteAtivadoEvent event) {
		notificador.notificar(event.getCliente(), "Seu cadastro no sistema está ativo!");
	}
}