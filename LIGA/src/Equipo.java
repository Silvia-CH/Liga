import java.util.ArrayList;
import java.util.Scanner;

public class Equipo {

	private String nombre;
	private ArrayList<Jugador> plantilla = new ArrayList<Jugador>();
	private int puntaje = 0;
	private String liga;
	private ArrayList<String> enfrentamientos = new ArrayList<String>();

	public Equipo() {
		super();
	}

	public Equipo(String nombre) {
		super();
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public ArrayList<Jugador> getPlantilla() {
		return plantilla;
	}

	public void setPlantilla(ArrayList<Jugador> plantilla) {
		this.plantilla = plantilla;
	}

	public int getPuntaje() {
		return puntaje;
	}

	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}

	public String getLiga() {
		return liga;
	}

	public void setLiga(String liga) {
		this.liga = liga;
	}

	public ArrayList<String> getEnfrentamiento() {
		return enfrentamientos;
	}

	public void setEnfrentamiento(ArrayList<String> enfrentamiento) {
		this.enfrentamientos = enfrentamiento;
	}

	@Override
	public String toString() {
		return "Equipo [nombre=" + nombre + ", plantilla=" + plantilla + ", puntaje=" + puntaje + ", liga=" + liga
				+ ", enfrentamiento=" + enfrentamientos + "]";
	}

//	muestra todos los jugadores dentro del equipo.
	public void readJug() {
		int longitud = plantilla.size();
		if (longitud <= 0) {
			System.out.println("Aun no se han introducido jugadores en la plantilla\n");
		} else {
			for (int i = 0; i < longitud; i++) {
				System.out.println((i + 1) + ". " + plantilla.get(i).toString());
			}
		}
	}

// añade id del enfrentamiento a las lista de ids del equipo, para saber en que enfrentamientos participa	
	public void addIdEnfrentamiento(String id) {
		enfrentamientos.add(id);
	}

	public void addJugador(Jugador item) {
		plantilla.add(item);
		item.setEquipo(nombre);
	}

	// Metodo para añadir jugadores
	public void createJug() {

		String nombreAux;
		String claseAux;
		int cantidad;
		Scanner lector = new Scanner(System.in);

		System.out.println("¿Cuántos jugadores quieres añadir?");
		cantidad = Utilidades.validarInt();

		for (int i = 1; i <= cantidad; i++) {
			System.out.println("Introduce el nombre del jugador " + i);
			nombreAux = Utilidades.validarString();
			System.out.println("Introduce la clase del jugador " + i);
			claseAux = Utilidades.validarString();
			addJugador(new Jugador(nombreAux, claseAux));
		}
	}

	// metodo para localizar jugadores, se usa al buscar un jugador para editarlo o
	// eliminarlo
	public int localizarJugador() {
		int indice;
		Scanner sc = new Scanner(System.in);

		System.out.println("Introduce el indice del jugador");
		readJug();
		indice = Utilidades.validarInt();

		return indice;
	}

	// Metodo para eliminar jugadores
	public void deleteJug() {

		int longitudPlantilla = plantilla.size();
		int indiceEliminar;
		boolean encontrado = false;
		if (longitudPlantilla <= 0) {
			System.out.println("Aun no hay jugadores en plantilla\n");
		} else {
			do {
				indiceEliminar = localizarJugador();
				if (indiceEliminar >= 1 && indiceEliminar <= longitudPlantilla) {
					plantilla.remove(indiceEliminar - 1);
					encontrado = true;
				} else {
					System.out.println("Indice incorrecto");
				}
			} while (!encontrado);
		}
	}

	// Metodo para editar los atributos de un jugador
	public void updateJug() {

		int longitudPlantilla = plantilla.size();
		int indiceEditar;
		int indiceAccion;
		String nuevoNombre;
		String nuevaClase;
		boolean encontrado = false;
		Scanner sc = new Scanner(System.in);

		if (longitudPlantilla <= 0) {
			System.out.println("Aun no hay jugadores en plantilla\n");
		} else {
			do {
				indiceEditar = localizarJugador();
				if (indiceEditar >= 1 || indiceEditar <= longitudPlantilla) {
					System.out.println("¿Que valor deseas editar? \n 1. nombre \n 2. clase ");
					indiceAccion = Utilidades.validarInt();

					switch (indiceAccion) {
					case 1:
						System.out.println("Introduce el nuevo nombre");
						nuevoNombre = Utilidades.validarString();
						plantilla.get(indiceEditar - 1).setNombre(nuevoNombre);
						break;
					case 2:
						System.out.println("Introduce la nueva clase");
						nuevaClase = Utilidades.validarString();
						plantilla.get(indiceEditar - 1).setNombre(nuevaClase);
						break;
					default:
						System.out.println("Opcion no valida");
						break;
					}
					encontrado = true;
				}
			} while (!encontrado);
		}
	}

	// metodo que muestra el menu, aqui se ejecutan los metodos para el
	// funcionamiento de CRUD
	public void menuCrud() {

		String opcionCrud;
		char opcionChar;
		Scanner sc = new Scanner(System.in);

		do {
			System.out.println("Elige la opcion para la accion que quieras realizar");
			System.out.println("(C)Create \n(R)Read \n(U)Update \n(D)Delete \n(S)Salir");
			opcionCrud = sc.nextLine().toUpperCase();
			opcionChar = opcionCrud.charAt(0);

			switch (opcionChar) {
			case 'C':
				createJug();
				break;
			case 'R':
				readJug();
				break;
			case 'U':
				updateJug();
				break;
			case 'D':
				deleteJug();
				break;
			case 'S':
				System.out.println("Gracias por usar este menú");
				break;
			default:
				System.out.println("Opción no valida");
				break;
			}
		} while (opcionChar != 'S');
	}

}
