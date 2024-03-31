package org.defuni;

import org.defuni.account.*;
import org.junit.jupiter.api.Test;

public class TestCourseCreate {

    @Test
    public void TestCourseCreate(){
        Lecturer lecturer = new Lecturer();
        lecturer.courseCreate();
    }

}
