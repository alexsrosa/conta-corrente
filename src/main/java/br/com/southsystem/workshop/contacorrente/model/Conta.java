package br.com.southsystem.workshop.contacorrente.model;

import br.com.southsystem.workshop.contacorrente.model.enumeration.TipoContaEnum;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@Document(collection = "conta")
public class Conta implements Serializable {

    @Id
    private String id;

    @NotNull
    private String numeroConta;

    @NotNull
    private String agencia;

    @NotNull
    private TipoContaEnum tipoConta;

    @NotNull
    private String cpf;

    private String nome;
}
