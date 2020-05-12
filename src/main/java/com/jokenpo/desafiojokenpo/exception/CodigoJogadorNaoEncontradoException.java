package com.jokenpo.desafiojokenpo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Código do Jogador Não Encontrado")
public class CodigoJogadorNaoEncontradoException extends RuntimeException
{
}
