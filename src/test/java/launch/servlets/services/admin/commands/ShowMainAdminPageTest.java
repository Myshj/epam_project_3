package launch.servlets.services.admin.commands;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ShowMainAdminPageTest {

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

    private ShowMainAdminPage command;

    private boolean redirected = false;
    private List<String> redirectedTo = new ArrayList<>();

    private void onRedirect(String uri) {
        redirected = true;
        redirectedTo.add(uri);
    }

    @BeforeEach
    void setUp() throws ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        Mockito.when(servletContext.getRequestDispatcher(
                Mockito.anyString())
        ).thenReturn(requestDispatcher);
        Mockito.when(request.getRequestURI())
                .thenReturn("/jsp/admin/main.jsp");
        command = new ShowMainAdminPage(servletContext);
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
        assertEquals("/jsp/admin/main.jsp", redirectedTo.get(0));


    }

}