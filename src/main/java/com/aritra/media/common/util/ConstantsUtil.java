package com.aritra.media.common.util;

import com.aritra.media.common.Constants;

public class ConstantsUtil {
    public static boolean isValidPrivateStatus(String privacy) {
        return privacy.equals(Constants.PrivacyStatus.PRIVATE)
                || privacy.equals(Constants.PrivacyStatus.PUBLIC);

    }
}
