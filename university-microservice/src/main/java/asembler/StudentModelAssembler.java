package asembler;



import controller.StudentController;
import entity.Student;
import models.StudentModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class StudentModelAssembler extends RepresentationModelAssemblerSupport<Student, StudentModel> {

    public StudentModelAssembler() {
        super(StudentController.class, StudentModel.class);
    }

    @Override
    public StudentModel toModel(Student entity) {
        return null;
    }

    // TODO
}