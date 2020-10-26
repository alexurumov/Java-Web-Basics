package app.web;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

@Named
@RequestScoped
public class LogoutBean extends BaseBean {

    public void logout(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        this.redirect("/index");
    }
}
