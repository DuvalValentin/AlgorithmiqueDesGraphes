package exemple;

import java.util.logging.Logger;
import algorithme.*;
import factory.Factory;
import graphelements.interfaces.*;

public class ExempleUtilisation
{
	private ExempleUtilisation()
	{}

	private static final Logger logger=Logger.getAnonymousLogger();

	public static void main(String[] args)
	{
		// Opérations de créations :
		Sommet<Integer> s1=Factory.sommet(1);
		Sommet<Integer> s2=Factory.sommet(2);
		Sommet<Integer> s3=Factory.sommet(3);
		Sommet<Integer> s4=Factory.sommet(4);
		logger.info("Un sommet : "+s3);
		Arc<Integer> a12=Factory.arcNonValue(s1,s2);
		Arc<Integer> a23=Factory.arcNonValue(s2,s3);
		Arc<Integer> a32=Factory.arcNonValue(s3,s2);
		Arc<Integer> a34=Factory.arcNonValue(s3,s4);
		logger.info("Un arc : "+a34);
		EnsembleSommet<Integer> x;
		x=Factory.ensembleSommet();
		x.ajouteElement(s1);
		x.ajouteElement(s2);
		x.ajouteElement(s3);
		x.ajouteElement(s4);
		logger.info("Un ensemble de sommets : "+x);
		EnsembleArcNonValue<Integer> gamma=Factory.ensembleArcNonValue();
		gamma.ajouteElement(a12);
		gamma.ajouteElement(a23);
		gamma.ajouteElement(a32);
		gamma.ajouteElement(a34);
		logger.info("Un ensemble d'arcs : "+gamma);
		GrapheNonValue<Integer> G=Factory.grapheNonValue(x,gamma);
		logger.info("Un graphe : "+G.toString());
		// Opération élémentaires
		G.getEnsembleSommet();// Obtenir l'ensemble de sommet du graphe
		G.getGamma();// Obtenir l'ensemble d'arc du graphe
		G.existeSommet(s4);// Savoir si le sommet est présent dans le graphe
		G.existeArc(a32);// Savoir si l'arc est présent dans le graphe
		G.listPred(s2);// Obtenir une liste de prédécesseurs d'un sommet
		G.listSucc(s3);// Obtenir une liste des successeurs d'un sommet
		G.existeBoucle();// Savoir si il existe une boucle dans le graphe(ATTENTION ici on ne tient pas compte de la fermeture transitive)
		G.existeBoucle(s3);// Savoir si le existe une boucle au niveau d'un sommet (ATTENTION ici on ne tient pas compte de la fermeture transitive)
		// Appels aux algorithmes
		Parcours.dfs(G);// Juste un parcours du graphe (ne fait absolument rien)
		Parcours.wfs(G,s1);// Pareil
		// logger.info("Le graphe composé de G (arcs en deux temps) : "+FermetureTransitive.Composition(G, G));//En commentaire tant que composition est privé
		logger.info("Fermeture transitive par puissance de Graphe : "+FermetureTransitive.puissanceDeGraphe(G));
		logger.info("Fermeture transitive par Roy-Warshall : "+FermetureTransitive.royWarshall(G));
		logger.info("Fermeture anti-transitive par Tau-Minalité (ne marche pas si circuit) : "+AntiTransitif.tauMinalite(G));
		logger.info("Existence d'un circuit par Roy-Warshall: "+DetectionCircuit.royWarshall(G));
		logger.info("Existence d'un circuit par Marimont par points d'entrée : "+DetectionCircuit.marimontEntree(G));
		logger.info("Existence d'un circuit par Marimont par points de sortie : "+DetectionCircuit.marimontSortie(G));
		logger.info("Calcul des CFC grâce à l'algorithme de Foulkes : "+CalculCFC.foulkes(FermetureTransitive.royWarshall(G)));
	}
}