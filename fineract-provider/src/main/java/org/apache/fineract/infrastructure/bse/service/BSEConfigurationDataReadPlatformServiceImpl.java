package org.apache.fineract.infrastructure.bse.service;

import org.apache.fineract.infrastructure.bse.data.BSEConfigurationData;
import org.apache.fineract.infrastructure.bse.data.BSEIPOData;
import org.apache.fineract.infrastructure.bse.domain.BSEConfiguration;
import org.apache.fineract.infrastructure.bse.domain.BSEConfigurationRepository;
import org.apache.fineract.infrastructure.core.service.RoutingDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class BSEConfigurationDataReadPlatformServiceImpl implements BSEConfigurationDataReadPlatformService {

    private final JdbcTemplate jdbcTemplate;
    private final BSEConfigurationRepository bseConfigurationRepository;

    @Autowired
    public BSEConfigurationDataReadPlatformServiceImpl(final RoutingDataSource dataSource, BSEConfigurationRepository bseConfigurationRepository) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.bseConfigurationRepository = bseConfigurationRepository;
    }

    private static final class BSEConfigurationDataExtractor implements ResultSetExtractor<BSEConfigurationData> {

        public BSEConfigurationData extractData(final ResultSet rs) throws SQLException, DataAccessException {
            int memberID = 0;
            String userName = "";
            String password = "";
            String baseAPIURL = "";
            while (rs.next()) {
                memberID = rs.getInt("mamberId");
                userName = rs.getString("userName");
                password = rs.getString("password");
                baseAPIURL = rs.getString("baseAPIURL");
            }
            return new BSEConfigurationData(memberID, baseAPIURL, password, userName);
        }
    }

    @Override
    public BSEConfiguration getBSEConfigurationData() {
        BSEConfiguration bseConfigurationData = bseConfigurationRepository.findById(1).orElse(null);
//        final ResultSetExtractor<BSEConfigurationData> resultSetExtractor = new BSEConfigurationDataExtractor();
//        final String sql = "SELECT * FROM m_bse_config";
//        final BSEConfigurationData bseConfigData = this.jdbcTemplate.query(sql, resultSetExtractor, new Object[] {});
        return bseConfigurationData;
    }

}
