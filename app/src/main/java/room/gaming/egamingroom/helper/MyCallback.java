package room.gaming.egamingroom.helper;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class MyCallback<T> implements Serializable {
    private Type type;
    public  MyCallback(Type type){
        this.type = type;
    }
public abstract  void Run(T t);
public  Class<T> getGenericType(){
    Class<T> persistendClass = (Class<T>)
            ((ParameterizedType)getClass().getGenericSuperclass())
            .getActualTypeArguments()[0];

    return  persistendClass;
}
public Type GetCustomType(){
    return type;
}
}
