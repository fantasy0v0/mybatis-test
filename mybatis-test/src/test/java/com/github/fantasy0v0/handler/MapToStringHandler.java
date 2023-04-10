package com.github.fantasy0v0.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class MapToStringHandler implements TypeHandler<Map<String, String>> {

  private static final ObjectMapper objectMapper = new ObjectMapper();

  @Override
  public void setParameter(PreparedStatement preparedStatement, int i, Map<String, String> stringStringMap, JdbcType jdbcType) throws SQLException {
    try {
      preparedStatement.setString(i, objectMapper.writeValueAsString(stringStringMap));
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public Map<String, String> getResult(ResultSet resultSet, String s) throws SQLException {
    try {
      return objectMapper.readValue(resultSet.getString(s), new TypeReference<>() {
      });
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public Map<String, String> getResult(ResultSet resultSet, int i) throws SQLException {
    try {
      return objectMapper.readValue(resultSet.getString(i), new TypeReference<>() {
      });
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public Map<String, String> getResult(CallableStatement callableStatement, int i) throws SQLException {
    try {
      return objectMapper.readValue(callableStatement.getString(i), new TypeReference<>() {
      });
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }
}
