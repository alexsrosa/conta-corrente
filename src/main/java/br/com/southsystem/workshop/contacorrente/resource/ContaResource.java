package br.com.southsystem.workshop.contacorrente.resource;

import br.com.southsystem.workshop.contacorrente.model.Conta;
import br.com.southsystem.workshop.contacorrente.model.enumeration.TipoContaEnum;
import br.com.southsystem.workshop.contacorrente.service.ContaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/conta")
public class ContaResource {

    private ContaService contaService;

    public ContaResource(ContaService contaService) {
        this.contaService = contaService;
    }

    @PostMapping
    public ResponseEntity<Conta> create(@Valid @RequestBody Conta conta){
        return ResponseEntity.ok(contaService.save(conta));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Conta> findOne(@Valid @PathVariable(name = "id") String id){
        return contaService.findOne(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/tipo-conta/{tipoConta}")
    public ResponseEntity<Page<Conta>> findTipoConta(
           @PathVariable(name = "tipoConta") TipoContaEnum tipoConta, Pageable pageable){
        return ResponseEntity.ok(contaService.findByTipoConta(tipoConta, pageable));
    }

    @GetMapping
    public ResponseEntity<Page<Conta>> findAll(Pageable pageable){
        return ResponseEntity.ok(contaService.findAll(pageable));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable(name = "id") String id){
        contaService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Conta> update(@Valid @RequestBody Conta conta){
        return ResponseEntity.ok(contaService.update(conta));
    }
}
