package com.kilde.exam.repayment;

import com.kilde.exam.investment.InvestmentRequest;
import com.kilde.exam.investor.Investor;
import com.kilde.exam.responses.RespondError;
import com.kilde.exam.responses.RespondSuccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class RepaymenScheduleController {

    @Autowired
    private RepaymentScheduleService repaymentScheduleService;

    @RequestMapping("/repayment-schedules")
    public ResponseEntity<Object> getAllRepaymentSchedules() {
        try {
            List<RepaymentSchedule> repaymentScheduleList = repaymentScheduleService.getAllRepayments();
            return RespondSuccess.generateResponse(HttpStatus.OK, true, "OK", repaymentScheduleList);
        } catch (Exception e) {
            return RespondError.generateResponse(HttpStatus.BAD_REQUEST, false, e.getMessage());
        }
    }

    @RequestMapping("/repayment-schedules/investment/{investmentId}")
    public ResponseEntity<Object> getRepaymentScheduleByInvestment(RepaymentScheduleRequest repaymentScheduleRequest) {
        try {
            List<RepaymentSchedule> repaymentScheduleList = repaymentScheduleService.getRepaymentsByInvestment(repaymentScheduleRequest.getInvestmentId());
            return RespondSuccess.generateResponse(HttpStatus.OK, true, "OK", repaymentScheduleList);
        } catch (Exception e) {
            return RespondError.generateResponse(HttpStatus.BAD_REQUEST, false, e.getMessage());
        }
    }

    @RequestMapping(method= RequestMethod.POST, value="/repayment-schedules/process")
    public ResponseEntity<Object> addRepaymentSchedule(@RequestBody RepaymentScheduleRequest repaymentScheduleRequest) throws Exception {
        try {
            List<RepaymentSchedule> repaymentSchedule = repaymentScheduleService.processRepayment(repaymentScheduleRequest.getInvestmentId());
            return RespondSuccess.generateResponse(HttpStatus.OK, true, "OK", repaymentSchedule);
        } catch (Exception e) {
            return RespondError.generateResponse(HttpStatus.BAD_REQUEST, false, e.getMessage());
        }
    }

    @RequestMapping(method=RequestMethod.PUT, value="/repayment-schedules/{id}")
    public ResponseEntity<Object> updateInvestor(@RequestBody RepaymentScheduleRequest repaymentScheduleRequest, @PathVariable UUID id) {
        try {
            RepaymentSchedule repaymentSchedule = repaymentScheduleService.updateRepayment(id, repaymentScheduleRequest);
            return RespondSuccess.generateResponse(HttpStatus.OK, true, "OK", repaymentSchedule);
        } catch (Exception e) {
            return RespondError.generateResponse(HttpStatus.BAD_REQUEST, false, e.getMessage());
        }
    }
}
