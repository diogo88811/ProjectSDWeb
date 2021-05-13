package hey.action;

import com.opensymphony.xwork2.ActionSupport;
import hey.model.ServerRmiBean;
import org.apache.struts2.interceptor.SessionAware;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class infoListAction extends ActionSupport implements SessionAware {
    private static final long serialVersionUID = 4L;
    private Map<String, Object> session;
    private String nameList = null;
    private String principalCandidate = null;
    private String listParticipants = null;

    @Override
    public String execute() throws IOException {
        if(!nameList.equals("") && !principalCandidate.equals("") && !listParticipants.equals("")){
            this.getHeyBean().setNameList(this.nameList);
            this.getHeyBean().setPrincipalCandidate(this.principalCandidate);
            this.getHeyBean().setListParticipants(this.listParticipants);
        }
        else{
            return ERROR;
        }
        ArrayList<String> aux = new ArrayList<String>(Arrays.asList(listParticipants.split(",")));
        ArrayList<String> temp = new ArrayList<String>();
        for(int i = 0; i<aux.size(); i++){
            if(aux.get(i).charAt(0) == ' '){
                temp.add(aux.get(i).substring(1));
            }
            else{
                temp.add(aux.get(i));
            }
        }
        this.getHeyBean().createList(this.nameList, this.principalCandidate, temp);
        return SUCCESS;
    }

    public void setNameList(String nameList) {
        this.nameList = nameList;
    }

    public void setPrincipalCandidate(String principalCandidate) {
        this.principalCandidate = principalCandidate;
    }

    public void setListParticipants(String listParticipants) {
        this.listParticipants = listParticipants;
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
