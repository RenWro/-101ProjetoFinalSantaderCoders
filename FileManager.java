import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    private static final String FILE_PATH = "agenda.txt";

    public void salvarContatos(List<Contato> contatos) {
        // Lógica para escrever contatos no arquivo
    }

    public List<Contato> carregarContatos() {
        // Lógica para ler contatos do arquivo
    }

    private String contatoParaString(Contato contato) {
        // Conversão de Contato para String
        // Exemplo: "1,Carlos,Silva,11,99999-9999"
        StringBuilder sb = new StringBuilder();
        sb.append(contato.getId()).append(",");
        sb.append(contato.getNome()).append(",");
        sb.append(contato.getSobreNome());

        for (Telefone telefone : contato.getTelefones()) {
            sb.append(",").append(telefone.getDdd()).append(",").append(telefone.getNumero());
        }

        return sb.toString();
    }

    private Contato stringParaContato(String str) {
        // Conversão de String para Contato
        String[] partes = str.split(",");
        Long id = Long.parseLong(partes[0]);
        String nome = partes[1];
        String sobreNome = partes[2];
        List<Telefone> telefones = new ArrayList<>();

        for (int i = 3; i < partes.length; i += 2) {
            telefones.add(new Telefone(id, partes[i], partes[i + 1]));
        }

        return new Contato(id, nome, sobreNome, telefones);
    }
}
