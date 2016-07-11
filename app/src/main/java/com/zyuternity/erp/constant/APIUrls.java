package com.zyuternity.erp.constant;

/**
 * Created by ZYuTernity on 7/10/2016.
 */
public class APIUrls {
    public static final String BASE_URL = "http://54.179.134.50:8088";
    public static final String LOGIN = BASE_URL + "/login";
    public static final String GET_CLASSES = BASE_URL + "/Classes";
    public static final String GET_INSTRUCTOR = BASE_URL + "/getInstructor";
    public static final String GET_INSTRUCTOR_BY_ID = GET_INSTRUCTOR + "?code=";
    public static final String GET_RATE = BASE_URL + "/getRate";
    public static final String GET_TEACHING_RECORD = BASE_URL + "/getTeachingRecord";
    public static final String ADD_TEACHING_RECORD = BASE_URL + "/addTeachingRecord";
    public static final String UPDATE_TEACHING_RECORD = BASE_URL + "/updateTeachingRecord";
    public static final String LOGOUT = BASE_URL + "/logout1";
    public static final String DELETE_TEACHING_RECORD = BASE_URL + "/deleteTeachingRecord";

}
