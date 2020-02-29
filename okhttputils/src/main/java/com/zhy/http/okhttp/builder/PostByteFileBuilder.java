package com.zhy.http.okhttp.builder;

import com.zhy.http.okhttp.request.PostByteFileRequest;
import com.zhy.http.okhttp.request.PostFileRequest;
import com.zhy.http.okhttp.request.PostFormRequest;
import com.zhy.http.okhttp.request.RequestCall;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;

/**
 * Created by zhy on 15/12/14.
 */

public class PostByteFileBuilder extends OkHttpRequestBuilder<PostByteFileBuilder> implements HasParamsable
{

    private List<FileInput> files = new ArrayList<>();

    @Override
    public RequestCall build()
    {
        return new PostByteFileRequest(url, tag, params, headers, files,id).build();
    }

    public PostByteFileBuilder files(String key, Map<String, byte[]> files)
    {
        for (String filename : files.keySet())
        {
            this.files.add(new FileInput(key, filename, files.get(filename)));
        }
        return this;
    }

    public PostByteFileBuilder addFile(String name, String filename, byte[] file)
    {
        files.add(new FileInput(name, filename, file));
        return this;
    }

    public static class FileInput
    {
        public String key;
        public String filename;
        public byte[] file;

        public FileInput(String name, String filename, byte[] file)
        {
            this.key = name;
            this.filename = filename;
            this.file = file;
        }

        @Override
        public String toString()
        {
            return "FileInput{" +
                    "key='" + key + '\'' +
                    ", filename='" + filename + '\'' +
                    ", file=" + file +
                    '}';
        }
    }



    @Override
    public PostByteFileBuilder params(Map<String, String> params)
    {
        this.params = params;
        return this;
    }

    @Override
    public PostByteFileBuilder addParams(String key, String val)
    {
        if (this.params == null)
        {
            params = new LinkedHashMap<>();
        }
        params.put(key, val);
        return this;
    }



}
