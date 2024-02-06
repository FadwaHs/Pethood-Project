package com.example.Pethood.CoreLayer.Application.Services.Publication.AlertSoin;

import com.example.Pethood.CoreLayer.BusinessObjects.Entities.Publication.Adoption;
import com.example.Pethood.CoreLayer.BusinessObjects.Entities.Publication.AlerteSoin;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

public interface AlertSoinService {

     AlerteSoin createAlertSoin(@RequestBody Map<String, Object> requestBody);

    List<AlerteSoin> getAllAlertSoinNotResolved();

     List<AlerteSoin> findByDataContainingAnimal(String searchData);


}
