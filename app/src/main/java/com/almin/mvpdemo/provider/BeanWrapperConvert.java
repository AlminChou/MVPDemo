package com.almin.mvpdemo.provider;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Type;

import retrofit.converter.ConversionException;
import retrofit.converter.Converter;
import retrofit.mime.MimeUtil;
import retrofit.mime.TypedInput;
import retrofit.mime.TypedOutput;

public class BeanWrapperConvert implements Converter {
    private final Gson mGson;
    private String mCharset;

    public BeanWrapperConvert() {
        GsonBuilder build = new GsonBuilder();
        BooleanSerializer serializer = new BooleanSerializer();
        build.registerTypeAdapter(Boolean.class, serializer);
        build.registerTypeAdapter(boolean.class, serializer);
        this.mGson = build.create();
        this.mCharset = "UTF-8";
    }

    @Override
    public Object fromBody(TypedInput body, Type type) throws ConversionException {
        String charset = this.mCharset;
        if (body.mimeType() != null) {
            charset = MimeUtil.parseCharset(body.mimeType(), charset);
        }
        InputStreamReader isr = null;
        Object result = null;
        try {
            isr = new InputStreamReader(body.in(), charset);
            BufferedReader bufferedReader = new BufferedReader(isr);
            StringBuilder builder = new StringBuilder();
            String line = bufferedReader.readLine();
            while (line != null) {
                builder.append(line);
                line = bufferedReader.readLine();
            }
            JSONObject wrapperJson = new JSONObject(builder.toString());
            JsonKey jsonKey = (JsonKey) ((Class)type).getAnnotation(JsonKey.class);
            if(jsonKey != null) {
                JSONObject targetBody = wrapperJson.optJSONObject(jsonKey.value());
                if(wrapperJson.length() == 1 && targetBody == null){
                    result = wrapperJson.opt((String) wrapperJson.keys().next());
                }else {
                    result = mGson.fromJson(targetBody.toString(), type);
                }
            }else if(wrapperJson.length() == 1 ){
                result = wrapperJson.opt((String) wrapperJson.keys().next());
            }else {
                result = mGson.fromJson(wrapperJson.toString(), type);
            }
        } catch (Exception e) {

        } finally {
            if (isr != null) {
                try {
                    isr.close();
                } catch (IOException ignored) {
                }
            }
        }
        return result;
    }


    @Override
    public TypedOutput toBody(Object object) {
        TypedOutput output = null;
        try {
            String json = mGson.toJson(object);
            JsonKey jsonKey = object.getClass().getAnnotation(JsonKey.class);
            JSONObject wrapperJson = new JSONObject();
            if(jsonKey != null){
                JSONObject jsonObject = new JSONObject(json);
                wrapperJson.put(jsonKey.value(), jsonObject);
                json = wrapperJson.toString();
            }else if(object instanceof JSONObject){
                json =  object.toString();
            }
            output = new JsonTypedOutput(json.getBytes(mCharset), mCharset);
        } catch (Exception e) {

        }
        return output;
    }
    private static class JsonTypedOutput implements TypedOutput {
        private final byte[] jsonBytes;
        private final String mimeType;

        JsonTypedOutput(byte[] jsonBytes, String encode) {
            this.jsonBytes = jsonBytes;
            this.mimeType = "application/json; charset=" + encode;
        }

        @Override
        public String fileName() {
            return null;
        }

        @Override
        public String mimeType() {
            return mimeType;
        }

        @Override
        public long length() {
            return jsonBytes.length;
        }

        @Override
        public void writeTo(OutputStream out) throws IOException {
            out.write(jsonBytes);
        }
    }

    private static final class BooleanSerializer implements JsonSerializer<Boolean> {

        @Override
        public JsonElement serialize(Boolean value, Type type, JsonSerializationContext context){
            return new JsonPrimitive(value ? 1 : 0);
        }
    }

}