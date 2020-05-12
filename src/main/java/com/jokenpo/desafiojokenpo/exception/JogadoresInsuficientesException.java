package com.jokenpo.desafiojokenpo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FORBIDDEN, reason = "Número de Jogadas Insuficientes para Jogar. Deve haver 2 ou 3 jogadas")
public class JogadoresInsuficientesException extends RuntimeException
{

    public JogadoresInsuficientesException(String message) {
        super(message);
    }
}
