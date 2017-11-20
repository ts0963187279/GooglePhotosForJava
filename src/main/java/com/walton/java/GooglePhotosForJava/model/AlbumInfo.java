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
package com.walton.java.GooglePhotosForJava.model;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class AlbumInfo {
    private String albumName;
    private List<PhotoInfo> photoInfos = new ArrayList<PhotoInfo>();
    private String albumId;
    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }
    public String getAlbumName() {
        return albumName;
    }
    public void addPhotoNameAndPhotoURL(String fileName,URL url){
        PhotoInfo photoInfo = new PhotoInfo();
        photoInfo.setPhotoName(fileName);
        photoInfo.setUrl(url);
        photoInfos.add(photoInfo);
    }
    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }
    public String getAlbumId() {
        return albumId;
    }
    public List<PhotoInfo> getPhotoInfos() {
        return photoInfos;
    }
}
