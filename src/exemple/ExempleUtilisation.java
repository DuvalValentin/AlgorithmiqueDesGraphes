package exemple;

import algorithme.*;
import graphElements.Elements.*;

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
		X.ajouteSommet(s1);X.ajouteSommet(s2);X.ajouteSommet(s3);X.ajouteSommet(s4);X.ajouteSommet(s5);X.ajouteSommet(s6);
		System.out.println("Un ensemble de sommets : "+X);
		
		EnsembleArc<Integer>Gamma= new EnsembleArc<Integer>();
		Gamma.ajouteArc(a1);Gamma.ajouteArc(a2);Gamma.ajouteArc(a3);Gamma.ajouteArc(a4);Gamma.ajouteArc(a5);
		Gamma.ajouteArc(a6);Gamma.ajouteArc(a7);Gamma.ajouteArc(a8);Gamma.ajouteArc(a9);Gamma.ajouteArc(a10);
		System.out.println("Un ensemble d'arcs : "+Gamma);
		
		Graphe<Integer> G = new Graphe<Integer>(X,Gamma);
		System.out.println("Un graphe : "+G.toString());
		
		
		
		//Opération élémentaires
		G.getX();//Obtenir l'ensemble de sommet du graphe
		G.getGamma();//Obtenir l'ensemble d'arc du graphe
		G.existSommet(s4);//Savoir si le sommet est présent dans le graphe
		G.existeArc(a5);//Savoir si l'arc est présent dans le graphe
		G.listPred(s6);//Obtenir une liste de prédécesseurs d'un sommet
		G.listSucc(s5);//Obtenir une liste des successeurs d'un sommet
		G.existeBoucle();//Savoir si il existe une boucle dans le graphe(ATTENTION ici on ne tient pas compte de la fermeture transitive)
		
		//Appels aux algorithmes
		Parcours.DFS(G, s1);//Juste un parcours du graphe (ne fait absolument rien)
		Parcours.WFS(G, s1);//Pareil	
		System.out.println();
		//System.out.println("Le graphe composé de G (arcs en deux temps) : "+FermetureTransitive.Composition(G, G));//En commentaire tant que composition est privé
		System.out.println("Fermeture transitive par puissance de Graphe : "+FermetureTransitive.PuissanceDeGraphe(G));
		System.out.println("Fermeture transitive par Roy-Warshall : "+FermetureTransitive.Roy_Warshall(G));
		System.out.println("Fermeture anti-transitive par Tau-Minalité (ne marche pas si circuit) : "+AntiTransitif.TauMinalite(G));
		System.out.println("Existence d'un circuit par Roy-Warshall: "+DetectionCircuit.Roy_Warshall(G));
		System.out.println("Existence d'un circuit par Marimont par points d'entrée: "+DetectionCircuit.MarimontEntree(G));
		System.out.println("Existence d'un circuit par Marimont par points de sortie: "+DetectionCircuit.MarimontSortie(G));
	}
}