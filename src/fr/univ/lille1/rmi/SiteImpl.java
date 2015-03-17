package fr.univ.lille1.rmi;

import java.rmi.RemoteException;
import java.rmi.server.RemoteObject;
import java.util.ArrayList;
import java.util.List;

public class SiteImpl extends RemoteObject implements SiteInterface {

	private static final long serialVersionUID = 1L;
	public static final int MAX_FILS = 3;

	private SiteInterface pere;
	private List<SiteInterface> fils;

	public SiteImpl() {
		this.fils = new ArrayList<SiteInterface>();
		this.pere = null;
	}

	@Override
	public void setPere(SiteInterface pere) {
		if (this.pere != null)
			return;
		this.pere = pere;
	}

	@Override
	public boolean addFils(SiteInterface fils) {
		if (this.fils.size() == MAX_FILS) {
			return false;
		}
		return this.fils.add(fils);
	}

	@Override
	public boolean removeFils(SiteInterface fils) {
		return this.fils.remove(fils);
	}
	
	@Override
	public List<SiteInterface> getFils() {
		return this.fils;
	}

	@Override
	public boolean diffuse(String message) throws RemoteException {
		if (message == null)
			return false;

		DiffuseThread dThread = new DiffuseThread(this, message);
		dThread.start();

		return true;
	}

}
