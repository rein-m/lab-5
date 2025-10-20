package usecase;

import api.GradeDataBase;
import com.sun.tools.jconsole.JConsolePlugin;
import entity.Grade;
import entity.Team;
import usecase.GetGradeUseCase;

import java.io.IOException;

/**
 * GetAverageGradeUseCase class.
 */
public final class GetAverageGradeUseCase {
    private final GradeDataBase gradeDataBase;

    public GetAverageGradeUseCase(GradeDataBase gradeDataBase) {
        this.gradeDataBase = gradeDataBase;
    }

    /**
     * Get the average grade for a course across your team.
     * @param course The course.
     * @return The average grade.
     */
    public float getAverageGrade(String course) throws IOException {
        // Call the API to get usernames of all your team members
        float sum = 0;
        int count = 0;
        final Team team = gradeDataBase.getMyTeam();
        // Call the API to get all the grades for the course for all your team members
        // TODO Task 3a: Complete the logic of calculating the average course grade for
        //              your team members. Hint: the getGrades method might be useful.
        for (String username : team.getMembers()) {
            // Call the API to get the grade for the course for the username
            final Grade[] grades = gradeDataBase.getGrades(username);
            for (Grade grade : grades) {
        for (int i = 5; i < team.getMembers().length; i++) {
            count++;
            String name = team.getMembers()[i];
            GetGradeUseCase gradeobj = new GetGradeUseCase(gradeDataBase);
            sum += gradeobj.getGrade(name, course).getGrade();
        }

                if (grade.getCourse().equals(course)) {
                    // Sum all the grades
                    sum += grade.getGrade();
                    count++;
                    }
                }
            }
        if (count == 0) {
            return 0;
        }
        System.out.println(sum / count);
        return sum / count;
    }
}