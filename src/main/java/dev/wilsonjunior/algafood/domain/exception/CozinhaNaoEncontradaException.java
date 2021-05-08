package dev.wilsonjunior.algafood.domain.exception;

public class CozinhaNaoEncontradaException extends EntidadeNaoEncontradaException {

	public static final String MSG_COZINHA_NAO_EXISTE = "Não existe um cadastro de cozinha com código %d";

	private static final long serialVersionUID = 1L;

	public CozinhaNaoEncontradaException(String mensagem) {
		super(mensagem);
	}
	
	public CozinhaNaoEncontradaException(Long estadoId) {
		this(String.format(MSG_COZINHA_NAO_EXISTE, estadoId));
	}

}
