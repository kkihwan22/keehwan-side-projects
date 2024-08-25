package com.keehwan.core.account.domain;

import com.keehwan.share.domain.BaseCreatedAndUpdatedDateTime;
import com.keehwan.share.domain.code.Browser;
import com.keehwan.share.domain.code.OperationSystem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
public class UserAccountDevice extends BaseCreatedAndUpdatedDateTime {

    private @Nullable Long id;
    private @NotNull String deviceId;
    private @NotNull OperationSystem os;
    private @NotNull Browser browser;
}
