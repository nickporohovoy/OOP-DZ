import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

// Интерфейс для операций с комплексными числами
interface ComplexOperation {
    ComplexNumber execute(ComplexNumber a, ComplexNumber b);
}

// Сложение комплексных чисел
class AdditionOperation implements ComplexOperation {
    @Override
    public ComplexNumber execute(ComplexNumber a, ComplexNumber b) {
        return new ComplexNumber(a.getReal() + b.getReal(), a.getImaginary() + b.getImaginary());
    }
}

// Умножение комплексных чисел
class MultiplicationOperation implements ComplexOperation {
    @Override
    public ComplexNumber execute(ComplexNumber a, ComplexNumber b) {
        double real = a.getReal() * b.getReal() - a.getImaginary() * b.getImaginary();
        double imaginary = a.getReal() * b.getImaginary() + a.getImaginary() * b.getReal();
        return new ComplexNumber(real, imaginary);
    }
}

// Деление комплексных чисел
class DivisionOperation implements ComplexOperation {
    @Override
    public ComplexNumber execute(ComplexNumber a, ComplexNumber b) {
        double denominator = b.getReal() * b.getReal() + b.getImaginary() * b.getImaginary();
        double real = (a.getReal() * b.getReal() + a.getImaginary() * b.getImaginary()) / denominator;
        double imaginary = (a.getImaginary() * b.getReal() - a.getReal() * b.getImaginary()) / denominator;
        return new ComplexNumber(real, imaginary);
    }
}

// Фабрика комплексных чисел
class ComplexNumberFactory {
    public static ComplexNumber create(double real, double imaginary) {
        return new ComplexNumber(real, imaginary);
    }
}

// Класс комплексного числа
class ComplexNumber {
    private double real;
    private double imaginary;

    public ComplexNumber(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    public double getReal() {
        return real;
    }

    public double getImaginary() {
        return imaginary;
    }

    @Override
    public String toString() {
        return real + " + " + imaginary + "i";
    }
}

// Класс калькулятора комплексных чисел
class ComplexCalculator {
    private ComplexOperation operation;

    public ComplexCalculator(ComplexOperation operation) {
        this.operation = operation;
    }

    public ComplexNumber calculate(ComplexNumber a, ComplexNumber b) {
        return operation.execute(a, b);
    }
}

// Пример использования
public class Main {
    public static void main(String[] args) {
        // Настройка логирования
        Logger logger = Logger.getLogger(Main.class.getName());
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setFormatter(new SimpleFormatter());
        logger.addHandler(consoleHandler);
        logger.setLevel(Level.INFO);

        // Создание комплексных чисел
        ComplexNumber num1 = ComplexNumberFactory.create(2, 3);
        ComplexNumber num2 = ComplexNumberFactory.create(1, -2);

        // Пример сложения
        ComplexCalculator additionCalculator = new ComplexCalculator(new AdditionOperation());
        ComplexNumber sum = additionCalculator.calculate(num1, num2);
        logger.info("Сложение: " + num1 + " + " + num2 + " = " + sum);

        // Пример умножения
        ComplexCalculator multiplicationCalculator = new ComplexCalculator(new MultiplicationOperation());
        ComplexNumber product = multiplicationCalculator.calculate(num1, num2);
        logger.info("Умножение: " + num1 + " * " + num2 + " = " + product);

        // Пример деления
        ComplexCalculator divisionCalculator = new ComplexCalculator(new DivisionOperation());
        ComplexNumber quotient = divisionCalculator.calculate(num1, num2);
        logger.info("Деление: " + num1 + " / " + num2 + " = " + quotient);
    }
}
