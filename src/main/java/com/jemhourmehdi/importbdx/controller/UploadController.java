package com.jemhourmehdi.importbdx.controller;

import com.jemhourmehdi.importbdx.service.UploadService;
import com.jemhourmehdi.importbdx.payload.BordereauPayload;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/upload")
@RequiredArgsConstructor
public class UploadController {

    private final UploadService uploadService;

    @PostMapping("/{filename}")
    public HttpEntity<Void> upload(@RequestBody BordereauPayload data, @PathVariable String filename) {
        uploadService.upload(data, filename);
        return ResponseEntity.ok().build();
    }
}
