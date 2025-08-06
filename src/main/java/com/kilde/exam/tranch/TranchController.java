package com.kilde.exam.tranch;

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
public class TranchController {

    @Autowired
    private TranchService tranchService;

    @RequestMapping("/tranches")
    public ResponseEntity<Object> getAllTranches() {
        try {
            List<Tranch> tranchList = tranchService.getAllTranches();
            return RespondSuccess.generateResponse(HttpStatus.OK, true, "OK", tranchList);
        } catch (Exception e) {
            return RespondError.generateResponse(HttpStatus.BAD_REQUEST, false, e.getMessage());
        }
    }

    @RequestMapping("/tranches/{id}")
    public ResponseEntity<Object> getTranch(@PathVariable UUID id) {
        try {
            Optional<Tranch> tranch = Optional.ofNullable(tranchService.getTranch(id).orElseThrow(() -> new Exception("No Tranch Found with id " + id + " found.")));
            return RespondSuccess.generateResponse(HttpStatus.OK, true, "OK", tranch);
        } catch (Exception e) {
            return RespondError.generateResponse(HttpStatus.BAD_REQUEST, false, e.getMessage());
        }
    }

    @RequestMapping(method= RequestMethod.POST, value="/tranches")
    public ResponseEntity<Object> addTranch(@RequestBody Tranch tranch) {
        try {
            tranchService.addTranch(tranch);
            return RespondSuccess.generateResponse(HttpStatus.OK, true, "OK", tranch);
        } catch (Exception e) {
            return RespondError.generateResponse(HttpStatus.BAD_REQUEST, false, e.getMessage());
        }
    }

    @RequestMapping(method= RequestMethod.PUT, value="/tranches/{id}")
    public ResponseEntity<Object> updateTranch(@RequestBody Tranch tranch) {
        try {
            tranchService.updateTranch(tranch);
            return RespondSuccess.generateResponse(HttpStatus.OK, true, "OK", tranch);
        } catch (Exception e) {
            return RespondError.generateResponse(HttpStatus.BAD_REQUEST, false, e.getMessage());
        }
    }
}
