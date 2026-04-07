package Actividades;

public class actividad4 {
	/*
	4.ANÁLISIS FINAL DE LA COMPLEJIDAD
	La complejidad temporal de la función depende
	de si el valor de y es impar o par.
	- En el peor de los casos:
	  Cuando y es impar, la función recursiva
	  se llama de manera más profunda,
	  disminuyendo de 1 en 1 (y, y-1, y-2, ...)

	  Complejidad:
	  O(y)

	- En el mejor de los casos:
	  Cuando y es par, la función reduce el problema
	  dividiendo entre 2 en cada llamada (y, y/2, y/4, ...)

	  Complejidad:
	  O(log y)
	  
	CONCLUSION:

	- Mejor caso (y par):
	  O(log y)

	- Peor caso (y impar):
	  O(y)

	Por lo tanto, el orden de complejidad en el peor caso es:
	
	O(y)
	ya que es el escenario mas costoso.
	*/
}
