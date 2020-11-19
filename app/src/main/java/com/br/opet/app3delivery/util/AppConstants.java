package com.br.opet.app3delivery.util;

public class AppConstants {

    private static final String ROUTE_PATH = "http://10.0.2.2:3333/"; //LOCALHOST IP ---- ONLY WORKS WITH EMULATOR/LOCALHOST IF IP IS 10.0.2.2

    public static final String SESSION = "session";
    public static final String USER = "users";
    public static final String DRAWING = "drawing";
    public static final String PROVIDERS = "providers";


    public static String getFullRoute(String route) {

        switch (route) {
            case SESSION:
                return ROUTE_PATH + SESSION;
            case USER:
                return ROUTE_PATH + USER;
            case DRAWING:
                return ROUTE_PATH + DRAWING;
            case PROVIDERS:
                return ROUTE_PATH + PROVIDERS;
            default: break;
        }
        return null;
    }

}
