package service;

import model.Funcionario;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class FuncionarioService {
    private List<Funcionario> funcionarios;

    public FuncionarioService(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

    public void removerPorNome(String nome) {
        funcionarios.removeIf(func -> func.getNome().equals(nome));
    }

    public void aplicarAumento(BigDecimal percentual) {
        funcionarios.forEach(func -> {
            BigDecimal novoSalario = func.getSalario()
                .multiply(BigDecimal.ONE.add(percentual))
                .setScale(2, RoundingMode.HALF_UP);
            func.setSalario(novoSalario);
        });
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }
}