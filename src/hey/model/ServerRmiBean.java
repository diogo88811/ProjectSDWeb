/**
 * Raul Barbosa 2014-11-07
 */
package hey.model;

import java.io.IOException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import rmiserver.InterfaceServerRMI;
import rmiserver.Pessoa;

public class ServerRmiBean {
	private InterfaceServerRMI server;
	private String username; // username and password supplied by the user
	private String password;
	private String ccnumber;
	private String phone;
	private String address;
	private String work;
	private String CCval;
	private String department;

	public ServerRmiBean() {
		try {
			server = (InterfaceServerRMI) Naming.lookup("//localhost:7000/RMIServer");
		}
		catch(NotBoundException|MalformedURLException|RemoteException e) {
			e.printStackTrace(); // what happens *after* we reach this line?
		}
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

	public void setCCval(String CCval) {
		this.CCval = CCval;
	}

	public void setServer(InterfaceServerRMI server) {
		this.server = server;
	}

	public void setCcnumber(String ccnumber) {
		this.ccnumber = ccnumber;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setWork(String work) {
		this.work = work;
	}

	public void setDepartment(String department) {
		this.department = department;
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

	public void registerUsers() throws IOException {
		Pessoa pessoa = new Pessoa();
		pessoa.RegisterPerson(this.username,this.phone,this.address,this.ccnumber,this.CCval,this.work,this.department,this.password);
		server.print_on_server(pessoa.toString());
		server.SaveRegistry(pessoa);
	}
}
