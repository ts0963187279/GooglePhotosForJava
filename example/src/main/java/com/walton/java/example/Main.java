/*
 * Copyright (C) 2017 RS Wong <ts0963187279@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.walton.java.example;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.gdata.client.photos.PicasawebService;
import com.walton.java.GooglePhotosForJava.processor.DownloadImage;
import com.walton.java.accessgoogleservice.module.OAuth2Data;
import com.walton.java.accessgoogleservice.processor.*;

import java.io.IOException;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Main{
    public static void main(String[] args) throws IOException {
        ResourceBundle res = ResourceBundle.getBundle("local");
        String clientId = res.getString("client_id");
        String clientSecrets = res.getString("client_secrets");
        OAuth2Data oAuth2Data = new OAuth2Data();
        oAuth2Data.addScope("https://picasaweb.google.com/data/");
        oAuth2Data.setClientId(clientId);
        oAuth2Data.setClientSecrets(clientSecrets);
        RefreshTokenStorage refreshTokenStorage = new RefreshTokenStorage();
        RefreshTokenIsValid refreshTokenIsValid = new RefreshTokenIsValid(oAuth2Data);
        GetAccessToken getAccessToken = new GetAccessToken(oAuth2Data);
        String refreshToken = refreshTokenStorage.getToken();
        String accessToken;
        if(refreshTokenIsValid.execute(refreshToken)){
            oAuth2Data.setUserName(refreshTokenStorage.getUserName());
        }else{
            BuildAuthorizeUrl buildAuthorizeUrl = new BuildAuthorizeUrl(oAuth2Data);
            System.out.println(buildAuthorizeUrl.execute(null));
            String authCode;
            System.out.println("entry Auth code");
            authCode = new Scanner(System.in).nextLine();
            String userAccount;
            System.out.println("entry userAccount");
            userAccount = new Scanner(System.in).nextLine();
            oAuth2Data.setUserName(userAccount);
            GetRefreshToken getRefreshToken = new GetRefreshToken(oAuth2Data);
            refreshToken = getRefreshToken.execute(authCode);
            refreshTokenStorage.update(oAuth2Data.getUserName(),refreshToken);
        }
        accessToken = getAccessToken.execute(refreshToken);

        GetGoogleCredential getGoogleCredential = new GetGoogleCredential(oAuth2Data);
        GoogleCredential credential = getGoogleCredential.execute(accessToken);
        PicasawebService picasawebService = new PicasawebService("SyncGooglePhotos");
        picasawebService.setOAuth2Credentials(credential);
        DownloadImage downloadImage = new DownloadImage(oAuth2Data.getUserName());
        downloadImage.setPath("./Google/");
        downloadImage.execute(picasawebService);
    }
}
