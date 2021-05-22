package org.apache.fineract.infrastructure.bse.service;

import org.apache.fineract.infrastructure.bse.domain.BSEIQRequest;
import org.apache.fineract.infrastructure.core.service.RoutingDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

public class BSEIQDataReadPlatformServiceImpl implements BSEIQDataReadPlatformService{

    private final JdbcTemplate jdbcTemplate;

    private static final class BSEIQDataMapper implements RowMapper<BSEIQRequest> {

        public String schema() {
            return "SCHEMA";
        }

        @Override
        public BSEIQRequest mapRow(final ResultSet rs, @SuppressWarnings("unused") final int rowNum) throws SQLException {
            final long id = rs.getLong("creditBureauID");
            final String name = rs.getString("creditBureauName");
            final String product = rs.getString("creditBureauProduct");
            final String country = rs.getString("country");
            final String cbSummary = rs.getString("cbSummary");
            final long implementationKey = rs.getLong("implementationKey");

            return new BSEIQRequest();

        }

//        @Override
//        public BSEIQRequest extractData(final ResultSet rs) throws SQLException, DataAccessException {
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
//            return new BSEIQRequest();
//        }
    }

    @Autowired
    public BSEIQDataReadPlatformServiceImpl(final RoutingDataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Collection<BSEIQRequest> getBSEIQRequestData(Long groupId) {
        final BSEIQDataMapper bseiqDataMapper = new BSEIQDataMapper();
        final String sql = "QUERY" + bseiqDataMapper.schema();
        return this.jdbcTemplate.query(sql, bseiqDataMapper, new Object[] {});
    }
}
