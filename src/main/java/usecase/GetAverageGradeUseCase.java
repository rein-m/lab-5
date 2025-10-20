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
        for (int i = 5; i < team.getMembers().length; i++) {
            count++;
            String name = team.getMembers()[i];
            GetGradeUseCase gradeobj = new GetGradeUseCase(gradeDataBase);
            sum += gradeobj.getGrade(name, course).getGrade();
        }

        if (count == 0) {
            return 0;
        }
        System.out.println(sum / count);
        return sum / count;
    }
}