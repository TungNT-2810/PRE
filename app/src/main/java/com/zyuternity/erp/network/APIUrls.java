package com.zyuternity.erp.network;

/**
 * Created by ZYuTernity on 7/10/2016.
 */
public class APIUrls {
    public static final String BASE_URL = "http://54.179.134.50:8088";
    public static final String LOGIN = "/login";
    public static final String GET_CLASSES = "/Classes";
    public static final String GET_INSTRUCTOR ="/getInstructor";
    public static final String GET_INSTRUCTOR_BY_ID = GET_INSTRUCTOR + "?code=";
    public static final String GET_RATE = "/getRate";
    public static final String GET_TEACHING_RECORD = "/getTeachingRecord";
    public static final String ADD_TEACHING_RECORD = "/addTeachingRecord";
    public static final String UPDATE_TEACHING_RECORD = "/updateTeachingRecord";
    public static final String LOGOUT = "/logout1";
    public static final String DELETE_TEACHING_RECORD = "/deleteTeachingRecord";
    public static final String GET_ROLE = "/getRole";

}
