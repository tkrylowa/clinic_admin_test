package ru.spring.tkrylova.clinicadminhomework.controller;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.spring.tkrylova.clinicadminhomework.dto.ClinicDto;

@RestController
@RequestMapping("/clinic")
public class ClinicController {


    @PostMapping("/create")

    public int createClinic(@RequestBody(required = false) ClinicDto clinicDto) {
        if (clinicDto != null && clinicDto.getId() < 0) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(400));
        }
        return 1;
    }

    //    GET http://domain.com/clinic/123

    @GetMapping("/{clinicId}")
    public ClinicDto getById(@PathVariable int clinicId) {
        return new ClinicDto();
    }

    //    GET http://domain.com/clinic/name/123

    @GetMapping("/name/{clinicName}")
    public ClinicDto getByName(@PathVariable String clinicName) {
        return new ClinicDto();
    }

    //    GET http://domain.com/clinic/filter?id=123
    @GetMapping("/filter")
    public ClinicDto getByIdParamFilter(@RequestParam(name = "clinic_id") int id) {
        return new ClinicDto();
    }
}
