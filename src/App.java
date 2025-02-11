import model.Funcionario;
import service.FuncionarioService;
import service.RelatorioService;
import util.FormatadorDados;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) {
        // Inicialização
        List<Funcionario> funcionarios = carregarDadosIniciais();
        FuncionarioService funcionarioService = new FuncionarioService(funcionarios);
        RelatorioService relatorioService = new RelatorioService();

        // Operações
        funcionarioService.removerPorNome("João");
        funcionarioService.aplicarAumento(new BigDecimal("0.10"));

        // Relatórios
        imprimirRelatorioGeral(funcionarioService.getFuncionarios());
        imprimirAgrupamentoPorFuncao(relatorioService, funcionarioService.getFuncionarios());
        imprimirAniversariantes(relatorioService, funcionarioService.getFuncionarios());
        imprimirMaisVelho(relatorioService, funcionarioService.getFuncionarios());
        imprimirTotalSalarios(relatorioService, funcionarioService.getFuncionarios());
        imprimirSalariosMinimos(relatorioService, funcionarioService.getFuncionarios());
    }

    private static List<Funcionario> carregarDadosIniciais() {
        List<Funcionario> funcionarios = new ArrayList<>();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        funcionarios.add(new Funcionario("Maria", LocalDate.parse("18/10/2000", dateFormatter), new BigDecimal("2009.44"), "Operador"));
        funcionarios.add(new Funcionario("João", LocalDate.parse("12/05/1990", dateFormatter), new BigDecimal("2284.38"), "Operador"));
        funcionarios.add(new Funcionario("Caio", LocalDate.parse("02/05/1961", dateFormatter), new BigDecimal("9836.14"), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.parse("14/10/1988", dateFormatter), new BigDecimal("19119.88"), "Diretor"));
        funcionarios.add(new Funcionario("Alice", LocalDate.parse("05/01/1995", dateFormatter), new BigDecimal("2234.68"), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.parse("19/11/1999", dateFormatter), new BigDecimal("1582.72"), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.parse("31/03/1993", dateFormatter), new BigDecimal("4071.84"), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.parse("08/07/1994", dateFormatter), new BigDecimal("3017.45"), "Gerente"));
        funcionarios.add(new Funcionario("Heloísa", LocalDate.parse("24/05/2003", dateFormatter), new BigDecimal("1606.85"), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.parse("02/09/1996", dateFormatter), new BigDecimal("2799.93"), "Gerente"));

        return funcionarios;
    }

    private static void imprimirRelatorioGeral(List<Funcionario> funcionarios) {
        System.out.println("Funcionários após remoção de João:");
        for (Funcionario func : funcionarios) {
            String dataNasc = FormatadorDados.formatarData(func.getDataNascimento());
            String salario = FormatadorDados.formatarSalario(func.getSalario());
            System.out.printf("Nome: %-10s | Data Nasc.: %s | Salário: %12s | Função: %s%n",
                    func.getNome(), dataNasc, salario, func.getFuncao());
        }
    }

    private static void imprimirAgrupamentoPorFuncao(RelatorioService relatorioService, List<Funcionario> funcionarios) {
        System.out.println("\nFuncionários agrupados por função:");
        Map<String, List<Funcionario>> porFuncao = relatorioService.agruparPorFuncao(funcionarios);
        porFuncao.forEach((funcao, lista) -> {
            System.out.println("Função: " + funcao);
            lista.forEach(func -> System.out.println("  " + func.getNome()));
        });
    }

    private static void imprimirAniversariantes(RelatorioService relatorioService, List<Funcionario> funcionarios) {
        System.out.println("\nAniversariantes em outubro e dezembro:");
        List<Funcionario> aniversariantes = relatorioService.filtrarAniversariantes(funcionarios, List.of(10, 12));
        aniversariantes.forEach(func -> System.out.println(func.getNome()));
    }

    private static void imprimirMaisVelho(RelatorioService relatorioService, List<Funcionario> funcionarios) {
        Funcionario maisVelho = relatorioService.encontrarMaisVelho(funcionarios);
        System.out.println("\nFuncionário mais velho:");
        System.out.println("Nome: " + maisVelho.getNome() + ", Idade: " + (LocalDate.now().getYear() - maisVelho.getDataNascimento().getYear()));
    }

    private static void imprimirTotalSalarios(RelatorioService relatorioService, List<Funcionario> funcionarios) {
        BigDecimal totalSalarios = relatorioService.calcularTotalSalarios(funcionarios);
        System.out.println("\nTotal dos salários: " + FormatadorDados.formatarSalario(totalSalarios));
    }

    private static void imprimirSalariosMinimos(RelatorioService relatorioService, List<Funcionario> funcionarios) {
        BigDecimal salarioMinimo = new BigDecimal("1212.00"); // Example value for minimum wage
        System.out.println("\nQuantos salários mínimos cada um ganha:");
        for (Funcionario func : funcionarios) {
            BigDecimal salariosMinimos = func.getSalario().divide(salarioMinimo, 2, RoundingMode.HALF_UP);
            System.out.printf("Nome: %-10s | Salários Mínimos: %6.2f%n", func.getNome(), salariosMinimos);
        }
    }
}