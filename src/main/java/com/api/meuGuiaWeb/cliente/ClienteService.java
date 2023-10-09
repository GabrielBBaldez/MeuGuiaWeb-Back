package com.api.meuGuiaWeb.cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> getAllClientes(){return clienteRepository.findAll();}

    public Cliente createCliente(Cliente cliente){return clienteRepository.save(cliente);}

    public Optional<Cliente> getClienteById (Long id){return clienteRepository.findById(id);}

 public Cliente updateCliente(Long id, Cliente clienteAtualizado){
        Optional<Cliente> optionalCliente = clienteRepository.findById(id);
        if(optionalCliente.isPresent()){
            Cliente cliente = optionalCliente.get();
            cliente.setEstado(clienteAtualizado.getEstado());
            cliente.setEmail(clienteAtualizado.getEmail());
            cliente.setNome(clienteAtualizado.getNome());
            cliente.setCidade(clienteAtualizado.getCidade());
            cliente.setTelefone(clienteAtualizado.getTelefone());
            cliente.setProfissao(clienteAtualizado.getProfissao());
            cliente.setObservacoes(clienteAtualizado.getObservacoes());
          return clienteRepository.save(cliente);
        }else {
            throw new IllegalArgumentException("Erro ao tentar atualizar o Cliente.");
        }
 }

    public boolean deleteCliente(Long id) {
        if (clienteRepository.existsById(id)) {
            clienteRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
