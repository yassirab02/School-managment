package usa.harvard.learning.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import usa.harvard.learning.bean.Prof;
import usa.harvard.learning.bean.StudentGrp;



@Repository
public interface ProfDao extends JpaRepository<Prof, Long> {
  Prof  findByCode(String code);

  Prof findByEmail(String email);

  int deleteByCode(String code);

  Prof findByStudentGrp(StudentGrp grp);
}
