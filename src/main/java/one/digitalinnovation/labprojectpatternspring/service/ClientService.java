package one.digitalinnovation.labprojectpatternspring.service;

import one.digitalinnovation.labprojectpatternspring.model.Client;

public interface ClientService  {


    Iterable<Client> searchAll();

    Client searchById(Long id);

    void insert(Client cliente);

    void att(Long id, Client cliente);

    void erase(Long id);
}
