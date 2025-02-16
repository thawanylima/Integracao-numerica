import java.util.Scanner;

public class RegraSimpson {

    // Parâmetros da função (embutidos no programa)
    //Matricula escolhida - SP3128181
    private static final double A = 5.0 / 8.0; // Coeficiente de x
    private static final double B = 5.0 / 4.0; // Coeficiente de cos
    private static final double C = 2.0 / 3.0; // Parâmetro da função cos(x + C)

    // Função a ser integrada f(x) = (5/A) * x + (B/4) * cos(x + C/3)
    public static double f(double x) {
        return A * x + B * Math.cos(x + C);
    }

    // Método para calcular a integral exata 
    public static double integralExata(double a, double b) {
        // Integral de f(x) = (5/8) * x + (5/4) * cos(x + 2/3)
        // Integral de (5/8) * x = (5/16) * x^2
        // Integral de (5/4) * cos(x + 2/3) = (5/4) * sin(x + 2/3)
        double integralX = (5.0 / 16.0) * (b * b - a * a); // Integral do termo linear
        double integralCos = (5.0 / 4.0) * (Math.sin(b + C) - Math.sin(a + C)); // Integral do termo cosseno
        return integralX + integralCos; // Soma das integrais
    }

    // Método para calcular a integral usando a Regra de 1/3 de Simpson
    public static double regraSimpson(double a, double b, int n) {
        // Verificando se n é par
        if (n % 2 != 0) {
            System.out.println("O número de subintervalos n deve ser par.");
            return -1;  // Indicando erro
        }

        // Calculando o tamanho do subintervalo
        double h = (b - a) / n;

        // Inicializando a soma
        double sum = f(a) + f(b);

        // Calculando a soma dos termos de acordo com a regra de Simpson
        for (int i = 1; i < n; i++) {
            double x = a + i * h;
            if (i % 2 == 0) {
                sum += 2 * f(x);  // termos de índice par
            } else {
                sum += 4 * f(x);  // termos de índice ímpar
            }
        }

        // Multiplicando pela constante (h / 3) para obter o resultado final
        sum *= h / 3;

        return sum;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Definindo os limites de integração
        double a = 0.4;  // Limite inferior
        double b = 0.54; // Limite superior

        // 2. Receber o número de intervalos do usuário
        System.out.print("Digite o número de subintervalos (n, deve ser par): ");
        int n = sc.nextInt();

         // 1. Cálculo do valor exato da integral
        double valorExato = integralExata(a, b);
        System.out.printf("Valor exato da integral: %.12f%n", valorExato);

        // 3. Calcular a integral usando a Regra de Simpson com o número de intervalos informado
        double resultadoSimpson = regraSimpson(a, b, n);
        if (resultadoSimpson != -1) {
            System.out.printf("Valor pela Regra de Simpson com %d intervalos: %.12f%n", n, resultadoSimpson);
        }

        sc.close();

        // 4. Incluir os valores das integrais como comentários no final do código
        /*
        Valor exato da integral: 0,114673568404
        Valor pela Regra de Simpson com 8 intervalos: 0,114673568442
        Valor pela Regra de Simpson com 16 intervalos: 0,114673568406
        */
    }
}