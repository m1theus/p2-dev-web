package dev.mmmartins.agendaservico.controller;

import dev.mmmartins.agendaservico.http.ViaCepHttpClient;
import dev.mmmartins.agendaservico.http.ViaCepResponse;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/enderecos")
public class EnderecoRestController {
    private final ViaCepHttpClient viaCepHttpClient;

    public EnderecoRestController(final ViaCepHttpClient viaCepHttpClient) {
        this.viaCepHttpClient = viaCepHttpClient;
    }

    @Cacheable(cacheNames = "enderecoCache",
            key = "#cep",
            unless = "#result == null")
    @GetMapping("/cep/{cep}")
    public ViaCepResponse getCep(@PathVariable final String cep) {
        return viaCepHttpClient.getCep(cep);
    }
}
