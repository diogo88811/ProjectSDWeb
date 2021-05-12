package hey.action;

import com.opensymphony.xwork2.ActionSupport;
import hey.model.ServerRmiBean;
import org.apache.struts2.interceptor.SessionAware;
import java.io.IOException;
import java.util.Map;

public class changeElectionPropertiesAction extends ActionSupport implements SessionAware {
    private static final long serialVersionUID = 4L;
    private Map<String, Object> session;
    private String changeElectionSelected = null;
    private String newElectionName = null;
    private String newInitDateElection = null;
    private String newEndDateElection = null;

    @Override
    public String execute() throws IOException {
        if(!changeElectionSelected.equals("")){
            this.getHeyBean().setNameList(this.changeElectionSelected);
        }
        else{
            return ERROR;
        }
        if(!newElectionName.equals("") && !newInitDateElection.equals("") && !newEndDateElection.equals("")){
            this.getHeyBean().setNewElectionName(this.newElectionName);
            this.getHeyBean().setNewInitDateElection(this.newInitDateElection);
            this.getHeyBean().setNewEndDateElection(this.newEndDateElection);
        }
        this.getHeyBean().updateElection(this.changeElectionSelected, this.newElectionName, this.newInitDateElection, this.newEndDateElection);
        return SUCCESS;
    }

    public void setChangeElectionSelected(String changeElectionSelected) {
        this.changeElectionSelected = changeElectionSelected;
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