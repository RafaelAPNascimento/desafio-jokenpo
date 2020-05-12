package com.jokenpo.desafiojokenpo.resource;

import com.jokenpo.desafiojokenpo.domain.Jogada;
import com.jokenpo.desafiojokenpo.domain.Jogador;
import com.jokenpo.desafiojokenpo.domain.Jokempo;
import com.jokenpo.desafiojokenpo.domain.Resultado;
import com.jokenpo.desafiojokenpo.exception.JogadorSemJogadaException;
import com.jokenpo.desafiojokenpo.exception.CodigoJogadorInvalidoException;
import com.jokenpo.desafiojokenpo.exception.CodigoJogadorNaoEncontradoException;
import com.jokenpo.desafiojokenpo.rules.spock.SpockRule;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Set;

@RestController
@RequestMapping("/api")
@Slf4j
@Api(value = "Jokenpo API")
public class JokenpoApi
{
    private Jokempo jokempo;

    @PostConstruct
    public void init()
    {
        jokempo = Jokempo.getInstance(new SpockRule());
    }

    @PostMapping("/addjogada")
    public String adicionarJogada(
            @ApiParam(value = "String que representa a jogada. Valores válidos: \"SPOCK\", \"PAPEL\", \"TESOURA\", \"LAGARTO\", \"PEDRA\"", required = true)
            @Valid @NotNull @RequestBody Jogada jogada)
    {
        boolean added = jokempo.add(new Jogador(jogada));

        if (added)
            return "Joga adicionada: "+jogada;
        else
            return "A lista já possui 3 jogadas. Remova uma para adicionare outra";
    }

    @GetMapping("/jogadores")
    public ResponseEntity<Set<Jogador>> getJogadores()
    {
        return ResponseEntity.ok(jokempo.getJogadores());
    }

    @PutMapping("/atualizajogada")
    public ResponseEntity atualizaJogada(@RequestBody Jogador jogador)
    {
        if (jogador.getId() == null || jogador.getId() <= 0)
        {
            throw new CodigoJogadorInvalidoException();
        }
        else if (jogador.getJogada() == null)
        {
            throw new JogadorSemJogadaException("Jogador Sem jogada");
        }

        boolean removed = jokempo.atualizaJogador(jogador);

        if (!removed)
            throw new CodigoJogadorNaoEncontradoException();

        return ResponseEntity.ok(jokempo.getJogadores());
    }

    @DeleteMapping("/removejogada/{id}")
    public ResponseEntity removeJogada(@PathVariable("id") Integer id)
    {
        if (id == null || id <= 0)
        {
            throw new CodigoJogadorInvalidoException();
        }
        boolean removed = jokempo.remove(id);

        if (!removed)
            throw new CodigoJogadorNaoEncontradoException();

        return ResponseEntity.ok("removido");
    }

    @DeleteMapping("/removetodos")
    public String removeJogada()
    {
        jokempo.removeAll();
        return "Todas as Jogadas Removidas";
    }

    @PutMapping("/jogar")
    public ResponseEntity<Resultado> jogar()
    {
        Resultado resultado = jokempo.jogar();
        return ResponseEntity.ok(resultado);
    }

}
