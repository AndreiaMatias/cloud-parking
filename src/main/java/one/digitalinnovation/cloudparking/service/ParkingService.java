/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package one.digitalinnovation.cloudparking.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import one.digitalinnovation.cloudparking.model.Parking;
import org.springframework.stereotype.Service;

/**
 *
 * @author deiam
 */
@Service
public class ParkingService {
    
    private static Map<String, Parking> parkingMap = new HashMap();
    
    static {
        var id = getUUID();
        var id1 = getUUID();
        Parking parking = new Parking(id, "DMS-1111", "SC", "CELTA", "PRETO");
        Parking parking1 = new Parking(id1, "MMM-1234", "SP", "VW GOL", "VERMELHO");

        parkingMap.put(id,parking);
        parkingMap.put(id1,parking1);
    }
    
    public List<Parking> findAll(){
        return parkingMap.values().stream().collect(Collectors.toList());
    }
    
    private static String getUUID(){
        return UUID.randomUUID().toString().replace("-","");
    }

    public Parking findById(String id) {
        return parkingMap.get(id);
    }
}
