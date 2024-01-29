import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AgendaApp {
    private Agenda agenda;
    private FileManager fileManager;
    private Scanner scanner;

    public AgendaApp() {
        agenda = new Agenda();
        fileManager = new FileManager();
        scanner = new Scanner(System.in);
        agenda.setContatos(fileManager.carregarContatos());
    }

    public void iniciar() {
        // Lógica para exibir o menu e processar opções
    }

    private void adicionarContato() {
        // Implementação para adicionar um contato
        // Exemplo:
        System.out.print("ID do Contato: ");
        Long id = scanner.nextLong();
        scanner.nextLine();
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Sobrenome: ");
        String sobreNome = scanner.nextLine();
        System.out.print("DDD: ");
        String ddd = scanner.nextLine();
        System.out.print("Número: ");
        String numero = scanner.nextLine();

        List<Telefone> telefones = new ArrayList<>();
        telefones.add(new Telefone(id, ddd, numero));

        Contato novoContato = new Contato(id, nome, sobreNome, telefones);
        agenda.adicionarContato(novoContato);
        fileManager.salvarContatos(agenda.listarContatos());
    }

    // Implementações para removerContato, editarContato, listarContatos, buscarContatoPorNome

    public static void main(String[] args) {
        new AgendaApp().iniciar();
    }
}
