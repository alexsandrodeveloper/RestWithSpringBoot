package br.com.alex.conversores;

public class Conversor {

	@SuppressWarnings("unused")
	public static Double convertDouble(String number) {
		if (number == null)
			return 0D;

		number = number.replace(",", ".");

		if (isNumeric(number))
			return Double.parseDouble(number);

		return 0D;
	}

	public static boolean isNumeric(String number) {
		return number.matches("[-+]?[0-9]*\\.?[0-9]+");
	}
}
