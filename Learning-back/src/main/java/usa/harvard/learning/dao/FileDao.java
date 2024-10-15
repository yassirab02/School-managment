package usa.harvard.learning.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import usa.harvard.learning.bean.File;

public interface FileDao extends JpaRepository<File, Long> {

}
