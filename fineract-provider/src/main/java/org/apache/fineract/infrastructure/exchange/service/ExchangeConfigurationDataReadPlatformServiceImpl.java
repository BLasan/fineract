package org.apache.fineract.infrastructure.exchange.service;

import org.apache.fineract.infrastructure.exchange.data.ExchangeConfigurationData;
import org.apache.fineract.infrastructure.exchange.domain.ExchangeConfiguration;
import org.apache.fineract.infrastructure.exchange.domain.ExchangeConfigurationRepository;
import org.apache.fineract.infrastructure.core.service.RoutingDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ExchangeConfigurationDataReadPlatformServiceImpl implements ExchangeConfigurationDataReadPlatformService {

    private final JdbcTemplate jdbcTemplate;
    private final ExchangeConfigurationRepository exchangeConfigurationRepository;

    @Autowired
    public ExchangeConfigurationDataReadPlatformServiceImpl(final RoutingDataSource dataSource, ExchangeConfigurationRepository exchangeConfigurationRepository) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.exchangeConfigurationRepository = exchangeConfigurationRepository;
    }

    private static final class ExchangeConfigurationDataExtractor implements ResultSetExtractor<ExchangeConfigurationData> {

        public ExchangeConfigurationData extractData(final ResultSet rs) throws SQLException, DataAccessException {
            int memberID = 0;
            String userName = "";
            String password = "";
            String baseAPIURL = "";
            while (rs.next()) {
                String configKey = rs.getString("configkey");
                String value = rs.getString("value");
                if (ExchangeServiceConstants.EXCHANGE_CONFIG_USERNAME.equals(rs.getString(""))) {
                    userName =
                }
                memberID = rs.getInt("mamberId");
                userName = rs.getString("userName");
                password = rs.getString("password");
                baseAPIURL = rs.getString("baseAPIURL");
            }
            return new ExchangeConfigurationData(memberID, baseAPIURL, password, userName);
        }
    }

    @Override
    public ExchangeConfigurationData getBSEConfigurationData() {
        //ExchangeConfiguration exchangeConfigurationData = exchangeConfigurationRepository.findById(1).orElse(null);
        final ResultSetExtractor<ExchangeConfigurationData> resultSetExtractor = new ExchangeConfigurationDataExtractor();
        final String sql = "SELECT * FROM m_exchange_configuration";
        final ExchangeConfigurationData exchangeConfigurationData = this.jdbcTemplate.query(sql, resultSetExtractor, new Object[] {});
        return exchangeConfigurationData;
    }

}
