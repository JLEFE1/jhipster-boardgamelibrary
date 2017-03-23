package org.homegrown.boardgamelibrary.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.HttpEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.UUID;
import java.util.concurrent.Future;

@FeignClient(value = "http://boardgame")
public interface BoardgameClient {
    @Async
    @RequestMapping(method = RequestMethod.GET, value = "boardgame/{userUuid}")
    Future<HttpEntity<Void>> getBoardgame(@PathVariable("userUuid") UUID userUuid);

}
