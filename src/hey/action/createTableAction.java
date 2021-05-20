package hey.action;
import com.opensymphony.xwork2.ActionSupport;
import hey.model.ServerRmiBean;
import org.apache.struts2.interceptor.SessionAware;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Map;

public class createTableAction extends ActionSupport implements SessionAware {
    private static final long serialVersionUID = 4L;
    private Map<String, Object> session;
    private String tableLocal = null;
    private String ip = null;

    @Override
    public String execute() throws IOException {
        if(!tableLocal.equals("") && !ip.equals("") ){
            this.getHeyBean().setTableLocal(this.tableLocal);
            this.getHeyBean().setIp(this.ip);
        }
        else{
            return ERROR;
        }
        this.getHeyBean().createTable(this.tableLocal, this.ip);
        return SUCCESS;
    }

    public void setTableLocal(String tableLocal) {
        this.tableLocal = tableLocal;
    }

    public void setIp(String ip) {
        this.ip = ip;
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
