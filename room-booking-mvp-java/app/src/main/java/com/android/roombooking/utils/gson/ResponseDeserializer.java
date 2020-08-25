package com.android.roombooking.utils.gson;

import com.android.roombooking.model.Calendar;
import com.android.roombooking.model.Event;
import com.android.roombooking.model.ResponseWrapper;
import com.android.roombooking.model.User;
import com.android.roombooking.utils.injector.module.NetworkModule;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ResponseDeserializer implements JsonDeserializer<ResponseWrapper> {

    private Type calendarType = new TypeToken<Calendar>() {

    }.getType();

    private Type eventType = new TypeToken<Event>() {

    }.getType();

    private Type userType = new TypeToken<User>() {

    }.getType();

    private Gson gson = new GsonBuilder()
            .setDateFormat(NetworkModule.API_DATE_FORMAT)
            .setFieldNamingPolicy(NetworkModule.API_JSON_NAMING_POLICY)
            .create();

    @Override
    public ResponseWrapper deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        ResponseWrapper responseWrapper = null;
        JsonObject jsonObject = json.getAsJsonObject();
        if (jsonObject.has(Calendar.JSON_ARRAY_NAME)) {
            JsonArray jsonArray = jsonObject.getAsJsonArray(Calendar.JSON_ARRAY_NAME);
            int size = jsonArray.size();
            List<Calendar> calendars = new ArrayList<>(size);
            JsonElement jsonElement;
            for (int i = 0; i < size; i++){
                jsonElement = jsonArray.get(i);
                Calendar calendar = parseCalendar(jsonElement);
                calendars.add(calendar);
            }
            responseWrapper = new ResponseWrapper<List<Event>> ();
            responseWrapper.body = calendars;
        } else if (jsonObject.has(Event.JSON_ARRAY_NAME)) {
            JsonArray jsonArray = jsonObject.getAsJsonArray(Event.JSON_ARRAY_NAME);
            int size = jsonArray.size();
            List<Event> events = new ArrayList<>(size);
            JsonElement jsonElement;
            for (int i = 0; i < size; i++) {
                jsonElement = jsonArray.get(i);
                Event event = parseEvent(jsonElement);
                events.add(event);
            }
            responseWrapper = new ResponseWrapper<List<Event>>();
            responseWrapper.body = events;
        } else if (jsonObject.has(User.JSON_ARRAY_NAME)) {
            JsonArray jsonArray = jsonObject.getAsJsonArray(User.JSON_ARRAY_NAME);
            int size = jsonArray.size();
            List<User> users = new ArrayList<>(size);
            JsonElement jsonElement;
            for (int i = 0; i < size; i++) {
                jsonElement = jsonArray.get(i);
                User user = parseUser(jsonElement);
                users.add(user);
            }
            responseWrapper = new ResponseWrapper<List<User>>();
            responseWrapper.body = users;
        } else if (jsonObject.has(Event.JSON_OBJECT_NAME)) {
            JsonElement jsonElement = jsonObject.get(Event.JSON_OBJECT_NAME);
            Event event = parseEvent(jsonElement);
            responseWrapper = new ResponseWrapper<Event>();
            responseWrapper.body = event;
        }
        return responseWrapper;
    }

    private Calendar parseCalendar(JsonElement jsonElement) {
        return gson.fromJson(jsonElement, calendarType);
    }

    private Event parseEvent(JsonElement jsonElement) {
        return gson.fromJson(jsonElement, eventType);
    }

    private User parseUser(JsonElement jsonElement) {
        return gson.fromJson(jsonElement, userType);
    }

}
