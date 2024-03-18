package com.example.test.common;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor

public class CmmnDao {
    
    private final SqlSession sqlSession;

	/*
	public CmmnDao(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	*/

    public int insert(String statement) {
		return sqlSession.insert(statement);
	}

	public int insert(String statement, Object parameter) {
		return sqlSession.insert(statement, parameter);
	}

	public int update(String statement) {
		return sqlSession.update(statement);
	}

	public int update(String statement, Object parameter) {
		return sqlSession.update(statement, parameter);
	}

	public int delete(String statement) {
		return sqlSession.delete(statement);
	}

	public int delete(String statement, Object parameter) {
		return sqlSession.delete(statement, parameter);
	}

	public <E> List<E> selectList(String statement) {
		return sqlSession.selectList(statement);
	}

	public <E> List<E> selectList(String statement, Object parameter) {
		return sqlSession.selectList(statement, parameter);
	}

	public <T> T selectOne(String statement, Object parameter) {
		return sqlSession.selectOne(statement, parameter);
	}

	public <T> T selectOne(String statement) {
		return sqlSession.selectOne(statement);
	}
	
}
