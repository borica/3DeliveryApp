package com.br.opet.app3delivery.util;

public class AppConstants {

    private static final String ROUTE_PATH = "http://10.0.2.2:3333/"; //LOCALHOST IP ---- ONLY WORKS WITH EMULATOR/LOCALHOST IF IP IS 10.0.2.2

    public static final String SESSION = "session";
    public static final String USER = "users";
    public static final String DRAWING = "drawing";
    public static final String CREATE_DRAWING = "drawing/create-drawing";
    public static final String LIST_DRAWING_BY_USER = "drawing/list-all-drawing";
    public static final String EDIT_DRAWING = "drawing/alter-drawing";
    public static final String DELETE_DRAWING = "drawing/delete-drawing";
    public static final String PROVIDERS = "providers";

    public static String getFullRoute(String route) {

        switch (route) {
            case SESSION:
                return ROUTE_PATH + SESSION;
            case USER:
                return ROUTE_PATH + USER;
            case PROVIDERS:
                return ROUTE_PATH + PROVIDERS;
            case DRAWING:
                return ROUTE_PATH + DRAWING;
            case CREATE_DRAWING:
                return ROUTE_PATH + CREATE_DRAWING;
            case LIST_DRAWING_BY_USER:
                return ROUTE_PATH + LIST_DRAWING_BY_USER;
            case EDIT_DRAWING:
                return ROUTE_PATH + EDIT_DRAWING;
            case DELETE_DRAWING:
                return ROUTE_PATH + DELETE_DRAWING;
            default: break;
        }
        return null;
    }

}
