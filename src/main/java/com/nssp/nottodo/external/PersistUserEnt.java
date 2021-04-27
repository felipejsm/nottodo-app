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
public class PersistUserEnt implements PersistRepository<UserEnt>, RowMapper<UserEnt> {

    private JdbcTemplate template;
    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.template = jdbcTemplate;
    }
    @Override
    public UserEnt create(UserEnt type) {
        String saveSql = "INSERT INTO user (name, email, nick, enabled, nottodo_id) VALUES (?, ?, ?, ?, ?)";
        type.setId((long) this.template. update(saveSql, type.getName(), type.getEmail(), type.getNick(), type.isEnabled(), type.getNotToDoEntList()));
        return type;
    }

    @Override
    public List<UserEnt> listAll() {
        String sql = "SELECT * FROM user";
        return this.template.query(sql, this::mapRow);
    }

    @Override
    public Optional<UserEnt> findById(Long id) {
        if(id > 0) {
            String sql = "SELECT * FROM user WHERE id = ?";
            return Optional.ofNullable(this.template.queryForObject(sql,this::mapRow, id));
        }
        return Optional.empty();
    }

    @Override
    public Boolean update(UserEnt type) {
        String sql = "UPDATE user SET name = ?, email = ?, nick = ?, enabled = ? WHERE id = ?";
        int affectedRows = this.template.update(sql, type.getName(), type.getEmail(), type.getNick(),type.isEnabled(), type.getId());
        if(affectedRows > 0) {
            return true;
        }
        return false;
    }

    @Override
    public UserEnt mapRow(ResultSet resultSet, int i) throws SQLException {
        return new UserEnt(resultSet.getLong("id"),
                resultSet.getString("name"),
                resultSet.getString("email"),
                resultSet.getString("nick"),
                resultSet.getObject ("nottodo_id", NotToDoEnt.class),
                resultSet.getBoolean("enabled"));
    }
}
