package hey.model;

import java.io.IOException;
import java.lang.reflect.Array;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.text.ParseException;
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
	private boolean isAdmin = false;
	private String phone;
	private String address;
	private String work;
	private String CCval;
	private String department;
	private String userSelected;
	private String tableLocal;
	private String ip;

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
	private String electionSelectedToVote = null;

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
	private ArrayList<String> listsElectionToChange = new ArrayList<String>();


	public ServerRmiBean() {
		try {
			server = (InterfaceServerRMI) Naming.lookup("//localhost:7000/RMIServer");
			System.out.println("criado");
		}
		catch(NotBoundException|MalformedURLException|RemoteException e) {
			e.printStackTrace(); // what happens *after* we reach this line?
		}
	}

	//Verifica o tipo de utilizador(User/Admin)
	public String verifyUsers() throws RemoteException {
		this.electionSelectedToVote = null;
		boolean flag =  server.verifyUser(this.username,this.ccnumber,this.password);
		for(int i = 0; i < server.getPerson().size();i++){
			if(server.getPerson().get(i).getNome().equals(this.username) && server.getPerson().get(i).getCCnumber().equals(this.ccnumber
			)){
				if(server.getPerson().get(i).getTypePerson().equals("ADMIN")){
					isAdmin = true;
					return "ADMIN";
				}
				else{
					isAdmin = false;
					return "USER";
				}
			}
		}
		return "none";
	}

	public ArrayList<String> getUserinfo() throws IOException{
		ArrayList<String> result = new ArrayList<String>();
		Pessoa a = new Pessoa();
		for(int i = 0; i<server.getPerson().size(); i++){
			if(server.getPerson().get(i).getNome().equals(userSelected)){
				a = server.getPerson().get(i);
			}
		}
		String nome = "Nome: " + a.getNome();
		result.add(nome);
		String cc = "CCNumber: " + a.getCCnumber();
		result.add(cc);
		String job = "Job: " + a.getTrabalho();
		result.add(job);
		String locais = "Locais onde votou:";
		result.add(locais);
		for(int i = 0; i<a.getTables().size(); i++){
			String aux = a.getTables().get(i);
			result.add(aux);
		}
		return result;
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

	public void createTable(String nome, String ip) throws IOException {
		server.createTable(nome, ip);
	}

	public ArrayList<String> getHello() throws RemoteException, ParseException {
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
						if(server.stateOfElections(server.getEleicoes().get(i),2)){
							auxElections.add((server.getEleicoes().get(i).getNome()));
						}

					}
				}
				else{
					if(server.stateOfElections(server.getEleicoes().get(i),2)){
						auxElections.add((server.getEleicoes().get(i).getNome()));
					}

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
				if(server.getEleicoes().get(i).getListas() != null) {
					for (int j = 0; j < server.getEleicoes().get(i).getListas().size(); j++) {
						auxList.add(server.getEleicoes().get(i).getListas().get(j).getNomeLista());
					}
				}
			}
		}
		return auxList;
	}

	public ArrayList<String> getResult() throws RemoteException {
		ArrayList<String> aux = new ArrayList<String>();
		String auxS = "";
		for(int i = 0; i < server.getEleicoes().size();i++){
			if(server.getEleicoes().get(i).getNome().equals(this.electionSelected)) {
				for (int j = 0; j < server.getEleicoes().get(i).getListas().size(); j++) {
					auxS = server.getEleicoes().get(i).getListas().get(j).getNomeLista();
					auxS += " " + String.valueOf(server.getEleicoes().get(i).getListas().get(j).getNumVotes());
					aux.add(auxS);
				}
			}
		}
		return aux;

	}

	public void savedVote() throws RemoteException {
		server.saveVotes(this.electionSelectedToVote,this.listSelectedToVote);
		System.out.println("---_> "+this.username+"   ccnumber "+this.ccnumber+ "  eleciao "+electionSelectedToVote);
		//server.saveVotedPlaceOnPeople(this.username, this.ccnumber, "Eleicao: " + electionSelectedToVote + " Local : Web");
	}

	public void peopleWhoVoted() throws RemoteException {
		server.saveUserVote(username,ccnumber,this.electionSelectedToVote);
		electionSelectedToVote = null;
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
	public ArrayList<String> getElection() throws IOException, ParseException {
		electionName.clear();
		for(int i = 0; i<server.getEleicoes().size(); i++){
			if(server.stateOfElections(server.getEleicoes().get(i),0)){
				electionName.add(server.getEleicoes().get(i).getNome());
			}

		}
		return electionName;
	}

	public ArrayList<String> getElection1() throws IOException, ParseException {
		electionName.clear();
		for(int i = 0; i<server.getEleicoes().size(); i++){
			if(server.stateOfElections(server.getEleicoes().get(i),1)){
				electionName.add(server.getEleicoes().get(i).getNome());
			}
		}
		return electionName;
	}

	//Retorna as Listas
	public ArrayList<String> getLists() throws IOException{
		ArrayList<String> listsElection = new ArrayList<String>();
		for(int i = 0; i<server.getEleicoes().size(); i++){
			if(server.getEleicoes().get(i).getNome().equals(electionSelected)){
				if(server.getEleicoes().get(i).getListas() != null) {
					for (int j = 0; j < server.getEleicoes().get(i).listas.size(); j++) {
						listsElection.add(server.getEleicoes().get(i).listas.get(j).getNomeLista());
					}
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

	//Retorna as Pessoas que podem ser removidas de uma lista
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

	//Retorna dados da eleicao selecionada
	public ArrayList<String> getelectiondata() throws RemoteException{
		ArrayList<String> dados = new ArrayList<String>();
		Eleicao eleicao = new Eleicao();
		for(int i = 0; i<server.getEleicoes().size(); i++){
			if(server.getEleicoes().get(i).getNome().equals(this.electionSelected)){
				eleicao = server.getEleicoes().get(i);
			}
		}
		String nome = "Nome Eleicao: " + eleicao.getNome();
		dados.add(nome);
		String init = "Data Inicio: " + eleicao.DataInicio;
		dados.add(init);
		String end = "Data Fim: " + eleicao.DataFim;
		dados.add(end);
		String target = "Público Alvo: " + eleicao.getPublicoAlvo();
		dados.add(target);
		String listas = "Listas:";
		dados.add(listas);
		for(int i = 0; i<eleicao.listas.size(); i++){
			dados.add(eleicao.listas.get(i).getNomeLista());
		}
		return dados;
	}

	//funcao para dar os dados em tempo real
	public String realTimeData() throws RemoteException {
		String aux = "";
		for(int i = 0; i < server.getEleicoes().size();i++){
			if(server.getEleicoes().get(i).getNome().equals(electionSelectedToVote)){
				aux = server.getEleicoes().get(i).getPublicoAlvo() + " Pessoas que votaram na eleicao:" ;
				for(int j = 0; j < server.getEleicoes().get(i).getpeopleWhoVoted().size(); j++){
					aux +=  "- "+server.getEleicoes().get(i).getpeopleWhoVoted().get(j).getNome() + "\n";
				}
			}
		}

		return aux;
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

	public String getUsername() {
		return username;
	}

	public boolean getIsAdmin() {
		return isAdmin;
	}

	public String getElectionSelectedToVote() {
		return electionSelectedToVote;
	}

	public void setUserSelected(String userSelected) {
		this.userSelected = userSelected;
	}

	public void setTableLocal(String tableLocal) {
		this.tableLocal = tableLocal;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
}

