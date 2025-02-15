import java.util.Scanner;

public class IntegracaoLinear{
    
    // Função a ser integrada f(x) = (5/8) * x + (5/4) * cos(x + 2/3)
    private static final double A = 5.0 / 8.0; // Coeficiente de x
    private static final double B = 5.0 / 4.0; // Coeficiente de cos
    private static final double C = 2.0 / 3.0; // Parâmetro da função cos(x + C)

    // Função a ser integrada
    public static double f(double x) {
        return A * x + B * Math.cos(x + C); // f(x) = (5/8) * x + (5/4) * cos(x + 2/3)
    }

    // Método para calcular a integral usando a Regra de 1/3 de Simpson
    public static double simpson(double a, double b, int n) {
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

        // Definindo os limites de integração (fixos)
        double a = 0.4;  // Limite inferior
        double b = 0.54; // Limite superior

        // Recebendo o número de intervalos do usuário
        System.out.print("Digite o número de subintervalos (n, deve ser par): ");
        int n = sc.nextInt();

        // Chamando o método de integração
        double result = simpson(a, b, n);

        // Exibindo o resultado
        if (result != -1) {
            System.out.println("Resultado da integral: " + result);
        }

        sc.close();
    }
}

//valor Simbólico  0.114674
//valor com 8 intervalos 0.11467356844236282
//0.1146735684064341  wolframalpha
//consdierando erro: 0.11467356844475782

//valor com 16 intervalos  0.11467356840643411
//0.1146735684041886 wolframalpha
//considerando o erro: 0.114674000000149533
