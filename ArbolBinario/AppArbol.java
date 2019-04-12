public class AppArbol {

    public static void main(String[] args)
    {
        Arbol a = new Arbol (6, new String ("+"));
        try 
        {
            a.Add(2, new String("*"));
            a.Add(1, new String("x"));
            a.Add(4, new String("-"));
            a.Add(3, new String("y"));
            a.Add(5, new String("C"));
            a.Add(8, new String("-"));
            a.Add(7, new String("B"));
            a.Add(9, new String("A"));
            
        }
        catch (Exception e) 
        {
            System.out.println(e.getMessage());
        }
        System.out.println("Total de nodos en árbol: "+a.CuentaNodos());
        System.out.println("PreOrden: ");
        a.PreOrden();
        System.out.println("InOrden: ");
        a.InOrden();
        System.out.println("PostOrden: ");
        a.PostOrden();
        System.out.println("Total de nodos en árbol: "+a.CuentaNodos());
        
        

    }

}