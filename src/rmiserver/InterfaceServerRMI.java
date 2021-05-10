/**
 * Raul Barbosa 2014-11-07
 */
package rmiserver;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface InterfaceServerRMI extends Remote {
	public boolean verifyUser(String nome, String ccNumber, String password) throws  RemoteException;
	public void print_on_server(String s) throws RemoteException;
	public void SaveRegistry(rmiserver.Pessoa string) throws RemoteException;
	public void createElection() throws RemoteException ;
	public ArrayList<rmiserver.Pessoa> getPerson() throws RemoteException;
}
