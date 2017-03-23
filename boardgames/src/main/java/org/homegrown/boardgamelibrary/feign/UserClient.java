package org.homegrown.boardgamelibrary.feign;

import org.homegrown.boardgamelibrary.resource.Account;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.UUID;

@FeignClient(value = "account")
public interface UserClient {
    @RequestMapping(method = RequestMethod.GET, value = "accounts/{uuid}")
    Account findByUuid(@RequestHeader("X-Forwarded-Host") String xForwardedHost, @RequestHeader("Authorization") String token, @PathVariable("uuid") UUID uuid);
}
