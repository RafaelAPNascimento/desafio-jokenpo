package com.jokenpo.desafiojokenpo.domain;

import java.util.Collection;

public class Resultado
{
    private String mensagem;
    private Jogador[] jogadores;
    private Jogador vencedor;

    private Resultado(){ }

    public static Resultado empateSemEliminados(Jogador[] jogadores)
    {
        Resultado resultado = new Resultado();
        resultado.mensagem = "Empate.";
        resultado.jogadores = jogadores;
        return resultado;
    }

    public static Resultado empate(Jogador[] jogadores)
    {
        Resultado resultado = new Resultado();
        resultado.mensagem = String.format("Empate entre os jogadores %s e %s", jogadores[0], jogadores[1]);
        resultado.jogadores = jogadores;
        return resultado;
    }

    public static Resultado empate(Collection<Jogador> jogadores)
    {
        return empateSemEliminados(jogadores.toArray(Jogador[]::new));
    }

    public static Resultado empateSemEliminados(Collection<Jogador> jogadores)
    {
        return empateSemEliminados(jogadores.toArray(Jogador[]::new));
    }

    public static Resultado vitoria(Jogador jogador) {

        Resultado resultado = new Resultado();
        resultado.mensagem = String.format("Vitoria do jogador %s", jogador);
        resultado.vencedor = jogador;
        return resultado;
    }

    public Jogador[] getJogadoresEmpatados(){
        return jogadores;
    }

    public Jogador getVencedor(){
        return vencedor;
    }

    public String getMensagem() {
        return mensagem;
    }
}
