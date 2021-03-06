package com.maze.student.Status;

import com.maze.student.security.SecuredRestController;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/status")
public class StatusController   implements SecuredRestController {

    StatusService statusService;
    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }
    @PostMapping
    public StatusDTO addStatus(@RequestBody Status status) {
         return statusService.addStatus(status);
    }



    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> findAll(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size) {
        CollectionModel<StatusDTO> statusDTOS = statusService.findAll(page, size);
        if (statusDTOS != null) {
            return ResponseEntity.ok(statusDTOS);
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> findStatusById(@PathVariable Long id) {
        StatusDTO statusDTO = statusService.findStatusById(id);
        if (statusDTO != null) return ResponseEntity.ok(statusDTO);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Long> deleteStatus(@PathVariable Long id) {

        var isRemoved = statusService.deleteById(id);

        if (isRemoved == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
