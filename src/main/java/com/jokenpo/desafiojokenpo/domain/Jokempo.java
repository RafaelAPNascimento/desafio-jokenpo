package com.jokenpo.desafiojokenpo.domain;

import com.jokenpo.desafiojokenpo.exception.JogadoresInsuficientesException;
import com.jokenpo.desafiojokenpo.exception.JogoVazioException;
import com.jokenpo.desafiojokenpo.rules.JokempoRule;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public final class Jokempo
{

    final int PLAYERS_LIMIT = 3;

    private Set<Jogador> jogadores;

    private JokempoRule regra;

    private Jokempo(){};

    public static Jokempo getInstance(JokempoRule regra)
    {

        Jokempo jokempo = new Jokempo();
        jokempo.jogadores = new HashSet<>();
        jokempo.regra = regra;
        return jokempo;
    }

    public boolean add(Jogador jogador)
    {

        if (jogadores.size() == PLAYERS_LIMIT)
            return false;

        jogador.setId(jogadores.size() + 1);
        return jogadores.add(jogador);
    }

    public boolean remove(Jogador jogador)
    {

        return jogadores.remove(jogador);
    }

    public boolean remove(Integer id)
    {

        Jogador jogador = new Jogador(id);
        return jogadores.remove(jogador);
    }

    public boolean atualizaJogador(Jogador jogador)
    {
        boolean removed = jogadores.remove(jogador);
        if (!removed)
            return removed;

        return jogadores.add(jogador);
    }

    public Resultado jogar()
    {
        if (jogadores.isEmpty())
            throw new JogoVazioException("Não há jogadores para jogar!");

        if (jogadores.size() == 1)
            throw new JogadoresInsuficientesException("Só há 1 jogador. Adicione outro para jogar!");

        return regra.apply(jogadores);
    }

    public Set<Jogador> getJogadores()
    {
        return Collections.unmodifiableSet( jogadores);
    }

    public void removeAll()
    {
        jogadores.clear();
    }
}
