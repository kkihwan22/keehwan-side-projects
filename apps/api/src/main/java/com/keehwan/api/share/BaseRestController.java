package com.keehwan.api.share;

import com.keehwan.api.consts.ErrorCodes;
import com.keehwan.api.rest.exceptions.BadRequestParameterException;
import org.springframework.validation.BindingResult;

public interface BaseRestController {

    default void hasError(BindingResult bindingResult) {

        if (!bindingResult.hasErrors()) {
            return;
        }

        throw new BadRequestParameterException(ErrorCodes.BAD_REQUEST, bindingResult.getFieldErrors());
    }

}
