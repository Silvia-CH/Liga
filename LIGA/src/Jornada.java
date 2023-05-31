import java.util.ArrayList;

public class Jornada {

	private String nombre;
	private ArrayList<Enfrentamiento> enfrentamientos = new ArrayList<Enfrentamiento>();
	private String liga;

	public Jornada(String nombre) {
		super();
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public ArrayList<Enfrentamiento> getEnfrentamientos() {
		return enfrentamientos;
	}

	public void setEnfrentamientos(ArrayList<Enfrentamiento> enfrentamientos) {
		this.enfrentamientos = enfrentamientos;
	}

	public String getLiga() {
		return liga;
	}

	public void setLiga(String liga) {
		this.liga = liga;
	}

	@Override
	public String toString() {
		return "Jornada [nombre=" + nombre + ", enfrentamientos=" + enfrentamientos + ", liga=" + liga + "]";
	}

//	muestra todos los enfrentamientos pertenecientes a la jornada, con un indice para mejor identificacion.
	public void mostrarEnfrentamientos() {
		int longitud = enfrentamientos.size();
		for (int i = 0; i < longitud; i++) {
			System.out.print(i + "-");
			enfrentamientos.get(i).mostrarEnfrentamiento();
		}
	}

//	añade enfrentamiento a la jornada
	public void addEnfrentamiento(Enfrentamiento item) {
		enfrentamientos.add(item);
		item.setJornada(nombre);
	}

}
