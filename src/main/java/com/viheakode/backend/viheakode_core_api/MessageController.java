package com.viheakode.backend.viheakode_core_api;

import com.viheakode.backend.model.Message;
import com.viheakode.backend.service.serviceImp.MessageServiceImp;
import com.viheakode.backend.util.ApiResponseStructure;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/messages")
public class MessageController {

    private final MessageServiceImp messageServiceImp;

    public MessageController(MessageServiceImp messageServiceImp) {
        this.messageServiceImp = messageServiceImp;
    }

    @PostMapping
    public ResponseEntity<Object> sendMessage(@RequestBody Message message){
        Message message1 = messageServiceImp.sendMessage(message);
        return ApiResponseStructure.responseSuccess("Created", message1, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Object> getAllMessages(){
        List<Message> messageList = messageServiceImp.getAllMessages();
        return ApiResponseStructure.responseSuccess("Ok", messageList, HttpStatus.OK);
    }
}
