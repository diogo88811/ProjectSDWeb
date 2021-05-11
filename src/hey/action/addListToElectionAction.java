package hey.action;

import com.opensymphony.xwork2.ActionSupport;
import hey.model.ServerRmiBean;
import org.apache.struts2.interceptor.SessionAware;

import java.io.IOException;
import java.util.Map;

public class addListToElectionAction extends ActionSupport implements SessionAware {
    private static final long serialVersionUID = 4L;
    private Map<String, Object> session;
    private String nameList = null;
    private String principalCandidate = null;
    private String electionSelected = null;

    @Override
    public String execute() throws IOException {
        System.out.println("selecionei: " + this.electionSelected);
        if(!nameList.equals("") && !principalCandidate.equals("") && !electionSelected.equals("")){
            this.getHeyBean().setNameList(this.nameList);
            this.getHeyBean().setPrincipalCandidate(this.principalCandidate);
            this.getHeyBean().setElectionSelected(this.electionSelected);
            System.out.println("selecionei: " + this.electionSelected);
        }
        else{
            return ERROR;
        }
        this.getHeyBean().createList();
        return SUCCESS;
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

    public ServerRmiBean getHeyBean() {
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

