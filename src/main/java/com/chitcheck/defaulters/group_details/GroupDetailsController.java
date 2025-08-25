package com.chitcheck.defaulters.group_details;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/group-details")
public class GroupDetailsController {

    private final GroupDetailsService groupDetailsService;

    public GroupDetailsController(GroupDetailsService groupDetailsService) {
        this.groupDetailsService = groupDetailsService;
    }

    @PostMapping
    public ResponseEntity<GroupDetails> createGroup(@Valid @RequestBody GroupDetailsDto dto) {
        GroupDetails createdGroup = groupDetailsService.createGroupDetails(dto);
        return new ResponseEntity<>(createdGroup, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<GroupDetails>> getAllGroups() {
        return ResponseEntity.ok(groupDetailsService.getAllGroupDetails());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GroupDetails> getGroupById(@PathVariable Integer id) {
        return ResponseEntity.ok(groupDetailsService.getGroupDetailsById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GroupDetails> updateGroup(@PathVariable Integer id, @Valid @RequestBody GroupDetailsDto dto) {
        return ResponseEntity.ok(groupDetailsService.updateGroupDetails(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGroup(@PathVariable Integer id) {
        groupDetailsService.deleteGroupDetails(id);
        return ResponseEntity.noContent().build();
    }
}