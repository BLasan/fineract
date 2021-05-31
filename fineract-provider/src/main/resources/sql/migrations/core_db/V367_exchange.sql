--
-- Licensed to the Apache Software Foundation (ASF) under one
-- or more contributor license agreements. See the NOTICE file
-- distributed with this work for additional information
-- regarding copyright ownership. The ASF licenses this file
-- to you under the Apache License, Version 2.0 (the
-- "License"); you may not use this file except in compliance
-- with the License. You may obtain a copy of the License at
--
-- http://www.apache.org/licenses/LICENSE-2.0
--
-- Unless required by applicable law or agreed to in writing,
-- software distributed under the License is distributed on an
-- "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
-- KIND, either express or implied. See the License for the
-- specific language governing permissions and limitations
-- under the License.
--

CREATE TABLE IF NOT EXISTS `m_exchange_configuration`(
    `id` INTEGER(20) NOT NULL AUTO_INCREMENT UNIQUE,
    `configkey` VARCHAR(25) NOT NULL,
    `value` VARCHAR(255) DEFAULT NULL,
    `exchange_id` VARCHAR(10) DEFAULT NULL,
    `description` VARCHAR(255) DEFAULT NULL
);

INSERT INTO `m_exchange_configuration` (`id`, `configkey`, `value`, `exchange_id`, `description`) VALUES ('1', 'Password', 'Admin@123456', '1', '');

INSERT INTO `m_exchange_configuration` (`id`, `configkey`, `value`, `exchange_id`, `description`) VALUES ('2', 'SubscriptionId', '179', '1', '');

INSERT INTO `m_exchange_configuration` (`id`, `configkey`, `value`, `exchange_id`, `description`) VALUES ('3', 'SubscriptionKey', '', '1', '');

INSERT INTO `m_exchange_configuration` (`id`, `configkey`, `value`, `exchange_id`, `description`) VALUES ('4', 'Username', 'C149804', '1', '');

INSERT INTO `m_exchange_configuration` (`id`, `configkey`, `value`, `exchange_id`, `description`) VALUES ('5', 'baseurl', 'https://uat.bseindia.in/ibbsapi/ibbsapiservice.svc', '1', '');

INSERT INTO `m_exchange_configuration` (`id`, `configkey`, `value`, `exchange_id`, `description`) VALUES ('5', 'tokenAPI', 'Login', '1', '');

INSERT INTO `m_permission`
(`grouping`, `code`, `entity_name`, `action_name`, `can_maker_checker`) VALUES
('portfolio', 'SAVE_BSEIQDATA', 'BSEIQDATA', 'SAVE', '0');

INSERT INTO `m_permission`
(`grouping`, `code`, `entity_name`, `action_name`, `can_maker_checker`) VALUES
('portfolio', 'SAVE_NSEIQDATA', 'NSEIQDATA', 'SAVE', '0');
