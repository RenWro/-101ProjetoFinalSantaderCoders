import java.util.Arrays;
import java.util.Scanner;
import java.util.List;

public class AgendaApp {
    private Agenda agenda;
    private FileManager fileManager;
    private Scanner scanner;

    public AgendaApp() {
        agenda = new Agenda();
        fileManager = new FileManager();
        scanner = new Scanner(System.in);
        inicializarContatos();
    }

    private void inicializarContatos() {
        // Contatos iniciais
        agenda.adicionarContato(new Contato(1L, "Carlos", "Silva", Arrays.asList(new Telefone(1L, "11", "99999-9999"))));
        agenda.adicionarContato(new Contato(2L, "Maria", "Fernandes", Arrays.asList(new Telefone(2L, "21", "88888-8888"))));
        agenda.adicionarContato(new Contato(3L, "José", "Almeida", Arrays.asList(new Telefone(3L, "31", "77777-7777"))));
    }

    public void iniciar() {
        boolean executando = true;
        while (executando) {
            System.out.println("\n##### AGENDA #####");
            System.out.println("1 - Adicionar Contato");
            System.out.println("2 - Remover Contato");
            System.out.println("3 - Editar Contato");
            System.out.println("4 - Listar Contatos");
            System.out.println("5 - Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer do scanner

            switch (opcao) {
                case 1:
                    adicionarContato();
                    break;
                case 2:
                    removerContato();
                    break;
                case 3:
                    editarContato();
                    break;
                case 4:
                    listarContatos();
                    break;
                case 5:
                    executando = false;
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
        scanner.close();
    }

    private void adicionarContato() {
        System.out.println("Adicionando novo contato:");
        System.out.print("ID: ");
        Long id = scanner.nextLong();
        scanner.nextLine(); // Limpa o buffer do scanner
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Sobrenome: ");
        String sobrenome = scanner.nextLine();
        System.out.print("DDD: ");
        String ddd = scanner.nextLine();
        System.out.print("Número: ");
        String numero = scanner.nextLine();

        Contato contato = new Contato(id, nome, sobrenome, Arrays.asList(new Telefone(id, ddd, numero)));
        try {
            agenda.adicionarContato(contato);
            System.out.println("Contato adicionado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao adicionar contato: " + e.getMessage());
        }
    }

    private void removerContato() {
        System.out.print("Digite o ID do contato a ser removido: ");
        Long id = scanner.nextLong();
        scanner.nextLine(); // Limpa o buffer do scanner
        try {
            agenda.removerContato(id);
            System.out.println("Contato removido com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao remover contato: " + e.getMessage());
        }
    }

    private void editarContato() {
        // Lógica para editar um contato
        // Similar ao método adicionarContato, mas buscando o contato pelo ID primeiro
    }

    private void listarContatos() {
        List<Contato> contatos = agenda.listarContatos();
        for (Contato contato : contatos) {
            System.out.println("ID: " + contato.getId() + ", Nome: " + contato.getNome() + ", Sobrenome: " + contato.getSobreNome());
            // Listar telefones do contato
        }
    }

    public static void main(String[] args) {
        new AgendaApp().iniciar();
    }

}
