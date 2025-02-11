package service;

import model.Funcionario;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RelatorioService {
    public Map<String, List<Funcionario>> agruparPorFuncao(List<Funcionario> funcionarios) {
        return funcionarios.stream()
            .collect(Collectors.groupingBy(Funcionario::getFuncao));
    }

    public List<Funcionario> filtrarAniversariantes(List<Funcionario> funcionarios, List<Integer> meses) {
        return funcionarios.stream()
            .filter(func -> meses.contains(func.getDataNascimento().getMonthValue()))
            .collect(Collectors.toList());
    }

    public Funcionario encontrarMaisVelho(List<Funcionario> funcionarios) {
        return funcionarios.stream()
            .min((f1, f2) -> f1.getDataNascimento().compareTo(f2.getDataNascimento()))
            .orElse(null);
    }

    public BigDecimal calcularTotalSalarios(List<Funcionario> funcionarios) {
        return funcionarios.stream()
            .map(Funcionario::getSalario)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
    
    public void imprimirSalariosMinimos(List<Funcionario> funcionarios) {
        BigDecimal salarioMinimo = new BigDecimal("1212.00");
        for (Funcionario func : funcionarios) {
            BigDecimal salarioFunc = func.getSalario();
            BigDecimal qtdSalariosMinimos = dividirComArredondamento(salarioFunc, salarioMinimo);
            System.out.printf("Funcionario: %-10s | Salarios Minimos: %.2f%n", func.getNome(), qtdSalariosMinimos.doubleValue());
        }
    }

    private BigDecimal dividirComArredondamento(BigDecimal numerador, BigDecimal denominador) {
        return numerador.divide(denominador, 2, RoundingMode.HALF_UP);
    }
}