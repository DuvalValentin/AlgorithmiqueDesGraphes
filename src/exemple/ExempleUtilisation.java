package exemple;

import algorithme.*;
import factory.Factory;
import graphElements.Interfaces.*;

public class ExempleUtilisation
{
	public static void main(String[] args) throws Exception
	{
		
		//Opérations de créations : 
		
		InterfaceSommet<Integer> s1=Factory.sommet(1);
		InterfaceSommet<Integer> s2=Factory.sommet(2);
		InterfaceSommet<Integer> s3=Factory.sommet(3);
		InterfaceSommet<Integer> s4=Factory.sommet(4);
		System.out.println("Un sommet : "+s3);
		
		InterfaceArc<Integer>a12=Factory.arcNonValue(s1,s2);
		InterfaceArc<Integer>a23=Factory.arcNonValue(s2,s3);
		InterfaceArc<Integer>a32=Factory.arcNonValue(s3,s2);
		InterfaceArc<Integer>a34=Factory.arcNonValue(s3,s4);

		System.out.println("Un arc : "+a34);
		
		InterfaceEnsembleSommet<Integer> X;
		X=Factory.ensembleSommet();
		X.ajouteSommet(s1);X.ajouteSommet(s2);X.ajouteSommet(s3);X.ajouteSommet(s4);
		System.out.println("Un ensemble de sommets : "+X);
		
		InterfaceEnsembleArcNonValue<Integer>Gamma= Factory.ensembleArcNonValue();
		Gamma.ajouteArc(a12);Gamma.ajouteArc(a23);Gamma.ajouteArc(a32);Gamma.ajouteArc(a34);
		System.out.println("Un ensemble d'arcs : "+Gamma);
		
		InterfaceGrapheNonValue<Integer> G = Factory.grapheNonValue(X,Gamma); 
		System.out.println("Un graphe : "+G.toString());
		
		
		
		//Opération élémentaires
		G.getX();//Obtenir l'ensemble de sommet du graphe
		G.getGamma();//Obtenir l'ensemble d'arc du graphe
		G.existeSommet(s4);//Savoir si le sommet est présent dans le graphe
		G.existeArc(a32);//Savoir si l'arc est présent dans le graphe
		G.listPred(s2);//Obtenir une liste de prédécesseurs d'un sommet
		G.listSucc(s3);//Obtenir une liste des successeurs d'un sommet
		G.existeBoucle();//Savoir si il existe une boucle dans le graphe(ATTENTION ici on ne tient pas compte de la fermeture transitive)
		G.existeBoucle(s3);//Savoir si le existe une boucle au niveau d'un sommet (ATTENTION ici on ne tient pas compte de la fermeture transitive)
		
		//Appels aux algorithmes
		Parcours.DFS(G);//Juste un parcours du graphe (ne fait absolument rien)
		Parcours.WFS(G,s1);//Pareil	
		System.out.println();
		//System.out.println("Le graphe composé de G (arcs en deux temps) : "+FermetureTransitive.Composition(G, G));//En commentaire tant que composition est privé

		System.out.println("Fermeture transitive par puissance de Graphe : "+FermetureTransitive.PuissanceDeGraphe(G));

		System.out.println("Fermeture transitive par Roy-Warshall : "+FermetureTransitive.Roy_Warshall(G));
		System.out.println("Fermeture anti-transitive par Tau-Minalité (ne marche pas si circuit) : "+AntiTransitif.TauMinalite(G));
		System.out.println("Existence d'un circuit par Roy-Warshall: "+DetectionCircuit.Roy_Warshall(G));
		System.out.println("Existence d'un circuit par Marimont par points d'entrée : "+DetectionCircuit.MarimontEntree(G));
		System.out.println("Existence d'un circuit par Marimont par points de sortie : "+DetectionCircuit.MarimontSortie(G));
		System.out.println("Calcul des CFC grâce à l'algorithme de Foulkes : "+CalculCFC.Foulkes(FermetureTransitive.Roy_Warshall(G)));
	}
}