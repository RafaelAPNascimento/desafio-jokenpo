package com.jokenpo.desafiojokenpo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FORBIDDEN, reason = "Jogador Sem Jogada!")
public class JogadorSemJogadaException extends RuntimeException
{
    public JogadorSemJogadaException(String message) {
        super(message);
    }
}
