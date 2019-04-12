public class Arbol<T>{
	private Nodo<T> raiz;

	public Arbol(int clave, T datos){
		this.raiz = new Nodo<T>(null,clave, datos, null);
	}
	public void Add(int clave,T datos) throws Exception{
		Add(clave, datos, raiz);
	}
	private void Add(int clave, T datos, Nodo<T>R) throws Exception	{

		if(R == null){
			raiz=new Nodo<T>(null, clave, datos, null);
		}else{
			if(clave< R.getClave()){
				if (R.getIzq()!=null)
					Add(clave,datos,R.getIzq());
				else{
					R.setIzq(new Nodo<T>(null,clave,datos,null));
				}
			}else{
				//Aquí agregamos
				if(clave>R.getClave()){
					if(R.getDer()!=null)
						Add(clave,datos,R.getDer());
					else{
						R.setDer(new Nodo<T>(null,clave,datos,null));
					}
				}else{
					throw new Exception("clave Duplicada");
				}
			}
		}
	}
	public void PreOrden(){
		PreOrden(raiz);
	}
	private void PreOrden(Nodo<T>R){
		if(R!=null){
			System.out.print(R.getClave() + "\t");	
			System.out.println(R.getDatos());
			PreOrden(R.getIzq());
			PreOrden(R.getDer());
		}
	}
	public void InOrden(){
		InOrden(raiz);
	}
	public void InOrden(Nodo<T>R){
		if(R!=null)	{
			InOrden(R.getIzq());
			System.out.print(R.getClave()+"\t");
			System.out.println(R.getDatos());
			InOrden(R.getDer());
		}
	}
	public void PostOrden() {
		PostOrden (raiz);
	}
	public void PostOrden(Nodo<T>R){
		if(R!=null)
		{
			PostOrden(R.getIzq());
			PostOrden(R.getDer());
			System.out.print(R.getClave()+"\t");
            System.out.println(R.getDatos());
		}
	}
	private void PasaIzquierda(Nodo<T>R,int Cantidad) throws Exception{
		if(Cantidad>0 && raiz!=null){
			//Paso 1.- Duplicar la raiz en el subarbol izquierdo porque se va a la izquierda
			Add(R.getClave(),R.getDatos(),R.getIzq());
			//Paso 2.- Sustituir la raiz con el menor de los mayores
			Nodo<T> TempI=R.getDer();
			while(TempI.getIzq()!=null)
				TempI=TempI.getIzq();
			R.setClave(TempI.getClave());
			R.setDatos(R.getDatos());
			//Eliminar el menor de los mayores
			Remove(TempI.getClave(),R.getDer(),R);
			PasaIzquierda(R,Cantidad-1);
		}
	}
	private void PasaDerecha(Nodo<T>R,int Cantidad) throws Exception{
		if(Cantidad>0 && raiz!=null){
			//Paso 1.- Duplicar la raiz en el subarbol derecho porque se va a la derecho
			Add(R.getClave(),R.getDatos(),R.getDer());
			//Paso 2.- Sustituir la raiz con el mayor de los menores
			Nodo<T> TempD=R.getIzq();
			while(TempD.getIzq()!=null)
				TempD=TempD.getIzq();
			R.setClave(TempD.getClave());
			R.setDatos(R.getDatos());
			//Eliminar el mayor de los menores
			Remove(TempD.getClave(),R.getIzq(),R);
			PasaDerecha(R,Cantidad-1);
		}
	}

	public void Balancea() throws Exception{
		Balancea(raiz);
	}

	private void Balancea(Nodo<T> R)throws Exception{
		if(R!=null){
			int NI=CuentaNodos(R.getIzq());
			int ND=CuentaNodos(R.getDer());
			if(NI>ND){
				if((NI-ND)>1)
					PasaDerecha(R,(NI-ND)/2);
			}else{
				if((ND-NI)>1)
					PasaIzquierda(R,(ND-NI)/2);
			}
			Balancea(R.getIzq());
			Balancea(R.getDer());
		}
	}

	public int CuentaNodos(){
		return CuentaNodos(raiz);
	}
	private int CuentaNodos(Nodo<T>R){
		if(R!=null)
		{
			return CuentaNodos(R.getIzq())+CuentaNodos(R.getDer())+1;
		}
		return 0;
	}

	public T getDatos(Nodo<T> R, int clave){
		if(R==null)
			return null;
		else{
			if (clave<R.getClave())
				return getDatos(R.getIzq(),clave);
			else if(clave > R.getClave())
				return getDatos(R.getDer(),clave);
			else
				return R.getDatos(); //return R!=null?R.getDatos():null;
		}
	}
	public boolean Remove(int cve){
		return Remove (cve,raiz ,raiz);
	}
	private boolean Remove(int cve, Nodo<T> R, Nodo<T> RA){
		Nodo<T> tmp;
		if(R==null)
			return false;
		else{
			if(cve<R.getClave()){
				return Remove(cve, R.getIzq(),R);
			}
			else
				if(cve>R.getClave()){
					return Remove(cve,R.getDer(),R);
				}
				else{
					//cuando tenga 2 hijos
					if(R.getIzq()!=null && R.getDer()!=null){
						if(CuentaNodos(R.getIzq())>CuentaNodos(R.getDer())){
							//El mayor de los menores
							tmp=R.getIzq();
							while(tmp.getDer()!=null)
								tmp=tmp.getDer();
							boolean x=Remove(tmp.getClave(),R.getIzq(),R);
							R.setClave(tmp.getClave());
							R.setDatos(tmp.getDatos());
							return x;
						}
						else{
							//El menor de los mayores
							tmp=R.getDer();
							while(tmp.getIzq()!=null)
								tmp=tmp.getIzq();
							boolean x=Remove(tmp.getClave(),R.getDer(),R);
							R.setClave(tmp.getClave());
							R.setDatos(tmp.getDatos());
							return x;
						}
					}
					else{
						//cuando tiene cero o un hijo y se trata de la raiz
						//principal la que se va a eliminar
						if(R==RA)
							//cuando ya es el ultimo nodo que queda en el arbol
							if(R.getIzq()==null && R.getDer()==null)
								raiz=null;
							else
								//Cuando es el penultimo nodo que queda en el arbol
								if(R.getIzq()!=null)
									raiz=R.getIzq();
								else
									raiz=R.getDer();
						else
							//Cuando tenga 0 o 1 hijo y no es la raiz principal
							if(R.getIzq()==null)
								if(RA.getClave()>=R.getClave())
									RA.setIzq(R.getDer());
								else
									RA.setDer(R.getDer());
							else if(RA.getClave()>=R.getClave())
								RA.setIzq(R.getIzq());
							else
								RA.setDer(R.getIzq());
						return true;
					}
				}
		}
	}
	public int CuentaLadoDer()
	{
		return CuentaLado(raiz.getDer());
	}
	public int CuentaLado(Nodo<T>R){
		if(R!=null)
			return CuentaNodos(R.getDer());
		else
			return 0;
	}
	public int CuentaLadoIzq()
	{
		return CuentaLado(raiz.getIzq());
	}
	public int CuentaLadoIzq(Nodo<T>R){
		if(R!=null)
			return CuentaNodos(R.getIzq());
		else
			return 0;
	}

}