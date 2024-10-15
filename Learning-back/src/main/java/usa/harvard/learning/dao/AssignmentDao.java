package usa.harvard.learning.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import usa.harvard.learning.bean.Assignment;

@Repository
public interface AssignmentDao extends JpaRepository<Assignment, Long> {
   Assignment findByCode(String code);

   int deleteByCode(String code);

}
