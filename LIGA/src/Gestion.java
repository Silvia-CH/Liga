
public class Gestion {

	public static void main(String[] args) {
		System.out.println("LA LIGA");
		System.out.println("Empezamos con el CRUD de equipos:");
		System.out.println("Se introducen dos equipos como mínimo (sin números en el nombre).");
		Liga liga = new Liga("LIGA ESPAÑOLA");
		liga.elegirAccion();

	}

}
