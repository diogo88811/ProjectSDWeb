package hey.action;

import com.opensymphony.xwork2.ActionSupport;
import hey.model.ServerRmiBean;
import org.apache.struts2.interceptor.SessionAware;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class changeListAction extends ActionSupport implements SessionAware {
    private static final long serialVersionUID = 4L;
    private Map<String, Object> session;
    private String newListName = null;
    private String changePrincipalCandidate = null;
    private String addPeopleToList = null;
    private String deletePeopleFromList = null;

    @Override
    public String execute() throws IOException {
        if(!this.newListName.equals("")){
            this.getHeyBean().setNewListName(this.newListName);
            this.getHeyBean().updateList(this.newListName, "");
        }
        else{
            return ERROR;
        }
        if(!this.changePrincipalCandidate.equals("")){
            this.getHeyBean().setChangePrincipalCandidate(this.changePrincipalCandidate);
            this.getHeyBean().updateList("", this.changePrincipalCandidate);
        }
        else{
            return ERROR;
        }
        if( !this.addPeopleToList.equals("")) {
            this.getHeyBean().setAddPeopleToList(this.addPeopleToList);
            ArrayList<String> aux2 = new ArrayList<String>(Arrays.asList(addPeopleToList.split(",")));
            ArrayList<String> temp2 = new ArrayList<String>();
            for (int i = 0; i < aux2.size(); i++) {
                if (aux2.get(i).charAt(0) == ' ') {
                    temp2.add(aux2.get(i).substring(1));
                } else {
                    temp2.add(aux2.get(i));
                }
            }
            this.getHeyBean().addPeopleToList(temp2);
        }
        else{
            return ERROR;
        }
        if( !this.deletePeopleFromList.equals("")) {
            this.getHeyBean().setDeletePeopleFromList(this.deletePeopleFromList);
            ArrayList<String> aux3 = new ArrayList<String>(Arrays.asList(deletePeopleFromList.split(",")));
            ArrayList<String> temp3 = new ArrayList<String>();
            for (int i = 0; i < aux3.size(); i++) {
                if (aux3.get(i).charAt(0) == ' ') {
                    temp3.add(aux3.get(i).substring(1));
                } else {
                    temp3.add(aux3.get(i));
                }
            }
            this.getHeyBean().removePeopleFromList(temp3);
        }
        else{
            return ERROR;
        }
        return SUCCESS;
    }

    public void setNewListName(String newListName) {
        this.newListName = newListName;
    }

    public void setChangePrincipalCandidate(String changePrincipalCandidate) {
        this.changePrincipalCandidate = changePrincipalCandidate;
    }

    public void setAddPeopleToList(String addPeopleToList) {
        this.addPeopleToList = addPeopleToList;
    }

    public void setDeletePeopleFromList(String deletePeopleFromList) {
        this.deletePeopleFromList = deletePeopleFromList;
    }

    public ServerRmiBean getHeyBean() throws RemoteException {
        if(!session.containsKey("heyBean"))
            this.setHeyBean(new ServerRmiBean());
        return (ServerRmiBean) session.get("heyBean");
    }

    public void setHeyBean(ServerRmiBean serverRmiBean) {
        this.session.put("heyBean", serverRmiBean);
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }
}