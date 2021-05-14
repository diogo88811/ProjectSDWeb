package hey.action;

import com.opensymphony.xwork2.ActionSupport;
import hey.model.ServerRmiBean;
import org.apache.struts2.interceptor.SessionAware;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class selectListToChangeAction extends ActionSupport implements SessionAware {
    private static final long serialVersionUID = 4L;
    private Map<String, Object> session;
    private String listSelectedToChange = null;

    @Override
    public String execute() throws IOException {
        if( !listSelectedToChange.equals("") ){
            this.getHeyBean().setListSelectedToChange(this.listSelectedToChange);
        }
        else{
            return ERROR;
        }
        System.out.println("ola");
        return SUCCESS;
    }

    public void setListSelectedToChange(String listSelectedToChange) {
        this.listSelectedToChange = listSelectedToChange;
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