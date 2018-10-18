package br.com.southsystem.workshop.contacorrente.service;

import br.com.southsystem.workshop.contacorrente.model.Conta;
import br.com.southsystem.workshop.contacorrente.model.enumeration.TipoContaEnum;
import br.com.southsystem.workshop.contacorrente.repository.ContaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ContaService {

    private final ContaRepository contaRepository;
    private final CpfRemoteService cpfRemoteService;

    public ContaService(ContaRepository contaRepository, CpfRemoteService cpfRemoteService) {
        this.contaRepository = contaRepository;
        this.cpfRemoteService = cpfRemoteService;
    }

    public Conta save(Conta conta){
        cpfRemoteService.findNomeByCpf(conta.getCpf())
                .ifPresent(result -> conta.setNome(result.get("name")));
        return contaRepository.save(conta);
    }

    public Optional<Conta> findOne(String id){
        return Optional.ofNullable(contaRepository.findOne(id));
    }

    public Page<Conta> findAll(Pageable pageable){
        return contaRepository.findAll(pageable);
    }

    public void delete(String id) {
        contaRepository.delete(id);
    }

    public Page<Conta> findByTipoConta(TipoContaEnum tipoContaEnum, Pageable pageable){
        return contaRepository.findByTipoConta(tipoContaEnum, pageable);
    }

    public Conta update(Conta conta) {

        Optional<Conta> one = findOne(conta.getId());
        if(one.isPresent()){
           return contaRepository.save(conta);
        }
        return null;
    }
}
