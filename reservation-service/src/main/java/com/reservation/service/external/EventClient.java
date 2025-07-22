package com.reservation.service.external;

import com.reservation.service.model.Event;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "event-service")
public interface EventClient {

    @GetMapping("/events/{id}")
    Event getEventById(@PathVariable("id") Long id);
}
