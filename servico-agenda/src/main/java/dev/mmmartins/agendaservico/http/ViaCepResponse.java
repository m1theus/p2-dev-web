package dev.mmmartins.agendaservico.http;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ViaCepResponse(
        String cep,
        String logradouro,
        String complemento,
        String bairro,
        @JsonProperty("localidade") String cidade,
        String uf,
        String estado
) {
}