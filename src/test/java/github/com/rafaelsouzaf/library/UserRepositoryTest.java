package github.com.rafaelsouzaf.library;

import github.com.rafaelsouzaf.library.model.User;
import github.com.rafaelsouzaf.library.model.UserRole;
import github.com.rafaelsouzaf.library.repository.UserRepository;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class UserRepositoryTest extends WebAppConfigTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private MockMvc mvc;

    @Before
    public void before() {
        System.out.println("Before");
    }

    @After
    public void after() {
        System.out.println("After");
    }

    @BeforeClass
    public static void beforeClass() {
        System.out.println("Before Class");
    }

    @AfterClass
    public static void afterClass() {
        System.out.println("After Class");
    }

    @Test
    public void contexLoads() {
        assertThat(userRepository).isNotNull();
    }

    @Test
    public void findByIdTest() {
        User user = userRepository.save(new User("test@test.com", "Test", "Test", "test", UserRole.ROLE_ADMIN, true));
        Optional<User> found = userRepository.findById(user.getId());
        assertEquals("Testing insert", "Test", found.get().getFirstName());
    }

    @Test
    public void findAllTest() throws Exception {
        mvc.perform(
                get("/book/list")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(6)))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
//                .andExpect(jsonPath("$[0].name", is("teste")));
    }

}
