/**
 * Raul Barbosa 2014-11-07
 */
package hey.action;

import com.opensymphony.xwork2.ActionSupport;
import hey.model.ServerRmiBean;
import org.apache.struts2.interceptor.SessionAware;

import java.rmi.RemoteException;
import java.util.Map;

public class LoginAction extends ActionSupport implements SessionAware {
	private static final long serialVersionUID = 4L;
	private Map<String, Object> session;
	private String username = null, password = null, ccnumber = null;

	@Override
	public String execute() throws RemoteException{
		// any username is accepted without confirmation (should check using RMI)
		if(this.username != null && !username.equals("") && !password.equals("")) {
			this.getHeyBean().setUsername(this.username);
			this.getHeyBean().setPassword(this.password);
			this.getHeyBean().setCcNumber(this.ccnumber);
			boolean correctPassword = this.getHeyBean().verifyUsers();
			if(correctPassword){
				session.put("username", username);
				session.put("loggedin", true); // this marks the user as logged in
				return SUCCESS;
			}
			return LOGIN;
		}
		else
			return LOGIN;
	}
	
	public void setUsername(String username) {
		this.username = username; // will you sanitize this input? maybe use a prepared statement?
	}

	public void setPassword(String password) {
		this.password = password; // what about this input? 
	}

	public void setCcnumber(String ccnumber) {
		this.ccnumber = ccnumber; // what about this input?
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
