package com.giret.consumer.client;



import com.giret.consumer.model.Loan;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;


@FeignClient(
        name = "loanClient",
        url = "${api.giret.loan.base-url}"
)
public interface LoanClient {

    @PutMapping(value = "/api/updateLoanByState/{state}/{id}",produces = "application/json")
    Loan updateLoanByState(@PathVariable ("state")String state,@PathVariable ("id")Long id);




}