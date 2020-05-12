package com.jokenpo.desafiojokenpo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FORBIDDEN, reason = "Código do Jogador Inválido")
public class CodigoJogadorInvalidoException extends RuntimeException
{
}
