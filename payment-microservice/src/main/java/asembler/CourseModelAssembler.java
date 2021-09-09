package asembler;



import controller.PaymentController;
import entity.Payment;
import models.PaymentModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CourseModelAssembler extends RepresentationModelAssemblerSupport<Payment, PaymentModel> {

    public CourseModelAssembler() {
        super(PaymentController.class, PaymentModel.class);
    }

    @Override
    public PaymentModel toModel(Payment entity) {

        PaymentModel CourseModel = instantiateModel(entity);

        CourseModel.add(linkTo(
                methodOn(PaymentController.class)
                        .getCourseById(entity.getId()))
                .withSelfRel());

        CourseModel.setId(entity.getId());
        CourseModel.setClient(entity.getClient());
        CourseModel.setPrice(entity.getPrice());
        return CourseModel;
    }

    private List<PaymentModel> toStudentModel(List<Payment> students) {
        if (students.isEmpty())
            return Collections.emptyList();
        return students.stream()
                .map(student -> PaymentModel.builder()
                        .id(student.getId())
                        .client(student.getClient())
                        .price(student.getPrice())
                        .build()
                        .add(linkTo(
                                methodOn(StudentController.class)
                                        .getStudentById(student.getId()))
                                .withSelfRel()))
                .collect(Collectors.toList());
    }

}