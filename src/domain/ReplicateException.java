package domain;
import java.util.*;
import  java.lang.Class;
import java.lang.reflect.Constructor;

    public class ReplicateException extends Exception{
        public static final String EN_CONSTRUCION="En construccion";
        private String message;
        public ReplicateException(String message){
            super(message);
        }
}