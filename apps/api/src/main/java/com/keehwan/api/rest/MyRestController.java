package com.keehwan.api.rest;

import com.keehwan.api.authentication.support.SecurityContextSupporter;
import com.keehwan.api.rest.dto.ApiResponse;
import com.keehwan.api.rest.dto.MyDTO.MyResponse;
import com.keehwan.api.rest.dto.MyDTO.NicknameUpdateRequest;
import com.keehwan.api.rest.dto.MyDTO.ProfileImageUpdateRequest;
import com.keehwan.api.rest.exceptions.BaseRestException;
import com.keehwan.api.share.BaseRestController;
import com.keehwan.core.account.domain.UserAccount;
import com.keehwan.core.account.service.usecases.UserAccountNicknameUpdateUsecase;
import com.keehwan.core.account.service.usecases.UserAccountProfileImageDeleteUsecase;
import com.keehwan.core.account.service.usecases.UserAccountProfileImageUpdateUsecase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class MyRestController implements BaseRestController {
    private final UserAccountNicknameUpdateUsecase userAccountNicknameUpdateUsecase;
    private final UserAccountProfileImageUpdateUsecase userAccountProfileImageUpdateUsecase;
    private final UserAccountProfileImageDeleteUsecase userAccountProfileImageDeleteUsecase;

    @GetMapping("/api/v1/my")
    public ApiResponse<MyResponse> my() {
        UserAccount account = Optional.ofNullable(SecurityContextSupporter.get())
                .orElseThrow(() -> new BaseRestException(null, HttpStatus.UNAUTHORIZED));
        return ApiResponse.just(MyResponse.of(account));
    }

    @PatchMapping("/api/v1/my/nickname")
    public ApiResponse<MyResponse> changeMyNickname(
            @Valid @RequestBody NicknameUpdateRequest request, BindingResult bindingResult) {
        hasError(bindingResult);

        UserAccount account = Optional.ofNullable(SecurityContextSupporter.get())
                .orElseThrow(() -> new BaseRestException(null, HttpStatus.UNAUTHORIZED));

        UserAccount changedAccount = userAccountNicknameUpdateUsecase.updateNickname(account.getUsername(), request.nickname());

        return ApiResponse.just(MyResponse.of(changedAccount));
    }

    @PatchMapping("/api/v1/my/profile-image")
    public ApiResponse<MyResponse> changeMyProfileImage(
            @Valid @RequestBody ProfileImageUpdateRequest request, BindingResult bindingResult) {

        hasError(bindingResult);

        UserAccount account = Optional.ofNullable(SecurityContextSupporter.get())
                .orElseThrow(() -> new BaseRestException(null, HttpStatus.UNAUTHORIZED));

        UserAccount changedAccount = userAccountProfileImageUpdateUsecase.updateProfileImage(account.getUsername(), request.profileImage());

        return ApiResponse.just(MyResponse.of(changedAccount));
    }

    @DeleteMapping("/api/v1/my/profile-image")
    public ApiResponse<MyResponse> deleteMyProfileImage() {
        UserAccount account = Optional.ofNullable(SecurityContextSupporter.get())
                .orElseThrow(() -> new BaseRestException(null, HttpStatus.UNAUTHORIZED));

        UserAccount changedAccount = userAccountProfileImageDeleteUsecase.deleteProfileImage(account.getUsername());

        return ApiResponse.just(MyResponse.of(changedAccount));
    }
}
