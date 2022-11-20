/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package one.digitalinnovation.cloudparking.service;

import one.digitalinnovation.cloudparking.exception.ParkingNotFoundException;
import java.time.*;
import java.time.temporal.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import one.digitalinnovation.cloudparking.model.Parking;
import one.digitalinnovation.cloudparking.repository.ParkingRepository;
import org.springframework.stereotype.Service;

/**
 *
 * @author deiam
 */
@Service
public class ParkingService {
    
    private final ParkingRepository parkingRepository;

    public ParkingService(ParkingRepository parkingRepository) {
        this.parkingRepository = parkingRepository;
    }
    
    private static Map<String, Parking> parkingMap = new HashMap();
    
    static {
        var id = getUUID();
        var id1 = getUUID();
        Parking parking = new Parking(id, "DMS-1111", "SC", "CELTA", "PRETO");
        Parking parking1 = new Parking(id1, "MMM-1234", "SP", "VW GOL", "VERMELHO");

        parkingMap.put(id,parking);
        parkingMap.put(id1,parking1);
    }
    
    @Transactional
    public List<Parking> findAll(){
        return parkingRepository.findAll();
    }
    
    @Transactional
    private static String getUUID(){
        return UUID.randomUUID().toString().replace("-","");
    }

    @Transactional
    public Parking findById(String id) {
        return parkingRepository.findById(id).orElseThrow(() ->
        new ParkingNotFoundException(id));
    }
    
    @Transactional
    public Parking create(Parking parkingCreate){
        String uuid = getUUID();
        parkingCreate.setId(getUUID());
        parkingCreate.setEntryDate(LocalDateTime.now());
        return parkingRepository.save(parkingCreate);
    }

    @Transactional
    public void delete(String id) {
        findById(id);
        parkingRepository.deleteById(id);
    }
    
    @Transactional
    public Parking update(String id, Parking parkingCreate) {
        Parking parking = findById(id);
        parking.setColor(parkingCreate.getColor());
        parking.setState(parkingCreate.getState());
        parking.setModel(parkingCreate.getModel());
        parking.setLicense(parkingCreate.getLicense());
        parkingRepository.save(parking);
        return parking;
    }
    
    @Transactional
    public Parking checkout(String id) {
        int numberHours;
        //recuperar o estacionado
        Parking parking = findById(id);
        //atualizar data de saída
        parking.setExitDate(LocalDateTime.now());
        //calcular o valor
        numberHours = (int) parking.getEntryDate().until(parking.getExitDate(), ChronoUnit.HOURS);
        parking.setBill(parking.hoursAmountToPay(numberHours));
        parkingRepository.save(parking);
        return parking;
    }
}
