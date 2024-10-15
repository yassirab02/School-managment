package usa.harvard.learning.service.facade;

import usa.harvard.learning.bean.Assignment;

import java.util.List;

public interface AssignmentService {
    Assignment findByCode(String code);

    int deleteByCode(String code);

    List<Assignment> findAll();

    int saveAssignment(Assignment assignment);

    int updateAssignment(Assignment assignment);
}
