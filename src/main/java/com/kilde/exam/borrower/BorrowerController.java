package com.kilde.exam.borrower;

import com.kilde.exam.responses.RespondError;
import com.kilde.exam.responses.RespondSuccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class BorrowerController {
    
    @Autowired
    private BorrowerService borrowerService;

    @RequestMapping("/borrowers")
    public ResponseEntity<Object> getAllBorrowers() {
        try {
            List<Borrower> borrowers = borrowerService.getAllBorrowers();
            return RespondSuccess.generateResponse(HttpStatus.OK, true, "OK", borrowers);
        } catch (Exception e) {
            return RespondError.generateResponse(HttpStatus.BAD_REQUEST, false, e.getMessage());
        }

    }

    @RequestMapping("/borrowers/{id}")
    public ResponseEntity<Object> getBorrower(@PathVariable UUID id) {
        try {
            Borrower investor = borrowerService.getBorrower(id)
                    .orElseThrow(() -> new Exception("No Borrower Found with id " + id + " found."));
            return RespondSuccess.generateResponse(HttpStatus.OK, true, "OK", investor);
        } catch (Exception e) {
            return RespondError.generateResponse(HttpStatus.BAD_REQUEST, false, e.getMessage());
        }

    }

    @RequestMapping(method= RequestMethod.POST, value="/borrowers")
    public ResponseEntity<Object> createBorrower(@RequestBody BorrowerRequest borrowerRequest) {
        try {
            Borrower borrower = borrowerService.addBorrower(borrowerRequest);
            return RespondSuccess.generateResponse(HttpStatus.OK, true, "OK", borrower);
        } catch (Exception e) {
            return RespondError.generateResponse(HttpStatus.BAD_REQUEST, false, e.getMessage());
        }
    }

    @RequestMapping(method=RequestMethod.PUT, value="/borrowers/{id}")
    public ResponseEntity<Object> updateBorrower(@RequestBody BorrowerRequest borrowerRequest, @PathVariable UUID id) {
        try {
            Borrower investor = borrowerService.updateBorrower(id, borrowerRequest);
            return RespondSuccess.generateResponse(HttpStatus.OK, true, "OK", investor);
        } catch (Exception e) {
            return RespondError.generateResponse(HttpStatus.BAD_REQUEST, false, e.getMessage());
        }
    }
}
