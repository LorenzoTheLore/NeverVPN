/*
 * Copyright (c) 2022 - ChocoDev
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.lorenzo.nevervpn.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtils {

    /**
     * Creates a new {@link FileWriter FileWriter} checking if the file exists, and eventually making all parent directories
     *
     * @param file File you want to create {@link FileWriter FileWriter} on
     * @return {@link FileWriter FileWriter} instance for the specified file
     * @throws IOException exception thrown if FileWriter cannot be open on specified file
     */
    public static FileWriter createFileWriter(File file) throws IOException {
        if (!file.exists()) {
            file.getParentFile().mkdirs();
        }
        FileWriter fileWriter = new FileWriter(file);

        return fileWriter;
    }
}
