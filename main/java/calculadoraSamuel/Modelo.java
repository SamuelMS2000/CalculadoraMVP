package calculadoraSamuel;

/**
 *
 * @author SamuelMS
 */
public class Modelo{
public static int digitosMax = 15; //variable para poner un limite de digitos

        //con los 2 numeros y el operador realizo el calculo
     static double calcular(char op, double num1, double Num2)
            throws ArithmeticException {
        double resultado = 0;

        switch (op) {
            case '+':
                resultado = num1 + Num2;
                break;
            case '-':
                resultado = num1 - Num2;
                break;
            case '*':
                resultado = num1 * Num2;
                break;
            case '/':
                if (Num2 == 0) { //si intenta dividir entre 0 sale error
                    throw new ArithmeticException("Err");
                } else resultado = num1 / Num2;
                break;
        }

        return (resultado);
    }
}

    