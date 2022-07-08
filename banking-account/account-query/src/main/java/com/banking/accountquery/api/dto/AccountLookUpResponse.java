package com.banking.accountquery.api.dto;

import com.banking.accountcommon.dto.BaseResponse;
import com.banking.accountquery.domain.BankAccount;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class AccountLookUpResponse extends BaseResponse {
    private List<BankAccount> accounts;


    public AccountLookUpResponse(String message){
        super(message);
    }
}
