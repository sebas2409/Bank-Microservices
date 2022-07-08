package com.banking.accountcmd.api.dto;

import com.banking.accountcommon.dto.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OpenAccountResponse extends BaseResponse {
    private String id;


    public OpenAccountResponse(String message,String id){
        super(message);
        this.id = id;
    }
}
