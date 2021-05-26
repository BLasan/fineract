package org.apache.fineract.infrastructure.exchange.service;

import org.apache.fineract.infrastructure.exchange.domain.ExchangeIQRequest;
import org.apache.fineract.infrastructure.core.service.RoutingDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

public class ExchangeIQDataReadPlatformServiceImpl implements ExchangeIQDataReadPlatformService {

    private final JdbcTemplate jdbcTemplate;

    private static final class BSEIQDataMapper implements RowMapper<ExchangeIQRequest> {

        public String schema() {
            return "SCHEMA";
        }

        @Override
        public ExchangeIQRequest mapRow(final ResultSet rs, @SuppressWarnings("unused") final int rowNum) throws SQLException {
            final long id = rs.getLong("creditBureauID");
            final String name = rs.getString("creditBureauName");
            final String product = rs.getString("creditBureauProduct");
            final String country = rs.getString("country");
            final String cbSummary = rs.getString("cbSummary");
            final long implementationKey = rs.getLong("implementationKey");

            return new ExchangeIQRequest();

        }

//        @Override
//        public ExchangeIQRequest extractData(final ResultSet rs) throws SQLException, DataAccessException {
//            int memberID = 0;
//            String userName = "";
//            String password = "";
//            String baseAPIURL = "";
//            while (rs.next()) {
//                memberID = rs.getInt("mamberId");
//                userName = rs.getString("userName");
//                password = rs.getString("password");
//                baseAPIURL = rs.getString("baseAPIURL");
//            }
//            return new ExchangeIQRequest();
//        }
    }

    @Autowired
    public ExchangeIQDataReadPlatformServiceImpl(final RoutingDataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Collection<ExchangeIQRequest> getBSEIQRequestData(Long groupId) {
        final BSEIQDataMapper bseiqDataMapper = new BSEIQDataMapper();
        final String sql = "QUERY" + bseiqDataMapper.schema();
        return this.jdbcTemplate.query(sql, bseiqDataMapper, new Object[] {});
    }
}
