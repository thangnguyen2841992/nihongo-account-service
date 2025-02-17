package com.regain.nihonggo_account.client;

import com.regain.nihonggo_account.model.dto.MessageDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "notification-service", url = "http://localhost:8081", fallback = NotificationServiceImpl.class)
public interface INotificationService {
    @PostMapping("/sendNotificationEmail.do")
    void sendNotificationEmail(@RequestBody MessageDTO messageDTO);
}
