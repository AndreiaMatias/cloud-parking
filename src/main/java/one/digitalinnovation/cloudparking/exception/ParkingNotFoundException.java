package one.digitalinnovation.cloudparking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author deiam
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ParkingNotFoundException  extends RuntimeException{
    
    public ParkingNotFoundException(String id){
        super("Parking not found with id: " + id);
    }
    
}
