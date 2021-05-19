package org.apache.fineract.infrastructure.bse.service;

import org.apache.fineract.infrastructure.bse.data.BSEIPOData;
import org.apache.fineract.infrastructure.core.service.RoutingDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BSEIPODataReadPlatformServiceImpl implements BSEIPODataReadPlatformService {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BSEIPODataReadPlatformServiceImpl(final RoutingDataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private static final class BSEConfigurationDataExtractor implements ResultSetExtractor<BSEIPOData> {

        public BSEIPOData extractData(final ResultSet rs) throws SQLException, DataAccessException {
            while (rs.next()) {

            }
            return new BSEIPOData();
        }
    }

    @Override
    public BSEIPOData getBSEConfiguration() {
        final ResultSetExtractor<BSEIPOData> resultSetExtractor = new BSEConfigurationDataExtractor();
        final String sql = " ";
        final BSEIPOData bseipoData = this.jdbcTemplate.query(sql, resultSetExtractor, new Object[] {});
        return bseipoData;
    }

}
