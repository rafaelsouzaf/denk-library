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
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.Arrays;
import java.util.List;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Execute dynamically the tests related with Security roles. This class will
 * auto execute itself many times because is using @Parameterized annotation.
 */
@RunWith(Parameterized.class)
public class RolesSecurityTest extends WebAppConfigTest {

    @Autowired
    private MockMvc mvc;

    private TestCase test;

    /**
     * Constructor
     * @param test
     */
    public RolesSecurityTest(TestCase test) {
        this.test = test;
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

    /**
     * Each item of the Collection will be an instance of this class.
     * @return List<TestCase>
     */
    @Parameterized.Parameters
    public static List<TestCase> start() {

        return Arrays.asList(

                /*
                 * Allowed tests
                 */
                new TestCase(HttpMethod.GET, "/book/list", UserRole.ROLE_ANONYMOUS,
                        Arrays.asList(
                            status().isOk(),
                            content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)
                        )),
                new TestCase(HttpMethod.GET, "/book/list/order/asc", UserRole.ROLE_ANONYMOUS,
                        Arrays.asList(
                                status().isOk(),
                                content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)
                        )),
                new TestCase(HttpMethod.GET, "/book/list/order/desc", UserRole.ROLE_ANONYMOUS,
                        Arrays.asList(
                                status().isOk(),
                                content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)
                        )),
                new TestCase(HttpMethod.GET, "/book/get/6", UserRole.ROLE_ANONYMOUS,
                        Arrays.asList(
                                status().isOk(),
                                content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)
                        )),
                new TestCase(HttpMethod.GET, "/book/get/title/book title", UserRole.ROLE_ANONYMOUS,
                        Arrays.asList(
                                status().isOk(),
                                content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)
                        )),
                new TestCase(HttpMethod.GET, "/book/get/title/exact/book title 1", UserRole.ROLE_ANONYMOUS,
                        Arrays.asList(
                                status().isOk(),
                                content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)
                        )),
                new TestCase(HttpMethod.PUT, "/book/add", UserRole.ROLE_ADMIN,
                        Arrays.asList(
                                status().isOk(),
                                content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)
                        ),
                        "{\"title\":\"Samdwish Book\",\"bookAuthor\":[{\"firstName\":\"Joanna\", \"lastName\":\"Fun\"}]}"),
                new TestCase(HttpMethod.PUT, "/book/add", UserRole.ROLE_LIBRARIAN,
                        Arrays.asList(
                                status().isOk(),
                                content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)
                        ),
                        "{\"title\":\"Samdwish Book\",\"bookAuthor\":[{\"firstName\":\"Joanna\", \"lastName\":\"Fun\"}]}"),
                new TestCase(HttpMethod.POST, "/book/edit/1", UserRole.ROLE_ADMIN,
                        Arrays.asList(
                                status().isOk(),
                                content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)
                        ),
                        "{\"title\":\"Samdwish Book Edited\"}"),
                new TestCase(HttpMethod.POST, "/book/edit/1", UserRole.ROLE_LIBRARIAN,
                        Arrays.asList(
                                status().isOk(),
                                content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)
                        ),
                        "{\"title\":\"Samdwish Book Edited\"}"),
                new TestCase(HttpMethod.DELETE, "/book/delete/1", UserRole.ROLE_ADMIN,
                        Arrays.asList(
                                status().isOk(),
                                content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)
                        )),
                new TestCase(HttpMethod.DELETE, "/book/delete/2", UserRole.ROLE_LIBRARIAN,
                        Arrays.asList(
                                status().isOk(),
                                content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)
                        )),

                /*
                 * Forbidden
                 */
                new TestCase(HttpMethod.POST, "/book/edit/3", UserRole.ROLE_VISITOR,
                        Arrays.asList(
                                status().isForbidden(),
                                content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)
                        ),
                        "{\"title\":\"Samdwish Book Edited\"}"),
                new TestCase(HttpMethod.POST, "/book/edit/4", UserRole.ROLE_VISITOR,
                        Arrays.asList(
                                status().isForbidden(),
                                content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)
                        ),
                        "{\"title\":\"Samdwish Book Edited\"}"),
                new TestCase(HttpMethod.PUT, "/book/add", UserRole.ROLE_VISITOR,
                        Arrays.asList(
                                status().isForbidden(),
                                content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)
                        ),
                        "{\"title\":\"Samdwish Book\",\"bookAuthor\":[{\"firstName\":\"Joanna\", \"lastName\":\"Fun\"}]}")

        );

    }

    /**
     * Execute the test
     * @throws Exception
     */
    @Test
    public void bookListTest() throws Exception {
        MockHttpServletRequestBuilder builder = buildGenerator(this.test);
        ResultActions resultActions = mvc.perform(builder);
        for (ResultMatcher matcher: test.MATCHERS) {
            resultActions.andExpect(matcher);
        }
    }

    /**
     * Get the instance of TestCase and generate the correspondent MockHttpServletRequestBuilder
     * @param test
     * @return MockHttpServletRequestBuilder
     */
    private MockHttpServletRequestBuilder buildGenerator(TestCase test) {

        MockHttpServletRequestBuilder builder = request(test.METHOD, test.URL);
        builder.contentType(MediaType.APPLICATION_JSON);
        if (test.BODY != null) {
            builder.content(test.BODY);
        }

        switch (test.ROLE_ALLOWED) {
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

    private static class TestCase {

        private HttpMethod METHOD;
        private String URL;
        private UserRole ROLE_ALLOWED;
        private String BODY;
        private List<ResultMatcher> MATCHERS;

        TestCase(HttpMethod method, String url, UserRole roleAllowed, List<ResultMatcher> matchers) {
            this.METHOD = method;
            this.URL = url;
            this.ROLE_ALLOWED = roleAllowed;
            this.MATCHERS = matchers;
        }

        TestCase(HttpMethod method, String url, UserRole roleAllowed, List<ResultMatcher> matchers, String body) {
            this(method, url, roleAllowed, matchers);
            this.BODY = body;
        }

    }

}
