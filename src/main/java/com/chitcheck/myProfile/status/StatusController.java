package com.chitcheck.myProfile.status;

import com.chitcheck.auth.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/status")
public class StatusController {

    private final StatusService statusService;

    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    @GetMapping
    public ResponseEntity<List<StatusUpdateDto>> getStatusUpdates(@AuthenticationPrincipal User user) {
        List<StatusUpdateDto> statuses = statusService.getStatusUpdatesForCompany(user);
        return ResponseEntity.ok(statuses);
    }

    // NEW ENDPOINT TO GET A SINGLE STATUS UPDATE BY ID
    @GetMapping("/{id}")
    public ResponseEntity<StatusUpdateDto> getStatusById(@PathVariable Long id, @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(statusService.getStatusUpdateById(id, user));
    }

    // NEW ENDPOINT TO DELETE A STATUS UPDATE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStatus(@PathVariable Long id, @AuthenticationPrincipal User user) {
        statusService.deleteStatusUpdate(id, user);
        return ResponseEntity.noContent().build();
    }
}