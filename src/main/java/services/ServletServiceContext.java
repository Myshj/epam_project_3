package services;

import utils.globals.IManagers;

import javax.servlet.ServletContext;

public class ServletServiceContext {
    private final ServletContext servlet;
    private final IManagers managers;

    public ServletContext getServlet() {
        return servlet;
    }

    public IManagers getManagers() {
        return managers;
    }

    public ServletServiceContext(ServletContext servlet, IManagers managers) {

        this.servlet = servlet;
        this.managers = managers;
    }
}
