package org.eligibilityms.controller;

import lombok.RequiredArgsConstructor;
import org.eligibilityms.dto.ClientDto;
import org.eligibilityms.proxy.ClientsMsProxy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/eligibility")
public class EligibilityController {

    private final ClientsMsProxy clientsMsProxy;

    @GetMapping
    public List<ClientDto> getClientsList(){
        return clientsMsProxy.getClientsList();
    }

    @GetMapping("ok")
    public ResponseEntity<?> getClientsList1(){
        return clientsMsProxy.getClientsList1();
    }
}
