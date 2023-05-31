import java.util.Scanner;

public class Enfrentamiento {

	private String id;
	private Equipo equipo1;
	private Equipo equipo2;
	private int puntosEquipo1;
	private int puntosEquipo2;
	private String jornada;

	public Enfrentamiento(String id) {
		super();
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Equipo getEquipo1() {
		return equipo1;
	}

	public void setEquipo1(Equipo equipo1) {
		this.equipo1 = equipo1;
	}

	public Equipo getEquipo2() {
		return equipo2;
	}

	public void setEquipo2(Equipo equipo2) {
		this.equipo2 = equipo2;
	}

	public int getPuntosEquipo1() {
		return puntosEquipo1;
	}

	public void setPuntosEquipo1(int puntosEquipo1) {
		this.puntosEquipo1 = puntosEquipo1;
	}

	public int getPuntosEquipo2() {
		return puntosEquipo2;
	}

	public void setPuntosEquipo2(int puntosEquipo2) {
		this.puntosEquipo2 = puntosEquipo2;
	}

	public String getJornada() {
		return jornada;
	}

	public void setJornada(String jornada) {
		this.jornada = jornada;
	}

	@Override
	public String toString() {
		return "Enfrentamiento [id=" + id + ", equipo1=" + equipo1 + ", equipo2=" + equipo2 + ", puntosEquipo1="
				+ puntosEquipo1 + ", puntosEquipo2=" + puntosEquipo2 + ", jornada=" + jornada + "]";
	}

//	metodo que muestra el enfrentamiento por consola con un formate de equipo1 puntosEquipo1 - equipo2 puntosEquipo2
//	si el equipo es el de descanso se muestra con color azul
	public void mostrarEnfrentamiento() {
		String reset = "\u001B[0m";
		String blue = "\u001B[36m";
		if (equipo1.getNombre().equals("descanso") || equipo2.getNombre().equals("descanso")) {
			System.out.println(blue + equipo1.getNombre() + " " + puntosEquipo1 + " - " + equipo2.getNombre() + " "
					+ puntosEquipo2 + reset);
		} else {
			System.out.println(
					equipo1.getNombre() + " " + puntosEquipo1 + " - " + equipo2.getNombre() + " " + puntosEquipo2);
		}

	}

//	metodo que recibe dos equipos y los añade al enfrentamiento
	public void addEquipos(Equipo item1, Equipo item2) {
		setEquipo1(item1);
		item1.addIdEnfrentamiento(id);
		setEquipo2(item2);
		item2.addIdEnfrentamiento(id);
	}

//	metodo que recibe por parametro una opcion 1 o 2 para asignar los puntos en el enfrentamiento
//	si el equipo es el de descanso, se muestra un mensaje diciendo que no se puede hacer.
	public void asignarPuntos(int opcion) {
		int puntos = 0;

		if ((equipo1.getNombre().equals("descanso") || equipo2.getNombre().equals("descanso"))) {
			System.out.println("No se puede realizar esa accion");
		} else {
			switch (opcion) {
			case 1:
				do {
					puntos = pedirPuntos("Introduce los puntos a asignar al equipo " + equipo1.getNombre() + " 0-5");
				} while (puntos < 0 || puntos > 5);
				equipo1.setPuntaje(equipo1.getPuntaje() - puntosEquipo1);
				setPuntosEquipo1(puntos);
				equipo1.setPuntaje(equipo1.getPuntaje() + puntos);
				break;
			case 2:
				do {
					puntos = pedirPuntos("Introduce los puntos a asignar al equipo " + equipo2.getNombre() + " 0-5");
				} while (puntos < 0 || puntos > 5);
				equipo2.setPuntaje(equipo2.getPuntaje() - puntosEquipo2);
				setPuntosEquipo2(puntos);
				equipo2.setPuntaje(equipo2.getPuntaje() + puntos);
				break;
			default:
				System.out.println("No existe este equipo");
				break;
			}

		}

	}

// metodo que se encarga de pedir los puntos al usuario, se valida que lo introducido sea un numero, en caso de no serlo o de ocurrir un erros
//	se le vuelve a pedir que introduzca los puntos
	private int pedirPuntos(String mensaje) {
		Scanner sc = new Scanner(System.in);
		boolean val;
		String puntos = "0";
		do {
			val = true;
			try {
				System.out.println(mensaje);
				puntos = sc.nextLine();
				val = Utilidades.validarNumero(puntos);
			} catch (Exception e) {
				System.out.println("Error sin controlar");
				val = false;
			}
		} while (!val);
		return Integer.parseInt(puntos);
	}

}
