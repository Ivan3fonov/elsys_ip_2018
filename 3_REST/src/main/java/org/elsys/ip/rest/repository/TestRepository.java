package org.elsys.ip.rest.repository;

import org.elsys.ip.rest.model.Test;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import java.util.*;
import java.util.stream.Collectors;

public class TestRepository {
  private static List<Test> testList = new ArrayList<>(
    Arrays.asList(
      new Test(1, "name1"),
      new Test(2, "name2"),
      new Test(3, "name3"),
      new Test(4, "name4")
    ));

  public List<Test> getTestList() {
    return testList;
  }

  public Optional<Test> getTestById(Integer id) {
    return testList.stream().filter(test -> test.getId() == id).findFirst();
  }
  public Set<Test> getTestByMultipleId(@Context UriInfo uriInfo ) {

    List<String> empIdList = uriInfo.getQueryParameters().get("id");
    List<Integer> integerList = empIdList.stream().map(Integer::parseInt).collect(Collectors.toList());
    ArrayList<Integer> arrayIntegerList = (ArrayList) integerList;

    List<Test> filtredList = new ArrayList<>();

    for(int id:arrayIntegerList)  {
        filtredList.add(getTestById(id).orElse(null));
    }

    return new HashSet<>(filtredList.stream().filter(test -> test != null).collect(Collectors.toList()));
  }

  public Test saveTest(Test test) {
    testList.add(test);
    return test;
  }

  public Test updateTest(Integer id, Test test) {
    int realId = id - 1;
    testList.set(realId, test);
    return test;
  }

  public void deleteTest(Integer id) {
    testList.removeIf(it -> it.getId() == id);
  }
}
