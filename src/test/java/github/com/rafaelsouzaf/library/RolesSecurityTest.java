package github.com.rafaelsouzaf.library;

import github.com.rafaelsouzaf.library.model.UserRole;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestContextManager;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.Arrays;
import java.util.List;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(Parameterized.class)
public class RolesSecurityTest extends WebAppConfigTest {

    @Autowired
    private MockMvc mvc;

    private WebService service;

    public RolesSecurityTest(WebService service) {
        this.service = service;
    }

    /**
     * Manually config for spring to use Parameterised
     * Without that we can not use the Autowired MockMvc
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        TestContextManager contextManager = new TestContextManager(getClass());
        contextManager.prepareTestInstance(this);
    }

    @Parameterized.Parameters
    public static List<WebService> start() {

        return Arrays.asList(
                new WebService(HttpMethod.GET, "/book/list", Arrays.asList(UserRole.ROLE_ANONYMOUS)),
                new WebService(HttpMethod.GET, "/book/list/order/asc", Arrays.asList(UserRole.ROLE_ANONYMOUS)),
                new WebService(HttpMethod.GET, "/book/list/order/desc", Arrays.asList(UserRole.ROLE_ANONYMOUS)),
                new WebService(HttpMethod.GET, "/book/get/6", Arrays.asList(UserRole.ROLE_ANONYMOUS)),
                new WebService(HttpMethod.GET, "/book/get/title/book title",  Arrays.asList(UserRole.ROLE_ANONYMOUS)),
                new WebService(HttpMethod.GET, "/book/get/title/exact/book title 1",  Arrays.asList(UserRole.ROLE_ANONYMOUS)),
                new WebService(HttpMethod.PUT, "/book/add", Arrays.asList(UserRole.ROLE_ADMIN, UserRole.ROLE_LIBRARIAN), "{\"title\":\"Samdwish Book\",\"bookAuthor\":[{\"firstName\":\"Joanna\", \"lastName\":\"Fun\"}]}"),
                new WebService(HttpMethod.POST, "/book/edit/1", Arrays.asList(UserRole.ROLE_ADMIN, UserRole.ROLE_LIBRARIAN), "{\"title\":\"Samdwish Book Edited\"}"),
                new WebService(HttpMethod.DELETE, "/book/delete/1", Arrays.asList(UserRole.ROLE_ADMIN)),
                new WebService(HttpMethod.DELETE, "/book/delete/2", Arrays.asList(UserRole.ROLE_LIBRARIAN))
        );

    }

    @Test
    public void bookListTest() throws Exception {

        /*
         * TEST ALL ROLES OF EACH URL
         */
        List<UserRole> rolesAllowed = this.service.ROLES_ALLOWED;
        for(UserRole userRole: rolesAllowed) {
            MockHttpServletRequestBuilder builder = buildGenerator(this.service, userRole);
            mvc.perform(builder)
                    .andExpect(status().isOk())
                    .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
        }

    }

    private MockHttpServletRequestBuilder buildGenerator(WebService service, UserRole userRole) {

        MockHttpServletRequestBuilder builder = request(service.METHOD, service.URL);
        builder.contentType(MediaType.APPLICATION_JSON);
        if (service.BODY != null) {
            builder.content(service.BODY);
        }

        switch (userRole) {
            case ROLE_ADMIN:
                builder.with(httpBasic("admin@gmail.com", "admin"));
                break;
            case ROLE_LIBRARIAN:
                builder.with(httpBasic("librarian@gmail.com", "librarian"));
                break;
            case ROLE_VISITOR:
                builder.with(httpBasic("visitor@gmail.com", "visitor"));
                break;
            case ROLE_ANONYMOUS:
                // Do nothing
                break;
        }

        return builder;

    }

    private static class WebService {

        private HttpMethod METHOD;
        private String URL;
        private List<UserRole> ROLES_ALLOWED;
        private String BODY;

        WebService(HttpMethod method, String url, List<UserRole> rolesAllowed) {
            this.METHOD = method;
            this.URL = url;
            this.ROLES_ALLOWED = rolesAllowed;
        }

        WebService(HttpMethod method, String url, List<UserRole> rolesAllowed, String body) {
            this(method, url, rolesAllowed);
            this.BODY = body;
        }

    }

}
