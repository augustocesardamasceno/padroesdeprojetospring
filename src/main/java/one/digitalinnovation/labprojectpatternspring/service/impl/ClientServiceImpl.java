package one.digitalinnovation.labprojectpatternspring.service.impl;

import one.digitalinnovation.labprojectpatternspring.model.Adress;
import one.digitalinnovation.labprojectpatternspring.model.AdressRepository;
import one.digitalinnovation.labprojectpatternspring.model.Client;
import one.digitalinnovation.labprojectpatternspring.model.ClientRepository;
import one.digitalinnovation.labprojectpatternspring.service.ClientService;
import one.digitalinnovation.labprojectpatternspring.service.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private AdressRepository adressRepository;
    @Autowired
    private ViaCepService viaCepService;
    @Override
    public Iterable<Client> searchAll() {
        return clientRepository.findAll();
    }

    @Override
    public Client searchById(Long id) {
        Optional<Client> client = clientRepository.findById(id);
        return client.get();
    }

    @Override
    public void insert(Client client) {
        savingClientUsingCep(client);
    }

    private void savingClientUsingCep(Client client) {
        String cep = client.getAdress().getCep();
        Adress adress = adressRepository.findById(cep).orElseGet(() -> {
            Adress newAdress = viaCepService.checkingCep(cep);
            adressRepository.save(newAdress);
            return newAdress;
        });
        client.setAdress(adress);
        clientRepository.save(client);
    }

    @Override
    public void att(Long id, Client client) {
        Optional<Client> clientBd = clientRepository.findById(id);
        if (clientBd.isPresent()){
            savingClientUsingCep(client);
        }
    }

    @Override
    public void erase(Long id) {
        clientRepository.deleteById(id);
    }
}
