package com.example.Restaurante.util;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.stereotype.Service;

@Service
public class Sms {
    public static final String ACCOUNT_SID = "AC31ab31a294378b75951ec7d2cf05741b";
    public static final String AUTH_TOKEN = "8089ed1dcfbeaddef427d06a8a7c2225";

    public String sendSms(String phoneNumber, String messageBody) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message
                .creator(
                        new PhoneNumber(phoneNumber),
                        new PhoneNumber("+18146795623"),
                        messageBody
                )
                .create();

        return message.getSid();
    }
}