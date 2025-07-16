package com.postgresql.demo1.dao;

import com.postgresql.demo1.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PersonDAOImpl implements IPersonDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<Person> personRowMapper = (rs, rowNum) -> {
        Person person = new Person();
        person.setId(rs.getLong("id"));
        person.setName(rs.getString("name"));
        person.setSubject(rs.getString("subject"));
        person.setGrade(rs.getString("grade"));
        return person;
    };

    @Override
    public void addPerson(Person person) {
        String sql = "INSERT INTO person (name,subject,grade) VALUES (?,?,?)";
         jdbcTemplate.update(sql, person.getName(), person.getSubject(), person.getGrade());

    }

    @Override
    public List<Person> getAllPersons() {
        String sql = "SELECT * FROM person";
        return jdbcTemplate.query(sql, personRowMapper);
    }

    @Override
    public Person getPersonById(Long id) {
        String sql = "SELECT * FROM person WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, personRowMapper, id);
    }


    @Override
    public Person updatePerson(Long id, Person person) {
        String sql = "UPDATE person SET name = ?, subject = ?, grade = ? WHERE id = ?";
        int rowsUpdated = jdbcTemplate.update(sql, person.getName(), person.getSubject(), person.getGrade(), id);
        return person;

    }

    @Override
    public int deletePerson(Long id){
        String sql="DELETE FROM person WHERE id=?";
        return jdbcTemplate.update(sql,id);
    }
    @Override
    public Person updatePartialPerson(Long id,Person person){
        String sql="UPDATE person SET name= ? WHERE id=?";
        jdbcTemplate.update(sql,person.getName(),id);
        return person;
    }


}
