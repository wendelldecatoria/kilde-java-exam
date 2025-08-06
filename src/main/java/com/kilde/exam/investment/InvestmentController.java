package com.kilde.exam.investment;

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
public class InvestmentController {

    @Autowired
    private InvestmentService investmentService;

    @RequestMapping("/investments")
    public ResponseEntity<Object> getAllInvestments() {
        try {
            List<Investment> investments = investmentService.getAllInvestments();
            return RespondSuccess.generateResponse(HttpStatus.OK, true, "OK", investments);
        } catch (Exception e) {
            return RespondError.generateResponse(HttpStatus.BAD_REQUEST, false, e.getMessage());
        }

    }

    @RequestMapping("/investments/{id}")
    public ResponseEntity<Object> getInvestment(@PathVariable UUID id) {
        try {
            Optional<Investment> investor = Optional.ofNullable(investmentService.getInvestment(id).orElseThrow(() -> new Exception("No Investment Found with id " + id + " found.")));
            return RespondSuccess.generateResponse(HttpStatus.OK, true, "OK", investor);
        } catch (Exception e) {
            return RespondError.generateResponse(HttpStatus.BAD_REQUEST, false, e.getMessage());
        }

    }

    @RequestMapping(method= RequestMethod.POST, value="/investments")
    public ResponseEntity<Object> addInvestment(@RequestBody InvestmentRequest investmentRequest) {
        try {
            Investment investment = investmentService.addInvestment(investmentRequest);
            return RespondSuccess.generateResponse(HttpStatus.OK, true, "OK", investment);
        } catch (Exception e) {
            return RespondError.generateResponse(HttpStatus.BAD_REQUEST, false, e.getMessage());
        }
    }

    // TODO: implement update and delete investment
}
