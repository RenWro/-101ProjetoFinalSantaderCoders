import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Agenda {
    private List<Contato> contatos;

    public Agenda() {
        contatos = new ArrayList<>();
    }

    public void adicionarContato(Contato contato) {
        if (contatos.stream().anyMatch(c -> c.getId().equals(contato.getId()))) {
            throw new RuntimeException("Contato com este ID já existe.");
        }
        contatos.add(contato);
    }

    public void removerContato(Long id) {
        contatos.removeIf(c -> c.getId().equals(id));
    }

    public void editarContato(Long id, Contato novoContato) {
        Optional<Contato> contatoExistente = contatos.stream()
                                                     .filter(c -> c.getId().equals(id))
                                                     .findFirst();
        if (contatoExistente.isPresent()) {
            contatos.remove(contatoExistente.get());
            contatos.add(novoContato);
        } else {
            throw new RuntimeException("Contato não encontrado.");
        }
    }

    public List<Contato> listarContatos() {
        return new ArrayList<>(contatos);
    }

    public List<Contato> buscarContatoPorNome(String nome) {
        return contatos.stream()
                       .filter(contato -> contato.getNome().equalsIgnoreCase(nome))
                       .collect(Collectors.toList());
    }

    // Outros métodos conforme necessário...
}
