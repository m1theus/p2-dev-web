package dev.mmmartins.agendaservico.http;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "viacep", url = "${feign.client.viacep.url}", configuration = FeignConfig.class)
public interface ViaCepHttpClient {

    @RequestMapping(
            path = "/{cep}/json",
            method = RequestMethod.GET,
            consumes = "application/json")
    ViaCepResponse getCep(@PathVariable final String cep);

}
