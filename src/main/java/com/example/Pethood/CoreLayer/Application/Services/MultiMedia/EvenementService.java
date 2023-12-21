package com.example.Pethood.CoreLayer.Application.Services.MultiMedia;

import com.example.Pethood.CoreLayer.BusinessObjects.Entities.Multimedia.Evenement;

import java.util.Map;

public interface EvenementService {


    public Evenement createEvenement(Map<String, Object> requestBody);
}
