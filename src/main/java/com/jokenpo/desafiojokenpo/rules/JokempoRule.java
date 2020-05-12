package com.jokenpo.desafiojokenpo.rules;


import com.jokenpo.desafiojokenpo.domain.Jogador;
import com.jokenpo.desafiojokenpo.domain.Resultado;

import java.util.Set;
import java.util.function.Function;

public interface JokempoRule extends Function<Set<Jogador>, Resultado>
{

}
