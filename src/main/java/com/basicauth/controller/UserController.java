package com.basicauth.controller;

/**
 * Created by Giancarlo Angulo.
 */

import com.basicauth.data.User;
import com.basicauth.data.VerifiedMember;
import com.basicauth.domain.ImageHolder;
import com.basicauth.service.*;
import com.basicauth.types.LoginJson;
import com.basicauth.types.UserJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private MaceService maceService;

    @Autowired
    private MemService memService;

    @Autowired
    private ClaimsService claimsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private VerifiedMemberService verifiedMemberService;


    @RequestMapping(value = "/user/", method = RequestMethod.GET)
    public ResponseEntity<List<UserJson>> listUsers() {
        List<User> users = userService.findAllUsers();
        List<UserJson> usersJson;
        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            usersJson = users.stream()
                    .map(u -> new UserJson(
                            u.getUsername(), null, u.getMemberCode() , u.getBirthDateString(), u.getLname(), u.getFname(), u.getMi(), u.getSex(), u.getRegisterDevice(),
                            u.getPhoneNo(),u.getEmail(),u.getDateRegistered(), u.getStatus()
                            ))
                    .collect(Collectors.toList());
        }
        return new ResponseEntity<>(usersJson, HttpStatus.OK);
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<UserJson> getUser(@PathVariable("id") long id) {
        System.out.println("Fetching User with id " + id);
        User user = userService.findById(id);
        if (user == null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userToUserJson(user), HttpStatus.OK);
    }


    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<UserJson> deleteUser(@PathVariable("id") long id) {
        System.out.println("Fetching & Deleting User with id " + id);

        User user = userService.findById(id);
        if (user == null) {
            System.out.println("Unable to delete. User with id " + id + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        userService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/register/", method = RequestMethod.POST)
    public ResponseEntity<Void> createUser(@RequestBody UserJson userJson, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating User " + userJson.getUsername());
        System.out.println("with password " + userJson.getPassword());
        System.out.println("with memberCode " + userJson.getMemberCode());
        System.out.println("with fname " + userJson.getFname());
        System.out.println("with lname " + userJson.getLname());
        System.out.println("with mi " + userJson.getMi());
        System.out.println("with phone " + userJson.getPhone());
        System.out.println("with birthDate " + userJson.getBirthDate());
        System.out.println("with sex " + userJson.getSex());



        if (userService.isUsernameUsed(userJson.getUsername())) {
            System.out.println("A User with name " + userJson.getUsername() + " already exist");
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Map memberInfo = memService.getMemberInfo(userJson.getMemberCode());


        userService.saveUser(new User(
                userJson.getUsername(), passwordEncoder.encode(userJson.getPassword()), userJson.getMemberCode() ,(String)memberInfo.get("MEM_LNAME"), (String)memberInfo.get("MEM_FNAME"),
                userJson.getMi(), userJson.getBirthDate(),userJson.getSexInt(), new Date(),
                userJson.getRegisterDevice(),userJson.getPhone(), userJson.getEmail(),"ACTIVE"
                ));
        User user = userService.findByUsername(userJson.getUsername());

        VerifiedMember verifiedMember = new VerifiedMember();
        verifiedMember.setMemCode(userJson.getMemberCode());
        verifiedMember.setIsVerified(0);
        verifiedMember.setIsSkipped(0);
        verifiedMember.setVerifyDate(new Date());
        verifiedMember.setStatus("UNVERIFIED");
        verifiedMember.setAppUsername("SYSTEM");
        verifiedMemberService.saveMember(verifiedMember);



        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }





    @RequestMapping(value = "/verifymember/", method = RequestMethod.GET)
    public ResponseEntity<?> verifyMember(@RequestParam("memberCode")String memberCode, @RequestParam("dob") String date_of_birth) {
        System.out.println("verifyMember");

        /**
         * Start validating if MemberCode has Existing User Account
         */
        //move hasExistingMemUserAcct to Mace Mapper
        if(maceService.hasExistingMemUserAcct(null, memberCode)){
            return new ResponseEntity<>("Member is already registered",HttpStatus.CONFLICT);
        } else {

            /**
             * Start validating Member Account
             */
            Map memberInfo = memService.getMemberInfo(memberCode);
            if(null==memberInfo){
                return new ResponseEntity<>("Member Account not found",HttpStatus.CONFLICT);
            } else {

                /**
                 * Start validating BDay
                 */
                String bdayString = (String) memberInfo.get("BDAY");
                System.out.println("bdayString:"+bdayString);
                SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy");
                SimpleDateFormat sdfParam = new SimpleDateFormat("yyyyMMdd");
                Date bday = null;
                Date bdayParam = null;
                try {
                    bday = sdf.parse(bdayString);
                    System.out.println("bdayParse:"+sdf.format(bday));
                    bdayParam = sdfParam.parse(date_of_birth);
                    if(bdayParam.compareTo(bday)==0){
                        /**
                         * Start validating Member Status
                         */

                        String Mem_OStat_Code = (String) memberInfo.get("Mem_OStat_Code");
                        System.out.println("Mem_OStat_Code:"+Mem_OStat_Code);
                        int memStat = memService.validateMemStat(Mem_OStat_Code);
                        System.out.println("memStat:" + memStat);
                        if(220 == memStat || 500== memStat ){
                            return new ResponseEntity<>("Member account is not valid for registration",HttpStatus.CONFLICT);
                        } else {
                            return new ResponseEntity<>(memberInfo,HttpStatus.OK);
                        }


                    } else {
                        return new ResponseEntity<>("Member Code and Date Of Birth don’t match", HttpStatus.CONFLICT);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return new ResponseEntity<>("Problem Parsing Date of Birth", HttpStatus.CONFLICT);
                }
            }
        }
//        return new ResponseEntity<>(, HttpStatus.OK);
    }


    @RequestMapping(value = "/loginuser/", method = RequestMethod.POST)
    public ResponseEntity<?> loginUser(@RequestBody LoginJson loginJson, UriComponentsBuilder ucBuilder) {

        System.out.println(loginJson.getUsername());
        System.out.println(loginJson.getPassword());
        System.out.println();

        //no encryption yet

        if(maceService.hasExistingMemUserAcct(loginJson.getUsername(), null)){
            if(maceService.matchUsernameAndPassword(loginJson.getUsername(),loginJson.getPassword())){
                if(!maceService.isStatusLocked(loginJson.getUsername())){
                    maceService.resetInvalidLoginCount(loginJson.getUsername());
                    Map user = maceService.getMemUserAccount(loginJson.getUsername());
                    Map response = new HashMap();
                    response.put("message","Login Successful.");
                    response.put("user", user);
                    return new ResponseEntity<>(response, HttpStatus.OK);
                } else {
                    return new ResponseEntity<>("User is locked.", HttpStatus.UNAUTHORIZED);
                }

            } else {
                //Update invalid login
                maceService.increaseInvalidLoginCount(loginJson.getUsername());
                maceService.lockIfNeeded(loginJson.getUsername());
                return new ResponseEntity<>("Invalid Password.", HttpStatus.UNAUTHORIZED);
            }

        } else {

            return new ResponseEntity<>("No User Account for entered username.", HttpStatus.NOT_FOUND);
        }




    }

    @RequestMapping(value = "/viewaccountinfo/{memberCode}", method = RequestMethod.GET)
    public ResponseEntity<?> viewAccountInfo(@PathVariable("memberCode") String memberCode, UriComponentsBuilder ucBuilder) {

        System.out.println("viewAccountInfo"+memberCode);
        Map memberInfo = memService.getMemberInfo(memberCode);
        List dependents = memService.getDependents(memberCode);
        List utilizations = claimsService.getMemberUtilization(memberCode);

        VerifiedMember verifiedMember = maceService.getLatestVerifiedMember(memberCode);


        System.out.println("memberInfo"+memberInfo);
        System.out.println("verifiedMember"+verifiedMember);
        System.out.println("dependents"+dependents);
        System.out.println("utilizations"+utilizations);

        HashMap<String,Object> response = new HashMap<>();

        if(null != memberInfo){
            response.put("responseCode",200);
            response.put("responseDesc","Success");
            response.put("MemberInfo",memberInfo);

            if(verifiedMember==null){
                response.put("VerifiedMember",0);
            } else {
                response.put("VerifiedMember",verifiedMember);
            }

            if(dependents==null){
                response.put("Dependents",0);
            } else {
                response.put("Dependents",dependents);
            }

            if(utilizations==null){
                response.put("Utilization",0);
            } else {
                response.put("Utilization",utilizations);
            }



            return new ResponseEntity<>(response,HttpStatus.OK);
        } else {
            response.put("responseCode",404);
            response.put("responseDesc","NOT FOUND");
            response.put("MemberInfo",0);
            response.put("VerifiedMember",0);
            response.put("Utilization",0);
            response.put("Dependents",0);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/getmemberutil/{memberCode}", method = RequestMethod.GET)
    public ResponseEntity<?> getMemberUtilization(@PathVariable("memberCode") String memberCode, UriComponentsBuilder ucBuilder) {

        System.out.println(memberCode);
        List memberUtilization = claimsService.getMemberUtilization(memberCode);
        if(!memberUtilization.isEmpty()){
            return new ResponseEntity<>(memberUtilization,HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/uploadpicture", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> uploadPicture(@RequestParam("file") MultipartFile file,@RequestParam("memCode") String memCode,
                                           @RequestParam("appUsername") String appUsername, @RequestParam(name = "userType",required = false) String userType) {
        String name = file.getName();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String id = sdf.format(new Date());
        String contentType = file.getContentType();
        ImageHolder imageHolder = new ImageHolder();
        imageHolder.setMemCode(memCode);
        imageHolder.setFileId(id);
        imageHolder.setContentType(contentType);
        imageHolder.setOriginalFileName(file.getOriginalFilename());
        imageHolder.setFileSuffix(file.getOriginalFilename().substring(file.getOriginalFilename().indexOf(".")));
        imageHolder.setFileNameNoSuffix(file.getOriginalFilename().substring(0,file.getOriginalFilename().indexOf(".")));
        System.out.println(name);
        System.out.println(contentType);
        System.out.println(file.getOriginalFilename());//original filename
        System.out.println(file.getOriginalFilename().substring(file.getOriginalFilename().indexOf(".")));//file suffix
        System.out.println(file.getOriginalFilename().substring(0,file.getOriginalFilename().indexOf(".")));//filename without suffix
        if (!file.isEmpty()) {
            try {
                //MOVE TO DATABASE
//                byte[] bytes = file.getBytes();
//                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(id)));
//                stream.write(bytes);
//                stream.close();
                imageHolder.setContent(file.getBytes());
                maceService.saveImage(imageHolder);

                VerifiedMember verifiedMember = maceService.getLatestVerifiedMember(memCode);
                if(null != verifiedMember ){
                    if(null == userType || userType.equalsIgnoreCase("COORDINATOR")) {
                        verifiedMember.setIsVerified(1);
                        verifiedMember.setStatus("VERIFIED");
                    }
                    else {
                        verifiedMember.setIsVerified(0);
                        verifiedMember.setStatus("UNVERIFIED");
                    }
                    verifiedMember.setAppUsername(appUsername);
                    verifiedMember.setIsSkipped(0);
                    verifiedMember.setVerifyDate(new Date());

                    verifiedMember.setAppUsername("MEMBER");
                    verifiedMemberService.saveMember(verifiedMember);
                } else {
                    verifiedMember = new VerifiedMember();
                    verifiedMember.setMemCode(memCode);
                    verifiedMember.setIsVerified(0);
                    verifiedMember.setIsSkipped(0);
                    verifiedMember.setAppUsername(appUsername);
                    verifiedMember.setVerifyDate(new Date());
                    verifiedMember.setStatus("UNVERIFIED");
                    verifiedMember.setAppUsername("MEMBER");
                    verifiedMemberService.saveMember(verifiedMember);
                }

                return new ResponseEntity<>("Uploaded " + name + " with id " + id + "!",HttpStatus.OK);

            } catch (Exception e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>("File is empty", HttpStatus.NO_CONTENT);
        }
    }

    @RequestMapping(value = "/downloadpicture/{fileId}", method = RequestMethod.GET)
    public ResponseEntity<?> downloadPicture (@PathVariable("fileId") String fileId){
        ImageHolder imageHolder = maceService.retrieveImage(fileId);

        if(imageHolder!=null){
            HttpHeaders header = new HttpHeaders();
            header.setContentType(MediaType.parseMediaType(imageHolder.getContentType()));
            header.set("Content-Disposition",
                    "attachment; filename=" + imageHolder.getOriginalFileName());
            header.setContentLength(imageHolder.getContent().length);
            return new ResponseEntity<> ( imageHolder.getContent() , header,HttpStatus.OK);
        } else {
            return new ResponseEntity<Object>("No Picture",HttpStatus.NOT_FOUND);
        }

    }

    @RequestMapping(value = "/deletepicture/{memCode}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deletePicture (@PathVariable("memCode") String memCode){
        maceService.deleteImage(memCode);

        return new ResponseEntity<> ( "Image Deleted." , HttpStatus.OK);
    }

    @RequestMapping(value = "/verifyAccount/", method = RequestMethod.POST)
    public ResponseEntity<?> verifyAccount (@RequestBody VerifiedMember verifiedMember){
        if(verifiedMember.getAppUsername()==null){
            verifiedMember.setAppUsername("SYSTEM");
        }

        verifiedMember.setStatus("ACTIVE");
        verifiedMember.setIsVerified(1);
        verifiedMember.setVerifyDate(new Date());
        verifiedMemberService.saveMember(verifiedMember);
        HashMap<String,Object> response = new HashMap<>();
        response.put("responseCode",200);
        response.put("responseDesc","Success");
        return new ResponseEntity<> ( response , HttpStatus.OK);
    }


    @RequestMapping(value = "/skipVerification/", method = RequestMethod.POST)
    public ResponseEntity<?> skipVerification (@RequestBody VerifiedMember verifiedMember){

        verifiedMember.setStatus("UNVERIFIED");
        verifiedMember.setIdType(null);
        verifiedMember.setIdNumber(null);
        verifiedMember.setIsVerified(0);
        verifiedMember.setIsSkipped(1);
        verifiedMember.setVerifyDate(new Date());
        verifiedMemberService.saveMember(verifiedMember);
        HashMap<String,Object> response = new HashMap<>();
        response.put("responseCode",200);
        response.put("responseDesc","Success");
        return new ResponseEntity<> ( response , HttpStatus.OK);
    }





    private static UserJson userToUserJson(User user) {
        // String mi, String sex, String registerDevice, String phone, String email
        return new UserJson(
                user.getUsername(),
                null,
                user.getMemberCode(),
                user.getBirthDateString(),
                user.getLname(),
                user.getFname(),
                user.getMi(),
                user.getSex(),
                user.getRegisterDevice(),
                user.getPhoneNo(),
                user.getEmail()
        );
    }


}