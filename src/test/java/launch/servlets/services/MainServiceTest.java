package launch.servlets.services;

import launch.servlets.ServiceContext;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import utils.globals.Managers;
import utils.managers.ConnectionManager;
import utils.managers.resource.ResourceBundleAccessor;
import utils.managers.resource.ResourceManager;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MainServiceTest {
    private class TestRequestDispatcher implements RequestDispatcher {

        @Override
        public void forward(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
            onRedirect(((HttpServletRequest) servletRequest).getRequestURI());
        }

        @Override
        public void include(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {

        }
    }

    @Mock
    private ServletContext servletContext;

    @Mock
    private HttpServletRequest request;

    private RequestDispatcher requestDispatcher = new TestRequestDispatcher();

    private MainService command;

    private boolean redirected = false;
    private List<String> redirectedTo = new ArrayList<>();
    private Map<String, Object> attributes = new HashMap<>();

    private void onRedirect(String uri) {
        redirected = true;
        redirectedTo.add(uri);
    }

    private void onSetAttribute(String attribute, Object object) {
        attributes.put(attribute, object);
    }

    @BeforeEach
    void setUp() throws ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        Mockito.when(servletContext.getRequestDispatcher(
                Mockito.anyString())
        ).thenReturn(requestDispatcher);
        Mockito.when(request.getRequestURI())
                .thenReturn("/jsp/admin/country/show_all");
        ConnectionManager connectionManager = new ConnectionManager(
                new ResourceManager(
                        new ResourceBundleAccessor().withResource("application")
                )
        );
        command = new MainService(
                new ServiceContext(servletContext, new Managers())
        );
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void execute() {
        int countOfRequests = 3;
        for (int i = 0; i < countOfRequests; i++) {
            command.accept(request, null);
        }
        assertTrue(redirected);
        assertEquals(countOfRequests, redirectedTo.size());
        //assertEquals("/jsp/admin/main.jsp", redirectedTo.get(0));
    }

}