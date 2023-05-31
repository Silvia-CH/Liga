
public class Jugador {

	private String nombre;
	private String clase;
	private String equipo;

	public Jugador() {
		super();
	}

	public Jugador(String nombre, String clase) {
		super();
		this.nombre = nombre;
		this.clase = clase;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getClase() {
		return clase;
	}

	public void setClase(String clase) {
		this.clase = clase;
	}

	public String getEquipo() {
		return equipo;
	}

	public void setEquipo(String equipo) {
		this.equipo = equipo;
	}

	@Override
	public String toString() {
		return "Jugador [nombre=" + nombre + ", clase=" + clase + ", equipo=" + equipo + "]";
	}

}
