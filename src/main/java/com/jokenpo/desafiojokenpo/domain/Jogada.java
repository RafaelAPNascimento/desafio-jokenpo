package com.jokenpo.desafiojokenpo.domain;

public enum Jogada
{

    PEDRA (1),
    PAPEL (2),
    TESOURA (3),
    SPOCK (4),
    LAGARTO (5);


    int ref;

    Jogada(int ref) {
        this.ref = ref;
    }

    public int getRef(){
        return ref;
    }

}
