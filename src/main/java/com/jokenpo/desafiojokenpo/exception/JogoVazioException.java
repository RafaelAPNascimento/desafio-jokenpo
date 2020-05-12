package com.jokenpo.desafiojokenpo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FORBIDDEN, reason = "Não há Jogadas. Deve haver 2 ou 3 jogadas")
public class JogoVazioException extends RuntimeException
{

    public JogoVazioException(String message) {
        super(message);
    }
}
