package com.example.tutorial;

import com.example.tutorial.application.dto.request.CreateUserRequest;
import com.example.tutorial.application.dto.request.UpdateUserRequest;
import com.example.tutorial.application.dto.response.UserResponse;
import com.example.tutorial.domain.user.vo.Activo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserApiIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void createAndGetAndUpdateAndDeleteThroughRestApi() {
        var req = new CreateUserRequest();
        req.setUsername("ana");
        req.setNombre("Ana");
        req.setApellidos("Martín");
        req.setPassword("secret1");
        req.setEmail("ana@example.com");
        req.setTelefono("600111222");

        ResponseEntity<UserResponse> createResp = restTemplate.postForEntity("/api/users", req, UserResponse.class);
        assertEquals(HttpStatus.CREATED, createResp.getStatusCode());
        assertNotNull(createResp.getBody());
        String id = createResp.getBody().getId();

        ResponseEntity<UserResponse> getResp = restTemplate.getForEntity("/api/users/" + id, UserResponse.class);
        assertEquals(HttpStatus.OK, getResp.getStatusCode());
        assertEquals("ana", getResp.getBody().getUsername());

        var update = new UpdateUserRequest();
        update.setNombre("Ana Maria");
        update.setApellidos("Martín Ruiz");
        update.setPassword("secret2");
        update.setEmail("anamar@example.com");
        update.setActivo(Activo.TRUE);
        update.setTelefono("600111223");

        HttpEntity<UpdateUserRequest> requestEntity = new HttpEntity<>(update);
        ResponseEntity<UserResponse> updateResp = restTemplate.exchange("/api/users/" + id, HttpMethod.PUT, requestEntity, UserResponse.class);
        assertEquals(HttpStatus.OK, updateResp.getStatusCode());
        assertEquals("Martín Ruiz", updateResp.getBody().getApellidos());

        ResponseEntity<Void> deleteResp = restTemplate.exchange("/api/users/" + id, HttpMethod.DELETE, HttpEntity.EMPTY, Void.class);
        assertEquals(HttpStatus.NO_CONTENT, deleteResp.getStatusCode());

        ResponseEntity<UserResponse> getAfterDelete = restTemplate.getForEntity("/api/users/" + id, UserResponse.class);
        assertEquals(HttpStatus.NOT_FOUND, getAfterDelete.getStatusCode());
    }
}
