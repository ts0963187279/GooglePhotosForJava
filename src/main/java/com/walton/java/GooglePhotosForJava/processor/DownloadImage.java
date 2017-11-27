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
import com.walton.java.GooglePhotosForJava.model.AlbumInfo;
import com.walton.java.GooglePhotosForJava.model.PhotoInfo;
import com.walton.java.socket.processor.DownloadData;
import poisondog.core.Mission;

import java.io.File;
import java.util.List;

public class DownloadImage implements Mission<PicasawebService>{
    private GetAlbumInfos getAlbumInfos;
    private String downloadPath = "./google photos/";
    public DownloadImage(String userName){
        getAlbumInfos = new GetAlbumInfos(userName);
    }
    public void setPath(String downloadPath){
        this.downloadPath = downloadPath;
    }
    public Void execute(PicasawebService picasawebService){
        List<AlbumInfo> albumInfos = getAlbumInfos.execute(picasawebService);
        for(AlbumInfo albumInfo : albumInfos){
            for(PhotoInfo photoInfo : albumInfo.getPhotoInfos()){
                CreateDownloadFile createDownloadFile = new CreateDownloadFile(downloadPath + albumInfo.getAlbumName());
                File imageFile = createDownloadFile.execute(photoInfo.getPhotoName());
                DownloadData downloadData = new DownloadData(imageFile);
                downloadData.execute(photoInfo.getUrl());
            }
        }
        return null;
    }
}
