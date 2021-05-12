package hey.model;

import java.io.IOException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import rmiserver.InterfaceServerRMI;
import rmiserver.Eleicao;
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
	private String nameElection;
	private String initDate;
	private String endDate;
	private String publicTarget;
	private String nameList;
	private String principalCandidate;
	private String electionSelected;
	private ArrayList<String> personName = new ArrayList<String>();
	private ArrayList<String> electionName = new ArrayList<String>();
	private ArrayList<Eleicao> elections = new ArrayList<Eleicao>();
	private ArrayList<Pessoa> listParticipants = new ArrayList<Pessoa>();
	private String typePerson;
	private String newElectionName;
	private String newInitDateElection;
	private String newEndDateElection;

	public ServerRmiBean() {
		try {
			server = (InterfaceServerRMI) Naming.lookup("//localhost:7000/RMIServer");
		}
		catch(NotBoundException|MalformedURLException|RemoteException e) {
			e.printStackTrace(); // what happens *after* we reach this line?
		}
	}

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

	public void registerUsers() throws IOException {
		Pessoa pessoa = new Pessoa();
		pessoa.RegisterPerson(this.username,this.phone,this.address,this.ccnumber,this.CCval,this.work,this.department,this.password,this.typePerson);
		server.print_on_server(pessoa.toString());
		server.SaveRegistry(pessoa);
	}

	public void createElection() throws  IOException {
		server.print_on_server("Eleicao Criada com Sucesso !");
		/*for(int i = 0; i <server.getEleicoes().size(); i++){
			if(electionName.equals(server.getEleicoes().get(i).getNome())){
				elections.add(server.getEleicoes().get(i));
			}
		}*/
	}

	/*public void addPersonByNameToList(String personNome) throws IOException{
		for(int i = 0; i <server.getPerson().size(); i++){
			if(personNome.equals(server.getPerson().get(i).getNome())){
				listParticipants.add(server.getPerson().get(i));
			}
		}
	}*/

	public void createList() throws  IOException {
		server.print_on_server("Lista Criada com Sucesso !");
	}

	public void updateElection(String eleicao, String nome, String init, String end){
		for(int i=0; i<elections.size(); i++){
			System.out.println(elections.get(i).getNome() + " = " + eleicao);
			if(elections.get(i).getNome().equals(eleicao)){
				if(!nome.equals("")){
					elections.get(i).setNome(nome);
				}
				if(!init.equals("")){
					elections.get(i).setDataInicio(init);
				}
				if(!end.equals("")){
					elections.get(i).setDataFim(end);
				}
			}
		}
	}

	public ArrayList<String> getUsers() throws IOException{
		personName.clear();
		for(int i = 0; i<server.getPerson().size(); i++){
			personName.add(server.getPerson().get(i).getNome());
		}
		return personName;
	}

	public ArrayList<String> getElection() throws IOException{
		electionName.clear();
		for(int i = 0; i<server.getEleicoes().size(); i++){
			electionName.add(server.getEleicoes().get(i).getNome());
		}
		return electionName;
	}



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

	public void setElections(ArrayList<Eleicao> elections) {
		this.elections = elections;
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
}
