package dev.wilsonjunior.algafood.domain.exception;

public class EstadoNaoEncontradoException extends EntidadeNaoEncontradaException {

	public static final String MSG_ESTADO_NAO_EXISTE = "Não existe um cadastro de estado com código %d";

	private static final long serialVersionUID = 1L;

	public EstadoNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public EstadoNaoEncontradoException(Long estadoId) {
		this(String.format(MSG_ESTADO_NAO_EXISTE, estadoId));
	}

}
