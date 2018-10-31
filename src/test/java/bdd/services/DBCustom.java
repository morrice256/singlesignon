package bdd.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.morrice.SingleSignOn.config.CustomDataSource;

@Component
public class DBCustom {

	@Autowired
	private CustomDataSource dataSource;
	
	private JdbcTemplate jdbcTemplate;
	
	public void execute(String sql) {
		jdbcTemplate = new JdbcTemplate(dataSource.dataSource());
		jdbcTemplate.execute(sql);
	} 
	
}
