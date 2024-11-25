package org.eligibilityms.proxy;

import org.eligibilityms.dto.ClientDto;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "Clients-ms")
@RibbonClient(name = "Clients-ms")
public interface ClientsMsProxy {
    @GetMapping(value = "/clients")
    List<ClientDto> getClientsList();

    @GetMapping(value = "/clients/ok")
    ResponseEntity<?> getClientsList1();
}
