package com.github.fantasy0v0;

import com.github.fantasy0v0.mappers.TestMapper;
import com.github.fantasy0v0.record.Student;
import com.github.fantasy0v0.record.StudentRecord;
import com.github.fantasy0v0.utils.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MyBatisTest {

  private SqlSession session;

  @BeforeEach
  void init() {
    session = MyBatisUtil.openSession();
  }

  @AfterEach
  void end() {
    session.close();
  }

  @Test
  void test() {
    TestMapper testMapper = session.getMapper(TestMapper.class);
    StudentRecord studentRecord = testMapper.testRecord();
    assertEquals(1, studentRecord.id());
    assertEquals("test", studentRecord.name());
    Map<String, String> ext = studentRecord.ext();
    assertTrue(ext.containsKey("test"));
    assertEquals("123", ext.get("test"));

    Student student = testMapper.test();
    assertEquals(1, student.getId());
    assertEquals("test", student.getName());
  }

}
