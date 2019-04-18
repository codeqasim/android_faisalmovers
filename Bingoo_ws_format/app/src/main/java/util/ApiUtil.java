package util;

public class ApiUtil {

    private static final String BASE_URL = "http://www.mocky.io/";

    private static final String BASE_URL2 = "https://www.bookkaru.com/api/bus/search?appKey=bookkaru&fromcity=54&tocity=114&depdate=2019-04-19&operator=1";

    public static RetrofitInterface getServiceClass(){
        return RetrofitAPI.getRetrofit(BASE_URL2).create(RetrofitInterface.class);
    }
}
