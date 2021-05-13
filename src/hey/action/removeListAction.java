package hey.action;

import com.opensymphony.xwork2.ActionSupport;
import hey.model.ServerRmiBean;
import org.apache.struts2.interceptor.SessionAware;
import java.io.IOException;
import java.util.Map;

public class removeListAction extends ActionSupport implements SessionAware {
    private static final long serialVersionUID = 4L;
    private Map<String, Object> session;
    private String listSelected = null;

    @Override
    public String execute() throws IOException {
        if( !listSelected.equals("")){
            this.getHeyBean().setListSelected(this.listSelected);
        }
        else{
            return ERROR;
        }
        System.out.println("--" + this.listSelected);
        this.getHeyBean().removeElection(this.listSelected);
        return SUCCESS;
    }

    public void setListSelected(String listSelected) {
        this.listSelected = listSelected;
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