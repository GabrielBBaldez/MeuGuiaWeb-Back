package com.api.meuGuiaWeb.controller;

import com.api.meuGuiaWeb.cliente.Cliente;
import com.api.meuGuiaWeb.cliente.ClienteService;
import com.api.meuGuiaWeb.cliente.DadosCadastroCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("cliente")
@CrossOrigin(origins = "http://localhost:3000")
public class ClienteController {
    private final ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> getAllClientes() {
        List<Cliente> clientes = clienteService.getAllClientes();
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable Long id) {
        Optional<Cliente> clienteOptional = clienteService.getClienteById(id);

        if (clienteOptional.isPresent()) {
            Cliente cliente = clienteOptional.get();
            return ResponseEntity.ok(cliente);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Cliente> createCliente(@RequestBody DadosCadastroCliente dadosCadastroCliente) {
        Cliente createdCliente = clienteService.createCliente(new Cliente(dadosCadastroCliente));
        return  ResponseEntity.ok(createdCliente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCliente(@PathVariable("id") Long id) {
        boolean deleted = clienteService.deleteCliente(id);
        if (deleted) {
            return ResponseEntity.ok("Cliente deletado com sucesso.");
        } else {
            return ResponseEntity.badRequest().body("Ocorreu um erro ao deletar o cliente");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCliente(@PathVariable("id") Long id, @RequestBody Cliente cliente) {
        Cliente updatedCliente = clienteService.updateCliente(id, cliente);
        if (updatedCliente != null) {
            return ResponseEntity.ok("Cliente alterado com sucesso.");
        } else {
            return ResponseEntity.badRequest()
                    .body("Ocorreu um erro ao alterar o cliente, verifique se o ID do cliente existe no banco.");
        }
    }

}
