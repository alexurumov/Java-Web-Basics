package web;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@RequestScoped
public class HelloWorldBean implements Serializable {
    private String message;
    private List<String> list;

    public HelloWorldBean() {
        this.message = "Hello world!";
    }

    @PostConstruct
    public void init() {
        this.list = new ArrayList<>();
        list.add("Pesho");
        list.add("Gosho");
        list.add("Sasho");
        list.add("Mitko");
        list.add("Hrisi");
        list.add("Maria");
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }
}
