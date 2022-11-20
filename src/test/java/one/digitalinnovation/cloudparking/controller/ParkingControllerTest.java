/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package one.digitalinnovation.cloudparking.controller;


import io.restassured.RestAssured;
import one.digitalinnovation.cloudparking.controller.dto.ParkingCreateDTO;
import one.digitalinnovation.cloudparking.controller.dto.ParkingDTO;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author deiam
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ParkingControllerTest {
    
    @LocalServerPort
    private int randomPort;
    
    @BeforeEach
    public void SetupTest(){
        System.out.println(randomPort);
        RestAssured.port = randomPort;
    }
    
    
    public ParkingControllerTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of findAll method, of class ParkingController.
     */
    @Test
    void whenFindAllThenCheckResult(){
        RestAssured.given()
                .when()
                .get("/parking")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("license[0]",Matchers.equalTo("DMS-1111"));
    }

    /**
     * Test of findById method, of class ParkingController.
     */
    @Test
    public void testFindById() {
        System.out.println("findById");
        String id = "";
        ParkingController instance = null;
        ResponseEntity<ParkingDTO> expResult = null;
        ResponseEntity<ParkingDTO> result = instance.findById(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of delete method, of class ParkingController.
     */
    @Test
    public void testDelete() {
        System.out.println("delete");
        String id = "";
        ParkingController instance = null;
        ResponseEntity expResult = null;
        ResponseEntity result = instance.delete(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of create method, of class ParkingController.
     */
    @Test
    public void whenCreateThenCheckIsCreated() {
        
        var createDTO = new ParkingCreateDTO();
        createDTO.setColor("AMARELO");
        createDTO.setLicense("WRT-5555");
        createDTO.setModel("BRASILIA");
        createDTO.setState("SP");
        RestAssured.given()
                .when()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(createDTO)
                .post("/parking")
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .body("license",Matchers.equalTo("WRT-5555"))
                .body("color",Matchers.equalTo("AMARELO"));
    }

    /**
     * Test of update method, of class ParkingController.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        String id = "";
        ParkingCreateDTO parkingCreateDto = null;
        ParkingController instance = null;
        ResponseEntity<ParkingDTO> expResult = null;
        ResponseEntity<ParkingDTO> result = instance.update(id, parkingCreateDto);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of exit method, of class ParkingController.
     */
    @Test
    public void testExit() {
        System.out.println("exit");
        String id = "";
        ParkingController instance = null;
        ResponseEntity<ParkingDTO> expResult = null;
        ResponseEntity<ParkingDTO> result = instance.exit(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
