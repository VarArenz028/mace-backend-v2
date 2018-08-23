package com.basicauth.controller;

import com.basicauth.service.MaceService;
import com.basicauth.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/*
    Created By:     Ricky B. NIno
    Created Date:   5/21/2018
    Purpose:        Controller for all Room related API transaction
*/

@RestController
@RequestMapping(value = "/api")
public class RoomController {

    @Autowired
    private RoomService myService;

    @RequestMapping(value = "getRoomType/" , method = RequestMethod.GET)
    public ResponseEntity<?> getRoomType() {

        HashMap<String, Object> response = new HashMap<>();

        List myList = myService.getRoomType();
        if(myList != null) {
            response.put("roomTypeList", myList);
            response.put("responseCode", 200);
            response.put("responseDesc", "Successfully retrieved list.");
        }else{
            response.put("responseCode", 210);
            response.put("responseDesc", "Nothing found.");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
