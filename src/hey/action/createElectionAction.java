package hey.action;
import com.opensymphony.xwork2.ActionSupport;
import hey.model.ServerRmiBean;
import org.apache.struts2.interceptor.SessionAware;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Map;

public class createElectionAction extends ActionSupport implements SessionAware {
    private static final long serialVersionUID = 4L;
    private Map<String, Object> session;
    private String nameElection = null; // username and password supplied by the user
    private String initDate = null;
    private String endDate = null;
    private String publicTarget = null;

    @Override
    public String execute() throws IOException {
        if(!nameElection.equals("") && !initDate.equals("") && !this.endDate.equals("") && !publicTarget.equals("")){
            this.getHeyBean().setNameElection(this.nameElection);
            this.getHeyBean().setInitDate(this.initDate);
            this.getHeyBean().setEndDate(this.endDate);
            this.getHeyBean().setPublicTarget(this.publicTarget);
        }
        else{
            return ERROR;
        }
        this.getHeyBean().createElection();
        return SUCCESS;
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
