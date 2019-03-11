package room.gaming.egamingroom.models;

import room.gaming.egamingroom.helper.MyApiRequest;

public class MyApiResult {
public String errorMessage = "";
public boolean isException = false;
public int resultCode = 0;
public  String value;

public static MyApiResult Error(int exceptionCode, String exceptionMessage){
    MyApiResult x = new MyApiResult();
     x.isException = true;
     x.resultCode = exceptionCode;
     x.errorMessage = exceptionMessage;
     return x;
}
public static MyApiResult OK(String value) {
    MyApiResult x = new MyApiResult();
    x.value = value;
    x.isException = false;
    x.resultCode = 0;
    x.errorMessage = "";
    return  x;
}
}
