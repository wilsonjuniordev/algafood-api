package dev.wilsonjunior.algafood.domain.exception;

public class RestauranteNaoEncontradoException extends EntidadeNaoEncontradaException {

	public static final String MSG_RESTAURANTE_NAO_EXISTE = "Não existe um cadastro de restaurante com código %d";

	private static final long serialVersionUID = 1L;

	public RestauranteNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public RestauranteNaoEncontradoException(Long estadoId) {
		this(String.format(MSG_RESTAURANTE_NAO_EXISTE, estadoId));
	}

}
