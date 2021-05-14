package hey.model;

import java.io.IOException;
import java.lang.reflect.Array;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import rmiserver.InterfaceServerRMI;
import rmiserver.Eleicao;
import rmiserver.Pessoa;
import rmiserver.Lista;

public class ServerRmiBean {
	private InterfaceServerRMI server;
	//Create Person Variables
	private String username;
	private String password;
	private String ccnumber;
	private String phone;
	private String address;
	private String work;
	private String CCval;
	private String department;

	//Create Election Variables
	private String nameElection;
	private String initDate;
	private String endDate;
	private String publicTarget;

	//Create List Variables
	private String nameList;
	private String principalCandidate;
	private String electionSelected;

	//Change Election Variables
	private String changeElectionSelected;
	private String typePerson;
	private String newElectionName;
	private String newInitDateElection;
	private String newEndDateElection;
	private String listParticipants;
	private String electionSelectedToVote;

	//Remove List Variables
	private String electionSelectedToRemoveList;
	private String listSelected;

	//Change List Variables
	private String electionSelectedToChangeList;
	private String listSelectedToChange;
	private String newListName;
	private String  listSelectedToVote;
	private String changePrincipalCandidate;
	private String addPeopleToList;
	private String deletePeopleFromList;

	private ArrayList<String> personName = new ArrayList<String>();
	private ArrayList<String> electionName = new ArrayList<String>();
	private ArrayList<String> listsElection = new ArrayList<String>();
	private ArrayList<String> listsElectionToChange = new ArrayList<String>();


	public ServerRmiBean() {
		try {
			server = (InterfaceServerRMI) Naming.lookup("//localhost:7000/RMIServer");
		}
		catch(NotBoundException|MalformedURLException|RemoteException e) {
			e.printStackTrace(); // what happens *after* we reach this line?
		}
	}

	//Verifica o tipo de utilizador(User/Admin)
	public String verifyUsers() throws RemoteException {
		boolean flag =  server.verifyUser(this.username,this.ccnumber,this.password);
		for(int i = 0; i < server.getPerson().size();i++){
			if(server.getPerson().get(i).getNome().equals(this.username) && server.getPerson().get(i).getCCnumber().equals(this.ccnumber
			)){
				if(server.getPerson().get(i).getTypePerson().equals("ADMIN")){
					return "ADMIN";
				}
				else{
					return "USER";
				}
			}
		}
		return "none";
	}

	//Cria um novo user ou admin
	public void registerUsers(String nome, String password, String job, String telemovel, String morada, String CCnumber, String CCVal, String departamento, String typePerson) throws IOException {
		Pessoa pessoa = new Pessoa(nome, password, job, telemovel, morada, CCnumber, CCVal, departamento, null, typePerson);
		server.SaveRegistry(pessoa);
		server.print_on_server("Pessoa Criada com Sucesso !");
	}

	//Cria uma eleição e adiciona ao array elections
	public void createElection(String name, String dateInit, String dateEnd, String publicTarget) throws  IOException {
		Eleicao eleicao = new Eleicao(name, dateInit, dateEnd, publicTarget, 0, null, null);
		server.criarEleicao(eleicao);
		server.print_on_server("Eleicao Criada com Sucesso !");
	}

	//Cria uma Lista
	public void createList(String listName, String person, ArrayList<String> participants) throws  IOException {
		Pessoa p = new Pessoa();
		for(int i = 0; i < server.getPerson().size(); i++){
			if(server.getPerson().get(i).getNome().equals(person)){
				p = server.getPerson().get(i);
			}
		}

		String aux = getElectionSelected();
		server.addListToElection(aux, listName, p, participants);
	}

	//Modifica os parametros de uma Eleição
	public void updateElection(String nome, String init, String end) throws RemoteException {
		System.out.println("entrei");
		for(int i=0; i<server.getEleicoes().size(); i++){
			if(server.getEleicoes().get(i).getNome().equals(this.electionSelected)){
				System.out.println("aqui");
				server.changeElection(this.electionSelected, nome, init, end);
			}
		}
	}

	//Modifica Lista
	public void updateList(String nome, String principalCandidate) throws RemoteException {
		server.changeList(this.electionSelected, this.listSelectedToChange, nome, principalCandidate);
	}

	//Remove Pessoas de uma Lista
	public void removePeopleFromList(ArrayList<String> removePeople) throws RemoteException {
		server.removePeopleFromList(this.electionSelected, this.listSelectedToChange, removePeople);
	}

	//Adiciona Pessoas a uma Lista
	public void addPeopleToList(ArrayList<String> addPeople) throws RemoteException{
		server.addPeopleToList(this.electionSelected, this.listSelectedToChange, addPeople);
	}

	public ArrayList<String> getHello() throws RemoteException {
		String aux = null;
		Pessoa pessoa = null;
		int flag = 0;
		ArrayList<String> auxElections = new ArrayList<String>();
		for(int i = 0; i < server.getPerson().size();i++){
			if(server.getPerson().get(i).getNome().equals(username)){
				aux =  server.getPerson().get(i).getTrabalho();
			}
		}
		for(int i = 0; i < server.getEleicoes().size(); i++){
			flag = 0;
			if(server.getEleicoes().get(i).getPublicoAlvo().equals(aux)){
				if(server.getEleicoes().get(i).getpeopleWhoVoted() != null){
					for(int j = 0; j <server.getEleicoes().get(i).getpeopleWhoVoted().size(); j++){
						if(server.getEleicoes().get(i).getpeopleWhoVoted().get(j).getNome().equals(username)){
							flag = 1;
						}
					}
					if(flag == 0){
						auxElections.add((server.getEleicoes().get(i).getNome()));
					}
				}
				else{
					auxElections.add((server.getEleicoes().get(i).getNome()));
				}

			}
		}
		return auxElections;
	}

	public ArrayList<String> getListVote() throws RemoteException {
		String aux = null;
		ArrayList<String> auxList = new ArrayList<String>();
		for(int i = 0; i < server.getEleicoes().size(); i++){
			if(server.getEleicoes().get(i).getNome().equals(this.electionSelectedToVote)){
				for(int j = 0; j < server.getEleicoes().get(i).getListas().size(); j++){
					auxList.add(server.getEleicoes().get(i).getListas().get(j).getNomeLista());
				}
			}
		}
		return auxList;
	}

	public void savedVote() throws RemoteException {
		server.saveVotes(this.electionSelectedToVote,this.listSelectedToVote);
	}

	public void peopleWhoVoted() throws RemoteException {
		System.out.println("Hello foi aqui");
		server.saveUserVote(username,ccnumber,this.electionSelectedToVote);
		System.out.println("bye");
	}

	//Remove Eleição
	public void removeElection() throws RemoteException {
		server.removeElection(electionSelected);
	}

	//Remove Lista
	public void removeList() throws RemoteException{
		server.removeList(this.electionSelected, this.listSelected);
	}

	//Retorna para o ArrayList electionName as pessoas (tipo <Pessoa> para o tipo <String>)
	public ArrayList<String> getUsers() throws IOException{
		personName.clear();
		for(int i = 0; i<server.getPerson().size(); i++){
			personName.add(server.getPerson().get(i).getNome());
		}
		return personName;
	}

	//Retorna para o ArrayList electionName as eleicoes(tipo <Eleicao> para o tipo <String>)
	public ArrayList<String> getElection() throws IOException{
		electionName.clear();
		for(int i = 0; i<server.getEleicoes().size(); i++){
			electionName.add(server.getEleicoes().get(i).getNome());
		}
		return electionName;
	}

	//Retorna as Listas
	public ArrayList<String> getLists() throws IOException{
		listsElection.clear();
		for(int i = 0; i<server.getEleicoes().size(); i++){
			if(server.getEleicoes().get(i).getNome().equals(electionSelected)){
				for(int j = 0; j<server.getEleicoes().get(i).listas.size(); j++) {
					listsElection.add(server.getEleicoes().get(i).listas.get(j).getNomeLista());
				}
			}
		}
		return listsElection;
	}

	//Retorna pessoas que podem ser adicionadas a uma Lista
	public ArrayList<String> getaddpersontochangelist() throws IOException{


		ArrayList<String> addpersonToChangeList = new ArrayList<String>();
		Eleicao eleicao = new Eleicao();
		Lista lista = new Lista();

		for(int i = 0; i<server.getEleicoes().size(); i++) {
			if (server.getEleicoes().get(i).getNome().equals(electionSelected)) {
				eleicao = server.getEleicoes().get(i);
			}
		}

		for (int k = 0; k < eleicao.getListas().size(); k++) {
			if (eleicao.getListas().get(k).getNomeLista().equals(listSelectedToChange)) {
				lista = eleicao.getListas().get(k);
			}
		}

		for(int i = 0; i< lista.getPessoas().size(); i++){
			System.out.println( "-------- " + lista.getPessoas().get(i).getNome());
		}

		int flag = 0;

		for (int j = 0; j < server.getPerson().size(); j++) {
			if (server.getPerson().get(j).getTrabalho().equals(eleicao.getPublicoAlvo())) {
				for(int k = 0; k<lista.getPessoas().size(); k++){
					if(lista.getPessoas().get(k).getNome().equals(server.getPerson().get(j).getNome())){
						flag = 1;
					}
				}
				if(flag == 0){
					addpersonToChangeList.add(server.getPerson().get(j).getNome());
				}
				flag = 0;
			}
		}

		return addpersonToChangeList;
	}

	//Retorna as Pessoas que podem estar na eleicao(electionSelected)
	public ArrayList<String> getpeople() throws RemoteException {
		ArrayList<String> peopleToElection = new ArrayList<String>();
		for(int i = 0; i<server.getEleicoes().size(); i++){
			if(server.getEleicoes().get(i).getNome().equals(getElectionSelected())){
				for(int j=0; j<server.getPerson().size(); j++){
					if(server.getPerson().get(j).getTrabalho().equals(server.getEleicoes().get(i).getPublicoAlvo())){
						peopleToElection.add(server.getPerson().get(j).getNome());
					}
				}
			}
		}
		return peopleToElection;
	}

	public ArrayList<String> getdeletepersonfromlist() throws RemoteException{
		ArrayList<String> remove = new ArrayList<String>();
		Eleicao eleicao = new Eleicao();
		Lista lista = new Lista();

		for(int i = 0; i<server.getEleicoes().size(); i++) {
			if (server.getEleicoes().get(i).getNome().equals(electionSelected)) {
				eleicao = server.getEleicoes().get(i);
			}
		}

		for (int k = 0; k < eleicao.getListas().size(); k++) {
			if (eleicao.getListas().get(k).getNomeLista().equals(listSelectedToChange)) {
				lista = eleicao.getListas().get(k);
			}
		}

		int flag = 0;

		for (int j = 0; j < server.getPerson().size(); j++) {
			if (server.getPerson().get(j).getTrabalho().equals(eleicao.getPublicoAlvo())) {
				for(int k = 0; k<lista.getPessoas().size(); k++){
					if(lista.getPessoas().get(k).getNome().equals(server.getPerson().get(j).getNome())){
						flag = 1;
					}
				}
				if(flag == 1){
					remove.add(server.getPerson().get(j).getNome());
				}
				flag = 0;
			}
		}

		return remove;
	}






	//Set Functions
	public void setPersonName(ArrayList<String> personName) {
		this.personName = personName;
	}

	public void setElectionName(ArrayList<String> electionName) {
		this.electionName = electionName;
	}

	public void setCcnumber(String ccnumber) {
		this.ccnumber = ccnumber;
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

	public void setNameElection(String nameElection) {
		this.nameElection = nameElection;
	}

	public void setInitDate(String initDate) {
		this.initDate = initDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public void setPublicTarget(String publicTarget) {
		this.publicTarget = publicTarget;
	}

	public void setNameList(String nameList) {
		this.nameList = nameList;
	}

	public void setPrincipalCandidate(String principalCandidate) {
		this.principalCandidate = principalCandidate;
	}

	public void setElectionSelected(String electionSelected) {
		this.electionSelected = electionSelected;
	}

	public void setTypePerson(String typePerson) {
		this.typePerson = typePerson;
	}

	public void setNewElectionName(String newElectionName) {
		this.newElectionName = newElectionName;
	}

	public void setNewInitDateElection(String newInitDateElection) {
		this.newInitDateElection = newInitDateElection;
	}

	public void setNewEndDateElection(String newEndDateElection) {
		this.newEndDateElection = newEndDateElection;
	}

	public String getElectionSelected() {
		return electionSelected;
	}

	public void setListParticipants(String listParticipants) {
		this.listParticipants = listParticipants;
	}

	public void setListSelected(String listSelected) {
		this.listSelected = listSelected;
	}

	public void setElectionSelectedToRemoveList(String electionSelectedToRemoveList) {
		this.electionSelectedToRemoveList = electionSelectedToRemoveList;
	}

	public void setElectionSelectedToChangeList(String electionSelectedToChangeList) {
		this.electionSelectedToChangeList = electionSelectedToChangeList;
	}

	public void setListSelectedToChange(String listSelectedToChange) {
		this.listSelectedToChange = listSelectedToChange;
	}

	public void setNewListName(String newListName) {
		this.newListName = newListName;
	}

	public void setElectionSelectedToVote(String electionSelectedToVote) {
		this.electionSelectedToVote = electionSelectedToVote;
	}

	public void setListSelectedToVote(String listSelectedToVote) {
		this.listSelectedToVote = listSelectedToVote;
	}

	public void setAddPeopleToList(String addPeopleToList) {
		this.addPeopleToList = addPeopleToList;
	}

	public void setDeletePeopleFromList(String deletePeopleFromList) {
		this.deletePeopleFromList = deletePeopleFromList;
	}

	public void setChangeElectionSelected(String changeElectionSelected) {
		this.changeElectionSelected = changeElectionSelected;
	}

	public void setChangePrincipalCandidate(String changePrincipalCandidate) {
		this.changePrincipalCandidate = changePrincipalCandidate;
	}
}
