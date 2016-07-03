package com.onlineteammakers.bottlechat.Intent;

import com.twitter.sdk.android.core.models.User;

/**
 * Created by jason on 7/3/16.
 */
public class UserInformation {

    private static String UserPhoneNumber = "";
    private static String UserEmail = "";
    private static String UserName = "";
    private static String UserNickName = "";

    public UserInformation() {}

    public static void setUserEmail(String email){
        UserEmail = email;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getUserNickName() {
        return UserNickName;
    }

    public static void setUserNickName(String userNickName) {
        UserNickName = userNickName;
    }

    public String getUserEmail() {
        return UserEmail;
    }

    public String getUserPhoneNumber() {
        return UserPhoneNumber;
    }

    public static void setUserPhoneNumber(String userPhoneNumber) {
        UserPhoneNumber = userPhoneNumber;
    }

}
