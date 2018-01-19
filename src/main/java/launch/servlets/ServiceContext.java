package launch.servlets;

import utils.globals.Managers;

import javax.servlet.ServletContext;

public class ServiceContext {
    private final ServletContext servlet;
    private final Managers managers;

    public ServletContext getServlet() {
        return servlet;
    }

    public Managers getManagers() {
        return managers;
    }

    public ServiceContext(ServletContext servlet, Managers managers) {

        this.servlet = servlet;
        this.managers = managers;
    }
}
