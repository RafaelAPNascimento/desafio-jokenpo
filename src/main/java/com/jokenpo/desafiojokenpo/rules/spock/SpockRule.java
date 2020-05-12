package com.jokenpo.desafiojokenpo.rules.spock;

import static com.jokenpo.desafiojokenpo.domain.Jogada.*;
import com.jokenpo.desafiojokenpo.domain.Jogada;
import com.jokenpo.desafiojokenpo.domain.Jogador;
import com.jokenpo.desafiojokenpo.domain.Resultado;
import com.jokenpo.desafiojokenpo.rules.JokempoRule;

import java.util.Comparator;
import java.util.Set;


public class SpockRule implements JokempoRule
{
    private static Comparator<Jogada> comparator;

    static {
        comparator = (j1, j2) -> {

            if (j1 == SPOCK && (j2 == TESOURA || j2 == PEDRA))
                return 1;

            if (j1 == TESOURA && (j2 == LAGARTO || j2 == PAPEL))
                return 1;

            if (j1 == LAGARTO && (j2 == SPOCK || j2 == PAPEL))
                return 1;

            if (j1 == PAPEL && (j2 == SPOCK || j2 == PEDRA))
                return 1;

            if (j1 == PEDRA && (j2 == TESOURA || j2 == LAGARTO))
                return 1;

            if (j1 == SPOCK && (j2 == PAPEL || j2 == LAGARTO))
                return -1;

            if (j1 == TESOURA && (j2 == PEDRA || j2 == SPOCK))
                return -1;

            if (j1 == LAGARTO && (j2 == TESOURA || j2 == PEDRA))
                return -1;

            if (j1 == PEDRA && (j2 == PAPEL || j2 == SPOCK))
                return -1;

            if (j1 == PAPEL && (j2 == TESOURA || j2 == LAGARTO))
                return -1;

            return 0;
        };
    }

    @Override
    public Resultado apply(Set<Jogador> jogadores)
    {
        if (isEmpate(jogadores))
        {
            return Resultado.empateSemEliminados(jogadores);
        }

        Jogador[] arr = getVencedores(jogadores);

        if (arr.length == 2)
            return Resultado.empate(arr);

        return Resultado.vitoria(arr[0]);
    }

    private Jogador[] getVencedores(Set<Jogador> jogadores)
    {
        Jogador[] arr =
                jogadores.stream()
                        .sorted((j1, j2) -> comparator.compare(j2.getJogada(), j1.getJogada()))
                        .limit(2)
                        .toArray(Jogador[]::new);

        if (arr[0].getJogada() == arr[1].getJogada())
            return arr; //empate

        return new Jogador[] {arr[0]};  //vencedor
    }

    private boolean isEmpate(Set<Jogador> jogadores) {

        // empate por igualdade entre as jogadas
        long count = jogadores.stream()
                .map(Jogador::getJogada)
                .distinct()
                .count();

        if (count == 1)
            return true;

        else if (jogadores.size() == 2)
            return false;

        // empate por assimetria: as 3 jogadas são diferentes, porem cada uma ganha de uma e perde da outra
        Jogador[] arr = jogadores.toArray(Jogador[]::new);

        int c1 = comparator.compare(arr[0].getJogada(), arr[1].getJogada());
        int c2 = comparator.compare(arr[1].getJogada(), arr[2].getJogada());
        return c1 == c2;
    }
}
