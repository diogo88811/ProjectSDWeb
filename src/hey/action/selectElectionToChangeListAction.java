package hey.action;

import com.opensymphony.xwork2.ActionSupport;
import hey.model.ServerRmiBean;
import org.apache.struts2.interceptor.SessionAware;
import java.io.IOException;
import java.util.Map;

public class selectElectionToChangeListAction extends ActionSupport implements SessionAware {
    private static final long serialVersionUID = 4L;
    private Map<String, Object> session;
    private String electionSelectedToChangeList = null;

    @Override
    public String execute() throws IOException {
        if (!electionSelectedToChangeList.equals("")) {
            this.getHeyBean().setElectionSelectedToChangeList(this.electionSelectedToChangeList);
        } else {
            return ERROR;
        }
        return SUCCESS;
    }

    public void setElectionSelectedToChangeList(String electionSelectedToChangeList) {
        this.electionSelectedToChangeList = electionSelectedToChangeList;
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