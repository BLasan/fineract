/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.fineract.portfolio.repaymentwithpostdatedchecks.api;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.fineract.commands.domain.CommandWrapper;
import org.apache.fineract.commands.service.CommandWrapperBuilder;
import org.apache.fineract.commands.service.PortfolioCommandSourceWritePlatformService;
import org.apache.fineract.infrastructure.core.data.CommandProcessingResult;
import org.apache.fineract.infrastructure.core.serialization.DefaultToApiJsonSerializer;
import org.apache.fineract.infrastructure.core.serialization.FromJsonHelper;
import org.apache.fineract.infrastructure.security.service.PlatformSecurityContext;
import org.apache.fineract.portfolio.loanaccount.loanschedule.data.LoanScheduleData;
import org.apache.fineract.portfolio.repaymentwithpostdatedchecks.data.PostDatedChecksData;
import org.apache.fineract.portfolio.repaymentwithpostdatedchecks.domain.PostDatedChecks;
import org.apache.fineract.portfolio.repaymentwithpostdatedchecks.service.RepaymentWithPostDatedChecksReadPlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import javax.print.attribute.standard.Media;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/loans/{loanId}")
@Component
@Scope("singleton")
@Tag(name = "repayment with post dated checks", description = "Repay with post dated checks")
public class RepaymentWithPostDatedChecksApiResource {
    private final PlatformSecurityContext context;
    private final FromJsonHelper fromJsonHelper;
    private final DefaultToApiJsonSerializer<PostDatedChecksData> apiJsonSerializer;
    private final PortfolioCommandSourceWritePlatformService commandsSourceWritePlatformService;
    private final RepaymentWithPostDatedChecksReadPlatformService repaymentWithPostDatedChecksReadPlatformService;

    @Autowired
    public RepaymentWithPostDatedChecksApiResource(final PlatformSecurityContext context,
                                                   final FromJsonHelper fromJsonHelper,
                                                   final DefaultToApiJsonSerializer<PostDatedChecksData> apiJsonSerializer,
                                                   final PortfolioCommandSourceWritePlatformService portfolioCommandSourceWritePlatformService,
                                                   final RepaymentWithPostDatedChecksReadPlatformService repaymentWithPostDatedChecksReadPlatformService) {
        this.context = context;
        this.fromJsonHelper = fromJsonHelper;
        this.apiJsonSerializer = apiJsonSerializer;
        this.commandsSourceWritePlatformService = portfolioCommandSourceWritePlatformService;
        this.repaymentWithPostDatedChecksReadPlatformService = repaymentWithPostDatedChecksReadPlatformService;
    }

    @GET
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public String getPostDatedChecks(@PathParam("loanId") @Parameter(description = "loanId") final Long loanId) {
        this.context.authenticatedUser();
        final List<PostDatedChecksData> postDatedChecksDataList = this.repaymentWithPostDatedChecksReadPlatformService.getPostDatedChecks(loanId);
        return this.apiJsonSerializer.serialize(postDatedChecksDataList);
    }

    @GET
    @Path("{id}")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    public String getPostDatedCheck(@PathParam("id") @Parameter(description = "id") final Long id,
                                    @PathParam("loanId") @Parameter(description = "loanId") final Long loanId) {
        /**
         * TODO: Check the permission to read data.
         */
        this.context.authenticatedUser();
        final PostDatedChecksData postDatedChecksData = this.repaymentWithPostDatedChecksReadPlatformService.getPostDatedCheck(id);
        return this.apiJsonSerializer.serialize(postDatedChecksData);

    }

//    @POST
//    @Consumes({ MediaType.APPLICATION_JSON })
//    @Produces({ MediaType.APPLICATION_JSON})
//    public String addPostDatedChecks(@Parameter(hidden = true) final String apiRequestBodyAsJson) {
//
//        return this.apiJsonSerializer.serialize("");
//    }

    @PUT
    @Path("{postDatedCheckId}")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    public String updatePostDatedChecks(@PathParam("postDatedCheckId") @Parameter(description = "postDatedCheckId") final Long id,
                                        @PathParam("loanId") @Parameter(description = "loanId") final Long loanId,
                                        @Parameter(hidden = true) final String apiRequestBodyAsJson) {

        final CommandWrapper commandRequest = new CommandWrapperBuilder().updatePostDatedCheck(id, loanId).withJson(apiRequestBodyAsJson).build();

        final CommandProcessingResult commandProcessingResult = this.commandsSourceWritePlatformService.logCommandSource(commandRequest);

        return this.apiJsonSerializer.serialize(commandProcessingResult);
    }

    @DELETE
    @Path("{postDatedCheckId}")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    public String deletePostDatedCheck(@PathParam("postDatedCheckId") @Parameter(description = "postDatedCheckId") final Long id,
                                       @PathParam("loanId") @Parameter(description = "loanId") final Long loanId) {
        final CommandWrapper commandRequest = new CommandWrapperBuilder().deletePostDatedCheck(id, loanId).build();

        final CommandProcessingResult commandProcessingResult = this.commandsSourceWritePlatformService.logCommandSource(commandRequest);

        return this.apiJsonSerializer.serialize(commandProcessingResult);
    }


}
