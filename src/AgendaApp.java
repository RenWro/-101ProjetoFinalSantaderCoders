package src;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import src.models.Contato;
import src.models.Telefone;

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
        System.out.println("Adicionando novo contato:");
        Long id = null;
        while (id == null) {
            System.out.print("ID (número longo): ");
            if (scanner.hasNextLong()) {
                id = scanner.nextLong();
                scanner.nextLine(); // Limpar buffer
            } else {
                System.out.println("Por favor, insira um número válido para o ID.");
                scanner.nextLine(); // Limpar buffer
            }
        }

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
        System.out.println("Contato adicionado com sucesso!");
    }

    private void removerContato() {
        System.out.print("Digite o ID do contato a ser removido: ");
        Long id = scanner.nextLong();
        scanner.nextLine();
        agenda.removerContato(id);
        fileManager.salvarContatos(agenda.listarContatos());
        System.out.println("Contato removido com sucesso!");
    }

    private void editarContato() {
        System.out.print("Digite o ID do contato a ser editado: ");
        Long id = scanner.nextLong();
        scanner.nextLine();
        System.out.print("Novo Nome: ");
        String novoNome = scanner.nextLine();
        System.out.print("Novo Sobrenome: ");
        String novoSobreNome = scanner.nextLine();
        List<Telefone> novosTelefones = new ArrayList<>();
        Contato novoContato = new Contato(id, novoNome, novoSobreNome, novosTelefones);
        agenda.editarContato(id, novoContato);
        fileManager.salvarContatos(agenda.listarContatos());
        System.out.println("Contato editado com sucesso!");
    }

    private void listarContatos() {
        List<Contato> contatos = agenda.listarContatos();
        if (contatos.isEmpty()) {
            System.out.println("Não há contatos na agenda.");
        } else {
            for (Contato contato : contatos) {
                System.out.println("ID: " + contato.getId() + ", Nome: " + contato.getNome() + " " + contato.getSobreNome());
                for (Telefone telefone : contato.getTelefones()) {
                    System.out.println("\tTelefone: " + telefone.getDdd() + " " + telefone.getNumero());
                }
            }
        }
    }

    private void buscarContatoPorNome() {
        System.out.print("Digite o nome do contato a ser buscado: ");
        String nome = scanner.nextLine();
        List<Contato> contatosEncontrados = agenda.buscarContatoPorNome(nome);
        if (contatosEncontrados.isEmpty()) {
            System.out.println("Nenhum contato encontrado com esse nome.");
        } else {
            for (Contato contato : contatosEncontrados) {
                System.out.println("ID: " + contato.getId() + ", Nome: " + contato.getNome() + " " + contato.getSobreNome());
                for (Telefone telefone : contato.getTelefones()) {
                    System.out.println("\tTelefone: " + telefone.getDdd() + " " + telefone.getNumero());
                }
            }
        }
    }

    public static void main(String[] args) {
        new AgendaApp().iniciar();
    }
}
