import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Liga {

	private String nombre;
	private ArrayList<Equipo> gruposLiga = new ArrayList<Equipo>();
	private ArrayList<Jornada> jornadas = new ArrayList<Jornada>();

	public Liga() {
		super();
	}

	public Liga(String nombre) {
		super();
		this.nombre = nombre;
	}

//	metodos

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public ArrayList<Equipo> getGruposLiga() {
		return gruposLiga;
	}

	public void setGruposLiga(ArrayList<Equipo> gruposLiga) {
		this.gruposLiga = gruposLiga;
	}

	public ArrayList<Jornada> getJornadas() {
		return jornadas;
	}

	public void setJornadas(ArrayList<Jornada> jornadas) {
		this.jornadas = jornadas;
	}

	@Override
	public String toString() {
		return "Liga [nombre=" + nombre + ", gruposLiga=" + gruposLiga + ", jornadas=" + jornadas + "]";
	}

	public void addEquipo(Equipo grupo) {
		gruposLiga.add(grupo);
		grupo.setLiga(nombre);
	}

	public void removeEquipo(int indice) {
		gruposLiga.remove(indice);
	}

	public void addJornada(Jornada item) {
		jornadas.add(item);
		item.setLiga(nombre);
	}

//	metodo que genera las jornadas necesarias para que se enfrenten todos los equipos pertenecientes a la liga.
	public void jornadas() {
		ArrayList<ArrayList<String>> arrayJornadas;
		int longitud = gruposLiga.size();
		int jornadas;
		int contador = 0;
		String grupo1;
		String grupo2;
		boolean val;
		boolean descansoCreado = false;
		String enfrentamientoActual;
		Equipo descanso = new Equipo("descanso");
		int longitudArrayJornadas;
		Enfrentamiento enfrentamiento;

		this.jornadas.clear();

//		dependiendo de si la cantidad de equipos es par o impar se calculan datos importante como el numero de jornadas, enfrentamientos totales y por jornada asi como la necesidad de añadir temporalmente un equipo de descanso.
		if (longitud % 2 != 0) {
			jornadas = longitud;
			gruposLiga.add(descanso);
			longitud = gruposLiga.size();
			descansoCreado = true;
		} else {
			jornadas = longitud - 1;
		}
//		utilizando un metodo se genera una lista de listas, siendo cada lista una jornada.
		arrayJornadas = generarJornadas(jornadas);
		createJornadas(jornadas);
		longitudArrayJornadas = jornadas - 1;

//		con estos bucles se generan todos los enfrentamientos posibles, el primero contra todos los que le siguen, asi hasta llegar al ultimo equipo.
		for (int i = 0; i < longitud; i++) {
			for (int j = i + 1; j < longitud; j++) {
				grupo1 = (gruposLiga.get(i).getNombre()) + ".";
				grupo2 = (gruposLiga.get(j).getNombre()) + ".";
				enfrentamientoActual = grupo1 + " -- " + grupo2;
//				System.out.println(enfrentamientoActual);
				val = false;
				do {
//					se valida que ninguno de los equipos del enfrentamiento actual ya haya participado en la jornada.
//					si no han participado se agrega el enfrentamiendo y si no se aumenta el contador para validar en la jornada siguiente.
					if (validarGrupos(grupo1, grupo2, arrayJornadas.get(contador))) {
						arrayJornadas.get(contador).add(enfrentamientoActual);
						enfrentamiento = new Enfrentamiento("" + i + j);
						enfrentamiento.addEquipos(gruposLiga.get(i), gruposLiga.get(j));
						this.jornadas.get(contador).addEnfrentamiento(enfrentamiento);
						val = true;
					}
//					si el contador es inferior a la ultima jornada se aumenta en uno
					if (contador < longitudArrayJornadas) {
						contador += 1;
					} else {
//						una vez llegado a la ultima jornada se resetea el contador para volver a la primera jornada.
						contador = 0;
					}
//					se seguira validando hasta que se encuentre en una jornda donde no hayan participado ninguno de los equipos y se añada a esta.
				} while (!val);
			}
		}
//		se valida si se ha creado o no el grupo de descanso, si se ha hecho se borra ya que solo era un auxiliar.
		if (descansoCreado) {
			gruposLiga.remove(descanso);
		}
	}

//	metodo para generar una lista con las jornadas.
	private ArrayList<ArrayList<String>> generarJornadas(int numeroJornadas) {
		ArrayList<ArrayList<String>> jornadas = new ArrayList<ArrayList<String>>();
		for (int i = 0; i < numeroJornadas; i++) {
			jornadas.add(new ArrayList<String>());
		}
		return jornadas;
	}

//	metodo para validar que ninguno de los dos grupos existe en la jornada dada.
	private boolean validarGrupos(String grupo1, String grupo2, ArrayList<String> jornada) {
		return (jornada.toString().indexOf(grupo1) < 0 && jornada.toString().indexOf(grupo2) < 0);
	}

	public void ListarLiga() {
		System.out.println(gruposLiga.toString());
	}

//	metodo que crea tantos objetos de tipo jornada como la cantidad pasada por parametro
//	y los añade a la lista del objeto liga
	private void createJornadas(int numero) {
		for (int i = 0; i < numero; i++) {
			addJornada(new Jornada("Jornada" + (i + 1)));
		}
	}

//	metodo que muestra todas la jornadas de la liga con sus enfrentamientos
	public void mostrarJornadas() {
		int longitud = jornadas.size();
		for (int i = 0; i < longitud; i++) {
			System.out.println("\n         " + jornadas.get(i).getNombre() + "(" + i + ")\n");
			jornadas.get(i).mostrarEnfrentamientos();
		}
	}

//	metodo que limpia en todos los equipos pertenecientes a la liga sus lista de ids para que a la hora de volver a generar las jorndas no se acumulen
	private void resetIdEnfrentamientos() {
		for (Equipo item : gruposLiga) {
			item.getEnfrentamiento().clear();
		}
	}

//	metodo que obtiene los puntos de los equipos de la liga y los devuelve en un array
	public ArrayList<Integer> obtenerPuntos() {
		ArrayList<Integer> puntosAuxiliar = new ArrayList<Integer>();
		int longitud = gruposLiga.size();

		for (int i = 0; i < longitud; i++) {
			puntosAuxiliar.add(gruposLiga.get(i).getPuntaje());

		}
		return puntosAuxiliar;
	}

	// metodo que ordena y muestra la clasificacion de puntos de la liga
	public void mostrarClasificacion() {
		ArrayList<Integer> puntosFinales = obtenerPuntos();
		ArrayList<Equipo> equiposAuxiliar = (ArrayList<Equipo>) gruposLiga.clone();
		int longitud = puntosFinales.size();
		int contador;
		int puntosActuales;
		int puntosActualesEquipo;
		boolean encontrado;
		Equipo equipoAuxiliar = new Equipo();

		equipoAuxiliar.setPuntaje(-1);
		Collections.sort(puntosFinales);
		Collections.reverse(puntosFinales);

		System.out.println("\n          Clasificación\n");

		for (int i = 0; i < longitud; i++) {
			contador = 0;
			puntosActuales = puntosFinales.get(i);
			encontrado = false;
			do {
				puntosActualesEquipo = equiposAuxiliar.get(contador).getPuntaje();
				if (puntosActuales == puntosActualesEquipo) {
					encontrado = true;
					System.out.println(
							(i + 1) + " - " + equiposAuxiliar.get(contador).getNombre() + " puntos: " + puntosActuales);
					equiposAuxiliar.set(contador, equipoAuxiliar);
				} else {
					contador++;
				}

			} while (!encontrado);
		}
	}

//	metodo que asigna puntos en todos los enfrentamientos de la liga, menos a aquellos donde participe el equipo de descanso.
	public void asignarTodosPuntos() {
		Scanner sc = new Scanner(System.in);
		String opcion;
		String equipo1;
		String equipo2;
		boolean val;
		int valor = 0;

		for (Jornada item : jornadas) {
			for (Enfrentamiento itemEnfrentamiento : item.getEnfrentamientos()) {
				equipo1 = itemEnfrentamiento.getEquipo1().getNombre();
				equipo2 = itemEnfrentamiento.getEquipo2().getNombre();
				if (!equipo1.equals("descanso") && !equipo2.equals("descanso")) {
					do {
						val = true;
						System.out.println("¿Para que equipo quieres introducir puntos? ");
						System.out.println("1- " + equipo1 + " 2- " + equipo2);
						opcion = sc.nextLine();

						if (!Utilidades.validarNumero(opcion)) {
							val = false;
						} else {
							valor = Integer.parseInt(opcion);
							if (valor < 1 || valor > 2) {
								val = false;
							}
						}
					} while (!val);
					itemEnfrentamiento.asignarPuntos(valor);
				}
			}
		}
	}

	// CRUD

	public void elegirAccion() {
		boolean continuar = true;

		try {
			crear("Introduzca un nombre para el primer equipo:");
			crear("Introduzca un nombre para el segundo equipo:");
			do {
				System.out.println("");
				System.out.println("Elige una opción:");

				switch (Utilidades
						.preguntarChar("Crear(C) || Modificar(M) || Eliminar(E) || Listar(L) || Empezar Liga(S)")) {
				case 'C':
					crear("Introduzca un nombre para el nuevo equipo:");
					break;
				case 'M':
					modificar();
					break;
				case 'E':
					eliminar();
					break;
				case 'L':
					listar();
					break;
				case 'S':
					continuar = false;
					resetIdEnfrentamientos();
					jornadas();
					break;

				default:
					System.out.println("No se ha escogido ninguna de las opciones.");
				}
			} while (continuar);

			continuar = true;

			do {
				switch (Utilidades
						.preguntarChar("Asignar puntos (A) || Clasificacion (C) || Jornadas (J) || Salir (S)")) {
				case 'A':
					asignarTodosPuntos();
					break;
				case 'C':
					mostrarClasificacion();
					break;
				case 'J':
					mostrarJornadas();
					break;
				case 'S':
					continuar = false;
					break;
				default:
					System.out.println("No se ha escogido ninguna de las opciones.");
				}
			} while (continuar);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Acciones
	public void crear(String texto) {
		String nombre;

		nombre = Utilidades.preguntarString(texto);
		gruposLiga.add(new Equipo(nombre));
	}

	public void listar() {
		int contador = 0;

		System.out.println("EQUIPOS: ");

		for (Equipo equipo : gruposLiga) {
			contador++;
			System.out.println("Equipo nº" + contador + ": " + equipo.getNombre());
		}
	}

	public void modificar() {
		switch (Utilidades.preguntarChar("¿Qué atributo quieres modificar? (Todos: T || Nombre: N || Plantilla: P)")) {
		case 'T':
			modTotal();
			break;

		case 'N':
			modNombre();
			break;

		case 'P':
			modPlantilla();
			break;

		default:
			System.out.println("No se ha escogido ninguna de las opciones.");
		}
	}

	private void eliminar() {
		String nombre;
		int contador = 0;
		boolean encontrar = false;

		if (gruposLiga.size() > 2) {
			listar();
			nombre = Utilidades.preguntarString("¿Qué equipo quieres eliminar?");

			do {
				if (nombre.equals(gruposLiga.get(contador).getNombre())) {
					gruposLiga.remove(gruposLiga.get(contador));
					encontrar = true;
				}
				contador++;
			} while (!encontrar);

			if (!encontrar) {
				System.out.println("Nombre no encontrado");
			}
		} else {
			System.out.println("No se puede eliminar equipos");
		}
	}

	// SUB ACCIONES

	private void modTotal() {
		String nombre;
		String nombreMod;
		int contador = 0;
		boolean encontrar = false;

		listar();
		nombre = Utilidades.preguntarString("¿Qué equipo quieres modificar?"); // pregunta por el nombre del equipo

		do {
			if (nombre.equals(gruposLiga.get(contador).getNombre())) { // busca por el nombre
				nombreMod = Utilidades.preguntarString("¿Qué nombre quieres introducir?"); // pregunta por un nombre
																							// nuevo para el
				// equipo
				gruposLiga.get(contador).setNombre(nombreMod); // cambia el nombre
				encontrar = true;
				contador++;

				gruposLiga.get(contador).menuCrud();

			}
		} while (!encontrar);

		if (!encontrar) {
			System.out.println("Nombre no encontrado");
		}
	}

	private void modNombre() {
		String nombre;
		String nombreMod;
		int contador = 0;
		boolean encontrar = false;

		listar();
		nombre = Utilidades.preguntarString("¿Qué equipo quieres modificar?"); // pregunta por el nombre del equipo

		do {
			if (nombre.equals(gruposLiga.get(contador).getNombre())) { // busca por el nombre
				nombreMod = Utilidades.preguntarString("¿Qué nombre quieres introducir?"); // pregunta por un nombre
																							// nuevo para el
				// equipo
				gruposLiga.get(contador).setNombre(nombreMod); // cambia el nombre
				encontrar = true;
			}
			contador++;
		} while (!encontrar);

		if (!encontrar) {
			System.out.println("Nombre no encontrado");
		}
	}

	private void modPlantilla() {
		String nombre;
		int contador = 0;
		boolean encontrar = false;

		listar();
		nombre = Utilidades.preguntarString("¿Qué equipo quieres modificar?"); // pregunta por el nombre del equipo

		do {
			if (nombre.equals(gruposLiga.get(contador).getNombre())) { // busca por el nombre
				encontrar = true;
				contador++;

				gruposLiga.get(contador).menuCrud();

			}
		} while (!encontrar);

		if (!encontrar) {
			System.out.println("Nombre no encontrado");
		}
	}
}