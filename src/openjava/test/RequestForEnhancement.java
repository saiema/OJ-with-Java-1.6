package openjava.test;

/**
 * Describes the Request-For-Enhancement(RFE) that led
 * to the presence of the annotated API element.
 */
public @interface RequestForEnhancement {

    String synopsis();
    String engineer() default "[unassigned]"; 
    String date()   default "[unimplemented]"; 
    int    id();
}