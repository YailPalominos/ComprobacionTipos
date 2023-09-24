import java.util.Stack;

public class RecorridoPrefijo {

    public static String realizarPrefijo(String operacion) {
        Stack<Character> operadores = new Stack<>();
        Stack<String> operandos = new Stack<>();

        String[] tokens = operacion.split("\\s+");
        StringBuilder exprecionPrefija = new StringBuilder();

        for (int i = 0; i < tokens.length; i++) {
            String token = tokens[i];

            if (esOperador(token)) {
                char operator = token.charAt(0);
                while (!operadores.isEmpty() && getJerarquia(operator) <= getJerarquia(operadores.peek())) {
                    String operando1 = operandos.pop();
                    String operando2 = operandos.pop();
                    char currentOperator = operadores.pop();
                    String newOperand = currentOperator + " " + operando2 + " " + operando1;
                    operandos.push(newOperand);
                }
                operadores.push(operator);
            } else {
                operandos.push(token);
            }
        }

        while (!operadores.isEmpty()) {
            String operando1 = operandos.pop();
            String operando2 = operandos.pop();
            char operator = operadores.pop();
            String newOperand = operator + " " + operando2 + " " + operando1;
            operandos.push(newOperand);
        }

        if (!operandos.isEmpty()) {
            exprecionPrefija.append(operandos.pop());
        }

        return exprecionPrefija.toString();
    }

    // Verifica si es operador el token actual.
    private static boolean esOperador(String token) {
        return token.matches("[+\\-*/&|!<>]=*");
    }

    // Obtinen la gerarquia del operador.
    private static int getJerarquia(char operador) {
        if (operador == '+' || operador == '-') {
            return 1;
        } else if (operador == '*' || operador == '/') {
            return 2;
        } else if (operador == '&' || operador == '|') {
            return 3;
        } else if (operador == '!') {
            return 4;
        }
        return 0;
    }
}
