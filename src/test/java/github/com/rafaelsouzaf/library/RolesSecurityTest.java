package github.com.rafaelsouzaf.library;

import github.com.rafaelsouzaf.library.model.UserRole;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class RolesSecurityTest extends WebAppConfigTest {

    @Autowired
    private MockMvc mvc;


    private List<WebService> services = new ArrayList<>();

    @Before
    public void init() {

        services = Arrays.asList(
                new WebService(HttpMethod.GET, "/book/list", null),
                new WebService(HttpMethod.GET, "/book/list/order/asc", null),
                new WebService(HttpMethod.GET, "/book/list/order/desc", null),
                new WebService(HttpMethod.GET, "/book/get/6", null),
                new WebService(HttpMethod.GET, "/book/get/title/book title", null),
                new WebService(HttpMethod.GET, "/book/get/title/exact/book title 1", null),
                new WebService(HttpMethod.PUT, "/book/add", Arrays.asList(UserRole.ROLE_ADMIN, UserRole.ROLE_LIBRARIAN), "{\"title\":\"Samdwish Book\",\"bookAuthor\":[{\"firstName\":\"Joanna\", \"lastName\":\"Fun\"}]}"),
                new WebService(HttpMethod.POST, "/book/edit/1", Arrays.asList(UserRole.ROLE_ADMIN, UserRole.ROLE_LIBRARIAN), "{\"title\":\"Samdwish Book Edited\"}"),
                new WebService(HttpMethod.DELETE, "/book/delete/2", Arrays.asList(UserRole.ROLE_ADMIN, UserRole.ROLE_LIBRARIAN))
        );

    }

    @Test
    public void bookListTest() throws Exception {

        /*
         * LIST ALL URLS TO TEST
         */
        for (WebService service: services) {

            /*
             * TEST ALL ROLES OF EACH URL
             */
            List<UserRole> rolesAllowed = service.ROLES_ALLOWED;
            if (rolesAllowed == null) {

                MockHttpServletRequestBuilder builder = request(service.METHOD, service.URL);
                builder.contentType(MediaType.APPLICATION_JSON_UTF8);

                mvc.perform(builder)
                        .andExpect(status().isOk());

            } else {
                for(UserRole userRole: rolesAllowed) {

                    MockHttpServletRequestBuilder builder = request(service.METHOD, service.URL);
                    builder.contentType(MediaType.APPLICATION_JSON);
                    if (service.BODY != null) {
                        builder.content(service.BODY);
                    }

                    if (userRole == UserRole.ROLE_ADMIN) {
                        System.out.println("----------------------- UserRole.ROLE_ADMIN");
                        builder.with(httpBasic("admin@gmail.com", "admin"));
                    }

                    if (userRole == UserRole.ROLE_LIBRARIAN) {
                        System.out.println("----------------------- UserRole.ROLE_LIBRARIAN");
                        builder.with(httpBasic("librarian@gmail.com", "librarian"));
                    }

                    if (userRole == UserRole.ROLE_VISITOR) {
                        System.out.println("----------------------- UserRole.ROLE_VISITOR");
                        builder.with(httpBasic("visitor@gmail.com", "visitor"));
                    }

                    mvc.perform(builder)
                            .andExpect(status().isOk());

                }
            }

        }

//        mvc.perform(
//                get("/user/list")
//                .with(httpBasic("admin@gmail.com", "admin"))
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
//                .andExpect(jsonPath("$", hasSize(6)))
//                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

    }

    private MockHttpServletRequestBuilder buildGenerator() {


        return null;


    }

//    @Test
//    public void bookListTest2() throws Exception {
//        mvc.perform(
//                get("/book/list")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(6)))
//                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
////                .andExpect(jsonPath("$[0].name", is("teste")));
//    }

    private class WebService {

        private HttpMethod METHOD;
        private String URL;
        private List<UserRole> ROLES_ALLOWED;
        private String BODY;

        public WebService(HttpMethod method, String url, List<UserRole> rolesAllowed) {
            this.METHOD = method;
            this.URL = url;
            this.ROLES_ALLOWED = rolesAllowed;
        }

        public WebService(HttpMethod method, String url, List<UserRole> rolesAllowed, String body) {
            this(method, url, rolesAllowed);
            this.BODY = body;
        }

    }


}
