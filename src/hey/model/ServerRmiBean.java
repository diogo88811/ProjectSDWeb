/**
 * Raul Barbosa 2014-11-07
 */
package hey.model;

import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import rmiserver.InterfaceServerRMI;

public class ServerRmiBean {
	private InterfaceServerRMI server;
	private String username; // username and password supplied by the user
	private String password;
	private String ccnumber;

	public ServerRmiBean() {
		try {
			server = (InterfaceServerRMI) Naming.lookup("//localhost:7000/RMIServer");
		}
		catch(NotBoundException|MalformedURLException|RemoteException e) {
			e.printStackTrace(); // what happens *after* we reach this line?
		}
	}

	public boolean verifyUsers() throws RemoteException {
		boolean flag =  server.verifyUser(this.username,this.ccnumber,this.password);
		if(flag){
			server.print_on_server("login done");
		}
		else{
			server.print_on_server("login wrong");
		}

		return flag;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setCcNumber(String ccnumber) {
		this.ccnumber = ccnumber;
	}

}
