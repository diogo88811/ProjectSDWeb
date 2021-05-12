package hey.action;
import com.opensymphony.xwork2.ActionSupport;
import hey.model.ServerRmiBean;
import org.apache.struts2.interceptor.SessionAware;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Map;

public class registerPersonAction extends ActionSupport implements SessionAware {
    private static final long serialVersionUID = 4L;
    private Map<String, Object> session;
    private String username = null; // username and password supplied by the user
    private String password = null;
    private String ccnumber = null;
    private String phone = null;
    private String address = null ;
    private String work= null;
    private String CCval = null;
    private String department = null;
    private String typePerson = null;
    @Override
    public String execute() throws IOException {
        if(this.username != null && !username.equals("") && !password.equals("")){
            this.getHeyBean().setUsername(this.username);
            this.getHeyBean().setPassword(this.password);
            this.getHeyBean().setCcNumber(this.ccnumber);
        }
        else{
            return ERROR;
        }
        if(this.phone != null && !address.equals("") && !work.equals("")){
            this.getHeyBean().setPhone(this.phone);
            this.getHeyBean().setAddress(this.address);
            this.getHeyBean().setWork(this.work);
        }
        else{
            return ERROR;
        }
        if(this.CCval != null && !department.equals("") && !typePerson.equals("")){
            this.getHeyBean().setCCval(this.CCval);
            this.getHeyBean().setDepartment(this.department);
            this.getHeyBean().setTypePerson(this.typePerson);
        }
        else{
            return ERROR;
        }
        this.getHeyBean().registerUsers();
        return SUCCESS;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCcnumber(String ccnumber) {
        this.ccnumber = ccnumber;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public void setCCval(String CCval) {
        this.CCval = CCval;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setTypePerson(String typePerson) {
        this.typePerson = typePerson;
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
