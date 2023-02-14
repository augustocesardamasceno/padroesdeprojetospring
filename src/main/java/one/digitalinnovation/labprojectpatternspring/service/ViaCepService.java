package one.digitalinnovation.labprojectpatternspring.service;

import one.digitalinnovation.labprojectpatternspring.model.Adress;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "viacep", url = "https://viacep.com.br/ws")
public interface ViaCepService {
    @GetMapping("/{cep}/json/")
    Adress checkingCep(@PathVariable("cep") String cep);
}
