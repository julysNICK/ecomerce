package com.julys.eccomerce.eccomerce.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.julys.eccomerce.eccomerce.auth.AuthenticationController;
import com.julys.eccomerce.eccomerce.config.SecurityConfiguration;
import com.julys.eccomerce.eccomerce.entity.Role;
import com.julys.eccomerce.eccomerce.entity.User;
import com.julys.eccomerce.eccomerce.response.user.UserErrorResponse;
import com.julys.eccomerce.eccomerce.response.user.UserResponse;
import com.julys.eccomerce.eccomerce.service.JwtService;
import com.julys.eccomerce.eccomerce.service.auth.AuthService;

@WebMvcTest(AuthenticationController.class)
@Import(SecurityConfiguration.class)

public class AuthControllerTest {
  @MockBean
  private AuthService authService;

  @MockBean
  private JwtService jwtService;

  @MockBean
  private AuthenticationProvider authenticationProvider;
  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @WithMockUser
  @AutoConfigureMockMvc
  @Test
  public void testRegister_success() throws Exception {

    User user = createUser("julysmartins", "test123", "123456789", Role.ADMIN, 1L);

    Mockito.when(authService.register(any(User.class)))
        .thenReturn(ResponseEntity.status(HttpStatus.OK).build());

    mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/register")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(user)))
        .andExpect(MockMvcResultMatchers.status().isOk());
  }

  @WithMockUser
  @Test
  public void testRegister_fail() throws Exception {

    User user = createUser("julysmartins", "test123", "123456789", Role.ADMIN, 1L);

    Mockito.when(authService.register(any(User.class)))
        .thenReturn(ResponseEntity.badRequest().build());

    mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/register")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(user)))
        .andExpect(MockMvcResultMatchers.status().isBadRequest());
  }

  @WithMockUser
  @Test
  public void testRegister_json_success() throws Exception {

    User user = createUser("julysmartins", "test123", "123456789", Role.ADMIN, 1L);

    UserResponse userResponse = createUserResponse(user);

    Mockito.doReturn(ResponseEntity.ok(userResponse.createJson())).when(authService).register(any(User.class));
    mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/register")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(user)))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.user.name").value(user.getUsername()))
        .andExpect(MockMvcResultMatchers.jsonPath("$.user.email").value(user.getEmail()))
        .andExpect(MockMvcResultMatchers.jsonPath("$.jwt").value(userResponse.getJwt()));
  }

  @WithMockUser
  @Test
  public void testRegister_json_error() throws Exception {

    User user = createUser("julysmartins", "test123", "123456789", Role.ADMIN, 1L);

    UserErrorResponse userResponse = createUserErrorResponse("Nome de usuário já cadastrado");

    Mockito.doReturn(ResponseEntity.badRequest().body(userResponse.createJson())).when(authService)
        .register(any(User.class));
    mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/register")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(user)))
        .andExpect(MockMvcResultMatchers.status().isBadRequest())
        .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(userResponse.getMessage()));
  }

  @WithMockUser
  @Test
  public void testRegister_UsernameAlreadyExists_Returns400() throws Exception {

    User user = createUser("julysmartins", "test123", "123456789", Role.ADMIN, 1L);
    ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);

    UserErrorResponse userResponse = createUserErrorResponse("Nome de usuário já cadastrado");

    Mockito.doReturn(ResponseEntity.badRequest().body(userResponse.createJson())).when(authService)
        .register(userCaptor.capture());

    assertEquals(userCaptor.getValue().getUsername(), user.getUsername());

    mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/register")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(user)))
        .andExpect(MockMvcResultMatchers.status().isBadRequest())
        .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(userResponse.getMessage()));
  }

  @WithMockUser
  @Test
  public void testRegister_arguments_captured_equal_user() throws Exception {

    User user = createUser("julysmartins", "test123", "123456789", Role.ADMIN, 1L);
    ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);

    UserErrorResponse userResponse = createUserErrorResponse("Nome de usuário já cadastrado");

    Mockito.doReturn(ResponseEntity.badRequest().body(userResponse.createJson())).when(authService)
        .register(userCaptor.capture());

    authService.register(user);

    System.out.println(userCaptor.getValue().getUsername());

    assertEquals(userCaptor.getValue().getUsername(), user.getEmail());

  }

  private User createUser(String username, String email, String password, Role role, Long id) {
    User user = new User();
    user.setId(id);
    user.setUsername(username);
    user.setEmail(email);
    user.setPassword(password);
    user.setRole(role);
    return user;
  }

  private UserResponse createUserResponse(User user) {
    UserResponse userResponse = new UserResponse();
    userResponse.setEmail(user.getEmail());
    userResponse.setHttpStatus(HttpStatus.OK);
    userResponse.setJwt("123456789");
    userResponse.setName(user.getUsername());

    return userResponse;
  }

  private UserErrorResponse createUserErrorResponse(String message) {
    UserErrorResponse userResponse = new UserErrorResponse();
    userResponse.setMessage(message);

    return userResponse;
  }
}