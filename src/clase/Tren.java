package clase;

import java.util.Scanner;

public class Tren {
	Scanner teclado= new Scanner (System.in);
	// Declaración variables

	private String tipo;
	private String referencia;
	private int asientosOcupados;
	private int kmRecorridos;
	private int duracionViaje;

	// Constante siempre en mayuscula
	// Podemos hacer getter de la constante para llamarla en el otro lado
	// La constante la podemos tener pública para luego usarla sin get
	// Las constantes son valores que no se cambian
	public final static int CAPACIDAD_MAXIMA = 400;

	// Constructor con todo.

	public Tren(String tipo, String referencia, int asientosOcupados, int kmRecorridos, int duracionViaje) {
		// Borrar el super siempre
		this.tipo = tipo;
		// Que cumpla con que si no mete esos tipos, lo cambie por Indefinido
		if (tipo.equalsIgnoreCase("Cercanias") || tipo.equalsIgnoreCase("Mercancias") || tipo.equalsIgnoreCase("Ave")) {
			// Importante revisar que tenga el this
			this.tipo = "indefinido";
		}
		this.referencia = referencia;
		this.asientosOcupados = asientosOcupados;
		this.kmRecorridos = kmRecorridos;
		this.duracionViaje = duracionViaje;
	}

	// Otro constructor en el que solo es necesario el tipo
	public Tren(String tipo) {
		this.tipo = tipo;
		if (tipo.equalsIgnoreCase("Cercanias") || tipo.equalsIgnoreCase("Mercancias") || tipo.equalsIgnoreCase("Ave")) {
			this.tipo = "indefinido";
		}
		// Cambie los valores a 0 para que fuese distinto al otro
		this.referencia = "";
		this.asientosOcupados = 0;
		this.kmRecorridos = 0;
		this.duracionViaje = 0;
	}

	// Getters and setters
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public int getAsientosOcupados() {
		return asientosOcupados;
	}

	public void setAsientosOcupados(int asientosOcupados) {
		this.asientosOcupados = asientosOcupados;
	}

	public int getKmRecorridos() {
		return kmRecorridos;
	}

	public void setKmRecorridos(int kmRecorridos) {
		this.kmRecorridos = kmRecorridos;
	}

	public int getDuracionViaje() {
		return duracionViaje;
	}

	public void setDuracionViaje(int duracionViaje) {
		this.duracionViaje = duracionViaje;
	}

	// Métodos propios
	// Devuelve un valor booleano que nos indica si el tren está o no lleno. La capacidad máxima
	// de un tren es de 400 viajeros (este valor lo guardaremos en una constante, (Arriba constante) para su tratamiento). 
	
	public boolean trenLleno() {
		return asientosOcupados==CAPACIDAD_MAXIMA;
	}
	
	// Nos devuelve la velocidad en Km/h del tren. Teniendo en cuenta que la
	// duración del viaje viene expresada en minutos, la velocidad se obtiene de dividir los kilómetros recorridos entre la
	// duración del viaje expresado en horas.

	// float porque es división
	
	public float obtenerVelocidadTotal() {
		return (float)kmRecorridos/duracionViaje/60;
	}

	public void setDatos() {
		// Creamos Set datos
		System.out.println("Introduce referencia: ");
		referencia= teclado.next();
		System.out.println("Introduce asientos ocupados: ");
		asientosOcupados= teclado.nextInt();
	}

	@Override
	public String toString() {
		return "Tren [tipo=" + tipo + ", referencia=" + referencia + ", asientosOcupados=" + asientosOcupados
				+ ", kmRecorridos=" + kmRecorridos + ", duracionViaje=" + duracionViaje + "]";
	}
	
}
