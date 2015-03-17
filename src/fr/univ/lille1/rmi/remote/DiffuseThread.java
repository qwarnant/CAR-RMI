package fr.univ.lille1.rmi.remote;

import java.rmi.RemoteException;

public class DiffuseThread extends Thread {

	private String message;
	private SiteInterface site;

	public DiffuseThread(SiteInterface site, String message) {
		this.message = message;
		this.site = site;
	}

	@Override
	public void run() {

		for (SiteInterface site : this.site.getFils()) {
			try {
				site.diffuse(message);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}

	}

}
