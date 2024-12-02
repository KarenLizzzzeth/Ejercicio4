package principal;

import java.util.Scanner;

import clase.Tren;

public class Principal {

	public static void main(String[] args) {
		// Una vez creada la clase anterior y utilizando cuando se precise,
		// los métodos en ella implementados, vamos a diseñar
		// un programa que gestione todos los trenes con salida desde Bilbao
		// (máximo 30).

		Scanner teclado = new Scanner(System.in);
		int opc;

		// Array con la clase

		Tren[] trenes = new Tren[30];
		// Crear índice con número de trenes
		int numTrenes = 0;

		do {
			menu();
			opc = teclado.nextInt();

			// Controlar que sino hay trenes metidos no se ejecute el menú
			if (numTrenes == 0 && (opc > 1 && opc < 6)) {
				System.out.println("No hay trenes introducidos");
			} else {
				// Ejecutar el resto
				switch (opc) {
				case 1:
					numTrenes = introducirTrenes(trenes, numTrenes);
					break;

				case 2:
					mostrarTrenesconVelocidadSuperiorA(trenes, numTrenes);

					break;

				case 3:
					ordenarTrenes(trenes, numTrenes);

					break;

				case 4:
					reservaAsientos(trenes, numTrenes);
					break;

				case 5:
					mostrarTrenes(trenes, numTrenes);

					break;

				case 6:

					break;

				default:
					break;
				}
			}
		} while (opc != 6);

	}

	private static void reservaAsientos(Tren[] trenes, int numTrenes) {
		// Se pide la referencia del tren, si existe y no está lleno, se le pedirá al
		// usuario el nº de asientos que quiere reservar.
		// Se le indicará si es o no posible (en función de los asientos disponibles en
		// ese momento) y si todo es correcto, se
		// actualizará la información de los asientos ocupados del tren. Mensaje de
		// aviso si no se puede llevar a cabo la
		// reserva por no encontrarse el tren buscado o estar lleno

		Scanner teclado = new Scanner(System.in);
		String ref;
		int asientos;
		String proceso = "Sin procesar";

		System.out.println("Introduce la referencia");
		ref = teclado.next();

		for (int i = 0; i < numTrenes; i++) {
			if (trenes[i].getReferencia().equalsIgnoreCase(ref)) {
				// Usamos la variable proceso para mandar mensajes
				proceso = "Encontrado";
				if (!trenes[i].trenLleno()) {
					proceso = "Disponibilidad";
					System.out.println("Introduce asientos a reservar: ");
					asientos = teclado.nextInt();

					// Asi llamamos la constante
					if ((trenes[i].getAsientosOcupados() + asientos) <= Tren.CAPACIDAD_MAXIMA) {

						// Vamos a hacer actualización, con el set y con el get lo sumamos
						trenes[i].setAsientosOcupados(asientos + trenes[i].getAsientosOcupados());
						proceso= "Correcto";
					}
				}
			}
		}
		if(proceso.equalsIgnoreCase("Correcto")) {
			System.out.println("Actualización realizada con éxito");
		} else if (proceso.equalsIgnoreCase("Disponibilidad")) {
			System.out.println("Error, no hay tantos asientos disponibles en el tren de referencia");
		} else if (proceso.equalsIgnoreCase("Encontrado")) {
			System.out.println("Error, el tren de referencia está lleno");
		} else {
			System.out.println("Tren, no encontrado");
		}

	}

	private static void ordenarTrenes(Tren[] trenes, int numTrenes) {
		// Ordenamos los trenes por duración del viaje de menor a mayor, y mostraremos
		// solo los que son de Cercanías.
		Tren inter;

		// método burbuja para ordenar
		for (int i = 0; i < numTrenes; i++) {
			for (int j = i + 1; j < numTrenes; j++) {
				if (trenes[j].getDuracionViaje() < trenes[i].getDuracionViaje()) {
					inter = trenes[i];
				trenes[i] = trenes[j];
				trenes[j] = inter;
				}
			}
		}
		// For para recorrer y mostrar solo los de cercanias
		for (int i = 0; i < numTrenes; i++) {
			if (trenes[i].getTipo().equalsIgnoreCase("Cercanias")) {
				System.out.println(trenes[i]);
			}
		}
	}

	private static void mostrarTrenesconVelocidadSuperiorA(Tren[] trenes, int numTrenes) {
		// Pediremos por pantalla la velocidad. Vamos a mostrar todos los trenes cuya
		// velocidad sea superior a la
		// introducida. De cada tren solo queremos visualizar la información de su
		// referencia y velocidad.
		// Si no existe ningún tren que supere dicha velocidad, mostraremos un mensaje
		// informando de este hecho.

		Scanner teclado = new Scanner(System.in);
		float velocidad, vel;
		boolean encontrado = false;

		System.out.println("Introduce la velocidad");
		velocidad = teclado.nextInt();

		for (int i = 0; i < numTrenes; i++) {

			// Creamos variable para obtener la velocidad desde el método creado antes
			vel = trenes[i].obtenerVelocidadTotal();
			if (trenes[i].obtenerVelocidadTotal() > velocidad) {
				// Usamos el format para mostrar mejor el float que tenemos en velocidad, usamos
				// el get para obtener referencia
				System.out.printf("Este tren con referencia %s tiene una velocidad de %,2f", trenes[i].getReferencia(),
						vel);
				encontrado = true;
			}
		}
		// Mensaje de no encontrado
		if (!encontrado) {
			System.out.println("No hay tren que cumpla con esa condición");
		}
	}

	private static void mostrarTrenes(Tren[] trenes, int numTrenes) {
		// Se ha de mostrar toda la información respectiva al tren con toString
		for (int i = 0; i < numTrenes; i++) {
			System.out.println(trenes[i]);
		}
	}

	// Método que devuelve int, parametros array e indice
	private static int introducirTrenes(Tren[] trenes, int numTrenes) {
		Scanner teclado = new Scanner(System.in);
		boolean seguir = true;
		Tren tren;
		String tipo;

		// For para introducir los datos
		for (; numTrenes < trenes.length && seguir;) {
			System.out.println("Introduce el tipo: ");
			tipo = teclado.next();

			// Darle valor al tren
			tren = new Tren(tipo);
			// Set datos creado al otro lado que da valor a los otros datos
			tren.setDatos();

			// NumTrenes indica que vamos desde el índice (Número que antes haya quedado)
			trenes[numTrenes] = tren;

			// Sumamos al indice el tren introducido
			numTrenes++;

			System.out.println("¿Quieres seguir introduciendo trenes?");
			seguir = teclado.next().equalsIgnoreCase("Si");
		}
		// Mensaje de no poder introducir más, fuera del for para que solo se muestre
		// una vez
		if (numTrenes >= trenes.length) {
			System.out.println("Límite sobrepasado");
		}
		// Retorna int
		return numTrenes;
	}

	private static void menu() {
		// Hacer menú

	}

}
