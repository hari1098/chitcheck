package com.chitcheck.myProfile.directordetails.controller;

import com.chitcheck.auth.model.User;
import com.chitcheck.myProfile.directordetails.dto.DirectorDto;
import com.chitcheck.myProfile.directordetails.service.DirectorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/directors")
public class DirectorController {

    private final DirectorService directorService;

    public DirectorController(DirectorService directorService) {
        this.directorService = directorService;
    }

    @PostMapping
    public ResponseEntity<DirectorDto> createDirector(
            @AuthenticationPrincipal User user,
            @Valid @RequestBody DirectorDto directorDto) {
        DirectorDto createdDirector = directorService.createDirector(user, directorDto);
        return new ResponseEntity<>(createdDirector, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DirectorDto> updateDirector(
            @PathVariable Integer id,
            @Valid @RequestBody DirectorDto directorDto,
            @AuthenticationPrincipal User user) {
        DirectorDto updatedDirector = directorService.updateDirector(id, directorDto, user);
        return ResponseEntity.ok(updatedDirector);
    }

    @GetMapping
    public ResponseEntity<List<DirectorDto>> getDirectors(@AuthenticationPrincipal User user) {
        List<DirectorDto> directors = directorService.getDirectorsForCompany(user);
        return ResponseEntity.ok(directors);
    }

    // NEW ENDPOINT TO GET A SINGLE DIRECTOR BY ID
    @GetMapping("/{id}")
    public ResponseEntity<DirectorDto> getDirectorById(
            @PathVariable Integer id,
            @AuthenticationPrincipal User user) {
        DirectorDto director = directorService.getDirectorById(id, user);
        return ResponseEntity.ok(director);
    }

    // NEW ENDPOINT TO DELETE A DIRECTOR
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDirector(
            @PathVariable Integer id,
            @AuthenticationPrincipal User user) {
        directorService.deleteDirector(id, user);
        return ResponseEntity.noContent().build(); // Returns 204 No Content on success
    }
}