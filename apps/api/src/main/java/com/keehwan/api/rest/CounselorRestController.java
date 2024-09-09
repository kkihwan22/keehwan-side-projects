package com.keehwan.api.rest;

import com.keehwan.api.rest.dto.ApiResponse;
import com.keehwan.api.rest.dto.CounselorDTO.CounselorSubmissionCreateRequest;
import com.keehwan.api.rest.facade.CounselorApplication;
import com.keehwan.api.share.BaseRestController;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class CounselorRestController implements BaseRestController {
    private final CounselorApplication counselorApplication;

    @PostMapping("/api/v1/counselor-submission/")
    public ApiResponse<Void> createCounselorSubmission(@Valid @RequestBody CounselorSubmissionCreateRequest request, BindingResult bindingResult) {
        hasError(bindingResult);
        counselorApplication.createCounselorSubmission(request);
        return ApiResponse.just(null);
    }
}
