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

public class PhotoInfo {
    private String fileName;
    private URL url;

    public void setPhotoName(String fileName) {
        this.fileName = fileName;
    }
    public String getPhotoName(){
        return fileName;
    }
    public void setUrl(URL url){
        this.url = url;
    }
    public URL getUrl(){
        return url;
    }
}
