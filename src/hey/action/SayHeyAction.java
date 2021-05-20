package hey.action;

import com.opensymphony.xwork2.ActionSupport;
import hey.model.ServerRmiBean;
import org.apache.struts2.interceptor.SessionAware;

import java.rmi.RemoteException;
import java.util.Map;

public class SayHeyAction extends ActionSupport implements SessionAware {
    private Map<String, Object> session;

    @Override
    public String execute() throws RemoteException {
        return SUCCESS;
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
