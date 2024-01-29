package src;
import src.models.Contato;
import src.models.Telefone;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    private static final String FILE_PATH = "agenda.txt";

    public void salvarContatos(List<Contato> contatos) {
        try (FileWriter writer = new FileWriter(FILE_PATH);
             BufferedWriter bw = new BufferedWriter(writer)) {

            for (Contato contato : contatos) {
                bw.write(contatoParaString(contato));
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
    }

    public List<Contato> carregarContatos() {
        List<Contato> contatos = new ArrayList<>();
        File arquivo = new File(FILE_PATH);

        if (!arquivo.exists()) {
            try {
                arquivo.createNewFile();
            } catch (IOException e) {
                System.err.println("Erro ao criar arquivo: " + FILE_PATH);
                return new ArrayList<>(); // Retorna lista vazia se falhar na criação do arquivo
            }
        }

        try (FileReader reader = new FileReader(FILE_PATH);
             BufferedReader br = new BufferedReader(reader)) {

            String linha;
            while ((linha = br.readLine()) != null) {
                Contato contato = stringParaContato(linha);
                if (contato != null) {
                    contatos.add(contato);
                }
            }
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }

        return contatos;
    }

    private String contatoParaString(Contato contato) {
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
