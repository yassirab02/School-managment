package usa.harvard.learning.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import usa.harvard.learning.bean.StudentGrp;

import java.util.List;

@Repository
public interface StudentGrpDao extends JpaRepository<StudentGrp, Long> {

    StudentGrp findByCode(String code);

    int deleteByCode(String code);

}
