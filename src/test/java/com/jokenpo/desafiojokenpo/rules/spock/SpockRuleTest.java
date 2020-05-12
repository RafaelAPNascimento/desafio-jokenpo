package com.jokenpo.desafiojokenpo.rules.spock;

import static com.jokenpo.desafiojokenpo.domain.Jogada.*;
import com.jokenpo.desafiojokenpo.domain.Jogada;
import com.jokenpo.desafiojokenpo.domain.Jogador;
import com.jokenpo.desafiojokenpo.domain.Jokempo;
import com.jokenpo.desafiojokenpo.exception.JogadorSemJogadaException;
import com.jokenpo.desafiojokenpo.exception.JogadoresInsuficientesException;
import com.jokenpo.desafiojokenpo.exception.JogoVazioException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class SpockRuleTest
{
    private Jokempo jokempo;

    @Before
    public void init(){
        jokempo = Jokempo.getInstance(new SpockRule());
    }

    @Test
    public void deveDisparar_JogadoresInsuficientesException(){

        jokempo.add(new Jogador(Jogada.PAPEL));

        Assert.assertThrows(JogadoresInsuficientesException.class, () -> jokempo.jogar());
    }

    @Test
    public void deveDisparar_JogoVazioException(){

        Assert.assertThrows(JogoVazioException.class, () -> jokempo.jogar());
    }

    @Test
    public void deveDisparar_JogadorSemJogadaException(){

        Assert.assertThrows(JogadorSemJogadaException.class, () -> jokempo.add(new Jogador(null)));
    }

    @Test
    public void deveDarEmpateSemEliminados()
    {
        jokempo.add(new Jogador(Jogada.PAPEL));
        jokempo.add(new Jogador(Jogada.TESOURA));
        jokempo.add(new Jogador(Jogada.PEDRA));

        Assert.assertEquals(jokempo.jogar().getJogadoresEmpatados().length, 3);
    }

    @Test
    public void deveDarEmpateSemEliminadosT2()
    {
        jokempo.add(new Jogador(PAPEL));
        jokempo.add(new Jogador(PEDRA));
        jokempo.add(new Jogador(LAGARTO));

        Assert.assertEquals(jokempo.jogar().getJogadoresEmpatados().length, 3);
    }

    @Test
    public void deveDarEmpateSemEliminadosT3()
    {
        jokempo.add(new Jogador(SPOCK));
        jokempo.add(new Jogador(PEDRA));
        jokempo.add(new Jogador(LAGARTO));

        Assert.assertEquals(jokempo.jogar().getJogadoresEmpatados().length, 3);
    }

    @Test
    public void deveDarEmpateSemEliminadosT4()
    {
        jokempo.add(new Jogador(SPOCK));
        jokempo.add(new Jogador(TESOURA));
        jokempo.add(new Jogador(LAGARTO));

        Assert.assertEquals(jokempo.jogar().getJogadoresEmpatados().length, 3);
    }

    @Test
    public void deveDarEmpateSemEliminadosT5()
    {
        jokempo.add(new Jogador(SPOCK));
        jokempo.add(new Jogador(TESOURA));
        jokempo.add(new Jogador(PAPEL));

        Assert.assertEquals(jokempo.jogar().getJogadoresEmpatados().length, 3);
    }

    @Test
    public void deveDarEmpateTodasJogadasIguaisT1()
    {
        jokempo.add(new Jogador(SPOCK));
        jokempo.add(new Jogador(SPOCK));
        jokempo.add(new Jogador(SPOCK));

        Assert.assertEquals(jokempo.jogar().getJogadoresEmpatados().length, 3);
    }

    @Test
    public void deveDarEmpateTodasJogadasIguaisT2()
    {
        jokempo.add(new Jogador(LAGARTO));
        jokempo.add(new Jogador(LAGARTO));

        Assert.assertEquals(jokempo.jogar().getJogadoresEmpatados().length, 2);
    }

    @Test
    public void deveDarEmpateTodasJogadasIguaisT3()
    {
        jokempo.add(new Jogador(PAPEL));
        jokempo.add(new Jogador(PAPEL));

        Assert.assertEquals(jokempo.jogar().getJogadoresEmpatados().length, 2);
    }

    @Test
    public void deveVencerSpockT1()
    {
        jokempo.add(new Jogador(PEDRA));
        jokempo.add(new Jogador(SPOCK));

        Assert.assertEquals(jokempo.jogar().getVencedor().getJogada(), SPOCK);
    }

    @Test
    public void deveVencerSpockT2()
    {
        jokempo.add(new Jogador(PEDRA));
        jokempo.add(new Jogador(SPOCK));
        jokempo.add(new Jogador(TESOURA));

        Assert.assertEquals(jokempo.jogar().getVencedor().getJogada(), SPOCK);
    }

    @Test
    public void deveEliminarSpockEempateEntrePapel()
    {
        jokempo.add(new Jogador(PAPEL));
        jokempo.add(new Jogador(SPOCK));
        jokempo.add(new Jogador(PAPEL));

        Assert.assertEquals(jokempo.jogar().getJogadoresEmpatados().length, 2);
        Assert.assertEquals(jokempo.jogar().getJogadoresEmpatados()[0].getJogada(), PAPEL);
        Assert.assertEquals(jokempo.jogar().getJogadoresEmpatados()[1].getJogada(), PAPEL);
    }

    @Test
    public void deveEliminarTesouraEempateEntrePedra()
    {
        jokempo.add(new Jogador(TESOURA));
        jokempo.add(new Jogador(PEDRA));
        jokempo.add(new Jogador(PEDRA));

        Assert.assertEquals(jokempo.jogar().getJogadoresEmpatados().length, 2);
        Assert.assertEquals(jokempo.jogar().getJogadoresEmpatados()[0].getJogada(), PEDRA);
        Assert.assertEquals(jokempo.jogar().getJogadoresEmpatados()[1].getJogada(), PEDRA);
    }

    @Test
    public void deveNaoAddicionarSpockEGanharTesoura(){

        jokempo.add(new Jogador(TESOURA));
        jokempo.add(new Jogador(PAPEL));
        jokempo.add(new Jogador(LAGARTO));
        jokempo.add(new Jogador(4, SPOCK));

        Assert.assertEquals(jokempo.getJogadores().size(), 3);
        Assert.assertEquals(jokempo.jogar().getVencedor().getJogada(), TESOURA);
    }

    @Test
    public void deveRemoverOJogadorId1EVencerLagarto(){

        jokempo.add(new Jogador(TESOURA));
        jokempo.add(new Jogador(PAPEL));
        jokempo.add(new Jogador(LAGARTO));

        jokempo.remove(new Jogador(1, TESOURA));

        Assert.assertEquals(jokempo.getJogadores().size(), 2);
        Assert.assertEquals(jokempo.jogar().getVencedor().getJogada(), LAGARTO);
    }

    @Test
    public void deveRemoverOJogadorId2EDarEmpate(){

        jokempo.add(new Jogador(PAPEL));
        jokempo.add(new Jogador(TESOURA));
        jokempo.add(new Jogador(PAPEL));

        jokempo.remove(new Jogador(2, TESOURA));

        Assert.assertEquals(jokempo.getJogadores().size(), 2);
        Assert.assertEquals(jokempo.jogar().getJogadoresEmpatados().length, 2);
    }
}
