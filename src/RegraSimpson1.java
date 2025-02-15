import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class RegraSimpson1 {

    // Constantes da função
    private static final BigDecimal A = new BigDecimal("5.0").divide(new BigDecimal("8.0"), 20, RoundingMode.HALF_UP); // 5/8
    private static final BigDecimal B = new BigDecimal("5.0").divide(new BigDecimal("4.0"), 20, RoundingMode.HALF_UP); // 5/4
    private static final BigDecimal C = new BigDecimal("2.0").divide(new BigDecimal("3.0"), 20, RoundingMode.HALF_UP); // 2/3

    // Função a ser integrada: f(x) = (5/8) * x + (5/4) * cos(x + 2/3)
    public static BigDecimal f(BigDecimal x) {
        BigDecimal termo1 = A.multiply(x); // (5/8) * x
        BigDecimal cosArg = x.add(C); // x + 2/3
        double cosArgDouble = cosArg.doubleValue(); // Converte para double para usar Math.cos
        BigDecimal termo2 = B.multiply(new BigDecimal(Math.cos(cosArgDouble))); // (5/4) * cos(x + 2/3)
        return termo1.add(termo2); // Soma os dois termos
    }

    // Método para calcular a integral usando a Regra de 1/3 de Simpson
    public static BigDecimal simpson(BigDecimal a, BigDecimal b, int n) {
        if (n % 2 != 0) {
            System.out.println("O número de subintervalos n deve ser par.");
            return new BigDecimal("-1"); // Indicando erro
        }

        BigDecimal h = b.subtract(a).divide(new BigDecimal(n), 20, RoundingMode.HALF_UP); // Tamanho do subintervalo
        BigDecimal sum = f(a).add(f(b)); // Soma inicial: f(a) + f(b)

        // Soma dos termos de acordo com a regra de Simpson
        for (int i = 1; i < n; i++) {
            BigDecimal x = a.add(h.multiply(new BigDecimal(i)));
            if (i % 2 == 0) {
                sum = sum.add(f(x).multiply(new BigDecimal("2"))); // Termos de índice par
            } else {
                sum = sum.add(f(x).multiply(new BigDecimal("4"))); // Termos de índice ímpar
            }
        }

        // Multiplica pela constante (h / 3) para obter o resultado final
        return sum.multiply(h).divide(new BigDecimal("3"), 20, RoundingMode.HALF_UP);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Definindo os limites de integração (fixos)
        BigDecimal a = new BigDecimal("0.4"); // Limite inferior
        BigDecimal b = new BigDecimal("0.54"); // Limite superior

        // Recebendo o número de intervalos do usuário
        System.out.print("Digite o número de subintervalos (n, deve ser par): ");
        int n = sc.nextInt();

        // Chamando o método de integração
        BigDecimal result = simpson(a, b, n);

        // Exibindo o resultado
        if (result.compareTo(new BigDecimal("-1")) != 0) {
            System.out.println("Resultado da integral: " + result);
        }

        sc.close();
    }
}