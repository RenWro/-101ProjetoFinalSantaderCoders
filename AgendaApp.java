import java.util.Arrays;
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
        inicializarContatos();
    }

    private void inicializarContatos() {
        // Adicionando contatos iniciais
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
            System.out.println("5 - Buscar Contato por Nome");
            System.out.println("6 - Sair");
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
                    buscarContatoPorNome();
                    break;
                case 6:
                    executando = false;
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
        scanner.close();
    }

    private void adicionarContato() {
        // Implementação para adicionar um contato
    }

    private void removerContato() {
        // Implementação para remover um contato
    }

    private void editarContato() {
        // Implementação para editar um contato
    }

    private void listarContatos() {
        List<Contato> contatos = agenda.listarContatos();
        if (contatos.isEmpty()) {
            System.out.println("Não há contatos na agenda.");
        } else {
            System.out.println("Lista de Contatos:");
            for (Contato contato : contatos) {
                System.out.println("\nID: " + contato.getId() + ", Nome: " + contato.getNome() + " " + contato.getSobreNome());
                List<Telefone> telefones = contato.getTelefones();
                if (telefones != null && !telefones.isEmpty()) {
                    System.out.println("Telefones:");
                    for (Telefone telefone : telefones) {
                        System.out.println("\tID: " + telefone.getId() + ", DDD: " + telefone.getDdd() + ", Número: " + telefone.getNumero());
                    }
                } else {
                    System.out.println("Sem telefones cadastrados.");
                }
            }
        }
    }

    private void buscarContatoPorNome() {
        // Implementação para buscar contatos por nome
    }

    public static void main(String[] args) {
        new AgendaApp().iniciar();
    }

}
