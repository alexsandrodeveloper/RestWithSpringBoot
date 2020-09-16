package br.com.alex.calculadora;

public class Calculadora {

	public static Double calcular(Double numberOne, Double numberTwo, String idOperacao) {

		switch (idOperacao) {
		case "1":
			return numberOne + numberTwo;

		case "2":
			return numberOne - numberTwo;

		case "3":
			return numberOne * numberTwo;

		case "4":
			return numberOne / numberTwo;

		case "5":
			return (numberOne + numberTwo) / 2;

		case "6":
			return Math.sqrt(numberOne);

		default:
			return 0D;
		}
	}

}
