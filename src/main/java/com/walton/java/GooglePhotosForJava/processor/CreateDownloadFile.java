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

import poisondog.core.Mission;

import java.io.File;
import java.io.IOException;

public class CreateDownloadFile implements Mission<String> {
    private String path;
    public CreateDownloadFile(String path){
        this.path = path;
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }
    @Override
    public File execute(String fileName){
        System.out.println(path +"/"+ fileName);
        File file = new File(path +"/"+ fileName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }
}
