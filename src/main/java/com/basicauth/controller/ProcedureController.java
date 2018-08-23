package com.basicauth.controller;

import com.basicauth.service.ProcedureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;


/*
    Created By:     Ricky B. NIno
    Created Date:   5/21/2018
    Purpose:        Controller for all Room related API transaction
*/

@RestController
@RequestMapping(value = "mace/api/procedure")
public class ProcedureController {

    @Autowired
    private ProcedureService myService;

    @RequestMapping(value = "read/" , method = RequestMethod.GET)
    public ResponseEntity<?> read() {

        HashMap<String, Object> response = new HashMap<>();

        List myList = myService.getProcedure();
        if(myList != null) {
            response.put("procedureList", myList);
            response.put("responseCode", 200);
            response.put("responseDesc", "Successfully retrieved list.");
        }else{
            response.put("responseCode", 210);
            response.put("responseDesc", "Nothing found.");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "delete/{procedureId}", method = RequestMethod.DELETE)
    public ResponseEntity<HashMap<String,Object>> delete(@PathVariable(value = "procedureId") int procedureId) {
        HashMap<String, Object> response = new HashMap<>();

        try{
            this.myService.delete(procedureId);
            response.put("responseCode", 200);
            response.put("responseDesc", "Successfully deleted record");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        catch(Exception e) {
            response.put("responseCode", -1);
            response.put("responseDesc", "Failed to complete deleting a record.");
            response.put("errorDesc", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
