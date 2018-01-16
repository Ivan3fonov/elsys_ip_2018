package org.elsys.ip.rest.service;

import org.elsys.ip.rest.model.Test;
import org.elsys.ip.rest.repository.TestRepository;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class TestService {

  private TestRepository testRepository = new TestRepository();

  public List<Test> getTestList() {
    return testRepository.getTestList();
  }

  public Test getTestById(Integer id) {
    return testRepository.getTestById(id).orElse(null);
  }

  public Set<Test> getTestListByMyltipleId(@Context UriInfo uriInfo) {
    return testRepository.getTestByMultipleId(uriInfo);
  }
  public Test saveTest(Test test) {
    return testRepository.saveTest(test);
  }

  public Test updateTest(Integer id, Test test) {
    return testRepository.updateTest(id, test);
  }

  public void deleteTest(Integer id) {
    testRepository.deleteTest(id);
  }

}
