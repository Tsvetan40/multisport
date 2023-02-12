package com.demo.multisport.controllers;


import com.demo.multisport.dto.PlanDto;
import com.demo.multisport.exceptions.plan.DuplicatePlanException;
import com.demo.multisport.services.user.AdminService;
import com.demo.multisport.utils.FileUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("multisport/admin")
public class AdminPlanController {

    private final AdminService adminService;

    public AdminPlanController(AdminService adminService) {
        this.adminService = adminService;
    }

    private Set<String> stringToSetAddresses(String string) {
        StringTokenizer stringTokenizer = new StringTokenizer(string, ";");
        Set<String> tokenizingCommaStrings = new HashSet<>();

        while(stringTokenizer.hasMoreTokens()) {
            tokenizingCommaStrings.add(stringTokenizer.nextToken());
        }

        return tokenizingCommaStrings;
    }

    @PostMapping(value = "/newplan", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Optional<PlanDto>> addPlan(@RequestPart String name,
                                                     @RequestPart String price,
                                                     @RequestPart MultipartFile file,
                                                     @RequestPart String centersAddresses,
                                                     HttpSession session) {
        if (session.getAttribute("user") == null) {
            return new ResponseEntity<>(Optional.empty(), HttpStatus.FORBIDDEN);
        }

        PlanDto planDto;
        try {
            Set<String> addresses = stringToSetAddresses(centersAddresses);
            Double priceConverted = Double.parseDouble(String.format("%.2f", Double.parseDouble(price)));

            planDto = new PlanDto(name, priceConverted, addresses);
        } catch (Exception e) {
            return new ResponseEntity<>(Optional.empty(), HttpStatus.BAD_REQUEST);
        }


        try {
            String filePath = FileUtil.savePlanImage(file);
            adminService.addPlan(planDto, filePath);
            return new ResponseEntity<>(Optional.of(planDto), HttpStatus.OK);
        } catch (DuplicatePlanException e) {
            return new ResponseEntity<>(Optional.empty(), HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/plans")
    public ResponseEntity<List<PlanDto>> getAllPlans() {
        try {
            return new ResponseEntity<>(adminService.getAllPlans(), HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(new LinkedList<PlanDto>(), HttpStatus.OK);
        }
    }
}
