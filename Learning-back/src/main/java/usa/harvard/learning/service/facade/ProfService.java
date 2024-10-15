package usa.harvard.learning.service.facade;

import usa.harvard.learning.bean.Prof;
import usa.harvard.learning.bean.Subject;

import java.util.List;

public interface ProfService {

    Prof findByCode(String code);

    List<Prof> findAll();

    int deleteByCode(String code);

    int saveProf(Prof prof);

    int updateProf(Prof prof);

//    int addSubjectToProf(Subject subject ,Prof prof);
}
