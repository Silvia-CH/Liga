
import java.util.Scanner;

public class Utilidades {

//	metodo que valida si hay algo distinto de un numero en una cadena
	public static boolean validarNumero(String numero) {
		int contador = 0;
		boolean val = true;
		int longitud = numero.length();
		if (longitud > 0) {
			do {
				if ((int) numero.charAt(contador) < 48 || (int) numero.charAt(contador) > 57) {
					val = false;
				} else {
					contador++;
				}
			} while (val && contador < longitud);
		} else {
			val = false;
		}
		return val;
	}

	// Metodo que se usa cada vez que se pida al usuario un valor de tipo Int
	// Se usa try catch en un bucle para pedir el dato hasta que se introduzca un
	// valor valido
	public static int validarInt() {

		boolean correcto = true;
		int numero = 0;
		Scanner sc = new Scanner(System.in);

		do {
			correcto = true;
			try {
				System.out.println("introduce un numero");
				numero = sc.nextInt();
			} catch (Exception e) {
				System.err.println("El tipo de dato introducido es incorrecto");
				correcto = false;
			} finally {
				sc.nextLine();
			}
		} while (!correcto);

		return numero;
	}

	// Metodo que evalua que un String introducido no contenga solo espacios en
	// blanco
	// se llama a este metodo cada que el usuario ingrese un valor de tipo String
	public static String validarString() {
		boolean correcto;
		String cadena;
		int longitud;
		Scanner sc = new Scanner(System.in);
		do {
			cadena = sc.nextLine().trim();
			longitud = cadena.length();
			if (longitud == 0) {
				System.err.println("No puedes ingresar espacios en blanco, vuelve a introducirlo");
			}
		} while (cadena.length() == 0);
		return cadena;
	}

	// SCANNERS
	public static char preguntarChar(String texto) {
		String letra;

		Scanner lector = new Scanner(System.in);

		do {
			System.out.println(texto);
			letra = lector.nextLine();
		} while (letra.isBlank() || contieneNumeros(letra));//

		return letra.toUpperCase().toCharArray()[0];
	}

	public static String preguntarString(String texto) {
		String palabra;

		Scanner lector = new Scanner(System.in);
		do {
			System.out.println(texto);
			palabra = lector.nextLine();
			palabra = palabra.toUpperCase();
		} while (palabra.isBlank() || contieneNumeros(palabra));//

		return palabra;
	}

	public static int preguntarInt(String texto) {
		int numero;

		Scanner lector = new Scanner(System.in);

		do {
			System.out.println(texto);
			numero = lector.nextInt();
			lector.nextLine();
		} while (texto.isBlank());

		return numero;
	}

	public static boolean contieneNumeros(String cadena) {
		boolean contiene = false;
		char letra;
		String validacion = "0123456789";
		int longitud = cadena.length();

		for (int i = 0; i < longitud; i++) {
			letra = cadena.charAt(i);
			if (validacion.indexOf(letra) != -1) {
				contiene = true;
			}
		}
		return contiene;
	}
}
