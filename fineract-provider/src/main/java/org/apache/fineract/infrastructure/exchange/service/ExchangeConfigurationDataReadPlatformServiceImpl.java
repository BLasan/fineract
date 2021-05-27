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
import java.util.HashMap;
import java.util.Map;

public class ExchangeConfigurationDataReadPlatformServiceImpl implements ExchangeConfigurationDataReadPlatformService {

    private final JdbcTemplate jdbcTemplate;
    private final ExchangeConfigurationRepository exchangeConfigurationRepository;
    private static Map configMap = new HashMap();

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
            String applyIPOAPI = "";
            String logoutAPI = "";
            String tokenAPI = "";
            String env = "";
            while (rs.next()) {
                String configKey = rs.getString("configkey");
                String value = rs.getString("value");
                if (ExchangeServiceConstants.EXCHANGE_CONFIG_USERNAME.equals(configKey)) {
//                    configMap.put(configKey, value);
                    userName = value;
                } else if (ExchangeServiceConstants.EXCHANGE_CONFIG_BASEAPIURL.equals(configKey)) {
                    password = value;
                } else if (ExchangeServiceConstants.EXCHANGE_CONFIG_BASEAPIURL.equals(configKey)) {
                    baseAPIURL = value;
                } else if (ExchangeServiceConstants.EXCHANGE_CONFIG_APPLYIPOAPI.equals(configKey)) {
                    applyIPOAPI = value;
                } else if (ExchangeServiceConstants.EXCHANGE_CONFIG_LOGOUTAPI.equals(configKey)) {
                    logoutAPI = value;
                } else if (ExchangeServiceConstants.EXCHANGE_CONFIG_TOKENAPI.equals(configKey)) {
                    tokenAPI = value;
                } else if (ExchangeServiceConstants.EXCHANGE_CONFIG_ENV.equals(configKey)) {
                    env = value;
                }
            }
            return new ExchangeConfigurationData(memberID, baseAPIURL, password, userName, applyIPOAPI, logoutAPI, tokenAPI, env);
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
