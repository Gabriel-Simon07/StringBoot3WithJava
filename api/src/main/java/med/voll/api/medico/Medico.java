package med.voll.api.medico;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.endereco.DadosEndereco;
import med.voll.api.endereco.Endereco;

@Table(name = "medicos")
@Entity(name = "Medico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String crm;
    private String telefone;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @Embedded
    private Endereco endereco;

    private Boolean ativo;

    public Medico(DadosCadastroMedico dadosMedico) {
        this.ativo = true;
        this.nome = dadosMedico.nome();
        this.email = dadosMedico.email();
        this.crm = dadosMedico.crm();
        this.telefone = dadosMedico.telefone();
        this.endereco = new Endereco(dadosMedico.endereco());
        this.especialidade = dadosMedico.especialidade();
    }

    public void atualizarInformacoes(DadosAtualizacaoMedico dadosMedico) {
        if(dadosMedico.nome() != null) {
            this.nome = dadosMedico.nome();
        }
        if(dadosMedico.telefone() != null) {
            this.telefone = dadosMedico.telefone();
        }
        if(dadosMedico.dadosEndereco() != null) {
            this.endereco.atualizarInformacoes(dadosMedico.dadosEndereco());
        }
    }

    public void excluir() {
        this.ativo = false;
    }
}
