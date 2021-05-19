package rmiserver;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.util.ArrayList;

public interface InterfaceServerRMI extends Remote {
	public boolean verifyUser(String nome, String ccNumber, String password) throws  RemoteException;
	public void print_on_server(String s) throws RemoteException;
	public void SaveRegistry(rmiserver.Pessoa string) throws RemoteException;
	public ArrayList<rmiserver.Pessoa> getPerson() throws RemoteException;
	public ArrayList<rmiserver.Eleicao> getEleicoes() throws RemoteException;
	public void criarEleicao(rmiserver.Eleicao eleicao) throws RemoteException;
	public void addListToElection(String nameElection, String listname, rmiserver.Pessoa p, ArrayList<String> participants) throws RemoteException;
	public void changeElection(String election, String nome, String initDate, String endDate) throws RemoteException;
	public void changeList(String election, String list, String name, String pricipalCandidate) throws RemoteException;
	public void removeElection(String election) throws RemoteException;
	public void removeList(String eleicao, String list) throws RemoteException;
	public void saveVotes(String eleicao, String lista) throws RemoteException;
	public void saveUserVote(String name, String ccNumber, String election) throws RemoteException;
	public void removePeopleFromList(String election, String list, ArrayList<String> removePeople) throws RemoteException;
	public void addPeopleToList(String election, String list, ArrayList<String> addPeople) throws RemoteException;
	public void saveVotedPlaceOnPeople(String name, String ccNumber, String table) throws RemoteException;
	public boolean stateOfElections(rmiserver.Eleicao eleicao, int option) throws RemoteException, ParseException;
}
