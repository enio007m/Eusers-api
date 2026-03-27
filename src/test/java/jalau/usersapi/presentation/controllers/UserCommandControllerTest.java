package jalau.usersapi.presentation.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import jalau.usersapi.core.domain.entities.User;
import jalau.usersapi.core.domain.services.IUserCommandService;
import jalau.usersapi.presentation.dtos.UserUpdateDto;
import jalau.usersapi.presentation.mappers.UserMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserCommandController.class)
class UserCommandControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private jalau.usersapi.infrastructure.mysql.mybatis.UserMyBatisMapper userMyBatisMapper;

    @MockBean
    private IUserCommandService userCommandService;

    @MockBean
    private UserMapper userMapper;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldRespond404WhenUserNotFound() throws Exception {
        when(userCommandService.updateUser(any(User.class))).thenReturn(null);

        UserUpdateDto dto = new UserUpdateDto();
        dto.setName("Some Name");
        dto.setLogin("slogin");
        dto.setPassword("pwd123");

        MvcResult result = mockMvc.perform(patch("/api/v1/users/non-existent")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andReturn();

        mockMvc.perform(asyncDispatch(result)).andExpect(status().isNotFound());
    }

    @Test
    void shouldRespond400WhenValidationFails() throws Exception {
        UserUpdateDto dto = new UserUpdateDto();
        dto.setName(""); // Invalid, triggers @NotBlank

        mockMvc.perform(patch("/api/v1/users/id")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(result -> {
                    Exception ex = result.getResolvedException();
                    org.junit.jupiter.api.Assertions.assertTrue(
                        ex instanceof org.springframework.web.bind.MethodArgumentNotValidException,
                        "Expected MethodArgumentNotValidException"
                    );
                });
    }

    @Test
    void shouldRespond200WhenUpdateIsSuccessful() throws Exception {
        User dummyUser = new User();
        dummyUser.setId("1");
        
        when(userMapper.toDomainEntity(any(), any())).thenReturn(dummyUser);
        
        User returnedUser = new User();
        returnedUser.setId("1");
        returnedUser.setName("Name");
        when(userCommandService.updateUser(any(User.class))).thenReturn(returnedUser);
        jalau.usersapi.presentation.dtos.UserResponseDto responseDto = new jalau.usersapi.presentation.dtos.UserResponseDto();
        responseDto.setId("1");
        responseDto.setName("Name");
        responseDto.setLogin("login");
        when(userMapper.toResponseDtoEntity(any(User.class))).thenReturn(responseDto);

        UserUpdateDto dto = new UserUpdateDto();
        dto.setName("New Name");
        dto.setLogin("new_login");
        dto.setPassword("pwd123");

        MvcResult result = mockMvc.perform(patch("/api/v1/users/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andReturn();

        mockMvc.perform(asyncDispatch(result)).andExpect(status().isOk());
    }
}
