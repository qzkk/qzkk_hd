package com.qzkk.service.impl;

import com.qzkk.dao.RegistrationRepository;
import com.qzkk.domain.Registration;
import com.qzkk.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationServiceImpl implements RegistrationService {
    @Autowired
    private RegistrationRepository registrationRepository;
    @Override
    public void save(Registration registration) {
       registrationRepository.save(registration);
    }
}
