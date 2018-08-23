package com.basicauth.controller;

/**
 * Created by Giancarlo Angulo.
 */

import com.basicauth.mapper.MemMapper;
import com.basicauth.service.ClaimsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;


@RestController
@RequestMapping(value = "/membership/")
public class MembershipController {

//    @Autowired
//    private UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(MembershipController.class);

    @Autowired
    private MemMapper memMapper;

    @Autowired
    private ClaimsService claimsService;

    @RequestMapping(value = "getAge/{birthDate}", method = RequestMethod.GET)
    public ResponseEntity<?> getAge(@PathVariable("birthDate") String birthDate) {
        System.out.println("in getAge");
        System.out.println("birthDate:"+birthDate);
        System.out.println("birthDate:"+birthDate.replace("-","/"));
        birthDate = birthDate.replace("-","/");

//        System.out.println(memMapper.getAll());
        return new ResponseEntity<>(memMapper.getAge(birthDate),HttpStatus.OK);
    }

    @RequestMapping(value = "getRemainingLimit/{memberCode}", method = RequestMethod.GET)
    public ResponseEntity<?> getRemainingLimit(@PathVariable("memberCode") String memberCode) {
        return new ResponseEntity<>(claimsService.getRemainingLimit(memberCode), HttpStatus.OK);
    }

    @RequestMapping(value="getDentalBenefitsByMemberCode", method = RequestMethod.GET)
    public ResponseEntity<?> getDentalBenefitsByMemberCode(@RequestParam("memberCode") String memberCode){
        HashMap<String, Object> response = new HashMap<>();
        //TODO wait for new reference table for Dental Benefits.

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}