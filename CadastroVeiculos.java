import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CadastroVeiculos {
    private List<Veiculo> veiculos;
    private Scanner scanner;

    public CadastroVeiculos() {
        veiculos = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void iniciar() {
        int opcao;
        do {
            limparTela();
            opcao = mostrarMenu();
            switch (opcao) {
                case 1:
                    cadastrarVeiculo();
                    break;
                case 2:
                    listarVeiculos();
                    break;
                case 3:
                    excluirVeiculo();
                    break;
                case 4:
                    pesquisarVeiculo();
                    break;
                case 0:
                    System.out.println("Encerrando o sistema...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private int mostrarMenu() {
        System.out.println("""
            ===== MENU =====
            1 - Cadastrar Veículo
            2 - Listar Veículos
            3 - Excluir Veículo
            4 - Pesquisar Veículo
            0 - Sair""");
        System.out.print("Opção: ");

        while (!scanner.hasNextInt()) {
            System.out.print("Digite um número válido: ");
            scanner.next();
        }
        int opcao = scanner.nextInt();
        scanner.nextLine();
        return opcao;
    }

    private void cadastrarVeiculo() {
        System.out.print("Marca: ");
        String marca = scanner.nextLine();

        System.out.print("Modelo: ");
        String modelo = scanner.nextLine();

        int ano;
        while (true) {
            System.out.print("Ano: ");
            String input = scanner.nextLine();
            try {
                ano = Integer.parseInt(input);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Digite um número válido para o ano!");
            }
        }

        System.out.print("Placa: ");
        String placa = scanner.nextLine();

        if (existePlaca(placa)) {
            System.out.println("Erro: Já existe um veículo com esta placa!");
        } else {
            veiculos.add(new Veiculo(marca, modelo, ano, placa));
            System.out.println("Veículo cadastrado com sucesso!");
        }
        pausa();
    }

    private void listarVeiculos() {
        if (veiculos.isEmpty()) {
            System.out.println("Nenhum veículo cadastrado.");
        } else {
            System.out.println("\n--- LISTA DE VEÍCULOS ---");
            veiculos.forEach(System.out::println);
        }
        pausa();
    }

    private void excluirVeiculo() {
        if (veiculos.isEmpty()) {
            System.out.println("Nenhum veículo cadastrado.");
            pausa();
            return;
        }

        listarVeiculos();
        System.out.print("\nInforme a placa do veículo a excluir: ");
        String placa = scanner.nextLine();

        boolean removido = veiculos.removeIf(v -> v.getPlaca().equalsIgnoreCase(placa));
        if (removido) System.out.println("Veículo removido com sucesso!");
        else System.out.println("Veículo não encontrado!");

        pausa();
    }

    private void pesquisarVeiculo() {
        if (veiculos.isEmpty()) {
            System.out.println("Nenhum veículo cadastrado.");
            pausa();
            return;
        }

        System.out.println("Pesquisar por: \n1 - Placa\n2 - Modelo");
        System.out.print("Opção: ");
        int tipoPesquisa = scanner.hasNextInt() ? scanner.nextInt() : -1;
        scanner.nextLine();

        List<Veiculo> resultados = new ArrayList<>();
        switch (tipoPesquisa) {
            case 1 -> {
                System.out.print("Digite a placa: ");
                String placa = scanner.nextLine();
                veiculos.stream()
                        .filter(v -> v.getPlaca().equalsIgnoreCase(placa))
                        .forEach(resultados::add);
            }
            case 2 -> {
                System.out.print("Digite parte do modelo: ");
                String modelo = scanner.nextLine();
                veiculos.stream()
                        .filter(v -> v.getModelo().toLowerCase().contains(modelo.toLowerCase()))
                        .forEach(resultados::add);
            }
            default -> System.out.println("Opção inválida!");
        }

        if (resultados.isEmpty()) System.out.println("Nenhum veículo encontrado!");
        else resultados.forEach(System.out::println);

        pausa();
    }

    private boolean existePlaca(String placa) {
        return veiculos.stream().anyMatch(v -> v.getPlaca().equalsIgnoreCase(placa));
    }

    private void limparTela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private void pausa() {
        System.out.println("\nPressione ENTER para continuar");
        scanner.nextLine();
    }

    public static void main(String[] args) {
        new CadastroVeiculos().iniciar();
    }
}
