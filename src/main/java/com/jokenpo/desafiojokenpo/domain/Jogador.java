package com.jokenpo.desafiojokenpo.domain;

import com.jokenpo.desafiojokenpo.exception.JogadorSemJogadaException;

import java.util.Objects;

public class Jogador
{
    private Integer id;
    private Jogada jogada;

    public Jogador(Integer id, Jogada jogada)
    {

        if (jogada == null)
            throw new JogadorSemJogadaException("Todo jogador deve ter uma jogada!");

        this.id = id;
        this.jogada = jogada;
    }

    public Jogador(Jogada jogada)
    {

        if (jogada == null)
            throw new JogadorSemJogadaException("Todo jogador deve ter uma jogada!");

        this.jogada = jogada;
    }

    Jogador(Integer id)
    {
        this.id = id;
    }


    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Jogada getJogada()
    {
        return jogada;
    }

    public void setJogada(Jogada jogada)
    {
        this.jogada = jogada;
    }

    @Override
    public boolean equals(Object o)
    {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Jogador jogador = (Jogador) o;
        return id == jogador.id;
    }

    @Override
    public int hashCode()
    {
        return Objects.hashCode(id);
    }

    @Override
    public String toString()
    {
        return "Jogador "+id;
    }
}
