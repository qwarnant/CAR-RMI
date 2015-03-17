package fr.univ.lille1.rmi.main;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

import fr.univ.lille1.rmi.remote.SiteImpl;
import fr.univ.lille1.rmi.remote.SiteInterface;

public class Main {

	private static Scanner scanner;
	private static SiteInterface root;

	public static void main(String[] args) {

		try {
			initSiteTree();
		} catch (Exception e) {
			System.out.println("Erreur pendant l'initialisation de l'arbre : "
					+ e.getMessage());
		}

		while (true) {
			System.out.println("Numéro du niveau pour propager le message : ");
			int diffuseLevel = scanner.nextInt();

			System.out.println("Message à propager dans l'arbre: ");
			String diffuseMessage = scanner.nextLine();

			try {
				diffuseMessageInTree(diffuseMessage, diffuseLevel);
			} catch (Exception e) {
				System.out
						.println("Erreur pendant la propagation du message : "
								+ e.getMessage());
			}
		}

	}

	private static void initSiteTree() throws RemoteException,
			MalformedURLException, NotBoundException {

		// Racine
		root = new SiteImpl(1);
		Naming.rebind("//localhost/" + root.toString(), root);
		SiteInterface remoteRoot = (SiteInterface) Naming.lookup("//localhost/"
				+ root.toString());

		// Fils
		SiteImpl fils1 = new SiteImpl(2);
		SiteImpl fils2 = new SiteImpl(3);
		SiteImpl fils3 = new SiteImpl(4);
		SiteImpl fils4 = new SiteImpl(5);
		SiteImpl fils5 = new SiteImpl(6);

		Naming.rebind("//localhost/" + fils1.toString(), fils1);
		Naming.rebind("//localhost/" + fils2.toString(), fils2);
		Naming.rebind("//localhost/" + fils3.toString(), fils3);
		Naming.rebind("//localhost/" + fils3.toString(), fils4);
		Naming.rebind("//localhost/" + fils3.toString(), fils5);

		SiteInterface remoteFils1 = (SiteInterface) Naming
				.lookup("//localhost/" + fils1.toString());
		SiteInterface remoteFils2 = (SiteInterface) Naming
				.lookup("//localhost/" + fils2.toString());
		SiteInterface remoteFils3 = (SiteInterface) Naming
				.lookup("//localhost/" + fils3.toString());
		SiteInterface remoteFils4 = (SiteInterface) Naming
				.lookup("//localhost/" + fils4.toString());
		SiteInterface remoteFils5 = (SiteInterface) Naming
				.lookup("//localhost/" + fils5.toString());

		fils1.setPere(remoteRoot);
		fils2.setPere(remoteRoot);

		remoteRoot.addFils(fils1);

	}

	private static void diffuseMessageInTree(String message, int level)
			throws RemoteException {
		SiteInterface temp = null;
		
		
		if(level == 1) {
			root.diffuse(message);
		}
		
		do {
			
			
			
		} while(temp != null);

	}

}
