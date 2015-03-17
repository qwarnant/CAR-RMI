package fr.univ.lille1.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface SiteInterface extends Remote{
	void setPere(SiteInterface pere);
	boolean addFils(SiteInterface fils);
	boolean removeFils(SiteInterface fils);
	List<SiteInterface> getFils();
	
	boolean diffuse(final String message) throws RemoteException;
}
