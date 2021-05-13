package hey.action;

import com.opensymphony.xwork2.ActionSupport;
import hey.model.ServerRmiBean;
import org.apache.struts2.interceptor.SessionAware;
import java.io.IOException;
import java.util.Map;

public class selectElectionToRemoveListAction extends ActionSupport implements SessionAware {
    private static final long serialVersionUID = 4L;
    private Map<String, Object> session;
    private String electionSelectedToRemoveList = null;

    @Override
    public String execute() throws IOException {
        if(!electionSelectedToRemoveList.equals("")){
            this.getHeyBean().setElectionSelectedToRemoveList(this.electionSelectedToRemoveList);
        }
        else{
            return ERROR;
        }
        this.getHeyBean().removeElection(this.electionSelectedToRemoveList);
        return SUCCESS;
    }

    public void setElectionSelectedToRemoveList(String electionSelectedToRemoveList) {
        this.electionSelectedToRemoveList = electionSelectedToRemoveList;
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