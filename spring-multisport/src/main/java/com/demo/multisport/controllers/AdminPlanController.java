package com.demo.multisport.controllers;


import com.demo.multisport.dto.PlanDto;
import com.demo.multisport.exceptions.plan.DuplicatePlanException;
import com.demo.multisport.services.MultipartService;
import com.demo.multisport.services.impl.PlanMultipartService;
import com.demo.multisport.services.user.AdminService;
import org.apache.catalina.valves.rewrite.QuotedStringTokenizer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.SecondaryTable;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("multisport/admin")
public class AdminPlanController {

    private final AdminService adminService;

    public AdminPlanController(AdminService adminService) {
        this.adminService = adminService;
    }

    private Set<String> stringToSetAddresses(String string) {
        StringTokenizer stringTokenizer = new StringTokenizer(string, ",");
        Set<String> tokenizingCommaStrings = new HashSet<>();

        while(stringTokenizer.hasMoreTokens()) {
            tokenizingCommaStrings.add(stringTokenizer.nextToken());
        }

        return tokenizingCommaStrings
                .stream()
                .map(quotedString -> {
                    return quotedString.substring(1, quotedString.length() - 1);
                })
                .collect(Collectors.toSet());

    }

    @PostMapping(value = "/newplan", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Optional<PlanDto>> addPlan(@RequestPart String name,
                                                     @RequestPart String price,
                                                     @RequestPart MultipartFile file,
                                                     @RequestPart String centersAddresses
                                                     ) {
        PlanDto planDto;
        try {

            Set<String> addresses = stringToSetAddresses(centersAddresses);

            Double priceConverted = Double.parseDouble(String.format("%.2f", Double.parseDouble(price)));
            planDto = new PlanDto(name, priceConverted, addresses);
        } catch (Exception e) {
            return new ResponseEntity<>(Optional.empty(), HttpStatus.OK);
        }


        try {
            adminService.addPlan(planDto);
            MultipartService multipartService = new PlanMultipartService();
            multipartService.save(file);
            return new ResponseEntity<>(Optional.of(planDto), HttpStatus.OK);
        } catch (DuplicatePlanException e) {
            return new ResponseEntity<>(Optional.empty(), HttpStatus.OK);
        }

    }

    @GetMapping("/plans")
    public ResponseEntity<List<PlanDto>> getAllPlans() {
        return new ResponseEntity<>(adminService.getAllPlans(), HttpStatus.OK);
    }
}
