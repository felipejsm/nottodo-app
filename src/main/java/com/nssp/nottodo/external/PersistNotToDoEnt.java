package com.nssp.nottodo.external;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class PersistNotToDoEnt implements PersistRepository<NotToDoEnt>, RowMapper<NotToDoEnt> {
    private JdbcTemplate jdbcTemplate;
    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public NotToDoEnt create(NotToDoEnt type) {
        var sql = "INSERT INTO nottodo(item_name, description, date, enabled) VALUES(?,?,?,?)";
        type.setId((long) jdbcTemplate.update(sql, type.getItemName(), type.getDescription(), type.getDate(), type.isEnabled()));
        return type;
    }

    @Override
    public List<NotToDoEnt> listAll() {
        var sql = "SELECT * FROM nottodo";
        return jdbcTemplate.query(sql, this::mapRow);
    }

    @Override
    public Optional<NotToDoEnt> findById(Long id) {
        if(id > 0) {
            var sql = "SELECT * FROM nottodo WHERE id = ?";
            return Optional.ofNullable(this.jdbcTemplate.queryForObject(sql, this::mapRow, id));
        }
        return Optional.empty();
    }

    @Override
    public Boolean update(NotToDoEnt type) {
        var sql = "UPDATE nottodo SET item_name = ?, description = ?, enabled = ?, date = ? WHERE id = ?";
        var affectedRows = this.jdbcTemplate.update(sql, type.getItemName(), type.getDescription(), type.isEnabled(), type.getDate(), type.getId());
        if(affectedRows > 0)
            return true;
        return false;
    }

    @Override
    public NotToDoEnt mapRow(ResultSet resultSet, int i) throws SQLException {
        return new NotToDoEnt(resultSet.getLong("id"),
                resultSet.getString("item_name"),
                resultSet.getString("description"),
                resultSet.getBoolean("enabled"),
                resultSet.getString("date"));
    }
}
