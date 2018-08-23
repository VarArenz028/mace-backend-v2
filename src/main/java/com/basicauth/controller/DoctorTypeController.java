package com.basicauth.controller;

/*
    Created By:     Ricky B. NIno
    Created Date:   5/22/2018
    Purpose:        Controller for all Room related API transaction
*/

import com.basicauth.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "mace/api/doctor")
public class DoctorTypeController
{
    @Autowired
    private DoctorService doctorService;

    @RequestMapping(value = "read/", method = RequestMethod.GET)
    public ResponseEntity<?> read()
    {
        HashMap<String, Object> response = new HashMap<String, Object>();

        List list = doctorService.getDoctorType();

        if(!list.isEmpty())
        {
            response.put("doctorList", list);
            response.put("responseCode", 200);
            response.put("responseDesc", "Successfully retrieved list.");
        }
        else if(list.isEmpty())
        {
            response.put("responseCode", 210);
            response.put("responseDesc", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @RequestMapping(value = "delete/{docTypeId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable(name = "docTypeId") int docTypeId)
    {
        HashMap<String, Object> response = new HashMap<String, Object>();
        try
        {
            this.doctorService.delete(docTypeId);
            response.put("responseCode", 200);
            response.put("responseDesc", "Successfully deleted record");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        catch(Exception e)
        {
            response.put("responseCode", -1);
            response.put("responseDesc", "Failed to complete deleting a record.");
            response.put("errorDesc", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
