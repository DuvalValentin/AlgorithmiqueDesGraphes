package graphElements;

public class Test
{
	public static void main(String[] args) 
	{
		Sommet<Integer> s1=new Sommet<Integer>(1);
		System.out.println("Un sommet : "+s1);
		
		Sommet<Integer> s2=new Sommet<Integer>(2);
		System.out.println("Un autre sommet : "+s2);
		
		//Sommet<Integer> s3=new Sommet<Integer>(2);
		//System.out.println(s2.equals(s3));
		
		Arc<Integer>a1=new Arc<Integer>(s1,s2);
		System.out.println("Un arc : "+a1);
		
		Arc<Integer>a2=new Arc<Integer>(s2,s1);
		System.out.println("Un autre arc : "+a2);
		
		//Arc<Integer>a3=new Arc<Integer>(s2,s1);
		
		EnsembleSommet<Integer> X;
		X=new EnsembleSommet<Integer>();
		X.add(s1);
		X.add(s2);
		//X.add(s3);
		System.out.println("Un ensemble de sommets : "+X);
		EnsembleArc<Integer>Gamma= new EnsembleArc<Integer>();
		Gamma.add(a1);
		Gamma.add(a2);
		//Gamma.add(a3);
		System.out.println("Un ensemble d'arcs : "+Gamma);
		Graphe<Integer> G = new Graphe<Integer>(X,Gamma);
		System.out.println("Un graphe : "+G);
		
	}
}
