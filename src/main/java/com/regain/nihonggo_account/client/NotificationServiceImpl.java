package com.regain.nihonggo_account.client;

import com.regain.nihonggo_account.model.dto.MessageDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class NotificationServiceImpl implements INotificationService{
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    public void sendNotificationEmail(MessageDTO messageDTO) {
        logger.error("Notification service is slow");
    }
}
