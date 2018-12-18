package graphElements;
import algorithme.*;
public class ExempleUtilisation
{
	public static void main(String[] args) 
	{
		
		//Opérations de créations : 
		
		Sommet<Integer> s1=new Sommet<Integer>(1);
		Sommet<Integer> s2=new Sommet<Integer>(2);
		Sommet<Integer> s3=new Sommet<Integer>(3);
		Sommet<Integer> s4=new Sommet<Integer>(4);
		Sommet<Integer> s5=new Sommet<Integer>(5);
		Sommet<Integer> s6=new Sommet<Integer>(6);
		System.out.println("Un sommet : "+s3);
		
		Arc<Integer>a1=new Arc<Integer>(s1,s2);
		Arc<Integer>a2=new Arc<Integer>(s1,s3);
		Arc<Integer>a3=new Arc<Integer>(s2,s4);
		Arc<Integer>a4=new Arc<Integer>(s2,s6);
		Arc<Integer>a5=new Arc<Integer>(s3,s4);
		Arc<Integer>a6=new Arc<Integer>(s3,s5);
		Arc<Integer>a7=new Arc<Integer>(s4,s6);
		Arc<Integer>a8=new Arc<Integer>(s5,s1);
		Arc<Integer>a9=new Arc<Integer>(s5,s6);
		Arc<Integer>a10=new Arc<Integer>(s6,s3);
		System.out.println("Un autre arc : "+a5);
		
		EnsembleSommet<Integer> X;
		X=new EnsembleSommet<Integer>();
		X.add(s1);X.add(s2);X.add(s3);X.add(s4);X.add(s5);X.add(s6);
		System.out.println("Un ensemble de sommets : "+X);
		
		EnsembleArc<Integer>Gamma= new EnsembleArc<Integer>();
		Gamma.add(a1);Gamma.add(a2);Gamma.add(a3);Gamma.add(a4);Gamma.add(a5);
		Gamma.add(a6);Gamma.add(a7);Gamma.add(a8);Gamma.add(a9);Gamma.add(a10);
		System.out.println("Un ensemble d'arcs : "+Gamma);
		
		Graphe<Integer> G = new Graphe<Integer>(X,Gamma);
		System.out.println("Un graphe : "+G.toString());
		
		
		
		//Opération élémentaires
		G.getX();//Obtenir l'ensemble de sommet du graphe
		G.getGamma();//Obtenir l'ensemble d'arc du graphe
		G.existSommet(s4);//Savoir si le sommet est présent dans le graphe
		G.existArc(a5);//Savoir si l'arc est présent dans le graphe
		G.listPred(s6);//Obtenir une liste de prédécesseurs d'un sommet
		G.listSucc(s5);//Obtenir une liste des successeurs d'un sommet
		G.existeBoucle(s4);//Savoir si il existe une boucle au niveau de ce sommet
		
		//Appels aux algorithmes
		Parcours.DFS(G, s1);//Juste un parcours du graphe (ne fait absolument rien)
		Parcours.WFS(G, s1);//Pareil	
		System.out.println();
		//System.out.println("Le graphe composé de G (arcs en deux temps) : "+FermetureTransitive.Composition(G, G));//En commentaire tant que composition est privé
		System.out.println("Fermeture transitive par puissance de Graphe : "+FermetureTransitive.PuissanceDeGraphe(G));
		System.out.println("Fermeture transitive par Roy-Warshall : "+FermetureTransitive.Roy_Warshall(G));
		//EnsembleArcValués<Integer,Integer>Gamma2= new EnsembleArcValués<Integer,Integer>();
	}
}
