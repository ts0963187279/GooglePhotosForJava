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
package com.walton.java.GooglePhotosForJava.processor;

import com.google.gdata.client.photos.PicasawebService;
import com.google.gdata.data.media.MediaFileSource;
import com.google.gdata.data.photos.PhotoEntry;
import com.google.gdata.util.ServiceException;
import com.walton.java.GooglePhotosForJava.model.AlbumInfo;
import poisondog.core.Mission;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class UploadImage implements Mission<PicasawebService>{
    private String userName;
    private GetAlbumInfos getAlbumInfos;
    public UploadImage(String userName){
        getAlbumInfos = new GetAlbumInfos(userName);
        this.userName = userName;
    }
    public Void execute(PicasawebService picasawebService){
        List<AlbumInfo> albumInfos = getAlbumInfos.execute(picasawebService);
        for (AlbumInfo albumInfo : albumInfos) {
            System.out.println(albumInfo.getAlbumName() + " : " + albumInfo.getAlbumId());
            File file = new File("./picture1.jpg");
            MediaFileSource mediaFileSource = new MediaFileSource(file, "image/jpeg");
            URL feedUrl = null;
            try {
                feedUrl = new URL("https://picasaweb.google.com/data/feed/api/user/"+userName+"/albumid/"+albumInfo.getAlbumId());
                picasawebService.insert(feedUrl, PhotoEntry.class, mediaFileSource);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ServiceException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
