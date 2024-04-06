package org.defuni;

import org.defuni.account.EducationManager;
import org.junit.Test;

public class DeleteStudent {
    @Test
    public void deleteStudent() {
        EducationManager e = new EducationManager();
        e.deleteStudent("testUser");

    }
}
