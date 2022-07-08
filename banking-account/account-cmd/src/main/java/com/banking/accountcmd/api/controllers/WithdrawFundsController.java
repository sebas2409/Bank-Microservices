package com.banking.accountcmd.api.controllers;


import com.banking.accountcmd.api.command.WithdrawFundsCommand;
import com.banking.accountcommon.dto.BaseResponse;
import com.banking.cqrs.core.Exceptions.AggregateNotFoundException;
import com.banking.cqrs.core.infrastructure.CommandDispatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.MessageFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1/withdrawFunds")
public class WithdrawFundsController {

    private final Logger logger = Logger.getLogger(WithdrawFundsController.class.getName());

    @Autowired
    private CommandDispatcher commandDispatcher;

    @PutMapping(path = "/{id}")
    public ResponseEntity<BaseResponse> withdrawFunds(@PathVariable(value = "id") String id,
                                                     @RequestBody WithdrawFundsCommand command){
        try{
            command.setId(id);
            commandDispatcher.send(command);
            return new ResponseEntity<>(new BaseResponse("El retiro se ha realizado correctamente"), HttpStatus.OK);
        }catch (IllegalStateException | AggregateNotFoundException e){
            logger.log(Level.WARNING, MessageFormat.format("El cliente envio un mal request {0}",e.toString()));
            return new ResponseEntity<>(new BaseResponse(e.toString()),HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            var mensaje = MessageFormat.format("Errores mientras se procesaba el request {id}",id);
            return new ResponseEntity<>( new BaseResponse(mensaje),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
