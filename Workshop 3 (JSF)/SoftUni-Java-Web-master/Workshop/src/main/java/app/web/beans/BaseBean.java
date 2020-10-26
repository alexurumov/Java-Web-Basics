package app.web.beans;

import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.IOException;

public abstract class BaseBean  {

    protected void redirect(String url)  {
        try {
            FacesContext.getCurrentInstance()
                    .getExternalContext()
                    .redirect("/views" + url + ".jsf");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

