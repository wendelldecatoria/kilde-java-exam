package com.kilde.exam.bond;

import com.kilde.exam.responses.RespondError;
import com.kilde.exam.responses.RespondSuccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class BondController {
    
    @Autowired
    private BondService bondService;

    @RequestMapping("/bonds")
    public ResponseEntity<Object> getAllBonds() {
        try {
            List<Bond> bonds = bondService.getAllBonds();
            return RespondSuccess.generateResponse(HttpStatus.OK, true, "OK", bonds);
        } catch (Exception e) {
            return RespondError.generateResponse(HttpStatus.BAD_REQUEST, false, e.getMessage());
        }

    }

    @RequestMapping("/bonds/{id}")
    public ResponseEntity<Object> getBond(@PathVariable UUID id) {
        try {
            Bond bond = bondService.getBond(id)
                    .orElseThrow(() -> new Exception("No Bond Found with id " + id + " found."));
            return RespondSuccess.generateResponse(HttpStatus.OK, true, "OK", bond);
        } catch (Exception e) {
            return RespondError.generateResponse(HttpStatus.BAD_REQUEST, false, e.getMessage());
        }

    }

    @RequestMapping(method= RequestMethod.POST, value="/bonds")
    public ResponseEntity<Object> createBond(@RequestBody BondRequest bondRequest) {
        try {
            Bond bond = bondService.createBond(bondRequest);
            return RespondSuccess.generateResponse(HttpStatus.OK, true, "OK", bond);
        } catch (Exception e) {
            return RespondError.generateResponse(HttpStatus.BAD_REQUEST, false, e.getMessage());
        }
    }

    @RequestMapping(method=RequestMethod.PUT, value="/bonds/{id}")
    public ResponseEntity<Object> updateBond(@RequestBody BondRequest bondRequest, @PathVariable UUID id) {
        try {
            Bond bond = bondService.updateBond(id, bondRequest);
            return RespondSuccess.generateResponse(HttpStatus.OK, true, "OK", bond);
        } catch (Exception e) {
            return RespondError.generateResponse(HttpStatus.BAD_REQUEST, false, e.getMessage());
        }
    }
}
