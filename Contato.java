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
        // Implementação para converter um contato em uma string
        // Exemplo: "1,Carlos,Silva,11,999999999"
        return "";
    }

    private Contato stringParaContato(String str) {
        // Implementação para converter uma string em um contato
        return null;
    }

}
