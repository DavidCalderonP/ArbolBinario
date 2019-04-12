//Árbol binario de busqueda (A la derecha los mayores que la raíz y a la izquierda los menores a la raíz.).
public class Nodo <T>
{
	// Datos.
	private int clave;
	private T datos;
	//Enlaces
	private Nodo<T>izq, der;
	public Nodo( Nodo<T> izq,int clave, T datos, Nodo<T> der) 
	{
		this.clave = clave;
		this.datos = datos;
		this.izq = izq;
		this.der = der;
	}
	public int getClave() {
		return clave;
	}
	public void setClave(int clave) {
		this.clave = clave;
	}
	public T getDatos() {
		return datos;
	}
	public void setDatos(T datos) {
		this.datos = datos;
	}
	public Nodo<T> getIzq() {
		return izq;
	}
	public void setIzq(Nodo<T> izq) {
		this.izq = izq;
	}
	public Nodo<T> getDer() {
		return der;
	}
	public void setDer(Nodo<T> der) {
		this.der = der;
	}


}