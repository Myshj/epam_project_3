package launch.servlets;

import utils.globals.IManagers;

import javax.servlet.ServletContext;

public class ServiceContext {
    private final ServletContext servlet;
    private final IManagers managers;

    public ServletContext getServlet() {
        return servlet;
    }

    public IManagers getManagers() {
        return managers;
    }

    public ServiceContext(ServletContext servlet, IManagers managers) {

        this.servlet = servlet;
        this.managers = managers;
    }
}
