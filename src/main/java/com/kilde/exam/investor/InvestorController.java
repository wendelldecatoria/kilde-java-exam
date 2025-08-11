package com.kilde.exam.investor;

import com.kilde.exam.responses.RespondError;
import com.kilde.exam.responses.RespondSuccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class InvestorController {

    @Autowired
    private InvestorService investorService;

    @RequestMapping("/investors")
    public ResponseEntity<Object> getAllInvestors() {
        try {
            List<Investor> investors = investorService.getAllInvestors();
            return RespondSuccess.generateResponse(HttpStatus.OK, true, "OK", investors);
        } catch (Exception e) {
            return RespondError.generateResponse(HttpStatus.BAD_REQUEST, false, e.getMessage());
        }

    }

    @RequestMapping("/investors/{id}")
    public ResponseEntity<Object> getInvestor(@PathVariable UUID id) {
        try {
            Investor investor = investorService.getInvestor(id)
                    .orElseThrow(() -> new Exception("No Investor Found with id " + id + " found."));
            return RespondSuccess.generateResponse(HttpStatus.OK, true, "OK", investor);
        } catch (Exception e) {
            return RespondError.generateResponse(HttpStatus.BAD_REQUEST, false, e.getMessage());
        }

    }

    @RequestMapping(method= RequestMethod.POST, value="/investors")
    public ResponseEntity<Object> createInvestor(@RequestBody InvestorRequest investorRequest) {
        try {
            investorService.addInvestor(investorRequest);
            return RespondSuccess.generateResponse(HttpStatus.OK, true, "OK", investorRequest);
        } catch (Exception e) {
            return RespondError.generateResponse(HttpStatus.BAD_REQUEST, false, e.getMessage());
        }
    }

    @RequestMapping(method=RequestMethod.PUT, value="/investors/{id}")
    public ResponseEntity<Object> updateInvestor(@RequestBody InvestorRequest investorRequest, @PathVariable UUID id) {
        try {
            Investor investor = investorService.updateInvestor(id, investorRequest);
            return RespondSuccess.generateResponse(HttpStatus.OK, true, "OK", investor);
        } catch (Exception e) {
            return RespondError.generateResponse(HttpStatus.BAD_REQUEST, false, e.getMessage());
        }
    }
}
