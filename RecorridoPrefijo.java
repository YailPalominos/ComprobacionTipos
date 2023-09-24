import java.util.Stack;

public class RecorridoPrefijo {

    public static String realizarPrefijo(String operacion) {
        Stack<Character> operators = new Stack<>();
        Stack<String> operands = new Stack<>();

        String[] tokens = operacion.split("\\s+");
        StringBuilder prefixExpression = new StringBuilder();

        for (int i = 0; i < tokens.length; i++) {
            String token = tokens[i];

            if (esOperador(token)) {
                char operator = token.charAt(0);
                while (!operators.isEmpty() && getJerarquia(operator) <= getJerarquia(operators.peek())) {
                    String operand1 = operands.pop();
                    String operand2 = operands.pop();
                    char currentOperator = operators.pop();
                    String newOperand = currentOperator + " " + operand2 + " " + operand1;
                    operands.push(newOperand);
                }
                operators.push(operator);
            } else {
                operands.push(token);
            }
        }

        while (!operators.isEmpty()) {
            String operand1 = operands.pop();
            String operand2 = operands.pop();
            char operator = operators.pop();
            String newOperand = operator + " " + operand2 + " " + operand1;
            operands.push(newOperand);
        }

        if (!operands.isEmpty()) {
            prefixExpression.append(operands.pop());
        }

        return prefixExpression.toString();
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
